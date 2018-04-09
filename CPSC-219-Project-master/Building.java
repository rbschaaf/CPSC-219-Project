import java.util.ArrayList;
import resources.BuiltFloorPlans;
public class Building{

  private ArrayList<FloorPlans> floorList = new ArrayList<FloorPlans>();
  private String buildingName;


  public Building(){}

  public Building(String bName){
    buildingName = bName;
    addFloors();
  }

  public Building(Building toCopy){
    buildingName = toCopy.getBuildingName();
    floorList = toCopy.getFloorList();
  }

  public String getBuildingName(){
    return buildingName;
  }

  public void addFloors(){
    if(buildingName.equals("Taylor Family Digital Library")){ //stairs, elevator
      floorList.add(new FloorPlans(BuiltFloorPlans.TFDLONE, 1, 125,170));
      floorList.add(new FloorPlans(BuiltFloorPlans.TFDLTWO, 2, 225, 270));
      floorList.add(new FloorPlans(BuiltFloorPlans.TFDLTHREE, 3,370,366));
      floorList.add(new FloorPlans(BuiltFloorPlans.TFDLFOUR, 4));
      floorList.add(new FloorPlans(BuiltFloorPlans.TFDLFIVE, 5));
      floorList.add(new FloorPlans(BuiltFloorPlans.TFDLSIX, 6));
      System.out.println("Floors added.");
      System.out.println("Length of plans1: " + floorList.size());
    }
  }
  public FloorPlans getFloorPlan(int roomNumber){
    int floorNum=0;
    int numDigits;
    numDigits = (int) Math.log10(roomNumber) + 1;
    /* if the provided room number has less than three digits it will be on the
    basement floor.*/
    if (numDigits < 3) {
      floorNum = 0;
    }
    /*get floor number from first digit of room number if it is not the basement*/
    else {
      floorNum = roomNumber;
      while (floorNum > 9) {
        floorNum /= 10;
      }
    }
    System.out.println("Length of plans2: " + floorList.size());
    FloorPlans toReturn = new FloorPlans();
    for(FloorPlans floor : getFloorList()){
      System.out.println(floor.getFlNum());
      if(floor.getFlNum() == floorNum) return new FloorPlans(floor);
    }
    return new FloorPlans();
  }

  public ArrayList<FloorPlans> getFloorList(){
    return floorList;
  }


}
