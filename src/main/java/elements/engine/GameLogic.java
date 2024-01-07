package elements.engine;

import elements.Manage;

public class GameLogic {

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
