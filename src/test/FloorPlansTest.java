import static org.junit.Assert.*;
import static resources.BuiltFloorPlans.*;

import org.junit.Test;
import resources.BuiltFloorPlans;
import java.util.Arrays;

/**
 * Class to test the FloorPlans class
 */
public class FloorPlansTest {
  /**
   * Test the constructors and ensure instances are properly set
   */
  @Test
  public void testConstructors() {
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", TFDLONE , 1, 125, 170);
    assertEquals("Destination number is", 1, a.getFlNum());
    assertEquals("Building is", "Taylor Family Digital Library", a.getBuildingName());
    assertEquals("Stairs number is", 125, a.getStairsNum());
    assertEquals("Elevator number is", 170, a.getElevatorNum());

    FloorPlans b = new FloorPlans(a);
    assertEquals("Destination number is", 1, b.getFlNum());
    assertEquals("Building is", "Taylor Family Digital Library", b.getBuildingName());
    assertEquals("Stairs number is", 125, b.getStairsNum());
    assertEquals("Elevator number is", 170, b.getElevatorNum());

  }


  /**
  * Method to test the length of the room list after adding rooms.
  *
  */
  @Test
  public void testRoomLists() {
    // Create a new floor plan.
    FloorPlans a = new FloorPlans();
    // Add rooms.
    a.addRoom(1);
    a.addRoom(2);
    a.addRoom(3);

    assertEquals("Room list contatins", 3, a.getRoomList().size());
  }

  /**
  * Method to test the grid being set.
  *
  */
  @Test
  public void testGrid() {
    int[][] g1 = {{1,1,1},{1,0,10}};
    int[][] g2 = {{0,1,5},{0,5,6}};

    // Create a new floor plan and set its grid to g1.
    FloorPlans a = new FloorPlans();
    a.setGrid(g1);
    assertNotNull("The grid exists", a.getGrid());//http://www.javacodex.com/JUnit/Test-Object-Is-Not-Null
    // Create a second new floor plan and set its grid to g2.
    FloorPlans b = new FloorPlans();
    b.setGrid(g2);
    assertNotNull("The grid exists", b.getGrid());
  }


  /**
  * Method to test making rooms.
  *
  */
  @Test
  public void testRooms() {
    // Make a new floor plan and give it rooms.
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLONE , 1, 125, 170);
    // Check the number of rooms on the floor plan.
    assertEquals("The number of rooms on the 1st floor of Taylor Digital Library is",
      8, a.getRoomList().size());
    // Check if a room was added to the room list.
    assertNotNull("Room 151 exists", a.getRoom(151 + 1000));

    // Make another new floor plan and give it rooms.
    FloorPlans b = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLTWO, 2, 250, 261);
    // Check the number of rooms on the floor plan.
    assertEquals("The number of rooms on the 1st floor of Taylor Digital Library is",
      11, b.getRoomList().size());
    // Check if a room was added to the room list.
    assertNotNull("Room 260 exists", b.getRoom(260 + 1000));

  }

  /**
  * Method to test getting the floor number for a floor plan.
  *
  */
  @Test
  public void testFlNum() {
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLTHREE, 3, 350, 361);
    assertEquals("The floor number is", 3, a.getFlNum());
  }

  /**
  * Method to test getting the elevator number.
  *
  */
  @Test
  public void testElevatorNum() {
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCIONE, 1, 190,177);
    assertEquals("The elevator number is", 177, a.getElevatorNum());
  }

  /**
  * Method to test getting the stairs number.
  *
  */
  @Test
  public void testStairsNum() {
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCITWO, 2, 291,284);
    assertEquals("The stairs number is", 291, a.getStairsNum());
  }

  /**
  * Method to test creating a copy Grid
  *
  */
  @Test
  public void testCopyGrid() {
    /* Create a floor plan object and get it's grid. Compare this to the copy of
    that same grid */
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCITWO, 2, 291,284);
    assertTrue("The grids are equal", Arrays.deepEquals(a.getGrid(),a.copyGrid(a.getGrid())));
  }

  @Test
  public void testRowLength() {
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLONE, 1,150,153);
    assertEquals("The row length is",14,a.getRowLength());

  }

  /**
  * Method to test the column length getter.
  *
  */
  @Test
  public void testColLength() {
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCITWO, 2, 291,284);
    assertEquals("The column length is",15,a.getColLength(15)); // 15 is the row length of g
  }

  /**
  * Method to test the building name getter method.
  *
  */
  @Test
  public void testBuildingName() {
    FloorPlans a = new FloorPlans("Taylor Family Digital Library",BuiltFloorPlans.TFDLFIVE,5,543,553);
    assertEquals("The building's name is","Taylor Family Digital Library",a.getBuildingName());

    FloorPlans b = new FloorPlans("Bioscience",BuiltFloorPlans.BIOSCITWO,2,295,213);
    assertEquals("The building's name is","Bioscience",b.getBuildingName());
  }

  /**
  * Method to test the grid return from getGrid()
  * is the same as the grid of the floor plan.
  */
  @Test
  public void testGrid2() {
    // Create a new floor plan
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLONE,1,150,151);
    /*Check if the grid returned from getGrid() is the same as the one the floor plans
    is created with */
    assertTrue("The grids are equal", Arrays.deepEquals(a.getGrid(),BuiltFloorPlans.TFDLONE));
  }

  /**
  * Method to test that the rooms are given the correct number
  * of tiles and that these tiles are valid.
  */
  @Test
  public void testPopulateRooms() {
    int[][] g = {{1001,1,1001,1002,2,1002},{1300,300,500,1500,1500,1500}};
    FloorPlans a = new FloorPlans();
    a.setGrid(g);
    a.makeRooms();
    a.populateRooms();
    // Check that the correct rooms were created.
    assertNotNull("Room 1 exists",a.getRoom(1+1000));
    assertNotNull("Room 2 exists",a.getRoom(2+1000));
    assertNotNull("Room 300 exists",a.getRoom(300+1000));
    assertNotNull("Room 500 exists", a.getRoom(500+1000));

    // Check for the correct amount of tiles for each room.
    assertEquals("Tiles for Room 1",2,a.getRoom(1+1000).getTileList().size());
    assertEquals("Tiles for Room 2",2,a.getRoom(2+1000).getTileList().size());
    assertEquals("Tiles for Room 300",1,a.getRoom(300+1000).getTileList().size());
    assertEquals("Tiles for Room 500",3,a.getRoom(500+1000).getTileList().size());

    // Check that the tiles have the proper x and y values for two rooms.
    assertEquals("X for room 1, tile 1", 0,a.getRoom(1+1000).getTileList().get(0).getXCoord());
    assertEquals("Y for room 1, tile 1",0, a.getRoom(1+1000).getTileList().get(0).getYCoord());
    assertEquals("X for room 500, tile 3",1,a.getRoom(500+1000).getTileList().get(2).getXCoord());
    assertEquals("Y for room 500, tile 3",5,a.getRoom(500+1000).getTileList().get(2).getYCoord());
  }

  /**
  * Method to test that the correct number of rooms are made
  * and that the rooms are valid.
  *
  */
  @Test
  public void testMakeRooms() {
    int[][] g = {{1001,999,8},{1375,40,1700}};
    // Make a new floor plan and set its grid to g. Make rooms based on g.
    FloorPlans a = new FloorPlans();
    a.setGrid(g);
    a.makeRooms();

    // Check if the room list is the appropriate size.
    assertEquals("Number of rooms made",3,a.getRoomList().size());

    // Check if each room added exists.
    assertNotNull("Room 1 exists",a.getRoom(1+1000));
    assertNotNull("Room 375 exists",a.getRoom(375+1000));
    assertNotNull("Room 700 exists",a.getRoom(700+1000));

  }

}

