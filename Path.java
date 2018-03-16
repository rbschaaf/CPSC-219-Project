import java.util.ArrayList;
/**
* Class that finds the shortest path from point A to point B
* on a single array of integers, avoiding any obstacles
* pathfinding portion of the program
* Last Edited by Dayan J.
* 15 Mar 2018
*/
public class Path {
  int[][] floorGrid;
  int[][] copyGrid;
  int startRoomNum;
  int endRoomNum;
  ArrayList<Node> nodes = new ArrayList<Node>();
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
  * Get Start Node
  */
  public Node getStartNode(ArrayList<Node> nodes) {
    Node startNode = null;
    if (nodes != null) {
      for(Node node : nodes) {
        if (node.getStartNodeVal() == true) {
          startNode = new Node(node);
        }
      }
    }
    return startNode;
  }
  /**
  * Get End Node
  */
  public Node getEndNode(ArrayList<Node> nodes) {
    Node endNode = null;
    if (nodes != null) {
      for(Node node : nodes) {
        if (node.getEndNodeVal() == true) {
          endNode = new Node(node);
        }
      }
    }
    return endNode;
  }

  /**
  * Add node to an arraylist list of nodes
  */
  public void addNodeToList(ArrayList<Node> nodes, Node aNode) {
    if (aNode != null) {
      nodes.add(new Node(aNode));
    }
  }

  /**
  * Removing a specific node from a list
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

  /**
  * Sets the instance variables of the Neighboring nodes to their appropriate
  * value
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
      if(newNode.calcDistance(initialNode) > 1) {
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
  * Begin to implement Dijkstra algorithm
  *
  *
  */

  /**
  * Takes the arraylist of nodes and initializes different values
  * for the distance based on the best move
  */
  public ArrayList<Node> setNodeDistances (ArrayList<Node> nodes) {
    int counter = 0;
    ArrayList<Node> unvisitedNodes = getCopyNodes(nodes);
    Node endNode = getEndNode(unvisitedNodes);
    ArrayList<Node> visitedNodes = new ArrayList<Node>();
    boolean endNodeVisited = false;
    do {
        counter += 1;

          Node vertex = getLowestDistanceNode(unvisitedNodes);
          if (vertex.equals(endNode)) {
            endNodeVisited = true;
          }
          //System.out.println(counter);
          addNodeToList(visitedNodes, vertex);
          //ArrayList<Node> removeList = new ArrayList<Node>();
          unvisitedNodes = removeNodeFromList(unvisitedNodes, vertex);
          //System.out.println(unvisitedNodes);
          //System.out.println(visitedNodes);
          for (Node eachNode : unvisitedNodes) {
            //System.out.println(eachNode.getStartDistance());
            setNeighborInstances(vertex, eachNode);
            //System.out.println(eachNode.getStartDistance());
          }
      } while (endNodeVisited != true);


    return getCopyNodes(visitedNodes);
  }

  /**
  * Returns a list of only the fully created path nodes to be used when
  * altering the grid
  */

  public ArrayList<Node> getConnectedNodes(ArrayList<Node> visitedNodes) {
    ArrayList<Node> shortestPathNodes = new ArrayList<Node>();
    Node currentNode = getEndNode(visitedNodes);
    if(visitedNodes != null) {
      while(currentNode.getConnectedNode() != null) {
        addNodeToList(shortestPathNodes, currentNode);
        Node nextNode = currentNode.getConnectedNode();
        currentNode = nextNode;
      }
    }
    return getCopyNodes(shortestPathNodes);
  }

  /**
  * Takes an Arraylist of connected nodes forming the shortest path
  * and alters the grid to show the final path
  */
  public int[][] addPathToGrid(ArrayList<Node> shortPathNodes) {
    for (Node aNode : shortPathNodes) {
      int nodeRow = aNode.getXCoord();
      int nodeCol = aNode.getYCoord();
      if (aNode.getEndNodeVal() == false) {
        copyGrid[nodeRow][nodeCol] = 7;
      }

    }
    return copyGrid;
  }

  /**
  * method that will combine other methods into simpler step
  */
  public int[][] createPath() {
    ArrayList<Node> gridNodes = createNodeArray(floorGrid);
    int[][] finGrid = addPathToGrid(getConnectedNodes(setNodeDistances(gridNodes)));
    return finGrid;
  }
}
