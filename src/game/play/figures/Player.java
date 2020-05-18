package game.play.figures;
import game.play.GameBoard.GameMap;

public abstract class Player {
    protected char icon = 'P';
    protected int x = -1, y = -1;

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public void setIcon(char icon) {
        this.icon = icon;
    }

    public char getIcon() {
        return icon;
    }


    public abstract void doMove(GameMap map);
}
