/**
* Test Class of JUnit tests to test the Door class of the room finding program.
*/

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoorTest{

  /**
  * Testing the copy constructor for the Door.
  */
  @Test
  public void testCopyConstructor() {
    //Construct a door and set the x and y coordinates.
    Door a = new Door();
    a.setDoorX(10);
    a.setDoorY(5);
    //Copy the previously created door to a new door and compare the x and y coordinates are the same.
    Door b = new Door(a);
    assertTrue("Door's X is not correct", a.getDoorX() == b.getDoorX() && a.getDoorY() == b.getDoorY());
  }

  /**
  * Test the setting and getting of the x coordinates of the Door.
  */
  @Test
  public void setAndGetDoorX() {
    //Create a Door, set the x coordinates for it, and compare with the getter method.
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


  /**
  * Test the setting and getting of the y coordinates of the Door.
  */
  @Test
  public void setAndGetDoorY() {
    //Create a Door, set the y coordinates for it, and compare with the getter method.
    Door a = new Door();
    a.setDoorY(15);
    assertEquals("Door's Y is not correct", 15, a.getDoorY());
    Door b = new Door();
    b.setDoorY(20);
    assertEquals("Door's Y is not correct", 20, b.getDoorY());
    Door c = new Door();
    c.setDoorY(0);
    assertEquals("Door's Y is not correct", 0, c.getDoorY());
  }

}
