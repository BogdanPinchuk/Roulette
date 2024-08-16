import elements.view.Ball;
import elements.view.Chip;
import elements.GameControls;
import elements.view.Icon;
import elements.MenuRoulette;
import elements.view.Sound;
import elements.view.Table;
import elements.engine.GameLogic;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import static elements.BasicData.BACKGROUND;

public class Main extends Application {

    protected static final GameLogic gameLogic;

    static {
        Sound.clazz = Main.class;
        Icon.clazz = Main.class;
        Icon.nameFolder = "Pictures/icons";
//        ImageShowing.clazz = Main.class;
//        ImageShowing.nameFolder = "Pictures/1";
        gameLogic = new GameLogic();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Mini-Roulette 8");
        stage.getIcons().add(Icon.getIcon());

        // positions
        BorderPane borderPane = new BorderPane();

        // controls
//        /*
        Group controls = GameControls.controls;
        BorderPane.setAlignment(controls, Pos.BOTTOM_LEFT);
        borderPane.setBottom(controls);
//         */

        // background table
//        /*
        Group table = new Group();
        table.getChildren().addAll(Table.table, Ball.ball);
        // chips
        table.getChildren().add(Chip.chips);
        BorderPane.setAlignment(table, Pos.CENTER);
        borderPane.setCenter(table);
//         */

        // menu
//        /*
        Group menu = MenuRoulette.menu;
        BorderPane.setAlignment(menu, Pos.TOP_LEFT);
        borderPane.setTop(menu);
//         */

        // audio
//        /*
        Sound.playRouletteSound();
        Sound.playChipsSound();
//         */

        // images
        /*
        Group background = new Group();
        background.getChildren().add(ImageShowing.showImages());
        BorderPane.setAlignment(background, Pos.CENTER);
        borderPane.setCenter(background);
//         */

        Scene scene = new Scene(borderPane);
        scene.setFill(BACKGROUND);
        stage.setScene(scene);

        stage.show();
    }

}
