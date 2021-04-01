package com.battleship.board;

import static com.battleship.board.PointStatus.*;

public class Point {

    private XCoord xCoord;
    private YCoord yCoord;
    private Ship ship;
    private boolean hasShip = false;
    private PointStatus status = UNCHECKED;

    Point(XCoord xCoord, YCoord yCoord) {
        setXCoord(xCoord);
        setYCoord(yCoord);
    }

    public void target() throws RepeatGuessException {

        if (status == UNCHECKED) {
            if (hasShip) {
                ship.hit();
                setStatus(HIT);
            } else {
                setStatus(MISS);
            }
        } else {
            throw new RepeatGuessException("You have already selected that point. Try again.");
        }
    }

    public XCoord getXCoord() {
        return xCoord;
    }

    public void setXCoord(XCoord xCoord) {
        this.xCoord = xCoord;
    }

    public YCoord getYCoord() {
        return yCoord;
    }

    public void setYCoord(YCoord yCoord) {
        this.yCoord = yCoord;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean hasShip() {
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
