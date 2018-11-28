import java.util.ArrayList;
import java.util.Random;

//Enemies class
//Hacked by Patima Poochai
public class Enemy extends Graphic {
	private ArrayList<Platform> platforms;
	private Random random = new Random();
	
	//points that would be tested against the player
	//the columns are each different points and rows are x and y coordinates
	private int[][] collisionSet1 = new int[5][2];
	private int[][] collisionSet2 = new int[8][2];
	
	//keep track of an animation's frame
	public int animFrame;
	//keep track of direction of an animation
	private int idleDirection = 1;
	
	private int minimumForSuccess = 70;
	
	//
	protected boolean wandering;
	protected static final int CONSTANT_SPEED = 5;
	protected int wanderingDirection = 1;
	//constructor if you want a specific coordinate
	Enemy(String filename,int x,int y,ArrayList<Platform> platformArray, boolean wanderingState){
		super(filename, x, y);
		animFrame = 0;
		
		wandering = wanderingState;
		
		platforms = platformArray;
	}
	
	//constructor for when you want to spawn on a random platform
	Enemy(String filename, ArrayList<Platform> platformArray,boolean wanderingState){
		super(filename,Main.RES_X/2,Main.RES_Y/2);
		
		platforms = platformArray;
		wandering = wanderingState;
		
		findRandomPlatform();
	}
	
	public void update(Player player) {
		if (Main.constantSpeedDemo) {
			scroll(1);
//			minimumForSuccess = 100;
		} else {
			scroll(Main.scrollSpeed);
		}
		
		if (wandering) {
			wander();
		}
		super.checks();
		set1Update(player.getImage().getWidth(), player.getImage().getHeight());
		collisionCheck(player);
		
	}
	
	public void spawnOnPlatform(Platform platform) {
		super.setPosition(platform.getPosX(), platform.getPosY()-60);
	}
	
	//find a platform (need to add empty check)
	private void findRandomPlatform() {
		//get a random number
		int index = random.nextInt(platforms.toArray().length-1);
		spawnOnPlatform(platforms.get(index));
	}
	
	//will make the input of scroll() such that the enemy float up and down
	private double scrollWithAnimation(int length,int animSpeed) {
		if (animFrame > 10) {
			idleDirection = -idleDirection;
		} else if (animFrame < 0) {
			idleDirection = -idleDirection;
		}
		
		animFrame = animFrame + idleDirection;
		
		int speed = length + (idleDirection * animSpeed);
		
		return speed;
	}
	
	//scroll the enemy down
	protected void scroll(int length)
	{
		//this.posX = this.picture.getXCenter();
		posY = this.picture.getYCenter() + (int)scrollWithAnimation(length, 1);
		picture.translateTo(this.posX, this.posY);
	}
	
	//modifying the points to position it around the image
	private void toggleAddSubtract(int value,int startIndex, int endIndex,boolean xOrY) {
		value = value * 7/8/*always a fraction*/; // how close are the points to the borders of the image
		
		//x is true, y is false
		if (xOrY) {
			collisionSet1[startIndex][0] += value;
			collisionSet1[endIndex][0] -= value;
		} else {
			collisionSet1[startIndex][1] += value;
			collisionSet1[endIndex][1] -= value;
		}
	}
	
	//set all x,y points in a 2d array to current x, y position
	private void setAllPointsToXAndY(int[][] set) {
		for (int row = 0;row < 2;row++) {
			for (int col = 0;col < 5;col++) {
				if (row == 0) {
					set[col][row] = posX;
				} else if (row == 1) {
					set[col][row] = posY;
				}
			}
		}
	}
	

	//updating each points
	protected void set1Update(int width,int height) {
		//make all the points the middle of the image first
		setAllPointsToXAndY(collisionSet1);
		
		//then add some calculations to points after index 0 to make it top, bottom, left, and right border
		//of the image relative to posX and posY
		toggleAddSubtract(picture.getWidth()/2, 1, 2,true);
		toggleAddSubtract(picture.getHeight()/2, 3, 4,false);
		
		//for testing		
//		for (int col = 0;col < 5;col++) {
//			for (int row = 0;row < 2;row++) {
//				System.out.print(collisionSet1[col][row]+ ", ");
//			}
//		}
	}
	
	//if we have time
	protected void set2Update() {
		setAllPointsToXAndY(collisionSet2);
		
		
	}
	
	//check each points against the player image
	protected void collisionCheck(Player player) {
		for (int col = 0;col < 5;col++) {
				if(player.isPointInPlayer(collisionSet1[col][0], collisionSet1[col][1])) {
					player.damage(1);
			}
		}
	}
	
	//the enemy will move from left to right
	protected void wander() {
		if (posX > 1024 || posX < 0) {
			wanderingDirection = -wanderingDirection;
		}
		
		posX = posX + (wanderingDirection * (Main.scrollSpeed + CONSTANT_SPEED));
	}
	
	//Check if an enemy should spawn on a platform
	public void spawnOnPlatformCreation(Platform platform) {
		if (random.nextInt(99)+1 > minimumForSuccess) {
			spawnOnPlatform(platform);
		}
	}
	
	//remove the EZImage picture
	public void remove() {
		EZ.removeEZElement(picture);
	}
}
