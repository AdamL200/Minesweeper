package oop.blueprints;

public class Tile {
    int row;
    int col;
    boolean bomb = false;
    boolean flag = false;
    boolean revealed = false;
    int nearby = 0;
    public Tile(int row,int column, int bomb) {
        this.row = row;
        this.col = column;
        if (bomb == 1) {
            this.bomb = true;
        }
    }
    public void getTile() {
        if (this.revealed) {
                if (this.bomb) {
                    System.out.print(" ! |");
            } else if (this.flag) {
                System.out.print(" ? |");
            } else if (this.nearby > 0) {
                    System.out.print(" "+this.nearby+" |");
                } else System.out.print(" . |");


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
}
