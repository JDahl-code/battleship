package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.ConsoleColors;
import com.battleship.board.Ship;
import com.battleship.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.battleship.board.ConsoleColors.*;

public class Game {
    private final String userName = "name";
    private final Board playerBoard = new Board();
    private final Player player = new Player(playerBoard, userName);

    private final Board oppBoard = new Board();
    private final Opponent opponent = new Opponent();

    private final int numberOfPlayerShips = 1;
    private final int numberOfOpponentShips = 1;

    public void loadBanner() {
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


    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void initialize() {
        boardDisplay();
        player.inputFromUser();
        Ship ship = new Ship("Carrier", 5);
        player.placeShip();
        opponent.placeShip(oppBoard,ship);

    }


    public void battle() {
        while (numberOfPlayerShips != 0 && numberOfOpponentShips != 0) {
            playerTurn();
            opponentTurn();
            boardDisplay();

            System.out.printf("You have %d ships left.\n", numberOfPlayerShips);
            System.out.printf("Opponent has %d ships left.\n", numberOfOpponentShips);
        }
    }

    private void boardDisplay() {
        System.out.print("    Opponent Board");
        oppBoard.display();

        playerBoard.display();
        System.out.println("\n     Your Board");
    }

    private void playerTurn() {


    }

    private void opponentTurn() {

    }

    public void over() {
        if (numberOfPlayerShips == 0) {
            System.out.println("Sorry, you lost the battle.");
        } else if (numberOfOpponentShips == 0) {
            System.out.println("Yeah, you won the battle.");
        }
    }

}