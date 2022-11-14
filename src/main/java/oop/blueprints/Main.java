package oop.blueprints;/*
This is going to be a minesweeper game
notes:
1. hard code a 5x5 grid with 1 bomb and get that working ✓
    a. Coordinate system
    b. Grid object that remembers where the bombs are ✓
    c. Way to check the tiles around a tile (in bounds and if bomb or not)
    d. Way to print and update the grid with guesses
2. Upgrade to nxn grid with 1 bomb
3. Add in a random and/or user input number of bombs
4.
*/
//Thinking that a 2d array of 1s and 0s

public class Main {
    public static void printGrid(Tile[][] grid) {
        for (int row=0; row< grid.length;row++ ) {

            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col].getTile();

            }
            System.out.println();
        }

    }

    public static void updateMines(Tile[][] grid,int row,int col) { //takes a single tile and checks for mines around it
        Tile tile = grid[row][col];
        int nearby = 0;
        for (int i=row-1;i<=row+1;i++) {
            for (int j=row+1;j<=row+1;j++) {
                try {
                    if (grid[i][j] != null) {
                        if (grid[i][j].bomb == true) {
                            nearby++;
                        }
                    }
                } catch(Exception e) {
                    continue;
                }
            }
            tile.setTile(nearby);
        }
    }

    public static int guess(Tile[][] grid, int row,int col) {
        int result = -1;
        Tile tile = grid[row][col];
        if (tile.revealed) {
            System.out.println("This tile has already been revealed");
            result = 1;
        }
        else if (tile.bomb) {
         result = 2;
        }
        else {
            result = 1;
        }
        tile.revealTile();
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = { {0,0,0,0,0}
                ,{0,0,0,0,0},
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}};
        Tile[][] tileGrid= { {null,null,null,null,null}, //initialise a grid full of nulls ready for tile objects
                {null,null,null,null,null},
                {null,null,null,null,null},
                {null,null,null,null,null},
                {null,null,null,null,null}};
        //System.out.println(grid[2][2]); //get specific cell
        for (int row=0; row<5;row++ ) {

            for (int col=0; col<5;col++) {
                //System.out.print(grid[row][col]); //print every cell individually but with a new line after every row
                tileGrid[row][col] = new Tile(row,col,grid[row][col]);
                //tileGrid[row][col].getTile();
            }
            //System.out.println();
        }
        printGrid(tileGrid);
        System.out.println();
        int result = guess(tileGrid,1,1);
        updateMines(tileGrid,1,1);
        printGrid(tileGrid);
        System.out.println();
        result = guess(tileGrid,2,2);
        updateMines(tileGrid,2,2);
        printGrid(tileGrid);

    }
}