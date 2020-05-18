package game.settings;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Settings {
    private byte standart_data_style=1;//data_style --> way of getting icons position from players
    private String[] dataStyles = new String [2];
    private Scanner input;


    public Settings(Scanner input){
        this.input = input;
        dataStyles[0] = "sequence";
        dataStyles[1] = "numberpad (works only with board size 3)";
    }

    public byte getStandartDataStyle() {
        return standart_data_style;
    }


    public void printSettings(){
        System.out.println("---SETTINGS---");
        System.out.println("1. Data_style: " + dataStyles[standart_data_style-1]);
        System.out.println("2. Exit");
        System.out.println("");
        System.out.println("If you want to change them. Write a number of chosen setting and then write another value");
    }

    public void printDataStyles(){
        System.out.println("---DATA STYLES---");
        System.out.println("1. " + dataStyles[0]);
        System.out.println("2. " + dataStyles[1]);
    }
    private int getValue(int min, int max){
        int value = -1;

        boolean flag = false;
        boolean exceptionFlag = false;

        while(!flag){
            try{
                Scanner input = new Scanner(System.in);
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
    public void changeSettings(){
        int value;

        do {
            printSettings();
            value = getValue(1,2);
            if(value == 1) {
                printDataStyles();
                standart_data_style = (byte) getValue(1,2);
            }
        }while(value!=2);
    }
}
