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

    static {
        chips = new Group();

        addAll();
    }

    public static void addAll() {
        String value =
//                "10.0";
                String.valueOf(Manage.getBetResult());

        // 0 and 00
        addChip(getChip(2.75 * RADIUS, RADIUS, value));
        addChip(getChip(2.75 * RADIUS, 1.75 * RADIUS, value));
        addChip(getChip(2.75 * RADIUS, 0.25 * RADIUS, value));
        // 1 and 2
        addChip(getChip(3.00 * RADIUS, RADIUS, value));
        addChip(getChip(3.00 * RADIUS, 1.50 * RADIUS, value));
        addChip(getChip(3.00 * RADIUS, 0.50 * RADIUS, value));
        addChip(getChip(3.50 * RADIUS, RADIUS, value));
        addChip(getChip(3.50 * RADIUS, 1.75 * RADIUS, value));
        addChip(getChip(3.50 * RADIUS, 0.25 * RADIUS, value));
        // 3 and 4
//        addChip(getChip(4.00 * RADIUS, RADIUS, value));
        addChip(getChip(4.00 * RADIUS, 1.50 * RADIUS, value));
        addChip(getChip(4.00 * RADIUS, 0.50 * RADIUS, value));
        addChip(getChip(4.50 * RADIUS, RADIUS, value));
        addChip(getChip(4.50 * RADIUS, 1.75 * RADIUS, value));
        addChip(getChip(4.50 * RADIUS, 0.25 * RADIUS, value));
        // 5 and 6
        addChip(getChip(5.00 * RADIUS, RADIUS, value));
        addChip(getChip(5.00 * RADIUS, 1.50 * RADIUS, value));
        addChip(getChip(5.00 * RADIUS, 0.50 * RADIUS, value));
        addChip(getChip(5.50 * RADIUS, RADIUS, value));
        addChip(getChip(5.50 * RADIUS, 1.75 * RADIUS, value));
        addChip(getChip(5.50 * RADIUS, 0.25 * RADIUS, value));
        // 7 and 8
//        addChip(getChip(6.00 * RADIUS, RADIUS, value));
        addChip(getChip(6.00 * RADIUS, 1.50 * RADIUS, value));
        addChip(getChip(6.00 * RADIUS, 0.50 * RADIUS, value));
        addChip(getChip(6.50 * RADIUS, RADIUS, value));
        addChip(getChip(6.50 * RADIUS, 1.75 * RADIUS, value));
        addChip(getChip(6.50 * RADIUS, 0.25 * RADIUS, value));
        // 2 to 1
        addChip(getChip(7.25 * RADIUS, 1.50 * RADIUS, value));
        addChip(getChip(7.25 * RADIUS, 0.50 * RADIUS, value));
        // 1 to 4
        addChip(getChip(4.50 * RADIUS, 2.25 * RADIUS, value));
        // 5 to 8
        addChip(getChip(6.50 * RADIUS, 2.25 * RADIUS, value));
        // even
        addChip(getChip(3.75 * RADIUS, -0.25 * RADIUS, value));
        // odd
        addChip(getChip(6.75 * RADIUS, -0.25 * RADIUS, value));
        // red diamond
        addChip(getChip(4.75 * RADIUS, -0.25 * RADIUS, value));
        // black diamond
        addChip(getChip(5.75 * RADIUS, -0.25 * RADIUS, value));
    }

    public static void addChip(Group chip) {
        chips.getChildren().add(chip);
    }

    public static void clearChips() {
        chips.getChildren().clear();
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
