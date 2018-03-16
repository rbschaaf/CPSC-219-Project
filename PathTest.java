import java.util.ArrayList;

/**
* Class used to develop the Path Class
*Last Edited by Dayan J.
*3 Mar 2018
*/

public class PathTest {
  public static void main(String[] args) {
    String building = "Taylor Family Digital Library";
    int roomNum = 250;
    TestGrid testGrid = new TestGrid();
    testGrid.setGrid(building, roomNum);
    int[][] grid = testGrid.getGrid();
    testGrid.print(grid);
    Path path = new Path(grid, 261, 262);
    ArrayList<Node> nodeList = path.createNodeArray(grid);
    //nodeList = path.createNodeArray(grid);
    Node lowStartNode = path.getLowestDistanceNode(nodeList);
    boolean val = lowStartNode.getStartNodeVal();

    /*
    * WORKING
    */
    Node startNode = path.getStartNode(nodeList);
    Node endNode = path.getEndNode(nodeList);
    System.out.println(nodeList);
    /*
    *Working
    */
    //path.addNodeToList(nodeList, endNode);
    /*
    * Working
    */
  //ArrayList<Node> removedArrays = new ArrayList<Node>();
  //  removedArrays = path.removeNodeFromList(nodeList,endNode);
    //System.out.println(removedArrays);


  //  ArrayList<Node> nodeDistanceList = new ArrayList<Node>();
  //  nodeDistanceList = path.setNodeDistances(nodeList);
    //System.out.println(nodeDistanceList);

  ///  ArrayList<Node> shortestDistanceList = new ArrayList<Node>();
  //  shortestDistanceList = path.getConnectedNodes(nodeDistanceList);
    //System.out.println(shortestDistanceList);

//    int[][] finalGrid = path.addPathToGrid(shortestDistanceList);
    int[][] finalGrid = path.createPath();
    testGrid.print(finalGrid);

  }
}
