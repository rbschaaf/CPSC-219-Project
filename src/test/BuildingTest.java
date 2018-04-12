import org.junit.Test;

import static org.junit.Assert.*;

public class BuildingTest {

    @Test
    public void getName() {
        Building a = new Building("Taylor Family Digital Library");
        assertEquals("This is not the correct name", "Taylor Family Digital Library", a.getName());
        Building b = new Building("Bioscience");
        assertEquals("This is not the correct name", "Bioscience", b.getName());
        Building c = new Building("incorrect");
        assertNull("This is not the correct name", c.getName());
    }

    @Test
    public void addFloors() {
        Building a = new Building("Taylor Family Digital Library");
        Building b = new Building("Taylor Family Digital Library");
        assertEquals("These do not create the same lists", b.getFloorList(), a.getFloorList());
        Building c = new Building("Bioscience");
        Building d = new Building("Bioscience");
        assertEquals("These do not create the same lists", d.getFloorList(), c.getFloorList());
    }

    @Test
    public void getFloorPlan() {
        Building a = new Building("Taylor Family Digital Library");
        Building b = new Building("Taylor Family Digital Library");
        assertEquals("These do not create the same lists", b.getFloorPlan(299) , a.getFloorPlan(200));
        Building c = new Building("Taylor Family Digital Library");
        Building d = new Building("Taylor Family Digital Library");
        assertNotEquals("These do not create the same lists", c.getFloorPlan(199) , d.getFloorPlan(200));
    }

    @Test
    public void getFloorList() {


    }

    @Test
    public void onAFloor() {

    }
}