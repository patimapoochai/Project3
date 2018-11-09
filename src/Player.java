
public class Player {
	//positions
	int posX;
	int posY;
	int speed;
	//is the player moving?
	boolean moving;
	EZImage playerLeft;
	EZImage playerRight;
	
	//this would keep the turn state. true is right, false is left
	boolean turnState;
	
	Player(String imageLeft, String imageRight,int x, int y){
		//set position
		posX = x;
		posY = y;
		speed = 10;
		//which side the player is facing
		turnState = true;
		
		
		//add image
		playerLeft = EZ.addImage(imageLeft, posX, posY);
		playerRight = EZ.addImage(imageRight, posX, posY);
		
		//hide both images
		playerLeft.hide();
		playerRight.hide();
	}
	
	//anything that need to be updated each frame
	void update() {
		isMoving();
		movement();
		facingDirection();
	}
	
	//set x and y position
	private void setPosition(int x, int y){
		posX = x; posY = y;
		setImagePosition(x,y);
	}
	
	//set the image to certain x and y value
	private void setImagePosition(int posx, int posy) {
		playerLeft.translateTo(posx, posy);
		playerRight.translateTo(posX, posY);
	}
	
	//is the player touching the x and y point?
	boolean isIn(int x,int y) {
		boolean state = false;
		if (playerLeft.isPointInElement(x, y) || playerRight.isPointInElement(x, y)) {
			state = true;
		}
		return state;
	}
	
	//return if any key is pressed
	void isMoving() {
		if (EZInteraction.isKeyDown('w') || EZInteraction.isKeyDown('a') || EZInteraction.isKeyDown('s') || EZInteraction.isKeyDown('d')) {
			moving = true;
		} else {
			moving = false;
		}
	}
	
	//WASD to move the player
	void movement() {
		if (moving) {
			
			if (EZInteraction.isKeyDown('w')) {
				moveUp(speed);
			}
			
			if (EZInteraction.isKeyDown('s')) {
				moveDown(speed);
			}
			
			if (EZInteraction.isKeyDown('a')) {
				moveLeft(speed);
			}
			
			if (EZInteraction.isKeyDown('d')) {
				moveRight(speed);
			}
		}
	}
	
	//determine which side the player is facing
	void facingDirection() {
		if (turnState) {
			playerRight.show();
			playerLeft.hide();
		} else {
			playerRight.hide();
			playerLeft.show();
		}
	}
	
	//movement
	public void moveLeft(int step){
		posX=posX-step;
		setImagePosition(posX,posY);
		turnState = false;
	}
	public void moveRight(int step){
		posX=posX+step;
		setImagePosition(posX,posY);
		turnState = true;
	}
	public void moveUp(int pace){
		posY=posY-pace;
		setImagePosition(posX,posY);
	}
	public void moveDown(int step) {
		posY=posY+step;
		setImagePosition(posX,posY);
	}
}
