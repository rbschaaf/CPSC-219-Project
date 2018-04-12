import resources.BuiltFloorPlans;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is for the buildings.
 */

public class Building implements Serializable{

  private ArrayList<FloorPlans> floorList = new ArrayList<FloorPlans>();



  private String buildingName;

  /**
  * Default constructor for Building.
  *
  */
  public Building(){}

  /**
  * Constructor for Building which takes a String as the building's name.
  * @param: bName is a building name as a String.
  *
  */
  public Building(String bName){
    setBuildingName(bName);
    addFloors();
  }

  /**
  * Copy constructor for building that copies the name of the building toCopy
  * and the list of floors it has.
  * @param: toCopy is the building to be copied of type Building.
  */
  public Building(Building toCopy){
    setBuildingName(toCopy.getName());
    floorList = toCopy.getFloorList();
  }

  /**
  * Method that returns the name of the building.
  * @return buildingName: the name of the building.
  */
  public String getName(){
    return buildingName;
  }

  /**
   * method that sets the building name
   * @param aBuildingName a Name of a building
   */
  public void setBuildingName(String aBuildingName) {
    // Conditional controls if the parameter string is one of the two buildings floorplans have been built for.
    if (aBuildingName.equals("Taylor Family Digital Library") || aBuildingName.equals("Bioscience")) {
      buildingName = aBuildingName;
    }
  }

  /**
  * Method that adds the floorplans to the building, depending on its name.
  *
  */
  public void addFloors(){
    if(buildingName != null) {
      //Adds the floorplans for Taylor Family Digital Library if this is the name of the Building object.
      if (buildingName.equals("Taylor Family Digital Library")) {
        String name = "Taylor Family Digital Library";
        floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLGROUND, 0, 23, 18));
        floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLONE, 1, 125, 170));
        floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLTWO, 2, 225, 270));
        floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLTHREE, 3, 370, 366));
        floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLFOUR, 4, 474, 475));
        floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLFIVE, 5, 552, 517));
        floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLSIX, 6, 603, 679));
        //Adds the floorplans for the BioScience building if this is the name of the Building object.
      } else if (buildingName.equals("Bioscience")) {
        String name = "Bioscience";
        floorList.add(new FloorPlans(name, BuiltFloorPlans.BIOSCIONE, 1, 180, 100));
        floorList.add(new FloorPlans(name, BuiltFloorPlans.BIOSCITWO, 2, 287, 286));
      }
    }
  }

  /**
  * Method that finds the floor plan that contains the given room number.
  * @param roomNumber: integer value of the room to look for
  * @return a copy of floor, the floor of type FloorPlans found that contains the given room number
  * @return null if no floorPlans is found.
  */
  public FloorPlans getFloorPlan(int roomNumber){
    int floorNum;
    int numDigits;
    // Determines the number of digits in the roomNumber parameter.
    numDigits = (int) Math.log10(roomNumber) + 1;
    /* If the provided room number has less than three digits it will be on the
    basement floor. */
    if (numDigits < 3) {
      floorNum = 0;
    }
    // Get floor number from first digit of room number if it is not the basement.
    else {
      floorNum = roomNumber;
      while (floorNum > 9) {
        floorNum /= 10;
      }
    }
    // Return a copy of the FloorPlans for the floor number the room number is on.
    for(FloorPlans floor : getFloorList()){
      if(floor.getFlNum() == floorNum) {
        return new FloorPlans(floor);
      }
    }
    return null;
  }

  /**
  * Method to get the current list of floors for the building.
  * @return ArrayList<FloorPlans> : copy list of floors of type FloorPlans for the building.
  */
  public ArrayList<FloorPlans> getFloorList(){
    // Copying the floors for the building into an ArrayList.
    ArrayList<FloorPlans> copyList = new ArrayList<FloorPlans>();
    for(FloorPlans floor : floorList){
      copyList.add(floor);
    }
    return copyList;
  }

  /**
  * Method to find out if a room is on any floor and if so, which one.
  * @param roomNumber: integer that is the room number.
  * @return nextFloor: FloorPlans for which floor the room number is on
  * or null if it is not on any floor plan in the building.
  */
  public FloorPlans onAFloor(int roomNumber){
    FloorPlans nextFloor = null;
    // Loop through all the FloorPlans for the building.
    for(FloorPlans floor : getFloorList()){
      // Loop through the roomlist for the current floor being looped through.
      for(Room room : floor.getRoomList()){
        /* Check if the interested roomNumber passed as a parameter is equal to 
        the current iterated room from the roomlist. Adds 1000 to the roomNumber parameter 
        because on floor plans the rooms are denoted by 1000 series numbers, while the
        3 digit numbers actually designate the doors of the rooms of type Door. */
        if((roomNumber + 1000) == room.getRoomsNumber()){
          nextFloor = floor;
        }
      }
    }
    return nextFloor;
  }


}
