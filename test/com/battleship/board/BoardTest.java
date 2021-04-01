package com.battleship.board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Before
    public void init() {
        board = new Board();
    }

    @Test
    public void pointsTest() {
        assertEquals(100, board.getPoints().size());
    }

    @Test
    public void findPointsTest() {
        assertEquals("A", board.findByCoords("8", "A").getYCoord().getIdentifier());
        assertEquals("2", board.findByCoords("2", "A").getXCoord().getIdentifier());
    }

    @Test
    public void boardColorTest() {
        board.findByCoords("2","B").setHasShip(true);
        board.findByCoords("3","B").setHasShip(true);
        board.findByCoords("4","B").setHasShip(true);
        board.findByCoords("3","B").setStatus(PointStatus.HIT);
        board.findByCoords("4","C").setStatus(PointStatus.MISS);
        board.display();
    }
}