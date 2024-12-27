package config;

import java.awt.*;

public class AppSettings {
    // ----- Application constants
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 600;
    public static final String WINDOW_TITLE = "Calculator Application";

    // ----- UI settings
    public static final String BUTTON_FONT_NAME = "Default";
    public static final int BUTTON_FONT_SIZE = 18;
    public static final int GRID_ROWS = 5;
    public static final int GRID_COLS = 4;
    public static final int GRID_GAP = 5;

    public static final String[] BUTTON_TEXTS = {
            "C", null, null, "=",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            null, "0", ",", "+"
    };

    // --- Application colors
    public static final Color DISPLAY_BG_COLOR = new Color(0, 0,0);
    public static final Color NUMBERSBUTTON_COLOR = new Color(20, 20, 20);
    public static final Color OPERATIONSBUTTON_COLOR = new Color(44, 33, 19);
    public static final Color STYLE_COLOR = new Color(161, 116, 66);
    public static final Color HOVER_COLOR = new Color(125, 125, 125);
    public static final Color DISPLAY_TEXT_COLOR = new Color(213, 202, 197, 255);
}
