package game.play;

import game.play.GameBoard.GameMap;
import game.play.Icon.Icon;
import game.play.figures.BotLevel;
import game.play.figures.Player;
import game.play.figures.PlayerBot;
import game.play.figures.PlayerHuman;
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

    private Icon tmpIcon = new Icon('X');



    public void setGame() {
       if(menu.getPlayState() == 0) {
           GameSettings gameSettings = new GameSettings(input);
           gameSettings.selectSettings();

           setBoard(gameSettings.getBoardSize());
           setGameMode(gameSettings.getNumberGameMode(),gameSettings.getBotLvl());
           setIcons(gameSettings.getPlayer1Icon());
           setStartingIcon(gameSettings.getStartingIcon());

           lastGameSettings = gameSettings; // remember last game
       }
       else if(menu.getPlayState() == 1){
           map.clean();
           setStartingIcon(lastGameSettings.getStartingIcon());
       }
    }

    private void setStartingIcon(Icon startingIcon){
       tmpIcon.setCharacter(startingIcon.getCharacter());
    }

    private void setGameMode(int game_mode, BotLevel botLevel){
        player1 = new PlayerHuman(menu.get_settings().getStandartDataStyle(), map.getMapSize(),input);
        if(game_mode==1) {
            player2 = new PlayerHuman(menu.get_settings().getStandartDataStyle(), map.getMapSize(),input);
            System.out.println("PvsP");
        }
        else if(game_mode==2) {

            player2 = new PlayerBot(botLevel);
            System.out.println("PvsB");
        }
    }
    public void setIcons(int player1Icon) {

        if(player1Icon == 1){
            player1.setIcon(new Icon('X'));
            player2.setIcon(new Icon('o'));
        }
        else if(player1Icon == 2) {
            player1.setIcon(new Icon('o'));
            player2.setIcon(new Icon('X'));
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
        tmpIcon.reverseCharacter();
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
        map.setCellIcon(player.getX(),player.getY(),player.getIcon().getCharacter());
    }

    public void doGame(){
        while(true) {

            menu.runMenu();
            setGame();

            map.printMap();
            while (map.checkGameCondtion() == 2) {
                System.out.println("It's a '" + tmpIcon.getCharacter() + "' turn" );
                if (tmpIcon.getCharacter() == player1.getIcon().getCharacter()) {
                    makeMove(player1);
                } else if (tmpIcon.getCharacter() == player2.getIcon().getCharacter()){
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
