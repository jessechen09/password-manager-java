package model;

public class Colors {
    public static final String MAIN_RED = "#c92d39";
    public static final String LIGHT_RED = "#da6770";
    public static final String MAIN_BLUE = "#001d4c";
    public static final String WHITE = "#ffffff";
    public static final String BLACK = "#000000";
    public static final String LIGHT_GREY = "#eaeaea";

    public static String setBackgroundColor(String color) { return "-fx-background-color: " + color + ";"; }
    public static String setTextColor(String color) {return "";}
}
