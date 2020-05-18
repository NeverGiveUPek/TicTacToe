package game.play.gameSettings;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GameSettings {
    private int numberGameMode;
    private int numberStartingIcon;
    private int player1Icon; //1. X   2. o
    private int boardSize;
    private int botLvl =0;
    private Scanner input;

    public GameSettings(Scanner input){
        this.input = input;
    }

    public int getBoardSize(){
        return boardSize;
    }
    public int getPlayer1Icon(){
        return player1Icon;
    }
    public int getNumberGameMode(){
        return numberGameMode;
    }
    public int getNumberStartingIcon(){
        return numberStartingIcon;
    }
    public int getBotLvl(){return botLvl;}

    private void printGameMode(){
        System.out.println("Set your game mode");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs bot");
    }
    private void printStartingIcon() {
        System.out.println("Who starts?");
        System.out.println("1. X");
        System.out.println("2. o");
    }
    private void printPlayer1Icon(){
        System.out.println("Select icon for P1: ");
        System.out.println("1. 'X'");
        System.out.println("2. 'o'");
    }
    private void printBotLvl(){
        System.out.println("Choose bot difficulty: ");
        System.out.println("1. 'God' ");
        System.out.println("2. 'Average' ");
        System.out.println("3. 'Easy' ");
    }
    private void printBoardSize(){
        System.out.println("Board is always a square");
        System.out.println("Enter a size of side <3-10> : ");
        System.out.println("Playing with bot is anable only with board size 3");
    }

    private int getValue(int min, int max){
        int value = -1;

        boolean flag = false;
        boolean exceptionFlag = false;

        while(!flag){
            try{
                value = input.nextInt();
                exceptionFlag = true;
            }catch(InputMismatchException e){
                System.out.println("Wrog input!");
            }
            if(exceptionFlag && value >= min && value <= max) flag = true;
            if(value < min || value > max){
                System.out.println("Number should be between " + min +" and " + max);
            }
        }
        return value;
    }


    public void selectSettings() {
        printBoardSize();
        boardSize = getValue(3,10);
        if(boardSize ==3){
            printGameMode();
            numberGameMode = getValue(1,2);
        }
        else{
            numberGameMode = 1;
        }
        if(numberGameMode == 2){
            printBotLvl();
            botLvl = getValue(1,3);
        }
        printStartingIcon();
        numberStartingIcon = getValue(1,2);
        printPlayer1Icon();
        player1Icon = getValue(1,2);
    }
}
