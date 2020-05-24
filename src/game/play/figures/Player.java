package game.play.figures;
import game.play.GameBoard.GameMap;
import game.play.Icon.Icon;

public abstract class Player {
    protected Icon icon = new Icon();

    protected int x = -1, y = -1;

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }


    public abstract void doMove(GameMap map);
}
