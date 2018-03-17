public class Node {
  private int xcoord;
  private int ycoord;
  private boolean startNodeVal = false;
  private boolean endNodeVal = false;
  private double startDistance = Double.POSITIVE_INFINITY;
  private Node connectedNode = null;


  /**
  * Constructor used for start node where startDistance initialized at 0
  * and the boolean for the startNode is set to true
  */
  public Node(int initX, int initY, double newStartDistance) {
    setXCoord(initX);
    setYCoord(initY);
    setStartDistance(newStartDistance);
    setStartNodeVal(true);
  }

  /**
  * Constructor used for Hallways and the End node where boolean
  * for the endNode is set to true
  */
  public Node(int initX, int initY, boolean val) {
    setXCoord(initX);
    setYCoord(initY);
    setEndNodeVal(val);
  }


  public Node(Node copyNode) {
    setXCoord(copyNode.getXCoord());
    setYCoord(copyNode.getYCoord());
    setStartNodeVal(copyNode.getStartNodeVal());
    setEndNodeVal(copyNode.getEndNodeVal());
    setStartDistance(copyNode.getStartDistance());
    setConnectedNode(copyNode.getConnectedNode());
  }
  
  public Node(int anXCoord, int aYCoord){
    xcoord = anXCoord;
    ycoord = aYCoord;
  }

  /*
  * Getter and Setters
  */

  public void setXCoord(int newX) {
    if (newX >= 0) {
      xcoord = newX;
    }
    else {
      xcoord = 0;
    }
  }
  public int getXCoord() {
    return xcoord;
  }

  public void setYCoord(int newY) {
    if (newY >= 0) {
      ycoord = newY;
    }
    else {
      ycoord = 0;
    }
  }
  public int getYCoord() {
    return ycoord;
  }

  public void setStartNodeVal(boolean value) {
    startNodeVal = value;
  }
  public boolean getStartNodeVal() {
    return startNodeVal;
  }

  public void setEndNodeVal(boolean value) {
    endNodeVal = value;
  }
  public boolean getEndNodeVal() {
    return endNodeVal;
  }


  public double getStartDistance() {
    return startDistance;
  }
  public void setStartDistance(double newDistance) {
    startDistance = newDistance;
  }

  public void setConnectedNode(Node prevNode) {
    if (prevNode != null) {
      connectedNode = new Node(prevNode);
    }
    else {
      connectedNode = null;
    }
  }

  public Node getConnectedNode() {
    Node newConnectedNode = null;
    if (connectedNode != null) {
      newConnectedNode = new Node(connectedNode);
    }
    else {
      newConnectedNode = null;
    }
    return newConnectedNode;
  }


  public double calcDistance(Node newNode) {
    int xDistance = xcoord - newNode.getXCoord();
    int yDistance = ycoord - newNode.getYCoord();
    return (Math.sqrt((xDistance*xDistance) + (yDistance*yDistance)));
  }

  public boolean equals(Node newNode) {
    boolean equality = false;
    if (newNode.getXCoord() == xcoord && newNode.getYCoord() == ycoord) {
      equality = true;
    }
    return equality;
  }
}