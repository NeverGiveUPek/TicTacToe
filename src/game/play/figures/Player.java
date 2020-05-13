package game.play.figures;
import game.play.GameBoard.Map;

public abstract class Player {
    protected char icon = 'P';
    protected int x = -1, y = -1;

    public int get_x() {
        return x;
    }

    public void set_x(int x) {
        this.x = x;
    }

    public int get_y() {
        return y;
    }

    public void set_y(int y) {
        this.y = y;
    }

    public void set_icon(char icon) {
        this.icon = icon;
    }

    public char get_icon() {
        return icon;
    }

    protected boolean check_cords(int x, int y, int map_size){
        return x >= 0 && x < map_size && y >= 0 && y < map_size;
    }

    public abstract void do_move(Map map);
}
