package elements;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Sound {

    public static void getSound(Class<?> clazz) {
        URL resources = clazz.getResource("/roulette_sound.mp3");

        if (resources != null) {
            String audioPath = resources.toExternalForm();

            Media media = new Media(audioPath);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
        }
    }

}
