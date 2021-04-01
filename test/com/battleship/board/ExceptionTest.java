package com.battleship.board;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public void instException() throws RepeatGuessException {
        RepeatGuessException e = new RepeatGuessException("test");
        System.out.println(e.getMessage());
    }

    @Test
    public void fuckYou() {
        String fu = "fuck you";
        System.out.println(fu.substring(0, 1));
    }
}