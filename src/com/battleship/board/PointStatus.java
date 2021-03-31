package com.battleship.board;

public enum PointStatus {
    HIT("x"), MISS("o"), UNCHECKED(" ");

    private final String symbol;

    PointStatus(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }
}
