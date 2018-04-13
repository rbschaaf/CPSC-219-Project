import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
* Test class of JUnit tests for the Node class of the room finding program.
* Nodes are used for path construction.
*/

public class NodeTest {

  /**
  * Testing the construction of a node and getting its x and y distance from the start.
  */
	@Test
	public void test_Constructor_Node_DistanceStart() {
		Node n = new Node(0,0, 0);
		assertEquals("Created Node(0,0,0) - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(0,0,0) - testing y value", 0, n.getYCoord());
		assertEquals("Created Node(0,0,0) - testing distance value", 0, n.getStartDistance(), 0.00001);
	}

  /**
  * Testing that only nodes can be added to hallway tiles. Boolean prevents nodes being added to
  * places that the path cannot travel.
  */
	@Test
	public void test_Constructor_HallwayBoolean() {
    //constructor boolean parameter is false if it is not the start or end room.
		Node n = new Node(1,1, false);
		assertEquals("Created Node(-1,10) - testing x value", 1, n.getXCoord());
		assertEquals("Created Node(-1,10) - testing y value", 1, n.getYCoord());
		assertFalse("Created Node(-1,10) - testing y value", n.getEndNodeVal());
	}

  /**
  * Testing the end node can be constructed and its values are correct.
  */
	@Test
	public void test_Constructor_EndNodeBooleanTrue() {
    // constructor boolean parameter is true if it is an end node.
		Node n = new Node(1,2, true);
		assertEquals("Created Node(-1,10) - testing x value", 1, n.getXCoord());
		assertEquals("Created Node(-1,10) - testing y value", 2, n.getYCoord());
		assertTrue("Created Node(-1,10) - testing y value", n.getEndNodeVal());
	}

  /**
  * Testing the end node and its values can be copied from a previously constructed end node.
  */
	@Test
	public void test_CopyEndConstructor() {
		Node n = new Node(1,2, true);
		Node n2 = new Node(n);
		assertEquals("Created Node(1,2,true) and copied it.  Testing x value of original.", 1, n.getXCoord());
		assertEquals("Created Node(1,2,true) and copied it.  Testing y value of original.", 2, n.getYCoord());
		assertTrue("Created Node(1,2,true) and copied it. Testing EndNodeVal of original", n.getEndNodeVal());
		assertEquals("Created Node(1,2,true) and copied it.  Testing x value of copy.", 1, n2.getXCoord());
		assertEquals("Created Node(1,2,true) and copied it.  Testing y value of copy.", 2, n2.getYCoord());
		assertTrue("Created Node(1,2,true) and copied it. Testing EndNodeVal of copy", n2.getEndNodeVal());
	}

  /**
  * Testing the start node and its values can be copied from a previously constructed start node.
  */
	@Test
	public void test_CopyStartConstructor() {
    // third constructor parameter value is 0 if it is an start node.
		Node n = new Node(1,2, 0);
		Node n2 = new Node(n);
		assertEquals("Created Node(1,2,0) and copied it.  Testing x value of original.", 1, n.getXCoord());
		assertEquals("Created Node(1,2,0) and copied it.  Testing y value of original.", 2, n.getYCoord());
		assertEquals("Created Node(1,2,0) and copied it. Testing distance of original", 0, n.getStartDistance(), 0.00001);
		assertEquals("Created Node(1,2,0) and copied it.  Testing x value of copy.", 1, n2.getXCoord());
		assertEquals("Created Node(1,2,0) and copied it.  Testing y value of copy.", 2, n2.getYCoord());
		assertEquals("Created Node(1,2,0) and copied it. Testing distance of copy",0, n2.getStartDistance(), 0.00001);
	}

  /**
  * Test the setting of the x cooredinate of a node can be set to zero.
  */
	@Test
	public void test_setXCoord_Zero() {
		Node n = new Node(5,7);
		n.setXCoord(0);
		assertEquals("Created Node(5,7) then set xcoord to zero - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(5,7) then set xcoord to zero - testing y value", 7, n.getYCoord());
	}

  /**
  * Test the setting of the y cooredinate of a node can be set to zero.
  */
	@Test
	public void test_setYCoord_Zero() {
		Node n = new Node(5,7);
		n.setYCoord(0);
		assertEquals("Created Node(5,7) then set ycoord to zero - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then set ycoord to zero - testing y value", 0, n.getYCoord());
	}

  /**
  * Test the setting of the x cooredinate of a node cannot be set to a negative number.
  */
	@Test
	public void test_setXCoord_Negative() {
		Node n = new Node(5,7);
		n.setXCoord(-54);
		assertEquals("Created Node(5,7) then set xcoord to -54 - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(5,7) then set xcoord to -54 - testing y value", 7, n.getYCoord());
	}

  /**
  * Test the setting of the y cooredinate of a node cannot be set to a negative number.
  */
	@Test
	public void test_setYCoord_Negative() {
		Node n = new Node(5,7);
		n.setYCoord(-32);
		assertEquals("Created Node(5,7) then set ycoord to -32 - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then set ycoord to -32 - testing y value", 0, n.getYCoord());
	}

  /**
  * Test the setting of the x cooredinate of a node can be set to a positive number.
  */
	@Test
	public void test_setXCoord_Positive() {
		Node n = new Node(5,7);
		n.setXCoord(1);
		assertEquals("Created Node(5,7) then set xcoord to 1 - testing x value", 1, n.getXCoord());
		assertEquals("Created Node(5,7) then set xcoord to 1 - testing y value", 7, n.getYCoord());
	}

  /**
  * Test the setting of the y cooredinate of a node can be set to a positive number.
  */
	@Test
	public void test_setYCoord_Positive() {
		Node n = new Node(5,7);
		n.setYCoord(3);
		assertEquals("Created Node(5,7) then set ycoord to 3 - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then set ycoord to 3 - testing y value", 3, n.getYCoord());
	}


  /**
  * Test the correct distance can be found between a start node in the top left corner of the grid and an
  * end one in the bottom right of the grid.
  */
	@Test
	public void test_distance_StartIsTopLeft_EndIsBottomRight() {
		Node n1 = new Node(1,2);
		Node n2 = new Node(4,6);
		double length = n1.calcDistance(n2);

		assertEquals("Distance from point (1,2) to (4,6) should be 5.0", 5.0, length, 0.00001);
	}

  /**
  * Test the correct distance can be found between a start node in the top right corner of the grid and an
  * end one in the bottom left of the grid.
  */
	@Test
	public void test_distance_StartIsTopRight_EndIsBottomLeft() {
		Node n1 = new Node(10,2);
		Node n2 =  new Node(4,6);
		double length = n1.calcDistance(n2);

		assertEquals("Distance from point (10,2) to (4,6) should be 7.21110", 7.21110, length, 0.00001);
	}

  /**
  * Test the correct distance can be found between a start node in the bottom right corner of the grid and an
  * end one in the top left of the grid.
  */
	@Test
	public void test_distance_StartIsBottomRight_EndIsTopLeft() {
		Node n1 = new Node(10,20);
		Node n2 = new Node(4,6);
		double length = n1.calcDistance(n2);

		assertEquals("Distance from point (10,20) to (4,6) should be 15.2315462", 15.2315462, length, 0.00001);
	}

  /**
  * Test the correct distance can be found between a start node in the bottom left corner of the grid and an
  * end one in the top right of the grid.
  */
	@Test
	public void test_distance_StartIsBottomLeft_EndIsTopRight() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(4,6);
		double length = n1.calcDistance(n2);

		assertEquals("Distance from point (2,20) to (4,6) should be 14.142135", 14.142135, length, 0.00001);
	}

  /**
  * Test that the exact same node can be recognized as the same/itself.
  */
	@Test
	public void test_equals_SameInstance() {
		Node n1 = new Node(2,20);
		Node n2 = n1;
    //boolean is true if two nodes are identical.
		boolean same = n1.equals(n2);

		assertTrue("Expected variables that reference same object to be equal", same);
	}

  /**
  * Test that nodes with the same x and y coordinates are recognized as the same.
  */
	@Test
	public void test_equals_SameXAndY() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(2,20);
		boolean same = n1.equals(n2);

		assertTrue("Expected variables that have the same x and y-coordinate to be equal", same);
	}

  /**
  * Test that nodes with different x coordinates are recognized as different.
  */
	@Test
	public void test_equals_DifferentX() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(3,20);
    //boolean is false if nodes are not identical.
		boolean same = n1.equals(n2);

		assertFalse("Expected variables with different x-coordinates to be unequal", same);
	}

  /**
  * Test that nodes with different y coordinates are recognized as different.
  */
	@Test
	public void test_equals_DifferentY() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(2,0);
		boolean same = n1.equals(n2);

		assertFalse("Expected variables with different y-coordinates to be unequal", same);
	}

}
