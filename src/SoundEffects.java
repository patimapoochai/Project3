import java.util.ArrayList;

// this class is basically an array list of sounds
// all the array loading is done in here instead of the main class for cleanliness
// Channeled by Patima Poochai
public class SoundEffects {
	//array list of sounds
	ArrayList<Sound> sounds;
	//array for any function that need EZSound only
	EZSound[] EZSounds;
	int ArrayLength;
	
	//take in a array of file names 
	SoundEffects(String[] soundEffectNames){
		ArrayLength = soundEffectNames.length;
		sounds = new ArrayList<Sound>();
		EZSounds = new EZSound[ArrayLength];
		
		//load each sound into sound[] slots
		for (int i = 0;i < soundEffectNames.length;i++) {
			Sound sound = new Sound(soundEffectNames[i]);
			sounds.add(sound);
		}
		
		//load each sound into an EZSound Array
		for (int i = 0;i < sounds.size();i++) {
			EZSounds[i] = sounds.get(i).getSound();
		}
	}
	
	//play certain sounds inside the array list
	void play(int soundIndex) {
		sounds.get(soundIndex).play();
	}
	//stop certain sound
	void stop(int soundIndex) {
		sounds.get(soundIndex).stop();
	}
	
	//play the file on a condition
	void playOnCondition(int soundIndex, boolean condition) {
		sounds.get(soundIndex).playOnCondition(condition);
	}
	
	void loop(int soundIndex) {
		sounds.get(soundIndex).loop();
	}
	
	//return an array of EZSound objects
	EZSound[] getEZSoundArray() {
		return EZSounds;
	}
}
