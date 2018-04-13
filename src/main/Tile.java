import java.io.Serializable;

/**
 * Class that represents a single point in the grid
 *
 */

public class Tile implements Serializable{
  private int xCoord;
  private int yCoord;


  /**
   * Constructor used for setting the tile with an x and y coordinate as well as the endTile value
   *
   * @param initX x coordinate integer for a Tile
   * @param initY y coordinate integer for a Tile
   */
  public Tile(int initX, int initY) {
    setXCoord(initX);
    setYCoord(initY);
  }

  /**
   * Copy Constructor for the tile
   *
   * @param copyTile a tile to copy of type Tile
   */
  public Tile(Tile copyTile) {
    setXCoord(copyTile.getXCoord());
    setYCoord(copyTile.getYCoord());
  }

  /**
   * Setter method for the x coordinate for a tile
   *
   * @param newX an x coordinate as an integer that cannot be a negative value
   */
  public void setXCoord(int newX) {
    if (newX >= 0) {
      xCoord = newX;
      //If negative integer for newX, the xCoord is set to 0.
    } else {
      xCoord = 0;
    }
  }

  /**
   * Getter method for the x coordinate of a Tile
   *
   * @return xCoord an x coordinate as an int
   */
  public int getXCoord() {
    return xCoord;
  }

  /**
   * Setter method for the y coordinate
   *
   * @param newY a y coordinate as an integer that cannot be a negative value
   */
  public void setYCoord(int newY) {
    if (newY >= 0) {
      yCoord = newY;
      //If negative integer for newY, the yCoord is set to 0.
    } else {
      yCoord = 0;
    }
  }

  /**
   * Getter method for the y coordinate of a tile.
   *
   * @return yCoord a y coordinate as an int
   */
  public int getYCoord() {
    return yCoord;
  }

  /**
   * Method: Euclidean distance calculator between tiles
   *
   * @param aTile a Tile for which to calculate the distance for
   * @return : the Euclidean distance as a double.
   */
  public double calcDistance(Tile aTile) {
    int xDistance = getXCoord() - aTile.getXCoord(); //distance between the current Tile and another Tile in the x direction.
    int yDistance = getYCoord() - aTile.getYCoord(); //distance between the current Tile and another Tile in the y direction.
    return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance)); // Calculate the Euclidean distance from distance in the x and y direction.
  }

  /**
   * Method to check if the position of another tile is the same as this tile
   *
   * @param aTile for which to compare of type Tile
   * @return equality whether or not the current Tile and the parameter Tile are equivalent in terms of coordinates
   */
  public boolean equals(Tile aTile) {
    boolean equality = false;
    // If the parameter Tile and current Tile have the same x and y coordinate then they have equality.
    if (aTile.getXCoord() == getXCoord() && aTile.getYCoord() == getYCoord()) {
      equality = true;
    }
    return equality;
  }
}
