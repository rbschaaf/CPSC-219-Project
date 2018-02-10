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
  // THIS DOES NOT ACCOUNT FOR OBSTACLES
  public int findShortestDistance(int possibleX, int possibleY) {
    int moveCounter;
    moveCounter = Math.abs(destY - possibleY) + Math.abs(destX - possibleX);
    return moveCounter;
  }

  public void createPath() {
    int oneMove = 1;
    char previousMove = ' '; //Either N (north), E (east), S (south), W (west)
    currentX=startX;
    currentY=startY;
    int tempX=startX;
    int tempY =startY;
    
    while(currentX!=destX || currentY!=destY){

    //if (map.grid[currentX][currentY] == 5) {
      //System.out.println("At destination");

      if ((map.isMoveValid(currentX, currentY + oneMove)) &&
        !(previousMove == 'W')) {
          
          if (findShortestDistance(currentX, currentY) > findShortestDistance(currentX, currentY+oneMove)){
            
            tempY += oneMove;
          }

        }

      else if((map.isMoveValid(currentX - oneMove, currentY)) &&
            !(previousMove == 'S')) {

 if (findShortestDistance(currentX, currentY) > findShortestDistance(currentX-oneMove, currentY)){
            
            tempx -= oneMove;
          }

            }

      else if((map.isMoveValid(currentX, currentY - oneMove)) &&
          !(previousMove == 'E')) {

 if (findShortestDistance(currentX, currentY) > findShortestDistance(currentX, currentY-oneMove)){
            
            tempY -= oneMove;
          }

    }

    else if((map.isMoveValid(currentX + oneMove, currentY)) &&
            !(previousMove == 'N')) {

if (findShortestDistance(currentX, currentY) > findShortestDistance(currentX+oneMove, currentY)){
            
            tempx += oneMove;
          }
    }
      map.grid[tempX][tempY] = 7;
      currentX=tempX;
      currentY=tempY;
  } System.out.println("found destination");

  }
}






  //public void drawPath()
