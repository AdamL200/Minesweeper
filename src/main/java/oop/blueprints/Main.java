package oop.blueprints;
import java.util.Scanner;

//minesweeper game

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //scans for input
        System.out.println("Enter a grid size: ");
        int length = scan.nextInt();
        int width = scan.nextInt();
        boolean invalidSize = width > 30 || width <= 4 || length > 30 || length <=4; //true if size is bad
        if (invalidSize) {
            while(invalidSize){ //keep getting input until valid size
                System.out.println("Invalid size please enter sizes from 5-30");
                length = scan.nextInt();
                width = scan.nextInt();
                invalidSize = width > 30 || width <= 4 || length > 30 || length <=4;
            }
        }

        Grid grid = new Grid(length, width); //initialises a grid of tiles
        Tile[][] tileGrid = grid.getTileGrid(); //returns the tile grid

        Game game = new Game(tileGrid); //initialises a game class
        game.gameLoop(scan); //runs the game loop

    }
}

