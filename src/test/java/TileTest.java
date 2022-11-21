import oop.blueprints.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {

    @Test
    public void testConstructor(){
        Tile tile1 = new Tile(0,0,1);
        Assertions.assertEquals(true,tile1.getMine());
        Assertions.assertEquals(false,tile1.getRevealed());
    }
}
