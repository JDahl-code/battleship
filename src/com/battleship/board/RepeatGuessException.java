package com.battleship.board;

class RepeatGuessException extends RuntimeException{

    RepeatGuessException() {
        super();
    }

    RepeatGuessException(String message) {
        super(message);
    }
}