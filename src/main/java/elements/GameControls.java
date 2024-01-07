package elements;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

public class GameControls {

    public static Group controls;
    private static final Map<String, Control> mapControls = new HashMap<>();
    private static final int HEIGHT = 25;
    private static final int MARGIN_SIZE = 10;
    private static final int START_VALUE = 10;

    static {
        controls = getControls();
    }

    private static Group getControls() {
        Group group = new Group();

        GridPane grid = new GridPane();
        grid.setPrefHeight(HEIGHT);

        Font font = new Font(HEIGHT);

        addLblStake(font, grid, 1);
        addLblWin(font, grid, 3);
        addBtnMinus(font, grid, 0);
        addBtnPlus(font, grid, 2);
        addTextWin(font, grid, 4);
        addBtnSpin(font, grid, 5);

        group.getChildren().add(grid);

        return group;
    }

    private static void addControl(Control control, GridPane grid, String nameControl, int index) {
        grid.add(control, index, 0);
        mapControls.put(nameControl, control);
    }

    private static void addLblStake(Font font, GridPane grid, int index) {
        Label control = new Label();
        control.setFont(font);
        GridPane.setMargin(control, new Insets(MARGIN_SIZE));

        Manage.currentBet(control, START_VALUE);

        addControl(control, grid, "lblStake", index);
    }

    private static void addLblWin(Font font, GridPane grid, int index) {
        Label control = new Label("Win:");
        control.setFont(font);
        GridPane.setMargin(control, new Insets(MARGIN_SIZE));

        addControl(control, grid, "lblWin", index);
    }

    private static void addBtnMinus(Font font, GridPane grid, int index) {
        Button control = new Button("-");
        control.setFont(font);

        control.setOnAction(event -> Manage.minusBet((Label) mapControls.get("lblStake")));

        addControl(control, grid, "btnMinus", index);
    }


    private static void addBtnPlus(Font font, GridPane grid, int index) {
        Button control = new Button("+");
        control.setFont(font);

        control.setOnAction(event -> Manage.plusBet((Label) mapControls.get("lblStake")));

        addControl(control, grid, "btnPlus", index);
    }

    private static void addTextWin(Font font, GridPane grid, int index) {
        TextArea control = new TextArea();
        control.setText("0.00");
        control.setPrefWidth(8 * HEIGHT);
        control.setFont(font);
        control.setEditable(false);
        GridPane.setMargin(control, new Insets(MARGIN_SIZE));

        addControl(control, grid, "textWin", index);
    }

    private static void addBtnSpin(Font font, GridPane grid, int index) {
        Button control = new Button("SPIN");
        control.setFont(font);

        addControl(control, grid, "btnSpin", index);
    }
}
