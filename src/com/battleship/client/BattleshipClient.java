package com.battleship.client;

import com.battleship.gameplay.Game;

class BattleshipClient {
    public static void main(String[] args) {
        Game game = new Game();
        game.loadBanner();
        game.clearScreen();
        game.initialize();

        game.battle();
        game.over();
   }
}