package pla;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 

public class Sound {
	String Thomas = "/home/tozenda/COURS/RICM3/S6/PLA/PLA/Resources/";
	String Najwa = "Resources/";
	String Anouar = "Resources/";
	String Jo = "/home/ferreira/Bureau/POO/PLA/Resources/";
	String Paul = "home/doublean/git/PLA/Resources/";
	String Shoo = "/Users/fathinsyuhadaabubakar/Downloads/PLA-master-2/Resources/";
	String Path = Najwa;
	private Clip clip;
    public static Sound bgmusic = new Sound("backmusic.wav");

    public Sound (String fileName) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(Path + fileName));

            clip = AudioSystem.getClip();

            clip.open(ais);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

 
//play once
    public void play() {

        try {

            if (clip != null) {

                new Thread() {

                    public void run() {

                        synchronized (clip) {

                            clip.stop();

                            clip.setFramePosition(0);

                            clip.start();

                        }

                    }

                }.start();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

     

    public void stop(){

        if(clip == null) return;

        clip.stop();

    }

//loop music
    public void loop() {

        try {

            if (clip != null) {

                new Thread() {

                    public void run() {

                        synchronized (clip) {

                            clip.stop();

                            clip.setFramePosition(0);

                            clip.loop(Clip.LOOP_CONTINUOUSLY);

                        }

                    }

                }.start();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public boolean isActive(){

        return clip.isActive();

    }
}

