/**
* Class to test the Door class
*/

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoorTest{

  @Test
  public void testCopyConstructor() {
    Door a = new Door();
    a.setDoorX(10);
    a.setDoorY(5);
    Door b = new Door(a);
    assertTrue("Door's X is not correct", a.getDoorX() == b.getDoorX() && a.getDoorY() == b.getDoorY());
  }

  @Test
  public void setAndGetDoorX() {
    Door a = new Door();
    a.setDoorX(10);
    a.setDoorY(5);
    assertEquals("Door's X is not correct", 10, a.getDoorX());
    Door b = new Door();
    b.setDoorX(0);
    assertEquals("Door's X is not correct", 0, b.getDoorX());
    Door c = new Door();
    c.setDoorX(9);
    assertEquals("Door's X is not correct", 9, c.getDoorX());
  }



  @Test
  public void setAndGetDoorY() {
    Door a = new Door();
    a.setDoorY(15);
    assertEquals("Door's Y is not correct", 15, a.getDoorY());
    Door b = new Door();
    b.setDoorY(20);
    assertEquals("Door's Y is not correct", 20, b.getDoorY());
    Door c = new Door();
    c.setDoorY(0);
    assertEquals("Door's Y is not correct", 0, b.getDoorY());
  }

}
