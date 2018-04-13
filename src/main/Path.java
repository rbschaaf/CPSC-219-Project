import resources.Constants;

import java.util.ArrayList;

/**
 * Class that finds the shortest path from point A to point B
 * on a single array of integers, avoiding any obstacles.
 * Pathfinding portion of the program.
 */

public class Path {
  private int[][] floorGrid;
  private int[][] copyGrid;
  private int startRoomNum;
  private int endRoomNum;


  /**
   * Constructor requires a grid on which to find a path
   *
   * @param newGrid of a desired grid of int[][].
   */
  public Path(int[][] newGrid) {
    setFloorGrid(newGrid);
  }

  /**
   * Constructor that will set start and end room numbers on a Grid
   *
   * @param aGrid         containing a 2-d integer grid (int[][])
   * @param aStartRoomNum a desired start point as an integer
   * @param aEndRoomNum   a desired end pointas an integer
   */
  public Path(int[][] aGrid, int aStartRoomNum, int aEndRoomNum) {
    setFloorGrid(aGrid);
    setStartRoomNum(aStartRoomNum);
    setEndRoomNum(aEndRoomNum);
  }

  /**
   * Copy Constructor to copy a path.
   *
   * @param aCopyPath a desired path to copy as type Path
   */
  public Path(Path aCopyPath) {
    setFloorGrid(aCopyPath.getFloorGrid());
    setStartRoomNum(aCopyPath.getStartRoomNum());
    setEndRoomNum(aCopyPath.getEndRoomNum());
  }

  /**
   * Getter method for the grid
   *
   * @return floorGrid integer two dimensional grid (int[][])
   */
  public int[][] getFloorGrid() {
    return floorGrid;
  }

  /**
   * Setter Method for the grid
   *
   * @param aGrid the two dimensional integer grid containing the floor plan int[][]
   */
  public void setFloorGrid(int[][] aGrid) {
    floorGrid = aGrid;
    copyGrid = aGrid;
  }

  /**
   * Getter method for the starting room number
   *
   * @return startRoomNum the starting room number as an integer.
   */
  public int getStartRoomNum() {
    return startRoomNum;
  }

  /**
   * Setter method for the starting room number
   *
   * @param newStart starting room number. Must be a positive integer
   */
  public void setStartRoomNum(int newStart) {
    if (newStart >= 0) {
      startRoomNum = newStart;
    }
  }


  /**
   * Getter method for the endpoint room number
   *
   * @return endRoomNum the endpoint room number as an integer
   */
  public int getEndRoomNum() {
    return endRoomNum;
  }

  /**
   * Setter method for the ending room number
   *
   * @param newEnd the ending room number. Must be a positive integer
   */
  public void setEndRoomNum(int newEnd) {
    if (newEnd >= 0) {
      endRoomNum = newEnd;
    }
  }

  /**
   * Getter method for the end node
   *
   * @param nodes an Arraylist of the nodes of the grid as type ArrayList<Node>
   * @return endNode the node as type Node that specifies it is the end location
   */
  public Node getEndNode(ArrayList<Node> nodes) {
    Node endNode = null;
    if (nodes != null) {
      // Iterate through the nodes Arraylist passed in as a parameter until the end node is found.
      for (Node node : nodes) {
        if (node.getEndNodeVal()) {
          endNode = new Node(node);
        }
      }
    }
    return endNode;
  }

  /**
   * Method to add a node to an ArrayList list of nodes
   *
   * @param nodes ArrayList of nodes as type ArrayList<Node>.
   * @param aNode the node of type Node to add to the ArrayList
   */
  public void addNodeToList(ArrayList<Node> nodes, Node aNode) {
    // If the node passed in as a parameter exists add it to the Arraylist of nodes.
    if (aNode != null) {
      nodes.add(new Node(aNode));
    }
  }

  /**
   * Method for removing a specific node from an Arraylist.
   *
   * @param nodes   arraylist of nodes of type ArrayList<Node> that contains the node desired to be removed.
   * @param newNode the node of type Node that is to be removed from the ArrayList
   * @return copyList an ArrayList of type ArrayList<Node> after the node has been removed.
   */
  public ArrayList<Node> removeNodeFromList(ArrayList<Node> nodes, Node newNode) {
    // Copy the Arraylist passed in as a parameter.
    ArrayList<Node> copyList = getCopyNodes(nodes);
    // an Arraylist containing the node that wants to be removed.
    ArrayList<Node> removeList = new ArrayList<Node>();
    // Iterate through the copy of the arraylist passed in as a parameter.
    for (Node node : copyList) {
      // If a node is the node you want removed, add it to the arraylist of nodes to be removed.
      if (node.equals(newNode)) {
        removeList.add(node);
      }
    }
    /* Iterate through the arraylist of nodes to be removed and remove its nodes
    from the copy of the arraylist passed in as a parameter. */
    for (Node removeNode : removeList) {
      copyList.remove(removeNode);
    }
    return copyList;
  }

  /**
   * Method to create a copy of an ArrayList and its items
   *
   * @param nodes the ArrayList to copy as type ArrayList<Node>
   * @return copyNodes the copy of the ArrayList of Nodes as type ArrayList<Node>
   */
  public ArrayList<Node> getCopyNodes(ArrayList<Node> nodes) {
    ArrayList<Node> copyNodes = new ArrayList<Node>();
    /* If the arraylist of nodes passed in as a parameter exists,
    iterate through it and add each node to the copy arraylist.*/
    if (nodes != null) {
      for (Node node : nodes) {
        addNodeToList(copyNodes, node);
      }
    }
    return copyNodes;
  }

  /**
   * Method determining the node with the lowest distance instance value from the start node
   *
   * @param nodes containing the ArrayList of nodes as type ArrayList<Node>
   * @return finalLowestNode the node as type Node with the lowest distance from the start
   */
  public Node getLowestDistanceNode(ArrayList<Node> nodes) {
    Node lowestNode = null;
    Node finalLowestNode = null;
    /* If arraylist of nodes passed in as a parameter exists,
    iterate through the list updating which node has the lowest distance from the start node. */
    if (nodes != null) {
      for (Node node : nodes) {
        if (lowestNode != null) {
          // If current iterated node is closer to the start node, update the lowestNode to this node.
          if (lowestNode.getStartDistance() > node.getStartDistance()) {
            lowestNode = node;
          }
        } else {
          lowestNode = node;
        }
        /*The finalLowestNode to be returned is a copy of the final lowestNode after
        iterating through the node arraylist.*/
        finalLowestNode = new Node(lowestNode);
      }
    }
    return finalLowestNode;
  }

  /**
   * Method: From a grid create an array of nodes that can be traversed.
   * Hallway, start room, and end room are the only locations that can be traversed.
   *
   * @param aGrid the grid (int[][]) from which to create an ArrayList of nodes
   * @return nodes is the created ArrayList of nodes as type ArrayList<Node>
   */
  public ArrayList<Node> createNodeArray(int[][] aGrid) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    // Go through each coordinate of the grid passed in as a parameter.
    for (int row = 0; row < aGrid.length; row++) {
      for (int column = 0; column < aGrid[row].length; column++) {
        /*used for the Node constructor.
        If a coordinate's value is that of the hallway, add it to the arraylist of nodes. */
        if (aGrid[row][column] == Constants.HALL) {
          addNodeToList(nodes, new Node(row, column, false));
          // Else if the coordinate's value is that of end room number, set it as the destination node.
        } else if (floorGrid[row][column] == endRoomNum) {
          addNodeToList(nodes, new Node(row, column, true));
          // Else if the coordinate's value is that of start room number, set it as the starting node.
        } else if (floorGrid[row][column] == startRoomNum) {
          addNodeToList(nodes, new Node(row, column, 0));
        }
      }
    }
    return nodes;
  }

  /**
   * Method sets the instance variables of the Neighboring nodes to their appropriate
   * value.
   *
   * @param initialNode the node of type Node that is currently being analyzed
   * @param newNode     object type Node which will have its intance variables altered accordingly. Potential next node to move to.
   */
  public void setNeighborInstances(Node initialNode, Node newNode) {
    double diagonalMoveWeight = 14; //Distance values used in calculating the cost and benefit of potential next moves when traversing the nodes.
    double otherMoveWeight = 10;
    double initialNodeDistance = initialNode.getStartDistance(); //Current node's distance from the start node.
    double newNodeDistance = newNode.getStartDistance(); //Potential next node's distance from the start node.
    double finalDiagonalDistance = diagonalMoveWeight + initialNodeDistance; //Total distance travelled if next move is diagonal.
    double finalOtherDistance = otherMoveWeight + initialNodeDistance; //Total distance travelled if next move is any direction other than diagonal.
    //Checking Euclidean distance for Neighboring Nodes, includes diagonals
    if (newNode.calcDistance(initialNode) < 2) {
      //Euclidean distance for Diagonal; move weighted lower
      if (newNode.calcDistance(initialNode) > 1) {
        //If new move's node's distance is greater than the current total distance, update the distance from the start node and node connections.
        if (newNodeDistance > finalDiagonalDistance) {
          newNode.setStartDistance(finalDiagonalDistance);
          newNode.setConnectedNode(new Node(initialNode));
        }
      }
      //Euclidean distance for Horizontal; move weighted higher
      else {
        //If new move's node's distance is greater than the current total distance, update the distance from the start node and node connections.
        if (newNodeDistance > finalOtherDistance) {
          newNode.setStartDistance(finalOtherDistance);
          newNode.setConnectedNode(new Node(initialNode));
        }
      }
    }
  }

  /**
   * Method takes the arraylist of nodes and initializes different values
   * for the distance based on the best move
   *
   * @param nodes containing the arraylist of nodes as type ArrayList<Node>. Nodes that have not been traversed through.
   * @return visitedNodes an arraylist of nodes with all the appropriate instance
   * variables set to create the shortest path
   */
  public ArrayList<Node> setNodeDistances(ArrayList<Node> nodes) {
    // copy the arraylist of nodes passed in as an arguement.
    ArrayList<Node> unvisitedNodes = getCopyNodes(nodes);
    Node endNode = getEndNode(unvisitedNodes); //get the end node from the arraylist passed in.
    ArrayList<Node> visitedNodes = new ArrayList<Node>();
    boolean endNodeVisited = false;
    /* Loop through the copy of the arraylist passed in as an arguement.
    Find the one with the lowest distance to the end node.
    Remove each node from the unvisitedNodes arraylist and add it the visitedNodes arraylist.
    Continue this until the end node is reached. */
    do {
      // Find the lowest distance node to the end node.
      Node vertex = getLowestDistanceNode(unvisitedNodes);
      // If end node is reached the loop will end on the current iteration.
      if (vertex.equals(endNode)) {
        endNodeVisited = true;
      }
      // Add current iteration node to the visited list and remove from the unvisited list of nodes.
      addNodeToList(visitedNodes, vertex);
      unvisitedNodes = removeNodeFromList(unvisitedNodes, vertex);
      // Iterate through the arraylist passed in as an arguement and set each node's neighbours' valuess.
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
   * @param visitedNodes containing an ArrayList of nodes of type ArrayList<Node> that have been traversed through.
   * @return shortestPathNodes an ArrayList of nodes of type ArrayList<Node> that only contain the direct path nodes from the start to end node.
   */

  public ArrayList<Node> getConnectedNodes(ArrayList<Node> visitedNodes) {
    ArrayList<Node> shortestPathNodes = new ArrayList<Node>();
    Node currentNode = getEndNode(visitedNodes); //Start with the end node.
    if (visitedNodes != null) {
      // Loop through working back from the end node to the start node through each connected node getting the shortest path.
      while (currentNode.getConnectedNode() != null) {
        /* Add the current node to the shortest path before setting the current node to the next node
        as it continues working back to the start.*/
        addNodeToList(shortestPathNodes, currentNode);
        currentNode = currentNode.getConnectedNode();
      }
    }
    return getCopyNodes(shortestPathNodes);
  }

  /**
   * Method takes an ArrayList of connected nodes forming the shortest path
   * and alters the grid to show the final path
   *
   * @param shortPathNodes containing an ArrayList of the shortest path nodes as type ArrayList<Node>
   * @return copyGrid a grid (int[][]) with the altered values showing the shortest path from the start node to end node.
   */
  public int[][] addPathToGrid(ArrayList<Node> shortPathNodes) {
    // Iterate through the shortest path getting the coordinates of each node from the arraylist passed in as an arguement.
    for (Node aNode : shortPathNodes) {
      int nodeRow = aNode.getXCoord();
      int nodeCol = aNode.getYCoord();
      // If currently iterated node is not the end node.
      if (!aNode.getEndNodeVal()) {
        // get the current direction of travel of the current node and set the node's value accordingly.
        String direction = aNode.getDirection();
        switch (direction) {
          case "N":
            copyGrid[nodeRow][nodeCol] = Constants.NPATH;
            break;
          case "NE":
            copyGrid[nodeRow][nodeCol] = Constants.NEPATH;
            break;
          case "NW":
            copyGrid[nodeRow][nodeCol] = Constants.NWPATH;
            break;
          case "S":
            copyGrid[nodeRow][nodeCol] = Constants.SPATH;
            break;
          case "SE":
            copyGrid[nodeRow][nodeCol] = Constants.SEPATH;
            break;
          case "SW":
            copyGrid[nodeRow][nodeCol] = Constants.SWPATH;
            break;
          case "E":
            copyGrid[nodeRow][nodeCol] = Constants.EPATH;
            break;
          case "W":
            copyGrid[nodeRow][nodeCol] = Constants.WPATH;
            break;
        }

      }

    }
    return copyGrid;
  }

  /**
   * Method that will combine other methods into simpler step to be called.
   *
   * @return gridNodes a grid that will show the shortest path on a grid as int[][]
   */
  public int[][] createPath() {
    // Create an arraylist of the nodes for the floors grid.
    ArrayList<Node> gridNodes = createNodeArray(floorGrid);
    /* Set the node distances traversing the arraylist of nodes, connect the nodes, 
    and then add the shortest path to the grid.*/
    return addPathToGrid(getConnectedNodes(setNodeDistances(gridNodes)));
  }
}
