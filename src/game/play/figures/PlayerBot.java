package game.play.figures;
import game.play.GameBoard.GameMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlayerBot extends Player {
    private int[] move = new int[2];// {x , y}
    private byte bestScore = -10;
    private BotLevel botLvl;


    public PlayerBot(BotLevel botLvl){
        this.botLvl = botLvl;
    }

    @Override
    public void doMove(GameMap map) {
        if(botLvl.equals(BotLevel.GOD)){ // unbeatable bot
            getBestMove(map);
        }
        else if(botLvl.equals(BotLevel.AVERAGE)){ // average bot
            getAverageMove(map);
        }
        else { //worst bot
            getRandomMove(map);
        }

        setCords(move);
    }
    private void getBestMove(GameMap map){
        byte score;
        int mapSize = map.getMapSize();
        first:
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map.checkCellEmpty(j, i)) {
                    map.setCellIcon(j, i, icon.getCharacter());
                    score = minimax(map, (byte) 0, false, false);
                    map.setCellIcon(j, i, ' ');
                    if (score > bestScore) {
                        move[0] = j;
                        move[1] = i;
                        bestScore = score;
                        if (bestScore == 10) {
                            break first;
                        }
                    }
                }
            }
        }
        bestScore = -10;
    }

    private void getRandomMove(GameMap map) {
        int mapSize = map.getMapSize();
        List<Integer> possibleMoves = new LinkedList<>();
        int counter=0;

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (!map.checkMapFull()) {
                    if (map.checkCellEmpty(j, i)) {
                        possibleMoves.add(i*10 + j);//y * 10 + x
                        counter++;

                    }
                }
            }
        }

        int randNumber;
        Random rand = new Random();
        randNumber = rand.nextInt(counter); //getting random move which won't end in next 2 rounds
        int scoreMove = possibleMoves.get(randNumber);
        move[0] = scoreMove%10; // x
        move[1] = scoreMove/10; // y


    }

    private void getAverageMove(GameMap map){

        byte counter = 0;
        List<Integer> possibleMoves = new LinkedList<>();
        byte score;
        int mapSize = map.getMapSize();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map.checkCellEmpty(j, i)) {
                    map.setCellIcon(j, i, icon.getCharacter());
                    score = minimax(map, (byte) 0, false, true);
                    map.setCellIcon(j, i, ' ');
                    if (score >= 5) {
                        counter++;
                        possibleMoves.add(i*10 + j);//y * 10 + x

                    }
                }
            }
        }
        if(counter == 0) {
            getRandomMove(map);
        }
        else {
            int randNumber;
            Random rand = new Random();
            randNumber = rand.nextInt(counter); //getting random move which won't end in next 2 rounds
            int scoreMove = possibleMoves.get(randNumber);
            move[0] = scoreMove%10; // x
            move[1] = scoreMove/10; // y
        }
        bestScore = -10;
    }

    protected byte minimax(GameMap map, byte depth, boolean isMaximazing, boolean isAverage) {  //isAverage needed to get_average_move

        byte result = map.checkGameCondtion();
        if (result != 2) {
            return (byte) (adaptGameCondition(result) - depth);
        }
        if(depth == 2 && isAverage == true){ // return 5 when bot can't lose in next 2 moves
            return 5;
        }

        int mapSize = map.getMapSize();

        byte number = getIsMaximazingNumber(isMaximazing);

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map.checkCellEmpty(j, i)) {
                    if (!isMaximazing) map.setCellIcon(j, i,icon.anotherIcon());
                    else map.setCellIcon(j, i, icon.getCharacter());
                    byte score = minimax(map, (byte) (depth + 1), !isMaximazing, isAverage);
                    map.setCellIcon(j, i, ' ');
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

    private byte getIsMaximazingNumber(boolean isMaximazing) {
        if (isMaximazing) return -20;
        return 20;
    }

    private void setCords(int[] bestMove) {
        x = bestMove[0];
        y = bestMove[1];
    }

    private byte adaptGameCondition(byte score) {
        if (icon.getCharacter() == 'o') score = (byte) (-1 * score);
        return score;
    }
}