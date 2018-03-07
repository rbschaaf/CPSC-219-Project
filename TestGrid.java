/**
* Class used to test the path on various grids
*
* Last Edited by Dayan J.
* 3 Mar 2018
*/

public class TestGrid {
  //private int[][] grid;
  private int[][] tfdlOne =
   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
   {0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0},
   {0,1,1,1,1,1,1,1,1,1,153,9,0,0,0,1,1,0},
   {0,1,1,1,1,1,1,1,1,1,9,9,0,0,1,1,1,0},
   {0,1,1,1,1,1,1,1,1,1,152,9,0,0,1,1,155,888},
   {0,1,1,1,1,1,1,1,1,1,9,9,0,0,1,1,154,888},
   {0,1,1,1,1,1,1,1,1,1,150,9,0,0,1,1,25,777},
   {0,1,1,9,9,1,1,1,1,1,1,0,0,1,1,1,0,0},
   {0,1,1,151,9,1,1,1,1,1,1,1,1,1,1,1,0,0},
   {0,1,1,999,9,1,1,1,1,1,1,1,1,1,1,1,700,555},
   {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,701,555},
   {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,702,555},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

   private int[][] tfdlTwo =
   {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,1,1,1,1,1,1,9,9,9,9,1,1,0,0,0,0},
   {0,0,1,1,261,1,260,1,252,9,9,9,1,1,0,0,0,0},
   {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
   {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
   {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
   {0,0,1,1,9,262,9,263,9,264,1,1,1,1,0,0,0,0},
   {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,251,888,9,0},
   {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,9,0},
   {0,0,1,1,9,259,9,9,9,9,1,1,1,1,250,888,9,0},
   {0,0,1,1,9,9,9,9,9,9,1,1,1,1,9,9,9,0},
   {0,0,1,1,1,1,1,1,1,1,1,1,1,1,25,777,0,0},
   {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

  private int[][] grid;

  /**
  * Copy constructor for the grid
  */
  public TestGrid(TestGrid gridCopy) {
    grid = gridCopy.getGrid();
  }

  public TestGrid(){}

    public int getFloorNum(int newRoomNumber) {
      int floorNum;
      //integer length - i suggest we make this numDigits
      int numDigits;
      numDigits = (int) Math.log10(newRoomNumber) + 1;
      if (numDigits < 3) {
        floorNum = 0;
      }
      // floor number from first digit of room number
      else {
        floorNum = newRoomNumber;
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
    public int[][] setGrid(String building, int roomNum) {
      int floor = getFloorNum(roomNum);
      if (building == "Taylor Family Digital Library") {
        if (floor == 1) {
          grid = tfdlOne;

        }

        else if (floor == 2) {
          grid = tfdlTwo;

        }
      }return grid;
    }

    public int[][] getGrid() {
      return grid;
    }

    public void print(int[][] grid){
      for (int row = 0; row < 14;row++){
        for (int column = 0; column <18; column++){
          System.out.printf("%4d", grid[row][column]);
        }
        System.out.println();
      }
    }

}
