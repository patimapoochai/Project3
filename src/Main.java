import java.util.ArrayList;
import java.util.Random;

import com.sun.glass.events.KeyEvent;
//written by the three musketeers
public class Main {
	// don't restrict these variables
	static final int RES_X = 1024;
	static final int RES_Y = 768;
	static Player player;
	static Platform test;
	static ArrayList<Platform> platforms = new ArrayList();
	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	static Random rand = new Random();
	static Background background;
	static int difficulty;
	static int scrollSpeed = 1;
	static Enemy eTest;
	static final String[] soundNames = {"Ninja Gaiden (NES) Music - Act 4 Part 2 loopable.wav", "jump.wav", "damage.wav", "death.wav", "determination.wav"};
	static SoundEffects sounds;
	
	static int play = 0;
	
	static EZImage title;
	static EZImage titleback; 
	static EZImage heart3;
	static EZImage heart2;
	static EZImage heart1;
	static EZImage gameOver;
	static EZSound opening = EZ.addSound("Ninja Gaiden (NES) Music - Act 2 Part 1 loopable.wav");

	static final int MINUMUM_CHANCE = 85;

	// All of the initialization goes here
	public static void setup() {
		//creates images of hearts
		heart1 = EZ.addImage("health.png", 25, 30);
		heart2 = EZ.addImage("health.png", 75, 30);
		heart3 = EZ.addImage("health.png", 125, 30);
		sounds = new SoundEffects(soundNames);
		// Background background = new Background("biggrid.jpg", RES_X, RES_Y);
		player = new Player("jumping_right.png", "jumping_left.png", RES_X / 2, RES_Y / 2 - 300, sounds);

		// test = new Platform("platform.png", player,RES_X/2,RES_Y/2 + 200);

		for (int i = 100; i < 600; i = i + 100) {
			Platform platform = new Platform("Platform.png", player, rand.nextInt(RES_Y - 30) + 30, i);
			platforms.add(platform);
		}

//		eTest = new Enemy("enemyPH.png",platforms,true);

		// variables for lowest platform calculation
		int lowestY = 0;
		int lowestX = RES_X;

		// set the lowest y coordinate of the platform
		for (Platform i : platforms) {
			if (i.getPosY() > lowestY) {
				lowestY = i.getPosY();
				lowestX = i.getPosX();
			}
		}

		// spawn player on top of the lowest platform platform
		player.setPosition(lowestX, lowestY - (player.getLegsLength() + 20));

		background = new Background("clouds.png", RES_X, RES_Y);

		// addPlatform(10, 15);
		// addPlatform(20, 25);
		// addPlatform(50, 75);

		// difficulty for how far the platform can be

		difficulty = 40;
		addPlatform(difficulty);

	}

	// all the update goes here
	public static void update() {
		player.update();
		for (Platform i : platforms) {
			i.update();
		}
		background.scroll(scrollSpeed + 1);
		for (Platform i : platforms)
			i.scroll(scrollSpeed);
		
		if (difficulty > 100)
			difficulty = 100;
		else
		{
			// difficulty = player.getPlatformsJumped() * 2;
			scrollSpeed = (difficulty / 10) + 1;
		}
		
		//System.out.println(eTest.animFrame);
		
		for (int i = 0; i < platforms.size(); i++)
		{
			boolean check = testOnScreenBool(platforms.get(i));
			if (check == false)
			{
				removePlatform(i);
				addPlatform(difficulty);

			}
		}

		//updating the enemies only there is at least 1 in array list
		if (enemies.size() > 0) {
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).update(player);
				if (enemies.get(i).getBelowScreenStatus()) {
					enemies.get(i).remove();
					enemies.remove(i);
				}
			}
		}
//		System.out.println(enemies.size());
		//checks player health and removes heart images when damage is taken
		if (player.getHealth() == 2) {
			heart3.hide();
		}
		else if (player.getHealth() == 1) {
			heart2.hide();
		}
		else if (player.getHealth() == 0){
			play = 2;
			sounds.play(3);
		}
		sounds.playOnCondition(0, Graphic.isPlayerAboveDeathLine(player));
		//checks to see if the player has died
		if(Graphic.isPlayerAboveDeathLine(player) == false) {
			play = 2;
			sounds.play(3);
		}
	}
	//for testing how many platform are on screen
	public static boolean testOnScreenBool(Platform p) {
		/* int index = 0;
		for (Platform i : platforms) {
			System.out.print( index + "is " + i.getOnScreen() + " ");
			System.out.println("");
			index++;
		}
		
		index = 0; */

		if (p.getPosY() > RES_Y)
			return false;
		else
			return true;
	}

	public static void testOnScreen() {
		int index = 0;
		for (Platform i : platforms) {
			System.out.print( index + "is " + i.getOnScreenStatus() + " ");
			System.out.println("");
			index++;
		}
	}
	
	public static void addPlatform(int difficulty)
	/*
	 * int lowerBound: sets the lowest amount from the top of the screen a platform
	 * can appear int upperBound: sets the highest amount from the top of the screen
	 * a platform can appear
	 */
	{
		// int offset = upperBound - lowerBound; // distance between the two
		int y = rand.nextInt(difficulty); // will generate a number between zero and the maximum offset
		int x = rand.nextInt(RES_X); // generate a random new platform
		Platform platform = new Platform("Platform.png", player, x, 0 - y);
		platforms.add(platform);
		System.out.println(platform.getPosX());
		System.out.println(platform.getPosY());
		spawn(platform);
	}

	public static void removePlatform(int i)
	/*
	 * int i: the index in the platforms array the platform to be closed is located
	 * at
	 */
	{
		platforms.get(i).close();
		platforms.remove(i);
		difficulty += 3;
	}

	//Use to spawn new enemy
	public static void spawn(Platform platform) {
		//Spawn chance calculation
		if (rand.nextInt(99) + 1 > MINUMUM_CHANCE) {
			//add an enemy into the array
			Enemy temp = new Enemy("shuriken.png", RES_X + 30, RES_Y + 30, platforms, true);
			temp.spawnOnPlatform(platform);
			enemies.add(temp);
			//For testing
			System.out.println("Spawned");
		}
	}

	// Main part of the program
	public static void main(String[] args) {
		EZ.initialize(RES_X, RES_Y);
		//creates title screen and plays opening sound
		titleback = EZ.addImage("titlescreenback.png", RES_X/2, RES_Y/2);
		title = EZ.addImage("titlescreen.png", RES_X/2, RES_Y/2);
		opening.play();
		while(play == 0) {
			//starts setup on game start
			if (EZInteraction.isKeyDown(KeyEvent.VK_SPACE)) {
				title.hide();
				titleback.hide();
				setup();
				play = 1;
			}
			System.out.println(play); //somehow this makes the program work, not sure why, 
									  //but the moment it is removed, the program no longer plays
		}
		//stops music and plays game
		opening.stop();
		while (play == 1) {
			update();

			// Do not change the refresh
			EZ.refreshScreen();
		}
		//plays the gameover music and changes screen to game over screen
		if (play == 2) {
			gameOver = EZ.addImage("gameover.png", RES_X/2, RES_Y/2);
			sounds.play(4);
		}

	}
}
