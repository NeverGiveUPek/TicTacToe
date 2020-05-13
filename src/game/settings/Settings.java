package game.settings;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Settings {
    private byte standart_data_style=1;//data_style --> way of getting icons position from players
    private String[] data_styles = new String [2];

    //datas required to make option "play again" with last set settings
    private int last_starting_icon;


    public Settings(){
        data_styles[0] = "sequence";
        data_styles[1] = "numberpad (works only with board size 3)";
    }

    public byte get_standart_data_style() {
        return standart_data_style;
    }

    public int get_last_starting_icon() {
        return last_starting_icon;
    }

    public void set_last_starting_icon(int last_starting_icon) {
        this.last_starting_icon = last_starting_icon;
    }

    public void print_settings(){
        System.out.println("---SETTINGS---");
        System.out.println("1. Data_style: " + data_styles[standart_data_style-1]);
        System.out.println("2. Exit");
        System.out.println("");
        System.out.println("If you want to change them. Write a number of chosen setting and then write another value");
    }

    public void print_data_styles(){
        System.out.println("---DATA STYLES---");
        System.out.println("1. " + data_styles[0]);
        System.out.println("2. " + data_styles[1]);
    }
    private int get_value(int min, int max){
        int value = -1;

        boolean flag = false;
        boolean exception_flag = false;

        while(!flag){
            try{
                Scanner input = new Scanner(System.in);
                value = input.nextInt();
                exception_flag = true;
            }catch(InputMismatchException e){
                System.out.println("Wrog input!");
            }
            if(exception_flag && value >= min && value <= max) flag = true;
            if(value < min || value > max){
                System.out.println("Number should be between " + min +" and " + max);
            }
        }
        return value;
    }
    public void change_settings(){
        int value;

        do {
            print_settings();
            value = get_value(1,2);
            if(value == 1) {
                print_data_styles();
                standart_data_style = (byte)get_value(1,2);
            }
        }while(value!=2);
    }




}
