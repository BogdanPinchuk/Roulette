package elements;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Manage {

    private static final Integer[] BETS =
            {1, 2, 3, 4, 5, 7, 10, 15, 20, 25, 30, 40, 50, 75, 100, 150, 200, 250, 300, 400, 500, 750, 1000};

    public static int bet;

    static {
        bet = BETS[0];
    }

    public static double getBetResult() {
        return bet / 100.0;
    }

    public static void exitGame() {
        Platform.exit();
    }

    public static void currentBet(Label label, int value) {
        int index = indexOf(BETS, value);
        setBet(label, index);
    }

    private static void setBet(Label label, int index) {
        if (index <= 0) {
            index = 0;
        }

        bet = BETS[index];

        label.setText(String.valueOf(getBetResult()));
    }


    public static void minusBet(Label label) {
        int index = indexOf(BETS, bet) - 1;
        index = Math.max(index, 0);

        setBet(label, index);
    }

    public static void plusBet(Label label) {
        int index = indexOf(BETS, bet) + 1;
        index = Math.min(index, BETS.length - 1);

        setBet(label, index);
    }

    public static <T> int indexOf(T[] array, T value) {
        int index = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                index = i;
                break;
            }
        }

        return index;
    }

}
