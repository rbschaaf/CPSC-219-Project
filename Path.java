import java.util.ArrayList;

/**This class finds the shortest route between a starting location and ending location on a
2D array. This is a non-functioning attempt and was based on walkthrough tutorial videos found at
https://www.youtube.com/watch?v=aKYlikFAV4k&t=1025s and https://www.youtube.com/watch?v=EaZxUCWAjb0.
He also posted his code on GitHub, which people have been updating https://github.com/CodingTrain/AStar.
This task is very complex and the video I was following were for JavaScript. I ran into problems early
that I could not solve with many many hours of work to go. Common ones were dealing with Reference and
primitive types. I put these parts in brackets. Many things would need to be initialized properly.
Note: A* algorithm uses the equation F=G+H for finding spot of best value, where we want F to be the lowest
number possible. H is a calculation of how far away the spot would be from the end if it moved to that spot
it is considering. G is the cost of movement to that next spot added to the cost of moving to that spot through
all previous spots on that route.
By Riley Schaaf. Last updated 17:45 February 7, 2018*/

public class Path{
  /**private static ArrayList<String> open;
  private static  ArrayList<String> closed;
  private static int COSTMOVEADJACENT = 10;
  private static int COSTMOVEDIAGONAL  = 14;
  private static int VALUESQUARESFROMEND = 10;
  private static boolean validPath;
  private static int rowStart;
  private static int colStart;
  private static int rowEnd;
  private static int colEnd;*/
  private  static int COLS=5;
  private  static int ROWS=5;
  private static int f;
  private static int g;
  private static int h;
  private static ArrayList<Path> openSet;
  private static ArrayList<Path> closedSet;
  private static int startRow;
  private static int endRow;
  private static int startCol;
  private static int endCol;





//Constructor to put value into each Spot on grid. With the F, G, and H values initialized
  public void createSpot(){
    this.f =0;
    this.g=0;
    this.h=0;
    this.neighbours = []; //keeps track of each of its neighbouring nodes
  }

//He is building a method so each Spot will add its 8 neighbours.
  public static void addNeighbours(grid){
    int x = this.i;
    int y = this.j;
    if (x<COLS-1){
      this.neighbours.add(grid[x+1][y]);
    }
    if (x>0){
      this.neighbours.add(grid[x-1][y]);
    }
    if (y<ROWS-1){
    this.neighbours.add(grid[x][y+1]);
    }
    if (y>0){
    this.neighbours.add(grid[x][y-1]);
    }
    if (x<COLS-1 && y>0){
    this.neighbours.add(grid[x+1][y-1]);
    }
    if (x< COLS-1 && y>0){
    this.neighbours.add(grid[x+1][y-1]);
    }
    if (x >0  && y< ROWS-1){
    this.neighbours.add(grid[x-11][y+1]);
    }
    if (x< COLS-1 && y<ROWS-1){
    this.neighbours.add(grid[x+1][y+1]);
    }
  }

//Trying to create a starting location. Just starting with top left corner for now.
  public static void setStart(){
    this.startRow=0;
    this.startCol=0;
  }

  //Trying to return starting location
  public static int[] getStart(){
    int[] start={startRow, startCol};
    return start;
  }


//Trying to create an end location. Just starting with bottom right corner for now
  public void setEnd(){
    this.endRow=ROWS-1;
    this.endCol=COLS-1;
  }

  // Trying to return an end location
  public static int[] getEnd(){
    int[] end = {endRow, endCol};
    return end;
  }

  //Add node to openSet
  public static void addNode(Path node){
    openSet.add(node);
  }

  //Remove from openSet after it has been added to closedSet
  public void removeFromArray(ArrayList<Path> newOpenSet, Path nodeToBeRemoved){
    for (int i = newOpenSet.length-1; i>=0; i--){
      if (newOpenSet[i] == nodeToBeRemoved){
        newOpenSet.splice(i,i+1);
      }
    }
  }

  //Calculation performed to find the distance from the endpoint the space will be.
  public static int heuristic((position variable) newNeighbour, (position variable) endPoint){
    int distance = abs(newNeighbour.x - endPoint.x) + abs(newNeighbour.y - endPoint.y);
    return distance;
  }


  public static void main(String[] args){
    //creating a grid
    Path[][] grid=new Path[COLS][ROWS];

//Creating a spot for every cell in the grid to allow values to be applied.
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j].createSpot();
      }
    }
    // initializing all the values for the neighbouring spots.
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j].addNeighbours(grid);
      }
    }

    //calling a method to have the starting point.
    grid.setStart();
    Path startPoint = grid.getStart;
    openSet.add(startPoint); //Alwasy want to add the starting point to the open list.

    //Looping through openList trying to find best move or the end.
    if (openSet.length>0){
      //we can keep going
      int lowestIndex=0;
      //looping through all of the spots in the openList
      for (int i =0; i<openSet.length; i++){
        //Supposed to be seeing if each spot is the spot with the lowest f score
        if(openSet[i].f < openSet[lowestIndex].f){
          lowestIndex=i;
        }
      }
      //Creating a variable that is way of referencing the best move.
      (variable type i am unsure) current = openSet[lowestIndex];
      //If successful path is found to the end point, it is supposed to work backwards from the endPoint
      //through all of the successful points. This was done with JavaScript methods. Could probably use
      //the closedList for it.
      if (current == end){
        Path[][] foundPath =new Path[][];
        (variable type) temp = current;
        foundPath.add(temp);
        //Connecting to each position before it add adding it to the list to get the successful path.
        while (temp.previous){
          foundPath.add(temp.previous);
          temp = temp.previous;
        }
        System.out.println("The end was found");
      }//if end is not found, add the current spot to the closedSet and remove it from the OpenSet
      else{
        removeFromArray(openSet,current);
        closedSet.add(current);
        //Goal here is to get the neighbours and see if it would be a better spot
        (some variable type that I dont know) neighbours = current.neighbours;
        for (int i = 0; i<neighbours.length; i++){
          (another variable) neighbour = neighbours[i];
          //Can control here if a new spot would be a valid place to move.
          if (!closedSet.includes(neighbour) && !neighbour.(obstacle that we decide)){
            (variable) tempG = current.g + 1;

            //Should probably be an arraylist to be modifiable
            //Deciding if the new spot is a better spot than before.
            boolean newPath = false;
            if (openSet.includes(neighbour)){
              if (tempG<neighbour.g){
                neighbour.g = tempG;
                newPath = true;
              }
            }else{
              nighbour.g = tempG;
              newPath = true;
              openSet.add(neighbour);
            }
            if (newPath) {}
              neighbour.h = heuristic(neighbour,end);
              neighbour.f = neighbour.g + neighbour.h;
              neighbour.previous = current;
            }
          }

        }//No path ever found
        else{
          System.out.println("No path was found.")
        }



  //This block of code for printing the array was from https://stackoverflow.com/questions/20960055/printing-a-2d-array-in-java-like-a-table:
    for (int a = 0; a < grid.length; a++) {
          for (int b = 0; b < grid[a].length; b++) {
              System.out.print(grid[a][b] + " ");
          }
          System.out.println();
        }
      }
    }

    //To display the found successful path.
    for (int i=0; i<foundPath.lenght; i++){
      //path[i].change to show what is successful
    }
  }
}




/**Have an open arraylist where loop to check each possible square to see what has lowest
F score according F=G+H, where G is movement cost to get to the spot along the path from the beginning
and H is the cost of the distance from the square to the final destination. The lowest F score move is
put into cloosed list.*/

/**
    At initialization add the starting location to the open list and empty the closed list
    While there are still more possible next steps in the open list and we haven’t found the target:
        Select the most likely next step (based on both the heuristic and path costs)
        Remove it from the open list and add it to the closed
        Consider each neighbor of the step. For each neighbor:
            Calculate the path cost of reaching the neighbor
            If the cost is less than the cost known for this location then remove it from the open or closed lists (since we’ve now found a better route)
            If the location isn’t in either the open or closed list then record the costs for the location and add it to the open list (this means it’ll be considered in the next search). Record how we got to this location
*/
/**  public static void setStart(ArrayList rowStart){
    open.add(rowStart);
  }

  public static void main(String[] args){
    int[][] test=new int[][]{{1,1,1,1,1,1},
                              {1,0,1,1,1,1},
                              {1,1,1,1,1,1},
                              {1,1,1,1,1,1},
                              {1,1,1,1,0,1},
                              {1,1,1,1,1,1},};
    rowStart=1;
    colStart=1;
    open.setStart(rowStart);
    //This block of code for printing the array was from https://stackoverflow.com/questions/20960055/printing-a-2d-array-in-java-like-a-table
    for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[i].length; j++) {
                System.out.print(test[i][j] + " ");
            }
            System.out.println();
          }
  }
}*/
