package com.battleship.gameplay;

import com.battleship.board.Point;
import com.battleship.board.Board;
import com.battleship.board.X_Coord;
import com.battleship.board.Y_Coord;

import java.util.ArrayList;
import java.util.Collection;

public class Opponent {

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
                                                   startPoint.getXCoord().getIdentifier(),
                                                   Y_Coord.values()[startPoint.getYCoord().ordinal() - i].getIdentifier()
                                                   );
                    break;
                case "down":
                    result[i] = board.findByCoords(
                                                   startPoint.getXCoord().getIdentifier(),
                                                   Y_Coord.values()[startPoint.getYCoord().ordinal() + i].getIdentifier()
                                                   );
                    break;
                case "left":
                    result[i] = board.findByCoords(
                                                   X_Coord.values()[startPoint.getXCoord().ordinal() - i].getIdentifier(),
                                                   startPoint.getYCoord().getIdentifier()
                                                   );
                    break;
                case "right":
                    result[1] = board.findByCoords(
                                                   X_Coord.values()[startPoint.getXCoord().ordinal() + i].getIdentifier(),
                                                   startPoint.getYCoord().getIdentifier()
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

    // Do this when the opponent doesn't know where a ship is.
    public void blindGuess(Board board) {
        Point point = pickRandomPoint(board);
        point.target();
        // if hit, store this point for future guesses.
    }

    public void afterInitialHit() {

    }
}