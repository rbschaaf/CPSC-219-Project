/**Class for the creations of the maps, sizing of the maps, and contains the path restriction for the pathfinding.*/

public class Map{
  // Instance variables
  private int startRoom;
  private int endRoom;
  private String Building; //This is probably fine as a string
  /*
  * NUMBERS       CORRESPONDING ROOM
  *    0          Wall
  *    1          Hallway - only thing path can move through
  *    9          Portions of rooms, WILL NEED TO CHANGE THIS
  *    25         Stairs that can be used later
  *   >100        Room Numbers, represent doors
  */
  public int[][] grid =
  {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,1,1,1,1,1,1,9,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,1,1,1,1,252,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,261,9,9,260,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,262,9,263,9,264,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,251,9,9,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,9,0},
  {0,0,1,1,259,9,9,9,9,9,1,1,1,1,250,9,9,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,9,9,9,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,25,9,0,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

  /*
  * Removed Row and Column, they should not be instances rather variables
  * within the methods below
  */


  // Constructors
  public Map() {}

  // Copy constructor for testing and path manipulation
  public Map(Map gridToCopy){
    for(int row =0; row <14; row++){
      for(int column =0; column <18; column++){
        grid[row][column] = gridToCopy.grid[row][column];
      }
    }
  }

  /**
  * GETTER AND SETTER METHODS
  */

  // getter start room
  public int getStartRoom() {
    return startRoom;
  }
  // setter start room
  public void setStartRoom(int newStartRoom) {
    startRoom = newStartRoom;
  }

  // getter end room
  public int getEndRoom() {
    return endRoom;
  }
  // setter end room
  public int setEndRoom(int newEndRoom) {
    endRoom = newEndRoom;
  }

  /*
  * Printing method for the Grid
  */

  public void print(){
    for (int row = 0; row < 14; row++){
      for (int column = 0; column <18; column++){
        System.out.printf("%4d", grid[row][column]);
      }
      System.out.println();
    }
  }

  /*
  * Check for valid I think should be moved to the setter methods
  *
  public boolean isMoveValid(int x, int y){
      return (grid[x][y] >0 && grid[x][y]!=7);// && grid[x][y]==9);// || //destination
              //grid[row][column] == 8 ||  //start
              //grid[row][column] == 7); //pathalreadytaken
  }
  */
}
