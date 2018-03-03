import java.util.ArrayList;
/**
* Class that finds the shortest path from point A to point B
* on a single array of integers, avoiding any obstacles
* pathfinding portion of the program
* Last Edited by Dayan J.
* 3 Mar 2018
*/
public class Path {
  int[][] floorGrid;
  int[][] copyGrid;
  int startRoomNum;
  int endRoomNum;
  ArrayList<Node> nodes = new ArrayList<Node>();
  boolean pathFound = false;
  /**
  * Contructor requires a grid on which to find a path
  */
  public Path(int[][] newGrid) {
    setFloorGrid(newGrid);
  }

  /**
  * Constructor that will set start and end room numbers on a Grid
  */
  public Path(int[][] aGrid, int aStartRoomNum, int aEndRoomNum) {
    setFloorGrid(aGrid);
    setStartRoomNum(aStartRoomNum);
    setEndRoomNum(aEndRoomNum);
  }


  /**
  * Getter and Setter methods for the Grid
  */
  public int[][] getFloorGrid() {
    return floorGrid;
  }
  public void setFloorGrid(int[][] aGrid) {
    floorGrid = aGrid;
    copyGrid = aGrid;
  }

  /**
  * Getter and Setter methods for the starting room number
  */
  public int getStartRoomNum() {
    return startRoomNum;
  }
  //Must be a postive value
  public void setStartRoomNum(int newStart) {
    if (newStart >= 0) {
      startRoomNum = newStart;
    }
  }

  /*
  * Getter and Setter methods for the endpoint room number
  */
  public int getEndRoomNum() {
    return endRoomNum;
  }
  //Must be a positive value
  public void setEndRoomNum(int newEnd) {
    if (newEnd >= 0) {
      endRoomNum = newEnd;
    }
  }

  /**
  * Add node to the arraylist list of nodes
  */
  public void addNode(Node aNode) {
    nodes.add(new Node(aNode));
  }

  /**
  * Return a list of the nodes
  */
  public ArrayList<Node> getNodes() {
    ArrayList<Node> copyNodes = new ArrayList<Node>();
    if (nodes != null) {
      for(int i = 0; i < nodes.size(); i++) {
        copyNodes.add(new Node(nodes.get(i)));
      }
    }
    return copyNodes;
  }

  /**
  * get the Start node from the ArrayList
  */
  public Node getStartNode() {
    Node startNode = null;
    if (nodes != null) {
      for(int i = 0; i < nodes.size(); i++) {
        if (nodes.get(i).getStartNode() == true){
          startNode = new Node(nodes.get(i));
        }
      }
    }
    return startNode;
  }

  /**
  * From a grid create an array of nodes that are traversable
  */
  public void createNodeArray() {
    for (int row = 0; row < 14;row++){
      for (int column = 0; column < 18; column++){
        //used for the Node constuctor
        boolean endNode = false;

        if (floorGrid[row][column] == 1) {
          addNode(new Node(row, column, endNode));
        }
        else if (floorGrid[row][column] == endRoomNum) {
          endNode = true;
          addNode(new Node(row, column, endNode));
        }

        else if(floorGrid[row][column] == startRoomNum) {
          addNode(new Node(row, column, 0));
        }
      }
    }
  }



  /**
  * Begin to implement Dijkstra algorithm
  *
  *
  */

  //Method to find a distance between points
}
