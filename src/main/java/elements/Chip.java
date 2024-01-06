package elements;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static elements.BasicData.BLUE;
import static elements.BasicData.GAP;
import static elements.BasicData.GRAY;
import static elements.BasicData.LENGTH;
import static elements.BasicData.RADIUS;
import static elements.BasicData.SIZE_CHIP;
import static elements.BasicData.SIZE_CIRCLE_IN;
import static elements.BasicData.VIOLET;

public class Chip {

    public static Group chips;

    public static double X = 700.0,
            Y = 50.0;

    static {
        chips = new Group();
    }

    public static void addChip(Group chip) {
        chips.getChildren().add(chip);
    }

    public static void clearChips() {
        chips.getChildren().clear();
    }

    public static Group getChip() {
        return getChip(X, Y, "10.0");
    }

    public static Group getChip(double X, double Y, String value) {
        Group group = new Group();

        // circle out
        group.getChildren().add(getCircleOut(X, Y));

        // sectors
        for (int i = 0; i < 10; i++) {
            group.getChildren().add(getArc(i, X, Y));
        }

        // circle inside
        group.getChildren().addAll(getCircleIn(X, Y), getCircleInside(X, Y));

        // text
        group.getChildren().add(getText(X, Y, value));

        return group;
    }

    private static Shape getCircleOut(double X, double Y) {
        return getCircle(SIZE_CHIP * RADIUS, X, Y);
    }

    private static Shape getCircle(double radius, double X, double Y) {
        Circle circle = new Circle();

        circle.setCenterX(X);
        circle.setCenterY(Y);
        circle.setRadius(radius);

        circle.setFill(VIOLET);

        return circle;
    }

    private static Shape getArc(int numSector, double X, double Y) {
        double length = LENGTH;
        double gap = 4 * GAP;
        double radius = SIZE_CHIP * RADIUS;

        Arc arc = new Arc();

        arc.setCenterX(X);
        arc.setCenterY(Y);

        double dr = 0.04 * radius;
        arc.setRadiusX(radius - dr);
        arc.setRadiusY(radius - dr);
        arc.setStartAngle(numSector * length + gap / 2.0);
        arc.setLength(length - gap);
        arc.setType(ArcType.ROUND);

        arc.setFill(BLUE);

        return arc;
    }

    private static Shape getCircleIn(double X, double Y) {
        return getCircle(SIZE_CHIP * SIZE_CIRCLE_IN * RADIUS, X, Y);
    }

    private static Shape getCircleInside(double X, double Y) {
        double radius = SIZE_CHIP * SIZE_CIRCLE_IN * RADIUS;
        double dr = 0.1 * radius;
        Shape shape = getCircle(radius - dr, X, Y);
        shape.setFill(GRAY);

        return shape;
    }

    private static Shape getText(double X, double Y, String value) {
        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20.0);
        text.setFont(font);

        text.setText(value);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        text.setLayoutX(X - text.getLayoutBounds().getWidth() / 2.0);
        text.setLayoutY(Y);

        return text;
    }

}
