
/**
 * A class that contains the data for the points on a grid and makes them usable within arraylists for
 * various tasks
 */

public class Node extends Tile {
  private boolean startNodeVal = false;
  private boolean endNodeVal = false;
  private double startDistance = Double.POSITIVE_INFINITY;
  private Node connectedNode = null;


  /**
   * Constructor used when provided with initial node coordinates and distance from the start point.
   *
   * @param initX            the x coordinate of the node as an integer
   * @param initY            the y coordinate of the node as an integer
   * @param newStartDistance the initial distance from the start point as a double
   */
  public Node(int initX, int initY, double newStartDistance) {
    super(initX, initY);
    setStartDistance(newStartDistance);
    setStartNodeVal(true);
  }

  /**
   * Constructor used for setting the node with an x and y coordinate as well as the endnode value
   *
   * @param initX the x coordinate of the node as an integer
   * @param initY the y coordinate of the node as an integer
   * @param val   boolean value whether it is an end point node or not
   */
  public Node(int initX, int initY, boolean val) {
    super(initX, initY);
    setEndNodeVal(val);
  }

  /**
   * Constructor used for setting the node with an x and y coordinate
   *
   * @param initX x coordinate integer
   * @param initY y coordinate integer
   */
  public Node(int initX, int initY) {
    super(initX, initY);
  }

  /**
   * Copy Constuctor for the node
   *
   * @param copyNode a node to copy of type Node.
   */
  public Node(Node copyNode) {
    super(copyNode.getXCoord(), copyNode.getYCoord());
    setStartNodeVal(copyNode.getStartNodeVal());
    setEndNodeVal(copyNode.getEndNodeVal());
    setStartDistance(copyNode.getStartDistance());
    setConnectedNode(copyNode.getConnectedNode());
  }


  /**
   * Setter method for whether the node is a start node
   *
   * @param value boolean value for a node. True if it is the start node.
   */
  public void setStartNodeVal(boolean value) {
    startNodeVal = value;
  }

  /**
   * Getter method for whether node is the start node
   *
   * @return startNodeVal boolean as to whether node is the start node.
   */
  public boolean getStartNodeVal() {
    return startNodeVal;
  }

  /**
   * Setter method for whether the node is a end node
   *
   * @param value boolean value for a node. True if it is the end node.
   */
  public void setEndNodeVal(boolean value) {
    endNodeVal = value;
  }

  /**
   * Getter method for whether node is the end node
   *
   * @return endNodeVal boolean as to whether node is the end node.
   */
  public boolean getEndNodeVal() {
    return endNodeVal;
  }

  /**
   * Getter method for the node's distance from the start node
   *
   * @return startDistance the distance from the start node as a double.
   */
  public double getStartDistance() {
    return startDistance;
  }

  /**
   * Setter method for the distance from the start node
   *
   * @param newDistance distance of the current node from the start node as a double.
   */
  public void setStartDistance(double newDistance) {
    startDistance = newDistance;
  }

  /**
   * Setter method for the nodes that are connected.
   *
   * @param: prevNode a node of type Node that will be set as the connected node.
   */
  public void setConnectedNode(Node prevNode) {
    /* If a previous node exists that was passed in as the parameter,
    create a copy of it is a connected node. */
    if (prevNode != null) {
      connectedNode = new Node(prevNode);
    } else {
      connectedNode = null;
    }
  }

  /**
   * Getter method for the connected node
   *
   * @return newConnectedNode node connected to the current node.
   * @return null if no connected node then return null.
   */
  public Node getConnectedNode() {
    Node newConnectedNode;
    // If a connected node exists, return a copy of it.
    if (connectedNode != null) {
      newConnectedNode = new Node(connectedNode);
    } else {
      newConnectedNode = null;
    }
    return newConnectedNode;
  }

  /**
   * Checks the direction of the movement from the connected node
   * @return currentDirection the direction of travel as a string
   */
  public String getDirection() {
    Node connectedNode = getConnectedNode();
    String currentDirection;
    double nonDiagonalMove = 1;
    /* Check if diagonal.
     If non-directional travel. */
    if (calcDistance(connectedNode) <= nonDiagonalMove) {
      //Check if same column
      if (getYCoord() == connectedNode.getYCoord()) {
        /* Check row.
        If same row and column, then current direction is either north or south. */
        if (getXCoord() < connectedNode.getXCoord()) {
          currentDirection = "N";
        }
        else {
          currentDirection = "S";
        }
      } // If same row and different column, then current direction is either west or east.
      else {
        if (getYCoord() < connectedNode.getYCoord()) {
          currentDirection = "W";
        }
        else {
          currentDirection = "E";
        }
      }
    } // Else current direction of travel is diagonal.
    else {
      if (getXCoord() < connectedNode.getXCoord()) {
        /*Check row.
         If current direction of travel is increasing rows than current direction is northwest
         or northeast. */
        if (getYCoord() < connectedNode.getYCoord()) {
          currentDirection = "NW";
        }
        else {
          currentDirection = "NE";
        }
      } /* Else current direction of travel is decreasing rows and current direction is southwest
        or southeast. */
      else {
        if (getYCoord() < connectedNode.getYCoord()) {
          currentDirection = "SW";
        }
        else {
          currentDirection = "SE";
        }
      }
    }
    return currentDirection;
  }
}
