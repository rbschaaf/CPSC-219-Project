import resources.Constants;
import java.io.Serializable;

/**
 * Class for the creations of the maps, sizing of the maps, and contains the path restriction for the pathfinding.
 */

public class Map implements Serializable{

  private FloorPlans currentFloorPlan = new FloorPlans();
  private Building currentBuilding;
  private int start;
  private int dest;

  /**
  * Constructor with a provided FloorPlans.
  * @param newFloorPlan a floor plan of type FloorPlans.
  */
  public Map (FloorPlans newFloorPlan){
    currentFloorPlan = new FloorPlans(newFloorPlan);
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
  * @return currentBuilding a copy of the currentBuilding of type Building.
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
  * @return currentFloorPlan a copy of the current floor plan as type FloorPlans.
  */
  public FloorPlans getCurrentFloorPlan(){
    return new FloorPlans(currentFloorPlan);
  }

  /**
  * Method to get the start value.
  * @return start the start value as an integer.
  */
  public int getStart(){
    return start;
  }

  /**
  * Method to get the dest value.
  * @return dest the destination value as an integer.
  */
  public int getDest(){
    return dest;
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
  * Method that sets the start value for the starting room.
  * @param : aFP is a floor plan of type FloorPlans.
  * @param : startRoom the room number of the start room as an integer.
  */
  public void setStartValues(int startRoom){
    // Set the start equal to the given startRoom
    start = startRoom;  
    
  }

  /**
  * Method that sets the destination point for the end/destination room.
  * @param aFP a floorPlan as type FloorPlans
  * @param endRoom the current end room number as an int.
  */
  public void setEndValues(int endRoom){
    // Set the destination value equal to the given endRoom.
    dest = endRoom;
  }



}
