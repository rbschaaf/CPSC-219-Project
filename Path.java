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
  * Add node to an arraylist list of nodes
  */
  public void addNodeToList(ArrayList<Node> nodes, Node aNode) {
    nodes.add(new Node(aNode));
  }

  public void removeNodeFromList(ArrayList<Node> nodes, Node newNode) {
    for (Node node : nodes) {
      if (node.equals(newNode) == true) {
        nodes.remove(node);
      }
    }
  }

  /**
  * Return a copy Arraylist
  */
  public ArrayList<Node> getCopyNodes(ArrayList<Node> nodes) {
    ArrayList<Node> copyNodes = new ArrayList<Node>();
    if (nodes != null) {
      for(int i = 0; i < nodes.size(); i++) {
        addNodeToList(copyNodes, nodes.get(i));
      }
    }
    return copyNodes;
  }

  /**
  * get the Start node from an ArrayList
  */
  public Node getLowestDistanceNode(ArrayList<Node> nodes) {
    Node lowestNode = null;
    Node finalLowestNode = null;
    if (nodes != null) {
      for(int i = 0; i < nodes.size(); i++) {
        if (lowestNode != null) {
          if (lowestNode.getStartDistance() > nodes.get(i).getStartDistance()) {
            lowestNode = nodes.get(i);
          }
        }
        else {
          lowestNode = nodes.get(i);
        }
        finalLowestNode = new Node(lowestNode);
      }
    }
    return finalLowestNode;
  }

  /**
  * From a grid create an array of nodes that are traversable
  */
  public ArrayList<Node> createNodeArray(int[][] aGrid) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    for (int row = 0; row < 14;row++){
      for (int column = 0; column < 18; column++){
        //used for the Node constuctor
        boolean endNode = false;

        if (aGrid[row][column] == 1) {
          addNodeToList(nodes, new Node(row, column, endNode));
        }
        else if (floorGrid[row][column] == endRoomNum) {
          endNode = true;
          addNodeToList(nodes, new Node(row, column, endNode));
        }

        else if(floorGrid[row][column] == startRoomNum) {
          addNodeToList(nodes, new Node(row, column, 0));
        }
      }
    }
    return nodes;
  }

  public void setNeighborWeightedNodeDistance(Node initialNode, Node newNode) {
    double diagonalMoveWeight = 1/2;
    double otherMoveWeight = 1;
    double initialNodeDistance = initialNode.getStartDistance();
    double newNodeDistance = newNode.getStartDistance();
    double finalDiagonalDistance = diagonalMoveWeight + initialNodeDistance;
    double finalOtherDistance = otherMoveWeight + initialNodeDistance;
    //Checking Euclidean distance for Neighboring Nodes, includes diagonals
    if (newNode.calcDistance(initialNode) > 2) {
      //Euclidean distance for Diagonal; move weighted lower
      if(newNode.calcDistance(initialNode) > 1) {
        if (newNodeDistance > finalDiagonalDistance) {
          newNode.setStartDistance(finalDiagonalDistance);
        }
      }
      //Euclidean distance for Horizontal; move weighted higher
      else {
        if (newNodeDistance > finalOtherDistance) {
          newNode.setStartDistance(finalOtherDistance);
        }
      }
    }
  }


  /**
  * Begin to implement Dijkstra algorithm
  *
  *
  */
  public ArrayList<Node> setNodeDistances (ArrayList<Node> nodes) {
    ArrayList<Node> unvisitedNodes = getCopyNodes(nodes);
    ArrayList<Node> visitedNodes = null;
    boolean endNodeVisited = false;
    while (unvisitedNodes != null) {
      while (endNodeVisited == false) {
        Node vertex = getLowestDistanceNode(nodes);
        removeNodeFromList(unvisitedNodes, vertex);
        addNodeToList(visitedNodes, vertex);
        for (Node eachNode : unvisitedNodes) {
          setNeighborWeightedNodeDistance(vertex, eachNode);
        }
      }
    }
    return visitedNodes;
  }
}
