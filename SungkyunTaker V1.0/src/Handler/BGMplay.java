package Handler;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGMplay {
	
	AudioInputStream audioInput;
	Clip clip;

	public void playLoop(String path) {
		try {
			File file = new File(path);
			audioInput = AudioSystem.getAudioInputStream(file);
			try {
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void playTemp(String path) {
		try {
			File file = new File(path);
			audioInput = AudioSystem.getAudioInputStream(file);
			try {
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {

	}
}
