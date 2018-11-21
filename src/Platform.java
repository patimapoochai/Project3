public class Platform {
	protected int posX;
	protected int posY;
	protected Player player;
	protected EZImage picture;
	// Random random = new Random();
	private boolean isOffScreen;
	
	Platform (String filename, Player player, int posX, int posY){
		this.posX = posX; //random.nextInt(1024);
		this.posY = posY; //random.nextInt(768);
		
		picture = EZ.addImage(filename, posX, posY);
		
		this.player = player;
	}
	
	public void update(){
		playerCollision();
		offScreenDetection();
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	//if the platform is touching player
	private boolean isInPlayer() {
		if (feetTouching(player.getFeetPoints())) {
			return true;
		} else {
			return false;
		}
	}
	
	//make the player bounce if they touch the platform
	private void playerCollision() {
		if (isInPlayer()) {
			player.bounce();
		}
	}
	
	
	//if the bottom points are inside the platforms
	private boolean feetTouching(int[][] array) { //what a lewd name huh?
		boolean state = false;
		
		for (int col = 0;col < 3;col++) {
			if (picture.isPointInElement(array[col][0], array[col][1])) {
				state = true;
			} else {
				state = false;
			}
		}
		
		return state;
	}
	
	//return true the platform is on screen
	private void offScreenDetection() {
		if ((posX > 0 && posX < Main.RES_X)||(posY > 0 && posY < Main.RES_Y) == false) {
			isOffScreen = true;
		} else {
			isOffScreen = false;
		}
	}
	
	//return the on-screen state
	public boolean getOffScreen() {
		return isOffScreen;
	}
}
