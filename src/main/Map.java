import resources.Constants;
import java.io.Serializable;

/**
 * Class for the creations of the maps, sizing of the maps, and contains the path restriction for the pathfinding.
 */

public class Map implements Serializable{

  private FloorPlans currentFloorPlan = new FloorPlans();
  private Building currentBuilding;
  private int startX;
  private int startY;
  private int endX;
  private int endY;

  /**
  * Constructor with a provided FloorPlans.
  * @param newFloorPlan a floor plan of type FloorPlans.
  */
  public Map (FloorPlans newFloorPlan){
    currentFloorPlan = newFloorPlan;
  }



  /**
  * Default constructor for Map
  */
  public Map(){}

  /**
  * Method that sets the current floor plan.
  * @param newFloorPlan the currently being used floor plan as type FloorPlans.
  */
  public void setCurrentFloorPlan(FloorPlans newFloorPlan){
    currentFloorPlan = new FloorPlans(newFloorPlan);
  }

  /**
  * Method to get the current building associated with the map.
  * @return : a copy of the currentBuilding of type Building.
  */
  public Building getCurrentBuilding(){
    return new Building(currentBuilding);
  }

  /**
  * Method that sets the current building.
  * @param : newBuilding is the currently being used building as type Building.
  */
  public void setCurrentBuilding(Building newBuilding){
    currentBuilding = new Building(newBuilding);
  }

  /**
  * Method that gets the current floor plan.
  * @return : a copy of the current floor plan as type FloorPlans.
  */
  public FloorPlans getCurrentFloorPlan(){
    return new FloorPlans(currentFloorPlan);
  }

  /**
  * Printing method for the floor plan as a 2-dimensional grid to print to the console.
  */
  public void printGrid() {
    // Loop through each coordinate of the 2D grid.
    for (int row = 0; row < currentFloorPlan.getRowLength(); row++) {
      for (int column = 0; column <currentFloorPlan.getColLength(row); column++) {
        // Print the values of the current coordinate 5 spaces wide.
        System.out.printf("%5d", currentFloorPlan.getGrid()[row][column]);
      }
      System.out.println();
    }
  }

  /**
  * Method that sets the coordinate values startX and startY for the starting room.
  * @param : aFP is a floor plan of type FloorPlans.
  * @param : startRoom the room number of the start room as an integer.
  */
  public void setStartValues(FloorPlans aFP,int startRoom){
    // Loop through each position on the floor plan.
    for(int row=0;row<aFP.getRowLength();row++){
      for(int col=0;col<aFP.getColLength(row);col++){
        /* If the current position on the floorplan is equal to the starting room number,
        set the current x and y coordinates. */
        if(aFP.getGrid()[row][col]==startRoom){
          startX = row;
          startY = col;
        }
      }
    }
  }

  /**
  * Method that sets the coordinate values endX and endY for the end/destination room.
  * @param aFP a floorPlan; endRoom the current end Room.
  */
  public void setEndValues(FloorPlans aFP, int endRoom){
    for(int row=0;row<aFP.getRowLength();row++){
      for(int col=0;col<aFP.getColLength(row);col++){
        if(aFP.getGrid()[row][col]==endRoom){
          endX = row;
          endY = col;
        }
      }
    }
  }

  /**
  * Method to place start marker at the starting room on the grid.
  * Starting marker is a constant of the number 8.
  * @param aGrid a chosen int[][] grid.
  *
  */
  public void placeStart(int[][] aGrid){
    aGrid[startX][startY]=Constants.START;
  }

  /**
  * Method to place the Destination marker on the destination room.
  * Destination marker is a constant of the number 5.
  * @param aGrid a chosen int[][] grid.
  */
  public void placeDest(int[][] aGrid){
    aGrid[endX][endY] = Constants.DEST;
  }


}
