import org.junit.Test;
import resources.Constants;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static resources.BuiltFloorPlans.BIOSCIONE;
import static resources.BuiltFloorPlans.TFDLSIX;

/**
* Class used to develop the Path Class
*Last Edited by Dayan J.
*3 Mar 2018
*/

public class PathTest {
  /*  public static void main(String[] args) {
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
  /*
    Node startNode = path.getStartNode(nodeList);
    Node endNode = path.getEndNode(nodeList);
    System.out.println(nodeList);
    */
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
   /*
    int[][] finalGrid = path.createPath();
    testGrid.print(finalGrid);
    */
  @Test
  public void pathConstructorTestWithStartAndEnd() {
    Path a = new Path(BIOSCIONE,180, 129);
    int [][] b = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1196,1196,1196,1108,1108,1108,1118,1118,1118,1126,1126,1126,1,129,1129,0,0},
            {0,1192,1192,1192,1,196,1196,1196,1108,1108,1108,1118,1118,1118,1126,1126,1126,1,1,1,0,0},
            {0,1192,1192,192,1,1196,1196,1196,1108,1108,1108,1118,1118,1118,1126,1126,1126,1,1,1,0,0},
            {1190,1190,190,1,1,1199,1199,1199,1107,1107,1107,1117,1117,1117,1123,1123,1123,1,1132,1132,1132,0},
            {1190,1190,1190,1,1,1199,1199,1199,1107,1107,1107,1117,1117,1117,1123,1123,1123,1,1132,1132,1132,0},
            {1190,1190,1190,1,1,1199,199,1199,107,1107,1107,1117,1117,117,1123,123,1123,1,132,1132,1132,0},
            {1186,1186,1186,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1136,1136,1136,0,},
            {1186,1186,1186,1,1,1177,1177,0,0,1,0,0,111,1111,119,1119,1,1,1136,1136,1136,0},
            {1186,1186,186,1,1,177,888,1180,180,1,100,1100,1111,1111,1149,1147,1147,1,1136,1136,1136,0},
            {1186,1186,1186,1,1,1177,1177,0,0,1,0,0,1111,1111,149,1147,147,1,136,1136,1136,0},
            {1186,1186,1186,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1136,1136,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    assertEquals("Checking Start room", 180, a.getStartRoomNum());
    assertEquals("Checking End room", 129, a.getEndRoomNum());
    assertTrue("Checking Grid", Arrays.deepEquals(b, a.getFloorGrid()));
  }

  @Test
  public void pathConstructorTest() {
    Path a = new Path(TFDLSIX);
    int [][] b = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1640, 1,  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1652, 0},
            {0, 640, 1,1647,  1, 1648,0, 636, 1636, 0,  638, 1638,0, 653, 1653,  1, 1652,0  },
            {0,0, 1,647,   1, 648,0, 1636, 1636, 0,  1638, 1638 ,0, 1653, 1653, 1, 1652, 0},
            {0, 1641,  1,1647,  1, 0, 0, 0, 0,0, 1638,0 , 1653, 1653,1653,  1, 1652, 0},
            {0, 641,  1,0, 1,   1649,  0, 1638,  1638, 1638,1638 , 0, 1653,1653, 1653,  1, 1652, 0},
            {0,0,  1, 1646,  1,649, 1,0,0,0,0,0,0,0,0,1,1652,0},
            {0, 642,  1,646,  1,0,1, 1656, 1656,  1656, 0,1670,1670,1670, 670, 1, 1652, 0},
            {0,1642, 1, 1646,   1,1639, 1,656, Constants.REST, 1656, 0,1670,1670,1670, 1670, 1, 1652, 0},
            {0,0, 1, 1, 1,   639, 1,0,0,0,0,1, 1,1,1, 1, 1652, 0},
            {0, 643, 1,  1,    1,1,  1,655, Constants.REST, 1655, 1655, 1, 1671, 1671, 671,1,  652, 0},
            {0, 1643,0,644,0, 645,   1,1655, 1655, 1655, 1655, 1, 0,0,0 ,1,  1652, 0},
            {0, 1643,0,1644,0, 1645,   1, 1, 1, 1, 1, 1, 1672, 1672,672, 1,  1652, 0},
            {0, 0, 0, 0, 0, 0, 1 ,606, 1606, 1606, 1606,  1 ,1672,1672,1672,1,0,0},
            {0, 1631, 1631, 1631,1631, 631,1,1606, 1606, 1606, 1606,  1, 0,0,0,1,1651,0  },
            {0, 1631, 1631,  1631,1631, 1631,1,1606, 1606, 1606, 1606, 1, 1673, 1673,673, 1,  1651, 0},
            {0, 1631, 1631, 1631,1631, 1631,1,603, 1603, 0, 0,  1, 1673, 1673,1673, 1,  1651, 0},
            {0, 1631, 1631,1631,1631,1631, 1, 0, 0, 0, 0, 1,0,0,0,1,1651,0  },
            {0, 0, 0, 0, 0, 0, 1, 0,0,1623,1623,1,1674, 1674,674, 1, 1651, 0},
            {0, 1625, 1625, 1625, 1625, 625, 1,0,0,1623,623, 1,1674, 1674,1674, 1,  1651, 0},
            {0, 1625, 1625, 1625, 1625, 1625, 1,0,0, 0, 0,1,0,0,0,1,1651,0  },
            {0, 1625, 1625, 1625, 1625, 1625, 1,679,1679,1624,624,  1,1675, 1675,675, 1,  1651, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0,0,0, 0,0, 0,0, 0,  0, 0}};
    assertTrue("Checking Grid", Arrays.deepEquals(b, a.getFloorGrid()));
  }

  @Test
  public void setFloorGrid() {
    Path a = new Path(TFDLSIX);
    int [][] b = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1291,1291,1291,1210,1210,1222,1222,1228,0,0},
            {0,1213,1288,1,1,1291,1291,1291,1210,210,1222,1222,228,0,0},
            {0,213,288,1,1,1291,1291,1291,1210,1,1222,1222,1,0,0},
            {0,1,1,1,291,1291,1291,1291,1217,1,222,1222,1,0,0},
            {0,289,290,1,292,1292,1297,1297,1217,1,1227,1227,1,0,0},
            {0,1289,1290,1,1293,1293,1297,1297,217,1,1227,227,1,0,0},
            {0,1284,284,1,0,293,1297,297,1217,1,1227,1227,1,0,0},
            {0,1283,1283,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,1283,283,1,1281,0,0,1,1211,1211,211,1211,263,1263,0},
            {0,1283,1283,1,281,888,287,1,286,1286,1211,1211,1263,1263,0},
            {0,1280,280,1,1281,0,1287,1,1211,1211,1211,1211,0,0,0},
            {0,1280,1280,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,1295,1295,1,1273,273,1273,1249,249,0,251,1251,1243,243,0},
            {0,1295,295,1,1273,1273,1273,1249,1249,0,1251,1251,1243,1243,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    a.setFloorGrid(b);
    assertTrue("Check if setter successfully changed the grid", Arrays.deepEquals(b, a.getFloorGrid()));
  }

  @Test
  public void getStartRoomNum() {
    Path a = new Path(BIOSCIONE,180, 129);
    assertEquals("Check if setter changed the number", 180, a.getStartRoomNum());
  }

  @Test
  public void setStartRoomNum() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setStartRoomNum(190);
    assertEquals("Check if setter changed the number", 190, a.getStartRoomNum());
  }

  @Test
  public void setStartRoomNum_Negative() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setStartRoomNum(-3);
    assertEquals("Check if setter changed the number", 180, a.getStartRoomNum());
  }

  @Test
  public void getEndRoomNum() {
    Path a = new Path(BIOSCIONE,180, 129);
    assertEquals("Check if setter changed the number", 129, a.getEndRoomNum());
  }

  @Test
  public void setEndRoomNum() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setEndRoomNum(190);
    assertEquals("Check if setter changed the number", 190, a.getEndRoomNum());
  }

  @Test
  public void setEndRoomNum_Negative() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setEndRoomNum(-3);
    assertEquals("Check if setter changed the number", 129, a.getEndRoomNum());
  }


  @Test
  public void getEndNode() {
    Path c = new Path(BIOSCIONE,180, 129);
    ArrayList<Node> list = new ArrayList<>();
    Node m1 = new Node(9, 9, true);
    Node m2 = new Node(1, 7);
    Node m3 = new Node(10, 5);
    Node m4 = new Node(4, 10);
    Node m5 = new Node(8, 9);
    Node m6 = new Node(12, 4);
    c.addNodeToList(list,m1);
    c.addNodeToList(list,m2);
    c.addNodeToList(list,m3);
    c.addNodeToList(list,m4);
    c.addNodeToList(list,m5);
    c.addNodeToList(list,m6);

    assertTrue("Check if End Node is found", c.getEndNode(list).getEndNodeVal());
  }

  @Test
  public void addNodeToList() {
    Path c = new Path(BIOSCIONE,180, 129);
    ArrayList<Node> list = new ArrayList<>();
    Node m1 = new Node(9, 9, true);
    Node m2 = new Node(1, 7);
    Node m3 = new Node(10, 5);
    Node m4 = new Node(4, 10);
    Node m5 = new Node(8, 9);
    Node m6 = new Node(12, 4);
    c.addNodeToList(list,m1);
    c.addNodeToList(list,m2);
    c.addNodeToList(list,m3);
    c.addNodeToList(list,m4);
    c.addNodeToList(list,m5);
    c.addNodeToList(list,m6);
    assertEquals("Check length of list", 6, list.size());
  }

  @Test
  public void removeNodeFromList() {
    Path c = new Path(BIOSCIONE,180, 129);
    ArrayList<Node> list = new ArrayList<>();
    Node m1 = new Node(9, 9, true);
    Node m2 = new Node(1, 7);
    Node m3 = new Node(10, 5);
    Node m4 = new Node(4, 10);
    Node m5 = new Node(8, 9);
    Node m6 = new Node(12, 4);
    c.addNodeToList(list,m1);
    c.addNodeToList(list,m2);
    c.addNodeToList(list,m3);
    c.addNodeToList(list,m4);
    c.addNodeToList(list,m5);
    c.addNodeToList(list,m6);
    assertEquals("Check length of list", 5, c.removeNodeFromList(list, m2).size());
  }

  @Test
  public void getCopyNodes() {

  }

  @Test
  public void getLowestDistanceNode() {
    Path c = new Path(BIOSCIONE,180, 129);
    ArrayList<Node> list = new ArrayList<>();
    Node m1 = new Node(9, 9, true);
    Node m2 = new Node(1, 7, 5);
    Node m3 = new Node(10, 5, 10);
    Node m4 = new Node(4, 10);
    Node m5 = new Node(8, 9);
    Node m6 = new Node(12, 4);
    c.addNodeToList(list,m1);
    c.addNodeToList(list,m2);
    c.addNodeToList(list,m3);
    c.addNodeToList(list,m4);
    c.addNodeToList(list,m5);
    c.addNodeToList(list,m6);
    assertEquals("Check length of list", 5, c.getLowestDistanceNode(list).getStartDistance(), 0.00001);
  }

  @Test
  public void createNodeArray() {
    int[][] a = {{0,0,0,129,0,0,0},
                  {0,1,1,0,0,0,0},
                  {0,0,1,1,0,0,0},
                  {0,0,0,0,180,0,0}};
    Path c = new Path(a ,180, 129);
    assertEquals("Check length of array", 6, c.createNodeArray(a).size());
  }

  @Test
  public void setNeighborInstances_nonDiagonal() {
    Path c = new Path(BIOSCIONE,180, 129);
    Node m2 = new Node(1, 1, 15);
    Node m3 = new Node(1, 2);
    c.setNeighborInstances(m2,m3);
    assertEquals("Check distance given to startDistance in Node", 25, m3.getStartDistance(), 0.00001);
  }

  @Test
  public void setNeighborInstances_Diagonal() {
    Path c = new Path(BIOSCIONE,180, 129);
    Node m2 = new Node(1, 1, 15);
    Node m3 = new Node(2, 2);
    c.setNeighborInstances(m2,m3);
    assertEquals("Check distance given to startDistance in Node", 29, m3.getStartDistance(), 0.00001);
  }

  @Test
  public void setNodeDistances() {
    int[][] a = {{0,0,0,129,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,1,1,0,0,0},
            {0,0,0,0,180,0,0}};
    Path c = new Path(a ,180, 129);
    ArrayList<Node> list = c.setNodeDistances(c.createNodeArray(a));
    boolean infinityExists = false;
    for (Node n : list) {
      if (n.getStartDistance() > 1000000000) {
          infinityExists = true;
      }
    }
    assertFalse("Ensure all instances got set", infinityExists);
  }

  @Test
  public void setNodeDistances_checkifTestisGood() {
    int[][] a = {{0,0,0,129,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,1,1,0,0,0},
            {0,0,0,0,180,0,0}};
    Path c = new Path(a ,180, 129);
    ArrayList<Node> list = c.setNodeDistances(c.createNodeArray(a));
    list.get(3).setStartDistance(1000000);
    boolean infinityExists = false;
    for (Node n : list) {
      if (n.getStartDistance() > 100000) {
        infinityExists = true;
      }
    }
    assertTrue("Ensure all instances got set", infinityExists);
  }

  @Test
  public void getConnectedNodes() {

  }

  @Test
  public void addPathToGrid() {
  }

  @Test
  public void createPath() {
  }
}
