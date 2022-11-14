package oop.blueprints;

public class Tile {
    int row = -1;
    int col = -1;
    boolean bomb = false;
    boolean flag = false;
    boolean revealed = false;
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
            }
        } else {
            System.out.print("   |");
        }
    }
    public void revealTile() {
        this.revealed = true;
    }
}
