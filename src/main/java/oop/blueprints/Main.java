package oop.blueprints;
import java.util.Scanner;

/*

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



    public static void updateMines(Tile[][] grid,int row,int col) { //takes a single tile and checks for mines around it
        Tile tile = grid[row][col];
        int nearby = 0;
        try {
            nearby += grid[row-1][col-1].bomb ? 1 : 0;
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

    public static int guess(Tile[][] grid, int row,int col) {
        int result = -1;
        Tile tile = grid[row][col];
        if (tile.revealed) {
            //System.out.println("This tile has already been revealed");
            result = 1;
        }
        else if (tile.bomb) {
         result = 2;
        } else if (tile.flag) {
            result = 3;
            return result;
        } else {
            result = 4; //when not revealed
        }
        tile.revealTile();
        return result;
    }



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a grid size: ");
        int length = scan.nextInt();
        int width = scan.nextInt();
        boolean invalidSize = width > 30 || width <= 4 || length > 30 || length <=4;
        if (invalidSize) {
            while(invalidSize){
                System.out.println("Invalid size please enter sizes from 5-30");
                length = scan.nextInt();
                width = scan.nextInt();
                invalidSize = width > 30 || width <= 4 || length > 30 || length <=4;
            }
        }
        int numMines = (int) (15*length*width)/100;
        System.out.println(numMines);
        int[][] grid = new int[length][width];
        Tile[][] tileGrid = new Tile[length][width];
        for (int[] row : grid) {
            for (int col : row) {
                col = 0;

            }
        }
        for (int count=0;count<numMines; count++) { // keeps looping until enough random mines have been placed avoids duplicate mine placement
            int rand1 = (int) (Math.random()*length);
            int rand2 = (int) (Math.random()*width);
            //System.out.println(rand1);
            //System.out.println(rand2);
            if (grid[rand1][rand2] == 1) {
                while (grid[rand1][rand2] == 1) {
                    rand1 = (int) (Math.random()*length);
                    rand2 = (int) (Math.random()*width);
                }

            }
            grid[rand1][rand2] = 1;
        }

        /*int[][] grid1 = { {0,0,0,0,0}
                ,{0,1,1,1,0},
                {0,1,0,1,0},
                {0,1,1,1,0},
                {0,0,0,0,0}};
        Tile[][] tileGrid1= { {null,null,null,null,null}, //initialise a grid full of nulls ready for tile objects
                {null,null,null,null,null},
                {null,null,null,null,null},
                {null,null,null,null,null},
                {null,null,null,null,null}};

        */
        //System.out.println(grid[2][2]); //get specific cell
        for (int row=0; row<length;row++ ) { //nested loop that initialises a tilegrid from an intgrid
            //System.out.println();
            for (int col=0; col<width;col++) {
                //System.out.print(grid[row][col]); //print every cell individually but with a new line after every row
                tileGrid[row][col] = new Tile(row,col,grid[row][col]);
                //tileGrid[row][col].getTile();
                //System.out.print(tileGrid[row][col].bomb);
            }
            //System.out.println();

        }
        for(int i=0;i<length;i++){
            for(int j=0;j<width;j++) {
                updateMines(tileGrid,i,j);
            }
        }
        Game game = new Game(tileGrid);
        game.gameLoop(scan);

    }
}

