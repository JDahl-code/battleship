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
        assertEquals("A", board.findByCoords("2", "A").getYCoord().getIdentifier());
        assertEquals("2", board.findByCoords("2", "A").getXCoord().getIdentifier());
    }
}