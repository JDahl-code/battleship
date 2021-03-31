package com.battleship.player;


import com.battleship.board.Board;
import com.battleship.board.Point;
import com.battleship.board.PointStatus;
import com.battleship.board.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
            System.out.println("Choose location on grid to place ship. #'s first then letter.  e.g. 1A or 9d");
            input = scanner.nextLine();
            isInputValid = validateInput(input);
        } while (isInputValid == false);
        return input;
    }

    public Point[] placeShip(Board board, Ship ship) {
        boolean available = false;
        List<Point[]> points;// assume the ship length is 3
        do {
            String location = inputFromUser();  // ask user for input and do checks
            // Generate Create a 2D array containg 4 x 3 Points
            points = new ArrayList<>();
            // Fetch the points int the up direction and put in row 0...
            String row = location.substring(0, 1);
            String column = location.substring(1, location.length());
            Point point = board.findByCoords(column, row);
            // Check if Point is marked
            if (point.getStatus() != PointStatus.UNCHECKED) {
                available = false;
                System.err.println("Point chosen is not available");
            } else {
                char rowChar = row.toUpperCase(Locale.ROOT).charAt(0);
                int colNumber = Integer.parseInt(column);
                if (rowChar - 65 >= 2) {
                    // go up direction
                    Point oneAbove = board.findByCoords(column, new String((char) (rowChar - 1) + ""));
                    Point twoAbove = board.findByCoords(column, new String((char) (rowChar - 2) + ""));
                    Point[] upDir = new Point[]{point, oneAbove, twoAbove};
                    points.add(upDir);
                }
                if ('J' - rowChar >= 2) {
                    // go down direction
                    Point oneBelow = board.findByCoords(column, new String((char) (rowChar + 1) + ""));
                    Point twoBelow = board.findByCoords(column, new String((char) (rowChar + 2) + ""));
                    Point[] belowDir = new Point[]{point, oneBelow, twoBelow};
                    points.add(belowDir);

                }
                if (colNumber - 1 >= 2) {
                    //  go left direction
                    String oneL = String.valueOf(colNumber - 1);
                    Point oneLeft = board.findByCoords(oneL, row);
                    String twoL = String.valueOf(colNumber - 2);
                    Point twoLeft = board.findByCoords(twoL, row);
                    Point[] leftDir = new Point[]{point, oneLeft, twoLeft};
                    points.add(leftDir);

                }
                if (10 - colNumber >= 2) {
                    // go right direction
                    String oneR = String.valueOf(colNumber + 1);
                    Point oneRight = board.findByCoords(oneR, row);
                    String twoR = String.valueOf(colNumber + 2);
                    Point twoRight = board.findByCoords(twoR, row);
                    Point[] rightDir = new Point[]{point, oneRight, twoRight};
                    points.add(rightDir);

                }
                for (Point [] p : points) {
                    // check if each point in array is unmarked
                    int count = 0;
                    for (Point p2 : p) {
                        if (p2.getStatus() == PointStatus.UNCHECKED) {
                            count ++;
                        }
                    }
                    if (count == p.length) {
                        board.setChosenShipPoints(p);
                        return p;
                    }
                }
                available = true;   // original was set to false. ***
            }
        }
        while(!available);

        // Check each row if any has all squares clean/unmarked
        // If so, use that return that Point[]
        return new Point[0];                                           // *** added by IntelliJ
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

    private boolean firstCharacterLetter(String input) { // secondCharacterNumber
        if (input.substring(0, 1).matches("^[a-jA-J]")) {
            return true;
        }
        return false;
    }

    private boolean secondCharacterNumber(String input) {  // was secondCharacterNumber
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