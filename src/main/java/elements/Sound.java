package elements;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

    public static void getSound(Class<?> clazz) {
        String audioPath = clazz.getResource("/roulette_sound.mp3").toExternalForm();

        Media media = new Media(audioPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

}
