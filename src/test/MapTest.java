
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import resources.BuiltFloorPlans;

/** 
* Testing class of JUnit tests for the Map of the room finding program.
*/

public class MapTest {

  /**
  * Method that tests setting and getting the current floor plan.
  *
  */
  @Test
  public void testCurrentPlan() {
    // Make a new floor plan.
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", BuiltFloorPlans.TFDLONE , 1, 125, 170);
    // Make a new map and set the floor plan as its current floor plan.
    Map m = new Map();
    m.setCurrentFloorPlan(a);
    // Check if the grids of each floor plan are the same.
    assertTrue("The grids are equal", Arrays.deepEquals(a.getGrid(),m.getCurrentFloorPlan().getGrid()));
    // Check if the floor numbers of each floor plan are the same.
    assertEquals("The floor number is", 1,m.getCurrentFloorPlan().getFlNum());
    // Check if the stairs and elevators numbers are the same.
    assertEquals("The stairs number is",125,m.getCurrentFloorPlan().getStairsNum());
    assertEquals("The elevator number is",170,m.getCurrentFloorPlan().getElevatorNum());
    // Check if both floor plans have the same number of rooms.
    assertEquals("The number of rooms is",8,m.getCurrentFloorPlan().getRoomList().size());
  }


  /**
  * Method that tests the start values for the map.
  * Note: negative values do not need to be tested for,
  * as the inputted number will be either 0 or positive.
  */
  @Test
  public void testStartValues() {
    Map m = new Map();
    m.setStartValues(100);
    assertEquals("The start value is",100,m.getStart());
  }

  /**
  * Method that tests the end values for the map.
  * Note: negative values do not need to be tested for,
  * as the inputted number will be either 0 or positive.
  */
  @Test
  public void testEndValues() {
    Map m = new Map();
    m.setEndValues(50);
    assertEquals("The end value is",50,m.getDest());
  }

  /**
  * Method that tests the getter and setter methods for the
  * current building.
  */
  @Test
  public void testCurrentBuilding() {
    // Create a new map and building and set the new building as the current.
    Map m = new Map();
    Building b = new Building("Bioscience");
    m.setCurrentBuilding(b);
    // Check if the building retrieved is the same as the building set.
    assertEquals("The current building name is", "Bioscience", m.getCurrentBuilding().getName());
  }

  /**
  * Method that tests the non-default constructor
  * of Map to make sure it sets the current floor plan.
  */
  @Test
  public void testConstructor(){
    // Create a new floor plan and enter it as a parameter into the Map constructor.
    FloorPlans a = new FloorPlans("Bioscience",BuiltFloorPlans.BIOSCITWO,2,287,249);
    Map m = new Map(a);

    // Make sure the floor plan is stored via the constructor.
    assertNotNull("Floor plan was set",m.getCurrentFloorPlan());

    // Check if the grids of the floorplan set are equal to those from the getter.
    assertTrue("The grids are equal", Arrays.deepEquals(a.getGrid(),m.getCurrentFloorPlan().getGrid()));
  }

}
