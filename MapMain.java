import java.util.Scanner;

public class MapMain {
  private Map newMap;
  private Path newPath;
  private int roomStart;
  private int roomDest;

  /* Constructors
  *
  * Create new Map and Path when running main
  */

  public MapMain() {
    newMap = new Map();
    newPath = new Path(newMap);
  }


  //user input Methods

  public int getStartRoom() {
    System.out.println("Enter the room number nearest to you: ");
    Scanner keyboard = new Scanner(System.in);
    roomStart = keyboard.nextInt();
    return roomStart;
  }

  public int getDestRoom() {
    System.out.println("Enter the room number of your destination: ");
    Scanner keyboard = new Scanner(System.in);
    roomDest = keyboard.nextInt();
    return roomDest;
  }

// win condition
  public boolean foundDestination(){
    int finalX = newPath.getCurrentX();
    int finalY = newPath.getCurrentY();
    Map finalMap = newPath.getMap();
    return(finalMap.grid[finalX][finalY] == 5);
  }





  public void main(){
    newMap.print();

    getStartRoom();
    newPath.setStartLoc(roomStart);
    newPath.placeStart();
    newPath.printMap();


    getDestRoom();
    newPath.setDestLoc(roomDest);
    newPath.placeDest();
    System.out.println("With destination.");
    newPath.printMap();
    System.out.println(" ");

  //  System.out.println("Current X: "+ newPath.getCurrentX());
  //  System.out.println("Current Y: "+ newPath.getCurrentY());

    while(!foundDestination()) {
      newPath.createPath();
    }

    System.out.println("A new grid: ");
    newPath.printMap();
  }


  public static void main(String[] args){
    MapMain map1 = new MapMain();
    map1.main();
  }
}
