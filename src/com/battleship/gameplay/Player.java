package com.battleship.gameplay;

import com.battleship.board.*;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

class Player {


    public void placeShip(Board board, Ship ship) {
        // ship needs to place points on the board
        // need a way to get a group of points.  Can use the branch method.


        Point startPoint = null;
        boolean validPoint = false;
        while (!validPoint) {
            System.out.printf("Place your %s (%d spaces).%n", ship.getName(), ship.getLength());
            try {
                startPoint = returnPointFromInput(board);
                if (startPoint != null && !startPoint.hasShip()) {
                    validPoint = true;
                } else {
                    System.out.println("Invalid input. Please enter a coordinate pair. Ex: 1A (Case insensitive).");
                }
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        int emptySpaces = 0;
        boolean validBranch = false;
        Point[] branch = null;
        while (!validBranch) {

            String direction = null;
            boolean validDirection = false;
            while (!validDirection) {
                try {
                    direction = directionFromInput();
                    validDirection = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            branch = makeBranch(board, startPoint, ship.getLength(), direction);
            for (Point point : branch) {
                if (!point.hasShip()) {
                    emptySpaces++;
                }
            }
            if (emptySpaces == ship.getLength()) {
                validBranch = true;
            } else {
                System.out.println("You can't put a ship there.  Not enough room");
                emptySpaces = 0;
            }
        }

        // pass ship to point
        for (Point point : branch) {
            point.setShip(ship);
            point.setHasShip(true);
        }
    }

    String getCoordinateFromUser() throws IllegalArgumentException {
        System.out.println("Please enter a coordinate pair. Ex: 1A (Case insensitive).");
        String input = new Scanner(System.in).nextLine();

        // Covers length
        if (input.length() > 3 || input.length() < 2) {
            throw new IllegalArgumentException("Input is invalid.  Please " +
                    "choose no more than 3 characters. X coord, Y coord.  e.g. 1B");
        } else
            return input;
    }

    // parsing input and return a point.
    Point returnPointFromInput(Board board)
            throws IllegalArgumentException, NoSuchElementException {
        String input = getCoordinateFromUser();
        String x = null;
        String y = null;
        if (input.length() == 2) {
            x = input.substring(0, 1);
            y = input.substring(1).toUpperCase();
        } else if (input.length() == 3) {
            x = input.substring(0, 2);
            y = input.substring(2).toUpperCase();
        }
        try {
            return board.findByCoords(x, y);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Input is invalid.  Please " +
                    "choose no more than 3 characters. X coord, Y coord.  e.g. 1B");
        }

    }

    String directionFromInput() throws IllegalArgumentException {
        System.out.println("Please choose direction to place ship. e.g. up, down, left, right");
        String input = new Scanner(System.in).nextLine().toLowerCase();
        String[] directions = {"up", "down", "left", "right"};
        boolean valid = false;
        for (String direction : directions) {
            if (input.equals(direction)) {
                valid = true;
                break;
            }
        }
        if (valid) {
            return input;
        } else throw new IllegalArgumentException("Invalid argument.  Please choose up, down, left or right");
    }

    public void takeTurn(Board board) {
        boolean valid = false;
        while (!valid) {
            try {
                Point point = returnPointFromInput(board);
                if (point != null) {
                    if (point.getStatus() == PointStatus.UNCHECKED) {
                        point.target();
                        valid = true;
                        if (point.hasShip()) {
                            if (point.getShip().getHitCount() == point.getShip().getLength()) {
                                point.getShip().setDestroyed(true);
                            }
                            if (point.getShip().isDestroyed()) {
                                System.out.printf("You destroyed my %s!\n", point.getShip().getName());
                                board.remainingShips.remove(point.getShip());

                            }
                        }
                    }
                    else {
                        System.out.println("You already picked that point. Try again.");
                    }
                }
                else {
                    System.out.println("Invalid input. Please enter a coordinate pair. Ex: 1A (Case insensitive).");
                }
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    Point[] makeBranch(Board board, Point startPoint, int length, String direction) {
        Point[] result = new Point[length];
        result[0] = startPoint;
        Point nextInBranch;

        addToBranches:
        for (int i = 1; i < length; i++) {
            try {
                switch (direction) {
                    case "up":
                        nextInBranch = board.findByCoords(
                                startPoint.getXCoord().getIdentifier(), // same x-coordinate
                                YCoord.values()[startPoint.getYCoord().ordinal() - i].getIdentifier() // different y-coordinate (decreasing ordinal)
                        );
                        if (nextInBranch.getStatus() == PointStatus.UNCHECKED) {
                            result[i] = nextInBranch;
                        } else {
                            break addToBranches;
                        }
                        break;
                    case "down":
                        nextInBranch = board.findByCoords(
                                startPoint.getXCoord().getIdentifier(), // same x-coordinate
                                YCoord.values()[startPoint.getYCoord().ordinal() + i].getIdentifier() // different y-coordinate (increasing ordinal)
                        );
                        if (nextInBranch.getStatus() == PointStatus.UNCHECKED) {
                            result[i] = nextInBranch;
                        } else {
                            break addToBranches;
                        }
                        break;
                    case "left":
                        nextInBranch = board.findByCoords(
                                XCoord.values()[startPoint.getXCoord().ordinal() - i].getIdentifier(), // different x-coordinate (decreasing ordinal
                                startPoint.getYCoord().getIdentifier() // same y-coordinate
                        );
                        if (nextInBranch.getStatus() == PointStatus.UNCHECKED) {
                            result[i] = nextInBranch;
                        } else {
                            break addToBranches;
                        }
                        break;
                    case "right":
                        nextInBranch = board.findByCoords(
                                XCoord.values()[startPoint.getXCoord().ordinal() + i].getIdentifier(), // different x
                                startPoint.getYCoord().getIdentifier() // same y
                        );
                        if (nextInBranch.getStatus() == PointStatus.UNCHECKED) {
                            result[i] = nextInBranch;
                        } else {
                            break addToBranches;
                        }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        // create new result array without any null values.
        result = Arrays.stream(result).filter(Objects::nonNull).toArray(Point[]::new);
        return result;
    }

}