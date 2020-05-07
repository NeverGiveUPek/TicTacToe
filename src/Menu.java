import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Settings settings;
    private byte play_state = 0; //before first game or after {0;1}

    public Menu(Settings settings,byte play_state){
        this.settings = settings;
        this.play_state = play_state;
    }

    public byte get_play_state() {
        return play_state;
    }

    private void print_menu(){

        System.out.println("---MENU---");
        System.out.println("1. New Game");
        System.out.println("2. Settings");
        System.out.println("3. Scoreboard");//not working yet
        System.out.println("4. Exit");
    }
    private void print_end_menu(){  //after game lose
        System.out.println("---MENU---");
        System.out.println("1. Play Again");
        System.out.println("2. New Game");
        System.out.println("3. Settings");
        System.out.println("4. Scoreboard");//not working yet
        System.out.println("5. Exit");

    }
    public void run_menu(){
        if(play_state == 0) {
            print_menu();
        }
        else{
            print_end_menu();
        }
        boolean flag=true;
        boolean exception_flag=false;
        int value;



        do {
            do {
                try {
                    Scanner input = new Scanner(System.in);
                    value = input.nextInt() - play_state;
                    exception_flag = true;
                }catch(InputMismatchException e){
                    value = -1;
                }
                if (value < (1 - play_state)|| value > 4 || !exception_flag) {
                    System.out.println("You wrote wrong input. Do it again: ");
                }
            } while (value < (1 - play_state) || value > 4);
            if(value == 2) {  //enters game settings
                settings.change_settings();
            }
            else if(value == 3){ //enters scoarboard
                System.out.println("SCOREBOARD");
            }
            else if(value == 4){
                System.exit(1);
            }

            if(play_state == 0){
                if(value == 1) flag = false;
                else print_menu();
            }
            else if(play_state == 1){
                if(value == 0 || value == 1) {
                    flag = false;
                    if(value == 1) play_state = 0; //changing play_state to execute right set_game function
                }
                else print_end_menu();
            }

        }while(flag);//player must select game or exit
    }
}
