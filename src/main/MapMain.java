/**
 * This Class to run the room finder application on the console as a text-version.
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class MapMain {


  /**
   * Default constructor for MapMain
   */
  public MapMain() {}

  /**
   * Method to get starting room from user keyboard input
   *
   * @return roomStart the starting room number as an int
   */
  public int getStartRoom(){
    int roomStart = 0;
    Scanner keyboard = new Scanner(System.in);
    try{
      roomStart = keyboard.nextInt();
    }catch(InputMismatchException e){}

    return roomStart;
  }

  /**
   * Method to get destination room from user keyboard input
   *
   * @return roomDest the destination room number as an int
   */
  public int getDestRoom() {
    int roomDest =0;
    Scanner keyboard = new Scanner(System.in);
    try{
      roomDest = keyboard.nextInt();
    }catch(InputMismatchException e){}

    return roomDest;
  }

  /**
  * Method to get the building name from the user keyboard input
  *
  *@return bName the building name as a String
  */
  public String getBuilding(){
    String bName = "";
    int bNum =0;
    Scanner keyboard = new Scanner(System.in);
    try{
      bNum = keyboard.nextInt();
    }catch(InputMismatchException e){}

    if(bNum == 1){
      bName = "Taylor Family Digital Library";
    }else if(bNum == 2){
      bName = "Bioscience";
    }
    return bName;
  }

  /**
  * Method that changes the footprint variables made by the path to
  * sevens to show the path more easily.
  * @param aGrid the grid for which we convert the path tiles to 7.
  */
  public void changeToSevens(int[][] aGrid){
    for(int row=0; row<aGrid.length;row++){
      for(int col=0;col<aGrid[0].length;col++){
        if(aGrid[row][col] >10000){
          aGrid[row][col] = 7;
        }
      }
    }
  }

  /**
  * Main method that runs the application.
  *
  */
  public void main() {
    // Create a new map.
    Map newMap = new Map();

    // Get the user's inputted for the building name.
    System.out.println("Choose the building to search: ");
    System.out.println("Enter (1) for Taylor Family Digital Library"+
    "\nEnter (2) for Bioscience");
    String bName = getBuilding();
    while(bName!="Bioscience" && bName!="Taylor Family Digital Library"){
      System.out.println("That is not a valid choice. Please choose again.");
      bName = getBuilding();
    }

    Building currentBuilding = new Building(bName);

    // Call method to get starting room from user and ensure it is a valid number.
    System.out.println("Enter the room number nearest to you: ");
    int roomStart = getStartRoom();
    while(currentBuilding.onAFloor(roomStart)==null){
      System.out.println("That room number is not valid. Please enter another number.");
      roomStart = getStartRoom();
    }

    // Call method to get destination room from user and ensure it is a valid number.
    System.out.println("Enter the room number of your destination: ");
    int roomDest = getDestRoom();
    while(currentBuilding.onAFloor(roomDest)==null){
      System.out.println("That room number is not valid. Please enter another number.");
      roomDest = getDestRoom();
    }

    // Generate the floor plan for the map.
    FloorPlans floorPlan = currentBuilding.getFloorPlan(roomStart);
    newMap.setCurrentFloorPlan(floorPlan);

    // Print the floor plan first without the path.
    System.out.println("\nGrid without the path: ");
    newMap.printGrid();

    // Create a new path and set its start and end values on the map for
    // the current floorplan.
    Path path = new Path(floorPlan.getGrid(), roomStart, roomDest);
    newMap.setStartValues(floorPlan, roomStart);
    newMap.setEndValues(floorPlan, roomDest);


    // Form the path from starting room to destination room.
    int[][] endGrid = path.createPath();

    // Place the number 8 as a marker for the starting room.
    newMap.placeStart(endGrid);

    // Place the number 5 as a marker for destination room.
    newMap.placeDest(endGrid);

    System.out.println("\nGrid with the path: ");

    // Print the map again with the number 7 showing a path between the rooms.
    changeToSevens(endGrid);
    newMap.printGrid();
  }


  public static void main(String[] args) {
    MapMain mapMain = new MapMain();
    mapMain.main();

  }
}



