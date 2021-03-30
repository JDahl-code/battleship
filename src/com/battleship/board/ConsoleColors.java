package com.battleship.board;

public enum ConsoleColors {
    //Color end string, color reset
    RESET("\033[0m"),

    // Regular Colors. Normal color, no bold, background color etc.
    BLACK("\033[0;30m"),    // BLACK
    RED("\033[0;31m"),      // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m"),     // BLUE
    MAGENTA("\033[0;35m"),  // MAGENTA
    CYAN("\033[0;36m"),     // CYAN
    WHITE("\033[0;37m"),   // WHITE


    // Background
    BLACK_BACKGROUND("\033[40m"),   // BLACK
    RED_BACKGROUND("\033[41m"),     // RED
    GREEN_BACKGROUND("\033[42m"),   // GREEN
    YELLOW_BACKGROUND("\033[43m"),  // YELLOW
    BLUE_BACKGROUND("\033[44m"),    // BLUE
    MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
    CYAN_BACKGROUND("\033[46m"),    // CYAN
    WHITE_BACKGROUND("\033[47m");   // WHITE

    private final String code;

    ConsoleColors(String code) {
        this.code = code;
    }

    public static void changeTo(ConsoleColors color){
        System.out.println(color);
    }

    public static void reset(){
        System.out.println(ConsoleColors.RESET);
    }

    @Override
    public String toString() {
        return code;
    }
}
