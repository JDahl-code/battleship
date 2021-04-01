package com.battleship.board;

class RepeatGuessException extends RuntimeException {

    RepeatGuessException(String message) {
        super(message);
    }
}