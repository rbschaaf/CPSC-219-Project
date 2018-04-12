import java.util.ArrayList;
import resources.BuiltFloorPlans;
import java.io.Serializable;

public class Building implements Serializable{

  private ArrayList<FloorPlans> floorList = new ArrayList<FloorPlans>();
  private String buildingName;

  /**
  * Basic constructor for Building.
  *
  */
  public Building(){}

  /**
  * Constructor for Building which takes a String as the building's name.
  *
  */
  public Building(String bName){
    buildingName = bName;
    addFloors();
  }

  /**
  * Copy constructor for building that copies the name of the building toCopy
  * and the list of floors it has.
  */
  public Building(Building toCopy){
    buildingName = toCopy.getName();
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
  * Method that adds floors to the building, depending on its name.
  *
  */
  public void addFloors(){
    if(buildingName.equals("Taylor Family Digital Library")){
      String name = "Taylor Family Digital Library";
      floorList.add(new FloorPlans(name,BuiltFloorPlans.TFDLGROUND,0,23,18));
      floorList.add(new FloorPlans(name, BuiltFloorPlans.TFDLONE, 1, 125,170));
      floorList.add(new FloorPlans(name,BuiltFloorPlans.TFDLTWO, 2, 225, 270));
      floorList.add(new FloorPlans(name,BuiltFloorPlans.TFDLTHREE, 3,370,366));
      floorList.add(new FloorPlans(name,BuiltFloorPlans.TFDLFOUR, 4,474,475));
      floorList.add(new FloorPlans(name,BuiltFloorPlans.TFDLFIVE, 5,552,517));
      floorList.add(new FloorPlans(name,BuiltFloorPlans.TFDLSIX, 6,603,679));
    }else if(buildingName.equals("Bioscience")){
      String name = "Bioscience";
      floorList.add(new FloorPlans(name, BuiltFloorPlans.BIOSCIONE,1,180,100));
      floorList.add(new FloorPlans(name, BuiltFloorPlans.BIOSCITWO,2,287,286));
    }
  }

  /**
  * Method that finds the floor plan that contains the given room number.
  * @param roomNumber: integer value of the room to look for
  * @return a copy of floor, the floor found that contains the given room number.
  * @return null if no floorPlans is found.
  */
  public FloorPlans getFloorPlan(int roomNumber){
    int floorNum=0;
    int numDigits;
    numDigits = (int) Math.log10(roomNumber) + 1;
    // If the provided room number has less than three digits it will be on the
    // basement floor.
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
    FloorPlans toReturn = new FloorPlans();
    for(FloorPlans floor : getFloorList()){
      if(floor.getFlNum() == floorNum) return new FloorPlans(floor);
    }
    return null;
  }

  /**
  * Method to get the current list of floors for the building.
  * @return ArrayList<FloorPlans> : copy list of floors
  */
  public ArrayList<FloorPlans> getFloorList(){
    ArrayList<FloorPlans> copyList = new ArrayList<FloorPlans>();
    for(FloorPlans floor : floorList){
      copyList.add(floor);
    }
    return copyList;
  }

  /**
  * Method to find out of a number is on any floor and if so, which one.
  * @param roomNumber: integer that is the room number.
  * @return nextFloor: floorPlans for which floor the room number is on
  * or null if it is not on any floor plan in the building.
  */
  public FloorPlans onAFloor(int roomNumber){
    FloorPlans nextFloor = null;
    for(FloorPlans floor : getFloorList()){
      for(Room room : floor.getRoomList()){
        if((roomNumber + 1000) == room.getRoomsNumber()){
          nextFloor = floor;
        }
      }
    }
    return nextFloor;
  }


}
