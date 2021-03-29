package com.battleship.board;

public enum X_Coord {
    One ("1"), Two ("2"), Three ("3"), Four ("4"), Five ("5"),
    Six ("6"), Seven ("7"), Eight ("8"), Nine ("9"), Ten ("10");
    private final String identifier;

    X_Coord(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
