package com.battleship.board;

import java.util.ArrayList;
import java.util.Collection;
import static com.battleship.board.X_Coord.*;
import static com.battleship.board.Y_Coord.*;

class Board {
    private Collection<Point> points = new ArrayList<>();

    public Board() {
        for (X_Coord x : X_Coord.values()) {
            for (Y_Coord y : Y_Coord.values()) {
                points.add(new Point(x, y));
            }
        }
    }

    public Point findByCoords(String x, String y) {
        return points.stream().filter(
                point -> point.getXCoord().getIdentifier().equals(x)
                        &&
                        point.getYCoord().getIdentifier().equals(y.toUpperCase())
        ).findAny().get();
    }

    public void display() {
        /*board
          1 2 3 4 5 6 7 8 9 10
        A| | | | | | | | | | |
        B| | | | | | | | | | |
        C| | | | | | | | | | |
        D| | | | | | | | | | |
        E| | | | | | | | | | |
        F| | | | | | | | | | |
        G| | | | | | | | | | |
        H| | | | | | | | | | |
        I| | | | | | | | | | |
        J| | | | | | | | | | |
        */
        String[] numbers = {"1","2","3","4","5","6","7","8","9","10"};
        String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        // The y coord line
        System.out.println(" ");
        for(String number : numbers){
            System.out.print(" "+ number);
        }

        for (int i = 0; i < 10; i++) {
            // x coord line
            System.out.printf("\n%s|", chars[i]);
            for (int j = 0; j < 10; j++) {
                System.out.printf("%s|", findByCoords(chars[i],numbers[j]).getStatus().getSymbol());
            }
        }
    }
}