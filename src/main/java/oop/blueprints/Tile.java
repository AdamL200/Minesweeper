package oop.blueprints;

public class Tile { //tile class to be used in a grid
    int row;
    int col;
    boolean bomb = false;
    boolean flag = false;
    boolean revealed = false;
    int nearby; //number of mines in surrounding cells
    public Tile(int row,int column, int bomb) { //gives tile a coordinate and sets mine value
        this.row = row;
        this.col = column;
        if (bomb == 1) {
            this.bomb = true;
        }
    }
    public void printTile() { //prints individual tile
        if (this.revealed) {
                if (this.bomb) {
                    System.out.print(" ! |");
            }   else if (this.nearby > 0) {
                    System.out.print(" "+this.nearby+" |");
                } else System.out.print(" . |");


        } else if (this.flag){System.out.print(" ? |");
        } else System.out.print(" _ |");
    }
    public void revealTile() {
        this.revealed = true;
    }
    public void setTile(int bombs) {
        this.nearby = bombs;
    }
    public void setFlag() {
        this.flag = !this.flag;
    }
    public void removeBomb() { //only used if first move gives a mine
        this.bomb = false;
    }
}
