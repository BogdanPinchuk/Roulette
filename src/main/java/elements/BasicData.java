package elements;

import javafx.scene.paint.Color;

public class BasicData {

    public static final int WAGER = 10;

    // symbols
    public static final int Blank = 0;
    public static final int Coin = 1;

    // payment stake
    public static final int PAY_Straight = 30;
    public static final int PAY_Split = 10;
    public static final int PAY_Corner = 5;
    public static final int PAY_Quartet = 5;
    public static final int PAY_RedBlack = 5;
    public static final int PAY_EvenOdd = 5;
    public static final int PAY_Column = 2;

    // payment jackpots
    public static final int Mini = 10;
    public static final int Minor = 30;
    public static final int Major = 125;
    public static final int Grand = 1000;

    public static final Integer[] BETS =
            {1, 2, 3, 4, 5, 7, 10, 15, 20, 25, 30, 40, 50, 75, 100, 150, 200, 250, 300, 400, 500, 750, 1000};

    public static final Integer[] COIN_VALUES =
            {1, 2, 5, Mini, 15, Minor, 75, Major, 250, 500, Grand};
    public static final Integer[] COIN_WEIGHTS =
            {1000, 500, 250, 125, 75, 30, 15, 10, 5, 2, 1};

    public static final Integer[][] BG_COIN_WEIGHT_ITEM_1 = {
            {Blank, Coin},
            {10, 1},
    };
    public static final Integer[][] BG_COIN_WEIGHT_ITEM_2 = {
            {Blank, Coin},
            {10, 1},
    };
    public static final Integer[][] BG_COIN_WEIGHT_ITEM_4 = {
            {Blank, Coin},
            {10, 1},
    };
    public static final Integer[][] BG_COIN_WEIGHT_ITEM_6 = {
            {Blank, Coin},
            {10, 1},
    };

    // data for views
    public static final double RADIUS = 200.0;
    public static final double LENGTH = 360.0 / 10.0;
    public static final double GAP = 4.0;
    public static final double SIZE_CIRCLE_IN = 0.65;
    public static final double SIZE_CHIP = 0.18;

    // colors for views
    public static final Color RED = Color.rgb(221, 42, 2);
    public static final Color BLACK = Color.rgb(44, 44, 48);
    public static final Color GREEN = Color.rgb(87, 133, 3);
    public static final Color YELLOW = Color.rgb(254, 253, 17);
    public static final Color BROWN = Color.rgb(126, 94, 0);
    public static final Color BACKGROUND = Color.rgb(247, 247, 247);
    public static final Color BLUE = Color.rgb(1, 30, 255);
    public static final Color GRAY = Color.rgb(157, 157, 157);
    public static final Color VIOLET = Color.rgb(145, 78, 184);

}
