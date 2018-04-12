import java.util.ArrayList;

/**
 * Class that finds the shortest path from point A to point B
 * on a single array of integers, avoiding any obstacles
 * pathfinding portion of the program
 * Last Edited by Dayan J.
 * 4 Mar 2018
 */
public class Path {
  private int[][] floorGrid;
  private int[][] copyGrid;
  private int startRoomNum;
  private int endRoomNum;


  /**
   * Constructor requires a grid on which to find a path
   *
   * @param newGrid of a desired grid
   */
  public Path(int[][] newGrid) {
    setFloorGrid(newGrid);
  }

  /**
   * Constructor that will set start and end room numbers on a Grid
   *
   * @param aGrid         containing a 2-d integer grid
   * @param aStartRoomNum a desired start point
   * @param aEndRoomNum   a desired end point
   */
  public Path(int[][] aGrid, int aStartRoomNum, int aEndRoomNum) {
    setFloorGrid(aGrid);
    setStartRoomNum(aStartRoomNum);
    setEndRoomNum(aEndRoomNum);
  }

  /**
   * Copy Constructor
   *
   * @param aCopyPath a desired path to copy
   */
  public Path(Path aCopyPath) {
    setFloorGrid(aCopyPath.getFloorGrid());
    setStartRoomNum(aCopyPath.getStartRoomNum());
    setEndRoomNum(aCopyPath.getEndRoomNum());
  }

  /**
   * Getter method for the grid
   *
   * @return integer two dimensional grid
   */
  public int[][] getFloorGrid() {
    return floorGrid;
  }

  /**
   * Setter Method for the grid
   *
   * @param aGrid the two dimensional integer grid containing the floor plan
   */
  public void setFloorGrid(int[][] aGrid) {
    floorGrid = aGrid;
    copyGrid = aGrid;
  }

  /**
   * Getter and Setter methods for the starting room number
   *
   * @return the start room number
   */
  public int getStartRoomNum() {
    return startRoomNum;
  }

  /**
   * Setter method for the starting room number
   *
   * @param newStart must be a positive integer
   */
  public void setStartRoomNum(int newStart) {
    if (newStart >= 0) {
      startRoomNum = newStart;
    }
  }


  /**
   * Getter method for the endpoint room number
   *
   * @return the endpoint room number
   */
  public int getEndRoomNum() {
    return endRoomNum;
  }

  /**
   * Setter method for the ending room number
   *
   * @param newEnd must be a positive integer
   */
  public void setEndRoomNum(int newEnd) {
    if (newEnd >= 0) {
      endRoomNum = newEnd;
    }
  }

  /**
   * Getter method for the end node
   *
   * @param nodes an Arraylist that of the nodes of the grid
   * @return the node that specifies it is the end location
   */
  public Node getEndNode(ArrayList<Node> nodes) {
    Node endNode = null;
    if (nodes != null) {
      for (Node node : nodes) {
        if (node.getEndNodeVal()) {
          endNode = new Node(node);
        }
      }
    }
    return endNode;
  }

  /**
   * Add node to an ArrayList list of nodes
   *
   * @param nodes ArrayList of nodes
   * @param aNode the node to add to the ArrayList
   */
  public void addNodeToList(ArrayList<Node> nodes, Node aNode) {
    if (aNode != null) {
      nodes.add(new Node(aNode));
    }
  }

  /**
   * Removing a specific node from a list
   *
   * @param nodes   containing the the arraylist of nodes
   * @param newNode that is to be removed from the ArrayList
   * @return an ArrayList with the removed node
   */
  public ArrayList<Node> removeNodeFromList(ArrayList<Node> nodes, Node newNode) {
    ArrayList<Node> copyList = getCopyNodes(nodes);
    ArrayList<Node> removeList = new ArrayList<Node>();
    for (Node node : copyList) {
      if (node.equals(newNode)) {
        removeList.add(node);
      }
    }
    for (Node removeNode : removeList) {
      copyList.remove(removeNode);
    }
    return copyList;
  }

  /**
   * Create a copy of an ArrayList and its items
   *
   * @param nodes the ArrayList to copy
   * @return the copy of the ArrayList
   */
  public ArrayList<Node> getCopyNodes(ArrayList<Node> nodes) {
    ArrayList<Node> copyNodes = new ArrayList<Node>();
    if (nodes != null) {
      for (Node node : nodes) {
        addNodeToList(copyNodes, node);
      }
    }
    return copyNodes;
  }

  /**
   * Determining the node with the lowest distance instance value from the start node
   *
   * @param nodes containing the ArrayList of nodes
   * @return the node with the lowest distance from the start
   */
  public Node getLowestDistanceNode(ArrayList<Node> nodes) {
    Node lowestNode = null;
    Node finalLowestNode = null;
    if (nodes != null) {
      for (Node node : nodes) {
        if (lowestNode != null) {
          if (lowestNode.getStartDistance() > node.getStartDistance()) {
            lowestNode = node;
          }
        } else {
          lowestNode = node;
        }
        finalLowestNode = new Node(lowestNode);
      }
    }
    return finalLowestNode;
  }

  /**
   * From a grid create an array of nodes that can be traversed
   *
   * @param aGrid the grid from which to create an ArrayList of nodes
   * @return the created ArrayList
   */
  public ArrayList<Node> createNodeArray(int[][] aGrid) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    for (int row = 0; row < aGrid.length; row++) {
      for (int column = 0; column < aGrid[row].length; column++) {
        //used for the Node constructor

        if (aGrid[row][column] == 1) {
          addNodeToList(nodes, new Node(row, column, false));
        } else if (floorGrid[row][column] == endRoomNum) {
          addNodeToList(nodes, new Node(row, column, true));
        } else if (floorGrid[row][column] == startRoomNum) {
          addNodeToList(nodes, new Node(row, column, 0));
        }
      }
    }
    return nodes;
  }

  /**
   * Sets the instance variables of the Neighboring nodes to their appropriate
   * value
   *
   * @param initialNode that the node is currently being analyzed
   * @param newNode     which will have its intance variables altered accordingly
   */
  public void setNeighborInstances(Node initialNode, Node newNode) {
    double diagonalMoveWeight = 14;
    double otherMoveWeight = 10;
    double initialNodeDistance = initialNode.getStartDistance();
    double newNodeDistance = newNode.getStartDistance();
    double finalDiagonalDistance = diagonalMoveWeight + initialNodeDistance;
    double finalOtherDistance = otherMoveWeight + initialNodeDistance;
    //Checking Euclidean distance for Neighboring Nodes, includes diagonals
    if (newNode.calcDistance(initialNode) < 2) {
      //Euclidean distance for Diagonal; move weighted lower
      if (newNode.calcDistance(initialNode) > 1) {
        if (newNodeDistance > finalDiagonalDistance) {
          newNode.setStartDistance(finalDiagonalDistance);
          newNode.setConnectedNode(new Node(initialNode));
        }
      }
      //Euclidean distance for Horizontal; move weighted higher
      else {
        if (newNodeDistance > finalOtherDistance) {
          newNode.setStartDistance(finalOtherDistance);
          newNode.setConnectedNode(new Node(initialNode));
        }
      }
    }
  }

  /**
   * Takes the arraylist of nodes and initializes different values
   * for the distance based on the best move
   *
   * @param nodes containing the arraylist of nodes
   * @return an arraylist of nodes with all the appropriate instance
   * variables set to create the shortest path
   */
  private ArrayList<Node> setNodeDistances(ArrayList<Node> nodes) {
    ArrayList<Node> unvisitedNodes = getCopyNodes(nodes);
    Node endNode = getEndNode(unvisitedNodes);
    ArrayList<Node> visitedNodes = new ArrayList<Node>();
    boolean endNodeVisited = false;
    do {
      Node vertex = getLowestDistanceNode(unvisitedNodes);
      if (vertex.equals(endNode)) {
        endNodeVisited = true;
      }
      addNodeToList(visitedNodes, vertex);
      unvisitedNodes = removeNodeFromList(unvisitedNodes, vertex);
      for (Node eachNode : unvisitedNodes) {
        setNeighborInstances(vertex, eachNode);
      }
    } while (!endNodeVisited);


    return getCopyNodes(visitedNodes);
  }

  /**
   * Returns a list of only the fully created path nodes to be used when
   * altering the grid
   *
   * @param visitedNodes containing an ArrayList of nodes
   * @return an ArrayList of nodes that only contain the direct path nodes
   */

  private ArrayList<Node> getConnectedNodes(ArrayList<Node> visitedNodes) {
    ArrayList<Node> shortestPathNodes = new ArrayList<Node>();
    Node currentNode = getEndNode(visitedNodes);
    if (visitedNodes != null) {
      while (currentNode.getConnectedNode() != null) {
        addNodeToList(shortestPathNodes, currentNode);
        currentNode = currentNode.getConnectedNode();
      }
    }
    return getCopyNodes(shortestPathNodes);
  }

  /**
   * Takes an ArrayList of connected nodes forming the shortest path
   * and alters the grid to show the final path
   *
   * @param shortPathNodes containing an ArrayList of the shortest path nodes
   * @return a grid with the altered values showing the shortest path from point a to point b
   */
  private int[][] addPathToGrid(ArrayList<Node> shortPathNodes) {
    for (Node aNode : shortPathNodes) {
      int nodeRow = aNode.getXCoord();
      int nodeCol = aNode.getYCoord();
      if (!aNode.getEndNodeVal()) {
        String direction = aNode.getDirection();
        switch (direction) {
          case "N":
            copyGrid[nodeRow][nodeCol] = 70001;
            break;
          case "NE":
            copyGrid[nodeRow][nodeCol] = 70002;
            break;
          case "NW":
            copyGrid[nodeRow][nodeCol] = 70003;
            break;
          case "S":
            copyGrid[nodeRow][nodeCol] = 70011;
            break;
          case "SE":
            copyGrid[nodeRow][nodeCol] = 70012;
            break;
          case "SW":
            copyGrid[nodeRow][nodeCol] = 70013;
            break;
          case "E":
            copyGrid[nodeRow][nodeCol] = 70020;
            break;
          case "W":
            copyGrid[nodeRow][nodeCol] = 70030;
            break;
        }

      }

    }
    return copyGrid;
  }

  /**
   * Method that will combine other methods into simpler step
   *
   * @return a grid that will show the shortest path on a grid
   */
  public int[][] createPath() {
    ArrayList<Node> gridNodes = createNodeArray(floorGrid);
    return addPathToGrid(getConnectedNodes(setNodeDistances(gridNodes)));
  }
}

