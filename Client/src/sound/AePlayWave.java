package sound;

import java.io.File; 
import java.io.IOException; 
import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException; 
 
/**
 * 
 * Class from anyexample.com of playing Sound File Asychronously From the original website's description
 *  (found at http://www.anyexample.com/programming/java/java_play_wav_sound_file.xml ) 
 *  "Our class extends Java Thread class to be asynchronous. There are two constructors - 
 *  AePlayWave(String wavfile) with has only one argument - file name, and AePlayWave(String wavfile, Position p),
 *   which takes file name and Position constant, either Position.LEFT or Position.RIGHT -- this is simple balance control,
 *	which can toggle only left or right channel of stereo sound (useful for storing two different sounds in single wave file). 
 *  Various javax.sound.sampled classes are used to prepare audio input stream and audio data line.
 *   Afterwards, we simply read and write (through 512Kb buffer, which is suitable for most formats) sound data from file to sound device.
 *
 */
public class AePlayWave extends Thread { 
 
    private String filename;
    private Position curPosition;
    private boolean stop;
    private final int EXTERNAL_BUFFER_SIZE = 64; // 0.015625Kb 

	public void stopMusic() {
		this.stop = true;
	}
 
    enum Position { 
        LEFT, RIGHT, NORMAL
    };
 
    public AePlayWave(String wavfile) { 
    	stop = false;
        filename = wavfile;
        curPosition = Position.NORMAL;
    } 
 
    public AePlayWave(String wavfile, Position p) { 
        filename = wavfile;
        curPosition = p;
    } 
 
    public void run() { 
 
        File soundFile = new File(filename);
        if (!soundFile.exists()) { 
            System.err.println("Wave file not found: " + filename);
            return;
        } 
 
        AudioInputStream audioInputStream = null;
        try { 
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) { 
            e1.printStackTrace();
            return;
        } catch (IOException e1) { 
            e1.printStackTrace();
            return;
        } 
 
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
        try { 
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) { 
            e.printStackTrace();
            return;
        } catch (Exception e) { 
            e.printStackTrace();
            return;
        } 
 
        if (auline.isControlSupported(FloatControl.Type.PAN)) { 
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) 
                pan.setValue(1.0f);
            else if (curPosition == Position.LEFT) 
                pan.setValue(-1.0f);
        } 
 
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
 
        try { 
            while (!stop && nBytesRead != -1) { 
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) 
                    auline.write(abData, 0, nBytesRead);
            } 
        } catch (IOException e) { 
            e.printStackTrace();
            return;
        } finally { 
            auline.drain();
            auline.close();
        } 
 
    } 
} 
