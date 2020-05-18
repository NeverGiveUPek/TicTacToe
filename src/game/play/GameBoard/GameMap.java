package game.play.GameBoard;

public class GameMap {
    private int size;
    private int winningLength;
    private char[][] board;

    public GameMap(int size, int winningLength){
        this.size = size;
        this.winningLength = winningLength;
        board = new char[size][size];
    }


    public void printMap() {
        {
            System.out.println("WINNING LENGTH: " + winningLength);

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
    public boolean checkCellEmpty(int x, int y){
        if(board[y][x]=='X') return false;
        else if(board[y][x]=='o')return false;
        return true;
    }
    public boolean checkCords(int x, int y){
        return x >= 0 && x < size && y >= 0 && y < size;
    }
    public int getMapSize(){return size;}
    public void setCellIcon(int x, int y, char icon) {
        board[y][x] = icon;
    }
    public boolean checkMapFull(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]!='X'&&board[i][j]!='o') return false;
            }
        }
        return true;
    }
    public boolean checkHorizontal(int x, int y, char givenIcon){
        for(int i = 0; i< winningLength; i++)
        {
            if(board[y][x+i]!=givenIcon) return false;
        }
        return true;
    }
    public boolean checkVertical(int x, int y, char givenIcon){
        for(int i = 0; i< winningLength; i++)
        {
            if(board[y+i][x]!=givenIcon) return false;
        }
        return true;
    }
    public boolean checkDiagonallyRight(int x, int y, char givenIcon){
        for(int i = 0; i< winningLength; i++)
        {
            if(board[y+i][x+i]!=givenIcon) return false;
        }
        return true;
    }
    public boolean checkDiagonallyLeft(int x, int y, char givenIcon){
        for(int i = 0; i< winningLength; i++)
        {
            if(board[y+i][x-i]!=givenIcon) return false;
        }
        return true;
    }

    public boolean checkWin(char icon){
        boolean flag = false;

        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++){
                if(j<=size- winningLength) {
                    if(checkHorizontal(j,i,icon)) flag = true;
                }
                if(i<=size- winningLength) {
                    if(checkVertical(j,i,icon)) flag = true;
                }
                if(j<=size- winningLength && i<=size- winningLength) {
                    if(checkDiagonallyRight(j,i,icon)) flag = true;
                }
                if(j>= winningLength -1 && i<=size- winningLength) {
                    if(checkDiagonallyLeft(j,i,icon)) flag = true;
                }
            }
        }
        return flag;
    }
    public byte checkGameCondtion(){
        if(checkWin('X')) return 10;//X won
        if(checkWin('o')) return -10;//o won
        if(checkMapFull()) return 0; //tie
        return 2; //still playing
    }




}
