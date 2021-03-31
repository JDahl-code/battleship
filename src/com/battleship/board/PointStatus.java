package com.battleship.board;

public enum PointStatus {
    HIT("x"), MISS("o"), SHIPBODY("█"), UNCHECKED(" ");

    private String symbol;

    PointStatus(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }
}
