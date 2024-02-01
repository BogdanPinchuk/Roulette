package elements;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ImageShowing {

    public static Class<?> clazz;
    public static String nameFolder;

    public static double scaling = 2.0;
    public static double duration = 1.0;
    private static final String MORNING = "m.jpg";
    private static final String AFTERNOON = "a.jpg";
    private static final String EVENING = "e.jpg";
    private static final String NIGHT = "n.jpg";
    private static final String[] DAY = {MORNING, AFTERNOON, EVENING, NIGHT};

    private static final Map<String, Image> IMAGES = new HashMap<>();
    private static int currentImageIndex = 0;
    private static ImageView imageView;

    private static Image getImage(String nameImage, String nameFolder) {
        Image image = null;

        if (clazz != null && !nameImage.isEmpty()) {
            if (isImage(nameImage)) {
                image = IMAGES.get(nameImage);
            } else {
                if (!nameFolder.isEmpty()) {
                    URL resources = clazz.getResource("/" + nameFolder + "/" + nameImage);

                    if (resources != null) {
                        String imagePath = resources.toExternalForm();

                        image = new Image(imagePath);
                        IMAGES.put(nameImage, image);
                    }
                }
            }
        }

        return image;
    }

    private static boolean isImage(String nameImage) {
        return IMAGES.containsKey(nameImage);
    }

    public static ImageView showImages() {
        Image[] images = {
                getImage(MORNING, nameFolder),
                getImage(AFTERNOON, nameFolder),
                getImage(EVENING, nameFolder),
                getImage(NIGHT, nameFolder),
        };

        imageView = new ImageView();
        imageView.setScaleX(scaling);
        imageView.setScaleY(scaling);
        imageView.setPreserveRatio(false);
//        switchImage(images);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> switchImage(images)),
                new KeyFrame(Duration.seconds(duration))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return imageView;
    }

    private static void switchImage(Image[] images) {
        if (imageView != null) {
            currentImageIndex = ++currentImageIndex % images.length;
            imageView.setImage(images[currentImageIndex]);
        }
    }

}
