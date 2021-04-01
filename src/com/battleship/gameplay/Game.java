package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.ConsoleColors;
import com.battleship.board.Ship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.battleship.board.ConsoleColors.*;

public class Game {

    private final Board playerBoard = new Board();
    private final Player player = new Player();

    private final Board oppBoard = new Board();
    private final Opponent opponent = new Opponent();

    private int numberOfPlayerShipsDestroyed = 0;
    private int numberOfOpponentShipsDestroyed = 0;

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

        player.placeShip(playerBoard, playerBoard.carrier);
        player.placeShip(playerBoard, playerBoard.battleship);
        player.placeShip(playerBoard, playerBoard.destroyer);
        player.placeShip(playerBoard, playerBoard.sub);
        player.placeShip(playerBoard, playerBoard.patrolBoat);

        opponent.placeShip(oppBoard, oppBoard.carrier);
        opponent.placeShip(oppBoard, oppBoard.battleship);
        opponent.placeShip(oppBoard, oppBoard.destroyer);
        opponent.placeShip(oppBoard, oppBoard.sub);
        opponent.placeShip(oppBoard, oppBoard.patrolBoat);

        boardDisplay();
    }


    public void battle() {
        while (oppBoard.remainingShips.size() != 0 && playerBoard.remainingShips.size() != 0) {
            playerTurn();

            opponentTurn();

            boardDisplay();

            System.out.printf("You have %d ships left.\n", playerBoard.remainingShips.size());
            System.out.printf("Opponent has %d ships left.\n", oppBoard.remainingShips.size());
        }
    }

    private void boardDisplay() {
        System.out.print("    Opponent Board");
        oppBoard.display();

        playerBoard.display();
        System.out.println("\n     Your Board");
    }

    private void playerTurn() {
        player.takeTurn(oppBoard);

    }

    private void opponentTurn() {
        opponent.takeTurn(playerBoard);
    }

    public void over() {
        if (playerBoard.remainingShips.size() == 0) {
            System.out.println("Sorry, you lost the battle.");
        } else if (oppBoard.remainingShips.size() == 0) {
            System.out.println("Yeah, you won the battle.");
        }
    }

}