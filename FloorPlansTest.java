/**
* Class to test the FloorPlans class
*/



import static org.junit.Assert.*;

import org.junit.Test;

public class FloorPlansTest{

  @Test
  public void testConstructors(){
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", 150);
    assertEquals("Destination number is", 150, a.getDestNumber());
    assertEquals("Building is", "Taylor Family Digital Library", a.getBuildingName());
    FloorPlans b = new FloorPlans(a);
    assertEquals("Destination number is", 150, b.getDestNumber());
    assertEquals("Building is", "Taylor Family Digital Library", b.getBuildingName());

  }

  @Test
  public void testTemporaryNums(){
    FloorPlans a = new FloorPlans();
    a.setTemporaryDestNum(260);
    a.setTemporaryStartNum(261);
    assertEquals("Temporary destination number is", 260, a.getTemporaryDestNum());
    assertEquals("Temporary start number is", 261, a.getTemporaryStartNum());
  }

  @Test
  public void testRoomLists(){
    FloorPlans a = new FloorPlans();
    a.addRoom(1);
    a.addRoom(2);
    a.addRoom(3);
    assertEquals("Room list contatins", 3, a.getRoomList().size());
  }

  @Test
  public void testGrid(){
    FloorPlans a = new FloorPlans();
    a.setGrid("Taylor Family Digital Library",150);
    assertNotNull("The grid exists", a.getGrid());//http://www.javacodex.com/JUnit/Test-Object-Is-Not-Null
    FloorPlans b = new FloorPlans();
    b.setGrid("Taylor Family Digital Library",261);
    assertNotNull("The grid exists", b.getGrid());
  }

  @Test
  public void testFloorNum(){
    FloorPlans a = new FloorPlans();
    assertEquals("Floor is", 2, a.getFloorNum(299));
    assertEquals("Floor is", 0, a.getFloorNum(51));
    assertEquals("Floor is", 3, a.getFloorNum(300));
    assertEquals("Floor is", 1, a.getFloorNum(101));
  }

  @Test
  public void testRooms(){
    FloorPlans a = new FloorPlans("Taylor Family Digital Library", 150);
    a.makeRooms();
    assertEquals("The number of rooms on the 1st floor of Taylor Digital Library is",
    10, a.getRoomList().size());
    assertNotNull("Room 151 exists", a.getRoom(151));
    FloorPlans b = new FloorPlans("Taylor Family Digital Library", 259);
    b.makeRooms();
    assertEquals("The number of rooms on the 2nd floor of Taylor Digital Library is",
    10, b.getRoomList().size());
    assertNotNull("Room 259 exists", b.getRoom(259));
    FloorPlans c = new FloorPlans();
    c.makeRooms();
    assertEquals("There is this many rooms on an empty floorplan:",
    0, c.getRoomList().size());
    assertNull("Rooms do not exist on empty floorplan grid", c.getRoom(0));
  }
}
