package game.play.Icon;

public class Icon {


    private char character;

    public Icon(char character) {
        this.character = character;
    }
    public Icon(){}

    public char getCharacter() {
        return character;
    }
    public void setCharacter(char character) {
        this.character = character;
    }
    public char anotherIcon() {
        if (character == 'X') return 'o';
        else return 'X';
    }
    public void reverseCharacter(){
        character = anotherIcon();
    }
}
