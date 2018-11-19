import java.util.Random;

public class Platform {
	int posX;
	int posY;
	Player player;
	
	EZImage picture;
	
	Random random = new Random();
	
	Platform (String filename, Player player){
		posX = 1024/2; //random.nextInt(1024);
		posY = 600; //random.nextInt(768);
		
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
