package com.battleship.client;

import com.battleship.gameplay.Game;

import java.io.IOException;

class BattleshipClient {
    public static void main(String[] args) throws InterruptedException {
        Game.loadBanner();
        Game.clearScreen();
        Game.initialize();



    }
}