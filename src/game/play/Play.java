package game.play;

import game.play.figures.*;
import game.play.GameBoard.*;
import game.play.gameSettings.GameSettings;
import game.settings.Menu;
import java.util.Scanner;


public class Play {
    private final Scanner input = new Scanner(System.in);

    private GameMap map;
    private Menu menu = new Menu((byte)0,input);
    private Player player1;
    private Player player2;
    private GameSettings lastGameSettings = new GameSettings(input);

    private char tmpIcon;



    public void setGame() {
       if(menu.getPlayState() == 0) {
           GameSettings gameSettings = new GameSettings(input);
           gameSettings.selectSettings();

           setBoard(gameSettings.getBoardSize());
           setGameMode(gameSettings.getNumberGameMode(),gameSettings.getBotLvl());
           setIcons(gameSettings.getPlayer1Icon());
           setStartingIcon(gameSettings.getNumberStartingIcon());

           lastGameSettings = gameSettings; // remember last game
       }
       else if(menu.getPlayState() == 1){
           map.clean();
           setStartingIcon(lastGameSettings.getNumberStartingIcon());
       }
    }

    private void setStartingIcon(int startingIcon){
        if(startingIcon==1){
            tmpIcon = 'X';
        }
        else if(startingIcon==2){
            tmpIcon = 'o';
        }
    }
    private void setGameMode(int game_mode, int botLvl){
        player1 = new PlayerHuman(menu.get_settings().getStandartDataStyle(), map.getMapSize(),input);
        if(game_mode==1) {
            player2 = new PlayerHuman(menu.get_settings().getStandartDataStyle(), map.getMapSize(),input);
            System.out.println("PvsP");
        }
        else if(game_mode==2) {

            player2 = new PlayerBot(botLvl);
            System.out.println("PvsB");
        }
    }
    public void setIcons(int player1Icon) {

        if(player1Icon == 1){
            player1.setIcon('X');
            player2.setIcon('o');
        }
        else if(player1Icon == 2) {
            player1.setIcon('o');
            player2.setIcon('X');
        }
    }
    public void setBoard(int boardSize) {
        int winning_length = 3;
        if(boardSize == 4) winning_length = 4;
        else if(boardSize > 4) winning_length = 5;
        map = new GameMap(boardSize,winning_length);
        map.clean();
    }
    public void changePlayer(){
        if(tmpIcon == 'X') tmpIcon = 'o';
        else if(tmpIcon == 'o') tmpIcon = 'X';
    }
    public void annouceGameEnding(int gameCondition){
        if(gameCondition == 0) System.out.println("It's TIE !");
        else if(gameCondition == 10) {
            System.out.println("'X' is a WINNER !");
        }
        else {
            System.out.println("'o' is a WINNER !");
        }
    }
    public void makeMove(Player player){
        player.doMove(map);
        map.setCellIcon(player.getX(),player.getY(),player.getIcon());
    }

    public void doGame(){
        while(true) {

            menu.runMenu();
            setGame();

            map.printMap();
            while (map.checkGameCondtion() == 2) {
                System.out.println("Teraz kolej: " + tmpIcon);
                if (tmpIcon == player1.getIcon()) {
                   makeMove(player1);
                } else if (tmpIcon == player2.getIcon()) {
                    makeMove(player2);
                }
                map.printMap();
                changePlayer();
            }
            annouceGameEnding(map.checkGameCondtion());
            menu.setPlayState((byte)1);
        }
    }
}
