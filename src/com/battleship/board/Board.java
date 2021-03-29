package com.battleship.board;

import java.util.ArrayList;
import java.util.Collection;
import static com.battleship.board.X_Coord.*;
import static com.battleship.board.Y_Coord.*;

public class Board {
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

    public Collection<Point> getPoints() {
        return points;
    }
}