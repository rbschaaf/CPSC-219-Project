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
    public void getTileList_Encapsulation() {
        Room c = new Room(20);
        c.addTile(3, 7);
        c.addTile(0, 7);
        c.addTile(8, 9);
        c.addTile(15, 10);
        c.addTile(12, 9);
        c.addTile(20, 4);

        ArrayList<Tile> list = c.getTileList();

        list.get(0).setXCoord(1);
        list.get(1).setXCoord(1);
        list.get(2).setXCoord(1);
        list.get(3).setXCoord(1);
        list.get(4).setXCoord(1);
        list.get(5).setXCoord(1);

        list = c.getTileList();


        assertEquals("Tile 1 test - testing x coordinate", 3, list.get(0).getXCoord());
        assertEquals("Tile 2 test - testing x coordinate", 0, list.get(1).getXCoord());
        assertEquals("Tile 3 test - testing x coordinate", 8, list.get(2).getXCoord());
        assertEquals("Tile 4 test - testing x coordinate", 15, list.get(3).getXCoord());
        assertEquals("Tile 5 test - testing x coordinate", 12, list.get(4).getXCoord());
        assertEquals("Tile 6 test - testing x coordinate", 20, list.get(5).getXCoord());
    }

    @Test
    public void addTile() {
        Room c = new Room(20);
        c.addTile(3, 7);
        c.addTile(0, 7);
        c.addTile(8, 9);

        assertEquals("Check size of ArrayList", 3, c.getTileList().size());
    }

    @Test
    public void getAndSetDoor() {
        Room c = new Room(20);
        c.setDoor(40, 50);

        assertEquals("Check door x dimensions", 40, c.getDoor().getDoorX());
        assertEquals("Check door y dimensions", 50, c.getDoor().getDoorY());
    }


}