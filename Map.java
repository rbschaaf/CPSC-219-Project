public class Map{
  // Instance variables
  // 25 is stair, 0 is wall and 1 is hallway, 9 is rooms
  public int[][] grid =
  {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,1,1,1,1,1,1,9,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,1,1,1,1,252,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,261,9,9,260,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,262,9,263,9,264,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,251,9,9,9},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,9,9},
  {0,0,1,1,259,9,9,9,9,9,1,1,1,1,250,9,9,9},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,9,9,9,9},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,25,9,0,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
  private int row;
  private int column;

  // Constructors
  public Map() {}

  // Copy constructor for testing and path manipulation
  public Map(Map gridToCopy){
    for(row =0; row <14; row++){
      for(column =0; column <18; column++){
        grid[row][column] = gridToCopy.grid[row][column];
      }
    }
  }
  // Methods

  public void print(){
    for (row = 0; row < 14;row++){
      for (column = 0; column <18; column++){
        System.out.printf("%4d", grid[row][column]);
      }
      System.out.println();
    }
  }
  // Valid movement
  public boolean isMoveValid(int row, int column){
    return (grid[row][column] == 1 || //hallway
            grid[row][column] == 5);// || //destination
            //grid[row][column] == 8 ||  //start
            //grid[row][column] == 7); //pathalreadytaken
  }
}
