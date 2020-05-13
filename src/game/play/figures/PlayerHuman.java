package game.play.figures;

import game.play.GameBoard.Map;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerHuman  extends Player {
    private byte data_style;

    public PlayerHuman(byte data_style, int board_size){
        if(data_style == 2 && board_size > 3) this.data_style = 1;
        else this.data_style = data_style;
    }


    @Override
    public void do_move(Map map) {
        int map_size = map.get_map_size();
        get_cords(data_style);
        if (!check_cords(x,y,map_size)) {
            System.out.println("Your cords should be beetween 0-" + (map_size-1));
            map.print_map();
            do_move(map);
        } else if (map.check_cell_empty(x, y) == false) {
            System.out.println("This cell is already occupied");
            map.print_map();
            do_move(map);
        }
    }
    public byte get_data_style(){return data_style;}


    private void get_cords(byte data_style){
        if(data_style == 1) get_cords_in_seq();
        else if(data_style == 2) get_cords_as_board();
    }

    private void get_cords_in_seq(){

        boolean good = false;
        while(!good) {
            try {
                Scanner input = new Scanner(System.in);

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

    private void get_cords_as_board(){

        boolean good = false;
        while(!good) {
            try {
                Scanner input = new Scanner(System.in);
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
