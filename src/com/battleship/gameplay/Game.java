package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.ConsoleColors;
import com.battleship.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static com.battleship.board.ConsoleColors.*;

public class Game {
    public static void loadBanner()  {
        try {
            List<String> lines = Files.readAllLines(Path.of("./resources/banner.txt"));
            ConsoleColors.changeTo(BLUE);
            for(String line : lines){
                System.out.println(line);
            }
            ConsoleColors.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void initialize(){

        String userName = "name";

        //create player, opponent, and two boards, one for player and one for opponent.
        Board playerBoard = new Board();
        Player player = new Player(playerBoard, userName);

        Board oppBoard = new Board();
        Opponent opponent = new Opponent();
        System.out.print("    Opponent Board");
        oppBoard.display();

        playerBoard.display();
        System.out.println("\n     Your Board");

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}