/**
* Class for the creations of the maps, sizing of the maps, and contains the path restriction for the pathfinding.
*/

public class Map{

  public static final int DESTINATION_MARKER = 5;
  public static final int STARTER_MARKER = 8;
  // Instance variables
  private int roomNum;
  private String building;
  private boolean avoidStairs; //Still in development.
  private boolean avoidElevator; //Still in development.
  private FloorPlans currentFloorPlan = new FloorPlans();
  private int avoidRoomNum; //Still in development.

  /**
  * Constructor with a provided FloorPlans.
  * @param: a floorplan of type FloorPlans.
  */
  public Map (FloorPlans newFloorPlan){
    currentFloorPlan = newFloorPlan;
  }

  /**
  * Constructor with a provided room number that generates a new floorplan.
  * @param: a room number of interest as an int.
  */
  public Map (int newRoomNumber) {
    roomNum = newRoomNumber;
    FloorPlans floorPlan = new FloorPlans();
    //floorPlan.setGrid(building, roomNum);
    //floorGrid = floorPlan.getGrid();
  }

  /**
  * Default constructor for Map
  */
  public Map(){};

  /**
  * Method that sets the current floorplan.
  * @param: the currently being used floorplan as type FloorPlans.
  */
  public void setCurrentFloorPlan(FloorPlans newFloorPlan){
    currentFloorPlan = newFloorPlan;
  }

  /**
  * Method that gets the current floorplan.
  * @return: the current floorplan as type FloorPlans.
  */
  public FloorPlans getCurrentFloorPlan(){
    return currentFloorPlan;
  }

  /**
  * Printing method for the floorplan as a 2-dimensional grid.
  */
  public void printGrid() {
    for (int row = 0; row < 14; row++) {
      for (int column = 0; column <18; column++) {
        System.out.printf("%4d", currentFloorPlan.getGrid()[row][column]);
      }
      System.out.println();
    }
  }

  /**
  * Method to place start marker at the starting room on the grid.
  * @param: row of the starting room as an int and column of the starting
  * room as an int.
  */
  public void placeStart(int startRow, int startCol){
    currentFloorPlan.getGrid()[startRow][startCol]=STARTER_MARKER;
  }

  /**
  * Method to place the Destination marker on the destination room.
  * @param: row of the destination room as an int and column of the destination
  * as an int.
  */
  public void placeDest(int endRow, int endCol){
    currentFloorPlan.getGrid()[endRow][endCol] = DESTINATION_MARKER;
  }

  /**
  * Getter method to get the associated number for each point in the grid.
  * @param: the row of the point as an int and the column of the point as an int.
  * @return: the value off the grid for the point as an int.
  */
  public int getGridPointNum(int row, int column) {
    int gridPointVal;
    gridPointVal = currentFloorPlan.getGrid()[row][column];
    return gridPointVal;
  }

}
