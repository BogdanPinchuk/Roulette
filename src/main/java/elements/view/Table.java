package elements.view;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static elements.BasicData.BLACK;
import static elements.BasicData.BROWN;
import static elements.BasicData.GAP;
import static elements.BasicData.GREEN;
import static elements.BasicData.LENGTH;
import static elements.BasicData.RADIUS;
import static elements.BasicData.RED;
import static elements.BasicData.SIZE_CIRCLE_IN;
import static elements.BasicData.YELLOW;

public class Table {

    public static Group table;

    static {
        table = getBackgroundTable();
    }

    private static Group getBackgroundTable() {
        Group group = new Group();

        // cylinder
        group.getChildren().add(getCylinder());
        // rectangle
        group.getChildren().add(getRectangle());

        return group;
    }

    private static Group getCylinder() {
        Group group = new Group();

        // circle out
        group.getChildren().add(getCircleOut());

        // sectors
        for (int i = 0; i < 10; i++) {
            group.getChildren().add(getArc(i));
        }

        // circle inside
        group.getChildren().addAll(getCircleIn(), getCircleInside());

        // text
        for (int i = 0; i < 10; i++) {
            group.getChildren().add(getArcText(i));
        }

        return group;
    }

    private static Shape getCircleOut() {
        return getCircle(RADIUS);
    }

    private static Shape getCircleIn() {
        return getCircle(SIZE_CIRCLE_IN * RADIUS);
    }

    private static Shape getCircleInside() {
        double dr = 0.04 * RADIUS;
        Shape shape = getCircle(SIZE_CIRCLE_IN * RADIUS - dr);
        shape.setFill(BROWN);

        return shape;
    }

    private static Shape getCircle(double radius) {
        Circle circle = new Circle();

        circle.setCenterX(RADIUS);
        circle.setCenterY(RADIUS);
        circle.setRadius(radius);

        circle.setFill(YELLOW);

        return circle;
    }

    private static Shape getArc(int numSector) {
        double length = LENGTH;
        double gap = GAP;
        double radius = RADIUS;

        Arc arc = new Arc();

        arc.setCenterX(radius);
        arc.setCenterY(radius);

        double dr = 0.04 * radius;
        arc.setRadiusX(radius - dr);
        arc.setRadiusY(radius - dr);
        arc.setStartAngle(numSector * length + gap / 2.0);
        arc.setLength(length - gap);
        arc.setType(ArcType.ROUND);

        Color color;
        switch (numSector) {
            case 2:
            case 7:
                color = GREEN;
                break;
            default:
                color = (numSector % 2 == 0) ? RED : BLACK;
                break;
        }

        arc.setFill(color);

        return arc;
    }

    private static Shape getArcText(int numSector) {
        double length = LENGTH;
        double radius = RADIUS * (1.0 + SIZE_CIRCLE_IN) / 2.0;

        String numStr = getStrNumSector(numSector);

        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 35.0);
        text.setFont(font);
        text.setUnderline(true);

        text.setText(numStr);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        double angle = numSector * length + length / 2.0;
        double angleRad = Math.toRadians(angle);

        double positionX = RADIUS + radius * Math.cos(angleRad);
        double positionY = RADIUS + radius * Math.sin(angleRad);

        text.setRotate(90 + angle);

        text.setLayoutX(positionX - text.getLayoutBounds().getWidth() / 2.0);
        text.setLayoutY(positionY);

        return text;
    }

    private static String getStrNumSector(int numSector) {
        String numStr = "";

        switch (numSector) {
            case 0:
                numStr = "4";
                break;
            case 1:
                numStr = "2";
                break;
            case 2:
                numStr = "0";
                break;
            case 3:
                numStr = "5";
                break;
            case 4:
                numStr = "7";
                break;
            case 5:
                numStr = "3";
                break;
            case 6:
                numStr = "1";
                break;
            case 7:
                numStr = "00";
                break;
            case 8:
                numStr = "6";
                break;
            case 9:
                numStr = "8";
                break;
        }

        return numStr;
    }

    private static Group getRectangle() {
        Group group = new Group();

        // triangles
        group.getChildren().add(getTriangle());
        // 0 and 00
        group.getChildren().addAll(getRect0(), getRect00());
        // 1-8
        group.getChildren().add(getRectNums());
        // 1 to 4
        group.getChildren().add(getRectXtoYs());
        // headers
        group.getChildren().add(getRectHeaders());
        // diamonds
        group.getChildren().add(getRectDiamonds());
        // right side rect
        group.getChildren().addAll(getRectUp(), getRectDown());

        return group;
    }

    private static Shape getTriangle() {
        Polygon polygon = new Polygon();

        // 1-st
        Double[] points = new Double[]{2.25 * RADIUS, RADIUS};
        polygon.getPoints().addAll(points);

        // 2-nd
        points = new Double[]{2.5 * RADIUS, 2.0 * RADIUS};
        polygon.getPoints().addAll(points);

        // 3-rd
        points = new Double[]{2.5 * RADIUS, 0.0 * RADIUS};
        polygon.getPoints().addAll(points);

        polygon.setFill(GREEN);
        polygon.setStrokeWidth(GAP);
        polygon.setStrokeType(StrokeType.INSIDE);
        polygon.setStroke(YELLOW);

        return polygon;
    }

    private static Group getRect0() {
        return getRectZero("0", RADIUS);
    }

    private static Group getRect00() {
        return getRectZero("00", 0.0);
    }

    private static Group getRectZero(String numStr, double y) {
        Group group = new Group();

        double x = 2.5 * RADIUS,
                width = RADIUS / 2.0,
                height = RADIUS;

        Rectangle rectangle = new Rectangle();

        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        Color color = getColorNumSector(numStr);
        rectangle.setFill(color);
        rectangle.setStrokeWidth(GAP);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(YELLOW);

        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40.0);
        text.setFont(font);

        text.setText(numStr);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        double positionX = x + width / 2.0;
        double positionY = y + height / 2.0;

        double angle = -90.0;
        text.setRotate(angle);

        text.setLayoutX(positionX - text.getLayoutBounds().getWidth() / 2.0);
        text.setLayoutY(positionY);

        group.getChildren().addAll(rectangle, text);

        return group;
    }

    private static Color getColorNumSector(String numStr) {
        Color color;

        int value;

        try {
            value = Integer.parseInt(numStr);
        } catch (Exception ex) {
            value = 0;
        }

        switch (value) {
            case 1:
            case 4:
            case 6:
            case 7:
                color = RED;
                break;
            case 2:
            case 3:
            case 5:
            case 8:
                color = BLACK;
                break;
            default:
                color = GREEN;
                break;
        }

        return color;
    }

    private static Group getRectNum(String numStr, double x, double y) {
        Group group = new Group();

        double width = RADIUS,
                height = RADIUS;

        Rectangle rectangle = new Rectangle();

        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        Color color = getColorNumSector(numStr);
        rectangle.setFill(color);
        rectangle.setStrokeWidth(GAP);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(YELLOW);

        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 100.0);
        text.setFont(font);

        text.setText(numStr);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        double positionX = x + width / 2.0;
        double positionY = y + height / 2.0;

        double angle = -90.0;
        text.setRotate(angle);

        text.setLayoutX(positionX - text.getLayoutBounds().getWidth() / 2.0);
        text.setLayoutY(positionY);

        group.getChildren().addAll(rectangle, text);

        return group;
    }

    private static Group getRectNums() {
        Group group = new Group();

        double x, y;
        for (int i = 1; i <= 8; i++) {
            x = (3.0 + (double) ((i - 1) / 2)) * RADIUS;
            y = (i % 2 == 1) ? RADIUS : 0.0;

            group.getChildren().add(getRectNum(String.valueOf(i), x, y));
        }

        return group;
    }

    private static Group getRectXtoY(String numStr, double x, double y) {
        Group group = new Group();

        double width = 2.0 * RADIUS,
                height = RADIUS / 2.0;

        Rectangle rectangle = new Rectangle();

        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        Color color = getColorNumSector(numStr);
        rectangle.setFill(color);
        rectangle.setStrokeWidth(GAP);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(YELLOW);

        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 35.0);
        text.setFont(font);

        text.setText(numStr);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        double positionX = x + width / 2.0;
        double positionY = y + height / 2.0;

        double angle = 0.0;
        text.setRotate(angle);

        text.setLayoutX(positionX - text.getLayoutBounds().getWidth() / 2.0);
        text.setLayoutY(positionY);

        group.getChildren().addAll(rectangle, text);

        return group;
    }

    private static Group getRectXtoYs() {
        Group group = new Group();

        double x = 3.0 * RADIUS,
                y = 2.0 * RADIUS;
        group.getChildren().add(getRectXtoY("1 to 4", x, y));
        x = 5.0 * RADIUS;
        group.getChildren().add(getRectXtoY("5 to 8", x, y));

        return group;
    }

    private static Group getRectHeader(String numStr, double x, double y) {
        Group group = new Group();

        double width = RADIUS,
                height = RADIUS / 2.0;

        Rectangle rectangle = new Rectangle();

        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        Color color = getColorNumSector(numStr);
        rectangle.setFill(color);
        rectangle.setStrokeWidth(GAP);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(YELLOW);

        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 35.0);
        text.setFont(font);

        text.setText(numStr);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        double positionX = x + width / 2.0;
        double positionY = y + height / 2.0;

        double angle = 0.0;
        text.setRotate(angle);

        text.setLayoutX(positionX - text.getLayoutBounds().getWidth() / 2.0);
        text.setLayoutY(positionY);

        group.getChildren().addAll(rectangle, text);

        return group;
    }

    private static Group getRectHeaders() {
        Group group = new Group();

        double x, y = -0.5 * RADIUS;

        String[] numStrs = {"even", "", "", "odd"};

        for (int i = 0; i < 4; i++) {
            x = (3.0 + i) * RADIUS;
            group.getChildren().add(getRectHeader(numStrs[i], x, y));
        }

        return group;
    }

    private static Shape getRectDiamond(Color color, double x, double y) {
        Polygon polygon = new Polygon();

        // 1-st
        double dx = RADIUS / 4.0,
                dy = RADIUS / 8.0,
                X = x - dx,
                Y = y;

        Double[] points = new Double[]{X, Y};
        polygon.getPoints().addAll(points);

        // 2-nd
        X = x;
        Y = y - dy;
        points = new Double[]{X, Y};
        polygon.getPoints().addAll(points);

        // 3-rd
        X = x + dx;
        Y = y;
        points = new Double[]{X, Y};
        polygon.getPoints().addAll(points);

        // 4-th
        X = x;
        Y = y + dy;
        points = new Double[]{X, Y};
        polygon.getPoints().addAll(points);

        polygon.setFill(color);
        polygon.setStrokeWidth(GAP);
        polygon.setStrokeType(StrokeType.INSIDE);
        polygon.setStroke(Color.WHITE);

        return polygon;
    }

    private static Group getRectDiamonds() {
        Group group = new Group();

        Color color = RED;
        double x = 4.5 * RADIUS,
                y = -0.25 * RADIUS;
        group.getChildren().add(getRectDiamond(color, x, y));

        color = BLACK;
        x = 5.5 * RADIUS;
        group.getChildren().add(getRectDiamond(color, x, y));

        return group;
    }

    private static Group getRectRightSide(String numStr, double y) {
        Group group = new Group();

        double x = 7.0 * RADIUS,
                width = RADIUS / 2.0,
                height = RADIUS;

        Rectangle rectangle = new Rectangle();

        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        Color color = getColorNumSector(numStr);
        rectangle.setFill(color);
        rectangle.setStrokeWidth(GAP);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(YELLOW);

        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 35.0);
        text.setFont(font);

        text.setText(numStr);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setTextOrigin(VPos.CENTER);

        double positionX = x + width / 2.0;
        double positionY = y + height / 2.0;

        double angle = -90.0;
        text.setRotate(angle);

        text.setLayoutX(positionX - text.getLayoutBounds().getWidth() / 2.0);
        text.setLayoutY(positionY);

        group.getChildren().addAll(rectangle, text);

        return group;
    }

    private static Group getRectUp() {
        return getRectRightSide("2 to 1", 0.0);
    }

    private static Group getRectDown() {
        return getRectRightSide("2 to 1", RADIUS);
    }

}
