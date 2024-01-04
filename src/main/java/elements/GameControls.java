package elements;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GameControls {

    public static Group controls;

    static {
        controls = getControls();
    }

    private static Group getControls() {
        Group group = new Group();

        int height = 25;
        int marginSize = 10;

        GridPane grid = new GridPane();
        grid.setPrefHeight(height);

        Font fontBtn = new Font(height);
        int counter = 0;

        Button btnMinus = new Button("-");
        btnMinus.setFont(fontBtn);
        grid.add(btnMinus, counter++, 0);

        Label lblStake = new Label("0.0");
        lblStake.setFont(fontBtn);
        GridPane.setMargin(lblStake, new Insets(marginSize));
        grid.add(lblStake, counter++, 0);

        Button btnPlus = new Button("+");
        btnPlus.setFont(fontBtn);
        grid.add(btnPlus, counter++, 0);

        Label lblWin = new Label("Win:");
        lblWin.setFont(fontBtn);
        GridPane.setMargin(lblWin, new Insets(marginSize));
        grid.add(lblWin, counter++, 0);

        TextArea textWin = new TextArea();
        textWin.setText("100.00");
        textWin.setPrefWidth(8 * height);
        textWin.setFont(fontBtn);
        textWin.setEditable(false);
        GridPane.setMargin(textWin, new Insets(marginSize));
        grid.add(textWin, counter++, 0);

        Button btnSpin = new Button("SPIN");
        btnSpin.setFont(fontBtn);
        grid.add(btnSpin, counter++, 0);

        group.getChildren().add(grid);

        return group;
    }
}
