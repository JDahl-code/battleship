package com.battleship.board;

import java.util.ArrayList;
import java.util.List;

import static com.battleship.board.ConsoleColors.*;

public class Board {
    private final List<Point> points = new ArrayList<>();
    private Point [] chosenShipPoints;

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

        String shipBody = "*";
        String hit = "x";
        String miss = "o";
        String empty = " ";
        String status;

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

                //color point status symbol first
                Point point = findByCoords( numbers[j],chars[i]);

                if (point.hasShip() && point.getStatus()== PointStatus.UNCHECKED ){
                    status = shipBody;
                    ConsoleColors.changeTo(WHITE);
                } else if(point.getStatus() == PointStatus.HIT){
                    status = hit;
                    ConsoleColors.changeTo(RED);
                }else if (point.getStatus() == PointStatus.MISS) {
                    status = miss;
                    ConsoleColors.changeTo(GREEN);
                }else{
                    status = empty;
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

    public Point[] getChosenShipPoints() {
        return chosenShipPoints;
    }

    public void setChosenShipPoints(Point[] chosenShipPoints) {
        this.chosenShipPoints = chosenShipPoints;
    }
}