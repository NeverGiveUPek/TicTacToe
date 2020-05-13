package game.play;

import game.play.figures.*;
import game.play.GameBoard.*;
import game.settings.Menu;


public class Play {
    private Map board;
    private Menu menu = new Menu((byte)0);
    private Player player1;
    private Player player2;

    private char tmp_icon;


    public void set_game() {
       if(menu.get_play_state() == 0) {
           GameSettings menu = new GameSettings();
           menu.select_settings();

           set_board(menu.get_board_size());
           set_game_mode(menu.get_number_game_mode(),menu.get_bot_lvl());
           set_icons(menu.get_player1_icon());
           set_starting_icon(menu.get_number_starting_icon());

       }
       else if(menu.get_play_state() == 1){
           board.clean();
           set_starting_icon(menu.get_settings().get_last_starting_icon());
       }
    }

    private void set_starting_icon(int starting_icon){
        if(starting_icon==1){
            tmp_icon = 'X';
        }
        else if(starting_icon==2){
            tmp_icon = 'o';
        }
    }
    private void set_game_mode(int game_mode, int bot_lvl){
        player1 = new PlayerHuman(menu.get_settings().get_standart_data_style(),board.get_map_size());
        if(game_mode==1) {
            player2 = new PlayerHuman(menu.get_settings().get_standart_data_style(),board.get_map_size());
            System.out.println("PvsP");
        }
        else if(game_mode==2) {

            player2 = new Bot(bot_lvl);
            System.out.println("PvsB");
        }
    }
    public void set_icons(int player1_icon) {

        if(player1_icon == 1){
            player1.set_icon('X');
            player2.set_icon('o');
        }
        else if(player1_icon == 2) {
            player1.set_icon('o');
            player2.set_icon('X');
        }
    }
    public void set_board(int board_size) {
        int winning_length = 3;
        if(board_size == 4) winning_length = 4;
        else if(board_size > 4) winning_length = 5;
        board = new Map(board_size,winning_length);
        board.clean();
    }
    public void change_player(){
        if(tmp_icon == 'X') tmp_icon = 'o';
        else if(tmp_icon == 'o') tmp_icon = 'X';
    }
    public void make_move(Player player){
        player.do_move(board);
        if(board.check_cell_empty(player.get_x(),player.get_y())){
            System.out.println(player.get_x() + " " + player.get_y());
            board.set_cell_icon(player.get_x(),player.get_y(),player.get_icon());
        }
        else{
            board.print_map();
            System.out.println("You wrote wrong cords, do it again");
            make_move(player);
        }
    }
    public void annouce_game_ending(int game_condition){
        if(game_condition == 0) System.out.println("It's TIE !");
        else if(game_condition == 10) {
            System.out.println("'X' is a WINNER !");
        }
        else {
            System.out.println("'o' is a WINNER !");
        }
    }

    public void do_game(){
        while(true) {

            menu.run_menu();
            set_game();

            board.print_map();
            while (board.check_game_condtion() == 2) {
                System.out.println("Teraz kolej: " + tmp_icon);
                if (tmp_icon == player1.get_icon()) {
                    make_move(player1);
                } else if (tmp_icon == player2.get_icon()) {
                    make_move(player2);
                }
                board.print_map();
                change_player();
            }
            annouce_game_ending(board.check_game_condtion());
            menu.set_play_state((byte)1);
        }
    }


}
