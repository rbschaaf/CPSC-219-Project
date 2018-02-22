public class FloorPlans {
  private int[][] grid;

  /**
  * Copy constructor for the grid
  */
  public FloorPlans(FloorPlans floorPlanCopy) {
    grid = floorPlanCopy.getGrid();
  }

  public FloorPlans(){}

  public int getFloorNum(int newRoomNumber) {
    int floorNum;
    //integer length
    int intLength;
    intLength = (int) Math.log10(newRoomNumber) + 1;
    if (intLength < 3) {
      floorNum = 0;
    }
    // floor number from first digit of room number
    else {
      int floorNum = newRoomNumber;
      while (floorNum > 9) {
        floorNum /= 10;
      }
    }
    return floorNum;
  }

  /*
  * Method to set the appropriate grid
  *
  * NUMBERS       CORRESPONDING ROOM
  *    0          Wall
  *    1          Hallway - only thing path can move through
  *    9          Portions of rooms, WILL NEED TO CHANGE THIS
  *    25         Stairs that can be used later
  *   >= 100        Room Numbers, represent doors
  */
  public void setGrid(String building, int roomNum) {
    int floor = getFloorNum(roomNum);
    if (building == "Taylor Family Digital Library") {
      if (floor == 1) {
        grid = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,1,1,1,1,9,9,9,9,1,1,0,0,0,0},
                {0,0,1,1,161,1,260,1,252,9,9,9,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
                {0,0,1,1,162,1,163,1,264,1,1,1,1,1,1,151,9,9,0},
                {0,0,1,1,1,159,1,1,1,1,1,1,1,1,9,9,9,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,150,9,9,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,9,9,9,0},
                {0,0,1,1,1,1,1,1,1,1,1,1,1,1,25,9,0,0},
                {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
      }

      else if (floor == 2) {
        grid = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,1,1,1,1,9,9,9,9,1,1,0,0,0,0},
                {0,0,1,1,261,1,260,1,252,9,9,9,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
                {0,0,1,1,262,1,263,1,264,1,1,1,1,1,1,251,9,9,0},
                {0,0,1,1,1,259,1,1,1,1,1,1,1,1,9,9,9,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,250,9,9,0},
                {0,0,1,1,9,9,9,9,9,9,1,1,1,1,9,9,9,0},
                {0,0,1,1,1,1,1,1,1,1,1,1,1,1,25,9,0,0},
                {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
      }
    }
  }

  public int[][] getGrid() {
    return grid;
  }

}
