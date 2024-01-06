package elements;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GameControls {

    public static Group controls;
    private static final int HEIGHT = 25;
    private static final int MARGIN_SIZE = 10;

    static {
        controls = getControls();
    }

    private static Group getControls() {
        Group group = new Group();

        GridPane grid = new GridPane();
        grid.setPrefHeight(HEIGHT);

        Font font = new Font(HEIGHT);
        int counter = 0;

        grid.add(getBtnMinus(font), counter++, 0);
        grid.add(getLblStake(font), counter++, 0);
        grid.add(getBtnPlus(font), counter++, 0);
        grid.add(getLblWin(font), counter++, 0);
        grid.add(getTextWin(font), counter++, 0);
        grid.add(getSpin(font), counter++, 0);

        group.getChildren().add(grid);

        return group;
    }

    private static Control getBtnMinus(Font font) {
        Button button = new Button("-");
        button.setFont(font);

        return button;
    }

    private static Control getLblStake(Font font) {
        Label label = new Label("0.0");
        label.setFont(font);
        GridPane.setMargin(label, new Insets(MARGIN_SIZE));

        return label;
    }

    private static Control getBtnPlus(Font font) {
        Button button = new Button("+");
        button.setFont(font);

        return button;
    }

    private static Control getLblWin(Font font) {
        Label label = new Label("Win:");
        label.setFont(font);
        GridPane.setMargin(label, new Insets(MARGIN_SIZE));

        return label;
    }

    private static Control getTextWin(Font font) {
        TextArea textWin = new TextArea();
        textWin.setText("0.00");
        textWin.setPrefWidth(8 * HEIGHT);
        textWin.setFont(font);
        textWin.setEditable(false);
        GridPane.setMargin(textWin, new Insets(MARGIN_SIZE));

        return textWin;
    }

    private static Control getSpin(Font font) {
        Button button = new Button("SPIN");
        button.setFont(font);

        return button;
    }
}
