package elements.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sound {

    public static Class<?> clazz;

    private static final String ROULETTE_SOUND = "roulette_sound.mp3";
    private static final String CHIPS_SOUND = "chips_sound.mp3";

    private static final Map<String, Media> MEDIAS = new HashMap<>();

    private static Media getMedia(String nameSound) {
        Media media = null;

        if (clazz != null && !nameSound.isEmpty()) {
            if (isMedia(nameSound)) {
                media = MEDIAS.get(nameSound);
            } else {
                URL resources = clazz.getResource("/" + nameSound);

                if (resources != null) {
                    String audioPath = resources.toExternalForm();

                    media = new Media(audioPath);
                    MEDIAS.put(nameSound, media);
                }
            }
        }

        return media;
    }

    private static boolean isMedia(String nameSound) {
        return MEDIAS.containsKey(nameSound);
    }

    public static void playSound(String nameSound) {
        Media media = getMedia(nameSound);

        if (media != null) {
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
        }
    }

    public static double getRouletteSoundDuration() {
        return 13.0;
    }

    public static double getChipsSoundDuration() {
        return 3.0;
    }

    public static void playRouletteSound() {
        playSound(ROULETTE_SOUND);
    }

    public static void playChipsSound() {
        playSound(CHIPS_SOUND);
    }

}
