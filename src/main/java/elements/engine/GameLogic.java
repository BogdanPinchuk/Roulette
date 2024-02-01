package elements.engine;

import elements.Manage;

import java.util.Random;

public class GameLogic {

    private final Random random = new Random();

    /**
     * spin, respin, select
     */
    public String gameStatus = "spin";

    public GameLogic() {
        gameStatus = "spin";
    }

    public static void playSpin() {

    }

    protected int getBet() {
        return Manage.getBet();
    }

}
