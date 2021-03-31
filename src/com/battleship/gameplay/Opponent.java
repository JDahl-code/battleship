package com.battleship.gameplay;

import com.battleship.board.Point;
import com.battleship.board.Board;
import com.battleship.board.X_Coord;
import com.battleship.board.Y_Coord;

import java.util.ArrayList;
import java.util.Collection;

public class Opponent {

    private boolean shipFound = false;
    private Point initialHit;
    private int maxLength = 5;
    private Point[][] branches;
    private int hitsInARow = 0;
    private int branchToken;
    private int missToken = 0;

    Point pickRandomPoint(Board board) {
        int rand = (int) (Math.random() * 100);
        return board.getPoints().get(rand);
    }

    // length includes the starting point. That way, the length attribute in Ship can be passed.
    Point[] makeBranch(Board board, Point startPoint, int length, String direction) {
        Point[] result = new Point[length];
        result[0] = startPoint;

        for (int i = 1; i < length; i++) {
            switch (direction) {
                case "up":
                    result[i] = board.findByCoords(
                                                   startPoint.getXCoord().getIdentifier(), // same x-coordinate
                                                   Y_Coord.values()[startPoint.getYCoord().ordinal() - i].getIdentifier() // different y-coordinate (decreasing ordinal)
                                                   );
                    break;
                case "down":
                    result[i] = board.findByCoords(
                                                   startPoint.getXCoord().getIdentifier(), // same x-coordinate
                                                   Y_Coord.values()[startPoint.getYCoord().ordinal() + i].getIdentifier() // different y-coordinate (increasing ordinal)
                                                   );
                    break;
                case "left":
                    result[i] = board.findByCoords(
                                                   X_Coord.values()[startPoint.getXCoord().ordinal() - i].getIdentifier(), // different x-coordinate (decreasing ordinal
                                                   startPoint.getYCoord().getIdentifier() // same y-coordinate
                                                   );
                    break;
                case "right":
                    result[1] = board.findByCoords(
                                                   X_Coord.values()[startPoint.getXCoord().ordinal() + i].getIdentifier(), // different x
                                                   startPoint.getYCoord().getIdentifier() // same y
                                                   );
            }
        }
        return result;
    }

    Point[][] makeBranches(Board board, Point startPoint, int length) {
        return new Point[][] {
                makeBranch(board, startPoint, length, "up"),
                makeBranch(board, startPoint, length, "down"),
                makeBranch(board, startPoint, length, "left"),
                makeBranch(board, startPoint, length, "right")
        };
    }

    // guess behaviors

    /*
     * Start game with blind guess. Continue until hit.
     * Once a ship is hit, make four branches off of that point.
     * Select a random branch and start selecting from index 1 (0 is the point that was just hit.)
     * If there is another hit, continue along the branch.
     * If there is a miss, go to the opposite branch (left -> right; up -> down).
     * If there is another miss, go to one of the two remaining branches.
     * repeat
     * Continue until ship is destroyed.
     * Resume random guesses.
     *
     * Required info:
     *  -Whether a ship has been found.
     *      * Did a random guess result in a hit?
     *  -The point that returned a hit.
     *  -branches from that point.
     *  -Was the follow up attack a hit or a miss?
     *  -Current orientation (vertical or horizontal) or which pair of branches to use.
     *  -The length of the longest unsunk ship
     *  -When a ship is sunk and which ship it is.
     */

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
        branches = makeBranches(board, initialHit, maxLength);
        branchToken = (int) (Math.random() * 4) + 1;
    }

    public void followUp() {
        if (missToken == 1) {
            if (branchToken % 2 == 0) {
                branchToken--;
            }
            else {
                branchToken++;
            }
        }
        else if (missToken > 1) {
            if (branchToken < 3) {
                branchToken = (int) ((Math.random() * 2) + 3);
            }
            else {
                branchToken = (int) ((Math.random() * 2) + 1);
            }
        }

        Point point = branches[branchToken-1][hitsInARow];
        point.target();
        if (point.hasShip()) {
            hitsInARow++;
            missToken = 0;
        }
        else {
            hitsInARow = 1;
            missToken++;
        }
    }

    public void takeTurn(Board board) {
        if (!shipFound) {
            blindGuess(board);
            if (shipFound) {
                onInitialHit(board);
            }
        }
        else {
            followUp();
            if (initialHit.getShip().isDestroyed()) {
                shipFound = false;
            }
        }
    }

    /*
     * To Do:
     *      - copy the list of points from board to make a list of valid points, from which points are removed after selection.
     *      - handle or prevent occurrences of empty branches or branches with fewer than expected points
     *      - handle not having all four branches
     *      - update maxLength based on the length of the longest remaining ship. Maybe make a list or array of ships in respective Board instances.
     *      - ship placement.
     */
}