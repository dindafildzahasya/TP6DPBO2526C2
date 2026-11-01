import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioPlayer {
    private Clip clip;

    // Constructor untuk memuat file audio
    public AudioPlayer(String resourcePath) {
        try {
            URL audioURL = getClass().getResource(resourcePath);

            if (audioURL == null) {
                System.out.println("File audio tidak ditemukan: " + resourcePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioURL);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            audioStream.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Gagal memuat audio: " + e.getMessage());
        }
    }

    // Method untuk memainkan audio
    public void play(boolean loop) {
        if (clip == null) {
            return;
        }

        if (clip.isRunning()) {
            clip.stop();
        }

        clip.setFramePosition(0);

        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }

    // Method untuk menghentikan audio
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}