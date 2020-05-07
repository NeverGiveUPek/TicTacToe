public class Map {
    private int size;
    private int winning_length;
    private char[][] board;

    Map(int size, int winning_length){
        this.size = size;
        this.winning_length = winning_length;
        board = new char[size][size];
    }


    public void print_map() {
        {
            System.out.println("WINNING LENGTH: " + winning_length);

            //drawing first row
            for(int j=0; j<size-1;j++){
                System.out.print(board[0][j] + "|");
            }
            System.out.println(board[0][size-1]);

            //drawing all middle rows
            for(int i=1; i<size-1;i++){
                for(int l=0;l<size-1;l++){
                    System.out.print("-+");
                }
                System.out.println("-");
                for(int k=0;k<size-1;k++){
                    System.out.print(board[i][k] + "|");
                }
                System.out.println(board[i][size-1]);
            }
            for(int l=0;l<size-1;l++){
                System.out.print("-+");
            }
            System.out.println("-");

            //drawing last row
            for (int j=0; j<size-1;j++){
                System.out.print(board[size-1][j] + "|");
            }
            System.out.println(board[size-1][size-1]);

        }
    }
    public void clean(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board[i][j] = ' ';
            }
        }
    }
    public boolean check_cell_empty(int x,int y){
        if(board[y][x]=='X') return false;
        else if(board[y][x]=='o')return false;
        return true;
    }
    public int get_map_size(){return size;}
    public void set_cell_icon(int x, int y, char icon) {
        board[y][x] = icon;
    }
    public char get_cell_icon(int x, int y) { return board[y][x]; }
    public boolean check_map_full(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]!='X'&&board[i][j]!='o') return false;
            }
        }
        return true;
    }
    public boolean check_horizontal(int x,int y, char given_icon){
        for(int i=0;i<winning_length;i++)
        {
            if(board[y][x+i]!=given_icon) return false;
        }
        return true;
    }
    public boolean check_vertical(int x, int y, char given_icon){
        for(int i=0;i<winning_length;i++)
        {
            if(board[y+i][x]!=given_icon) return false;
        }
        return true;
    }
    public boolean check_diagonally_right(int x, int y, char given_icon){
        for(int i=0;i<winning_length;i++)
        {
            if(board[y+i][x+i]!=given_icon) return false;
        }
        return true;
    }
    public boolean check_diagonally_left(int x, int y, char given_icon){
        for(int i=0;i<winning_length;i++)
        {
            if(board[y+i][x-i]!=given_icon) return false;
        }
        return true;
    }

    public boolean check_win(char icon){
        boolean flag = false;

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++){
                if(j<=size-winning_length) {
                    if(check_horizontal(j,i,icon)) flag = true;
                }
                if(i<=size-winning_length) {
                    if(check_vertical(j,i,icon)) flag = true;
                }
                if(j<=size-winning_length && i<=size-winning_length) {
                    if(check_diagonally_right(j,i,icon)) flag = true;
                }
                if(j>=winning_length-1 && i<=size-winning_length) {
                    if(check_diagonally_left(j,i,icon)) flag = true;
                }
            }
        }
        return flag;
    }
    public byte check_game_condtion(){
        if(check_win('X')) return 10;//X won
        if(check_win('o')) return -10;//o won
        if(check_map_full()) return 0; //tie
        return 2; //still playing
    }




}
