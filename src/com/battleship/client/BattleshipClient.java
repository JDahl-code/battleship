package com.battleship.client;

import com.battleship.gameplay.Game;

class BattleshipClient {
    public static void main(String[] args) {
        //initialize a new game
        Game game = new Game();

        game.loadBanner();

        game.clearScreen();

        game.initialize();

        game.battle();

        game.over();
    }
}