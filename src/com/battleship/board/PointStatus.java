package com.battleship.board;

enum PointStatus {
    Unchecked (" "), Hit ("x"), Miss ("o");

    private String symbol;

    PointStatus(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }
}
