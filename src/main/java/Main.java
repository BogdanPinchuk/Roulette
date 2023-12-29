import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double RADIUS = 200.0;
    private static final double LENGTH = 360.0 / 10.0;
    private static final double GAP = 4.0;
    private static final double SIZE_CIRCLE_IN = 0.65;

    private static final Color RED = Color.rgb(221, 42, 2);
    private static final Color BLACK = Color.rgb(44, 44, 48);
    private static final Color GREEN = Color.rgb(87, 133, 3);
    private static final Color YELLOW = Color.rgb(254, 253, 17);
    private static final Color BROWN = Color.rgb(126, 94, 0);
    private static final Color GRAY = Color.rgb(247, 247, 247);


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Roulette");

        Group group = new Group();

        // cylinder
        group.getChildren().add(getCylinder());
        // rectangle
        group.getChildren().add(getRectangle());
        // move
        Translate translate = new Translate();
        translate.setY(RADIUS / 2.0);
        group.getTransforms().add(translate);

        Scene scene = new Scene(group);
        scene.setFill(GRAY);
        stage.setScene(scene);

        stage.show();
    }

    private Group getCylinder() {
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

    private Shape getCircleOut() {
        return getCircle(RADIUS);
    }

    private Shape getCircleIn() {
        return getCircle(SIZE_CIRCLE_IN * RADIUS);
    }

    private Shape getCircleInside() {
        Shape shape = getCircle(SIZE_CIRCLE_IN * RADIUS - 2.0 * GAP);
        shape.setFill(BROWN);

        return shape;
    }

    private Shape getCircle(double radius) {
        Circle circle = new Circle();

        circle.setCenterX(RADIUS);
        circle.setCenterY(RADIUS);
        circle.setRadius(radius);

        circle.setFill(YELLOW);

        return circle;
    }

    private Shape getArc(int numSector) {
        double length = LENGTH;
        double gap = GAP;
        double radius = RADIUS;

        Arc arc = new Arc();

        arc.setCenterX(radius);
        arc.setCenterY(radius);
        arc.setRadiusX(radius - 2.0 * gap);
        arc.setRadiusY(radius - 2.0 * gap);
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

    private Shape getArcText(int numSector) {
        double length = LENGTH;
        double radius = RADIUS * (1.0 + SIZE_CIRCLE_IN) / 2.0;

        String numStr = getStrNumSector(numSector);

        Text text = new Text();

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 35.0);
        text.setFont(font);

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

    private String getStrNumSector(int numSector) {
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

    private Group getRectangle() {
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

    private Shape getTriangle() {
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

    private Group getRect0() {
        return getRectZero("0", RADIUS);
    }

    private Group getRect00() {
        return getRectZero("00", 0.0);
    }

    private Group getRectZero(String numStr, double y) {
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

    private Color getColorNumSector(String numStr) {
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

    private Group getRectNum(String numStr, double x, double y) {
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

        Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50.0);
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

    private Group getRectNums() {
        Group group = new Group();

        double x, y;
        for (int i = 1; i <= 8; i++) {
            x = (3.0 + (double) ((i - 1) / 2)) * RADIUS;
            y = (i % 2 == 1) ? RADIUS : 0.0;

            group.getChildren().add(getRectNum(String.valueOf(i), x, y));
        }

        return group;
    }

    private Group getRectXtoY(String numStr, double x, double y) {
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

    private Group getRectXtoYs() {
        Group group = new Group();

        double x = 3.0 * RADIUS,
                y = 2.0 * RADIUS;
        group.getChildren().add(getRectXtoY("1 to 4", x, y));
        x = 5.0 * RADIUS;
        group.getChildren().add(getRectXtoY("5 to 8", x, y));

        return group;
    }

    private Group getRectHeader(String numStr, double x, double y) {
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

    private Group getRectHeaders() {
        Group group = new Group();

        double x, y = -0.5 * RADIUS;

        String[] numStrs = {"even", "", "", "odd"};

        for (int i = 0; i < 4; i++) {
            x = (3.0 + i) * RADIUS;
            group.getChildren().add(getRectHeader(numStrs[i], x, y));
        }

        return group;
    }

    private Shape getRectDiamond(Color color, double x, double y) {
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

    private Group getRectDiamonds() {
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

    private Group getRectRightSide(String numStr, double y) {
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

    private Group getRectUp() {
        return getRectRightSide("2 to 1", 0.0);
    }

    private Group getRectDown() {
        return getRectRightSide("2 to 1", RADIUS);
    }


}
