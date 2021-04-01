package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.ConsoleColors;
import com.battleship.board.Ship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static com.battleship.board.ConsoleColors.*;

public class Game {
    private final String userName = "name";
    private final Board playerBoard = new Board();
    private final Player player = new Player();

    private final Board oppBoard = new Board();
    private final Opponent opponent = new Opponent();

    private int numberOfPlayerShipsDestroyed = 0;
    private int numberOfOpponentShipsDestroyed = 0;

    private final int SHIPSCOUNT = 5;

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

        opponent.placeShip(oppBoard,oppBoard.carrier);
        opponent.placeShip(oppBoard,oppBoard.battleship);
        opponent.placeShip(oppBoard,oppBoard.destroyer);
        opponent.placeShip(oppBoard,oppBoard.sub);
        opponent.placeShip(oppBoard,oppBoard.patrolBoat);


        boardDisplay();
    }


    public void battle() {
        while (numberOfPlayerShipsDestroyed != SHIPSCOUNT && numberOfOpponentShipsDestroyed != SHIPSCOUNT) {
            playerTurn();

            numberOfOpponentShipsDestroyed = (int) oppBoard.remainingShips.stream().filter(Ship::isDestroyed).count();


            opponentTurn();

            numberOfPlayerShipsDestroyed = (int) playerBoard.remainingShips.stream().filter(Ship::isDestroyed).count();

            boardDisplay();

            System.out.printf("You have %d ships left.\n", 5-numberOfPlayerShipsDestroyed);
            System.out.printf("Opponent has %d ships left.\n", 5-numberOfOpponentShipsDestroyed);
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
        if (numberOfPlayerShipsDestroyed == SHIPSCOUNT) {
            System.out.println("Sorry, you lost the battle.");
        } else if (numberOfOpponentShipsDestroyed == SHIPSCOUNT) {
            System.out.println("Yeah, you won the battle.");
        }
    }

}