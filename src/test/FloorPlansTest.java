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
    a.makeRooms();
    // Check the number of rooms on the floor plan.
    assertEquals("The number of rooms on the 1st floor of Taylor Digital Library is",
      8, a.getRoomList().size());
    // Check if a room was added to the room list.
    assertNotNull("Room 151 exists", a.getRoom(151 + 1000));

    // Make another new floor plan and give it rooms.
    FloorPlans b = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLTWO, 2, 250, 261);
    b.makeRooms();
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
  public void getFlNum() {
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLTHREE, 3, 350, 361);
    assertEquals("The floor number is", 3, a.getFlNum());
  }

  /**
  * Method to test getting the elevator number.
  *
  */
  @Test
  public void getElevatorNum() {
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCIONE, 1, 190,177);
    assertEquals("The elevator number is", 177, a.getElevatorNum());
  }

  /**
  * Method to test getting the stairs number.
  *
  */
  @Test
  public void getStairsNum() {
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCITWO, 2, 291,284);
    assertEquals("The stairs number is", 291, a.getStairsNum());
  }

  @Test
  public void copyGrid() {
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCITWO, 2, 291,284);
    assertTrue("The grids are equal", Arrays.deepEquals(a.getGrid(),a.copyGrid(a.getGrid())));
  }

  @Test
  public void getRowLength() {
  }

  /**
  * Method to test the column length getter.
  *
  */
  @Test
  public void getColLength() {
    int[][] g = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    {0,0,0,1,1,1291,1291,1291,1210,1210,1222,1222,1228,0,0},
    {0,1213,1288,1,1,1291,1291,1291,1210,210,1222,1222,228,0,0},
    {0,213,288,1,1,1291,1291,1291,1210,1,1222,1222,1,0,0},
    {0,1,1,1,291,1291,1291,1291,1217,1,222,1222,1,0,0},
    {0,289,290,1,292,1292,1297,1297,1217,1,1227,1227,1,0,0},
    {0,1289,1290,1,1293,1293,1297,1297,217,1,1227,227,1,0,0},
    {0,1284,284,1,0,293,1297,297,1217,1,1227,1227,1,0,0},
    {0,1283,1283,1,1,1,1,1,1,1,1,1,1,1,0},
    {0,1283,283,1,1281,0,0,1,1211,1211,211,1211,263,1263,0},
    {0,1283,1283,1,281,888,287,1,286,1286,1211,1211,1263,1263,0},
    {0,1280,280,1,1281,0,1287,1,1211,1211,1211,1211,0,0,0},
    {0,1280,1280,1,1,1,1,1,1,1,1,1,1,1,0},
    {0,1295,1295,1,1273,273,1273,1249,249,0,251,1251,1243,243,0},
    {0,1295,295,1,1273,1273,1273,1249,1249,0,1251,1251,1243,1243,0},
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    FloorPlans a = new FloorPlans("Bioscience", BuiltFloorPlans.BIOSCITWO, 2, 291,284);
    a.setGrid(g);
    assertEquals("The column length is",15,a.getColLength(15)); // 15 is the row length of g
  }

  @Test
  public void getBuildingName() {
  }

  @Test
  public void getGrid() {
  }


  @Test
  public void populateRooms() {
  }

  @Test
  public void printSavedGrid() {
  }
}
