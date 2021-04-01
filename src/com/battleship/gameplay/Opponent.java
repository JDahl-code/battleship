package com.battleship.gameplay;

import com.battleship.board.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class Opponent {

    private boolean shipFound = false;
    private Point initialHit;
    private Point[][] branches;
    private int hitsInARow = 0;
    private int branchToken;
    private int missToken = 0;


    Point pickRandomPoint(Board board) {
        ArrayList<Point> validPoints = board.getPoints().stream().filter(point -> point.getStatus() == PointStatus.UNCHECKED)
                .collect(Collectors.toCollection(ArrayList::new));
        int rand = (int) (Math.random() * validPoints.size());
        return validPoints.get(rand);
    }

    // length includes the starting point. That way, the length attribute in Ship can be passed.
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
                                Y_Coord.values()[startPoint.getYCoord().ordinal() - i].getIdentifier() // different y-coordinate (decreasing ordinal)
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
                                Y_Coord.values()[startPoint.getYCoord().ordinal() + i].getIdentifier() // different y-coordinate (increasing ordinal)
                        );
                        if (nextInBranch.getStatus() == PointStatus.UNCHECKED) {
                            result[i] = nextInBranch;
                        } else {
                            break addToBranches;
                        }
                        break;
                    case "left":
                        nextInBranch = board.findByCoords(
                                X_Coord.values()[startPoint.getXCoord().ordinal() - i].getIdentifier(), // different x-coordinate (decreasing ordinal
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
                                X_Coord.values()[startPoint.getXCoord().ordinal() + i].getIdentifier(), // different x
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

    Point[][] makeBranches(Board board, Point startPoint, int length) {
        return new Point[][]{
                makeBranch(board, startPoint, length, "up"),
                makeBranch(board, startPoint, length, "down"),
                makeBranch(board, startPoint, length, "left"),
                makeBranch(board, startPoint, length, "right")
        };
    }

    public void placeShip(Board board, Ship ship) {
        boolean valid = false;

        while (!valid) {
            Point startPoint = pickRandomPoint(board);
            Point[][] branches = makeBranches(board, startPoint, ship.getLength());
            Point[] branch = branches[(int) (Math.random() * 4)];

            if (branch.length == ship.getLength()) {
                int unoccupied = 0;

                for (Point point : branch) {
                    if (!point.hasShip()) {
                        unoccupied++;
                    }
                }
                if (unoccupied == ship.getLength()) {
                    for (Point point : branch) {
                        point.setShip(ship);
                        point.setHasShip(true);
                    }
                    valid = true;
                }
            }
        }
    }

    // guess behaviors

    // Do this when the opponent doesn't know where a ship is.
    public void blindGuess(Board board) {
        Point point = pickRandomPoint(board);
        point.target();
        if (point.hasShip()) {
            shipFound = true;
            initialHit = point;
            hitsInARow++;
        }
    }

    public void onInitialHit(Board board) {
        int maxShipLength = Collections.max(board.remainingShips).getLength();
        branches = makeBranches(board, initialHit, maxShipLength);
        branchToken = (int) (Math.random() * 4) + 1;
    }

    private void switchBranch() {
        if (missToken == 1) {
            if (branchToken % 2 == 0) {
                branchToken--;
            } else {
                branchToken++;
            }
        } else if (missToken > 1) {
            if (branchToken < 3) {
                branchToken = (int) ((Math.random() * 2) + 3);
            } else {
                branchToken = (int) ((Math.random() * 2) + 1);
            }
            missToken = 0;
        }
    }

    public void followUp() {
        switchBranch();

        if (branches[branchToken - 1].length > hitsInARow) {
            Point point = branches[branchToken - 1][hitsInARow];
            point.target();
            if (point.hasShip()) {
                hitsInARow++;
                missToken = 0;
            } else {
                hitsInARow = 1;
                missToken++;
            }
        } else {
            hitsInARow = 1;
            missToken++;
            followUp();
        }
    }

    public void takeTurn(Board board) {
        if (!shipFound) {
            blindGuess(board);
            if (shipFound) {
                onInitialHit(board);
            }
        } else {
            followUp();
            if (initialHit.getShip().isDestroyed()) {
                board.remainingShips.remove(initialHit.getShip());
                shipFound = false;
                hitsInARow = 0;
            }
        }
    }
}