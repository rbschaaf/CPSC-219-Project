/**
* Class for the creations of the maps, sizing of the maps, and contains the path restriction for the pathfinding.
*/

public class Map{

  private int roomNum;
  private String building;
  private FloorPlans currentFloorPlan = new FloorPlans();
  private int startX;
  private int startY;
  private int endX;
  private int endY;

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
  * Method that sets the coordinate values startX and startY for the starting room.
  * @param: a floorPlan; the current start Room.
  */
  public void setStartValues(FloorPlans aFP,int startRoom){
    int row;
    int col;
    for(row=0;row<14;row++){
      for(col=0;col<18;col++){
        if(aFP.getGrid()[row][col]==startRoom){
          startX = row;
          startY = col;
        }
      }
    }
  }

  /**
  * Method that sets the coordinate values endX and endY for the end/destination room.
  * @param: a floorPlan; the current end Room.
  */
  public void setEndValues(FloorPlans aFP, int endRoom){
    int row;
    int col;
    for(row=0;row<14;row++){
      for(col=0;col<18;col++){
        if(aFP.getGrid()[row][col]==endRoom){
          endX = row;
          endY = col;
        }
      }
    }
  }

  /**
  * Method to place start marker at the starting room on the grid.
  * @param: a chosen int[][] grid.
  *
  */
  public void placeStart(int[][] aGrid){
    aGrid[startX][startY]=8;
  }

  /**
  * Method to place the Destination marker on the destination room.
  * @param: a chosen int[][] grid.
  */
  public void placeDest(int[][] aGrid){
    aGrid[endX][endY] = 5;
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
