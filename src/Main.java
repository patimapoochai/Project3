
public class Main {
	static final int RES_X = 1024;
	static final int RES_Y = 768;
	static Player player;
	
	
	//All of the initialization goes here 
	public static void setup(){
		EZ.initialize(RES_X, RES_Y);
		player = new Player();
	}

	//Main part of the program
	public static void main(String[] args) {
		setup();
		
		
		while (true) {
			
			
			//Do not change the refresh
			EZ.refreshScreen();
		}

	}
}
