package com.battleship.board;

import static com.battleship.board.PointStatus.*;

class Point {

    private X_Coord xCoord;
    private Y_Coord yCoord;
    private Ship ship;
    private boolean hasShip = false;
    private PointStatus status = Unchecked;

    Point(X_Coord xCoord, Y_Coord yCoord) {
        setXCoord(xCoord);
        setYCoord(yCoord);
    }

    void target() {
        if (status == Unchecked) {
            if (hasShip) {
                ship.hit();
                setStatus(Hit);
            } else {
                setStatus(Miss);
            }
        }
        else {
            // Maybe throw exception. Have player guess again. Don't handle exception here, just throw.
            // To do: make new exception class.
        }
    }

    public X_Coord getXCoord() {
        return xCoord;
    }

    public void setXCoord(X_Coord xCoord) {
        this.xCoord = xCoord;
    }

    public Y_Coord getYCoord() {
        return yCoord;
    }

    public void setYCoord(Y_Coord yCoord) {
        this.yCoord = yCoord;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean isHasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public PointStatus getStatus() {
        return status;
    }

    public void setStatus(PointStatus status) {
        this.status = status;
    }
}