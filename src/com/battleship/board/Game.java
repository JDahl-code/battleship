package com.battleship.board;

import com.battleship.player.Player;

public class Game {

    public static void initialize(){

        String userName = "name";

        //create player, opponent, and two boards, one for player and one for opponent.
        Board playerBoard = new Board();
        Player player = new Player(playerBoard, userName);

        Board oppBoard = new Board();
        //Opponent opponent = new Opponent(oppBoard);
        playerBoard.display();

    }

}