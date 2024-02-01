package elements;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import static elements.BasicData.RADIUS;

public class Ball {

    public static Group ball;

    static {
        ball = new Group(getActiveBall());
    }

    private static Shape getActiveBall() {
        Shape shape = getBall();
        double radius = 0.65 * RADIUS;
        double dr = RADIUS - radius;

        // path
        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(RADIUS);
        moveTo.setY(dr);

        ArcTo arcToRight = new ArcTo();
        arcToRight.setX(RADIUS);
        arcToRight.setY(RADIUS + radius);
        arcToRight.setRadiusX(radius);
        arcToRight.setRadiusY(radius);
        arcToRight.setSweepFlag(true);
        arcToRight.setLargeArcFlag(true);

        ArcTo arcToLeft = new ArcTo();
        arcToLeft.setX(RADIUS);
        arcToLeft.setY(dr);
        arcToLeft.setRadiusX(radius);
        arcToLeft.setRadiusY(radius);
        arcToLeft.setSweepFlag(true);
        arcToLeft.setLargeArcFlag(true);

        path.getElements().addAll(moveTo, arcToRight, arcToLeft);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(3));
        pathTransition.setNode(shape);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

        // todo: change
//        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setCycleCount(1);

        pathTransition.setAutoReverse(false);
        pathTransition.play();

        return shape;
    }

    private static Shape getBall() {
        Circle circle = new Circle();

        circle.setCenterX(RADIUS);
        circle.setCenterY(RADIUS);
        circle.setRadius(0.1 * RADIUS);

        RadialGradient gradient = new RadialGradient(0.0,
                0.0,
                circle.getCenterX(),
                circle.getCenterY(),
                circle.getRadius(),
                false,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.YELLOW),
                new Stop(5, Color.BLUE));

        circle.setFill(gradient);

        return circle;
    }
}
