import resources.Constants;
import java.io.Serializable;
import java.util.ArrayList;

/**
* This class handles the floor plans for each building.
* Floor plans are what the path is built upon.
*/

public class FloorPlans implements Serializable {
  private int[][] grid; //FloorPlans are built on 2Dimensional integer arrays.
  private int destNum;
  private String building;
  private ArrayList<Room> roomList = new ArrayList<Room>();
  private int flNum;
  private int stairsNum;
  private int elevatorNum;


  /**
   * Default constructor for FloorPlans
   */
  public FloorPlans() {
  }

  /**
   * Copy constructor for FloorPlans
   *
   * @param toBeCopied:  an object of type FloorPlans to be copied.
   *
   */
  public FloorPlans(FloorPlans toBeCopied){
    grid = copyGrid(toBeCopied.getGrid());
    flNum = toBeCopied.getFlNum();
    stairsNum = toBeCopied.getStairsNum();
    elevatorNum = toBeCopied.getElevatorNum();
    roomList = toBeCopied.getRoomList();
    building = toBeCopied.getBuildingName();
  }

  /**
   * Constructor for FloorPlans  with the building name, grid, floor number,
   * stairs number, and elevator number.
   *
   * @param bName : a building name of type String.
   * @param aGrid : an integer 2D array grid.
   * @param floorNumber : the floor number as an integer.
   * @param stairsN : the number of the stairs tile as an integer on the floor.
   * @param eleN : the number of the elevator tile as an integer on the floor.
   *
   */
  public FloorPlans(String bName, int[][] aGrid, int floorNumber, int stairsN, int eleN){
    building = bName;
    grid = copyGrid(aGrid);
    flNum = floorNumber;
    stairsNum = stairsN;
    elevatorNum = eleN;
    makeRooms(); // Make the rooms on the grid.
    populateRooms(); // Place the rooms on the floor plan.
  }

  /**
  * Method that returns the floor number.
  *
  * @return flNum : floor number as an integer of the floor plan.
  */
  public int getFlNum(){
    return flNum;
  }

  /**
  * Method that returns the tile number for elevator.
  *
  * @return elevatorNum : number as an integer the elevator tile is set to.
  */
  public int getElevatorNum(){
    return elevatorNum;
  }

  /**
  * Method that returns the tile number for stairs.
  *
  * @return stairsNum : number as an integer the stairs tile is set to.
  */
  public int getStairsNum(){
    return stairsNum;
  }

  /**
  * Method that copies a given grid.
  *
  *@param gridToCopy : the grid to be copied as a 2D integer array (int[][]).
  *@return newGrid : the copied grid as a 2D integer array (int[][]).
  */
  public int[][] copyGrid(int[][] gridToCopy){
    int[][] newGrid = new int[gridToCopy.length][gridToCopy[0].length];
    /* Loop through each row and column to copy each coordinate's values
    from the old grid to the new grid. */
    for(int row=0; row<gridToCopy.length;row++){
      for(int col=0;col<gridToCopy[row].length;col++){
        newGrid[row][col] = gridToCopy[row][col];
      }
    }
    return newGrid;
  }


  /**
   * Getter method for the row length of the grid.
   *
   * @return: rowLength the row length of the grid as an integer.
   */
  public int getRowLength() {
    return grid.length;
  }


  /**
   * Getter method for the column length of the grid.
   *
   * @param: rowNumber the number of the current row in the grid as an integer.
   * @return: colLength the column length of the grid as an integer.
   */
  public int getColLength(int rowNumber) {
    return grid[rowNumber].length;
  }

  /**
   * Getter method for building name of current floorplan.
   *
   * @return: getBuildingName the building name as a String.
   */
  public String getBuildingName() {
    return building;
  }



  /**
   * Method that is a getter method for the current grid of the floorplan.
   *
   * @return: grid the current grid from the floorplan as an 2-dimensional int array (int[][]).
   */
  public int[][] getGrid() {
    return copyGrid(grid);
  }

  /**
  * Method that sets the grid for the current floorplan.
  *
  * @param toSet the 2-dimensional int array we want to set as the new grid.
  */
  public void setGrid(int[][] newGrid){
    grid = copyGrid(newGrid);
  }

  /**
   * Getter method to get a room object of type Room from a room number of type int from the room list.
   * These values for gridNum will be 1000 series numbers.
   *
   * @param gridNum the number of a room of type int
   * @return room the room as an object of type Room
   * @return null is returned if the passed in gridNum is not actually denoting a room that exists.
   */
  public Room getRoom(int gridNum) {
    Room room = null;
    // Loop through the room list for the floor to see if the room exists.
    for (int i = 0; i < roomList.size(); i++) {
      // If the room exists as an integer, return the room as type Room.
      if (roomList.get(i).getRoomsNumber() == gridNum) {
        room = new Room(roomList.get(i));
        //Else if the current iterated room is the destination room, return the destination as type Room.
      } else if (roomList.get(i).getRoomsNumber() == Constants.DEST) {
        room = new Room(roomList.get(i));
      }
    }
    return room;
  }

  /**
   * Method to add a room to the roomlist in the floorplan
   *
   * @param gridNum the number of a grid as an int.
   */
  public void addRoom(int gridNum) {
    // if number is not in roomlist... **
    roomList.add(new Room(gridNum));
  }

  /**
   * Method to get the list of rooms for the current floor plan
   *
   * @return copyList a copy of roomList as type ArrayList<Room>.
   */
  public ArrayList<Room> getRoomList() {
    ArrayList<Room> copyList = new ArrayList<Room>();
    // Loop through and copy each Room to the copyList.
    for (int i = 0; i < roomList.size(); i++) {
      copyList.add(new Room(roomList.get(i)));
    }
    return copyList;
  }

  /**
   * Method to go through the grid and find and add any rooms to the room list.
   */
  public void makeRooms() {
    int gridNum;
    // Loop through each coordinate in the 2D grid.
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        gridNum = grid[row][col];
        // If the current coordinate's value is > 1000, it would be a room.
        if (gridNum > 1000) {
          /* If the room is not already on the room list call the addRoom method to add the
          currently iterated room to the list. */
          if (getRoom(gridNum) == null) {
            addRoom(gridNum);
          }
        }
      }
    }
  }



  /**
   * Method to populate each room within the floorplan with tiles and a door
   * meant to be used in a for-loop
   */
  public void populateRooms() {
    int gridNum;
    // Loop through each coordinate in the 2D grid.
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        gridNum = grid[row][col];
        // If the current coordinate being iterated is a room, add a tile to the coordinate.
        for(Room room : roomList){
          if (room.getRoomsNumber() == gridNum) {
            room.addTile(row, col);
            // If the current coordinate being iterated through has a value less than 1000
          } else if (gridNum < 1000) {
            /* get the room for a door (1000+ item) and subtract 1000 to find
            which room it is for. Set the door as the current tile in the loop. */
            if (getRoom(gridNum) != null) {
              Room tempRoom = getRoom(gridNum);
              tempRoom.setDoor(row, col);
            }

          }
        }


      }
    }
  }

}
