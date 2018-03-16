import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void test_Constructor_Node0_0() {
		Node n = new Node(0,0);
		assertEquals("Created Node(0,0) - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(0,0) - testing y value", 0, n.getYCoord());
	}

	@Test
	public void test_Constructor_NegativeX() {
		Node n = new Node(-1,10);
		assertEquals("Created Node(-1,10) - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(-1,10) - testing y value", 10, n.getYCoord());
	}

	@Test
	public void test_Constructor_NegativeY() {
		Node n = new Node(5,-4);
		assertEquals("Created Node(5,-4) - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,4) - testing y value", 0, n.getYCoord());
	}

	@Test
	public void test_Constructor_NegativeXandY() {
		Node n = new Node(-2,-11);
		assertEquals("Created Node(-2,-11) - testing x value", 0, n.getXCoord());
		assertEquals("Created Node(-2,-11) - testing y value", 0, n.getYCoord());
	}

	@Test
	public void test_Constructor_PositiveXandY() {
		Node n = new Node(5,7);
		assertEquals("Created Node(5,7) - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) - testing y value", 7, n.getYCoord());
	}

	@Test
	public void test_CopyConstructor() {
		Node n = new Node(5,7);
		Node n2 = new Node(n);
		assertEquals("Created Node(5,7) and copied it.  Testing x value of original.", 5, n.getXCoord());
		assertEquals("Created Node(5,7) and copied it.  Testing y value of original.", 7, n.getYCoord());
		assertEquals("Created Node(5,7) and copied it.  Testing x value of copy.", 5, n.getXCoord());
		assertEquals("Created Node(5,7) and copied it.  Testing y value of copy.", 7, n.getYCoord());
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
	public void test_moveUp() {
		Node n = new Node(5,7);
		n.moveUp(3);
		assertEquals("Created Node(5,7) then moved up 3 - testing x value", 5, n.getXCoord());
		assertEquals("Created Node(5,7) then moved up 3 - testing y value", 4, n.getYCoord());
	}

	@Test
	public void test_moveDown() {
		Node n = new Node(10,4);
		n.moveDown(5);
		assertEquals("Created Node(10,4) then moved down 4 - testing x value", 10, n.getXCoord());
		assertEquals("Created Node (10,4) then moved down 4 - testing y value", 9, n.getYCoord());
	}

	@Test
	public void test_moveLeft() {
		Node n = new Node(10,4);
		n.moveLeft(1);
		assertEquals("Created Node(10,4) then moved left 1- testing x value", 9, n.getXCoord());
		assertEquals("Created Node (10,4) then moved left 1 - testing y value", 4, n.getYCoord());
	}

	@Test
	public void test_moveRight() {
		Node n = new Node(0,0);
		n.moveRight(101);
		assertEquals("Created Node(0,0) then moved right 101- testing x value", 101, n.getXCoord());
		assertEquals("Created Node (0,0) then moved right 101 - testing y value", 0, n.getYCoord());
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
