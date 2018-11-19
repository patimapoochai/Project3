import java.util.ArrayList;
import java.util.Random;


public class Main {
	static final int RES_X = 1024;
	static final int RES_Y = 768;
	static Player player;
	static Platform test;
	static ArrayList<Platform> platforms = new ArrayList();
	
	
	//All of the initialization goes here 
	public static void setup(){
		EZ.initialize(RES_X, RES_Y);
		player = new Player("placeHolderLeft.png","placeHolderRight.png",RES_X/2,RES_Y/2-300);
		
		// test = new Platform("platform.png", player);
		Random rand = new Random();
		
		for (int i = 100; i < 600; i = i + 100)
		{
			Platform platform = new Platform("platform.png", player, rand.nextInt(RES_Y - 30) + 30, i);
			platforms.add(platform);
		}
		
		
	}
	
	public static void update() {
		player.update();
		for (Platform i : platforms)
			i.update();
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
