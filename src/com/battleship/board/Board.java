package com.battleship.board;

import java.util.ArrayList;
import java.util.List;

import static com.battleship.board.ConsoleColors.*;

public class Board {
    private final List<Point> points = new ArrayList<>();
    public Ship carrier = new Ship("carrier", 5);
    public Ship battleship = new Ship("battleship", 4);
    public Ship destroyer = new Ship("destroyer", 3);
    public Ship sub = new Ship("submarine", 3);
    public Ship patrolBoat = new Ship("patrol boat", 2);
    public List<Ship> remainingShips = new ArrayList<>();

    public Board() {
        for (X_Coord x : X_Coord.values()) {
            for (Y_Coord y : Y_Coord.values()) {
                points.add(new Point(x, y));
            }
        }
        remainingShips.add(carrier);
        remainingShips.add(battleship);
        remainingShips.add(destroyer);
        remainingShips.add(sub);
        remainingShips.add(patrolBoat);
    }


    public Point findByCoords(String x, String y) {
        return points.stream().filter(
                point -> point.getXCoord().getIdentifier().equals(x)
                        &&
                        point.getYCoord().getIdentifier().equals(y.toUpperCase())
        ).findAny().get();
    }
    /*  This method display a 10x10 board in color.

                  board
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
    public void display() {

        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        // The y coord line in yellow color
        ConsoleColors.changeTo(YELLOW);
        System.out.print("\n ");
        for (String number : numbers) {
            System.out.print(" " + number);
        }
        ConsoleColors.reset();

        for (int i = 0; i < 10; i++) {
            // x coord line in cyan color
            ConsoleColors.changeTo(CYAN);
            System.out.printf("\n%s", chars[i]);
            ConsoleColors.changeTo(WHITE);
            System.out.print("|");
            ConsoleColors.reset();

            for (int j = 0; j < 10; j++) {
                //System.out.printf("%s|", findByCoords(chars[i],numbers[j]).getStatus().getSymbol());
                //color point status symbol first
                String status = findByCoords( numbers[j],chars[i]).getStatus().getSymbol();
                switch (status) {
                    case " ":
                        break;
                    case "x":
                        ConsoleColors.changeTo(RED);
                        break;
                    case "o":
                        ConsoleColors.changeTo(GREEN);
                        break;
                }
                System.out.print(status);
                ConsoleColors.changeTo(WHITE);
                System.out.print("|");
                ConsoleColors.reset();
            }
        }
    }

    public List<Point> getPoints() {
        return points;
    }
}