import java.util.ArrayList;

/**
 * Class that handles everything related to the rooms of the grid map.
 */
public class Room{
  private int roomNumber=0;
  private Door aDoor = new Door();
  //no door in tileList
  private ArrayList<Node> tileList = new ArrayList<Node>();



  /**
   * Constructor for Room class that takes an int
   * @param num the number of the room as type int
   */
  public Room(int num){
    roomNumber = num;
  }


  /**
   * Method to get the room number for the room
   * @return roomNumber the number of the room as an int
   */
  public int getRoomsNumber(){
    return roomNumber;
  }



  /**
   * Method to add a node to the tile list
   * @param row the row the tile is on the grid as an int.
   * @param col the column the tile is on the grid as an int.
   */
  public void addNode(int row, int col){
    tileList.add(new Node(row,col));
  }



  /**
   * Method to set the door of the room on the grid
   * @param row the row the door is in on the grid as an int.
   * @param col the column the door is in on the grid as an int.
   */
  public void setDoor(int row, int col){
    aDoor.setDoorX(row);
    aDoor.setDoorY(col);
  }



  /**
   * Method to Get the door of the room
   * @return aDoor the door of the room of type Door
   */
  public Door getDoor(){
    return aDoor;
  }

}
