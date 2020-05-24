package game.settings;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
    private Scanner input;
    private Settings settings = new Settings(input);
    private byte playState = 0; //before first game or after {0;1}



    public Menu(byte playState, Scanner input){
        this.input = input;
        this.playState = playState;
    }

    public byte getPlayState() {
        return playState;
    }

    public void setPlayState(byte play_state){
        this.playState = play_state;
    }
    public Settings get_settings(){
        return settings;
    }

    private void printMenu(){

        System.out.println("---MENU---");
        System.out.println("1. New Game");
        System.out.println("2. Settings");
        System.out.println("3. Scoreboard");//not working yet
        System.out.println("4. Exit");
    }
    private void printEndMenu(){  //after game lose
        System.out.println("---MENU---");
        System.out.println("1. Play Again");
        System.out.println("2. New Game");
        System.out.println("3. Settings");
        System.out.println("4. Scoreboard");//not working yet
        System.out.println("5. Exit");

    }
    public void runMenu(){
        if(playState == 0) {
            printMenu();
        }
        else{
            printEndMenu();
        }
        boolean flag=true;
        boolean exceptionFlag=false;
        int value;



        do {
            do {
                try {
                    value = input.nextInt() - playState;
                    exceptionFlag = true;
                }catch(InputMismatchException e){
                    input.nextLine();
                    value = -1;
                }
                if (value < (1 - playState)|| value > 4 || !exceptionFlag) {
                    System.out.println("You wrote wrong input. Do it again: ");
                }
            } while (value < (1 - playState) || value > 4);
            if(value == 2) {  //enters game settings
                settings.changeSettings();
            }
            else if(value == 3){ //enters scoarboard
                System.out.println("SCOREBOARD");
            }
            else if(value == 4){
                input.close();//close Scanner object
                System.exit(1);
            }

            if(playState == 0){
                if(value == 1) flag = false;
                else printMenu();
            }
            else if(playState == 1){
                if(value == 0 || value == 1) {
                    flag = false;
                    if(value == 1) playState = 0; //changing play_state to execute right set_game function
                }
                else printEndMenu();
            }

        }while(flag);//player must select game or exit
    }
}
