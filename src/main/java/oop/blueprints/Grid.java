package oop.blueprints;

public class Grid {  //class that makes a grid of tiles

    int length;
    int width;
    int numMines;

    int[][] intGrid;

    Tile[][] tileGrid;

    public Grid(int length,int width, int difficulty){
        this.length = length;
        this.width = width;
        if (difficulty==5){this.numMines = (int) (50*length*width)/100;}
        else if (difficulty==2){this.numMines = (int) (25*length*width)/100;}
        else if (difficulty==3){this.numMines = (int) (35*length*width)/100;}
        else if (difficulty==4){this.numMines = (int) (40*length*width)/100;}
        else {this.numMines = (int) (15*length*width)/100;}

        System.out.println("Mines to avoid: " + numMines);
        this.intGrid = new int[length][width];
        this.tileGrid = new Tile[length][width];
        for (int[] row : intGrid) {
            for (int col : row) {
                col = 0;

            }
        }
        for (int count=0;count<numMines; count++) { // keeps looping until enough random mines have been placed avoids duplicate mine placement
            int rand1 = (int) (Math.random()*length);
            int rand2 = (int) (Math.random()*width);
            //System.out.println(rand1);
            //System.out.println(rand2);
            if (this.intGrid[rand1][rand2] == 1) {
                while (this.intGrid[rand1][rand2] == 1) {
                    rand1 = (int) (Math.random()*length);
                    rand2 = (int) (Math.random()*width);
                }

            }
            this.intGrid[rand1][rand2] = 1;
        }

        for (int row=0; row<length;row++ ) { //nested loop that initialises a tile grid from an int grid
            //System.out.println();
            for (int col=0; col<width;col++) {
                //System.out.print(grid[row][col]); //print every cell individually but with a new line after every row
                this.tileGrid[row][col] = new Tile(row,col,this.intGrid[row][col]);
                //tileGrid[row][col].getTile();
                //System.out.print(tileGrid[row][col].bomb);
            }
            //System.out.println();

        }
        for(int i=0;i<length;i++){
            for(int j=0;j<width;j++) {
                updateMines(this.tileGrid,i,j); //gives every tile the number of mines around the cell
            }
        }

    }
    public static void updateMines(Tile[][] grid,int row,int col) { //takes a single tile and checks for mines around it
        Tile tile = grid[row][col];
        int nearby = 0;
        try {
            nearby += grid[row-1][col-1].bomb ? 1 : 0; //if there is a mine add 1 to current neighbors
        }
        catch (Exception ignored) {}
        try {
            nearby += grid[row-1][col].bomb ? 1 : 0;
        } catch (Exception ignored) {}
        try {
            nearby += grid[row-1][col+1].bomb ? 1 : 0;
        } catch (Exception ignored) {}
        try {
            nearby += grid[row][col-1].bomb ? 1 : 0;
        } catch (Exception ignored) {}
        try {
            nearby += grid[row][col+1].bomb ? 1 : 0;
        } catch (Exception ignored) {}
        try {
            nearby += grid[row+1][col-1].bomb ? 1 : 0;
        } catch (Exception ignored) {}
        try {
            nearby += grid[row+1][col].bomb ? 1 : 0;
        } catch (Exception ignored) {}
        try {
            nearby += grid[row+1][col+1].bomb ? 1 : 0;
        } catch (Exception ignored) {}
        tile.setTile(nearby);
    }
    public int getMines(){
        return this.numMines;
    }
    public Tile[][] getTileGrid(){
        return this.tileGrid;
    }
}
