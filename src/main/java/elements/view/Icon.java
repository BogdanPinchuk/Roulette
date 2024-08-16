package elements.view;

import javafx.scene.image.Image;

import java.awt.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Icon {

    public static Class<?> clazz;
    public static String nameFolder;

    private static final String ICON = "icon.png";

    private static final Map<String, Image> ICONS = new HashMap<>();

    public static Image getIcon(String nameImage, String nameFolder) {
        Image image = null;

        if (clazz != null && !nameImage.isEmpty()) {
            if (isIcon(nameImage)) {
                image = ICONS.get(nameImage);
            } else {
                if (!nameFolder.isEmpty()) {
                    URL resources = clazz.getResource("/" + nameFolder + "/" + nameImage);

                    if (resources != null) {
                        String imagePath = resources.toExternalForm();

                        image = new Image(imagePath);
                        ICONS.put(nameImage, image);
                    }
                }
            }
        }

        return image;
    }

    public static Image getIcon(String nameImage) {
        return getIcon(nameImage, nameFolder);
    }

    public static Image getIcon() {
        return getIcon(ICON, nameFolder);
    }

    public static java.awt.Image getImageIcon() {
        URL url = clazz.getResource("/" + nameFolder + "/" + ICON);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    private static boolean isIcon(String nameImage) {
        return ICONS.containsKey(nameImage);
    }

}
