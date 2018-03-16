/**Main portion of the pathfinding program. Finds a path between a destination room from a starting
room from the user. Floor is modelled off of north portion of 2nd floor Taylor Family Digital Library.
1s are hallway, 0s are walls, 200-series numbers are room names, 9s are rooms themselves, 8 marks starting room,
5 marks destination room, 7s mark path found between rooms. Created by Nicki Lindstrom, Dayan Jayasuriya, and Riley Schaaf
Last Edited by Nicki Feb 28*/

import java.util.Scanner;

public class MapMain {
  private int roomStart;
  private int roomDest;


  //constructor
  public MapMain (){

  };

  //get starting room from user
  public int getStartRoom() {
    System.out.println("Enter the room number nearest to you: ");
    Scanner keyboard = new Scanner(System.in);
    roomStart = keyboard.nextInt();
    return roomStart;
  }

  //get destination room from user
  public int getDestRoom() {
    System.out.println("Enter the room number of your destination: ");
    Scanner keyboard = new Scanner(System.in);
    roomDest = keyboard.nextInt();
    return roomDest;
  }


  public void main(){
    Map newMap = new Map();
    //call method to print the map
    newMap.printGrid();
    //call method to get starting room from user
    int roomStart = getStartRoom();

    //call method to get destination room from user
    int roomDest = getDestRoom();


    // Make a new floorplan
    FloorPlans floorPlan = new FloorPlans("Taylor Family Digital Library",roomDest);
    newMap.setCurrentFloorPlan(floorPlan);
    // make a new path
    Path path = new Path(floorPlan.getGrid(),roomStart,roomDest);
    newMap.setStartValues(floorPlan,roomStart);
    newMap.setEndValues(floorPlan,roomDest);


    //call method to create the path from starting room to destination room.
    int[][] endGrid = path.createPath();
    //place the number 8 as a marker for the starting room
    newMap.placeStart(endGrid);
    newMap.printGrid();

    //place the number 5 as a marker for destination room
    newMap.placeDest(endGrid);
    newMap.printGrid();

    System.out.println("A new grid: ");
    //print the map again with the number 7 showing a path between the rooms
    newMap.printGrid();
  }


  public static void main(String[] args){
    MapMain mapMain = new MapMain();
    mapMain.main();

  }
}
