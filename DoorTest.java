/**
* Class to test the Door class
*/

import static org.junit.Assert.*;

import org.junit.Test;

public class DoorTest{

  @Test
  public void test_getter_and_setter_DoorXandY(){
    Door a = new Door();
    a.setDoorX(10);
    a.setDoorY(5);
    assertEquals("Door's X is at", 10, a.getDoorX());
    assertEquals("Door's Y is at", 5, a.getDoorY());
    Door b = new Door();
    b.setDoorX(0);
    b.setDoorY(7);
    assertEquals("Door's X is at", 0, b.getDoorX());
    assertEquals("Door's Y is at", 7, b.getDoorY());
    Door c = new Door();
    c.setDoorX(9);
    c.setDoorY(0);
    assertEquals("Door's X is at", 9, c.getDoorX());
    assertEquals("Door's Y is at", 0, c.getDoorY());
  }
}
