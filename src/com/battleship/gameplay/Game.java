package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.ConsoleColors;
import com.battleship.board.Ship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static com.battleship.board.ConsoleColors.BLUE;

public class Game {
    //initialize  player, opponent, player board and opponent board.
    private final Board playerBoard = new Board();
    private final Player player = new Player();

    private final Board oppBoard = new Board();
    private final Opponent opponent = new Opponent();

    /*
     * This method load text of battleship and freeze for 2 seconds
     */
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

    /*
     * This method clear the console screen
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void initialize() {
        //display an empty first
        boardDisplay();

        //prompt player to place 5 ships on the playerboard
        for(Ship playerShip : playerBoard.remainingShips){
            player.placeShip(playerBoard, playerShip);
            boardDisplay();
        }

        //opponent will automatically place 5 ships on his board.
        for(Ship oppoShip : oppBoard.remainingShips ){
            opponent.placeShip(oppBoard, oppoShip);
        }
        //display boards again to show player where his ships at
        boardDisplay();
    }

    /*
     * This methods simulates the battle between player and opponent. Player takes turn first and fire rounds at opponent
     * board, then opponent fires back at player board. After each round, display two boards to show result.
     */
    public void battle() {
        System.out.println("Game has been initialized, please attack.");
        // if each board's still has remaining ships, keep looping
        while (oppBoard.remainingShips.size() != 0 && playerBoard.remainingShips.size() != 0) {
            playerTurn();

            opponentTurn();

            boardDisplay();

            System.out.printf("You have %d ships left.\n", playerBoard.remainingShips.size());
            System.out.printf("Opponent has %d ships left.\n", oppBoard.remainingShips.size());
        }
    }

    /*
     * This method displays the board.
     */
    private void boardDisplay() {

        System.out.print("    Opponent Board");

        oppBoard.display(false);


        playerBoard.display(true);
        System.out.println("\n     Your Board");
    }

    private void playerTurn() {
        player.takeTurn(oppBoard);

    }

    private void opponentTurn() {
        opponent.takeTurn(playerBoard);
    }

    /*
     * This method output the final results.
     */
    public void over() {
        if (playerBoard.remainingShips.size() == 0) {
            System.out.println("Sorry, you lost the battle.");
            oppBoard.display(true);
            playerBoard.display(true);
        } else if (oppBoard.remainingShips.size() == 0) {
            System.out.println("Yeah, you won the battle.");
        }
        System.out.println("\n Please enter any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}