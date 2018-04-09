/**
* This class is for the FloorPlans that form the basis of each map/grid. They are based
* on the floorplans of a building.
*
* NUMBERS       CORRESPONDING ROOM
*    0          Wall
*    1          Hallway - only thing path can move through
*    9          Portions of rooms
*    25         Constants.STAIRs
*   >= 100      Room Numbers, represent doors
*  888 Bathrooms
*/

import resources.Constants;
import resources.BuiltFloorPlans;

import java.util.ArrayList;
import java.util.Arrays;

public class FloorPlans {


  private int[][] grid =
  {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

  private int[][] aCopyGrid =
  {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};


  private int destNum;
  private String building;
  private ArrayList<Room> roomList = new ArrayList<Room>();
  private int tempDestNum;
  private int tempStartNum;


  /**
  * Default constructor for FloorPlans
  */
  public FloorPlans() {
  }

  /**
  * Copy constructor for FloorPlans
  *
  * @param: fPToBeCopied an object of type FloorPlans to be copied.
  * Includes destination room, building name, and the actual floorplan.
  */
  public FloorPlans(FloorPlans fPToBeCopied) {
    destNum = fPToBeCopied.getDestNumber();
    building = fPToBeCopied.getBuildingName();
    for (int row = 0; row < 3; row++) {
      for (int column = 0; column < 3; column++) {
        grid[row][column] = fPToBeCopied.grid[row][column];
      }
    }
  }

  /**
  * Constructor with a building and room number.
  * @param: aBuilding a building name of type String.
  * @theDestNum a destination room of type int.
  */
  public FloorPlans(String aBuilding, int theDestNum) {
    building = aBuilding;
    destNum = theDestNum;
    setGrid(aBuilding, theDestNum);
    makeRooms();
    populateRooms();
  }

  /**
  * Getter method for destinaton room number.
  *
  * @return: destNum destination room number as an int.
  */
  public int getDestNumber() {
    return destNum;
  }

/**
* Getter method for the row length of the grid.
*
* @return: rowLength the row length of the grid
*/
public int getRowLength(){
  return grid.length;
}

/**
* Getter method for the column length of the grid.
*
* @param: rowNumber the number of the current row in the grid
* @return: colLength the column length of the grid
*/
public int getColLength(int rowNumber){
  return grid[rowNumber].length;
}

  /**
  * Getter method for building name of current flooplan.
  *
  * @return: getBuildingName the building name as a String.
  */
  public String getBuildingName() {
    return building;
  }

  /**
  * Getter method for the temporary destination number.
  * @return: get the temporary destination number as an integer.
  */
  public int getTemporaryDestNum(){
    return tempDestNum;
  }

  /**
  * Setter method for the temporary destination number.
  * @param: newTempDestNum new temporary destination number.
  */
  public void setTemporaryDestNum(int newTempDestNum){
    tempDestNum = newTempDestNum;
  }

  /**
  * Getter method for the temporary start number.
  * @return: get the temporary start number as an integer.
  */
  public int getTemporaryStartNum(){
    return tempStartNum;
  }

  /**
  * Setter method for the temporary start number.
  * @param: newTempStartNum new temporary start number.
  */
  public void setTemporaryStartNum(int newTempStartNum){
    tempStartNum = newTempStartNum;
  }

  /**
  * Method provides the floor number a provided room is on.
  *
  * @param: newRoomNumber a room as an int.
  * @return: floorNum the floor number the room is on as int.
  */
  public int getFloorNum(int newRoomNumber) {
    int floorNum;
    int numDigits;
    numDigits = (int) Math.log10(newRoomNumber) + 1;
    /* if the provided room number has less than three digits it will be on the
    basement floor.*/
    if (numDigits < 3) {
      floorNum = 0;
    }
    /*get floor number from first digit of room number if it is not the basement*/
    else {
      floorNum = newRoomNumber;
      while (floorNum > 9) {
        floorNum /= 10;
      }
    }
    return floorNum;
  }

  /**
  * Method that is a getter method for the current grid of the floorplan.
  *
  * @return: grid the current grid from the floorplan as an 2-dimensional int array.
  */
  public int[][] getGrid() {
    return grid;}
    /*for(int row=0; row<grid.length;row++){
      for(int col=0;col<grid[row].length;col++){
        aCopyGrid[row][col]=grid[row][col];
      }
    }
    return aCopyGrid;
  }*/

  /**
  * Method that copies a floorplan.
  * @param: aFloorPlan a floorplan to be copied as type int[][]..
  * @return: aCopyGrid a copy of the floorplan passed as a paramter at type int[][].
  * https://stackoverflow.com/questions/5563157/how-to-deep-copy-2-dimensional-array-different-row-sizes
  */
  public int[][] copyGrid(int[][] aFloorPlan){
    int[][] aDuplicateGrid = new int[aFloorPlan.length][];
    for(int row=0; row<aFloorPlan.length;row++){
      //for(int col=0;col<aFloorPlan[row].length;col++){
        aDuplicateGrid[row]=Arrays.copyOf(aFloorPlan[row], aFloorPlan[row].length);
      }
    //}
    printSavedGrid(aDuplicateGrid);
    return aDuplicateGrid;
  }

  /**
  * Method to set the appropriate grid for the floorplan based on the building
  * and room of choice.
  *
  * @param: building the current building as a String. roomNum the desired room number as an int.
  */
  public void setGrid(String building, int roomNum) {
    int floor = getFloorNum(roomNum);
    // Enters the outer loop based on the building name.
    if (building.equals("Taylor Family Digital Library")) {
      /* Enters the inner loops based on the floor number, which is based on
      * desired room number. */
      if (floor == 0){
        grid = copyGrid(BuiltFloorPlans.TFDLGROUND);
      } else if (floor == 1) {
        grid = copyGrid(BuiltFloorPlans.TFDLONE);
      } else if (floor == 2) {
        grid = copyGrid(BuiltFloorPlans.TFDLTWO);
      } else if (floor == 3){
        grid = copyGrid(BuiltFloorPlans.TFDLTHREE);
      } else if (floor == 4){
        grid = copyGrid(BuiltFloorPlans.TFDLFOUR);
      } else if (floor == 5){
        grid = copyGrid(BuiltFloorPlans.TFDLFIVE);
      } else if (floor == 6) {
        grid = copyGrid(BuiltFloorPlans.TFDLSIX);
      }
      System.out.println(floor);
    }
  }

  /**
  *  Getter method to get a room.
  * @param gridNum the number of a grid of type int
  * @return room a room of type Room
  */
  public Room getRoom(int gridNum) {
    Room room = null;
    for (int i = 0; i < roomList.size(); i++) {
      if (roomList.get(i).getRoomsNumber() == gridNum) {
        room = new Room(roomList.get(i));
      } else if (roomList.get(i).getRoomsNumber() == Constants.DEST){
        room = new Room(roomList.get(i));
      }
    }
    return room;
  }

  /**
  * Method to  add a room to the roomlist in the floorplan
  * @param gridNum the number of a grid as an int.
  */
  public void addRoom(int gridNum) {
    // if number is not in roomlist... **
    roomList.add(new Room(gridNum));
  }

  /**
  * Method to get the list of rooms for the current floor plan
  *@ return a copy of roomList.
  */
  public ArrayList<Room> getRoomList(){
    ArrayList<Room> copyList = new ArrayList<Room>();
    for(int i=0;i<roomList.size();i++){
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
          getRoom(gridNum).addNode(row, col);
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
  public void printSavedGrid(int[][] aGrid) {
    for (int row = 0; row < aGrid.length; row++) {
      for (int column = 0; column < aGrid[row].length; column++) {
        System.out.printf("%4d", aGrid[row][column]);
      }
      System.out.println();
    }
  }


}
