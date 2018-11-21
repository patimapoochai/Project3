
public class Player {
	// how fast the player falls (always positive)
	private static final int GRAVITY = 1;
	// overall bounce multiply-er (always positive)
	private static final int RATE = 2;
	//player strafe speed
	private static final int SPEED = 20;

	// positions
	int posX;
	int posY;
	int speed;
	// is the player moving?
	boolean moving;
	// images
	private EZImage playerLeft;
	private EZImage playerRight;

	private int verticalVelocity;
	
	//row is the left, middle, right points(left to right). col is x, y (top to bottom)
	private int[][] feet = new int[3][2];

	// this would keep the turn state. true is right, false is left
	private boolean turnState;

	Player(String imageLeft, String imageRight, int x, int y) {
		// set position
		posX = x;
		posY = y;
		// which side the player is facing
		turnState = true;

		verticalVelocity = 10;

		// add image
		playerLeft = EZ.addImage(imageLeft, posX, posY);
		playerRight = EZ.addImage(imageRight, posX, posY);

		// hide both images
		playerLeft.hide();
		playerRight.hide();
	}

	// anything that need to be updated each frame
	public void update() {
		isMoving();
		movement();
		facingDirection();
		fall();
		verticalVelocityUpdate();
		bottomPoints();
	}

	// set x and y position
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
		setImagePosition(x, y);
	}

	// set the image to certain x and y value
	private void setImagePosition(int posx, int posy) {
		playerLeft.translateTo(posx, posy);
		playerRight.translateTo(posX, posY);
	}

	// is the player touching the x and y point?
	boolean isIn(int x, int y) {
		boolean state = false;
		if (playerLeft.isPointInElement(x, y) || playerRight.isPointInElement(x, y)) {
			state = true;
		}
		return state;
	}

	// return if any key is pressed
	private void isMoving() {
		if (EZInteraction.isKeyDown('w') || EZInteraction.isKeyDown('a') || EZInteraction.isKeyDown('s')
				|| EZInteraction.isKeyDown('d')) {
			moving = true;
		} else {
			moving = false;
		}
	}

	// translation movement
	private void moveLeft(int step) {
		posX = posX - step;
		setImagePosition(posX, posY);
		turnState = false;
	}

	private void moveRight(int step) {
		posX = posX + step;
		setImagePosition(posX, posY);
		turnState = true;
	}

	// determine which side the player is facing
	private void facingDirection() {
		if (turnState) {
			playerRight.show();
			playerLeft.hide();
		} else {
			playerRight.hide();
			playerLeft.show();
		}
	}

	// Actual movement, A,D to move the player
	private void movement() {
		if (moving) {

			if (EZInteraction.isKeyDown('a')) {
				moveLeft(SPEED);
			}

			if (EZInteraction.isKeyDown('d')) {
				moveRight(SPEED);
			}
			
		}
	}
	
	//bounce the player
	public void bounce() {
		// player will rise
		verticalVelocity = -20;
	}

	//decrease the bounce overtime
	private void verticalVelocityUpdate() {
		// at terminal velocity
		if (verticalVelocity < 10) {
			// the gravity will overtime pull player down
			verticalVelocity = verticalVelocity + GRAVITY;
		}
	}

	//newton's law or something
	private void fall() {
		posY = posY + (RATE * verticalVelocity);
		setImagePosition(posX, posY);
	}
	
	//update the bottom collision points
	private void bottomPoints() {
		feet[0][0] = posX - (playerLeft.getWidth()/2);
		feet[0][1] = posY + (playerLeft.getHeight()/2);
		
		feet[1][0] = posX + (playerLeft.getWidth()/2);
		feet[1][0] = posY + (playerLeft.getHeight()/2);
		
		feet[2][0] = posX;
		feet[2][1] = posY + (playerLeft.getHeight()/2);
	}
	
	public int[][] getFeetPoints() {
		return feet;
	}

}
