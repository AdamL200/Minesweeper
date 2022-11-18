package oop.blueprints;

import java.util.Scanner;

public class Game { //game class that runs the main game loop

    boolean game = true;
    int check;
    Tile[][] grid;
    int length;
    boolean firstGuess=true;
    int width;
    public Game(Tile[][] grid){
        this.grid = grid;
        this.length = grid.length;
        this.width = grid[0].length;
    }

    public static void revealMines(Tile[][] grid){ //for revealing when you lose
        for (Tile[] row : grid) {
            for (Tile cell : row) {
                if (cell.bomb) {
                    cell.revealTile();
                }
            }
        }
    }


    public static int guess(Tile[][] grid, int row,int col) { //"guess" a cell which returns a cell state and reveals it if unrevealed
        int result;
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
    public static void revealChain(Tile[][] grid,int row,int col){ //recursively reveals cells and stops if there are mines nearby
        int result = guess(grid,row,col);
        if (result==1 || result == 2 || result==3) {
            return;
        } else if (result==4) {
            if (grid[row][col].nearby==0) { //if revealed and no mines adjacent
                try {
                    revealChain(grid,row-1,col);
                } catch (Exception ignored) {}
                try {
                    revealChain(grid,row+1,col);
                } catch (Exception ignored) {}
                try {
                    revealChain(grid,row,col-1);
                } catch (Exception ignored) {}
                try {
                    revealChain(grid,row,col+1);
                } catch (Exception ignored) {}
            }
        } else return;
    }

    public static int winCheck(Tile[][] grid){ //count unrevealed safe tiles in grid
        int count = 0;
        for (Tile[] row: grid) {
            for (Tile tile : row){
                if (!tile.bomb && !tile.revealed) {
                    count++;
                }
            }
        }
        System.out.println("You have "+count+" safe tiles left to reveal!");
        return count;
    }
    public static void printGrid(Tile[][] grid) {
        int len = grid[0].length;

        System.out.print("    "); //top row of x co-ords
        for(int i=0; i< len; i++) {
            if (i > 9){System.out.print(i+"  ");} //formatting depending on size of grid
            else System.out.print(" "+i+"  ");
        }
        System.out.println();
        for (int row=0; row< grid.length;row++ ) { //column of y co-ords
            if (row > 9) {
                System.out.print(row + " |"); //formatting
            } else System.out.print(row + "  |");
            for (int col = 0; col < grid[row].length; col++) { //prints the actual cells
                grid[row][col].printTile();

            }
            System.out.println();
        }

    }

    public void gameLoop(Scanner scan){
        while (this.game) { //while game is not won or lost
            System.out.println("Enter a cell to check: ");
            int row = scan.nextInt();
            int col = scan.nextInt();
            String flag = scan.nextLine();
            boolean outRange = (length <= row || row < 0 || col < 0 || col >= width); //check if cell is in grid
            if (outRange){
                while (outRange){
                    System.out.println("That coordinate is not in the grid\nplease enter a valid cell:");
                    row = scan.nextInt();
                    col = scan.nextInt();
                    flag = scan.nextLine();
                    outRange = (length <= row || row < 0 || col < 0 || col >= width);
                }
            }
            if (row == -1) { //for use if you want to quit the game early
                game = false;
                break;
            }
            if (this.firstGuess){ //if your first guess has a bomb remove it and continue
                if (this.grid[col][row].bomb){
                    System.out.println("There was a mine here but we removed it because first guess");
                    this.grid[col][row].removeBomb();

                }
                this.firstGuess=false;
            }
            //System.out.println("flag = "+flag);
            if (flag.equals(" F") || flag.equals(" f")) {
                if (this.grid[col][row].revealed){System.out.println("Cannot set flag on a revealed tile!");}
                else this.grid[col][row].setFlag(); //toggles flag if tile is unrevealed
            }
            else if (this.grid[col][row].bomb){
                System.out.println("You have chosen a mine\nGame over!");
                game=false; //end game
                revealMines(this.grid);
                printGrid(this.grid);

            } else if (this.grid[col][row].flag){
                System.out.println("Remove flag to select this cell");
            }
            else {
                revealChain(this.grid,col,row); //recursively reveal tiles
            }
            printGrid(this.grid);
            System.out.println();
            check = winCheck(this.grid); //check is number of unrevealed non-bomb tiles in the grid
            if (check==0){
                System.out.println("You have revealed all the safe tiles. You win!");
                game=false;
            }
        }
    }

}
