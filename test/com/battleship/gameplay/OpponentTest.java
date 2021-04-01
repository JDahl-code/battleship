package com.battleship.gameplay;

import com.battleship.board.Board;
import com.battleship.board.Point;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OpponentTest {

    Board board;
    Opponent op;

    @Before
    public void init() {
        board = new Board();
        op = new Opponent();
    }
/*
    @Test
    public void branchTest() {
        Point[] testArray = op.makeBranch(board, board.getPoints().get(0), 4, "up");

        for (Point p : testArray) {
            System.out.printf("X: %s | Y: %s%n", p.getXCoord().getIdentifier(), p.getYCoord().getIdentifier());
        }
    }
    */
/*
    @Test
    public void branchesTest() {
        Point[][] branches = op.makeBranches(board, board.findByCoords("7", "H"), 4);

        for (Point[] branch : branches) {
            for (Point p : branch) {
                System.out.printf("%s:%s, ", p.getXCoord().getIdentifier(), p.getYCoord().getIdentifier());
            }
            System.out.println("\n");
        }
    }
*/
    @Test
    @Ignore
    public void exceptionTest() {
        //ArrayIndexOutOfBoundsException for Array
        //IndexOutOfBoundsException for ArrayList
        ArrayList<Integer> ar = new ArrayList<>();
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