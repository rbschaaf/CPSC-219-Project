import java.io.Serializable;

/**
 * Class for the doors of the rooms on the floor plans.
 * The doors are what the path will start from and end at. 
 */

public class Door implements Serializable{
  private int doorX; // x-coordinate of the door location
  private int doorY; // y-coordinate of the door location.

  /**
  * Default constructor for Door.
  */
  public Door(){}

  /**
  * Copy constructor for Door
  */
  public Door(Door toBeCopied){
    doorX = toBeCopied.getDoorX();
    doorY = toBeCopied.getDoorY();
  }

  /**
  * Method to get the x-coordinate of the door on the floor plan.
  * @return: doorX the x-coordinate of the door as an integer.
  */
  public int getDoorX(){
    return doorX;
  }

  /**
  * Method to set the x-coordinate of the door on the floor plan.
  * @param: newDoorX is a x-coordinate to set the location the door as an integer.
  */
  public void setDoorX(int newDoorX){
    doorX = newDoorX;
  }

  /**
  * Method to get the y-coordinate of the door on the floor plan.
  * @return: doorY the y-coordinate of the door as an integer.
  */
  public int getDoorY(){
    return doorY;
  }

  /**
  * Method to set the y-coordinate of the door on the floor plan.
  * @param: newDoorY is a y-coordinate to set the location the door as an integer.
  */
  public void setDoorY(int newDoorY){
    doorY = newDoorY;
  }
}
