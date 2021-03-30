package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OpponentTest {

    Board board;
    Opponent op = new Opponent();

    @Before
    public void init() {
        board = new Board();
    }

    @Test
    public void branchTest() {
        Point[] testArray = op.makeBranch(board, board.getPoints().get(29), 4, "up");

        for (Point p : testArray) {
            System.out.printf("X: %s | Y: %s%n", p.getXCoord().getIdentifier(), p.getYCoord().getIdentifier());
        }

        //board.getPoints().forEach(point -> System.out.printf("%d X: %s | Y: %s%n", board.getPoints().indexOf(point), point.getXCoord().getIdentifier(), point.getYCoord().getIdentifier()));
    }
}