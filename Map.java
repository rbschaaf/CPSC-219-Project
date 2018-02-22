/**Class for the creations of the maps, sizing of the maps, and contains the path restriction for the pathfinding.*/

public class Map{
  // Instance variables
  private int roomNum;
  //Defaults at TFDL
  private String building = "Taylor Family Digital Library"; //This is probably fine as a string
  //private String endBuilding = "Taylor Family Digital Library";
  private FloorPlans floorPlan;
  /*
  * Removed Row and Column, they should not be instances rather variables
  * within the methods below
  */


  // Constructors
  public Map (int newRoomNumber) {
    roomNum = newRoomNumber;
    floorPlan = new FloorPlans(floorPlan);
    floorPlan.setGrid(building, roomNum);
  }



  /**
  * GETTER AND SETTER METHODS
  */

  // getter room
  public int getRoomNum() {
    return roomNum;
  }
  // setter room
  public void setRoomNum(int newRoom) {
    roomNum = newRoom;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String newBuilding) {
    building = newBuilding;
  }

  public void setFloorPlan(FloorPlans newFloorPlan) {
    floorPlan = newFloorPlan;
  }
  /*
  * Printing method for the Grid
  */

  public void print(){
    floorPlan.setGrid(building, roomNum);
    FloorPlans copyFloorPlan = new FloorPlans(floorPlan);
    int[][] copyGrid = copyFloorPlan.getGrid();
    for (int row = 0; row < 14; row++){
      for (int column = 0; column <18; column++){
        System.out.printf("%4d", grid[row][column]);
      }
      System.out.println();
    }
  }


  /*
  * Checking move validity
  */

  public boolean isMoveValid(int row, int column){
    boolean valid;
    floorPlan.setGrid(building, roomNum);
    FloorPlans copyFloorPlan = new FloorPlans(floorPlan);
    int[][] copyGrid = copyFloorPlan.getGrid();
    valid = (copyGrid[row][column] > 0 && copyGrid[row][column] != 9 && copyGrid[row][column] != 7);// && copyGrid[row][column]==9);// || //destination
            //copyGrid[row][column] == 8 ||  //start
            //copyGrid[row][column] == 7); //pathalreadytaken
    return (valid);
  }

  /*
  * get the room number for each point in the grid
  */
  public int getGridPointNum(int row, int column) {
    int gridPointVal;
    floorPlan.setGrid(building, roomNum);
    FloorPlans copyFloorPlan = new FloorPlans(floorPlan);
    int[][] copyGrid = copyFloorPlan.getGrid();
    gridPointVal = copyGrid[row][column];
    return gridPointVal;
  }
}
