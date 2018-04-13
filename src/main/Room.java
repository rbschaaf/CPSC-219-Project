import java.util.ArrayList;
import java.io.Serializable;

/**
 * Class that handles everything related to the rooms of the grid map.
 */
public class Room implements Serializable{
  private int roomNumber = 0;
  private Door aDoor = new Door();
  //no door in tileList
  private ArrayList<Tile> tileList = new ArrayList<Tile>();


  /**
   * Constructor for Room class that takes an int
   *
   * @param num the number of the room as type int
   */
  public Room(int num) {
    roomNumber = num;
  }

  /**
   * Copy constructor for Room class.
   *
   * @param toBeCopied the room to be copied as type Room.
   */
  public Room(Room toBeCopied) {
    roomNumber = toBeCopied.getRoomsNumber();
    aDoor = toBeCopied.getDoor();
    tileList = toBeCopied.getTileList();
  }

  /**
   * Method to get the room number for the room
   *
   * @return roomNumber the number of the room as an int
   */
  public int getRoomsNumber() {
    return roomNumber;
  }

  /**
  * Method to get a copy of the tiles in an arraylist.
  *
  * @return newList are the tiles in an arraylist of type ArrayList<Tile>
  */
  public ArrayList<Tile> getTileList() {
    ArrayList<Tile> newList = new ArrayList<Tile>();
    // Loop through the tileList and add each one to the newList arraylist.
    for (int i = 0; i < tileList.size(); i++) {
      newList.add(new Tile(tileList.get(i)));
    }
    return newList;
  }

  /**
   * Method to add a Tile to the tile list
   *
   * @param row the row the tile is on in the grid as an int.
   * @param col the column the tile is on in the grid as an int.
   */
  public void addTile(int row, int col) {
    tileList.add(new Tile(row, col));
  }


  /**
   * Method to set the door of the room on the grid
   *
   * @param row the row the door is in on the grid as an int.
   * @param col the column the door is in on the grid as an int.
   */
  public void setDoor(int row, int col) {
    aDoor.setDoorX(row);
    aDoor.setDoorY(col);
  }


  /**
   * Method to Get the door of the room
   *
   * @return aDoor a copy of the door of the room of type Door
   */
  public Door getDoor() {
    return new Door(aDoor);
  }

}
