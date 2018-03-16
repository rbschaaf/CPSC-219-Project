import java.util.ArrayList;

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
  {0,0,1,1,1,1,1,1,1252,1252,9,9,1,1,0,0,0,0},
  {0,0,1,1,1,1,1,1,252,1252,9,9,1,1,0,0,0,0},
  {0,0,1,1,261,1261,260,1260,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,1261,1261,1260,1260,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,1262,1262,1263,1263,1264,1264,1,1,1,1,0,0,0,0},
  {0,0,1,1,1262,262,1263,263,1264,264,1,1,1,1,0,0,0,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,251,1251,1251,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,9,0},
  {0,0,1,1,9,259,1259,9,9,9,1,1,1,1,250,888,1250,0},
  {0,0,1,1,9,1259,1259,9,9,9,1,1,1,1,9,9,9,0},
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
  private ArrayList<Room> roomList = new ArrayList<Room>();


  /**
  * default constructor for FloorPlans
  */
  public FloorPlans(){}

    /**
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

    /**
    * Constructor with a building and room number.
    * @param: a building name of type String and a destination room of type int.
    */
    public FloorPlans(String aBuilding, int theDestNum){
      building = aBuilding;
      destNum = theDestNum;
      setGrid(aBuilding,theDestNum);
      makeRooms();
      populateRooms();
    }

    /**
    * Getter method for destinaton room number.
    * @return: destination room number as an int.
    */
    public int getDestNumber(){
      return destNum;
    }

    /**
    * Getter method for building name of current flooplan.
    * @return: the building name as a String.
    */
    public String getBuildingName(){
      return building;
    }

    /**
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

    /**
    * Method that is a getter method for the current grid of the floorplan.
    * @return: the current grid from the floorplan as an 2-dimensional int array.
    */
    public int[][] getGrid(){
      return grid;
    }

    /**
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

    // get room method
 public Room getRoom(int gridNum){
   Room room = null;
   for(int i = 0; i <roomList.size();i++){
     if (roomList.get(i).getRoomsNumber() == gridNum){
       room = roomList.get(i);
     }
   }
   return room;
 }
    // add a room to the roomlist in the floorplan
    public void addRoom(int gridNum){
      // if number is not in roomlist... **
      roomList.add(new Room(gridNum));
    }
    // make a room for each corresponding room on a grid
    // meant to be used in a for-loop
    public void makeRooms(){
      int gridNum;
      for (int row = 0; row<14; row++ ){
        for(int col = 0; col<18; col++){
          gridNum = grid[row][col];
          if(gridNum>1000){
            if (getRoom(gridNum)== null){
              addRoom(gridNum);
            }
          }
        }
      }
    }

    // populate each room within the floorplan with tiles and a door
    // meant to be used in a for-loop
    public void populateRooms(){
      int gridNum;
      for (int row = 0; row<14;row++){
        for (int col =0; col<18; col++){
          gridNum = grid[row][col];
          if (getRoom(gridNum) != null){
            getRoom(gridNum).addNode(row,col);
          }
          else if(gridNum < 1000){
              // get the room for a door (1000+ item) and subtract 1000 to find
              // which room it is for. Set the door as the current tile in the loop.
              if(getRoom(gridNum)!=null){
                Room tempRoom = getRoom(gridNum);
                tempRoom.setDoor(row,col);
              }

          }

        }
      }
    }




  }
