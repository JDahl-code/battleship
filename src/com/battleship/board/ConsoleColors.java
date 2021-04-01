package com.battleship.board;

public enum ConsoleColors {
    //color reset
    RESET("\033[0m"),

    // colors and corresponding code
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m");

    private final String code;

    ConsoleColors(String code) {
        this.code = code;
    }

    public static void changeTo(ConsoleColors color) {
        System.out.print(color);
    }

    public static void reset() {
        System.out.print(ConsoleColors.RESET);
    }

    @Override
    public String toString() {
        return code;
    }
}
