import java.util.ArrayList;
// Last updated Feb. 29 by Nicki.
public class Room{
  private int roomNumber=0;
  private Door aDoor = new Door();
  //no door in tileList
  private ArrayList<Node> tileList = new ArrayList<Node>();

  //Constructor for Room class that takes an int
  public Room(int num){
    roomNumber = num;
  }
  // Get the room number for the room
  public int getRoomsNumber(){
    return roomNumber;
  }

  //Add a tile to the tile list
  public void addNode(int row, int col){
    tileList.add(new Node(row,col));
  }

  //Set the door of the room
  public void setDoor(int row, int col){
    aDoor.setDoorX(row);
    aDoor.setDoorY(col);
  }

  //Get the door of the room
  public Door getDoor(){
    return aDoor;
  }

}
