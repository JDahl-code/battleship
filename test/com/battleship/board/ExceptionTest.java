package com.battleship.board;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public void instException() throws RepeatGuessException{
        RepeatGuessException e = new RepeatGuessException("test");
        System.out.println(e.getMessage());
    }
}