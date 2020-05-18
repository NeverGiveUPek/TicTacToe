package game.play.figures;

import game.play.GameBoard.GameMap;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerHuman  extends Player {
    private byte dataStyle;
    private Scanner input;

    public PlayerHuman(byte dataStyle, int boardSize, Scanner input){
        this.input = input;
        if(dataStyle == 2 && boardSize > 3) this.dataStyle = 1;
        else this.dataStyle = dataStyle;
    }


    @Override
    public void doMove(GameMap map) {
        boolean correctCords;
        do{
            correctCords = true;
            getCords(dataStyle);
            if (!map.checkCords(x,y)) {
                System.out.println("Your cords should be beetween 0-" + (map.getMapSize()-1));
                correctCords = false;
                continue;
            }
            if (!map.checkCellEmpty(x, y)) {
                System.out.println("This cell is already occupied");
                correctCords = false;
                continue;
            }
            map.printMap();
        }while(!correctCords);
    }



    private void getCords(byte dataStyle){
        if(dataStyle == 1) getCordsInSeq();
        else if(dataStyle == 2) getCordsAsBoard();
    }

    private void getCordsInSeq(){

        boolean good = false;
        while(!good) {
            try {
                System.out.println("x: ");
                x = input.nextInt();
                System.out.println("y: ");
                y = input.nextInt();
                good = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            }
        }
    }

    private void getCordsAsBoard(){

        boolean good = false;
        while(!good) {
            try {
                int number = input.nextInt();
                x = (number-1) % 3;
                y = 2 - ((number-1)/3);
                System.out.println("x: " + x + "y: " + y);
                good = true;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
            }
        }


    }

}
