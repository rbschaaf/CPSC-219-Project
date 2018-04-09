// Last updated March 18th by Nicki.
public class Door{
  private int doorX;
  private int doorY;

  // basic constructor
  public Door(){}

  // copy constructor
  public Door(Door toBeCopied){
    doorX = toBeCopied.getDoorX();
    doorY = toBeCopied.getDoorY();
  }

  // getter & setter methods for doorX
  public int getDoorX(){
    return doorX;
  }
  public void setDoorX(int newDoorX){
    doorX = newDoorX;
  }

  // getter & setter methods for doorY
  public int getDoorY(){
    return doorY;
  }
  public void setDoorY(int newDoorY){
    doorY = newDoorY;
  }
}
