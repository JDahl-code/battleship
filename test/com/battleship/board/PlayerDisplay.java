package com.battleship.board;

import org.junit.Test;
import com.battleship.gameplay.Opponent;

public class PlayerDisplay {

    public void playerDisplay(Board board) {

        String symbol1 = "#";
        String symbol2 = "X";
        String symbol3 = " ";

        System.out.print("  ");
        for (XCoord x : XCoord.values()) {
            System.out.print(x.getIdentifier() + " ");
        }
        System.out.print("\n");
        for (YCoord y : YCoord.values()) {
            System.out.printf("%s", y.getIdentifier());
            for (XCoord x : XCoord.values()) {
                if (board.findByCoords(x.getIdentifier(), y.getIdentifier()).hasShip() && board.findByCoords(x.getIdentifier(), y.getIdentifier()).getStatus() == PointStatus.UNCHECKED) {
                    System.out.printf("|%s", symbol1);
                }
                else if (board.findByCoords(x.getIdentifier(), y.getIdentifier()).hasShip() && board.findByCoords(x.getIdentifier(), y.getIdentifier()).getStatus() == PointStatus.HIT){
                    System.out.printf("|%s", symbol2);
                }
                else {
                    System.out.printf("|%s", symbol3);
                }
            }
            System.out.print("|\n");
        }
    }

    Board b = new Board();
    Opponent op = new Opponent();

    @Test
    public void test() {
        for (Ship ship : b.remainingShips) {
            op.placeShip(b, ship);
        }
        playerDisplay(b);
    }
}