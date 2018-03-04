import java.util.ArrayList;

/**
* Class used to develop the Path Class
*Last Edited by Dayan J.
*3 Mar 2018
*/

public class PathTest {
  public static void main(String[] args) {
    String building = "Taylor Family Digital Library";
    int roomNum = 161;
    TestGrid testGrid = new TestGrid();
    testGrid.setGrid(building, roomNum);
    int[][] grid = testGrid.getGrid();
    //path.print(grid);
    Path path = new Path(grid, 160, 161);
    ArrayList<Node> nodeList = new ArrayList<Node>();
    nodeList = path.createNodeArray(grid);
    //System.out.println(nodeList);
    ArrayList<Node> nodeDistanceList = new ArrayList<Node>();
    nodeDistanceList = path.setNodeDistances(nodeList);
    System.out.println(nodeDistanceList);

  }
}
