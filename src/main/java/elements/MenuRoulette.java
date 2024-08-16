package elements;

import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

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

        MenuItem empty = new MenuItem("");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem exit = new MenuItem("Exit");

        exit.setOnAction(event -> Manage.exitGame());

        menuFile.getItems().addAll(empty, separator, exit);

        return menuFile;
    }

    private static Menu getPlayMenu() {
        Menu menuPlay = new Menu("Play");

        MenuItem spin = new MenuItem("Spin");
        SeparatorMenuItem separator0 = new SeparatorMenuItem();
        MenuItem respin = new MenuItem("ReSpin");
        MenuItem pick = new MenuItem("Pick");
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        MenuItem select = new MenuItem("Select");

        MenuItem[] listMenu = {
                spin,
                separator0,
                respin,
                pick,
                separator1,
                select,
        };

        menuPlay.getItems().addAll(listMenu);

        return menuPlay;
    }

}
