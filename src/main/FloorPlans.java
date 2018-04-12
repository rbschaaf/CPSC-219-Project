/** New class description*/

import resources.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class FloorPlans implements Serializable {
  private int[][] grid;
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
   * @param stairsN : the number of the stairs tile as an integer.
   * @param eleN : the number of the elevator tile as an integer.
   *
   */
  public FloorPlans(String bName, int[][] aGrid, int floorNumber, int stairsN, int eleN){
    building = bName;
    grid = copyGrid(aGrid);
    flNum = floorNumber;
    stairsNum = stairsN;
    elevatorNum = eleN;
    makeRooms();
    populateRooms();
  }

  /**
  * Method that returns the floor number.
  *
  * @return flNum : floor number of the floor plan.
  */
  public int getFlNum(){
    return flNum;
  }

  /**
  * Method that returns the tile number for elevator.
  *
  * @return elevatorNum : number the elevator tile is set to.
  */
  public int getElevatorNum(){
    return elevatorNum;
  }

  /**
  * Method that returns the tile number for stairs.
  *
  * @return stairsNum : number the stairs tile is set to.
  */
  public int getStairsNum(){
    return stairsNum;
  }

  /**
  * Method that copies a given grid.
  *
  *@param gridToCopy : the grid to be copied as a 2D integer array.
  *@return newGrid : the copied grid.
  */
  public int[][] copyGrid(int[][] gridToCopy){
    int[][] newGrid = new int[gridToCopy.length][gridToCopy[0].length];
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
   * @return: rowLength the row length of the grid
   */
  public int getRowLength() {
    return grid.length;
  }


  /**
   * Getter method for the column length of the grid.
   *
   * @param: rowNumber the number of the current row in the grid
   * @return: colLength the column length of the grid
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
   * @return: grid the current grid from the floorplan as an 2-dimensional int array.
   */
  public int[][] getGrid() {
    return grid;
  }


  /**
   * Getter method to get a room.
   *
   * @param gridNum the number of a grid of type int
   * @return room a room of type Room
   */
  public Room getRoom(int gridNum) {
    Room room = null;
    for (int i = 0; i < roomList.size(); i++) {
      if (roomList.get(i).getRoomsNumber() == gridNum) {
        room = new Room(roomList.get(i));
      } else if (roomList.get(i).getRoomsNumber() == Constants.DEST) {
        room = new Room(roomList.get(i));
      }
    }
    return room;
  }

  /**
   * Method to  add a room to the roomlist in the floorplan
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
   * @return copyList a copy of roomList.
   */
  public ArrayList<Room> getRoomList() {
    ArrayList<Room> copyList = new ArrayList<Room>();
    for (int i = 0; i < roomList.size(); i++) {
      copyList.add(new Room(roomList.get(i)));
    }
    return copyList;
  }

  /**
   * Method to make a room for each corresponding room on a grid
   * meant to be used in a for-loop
   */
  public void makeRooms() {
    int gridNum;
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        gridNum = grid[row][col];
        if (gridNum > 1000) {
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
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        gridNum = grid[row][col];
        if (getRoom(gridNum) != null) {
          getRoom(gridNum).addTile(row, col);
        } else if (gridNum < 1000) {
          // get the room for a door (1000+ item) and subtract 1000 to find
          // which room it is for. Set the door as the current tile in the loop.
          if (getRoom(gridNum) != null) {
            Room tempRoom = getRoom(gridNum);
            tempRoom.setDoor(row, col);
          }

        }

      }
    }
  }

  /**
   * Printing method for the a saved 2-dimensional grid from a file
   */
  public static void printSavedGrid(int[][] aGrid) {
    for (int row = 0; row < aGrid.length; row++) {
      for (int column = 0; column < aGrid[row].length; column++) {
        System.out.printf("%4d", aGrid[row][column]);
      }
      System.out.println();
    }
  }


}

