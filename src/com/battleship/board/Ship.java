package com.battleship.board;

public class Ship {

    private String name;
    private int length;
    private int hitCount = 0;
    private boolean isDestroyed = false;

    public Ship(String name, int length) {
        setName(name);
        setLength(length);
    }

    void hit() {
        hitCount += 1;
        if (getHitCount() == getLength()) {
            setDestroyed(true);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
}