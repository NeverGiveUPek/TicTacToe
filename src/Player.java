import java.util.InputMismatchException;


public abstract class Player {
    char icon = 'P';
    int x = -1, y = -1;

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
