import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;
import static resources.BuiltFloorPlans.TFDLONE;
import static resources.BuiltFloorPlans.TFDLSIX;
import static resources.Constants.*;
/**
* Test class of JUnit tests to test the Building class for the room finding app.
* The Building class has associated FloorPlans with it for each floor.
*/

public class BuildingTest {

    /**
    * Testing the name of a constructed building can be gotten.
    */
    @Test
    public void getName() {
        Building a = new Building("Taylor Family Digital Library");
        assertEquals("This is not the correct name", "Taylor Family Digital Library", a.getName());
        Building b = new Building("Bioscience");
        assertEquals("This is not the correct name", "Bioscience", b.getName());
        Building c = new Building("incorrect");
        assertNull("This is not the correct name", c.getName());
    }

    /**
    * Test that all the correct floors can be added for the building and this list of floors can be gotten.
    */
    @Test
    public void addFloorsAndGetFloorList() {
        Building a = new Building("Taylor Family Digital Library");
        Building b = new Building("Taylor Family Digital Library");
        assertEquals("These do not create the same lists", a.getFloorList().size(), b.getFloorList().size());
        Building c = new Building("Bioscience");
        Building d = new Building("Bioscience");
        assertEquals("These do not create the same lists", c.getFloorList().size(), d.getFloorList().size());
        assertFalse("Lists should not be the same size should not be the same size.", a.getFloorList().size() == c.getFloorList().size());
    }

    /**
    * Test that a specific Floor plan for a building can be gotten based on provided room numbers.
    */
    @Test
    public void getFloorPlan() {
        Building a = new Building("Taylor Family Digital Library");
        Building b = new Building("Taylor Family Digital Library");
        assertTrue("These do not create the same floorplans", Arrays.deepEquals(b.getFloorPlan(200).getGrid(), a.getFloorPlan(299).getGrid()));
        Building c = new Building("Taylor Family Digital Library");
        Building d = new Building("Taylor Family Digital Library");
        assertFalse("These should not create the same floorplans", Arrays.deepEquals(b.getFloorPlan(200).getGrid(), a.getFloorPlan(199).getGrid()));
    }

    /**
    * Test the room provided as a parameter actually exists on a floor in the building.
    */
    @Test
    public void onAFloor() {
        Building a = new Building("Taylor Family Digital Library");
        assertTrue("Should create the same floorplans", Arrays.deepEquals(TFDLONE,a.onAFloor(150).getGrid()));
    }
}
