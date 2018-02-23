
/**
* Class that finds the shortest path from point A to point B
* on a single array of integers, avoiding any obstacles
* pathfinding portion of the program
* Last Edited - Dayan J
* 14 Feb 2018 from 7:00PM -
*/
public class Path {
  /**
  * Removed all of the instance variables that are not the map
  * map object should contain the intial points to use in this
  */
  private int startRow;
  private int endRow;
  private int startCol;
  private int endCol;
  private int grid[][];
  /*
  * Added the instances again, I think the map class should only be used
  * to deliver various floorplans and do stuff to thems
  *
  //private String startBuilding = "Taylor Family Digital Library";
  //  private String endBuilding = "Taylor Family Digital Library";
  */



/** Constructors
*
* Require a grid to manipulate in path formation
*/
  public Path(int[][] newGrid){
    grid = newGrid;
  }

  //setter for grid
  public void setGrid(int[][] newGrid) {
    grid = newGrid;
  }

  //getter and setter methods for startX and startY
  public int getStartX(){
    return startX;
  }
  public void setStartX(int newStartX){
    startX = newStartX;
  }
  public int getStartY(){
    return startY;
  }
  public void setStartY(int newStartY){
    startY = newStartY;
  }
  //getter and setter methods for destRow and destColumn
  public int getDestY(){
    return destY;
  }
  public void setDestY(int newDestY){
    destY = newDestY;
  }
  public int getDestX(){
    return destX;
  }
  public void setDestX(int newDestX){
    destX = newDestX;
  }

  // setter method for the destination row and column based on Room Number
  public void setDestLoc(int roomDest){
    int row;
    int column;

    for(row =0; row<14;row++){
      for(column =0; column<18;column++){
        if(grid[row][column]==roomDest){
          endRow = row;
          endCol = column;
        }
      }
    }
  }
  // setter method for the starting row and column based on user input.
  public void setStartLoc(int roomStart){
    int row;
    int column;

    for(row =0; row<14;row++){
      for(column =0; column<18;column++){
        if(grid[row][column] == roomStart){
          startRow = row;
          startCol = column;
        }
      }
    }
  }

  /* UNSURE IF THIS IS NEEDED
  //Getter methods for Current X and Current Y
  public int getcurrentRow(){
    return currentRow;
  }

  public int getcurrentCol(){
    return currentCol;
  }

  // place Start marker
  public void placeStart(){
    grid[startRow][startCol] = 8;
  }
  // place Destination marker
  public void placeDest(){
    grid[endRow][endCol] = 5;
  }

  */

  //Getter method for the map
  public Map getMap() {
    return map;
  }
  //Print method to print the manipulated copy of the map
  public void printMap() {
    map.print();
  }


  /**
  *Creation of the actual path
  */

  /*
  * Checking move validity
  */

  public boolean isMoveValid(int row, int column){
    boolean valid;
    valid = (grid[row][column] > 0 && grid[row][column] != 9 && grid[row][column] != 7);// && floorGrid[row][column]==9);// || //destination
            //floorGrid[row][column] == 8 ||  //start
            //floorGrid[row][column] == 7); //pathalreadytaken
    return (valid);
  }
  // Find the smallest amount of possible moves from point A to Point B
  // THIS DOES NOT ACCOUNT FOR OBSTACLES. Not functional at this point
  public int findShortestDistance(int positionX, int positionY) {
    int moveCounter;
    moveCounter = Math.abs(destY - positionY) + Math.abs(destX - positionX);
    return moveCounter;
  }

  //find the best possible move and returns the direction as a string.
  public String bestMove(int positionX, int positionY, int oneMove){
    int temporaryX = 0;
    int temporaryY = 0;
    String moveString = "a";
    int moveValue = 0;

    if (map.isMoveValid(positionX, positionY + oneMove, map.getCopyGrid())) {
      if (temporaryX == 0 && temporaryY == 0) {
        temporaryX = positionX;
        temporaryY = positionY + oneMove;
        moveValue = findShortestDistance(positionX, positionY + oneMove);
        moveString = "south";
      }

    }

    if(map.isMoveValid(positionX - oneMove, positionY, map.getCopyGrid())) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX - oneMove;
        temporaryY = positionY;
        moveValue = findShortestDistance(positionX - oneMove, positionY);
      }
      if (findShortestDistance(positionX - oneMove,positionY) <= moveValue){
        moveValue = findShortestDistance(positionX - oneMove,positionY);
        temporaryX = positionX - oneMove;
        temporaryY = positionY;
        moveString = "west";
      }
    }

    if(map.isMoveValid(positionX, positionY - oneMove, map.getCopyGrid())) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX;
        temporaryY = positionY - oneMove;
        moveValue = findShortestDistance(positionX, positionY - oneMove);
      }
      if (findShortestDistance(positionX,positionY - oneMove) <= moveValue){
        moveValue = findShortestDistance(positionX,positionY - oneMove);
        temporaryX = positionX;
        temporaryY = positionY - oneMove;
        moveString = "north";
      }
    }

    if(map.isMoveValid(positionX + oneMove, positionY, map.getCopyGrid())) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX + oneMove;
        temporaryY = positionY;
        moveValue = findShortestDistance(positionX + oneMove, positionY);
      }
      if (findShortestDistance(positionX + oneMove,positionY) <= moveValue){
        moveValue = findShortestDistance(positionX + oneMove,positionY);
        temporaryX = positionX + oneMove;
        temporaryY = positionY;
        moveString = "east";
      }
    }

    if (map.isMoveValid(positionX + oneMove, positionY - oneMove, map.getCopyGrid())) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX + oneMove;
        temporaryY = positionY - oneMove;
        moveValue = findShortestDistance(positionX  + oneMove, positionY - oneMove);
      }
      if (findShortestDistance(positionX  + oneMove,positionY - oneMove) <= moveValue){
        moveValue = findShortestDistance(positionX  + oneMove,positionY - oneMove);
        temporaryX = positionX + oneMove;
        temporaryY = positionY - oneMove;
        moveString = "northeast";
      }
    }

    if (map.isMoveValid(positionX - oneMove, positionY - oneMove, map.getCopyGrid())) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX - oneMove;
        temporaryY = positionY - oneMove;
        moveValue = findShortestDistance(positionX - oneMove, positionY + oneMove);
      }
      if (findShortestDistance(positionX - oneMove,positionY - oneMove) <= moveValue){
        moveValue = findShortestDistance(positionX - oneMove,positionY - oneMove);
        temporaryX = positionX - oneMove;
        temporaryY = positionY - oneMove;
        moveString = "northwest";
      }
    }

    if (map.isMoveValid(positionX - oneMove, positionY + oneMove, map.getCopyGrid())) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX - oneMove;
        temporaryY = positionY + oneMove;
        moveValue = findShortestDistance(positionX - oneMove, positionY + oneMove);
      }
      if (findShortestDistance(positionX - oneMove,positionY + oneMove) <= moveValue){
        moveValue = findShortestDistance(positionX - oneMove,positionY + oneMove);
        temporaryX = positionX - oneMove;
        temporaryY = positionY + oneMove;
        moveString = "southwest";
      }


    }
    if (map.isMoveValid(positionX + oneMove, positionY + oneMove)) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX + oneMove;
        temporaryY = positionY + oneMove;
        moveValue = findShortestDistance(positionX + oneMove, positionY + oneMove);
      }
      if (findShortestDistance(positionX + oneMove,positionY + oneMove) <= moveValue){
        moveValue = findShortestDistance(positionX + oneMove,positionY + oneMove);
        temporaryX = positionX + oneMove;
        temporaryY = positionY + oneMove;
        moveString = "southeast";
      }
    }

    return moveString;
  }


  //method to create a path between starting room and destination room.
  public void createPath() {
    int oneMove = 1;
    //char previousMove = ' '; //Either N (north), E (east), S (south), W (west)
    int currentRow = startRow;
    int currentCol = startCol;
    int temporaryRow = currentRow;
    int temporaryCol = currentCol;
    int temporaryRow2 = 0;
    int temporaryCol2 = 0;
    char previousMove = ' '; //Either N (north), E (east), S (south), W (west)
    String moveDirection;

    //loops as long as the current location is not the destination room.
    while(currentRow != endRow || currentCol != endCol){
      moveDirection = bestMove(currentRow, currentCol, oneMove);
      //if (map.grid[currentRow][currentCol] == 5) {
      //System.out.println("At destination");
      //if (temporaryX != temporaryX2 || temporaryY != temporaryY2){
      //temporaryX2 = temporaryX;
      //temporaryY2 = temporaryY;

      //allows this movement of current room east if it is valid and not the previous move
      if (moveDirection.equals("south")){
        map.grid[currentRow][currentCol + oneMove] = 7;
        currentCol += oneMove;
        printMap();
      }
      if (moveDirection.equals("west")){
        map.grid[currentRow - oneMove][currentCol] =7;
        currentRow -= oneMove;
        printMap();
      }
      if (moveDirection.equals("north")){
        map.grid[currentRow][currentCol - oneMove] = 7;
        currentCol -= oneMove;
        printMap();
      }
      if (moveDirection.equals("east")){
        map.grid[currentRow + oneMove][currentCol] =7;
        currentRow += oneMove;
        printMap();
      }
      if (moveDirection.equals("northeast")){
        map.grid[currentRow + oneMove][currentCol - oneMove] = 7;
        currentRow += oneMove;
        currentCol -= oneMove;
        printMap();
      }
      if (moveDirection.equals("northwest")){
        map.grid[currentRow - oneMove][currentCol - oneMove] = 7;
        currentRow -= oneMove;
        currentCol -= oneMove;
        printMap();
      }
      if (moveDirection.equals("southwest")){
        map.grid[currentRow - oneMove][currentCol + oneMove] = 7;
        currentRow -= oneMove;
        currentCol += oneMove;
        printMap();
      }
      if (moveDirection.equals("southeast")){
        map.grid[currentRow + oneMove][currentCol + oneMove] = 7;
        currentRow += oneMove;
        currentCol += oneMove;
        printMap();
      }
      System.out.println("found destination");
    }
  }
}
