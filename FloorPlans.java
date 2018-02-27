public class FloorPlans {
  //private int[][] grid;
  private static int[][] tfdlOne = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,1,1,1,1,1,1,9,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,161,1,160,1,152,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,162,1,163,1,164,1,1,1,1,1,1,151,9,9,0},
  {0,0,1,1,1,159,1,1,1,1,1,1,1,1,9,9,9,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,150,9,9,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,9,9,9,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,25,9,0,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

  private static int[][] tfdlTwo = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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

  private static int[][] grid;
  private static int row;
  private static int column;

  /**
  * Copy constructor for the grid
  */
  public FloorPlans(FloorPlans floorPlanCopy) {
    grid = floorPlanCopy.getGrid();
  }

  public FloorPlans(){}

    public static int getFloorNum(int newRoomNumber) {
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
    public static int[][] setGrid(String building, int roomNum) {
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

    public static int[][] getGrid() {
      return grid;
    }

    public static void print(int[][] aBuildingFloor){
      for (row = 0; row < 14;row++){
        for (column = 0; column <18; column++){
          System.out.printf("%4d", aBuildingFloor[row][column]);
        }
        System.out.println();
      }
    }

    public static void main(String[] args){
      //setGrid("Taylor Family Digital Library", 161);
      //int[][] aGrid = getGrid();
      /*int room = 161;
      Map map = new Map(room);
      map.printGrid();*/
    }

  }

