package elements;

import javafx.animation.FadeTransition;
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
    private static Image[] images;

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
        Image[] images = new Image[]{
                getImage(MORNING, nameFolder),
                getImage(AFTERNOON, nameFolder),
                getImage(EVENING, nameFolder),
                getImage(NIGHT, nameFolder),
        };

        imageView = new ImageView();
        imageView.setScaleX(scaling);
        imageView.setScaleY(scaling);
        imageView.setPreserveRatio(false);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(duration), imageView);
//        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> switchImage(images));
        fadeOut.play();

        return imageView;
    }

    private static void switchImage(Image[] images) {
        if (imageView != null) {
            currentImageIndex = ++currentImageIndex % images.length;
            imageView.setImage(images[currentImageIndex]);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(duration), imageView);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        }
    }

}
