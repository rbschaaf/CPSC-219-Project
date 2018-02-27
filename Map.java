/**Class for the creations of the maps, sizing of the maps, and contains the path restriction for the pathfinding.*/

public class Map{
  // Instance variables
  private int roomNum;
  //Defaults at TFDL
  private String building = "Taylor Family Digital Library"; //This is probably fine as a string
  //private String endBuilding = "Taylor Family Digital Library";
  private int[][] floorGrid;

  private boolean avoidStairs;
  private boolean avoidElevator;

  private int avoidRoomNum;
  /*
  * Removed Row and Column, they should not be instances rather variables
  * within the methods below
  */


  // Constructors
  public Map (int newRoomNumber) {
    roomNum = newRoomNumber;
    FloorPlans floorPlan = new FloorPlans();
    floorPlan.setGrid(building, roomNum);
    floorGrid = floorPlan.getGrid();
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
/*
  public void setFloorPlan(FloorPlans newFloorPlan) {
    floorPlan = newFloorPlan;
  }

*/
  /*
  * Printing method for the Grid
  */

  public void printGrid() {
    for (int row = 0; row < 14; row++) {
      for (int column = 0; column <18; column++) {
        System.out.printf("%4d", floorGrid[row][column]);
      }
      System.out.println();
    }
  }




  /*
  * get the room number for each point in the grid
  */
  public int getGridPointNum(int row, int column) {
    int gridPointVal;
    gridPointVal = floorGrid[row][column];
    return gridPointVal;
  }


  public int[][] getCopyGrid() {
    FloorPlans copyFloorPlan = new FloorPlans();
    int[][] copyGrid = copyFloorPlan.getGrid();
    return copyGrid;
  }


  /*public static void main(String[] args){
    int room = 161;
    Map map = new Map(room);
    map.printGrid();

  }*/
}
