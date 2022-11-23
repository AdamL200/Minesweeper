import oop.blueprints.Game;
import oop.blueprints.Grid;
import oop.blueprints.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void testConstructor(){
        Tile tile1 = new Tile(0,0,1);
        Assertions.assertEquals(true,tile1.getMine(), "Mine is missing");
        Assertions.assertEquals(false,tile1.getRevealed(), "tile revealed");
    }
    @Test
    public void testGrid(){

        Grid grid1 = new Grid(10,10,1);
        Tile[][] tileGrid = grid1.getTileGrid();
        int mines = 0;
        for(int i=0;i<tileGrid.length;i++){
            for(int j=0; j<tileGrid[0].length;j++){
                if(tileGrid[i][j].getMine()==true){mines++;}
                //Assertions.assertInstanceOf(Tile.class,tileGrid[i][j],i+" " +j+"is not of type Tile");
            }
        }
        Assertions.assertEquals(mines, grid1.getMines(),"Number of mines is wrong");

    }
}
