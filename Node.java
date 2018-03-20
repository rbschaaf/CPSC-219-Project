/**
 * A class that contains the data for the points on a grid and makes them usable within arraylists for
 * various tasks
 */
public class Node {
  private int xcoord;
  private int ycoord;
  private boolean startNodeVal = false;
  private boolean endNodeVal = false;
  private double startDistance = Double.POSITIVE_INFINITY;
  private Node connectedNode = null;


  /**
   * Constructor used when provided with various arguments
   *
   * @param initX            the x coordinate of the node
   * @param initY            the y coordinate of the node
   * @param newStartDistance the initial distance from the start point
   */
  public Node(int initX, int initY, double newStartDistance) {
    setXCoord(initX);
    setYCoord(initY);
    setStartDistance(newStartDistance);
    setStartNodeVal(true);
  }

  /**
   * Constructor used for setting the node with an x and y coordinate as well as the endnode value
   *
   * @param initX xcoordinate integer
   * @param initY ycoordinate integer
   * @param val   boolean value whether it is an end point node or not
   */
  public Node(int initX, int initY, boolean val) {
    setXCoord(initX);
    setYCoord(initY);
    setEndNodeVal(val);
  }

  /**
   * Constructor used for setting the node with an x and y coordinate as well as the endnode value
   *
   * @param initX xcoordinate integer
   * @param initY ycoordinate integer
   */
  public Node(int initX, int initY) {
    setXCoord(initX);
    setYCoord(initY);
  }

  /**
   * Copy Constuctor for the node
   *
   * @param copyNode
   */
  public Node(Node copyNode) {
    setXCoord(copyNode.getXCoord());
    setYCoord(copyNode.getYCoord());
    setStartNodeVal(copyNode.getStartNodeVal());
    setEndNodeVal(copyNode.getEndNodeVal());
    setStartDistance(copyNode.getStartDistance());
    setConnectedNode(copyNode.getConnectedNode());
  }


  /**
   * Setter method for the xcoordinate
   *
   * @param newX an xcoordinate cannot be a negative value
   */
  public void setXCoord(int newX) {
    if (newX >= 0) {
      xcoord = newX;
    } else {
      xcoord = 0;
    }
  }

  /**
   * Getter method for the xcoordinate
   *
   * @return an xcoordinate
   */
  public int getXCoord() {
    return xcoord;
  }

  /**
   * Setter method for the ycoordinate
   *
   * @param newY an ycoordinate cannot be a negative value
   */
  public void setYCoord(int newY) {
    if (newY >= 0) {
      ycoord = newY;
    } else {
      ycoord = 0;
    }
  }

  /**
   * Getter method for the ycoordinate
   *
   * @return a yoordinate
   */
  public int getYCoord() {
    return ycoord;
  }

  /**
   * Setter method for whether the node is a start node
   *
   * @param value boolean
   */
  public void setStartNodeVal(boolean value) {
    startNodeVal = value;
  }

  /**
   * Getter method for whether node is a start node
   *
   * @return start node value boolean
   */
  public boolean getStartNodeVal() {
    return startNodeVal;
  }

  /**
   * Setter method for whether the node is a end node
   *
   * @param value boolean
   */
  public void setEndNodeVal(boolean value) {
    endNodeVal = value;
  }

  /**
   * Getter method for whether node is a end node
   *
   * @return end node value boolean
   */
  public boolean getEndNodeVal() {
    return endNodeVal;
  }

  /**
   * Getter method for the distance from the startnode
   *
   * @return the startnode distance
   */
  public double getStartDistance() {
    return startDistance;
  }

  /**
   * Getter method for the distance from the startnode
   *
   * @param newDistance double
   */
  public void setStartDistance(double newDistance) {
    startDistance = newDistance;
  }

  /**
   * Setter method for the node
   *
   * @param prevNode a node that will be set as the connected node
   */
  public void setConnectedNode(Node prevNode) {
    if (prevNode != null) {
      connectedNode = new Node(prevNode);
    } else {
      connectedNode = null;
    }
  }

  /**
   * Getter method for the connected node
   *
   * @return the connected node
   */
  public Node getConnectedNode() {
    Node newConnectedNode = null;
    if (connectedNode != null) {
      newConnectedNode = new Node(connectedNode);
    } else {
      newConnectedNode = null;
    }
    return newConnectedNode;
  }

  /**
   * Euclidean distance calculator between nodes
   *
   * @param newNode for which to calculate the distance for
   * @return the distance
   */
  public double calcDistance(Node newNode) {
    int xDistance = xcoord - newNode.getXCoord();
    int yDistance = ycoord - newNode.getYCoord();
    return (Math.sqrt((xDistance * xDistance) + (yDistance * yDistance)));
  }

  /**
   * Check is the position of another node is the same as this node
   *
   * @param newNode for which to compare
   * @return whether or not they are equivalent in terms of coordinates
   */
  public boolean equals(Node newNode) {
    boolean equality = false;
    if (newNode.getXCoord() == xcoord && newNode.getYCoord() == ycoord) {
      equality = true;
    }
    return equality;
  }
}
