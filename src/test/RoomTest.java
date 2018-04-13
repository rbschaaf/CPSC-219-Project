import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RoomTest {

    @Test
    public void test_ConstructorAndGetter() {
        Room c = new Room(0);
        assertEquals("Testing constructor and getter", 0, c.getRoomsNumber());
    }

    @Test
    public void test_addTile_addingOne() {
        Room c = new Room(10);
        c.addTile(12,5);
        ArrayList<Tile> list = c.getTileList();
        Tile m2 = null;
        if (list.size() > 0){
            m2 = list.get(0);
        }
        assertEquals("Room only has one Tile - testing size.", 1, list.size());
        assertEquals("Room only has one Tile - testing x coord.", 12, m2.getXCoord());
        assertEquals("Room only has one Tile - testing y coord.", 5, m2.getYCoord());
    }

    @Test
    public void getTileList() {

    }

    @Test
    public void addTile() {
    }

    @Test
    public void setDoor() {
    }

    @Test
    public void getDoor() {
    }
}