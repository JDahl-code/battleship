package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        Point[] testArray = op.makeBranch(board, board.getPoints().get(0), 4, "up");

        for (Point p : testArray) {
            System.out.printf("X: %s | Y: %s%n", p.getXCoord().getIdentifier(), p.getYCoord().getIdentifier());
        }
    }

    @Test
    public void exceptionTest() {
        //ArrayIndexOutOfBoundsException for Array
        //IndexOutOfBoundsException for ArrayList
        ArrayList<Integer> ar = new ArrayList<Integer>();
        ar.add(1);
    }

    @Test
    public void singleItemArrayTest() {
        int[] a = {1};
        for (int num : a) {
            System.out.println(num);
        }
    }
}