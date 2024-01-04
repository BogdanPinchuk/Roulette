package elements;

import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuRoulette {

    public static Group menu;

    static {
        menu = getMenu();
    }

    private static Group getMenu() {
        Group group = new Group();

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(getFileMenu(), getPlayMenu());

        group.getChildren().add(menuBar);

        return group;
    }

    private static Menu getFileMenu() {
        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        menuFile.getItems().add(exit);

        return menuFile;
    }

    private static Menu getPlayMenu() {
        Menu menuPlay = new Menu("Play");
        MenuItem spin = new MenuItem("Spin");
        menuPlay.getItems().add(spin);

        return menuPlay;
    }

}
