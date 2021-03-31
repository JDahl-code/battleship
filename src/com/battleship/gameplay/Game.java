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
    static String userName = "name";
    static Board playerBoard = new Board();
    static Player player = new Player(playerBoard, userName);

    static Board oppBoard = new Board();
    static Opponent opponent = new Opponent();

    public static void loadBanner() {
        try {
            List<String> lines = Files.readAllLines(Path.of("./resources/banner.txt"));
            ConsoleColors.changeTo(BLUE);
            for (String line : lines) {
                System.out.println(line);
            }
            ConsoleColors.reset();
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException("Unexpected interrupt", e);
        }

    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void initialize() {
        boardDisplay();
        player.inputFromUser();
        player.placeShip();
        Opponent.placeShip();

    }



    public void battle(){
        playerTurn();
        opponentTurn();
        boardDisplay();
        System.out.printf("You have %d ships left.", numberofShips);

    }

    private static void boardDisplay() {
        System.out.print("    Opponent Board");
        oppBoard.display();

        playerBoard.display();
        System.out.println("\n     Your Board");
    }

    private static void playerTurn(){



    }

    public static void opponentTurn(){

    }


}