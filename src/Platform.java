public class Platform {
	protected int posX;
	protected int posY;
	Player player;
	EZImage picture;
	// Random random = new Random();
	
	Platform (String filename, Player player, int posX, int posY){
		this.posX = posX; //random.nextInt(1024);
		this.posY = posY; //random.nextInt(768);
		
		picture = EZ.addImage(filename, posX, posY);
		
		this.player = player;
	}
	
	public void update(){
		playerCollision();
	}
	
	//if the platform is touching player
	private boolean isInPlayer() {
		if (feetTouching()) {
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
	private boolean feetTouching() { //what a lewd name huh?
		if (picture.isPointInElement(player.feetX, player.feetY) || picture.isPointInElement(player.feetLeftX, player.feetLeftY) || picture.isPointInElement(player.feetRightX, player.feetRightY)) {
			return true;
		} else {
			return false;
		}
	}
}
