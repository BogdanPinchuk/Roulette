import elements.Ball;
import elements.GameControls;
import elements.MenuRoulette;
import elements.Sound;
import elements.Table;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static elements.BasicData.GRAY;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Mini-Roulette 8");

        // positions
        BorderPane borderPane = new BorderPane();

        // background table
        Group table = new Group();
        table.getChildren().addAll(Table.table, Ball.ball);
        BorderPane.setAlignment(table, Pos.CENTER);
        borderPane.setCenter(table);

        // menu
        Group menu = MenuRoulette.menu;
        BorderPane.setAlignment(menu, Pos.TOP_LEFT);
        borderPane.setTop(menu);

        // controls
        Group controls = GameControls.controls;
        BorderPane.setAlignment(controls, Pos.BOTTOM_LEFT);
        borderPane.setBottom(controls);

        // audio
        Sound.getSound(this.getClass());

        // move
//        Translate translate = new Translate();
//        translate.setY(RADIUS / 2.0);
//        group.getTransforms().add(translate);

//        Scene scene = new Scene(group);
        Scene scene = new Scene(borderPane);
        scene.setFill(GRAY);
        stage.setScene(scene);

        stage.show();
    }


}
