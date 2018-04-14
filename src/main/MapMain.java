/**
 * This Class to run the room finder application on the console as a text-version.
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import resources.Constants;

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
      //Catch if the user does not enter an integer for the starting room number.
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
      //Catch if the user does not enter an integer for the destination room number.
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
      //Catch if the user does not enter an integer corresponding with a building.
    }catch(InputMismatchException e){}
    //Taylor Family Digital Library has a building number 1 assisgned to it and Bilogical Scienses has a number 2.
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
  * @param aGrid the grid (int[][]) for which we convert the path tiles to 7.
  */
  public void changeToSevens(int[][] aGrid){
    //Loop through each coordinate on the grid parameter.
    for(int row=0; row<aGrid.length;row++){
      for(int col=0;col<aGrid[0].length;col++){
        /*Values above 10000 correspond to the path being created.
        This is changed to 7 to be easier displayed on the text-based version of the program.*/
        if(aGrid[row][col] >10000){
          aGrid[row][col] = 7;
        }
      }
    }
  }

  /**
  * Main method that runs the application for the text-based console version of the program.
  *
  */
  public void main() {
    // Create a new map.
    Map newMap = new Map();

    // Get the user's inputted for the building name.
    System.out.println("Choose the building to search: ");
    System.out.println("Enter (1) for Taylor Family Digital Library"+
    "\nEnter (2) for Bioscience");
    String bName = getBuilding(); //Get the building name.
    //Loop until the user gives program a valid building.
    while(bName!="Bioscience" && bName!="Taylor Family Digital Library"){
      System.out.println("That is not a valid choice. Please choose again.");
      bName = getBuilding();
    }

    Building currentBuilding = new Building(bName); //Construct the building the path will be found in.

    // Call method to get starting room from user and ensure it is a valid number.
    System.out.println("Enter the room number nearest to you: ");
    int roomStart = getStartRoom();
    //Loop until user gives program a valid starting room that is on one of the floors in the building.
    while(currentBuilding.onAFloor(roomStart)==null){
      System.out.println("That room number is not valid. Please enter another number.");
      roomStart = getStartRoom();
    }

    // Call method to get destination room from user and ensure it is a valid number.
    System.out.println("Enter the room number of your destination (on the same floor): ");
    int roomDest = getDestRoom();
    //Loop until user gives program a valid destination room that is on the same floor as the starting room.
    while(currentBuilding.onAFloor(roomDest)==null ||
    (currentBuilding.getFloorPlan(roomDest).getFlNum()!=currentBuilding.getFloorPlan(roomStart).getFlNum())){
      System.out.println("That room number is not valid or is on a different floor.");
      System.out.println("Please enter another number: ");
      roomDest = getDestRoom();
    }

    // Generate the floor plan for the map for the floor with the starting and destination room..
    FloorPlans floorPlan = currentBuilding.getFloorPlan(roomStart);
    newMap.setCurrentFloorPlan(floorPlan);

    // Print the floor plan first without the path.
    System.out.println("\nGrid without the path: ");
    newMap.printGrid();

    /* Create a new path and set its start and end values on the map for
     the current floorplan.*/
    Path path = new Path(floorPlan.getGrid(), roomStart, roomDest);
    newMap.setStartValues(roomStart);
    newMap.setEndValues(roomDest);


    // Form the path from starting room to destination room.
    int[][] endGrid = path.createPath();


    /* Place the number 8 as a marker for the starting room.
    and place the number 5 as a marker for the destination room.
    Loop through each coordinate on the grid with the path.*/
    for(int row=0;row<endGrid.length;row++){
      for(int col=0;col<endGrid[0].length;col++){
        //If current coordinate is destination room, change the coordinate's value to 8.
        if(endGrid[row][col] == newMap.getStart()){
          endGrid[row][col] = Constants.START;
          System.out.println("Found start.");
          //Else if current coordinate is destination room, change the coordinate's value to 5.
        }else if(endGrid[row][col] == newMap.getDest()){
          endGrid[row][col] =Constants.DEST;
        }
      }
    }

  // Change all footprint variables to sevens.
    changeToSevens(endGrid);

    // Set the new grid as the current grid.
    floorPlan.setGrid(endGrid);
    //Update the floorplan on the map.
    newMap.setCurrentFloorPlan(floorPlan);

    System.out.println("\nGrid with the path: ");
    System.out.println("8 is the starting room, 5 is the destination room, and 7's are your path.");

    // Print the map again with the number 7 showing a path between the rooms.
    newMap.printGrid();
  }


  public static void main(String[] args) {
    MapMain mapMain = new MapMain();
    mapMain.main();

  }
}
