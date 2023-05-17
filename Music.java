import javax.sound.sampled.*;
import java.io.*;

public class Music {
    private Clip clip;

    public Music() {
        try {
            File file = new File("C:\\Users\\ASUS\\JavaWorkspace\\untitled\\AircraftBattle\\src\\Music\\BackgroundMusic.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }
}