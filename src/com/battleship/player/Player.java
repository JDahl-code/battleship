package com.battleship.player;


import com.battleship.board.Board;

class Player {

    Board board;    // make a Board object that belongs to the player
    String name;

    // ctor
    public Player(Board board, String name) {
        this.board = board;
        this.name = name;
    }

    // GETTERS
    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

}