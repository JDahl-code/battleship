package com.battleship.board;

import org.junit.Test;
import com.battleship.gameplay.Opponent;

public class PlayerDisplay {

    public void playerDisplay(Board board) {

        String symbol1 = "#";
        String symbol2 = "X";
        String symbol3 = " ";

        System.out.print("  ");
        for (X_Coord x : X_Coord.values()) {
            System.out.print(x.getIdentifier() + " ");
        }
        System.out.print("\n");
        for (Y_Coord y : Y_Coord.values()) {
            System.out.printf("%s", y.getIdentifier());
            for (X_Coord x : X_Coord.values()) {
                if (board.findByCoords(x.getIdentifier(), y.getIdentifier()).hasShip() && board.findByCoords(x.getIdentifier(), y.getIdentifier()).getStatus() == PointStatus.Unchecked) {
                    System.out.printf("|%s", symbol1);
                }
                else if (board.findByCoords(x.getIdentifier(), y.getIdentifier()).hasShip() && board.findByCoords(x.getIdentifier(), y.getIdentifier()).getStatus() == PointStatus.Hit){
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