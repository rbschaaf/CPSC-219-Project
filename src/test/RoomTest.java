import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
* This test class is a JUnit test to test the Room Class of the Roomfinding Program.
*/

public class RoomTest {

    /**
    * Test case to test constructing a room and using the getter method to get the room number.
    */
    @Test
    public void test_ConstructorAndGetter() {
        Room c = new Room(0);
        assertEquals("Testing constructor and getter", 0, c.getRoomsNumber());
    }

    /**
    * Test case to test adding a tile to the list of tiles for a room, checking its size, and the x and y coordinates of the tile.
    */
    @Test
    public void test_addTile_addingOne() {
        // Create a new room and add a tile to it.
        Room c = new Room(10);
        c.addTile(12,5);
        // If the tile list size is larger than 0, get the Tile at index 0.
        ArrayList<Tile> list = c.getTileList();
        Tile m2 = null;
        if (list.size() > 0){
            m2 = list.get(0);
        }
        // Check if the list is of size 1 and if the x and y values are valid.
        assertEquals("Room only has one Tile - testing size.", 1, list.size());
        assertEquals("Room only has one Tile - testing x coord.", 12, m2.getXCoord());
        assertEquals("Room only has one Tile - testing y coord.", 5, m2.getYCoord());
    }

    /**
    * Test case adding a bunch of tiles to the tile list for a Room and checking each one was added and has the correct
    * x coordinates.
    */
    @Test
    public void getTileList_Encapsulation() {
        //Create a new room and add tiles to it.
        Room c = new Room(20);
        c.addTile(3, 7);
        c.addTile(0, 7);
        c.addTile(8, 9);
        c.addTile(15, 10);
        c.addTile(12, 9);
        c.addTile(20, 4);
        // Get the tile list from the room and set the x value for each tile to 1.
        ArrayList<Tile> list = c.getTileList();
    
        list.get(0).setXCoord(1);
        list.get(1).setXCoord(1);
        list.get(2).setXCoord(1);
        list.get(3).setXCoord(1);
        list.get(4).setXCoord(1);
        list.get(5).setXCoord(1);

        list = c.getTileList();

        // Check if the x coordinate values remain unchanged. If so, the list is properly encapsulated. 
        assertEquals("Tile 1 test - testing x coordinate", 3, list.get(0).getXCoord());
        assertEquals("Tile 2 test - testing x coordinate", 0, list.get(1).getXCoord());
        assertEquals("Tile 3 test - testing x coordinate", 8, list.get(2).getXCoord());
        assertEquals("Tile 4 test - testing x coordinate", 15, list.get(3).getXCoord());
        assertEquals("Tile 5 test - testing x coordinate", 12, list.get(4).getXCoord());
        assertEquals("Tile 6 test - testing x coordinate", 20, list.get(5).getXCoord());
    }

    /**
    * Testing adding tiles to a Room and checking they are all added successfully based on size of the tilelist.
    */
    @Test
    public void addTile() {
        // Create a new room and give it tiles.
        Room c = new Room(20);
        c.addTile(3, 7);
        c.addTile(0, 7);
        c.addTile(8, 9);
        // Check if the number of tiles added is equal to the size of the list.
        assertEquals("Check size of ArrayList", 3, c.getTileList().size());
    }

    /**
    * Testing a Room can be constructed and correctly sets and gets the x and y coordinate for a door for that room.
    */
    @Test
    public void getAndSetDoor() {
        // Create a new room and set its door.
        Room c = new Room(20);
        c.setDoor(40, 50);
        // Verify the x and y dimensions of the door are sucessfully set when accessing the door from the room.
        assertEquals("Check door x dimensions", 40, c.getDoor().getDoorX());
        assertEquals("Check door y dimensions", 50, c.getDoor().getDoorY());
    }


}
