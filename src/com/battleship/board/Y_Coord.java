package com.battleship.board;

public enum Y_Coord {
    A ("A"), B ("B"), C ("C"), D ("D"), E ("E"), F ("F"), G ("G"), H ("H"), I ("I"), J ("J");

    private final String identifier;

    private Y_Coord(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
