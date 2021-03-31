package com.battleship.player;


import com.battleship.board.Board;

import java.util.Scanner;

public class Player {

    Board board;    // make a Board object that belongs to the player
    String name;
    Scanner scanner;

    // ctor
    public Player(Board board, String name) {
        this.board = board;
        this.name = name;
        scanner = new Scanner(System.in);
    }

    // METHODS
    // Loop until input value is within parameters a-j and a-10
    public String inputFromUser() {
        boolean isInputValid = false;
        String input = "";  // declared empty
        do {
            System.out.println("Choose location on grid to place ship.  e.g. A1 or G9");
            input = scanner.nextLine();
            isInputValid = validateInput(input);
        } while (isInputValid == false);
        return input;
    }

    public void placeShip() {
        String location = inputFromUser();


    }

    public boolean validateInput(String input) {
        // Expect input to be 2 characters.
        // 1st character needs to be an 'letter' e.g. "A"
        // 2nd character needs to be a number value
        if (expectTwoOrThreeCharacters(input) && firstCharacterLetter(input) && secondCharacterNumber(input)) {
            return true;
        }
        return false;
    }

    private boolean expectTwoOrThreeCharacters(String input) {
        if (input.length() == 2 || input.length() == 3) {
            return true;
        }
        return false;
    }

    private boolean firstCharacterLetter(String input) {
        if (input.substring(0, 1).matches("^[a-jA-J]")) {
            return true;
        }
        return false;
    }

    private boolean secondCharacterNumber(String input) {
        String subString = "";
        if (input.length() == 2) {
            subString = input.substring(1, 2);
        } else if (input.length() == 3) {
            subString = input.substring(1, 3);
        }
        try {
            int num = Integer.parseInt(subString);
            if (num >= 1 && num <= 10) {
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return false;
    }

    // GETTERS
    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

}