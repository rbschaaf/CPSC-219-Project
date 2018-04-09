import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void test_Constructor_Node_DistanceStart() {
		Node n = new Node(0,0, 0);
		assertEquals("Created Node(0,0,0) - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(0,0,0) - testing y value", 0, n.getYCoord());
		assertEquals("Created Node(0,0,0) - testing distance value", 0, n.getStartDistance());
	}

	@Test
	public void test_Constructor_HallwayBoolean() {
		Node n = new Node(1,1, false);
		assertEquals("Created Node(-1,10) - testing x value", 1, n.getXCoord());
		assertEquals("Created Node(-1,10) - testing y value", 1, n.getYCoord());
		assertEquals("Created Node(-1,10) - testing y value", false, n.getEndNodeVal();
	}

	@Test
	public void test_Constructor_EndNodeBooleanTrue() {
		Node n = new Node(1,2, true);
		assertEquals("Created Node(-1,10) - testing x value", 1, n.getXCoord());
		assertEquals("Created Node(-1,10) - testing y value", 2, n.getYCoord());
		assertEquals("Created Node(-1,10) - testing y value", false, n.getEndNodeVal();
	}


	@Test
	public void test_CopyEndConstructor() {
		Node n = new Node(1,2, true);
		Node n2 = new Node(n);
		assertEquals("Created Node(1,2,true) and copied it.  Testing x value of original.", 5, n.getXCoord());
		assertEquals("Created Node(1,2,true) and copied it.  Testing y value of original.", 7, n.getYCoord());
		assertEquals("Created Node(1,2,true) and copied it. Testing EndNodeVal of original", false, n.getEndNodeVal();
		assertEquals("Created Node(1,2,true) and copied it.  Testing x value of copy.", 5, n2.getXCoord());
		assertEquals("Created Node(1,2,true) and copied it.  Testing y value of copy.", 7, n2.getYCoord());
		assertEquals("Created Node(1,2,true) and copied it. Testing EndNodeVal of copy", false, n2.getEndNodeVal();
	}

	@Test
	public void test_CopyStartConstructor() {
		Node n = new Node(1,2, 0);
		Node n2 = new Node(n);
		assertEquals("Created Node(1,2,0) and copied it.  Testing x value of original.", 5, n.getXCoord());
		assertEquals("Created Node(1,2,0) and copied it.  Testing y value of original.", 7, n.getYCoord());
		assertEquals("Created Node(1,2,0) and copied it. Testing distance of original", false, n.getStartDistance();
		assertEquals("Created Node(1,2,0) and copied it.  Testing x value of copy.", 5, n2.getXCoord());
		assertEquals("Created Node(1,2,0) and copied it.  Testing y value of copy.", 7, n2.getYCoord());
		assertEquals("Created Node(1,2,0) and copied it. Testing distance of copy", false, n2.getStartDistance();
	}

	@Test
	public void test_setXCoord_Zero() {
		Node n = new Node(5,7);
		n.setXCoord(0);
		assertEquals("Created Node(5,7) then set xcoord to zero - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(5,7) then set xcoord to zero - testing y value", 7, n.getYCoord());
	}

	@Test
	public void test_setYCoord_Zero() {
		Node n = new Node(5,7);
		n.setYCoord(0);
		assertEquals("Created Node(5,7) then set ycoord to zero - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then set ycoord to zero - testing y value", 0, n.getYCoord());
	}

	@Test
	public void test_setXCoord_Negative() {
		Node n = new Node(5,7);
		n.setXCoord(-54);
		assertEquals("Created Node(5,7) then set xcoord to -54 - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then set xcoord to -54 - testing y value", 7, n.getYCoord());
	}

	@Test
	public void test_setYCoord_Negative() {
		Node n = new Node(5,7);
		n.setYCoord(-32);
		assertEquals("Created Node(5,7) then set ycoord to -32 - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then set ycoord to -32 - testing y value", 7, n.getYCoord());
	}

	@Test
	public void test_setXCoord_Positive() {
		Node n = new Node(5,7);
		n.setXCoord(1);
		assertEquals("Created Node(5,7) then set xcoord to 1 - testing x value", 1, n.getXCoord());
		assertEquals("Created Node(5,7) then set xcoord to 1 - testing y value", 7, n.getYCoord());
	}

	@Test
	public void test_setYCoord_Positive() {
		Node n = new Node(5,7);
		n.setYCoord(3);
		assertEquals("Created Node(5,7) then set ycoord to 3 - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then set ycoord to 3 - testing y value", 3, n.getYCoord());
	}


	@Test
	public void test_distance_StartIsTopLeft_EndIsBottomRight() {
		Node n1 = new Node(1,2);
		Node n2 = new Node(4,6);
		double length = n1.distance(n2);

		assertEquals("Distance from point (1,2) to (4,6) should be 5.0", 5.0, length, 0.00001);
	}

	@Test
	public void test_distance_StartIsTopRight_EndIsBottomLeft() {
		Node n1 = new Node(10,2);
		Node n2 =  new Node(4,6);
		double length = n1.distance(n2);

		assertEquals("Distance from point (10,2) to (4,6) should be 7.21110", 7.21110, length, 0.00001);
	}

	@Test
	public void test_distance_StartIsBottomRight_EndIsTopLeft() {
		Node n1 = new Node(10,20);
		Node n2 = new Node(4,6);
		double length = n1.distance(n2);

		assertEquals("Distance from point (10,20) to (4,6) should be 15.2315462", 15.2315462, length, 0.00001);
	}

	@Test
	public void test_distance_StartIsBottomLeft_EndIsTopRight() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(4,6);
		double length = n1.distance(n2);

		assertEquals("Distance from point (2,20) to (4,6) should be 14.142135", 14.142135, length, 0.00001);
	}

	@Test
	public void test_equals_SameInstance() {
		Node n1 = new Node(2,20);
		Node n2 = n1;
		boolean same = n1.equals(n2);

		assertTrue("Expected variables that reference same object to be equal", same);
	}

	@Test
	public void test_equals_SameXAndY() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(2,20);
		boolean same = n1.equals(n2);

		assertTrue("Expected variables that have the same x and y-coordinate to be equal", same);
	}

	@Test
	public void test_equals_DifferentX() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(3,20);
		boolean same = n1.equals(n2);

		assertFalse("Expected variables with different x-coordinates to be unequal", same);
	}

	@Test
	public void test_equals_DifferentY() {
		Node n1 = new Node(2,20);
		Node n2 = new Node(2,0);
		boolean same = n1.equals(n2);

		assertFalse("Expected variables with different y-coordinates to be unequal", same);
	}

}
