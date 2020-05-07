import java.util.Scanner;
import java.util.InputMismatchException;

public class GameSettings {
    private int number_game_mode;
    private int number_starting_icon;
    private int player1_icon; //1. X   2. o
    private int board_size;
    private int bot_lvl=0;

    public int get_board_size(){
        return board_size;
    }
    public int get_player1_icon(){
        return player1_icon;
    }
    public int get_number_game_mode(){
        return number_game_mode;
    }
    public int get_number_starting_icon(){
        return number_starting_icon;
    }
    public int get_bot_lvl(){return bot_lvl;}

    private void print_game_mode(){
        System.out.println("Set your game mode");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs bot");
    }
    private void print_starting_icon() {
        System.out.println("Who starts?");
        System.out.println("1. X");
        System.out.println("2. o");
    }
    private void print_player1_icon(){
        System.out.println("Select icon for P1: ");
        System.out.println("1. 'X'");
        System.out.println("2. 'o'");
    }
    private void print_bot_lvl(){
        System.out.println("Choose bot difficulty: ");
        System.out.println("1. 'God' ");
        System.out.println("2. 'Average' ");
        System.out.println("3. 'Easy' ");
    }
    private void print_board_size(){
        System.out.println("Board is always a square");
        System.out.println("Enter a size of side <3-10> : ");
        System.out.println("Playing with bot is anable only with board size 3");
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


    public void select_settings() {
        print_board_size();
        board_size = get_value(3,10);
        if(board_size==3){
            print_game_mode();
            number_game_mode = get_value(1,2);
        }
        else{
            number_game_mode = 1;
        }
        if(number_game_mode == 2){
            print_bot_lvl();
            bot_lvl = get_value(1,3);
        }
        print_starting_icon();
        number_starting_icon = get_value(1,2);
        print_player1_icon();
        player1_icon = get_value(1,2);
    }
}
