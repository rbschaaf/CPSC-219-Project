/**This class is the majority of methods that are called. All involved in the
pathfinding portion of the program*/

public class Path {
  private int startY;
  private int startX;
  private int destY;
  private int destX;
  private int currentX;
  private int currentY;
  private Map map;



/** Constructors
*
* Create a map to manipulate in path formation
*/
  public Path(Map newMap){
    map = new Map(newMap);
  }

  public Path() {}

  //setter for map creates a new copy as well
  public void setMap(Map newMap) {
    map = new Map(newMap);
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
  //getter and setter methods for destX and destY
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

  //Getter methods for Current X and Current Y
  public int getCurrentX(){
    return currentX;
  }

  public int getCurrentY(){
    return currentY;
  }

  // place Start marker
  public void placeStart(){
    map.grid[startX][startY] = 8;
  }
  // place Destination marker
  public void placeDest(){
    map.grid[destX][destY] = 5;
  }

  // setter method for the destination location based on user input
  public void setDestLoc(int roomDest){
    int row;
    int column;

    for(row =0; row<14;row++){
      for(column =0; column<18;column++){
        if(map.grid[row][column]==roomDest){
          destX = row;
          destY = column;
        }
      }
    }
  }
  // setter method for the starting location based on user input.
  public void setStartLoc(int roomStart){
    int row;
    int column;

    for(row =0; row<14;row++){
      for(column =0; column<18;column++){
        if(map.grid[row][column] == roomStart){
          startX = row;
          startY = column;
        }
      }
    }
  }
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

    if (map.isMoveValid(positionX, positionY + oneMove)) {
      if (temporaryX == 0 && temporaryY == 0){
        temporaryX = positionX;
        temporaryY = positionY + oneMove;
        moveValue = findShortestDistance(positionX, positionY + oneMove);
        moveString = "south";
      }

    }

    if(map.isMoveValid(positionX - oneMove, positionY)) {
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

    if(map.isMoveValid(positionX, positionY - oneMove)) {
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

    if(map.isMoveValid(positionX + oneMove, positionY)) {
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

    if (map.isMoveValid(positionX + oneMove, positionY - oneMove)) {
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

    if (map.isMoveValid(positionX - oneMove, positionY - oneMove)) {
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

    if (map.isMoveValid(positionX - oneMove, positionY + oneMove)) {
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
    currentX=startX;
    currentY=startY;
    int temporaryX = currentX;
    int temporaryY = currentY;
    int temporaryX2 = 0;
    int temporaryY2 = 0;
    char previousMove = ' '; //Either N (north), E (east), S (south), W (west)
    String moveDirection;

    //loops as long as the current location is not the destination room.
    while(currentX!=destX || currentY!=destY){
      moveDirection = bestMove(currentX, currentY, oneMove);
      //if (map.grid[currentX][currentY] == 5) {
      //System.out.println("At destination");
      //if (temporaryX != temporaryX2 || temporaryY != temporaryY2){
      //temporaryX2 = temporaryX;
      //temporaryY2 = temporaryY;

      //allows this movement of current room east if it is valid and not the previous move
      if (moveDirection.equals("south")){
        map.grid[currentX][currentY + oneMove] = 7;
        currentY += oneMove;
        printMap();
      }
      if (moveDirection.equals("west")){
        map.grid[currentX - oneMove][currentY] =7;
        currentX -= oneMove;
        printMap();
      }
      if (moveDirection.equals("north")){
        map.grid[currentX][currentY - oneMove] = 7;
        currentY -= oneMove;
        printMap();
      }
      if (moveDirection.equals("east")){
        map.grid[currentX + oneMove][currentY] =7;
        currentX += oneMove;
        printMap();
      }
      if (moveDirection.equals("northeast")){
        map.grid[currentX + oneMove][currentY - oneMove] = 7;
        currentX += oneMove;
        currentY -= oneMove;
        printMap();
      }
      if (moveDirection.equals("northwest")){
        map.grid[currentX - oneMove][currentY - oneMove] = 7;
        currentX -= oneMove;
        currentY -= oneMove;
        printMap();
      }
      if (moveDirection.equals("southwest")){
        map.grid[currentX - oneMove][currentY + oneMove] = 7;
        currentX -= oneMove;
        currentY += oneMove;
        printMap();
      }
      if (moveDirection.equals("southeast")){
        map.grid[currentX + oneMove][currentY + oneMove] = 7;
        currentX += oneMove;
        currentY += oneMove;
        printMap();
      }
      System.out.println("found destination");
    }
  }
}
