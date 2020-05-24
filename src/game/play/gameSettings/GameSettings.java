package game.play.gameSettings;

import game.play.figures.BotLevel;
import game.play.Icon.Icon;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameSettings {
    private int numberGameMode;
    private int player1Icon; //1. X   2. o
    private int boardSize;
    private Icon startingIcon;
    private BotLevel botLvl;
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
    public Icon getStartingIcon(){
        return startingIcon;
    }
    public BotLevel getBotLvl(){return botLvl;}

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
                input.nextLine();
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
            int numberOfBot = getValue(1,3);
            if(numberOfBot == 1) botLvl = BotLevel.GOD;
            else if(numberOfBot == 2) botLvl = BotLevel.AVERAGE;
            else botLvl = BotLevel.POOR;
        }
        printStartingIcon();
        int numberStartingIcon = getValue(1,2);
        if(numberStartingIcon == 1) {
            startingIcon = new Icon('X');
        }
        else{
            startingIcon = new Icon('o');
        }
        printPlayer1Icon();
        player1Icon = getValue(1,2);
    }
}
