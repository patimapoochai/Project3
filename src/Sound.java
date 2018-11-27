// bascially just a fancy EZSound class
//Whisked into being by Patima Poochai
public class Sound {
	//the file name
	String soundNames;
	//EZSound Object
	EZSound sound;
	//the condition
	boolean activationCondition;
	
	
	Sound(String name){
		soundNames = name;
		
		sound = new EZSound(name);
	}
	//set the sound
	void setSound(EZSound sound) {
		this.sound = sound;
	}
	//get the sound
	EZSound getSound() {
		return sound;
	}
	//play the sound
	void play() {
		sound.play();
	}
	//stop da sound
	void stop() {
		sound.stop();
	}
	
	void loop() {
		sound.loop();
	}
	
	//loop the sound on certain condition
	void playOnCondition(boolean condition) {
		//play the sound when condition is triggered
		if (sound.getFramePosistion() == 0 && condition == true) {
			sound.play();
		//loop the sound while condition is triggered
		} else if (sound.getFramePosistion() >= sound.getFrameLength() && condition == true) {
				sound.play();
		//stop when the condition stops
		} else if (sound.getFramePosistion() > 0 && condition == false) {
				sound.stop();
		
		}
	}
	
}
