
public class Main {
	static final int RES_X = 1024;
	static final int RES_Y = 768;
	static Player player;
	static Platform test;
	
	
	//All of the initialization goes here 
	public static void setup(){
		EZ.initialize(RES_X, RES_Y);
		player = new Player("placeHolderLeft.png","placeHolderRight.png",RES_X/2,RES_Y/2-300);
		
		test = new Platform("platform.png", player);
	}
	
	public static void update() {
		player.update();
		test.update();
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
