package game.play.figures;
import game.play.GameBoard.Map;

import java.util.LinkedList;
import java.util.Random;
import java.util.List;

public class Bot extends Player {
    private int[] move = new int[2];// {x , y}
    private byte best_score = -10;
    private int bot_lvl;


    public Bot(int bot_lvl){
        this.bot_lvl = bot_lvl;
    }

    @Override
    public void do_move(Map map) {
        if(bot_lvl == 1){ //worst bot
            get_random_move(map);
        }
        else if(bot_lvl == 2){ // average bot
            get_average_move(map);
        }
        else { // unbeatable bot
            get_best_move(map);
        }

        set_cords(move);
    }
    private void get_best_move(Map map){
        byte score;
        int map_size = map.get_map_size();
        first:
        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                if (map.check_cell_empty(j, i)) {
                    map.set_cell_icon(j, i, icon);
                    score = minimax(map, (byte) 0, false, false);
                    map.set_cell_icon(j, i, ' ');
                    if (score > best_score) {
                        move[0] = j;
                        move[1] = i;
                        best_score = score;
                        if (best_score == 10) {
                            break first;
                        }
                    }
                }
            }
        }
        best_score = -10;
    }

    private void get_random_move(Map map) {
        int map_size = map.get_map_size();
        List<Integer> possible_moves = new LinkedList<>();
        int counter=0;

        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                if (!map.check_map_full()) {
                    if (map.check_cell_empty(j, i)) {
                        possible_moves.add(i*10 + j);//y * 10 + x
                        counter++;

                    }
                }
            }
        }

        int rand_number;
        Random rand = new Random();
        rand_number = rand.nextInt(counter); //getting random move which won't end in next 2 rounds
        int score_move = possible_moves.get(rand_number);
        move[0] = score_move%10; // x
        move[1] = score_move/10; // y


    }

    private void get_average_move(Map map){

        byte counter = 0;
        List<Integer> possible_moves = new LinkedList<>();
        byte score;
        int map_size = map.get_map_size();

        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                if (map.check_cell_empty(j, i)) {
                    map.set_cell_icon(j, i, icon);
                    score = minimax(map, (byte) 0, false, true);
                    map.set_cell_icon(j, i, ' ');
                    if (score >= 5) {
                        counter++;
                        possible_moves.add(i*10 + j);//y * 10 + x

                    }
                }
            }
        }
        if(counter == 0) {
            get_random_move(map);
        }
        else {
            int rand_number;
            Random rand = new Random();
            rand_number = rand.nextInt(counter); //getting random move which won't end in next 2 rounds
            int score_move = possible_moves.get(rand_number);
            move[0] = score_move%10; // x
            move[1] = score_move/10; // y
        }
        best_score = -10;
    }

    protected byte minimax(Map map, byte depth, boolean isMaximazing, boolean isAverage) {  //isAverage needed to get_average_move

        byte result = map.check_game_condtion();
        if (result != 2) {
            return (byte) (adapt_game_condition(result) - depth);
        }
        if(depth == 2 && isAverage == true){ // return 5 when bot can't lose in next 2 moves
            return 5;
        }

        int map_size = map.get_map_size();

        byte number = get_isMaximazing_number(isMaximazing);

        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                if (map.check_cell_empty(j, i)) {
                    if (!isMaximazing) map.set_cell_icon(j, i, another_icon());
                    else map.set_cell_icon(j, i, icon);
                    byte score = minimax(map, (byte) (depth + 1), !isMaximazing, isAverage);
                    map.set_cell_icon(j, i, ' ');
                    if (!isMaximazing) {
                        if (score < number) {
                            number = score;
                        }
                    } else {
                        if (score > number) {
                            number = score;
                        }
                    }
                }
            }
        }
        return number;
    }

    private byte get_isMaximazing_number(boolean isMaximazing) {
        if (isMaximazing) return -20;
        return 20;
    }

    private void set_cords(int[] best_move) {
        x = best_move[0];
        y = best_move[1];
    }

    private byte adapt_game_condition(byte score) {
        if (icon == 'o') score = (byte) (-1 * score);
        return score;
    }

    private char another_icon() {
        if (icon == 'X') return 'o';
        else return 'X';
    }
}