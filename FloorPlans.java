/**
* This class is for the FloorPlans that form the basis of each map/grid. They are based
* on the floorplans of a building.
*
* NUMBERS       CORRESPONDING ROOM
*    0          Wall
*    1          Hallway - only thing path can move through
*    9          Portions of rooms
*    25         Stairs
*   >= 100        Room Numbers, represent doors
*  888 Bathrooms
*  777 Stairs
*  555 Elevators
*/

public class FloorPlans {
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

  private int[][] grid =
  {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

  private int destNum;
  private String building;


  /*
  * default constructor for FloorPlans
  */
  public FloorPlans(){}

    /*
    * Copy constructor for FloorPlans
    * @param: an object of type FloorPlans to be copied.
    * Includes destination room, building name, and the actual floorplan.
    */
    public FloorPlans(FloorPlans fPToBeCopied){
      destNum = fPToBeCopied.getDestNumber();
      building = fPToBeCopied.getBuildingName();
      for (int row = 0; row <3; row++){
        for(int column = 0; column <3; column++){
          grid[row][column] = fPToBeCopied.grid[row][column];
        }
      }
    }

    /*
    * Constructor with a building and room number.
    * @param: a building name of type String and a destination room of type int.
    */
    public FloorPlans(String aBuilding, int theDestNum){
      building = aBuilding;
      destNum = theDestNum;
      setGrid(aBuilding,theDestNum);
    }

    /*
    * Getter method for destinaton room number.
    * @return: destination room number as an int.
    */
    public int getDestNumber(){
      return destNum;
    }

    /*
    * Getter method for building name of current flooplan.
    * @return: the building name as a String.
    */
    public String getBuildingName(){
      return building;
    }

    /*
    * Method provides the floor number a provided room is on.
    * @param: a room as an int.
    * @return: the floor number the room is on as int.
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
      // get floor number from first digit of room number if it is not the basement
      else {
        floorNum = newRoomNumber;
        while (floorNum > 9) {
          floorNum /= 10;
        }
      }
      return floorNum;
    }

    /*
    * Method that is a getter method for the current grid of the floorplan.
    * @return: the current grid from the floorplan as an 2-dimensional int array.
    */
    public int[][] getGrid(){
      return grid;
    }

    /*
    * Method to set the appropriate grid for the floorplan based on the building
    * and room of choice.
    * @param: the current building as a String and the desired room number as an int.
    */
    public void setGrid(String building, int roomNum) {
      int floor = getFloorNum(roomNum);
      // Enters the outer loop based on the building name.
      if (building.equals("Taylor Family Digital Library")) {
        /* Enters the inner loops based on the floor number, which is based on
        * desired room number. */
        if (floor == 1) {
          grid = tfdlOne;
        } else if (floor == 2) {
          grid = tfdlTwo;
        }
      }
    }
  }
