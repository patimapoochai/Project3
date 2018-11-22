import java.util.ArrayList;
import java.util.Random;


public class Main {
	//don't restric these variables
	static final int RES_X = 1024;
	static final int RES_Y = 768;
	static Player player;
	static Platform test;
	static ArrayList<Platform> platforms = new ArrayList();
	static Random rand = new Random();
	static Background background;
	
	
	//All of the initialization goes here 
	public static void setup(){
		EZ.initialize(RES_X, RES_Y);
		//Background background = new Background("biggrid.jpg", RES_X, RES_Y);
		player = new Player("placeHolderLeft.png","placeHolderRight.png",RES_X/2,RES_Y/2-300);
		
		//test = new Platform("platform.png", player,RES_X/2,RES_Y/2 + 200);
		
		for (int i = 100; i < 600; i = i + 100)
		{
			Platform platform = new Platform("platform.png", player, rand.nextInt(RES_Y - 30) + 30, i);
			platforms.add(platform);
		}
		
		//variables for lowest platform calculation
		int lowestY = 0;
		int lowestX = RES_X;
		
		// set the lowest y coordinate of the platform
		for (Platform i : platforms) {
			if (i.getPosY() > lowestY) {
				lowestY = i.getPosY();
				lowestX = i.getPosX();
			}
		}
		
		//spawn player on top of the lowest platform platform
		player.setPosition(lowestX, lowestY - (player.getLegsLength()+20));
		
		background = new Background("biggrid.jpg", RES_X, RES_Y);
		
		
	}
	
	//all the update goes here
	public static void update() {
		player.update();
		for (Platform i : platforms) {
			i.update();
		}
		testOnScreen();
		
		//test.update();
		background.scroll();
		for (Platform i : platforms)
			i.scroll(1);
	}
	
	//for testing how many platform are on screen
	public static void testOnScreen() {
		int index = 0;
		for (Platform i : platforms) {
			System.out.print( index + "is " + i.getOnScreen() + " ");
			System.out.println("");
			index++;
		}
		
		index = 0;
	}

	//Main part of the program
	public static void main(String[] args) {
		setup();

		
		while (true) {
			update();
			
			//Do not change the refresh
			EZ.refreshScreen();
		}

	}
}
