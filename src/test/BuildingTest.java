import org.junit.Test;

import static org.junit.Assert.*;

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
    public void addFloors() {
        Building a = new Building("Taylor Family Digital Library");
        Building b = new Building("Taylor Family Digital Library");
        assertEquals("These do not create the same lists", b.getFloorList(), a.getFloorList());
        Building c = new Building("Bioscience");
        Building d = new Building("Bioscience");
        assertEquals("These do not create the same lists", d.getFloorList(), c.getFloorList());
    }

    /**
    * Test that a specific Floor plan for a building can be gotten based on provided room numbers.
    */
    @Test
    public void getFloorPlan() {
        Building a = new Building("Taylor Family Digital Library");
        Building b = new Building("Taylor Family Digital Library");
        assertEquals("These do not create the same lists", b.getFloorPlan(299) , a.getFloorPlan(200));
        Building c = new Building("Taylor Family Digital Library");
        Building d = new Building("Taylor Family Digital Library");
        assertNotEquals("These do not create the same lists", c.getFloorPlan(199) , d.getFloorPlan(200));
    }

    /**
    * Test the getter method for the list of floors for a building.
    */
    @Test
    public void getFloorList() {


    }

    /**
    * Test the room provided as a parameter actually exists on a floor in the building.
    */
    @Test
    public void onAFloor() {

    }
}
