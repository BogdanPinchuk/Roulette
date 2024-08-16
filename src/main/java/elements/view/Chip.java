package elements.view;

import elements.Manage;
import elements.engine.KeyValuePair;
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

import java.util.HashMap;
import java.util.Map;

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
    /**
     * Map(Index, KeyValuePair(Name, Items))
     */
    public static Map<Integer, KeyValuePair<String, Integer>> elements = new HashMap<>();
    public static Map<Integer, KeyValuePair<Double, Double>> positions = new HashMap<>();


    static {
        chips = new Group();

        addElementsInfo();
        addPositionsInfo();

        // todo: delete
        addAll();
    }

    public static void addAll() {
        String value =
//                "10.0";
                String.valueOf(Manage.getBetResult());

        for (KeyValuePair<Double, Double> position : positions.values()) {
            addChip(getChip(position.getKey(), position.getValue(), value));
        }
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

    private static void addElementsInfo() {
        Map<Integer, KeyValuePair<String, Integer>> map = elements;

        // 1
        map.put(0, new KeyValuePair<>("00", 1));
        map.put(1, new KeyValuePair<>("0;00", 2));
        map.put(2, new KeyValuePair<>("0", 1));
        // 2
        map.put(3, new KeyValuePair<>("00;2", 2));
        map.put(4, new KeyValuePair<>("0;00;1;2", 4));
        map.put(5, new KeyValuePair<>("0;1", 2));
        // 3
        map.put(6, new KeyValuePair<>("even", 4));
        map.put(7, new KeyValuePair<>("2", 1));
        map.put(8, new KeyValuePair<>("1;2", 2));
        map.put(9, new KeyValuePair<>("1", 1));
        // 4
        map.put(10, new KeyValuePair<>("2;4", 2));
        map.put(11, new KeyValuePair<>("1;3", 2));
        map.put(12, new KeyValuePair<>("1to4", 4));
        // 5
        map.put(13, new KeyValuePair<>("red", 4));
        map.put(14, new KeyValuePair<>("4", 1));
        map.put(15, new KeyValuePair<>("3;4", 2));
        map.put(16, new KeyValuePair<>("3", 1));
        // 6
        map.put(17, new KeyValuePair<>("4;6", 2));
        map.put(18, new KeyValuePair<>("3;4;5;6", 4));
        map.put(19, new KeyValuePair<>("3;5", 2));
        // 7
        map.put(20, new KeyValuePair<>("black", 4));
        map.put(21, new KeyValuePair<>("6", 1));
        map.put(22, new KeyValuePair<>("5;6", 2));
        map.put(23, new KeyValuePair<>("5", 1));
        // 8
        map.put(24, new KeyValuePair<>("6;8", 2));
        map.put(25, new KeyValuePair<>("5;7", 2));
        map.put(26, new KeyValuePair<>("5to8", 4));
        // 9
        map.put(27, new KeyValuePair<>("odd", 4));
        map.put(28, new KeyValuePair<>("8", 1));
        map.put(29, new KeyValuePair<>("7;8", 2));
        map.put(30, new KeyValuePair<>("7", 1));
        // 10
        map.put(31, new KeyValuePair<>("00;2to1", 6));
        map.put(32, new KeyValuePair<>("0;2to1", 6));
    }

    private static void addPositionsInfo() {
        Map<Integer, KeyValuePair<Double, Double>> map = positions;

        // 1
        map.put(0, new KeyValuePair<>(2.75 * RADIUS, 0.25 * RADIUS));
        map.put(1, new KeyValuePair<>(2.75 * RADIUS, RADIUS));
        map.put(2, new KeyValuePair<>(2.75 * RADIUS, 1.75 * RADIUS));
        // 2
        map.put(3, new KeyValuePair<>(3.00 * RADIUS, 0.50 * RADIUS));
        map.put(4, new KeyValuePair<>(3.00 * RADIUS, RADIUS));
        map.put(5, new KeyValuePair<>(3.00 * RADIUS, 1.50 * RADIUS));
        // 3
        map.put(6, new KeyValuePair<>(3.75 * RADIUS, -0.25 * RADIUS));
        map.put(7, new KeyValuePair<>(3.50 * RADIUS, 0.25 * RADIUS));
        map.put(8, new KeyValuePair<>(3.50 * RADIUS, RADIUS));
        map.put(9, new KeyValuePair<>(3.50 * RADIUS, 1.75 * RADIUS));
        // 4
        map.put(10, new KeyValuePair<>(4.00 * RADIUS, 0.50 * RADIUS));
        map.put(11, new KeyValuePair<>(4.00 * RADIUS, 1.50 * RADIUS));
        map.put(12, new KeyValuePair<>(4.50 * RADIUS, 2.25 * RADIUS));
        // 5
        map.put(13, new KeyValuePair<>(4.75 * RADIUS, -0.25 * RADIUS));
        map.put(14, new KeyValuePair<>(4.50 * RADIUS, 0.25 * RADIUS));
        map.put(15, new KeyValuePair<>(4.50 * RADIUS, RADIUS));
        map.put(16, new KeyValuePair<>(4.50 * RADIUS, 1.75 * RADIUS));
        // 6
        map.put(17, new KeyValuePair<>(5.00 * RADIUS, 0.50 * RADIUS));
        map.put(18, new KeyValuePair<>(5.00 * RADIUS, RADIUS));
        map.put(19, new KeyValuePair<>(5.00 * RADIUS, 1.50 * RADIUS));
        // 7
        map.put(20, new KeyValuePair<>(5.75 * RADIUS, -0.25 * RADIUS));
        map.put(21, new KeyValuePair<>(5.50 * RADIUS, 0.25 * RADIUS));
        map.put(22, new KeyValuePair<>(5.50 * RADIUS, RADIUS));
        map.put(23, new KeyValuePair<>(5.50 * RADIUS, 1.75 * RADIUS));
        // 8
        map.put(24, new KeyValuePair<>(6.00 * RADIUS, 0.50 * RADIUS));
        map.put(25, new KeyValuePair<>(6.00 * RADIUS, 1.50 * RADIUS));
        map.put(26, new KeyValuePair<>(6.50 * RADIUS, 2.25 * RADIUS));
        // 9
        map.put(27, new KeyValuePair<>(6.75 * RADIUS, -0.25 * RADIUS));
        map.put(28, new KeyValuePair<>(6.50 * RADIUS, 0.25 * RADIUS));
        map.put(29, new KeyValuePair<>(6.50 * RADIUS, RADIUS));
        map.put(30, new KeyValuePair<>(6.50 * RADIUS, 1.75 * RADIUS));
        // 10
        map.put(31, new KeyValuePair<>(7.25 * RADIUS, 0.50 * RADIUS));
        map.put(32, new KeyValuePair<>(7.25 * RADIUS, 1.50 * RADIUS));
    }

}
