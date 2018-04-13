import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileTest {
    
    /**
    * Method to test the values set by the tile constructor.
    *
    */
    @Test
    public void test_Constructor() {
        /*Create a new Tile with the x-value at 0 and the y-value at 0. Use getter methods to see if 
        x-value and y-values returned from the object are the same as those inputted.*/
        Tile n = new Tile(0,0);
        assertEquals("Created Tile(0,0) - testing x value", 0, n.getXCoord());
        assertEquals("Created Tile(0,0) - testing y value", 0, n.getYCoord());
    }

    /**
    * Method to test when the constructor is given a negative value.
    *
    */
    @Test
    public void test_Constructor_Negative() {
        /* When given negative values, the constructor should set the variable the
        value was provided for to 0*/
        Tile n = new Tile(-1,1);
        assertEquals("Created Tile(-1,10) - testing x value", 0, n.getXCoord());
        assertEquals("Created Tile(-1,10) - testing y value", 1, n.getYCoord());
    }



    /*
    * Method to test if the copy constructor sets the new object with appropriate x and y values 
    * from the tile object it copies.
    */
    @Test
    public void test_CopyConstructor() {
        // Create a new tile n.
        Tile n = new Tile(1,2);
        // Copy the tile n using the copy constructor.
        Tile n2 = new Tile(n);
        assertEquals("Created Tile(1,2) and copied it.  Testing x value of original.", 1, n.getXCoord());
        assertEquals("Created Tile(1,2) and copied it.  Testing y value of original.", 2, n.getYCoord());
        assertEquals("Created Tile(1,2) and copied it.  Testing x value of copy.", 1, n2.getXCoord());
        assertEquals("Created Tile(1,2) and copied it.  Testing y value of copy.", 2, n2.getYCoord());
    }


    /*
    * Method to test when the x value setter is given 0 as a parameter.
    *
    */
    @Test
    public void test_setXCoord_Zero() {
        Tile n = new Tile(5,7);
        // Set the tile's x coord to 0.
        n.setXCoord(0);
        // The x value should be equal to 0 and the y value should remain the same.
        assertEquals("Created Tile(5,7) then set xcoord to zero - testing x value", 0, n.getXCoord());
        assertEquals("Created Tile(5,7) then set xcoord to zero - testing y value", 7, n.getYCoord());
    }

    /**
    * Method to test when the y value setter is given 0 as a parameter.
    *
    */
    @Test
    public void test_setYCoord_Zero() {
        Tile n = new Tile(5,7);
        // Set the tile's y coord to 0.
        n.setYCoord(0);
        // The x value should remain the same and the y value should be equal to 0.
        assertEquals("Created Tile(5,7) then set ycoord to zero - testing x value", 5, n.getXCoord());
        assertEquals("Created Tile(5,7) then set ycoord to zero - testing y value", 0, n.getYCoord());
    }

    /**
    * Method to test when the x value is set to a negative integer.
    *
    */
    @Test
    public void test_setXCoord_Negative() {
        Tile n = new Tile(5,7);
        n.setXCoord(-54);
        // The x value should be set to 0 when the setter is given a negative integer as a parameter.
        assertEquals("Created Tile(5,7) then set xcoord to -54 - testing x value", 0, n.getXCoord());
        assertEquals("Created Tile(5,7) then set xcoord to -54 - testing y value", 7, n.getYCoord());
    }

    /**
    * Method to test when the y value is set to a negative integer.
    *
    */
    @Test
    public void test_setYCoord_Negative() {
        Tile n = new Tile(5,7);
        n.setYCoord(-32);
        // The y value should be set to 0 when the setter is given a negative integer as a parameter.
        assertEquals("Created Tile(5,7) then set ycoord to -32 - testing x value", 5, n.getXCoord());
        assertEquals("Created Tile(5,7) then set ycoord to -32 - testing y value", 0, n.getYCoord());
    }

    /**
    * Method to test when the x value is given a positive integer as a parameter.
    *
    */
    @Test
    public void test_setXCoord_Positive() {
        Tile n = new Tile(5,7);
        n.setXCoord(1);
        // The x value should be changed to the given parameter, while the y value remains the same.
        assertEquals("Created Tile(5,7) then set xcoord to 1 - testing x value", 1, n.getXCoord());
        assertEquals("Created Tile(5,7) then set xcoord to 1 - testing y value", 7, n.getYCoord());
    }

    /**
    * Method to test when the y value is given a positive integer as a parameter.
    *
    */
    @Test
    public void test_setYCoord_Positive() {
        Tile n = new Tile(5,7);
        n.setYCoord(3);
         // The x value should be changed to the given parameter, while the y value remains the same.
        assertEquals("Created Tile(5,7) then set ycoord to 3 - testing x value", 5, n.getXCoord());
        assertEquals("Created Tile(5,7) then set ycoord to 3 - testing y value", 3, n.getYCoord());
    }


    @Test
    /* Creating two tiles and finding the distance between them should return the correct distance.*/
    public void test_distance_StartIsTopLeft_EndIsBottomRight() {
        Tile n1 = new Tile(1,2);
        Tile n2 = new Tile(4,6);
        double length = n1.calcDistance(n2);

        assertEquals("Distance from Tile (1,2) to (4,6) should be 5.0", 5.0, length, 0.00001);
    }

    @Test
    public void test_distance_StartIsTopRight_EndIsBottomLeft() {
        Tile n1 = new Tile(10,2);
        Tile n2 =  new Tile(4,6);
        double length = n1.calcDistance(n2);

        assertEquals("Distance from Tile (10,2) to (4,6) should be 7.21110", 7.21110, length, 0.00001);
    }

    @Test
    public void test_distance_StartIsBottomRight_EndIsTopLeft() {
        Tile n1 = new Tile(10,20);
        Tile n2 = new Tile(4,6);
        double length = n1.calcDistance(n2);

        assertEquals("Distance from Tile (10,20) to (4,6) should be 15.2315462", 15.2315462, length, 0.00001);
    }

    @Test
    public void test_distance_StartIsBottomLeft_EndIsTopRight() {
        Node n1 = new Node(2,20);
        Node n2 = new Node(4,6);
        double length = n1.calcDistance(n2);

        assertEquals("Distance from point (2,20) to (4,6) should be 14.142135", 14.142135, length, 0.00001);
    }

    @Test
    public void test_equals_SameInstance() {
        Tile n1 = new Tile(2,20);
        Tile n2 = n1;
        boolean same = n1.equals(n2);

        assertTrue("Expected variables that reference same object to be equal", same);
    }

    @Test
    public void test_equals_SameXAndY() {
        Tile n1 = new Tile(2,20);
        Tile n2 = new Tile(2,20);
        boolean same = n1.equals(n2);

        assertTrue("Expected variables that have the same x and y-coordinate to be equal", same);
    }

    @Test
    public void test_equals_DifferentX() {
        Tile n1 = new Tile(2,20);
        Tile n2 = new Tile(3,20);
        boolean same = n1.equals(n2);

        assertFalse("Expected variables with different x-coordinates to be unequal", same);
    }

    @Test
    public void test_equals_DifferentY() {
        Tile n1 = new Tile(2,20);
        Tile n2 = new Tile(2,0);
        boolean same = n1.equals(n2);

        assertFalse("Expected variables with different y-coordinates to be unequal", same);
    }
}
