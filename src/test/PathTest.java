import org.junit.Test;
import resources.Constants;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static resources.BuiltFloorPlans.BIOSCIONE;
import static resources.BuiltFloorPlans.TFDLSIX;
import static resources.Constants.*;

/**
* Test Class of JUnit tests used to develop and test the Path Class
*/

public class PathTest {

  /**
  * Testing the adding of the start room number and destination room number on a path.
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

  /**
  * Testing the correct grid is constructed from the BuiltFloorPlans.
  */
  @Test
  public void pathConstructorTest() {
    //Constructing a grid from the BuiltFloorPlans in resources.
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

  /**
  * Testing the correct BuiltFloorPlans grid is set to the grid using the setter method.
  */
  @Test
  public void setFloorGrid() {
    //Constructing a grid from the BuiltFloorPlans in resources.
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
    //Setting the grid to a different grid.
    a.setFloorGrid(b);
    assertTrue("Check if setter successfully changed the grid", Arrays.deepEquals(b, a.getFloorGrid()));
  }

  /**
  * Testing getting starting room number from a constucted path.
  */
  @Test
  public void getStartRoomNum() {
    //1st parameter is grid name from BuiltFloorPlans, second is starting room number, and third is destination room number.
    Path a = new Path(BIOSCIONE,180, 129);
    assertEquals("Check if setter changed the number", 180, a.getStartRoomNum());
  }

  /**
  * Testing setting the starting room after the path has been constructed.
  */
  @Test
  public void setStartRoomNum() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setStartRoomNum(190);
    assertEquals("Check if setter changed the number", 190, a.getStartRoomNum());
  }

  /**
  * Testing the starting room of the the path cannot be set to a negative number.
  */
  @Test
  public void setStartRoomNum_Negative() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setStartRoomNum(-3);
    assertEquals("Check if setter changed the number", 180, a.getStartRoomNum());
  }

  /**
  * Testing getting destination room number from a constucted path.
  */
  @Test
  public void getEndRoomNum() {
    Path a = new Path(BIOSCIONE,180, 129);
    assertEquals("Check if setter changed the number", 129, a.getEndRoomNum());
  }

  /**
  * Testing getting the destintion room after the path has been constructed.
  */
  @Test
  public void setEndRoomNum() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setEndRoomNum(190);
    assertEquals("Check if setter changed the number", 190, a.getEndRoomNum());
  }

  /**
  * Testing the destination room of the the path cannot be set to a negative number.
  */
  @Test
  public void setEndRoomNum_Negative() {
    Path a = new Path(BIOSCIONE,180, 129);
    a.setEndRoomNum(-3);
    assertEquals("Check if setter changed the number", 129, a.getEndRoomNum());
  }

  /**
  * Testing creating a arraylist of nodes for the path and getting the end node from this arraylist
  */
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

  /**
  * Testing creating a arraylist of nodes for the path and all nodes were successfully added to the arraylist.
  */
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
    //Check for all nodes added to arraylist based on length of arraylist.
    assertEquals("Check length of list", 6, list.size());
  }

  /**
  * Testing nodes can be successfully removed from arraylist after being added.
  */
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
    //Use removeNodeFromList method to remove nodes that have been added to the arraylist.
    assertEquals("Check length of list", 5, c.removeNodeFromList(list, m2).size());
  }

  /**
  * Testing a copy of an already created arraylist of nodes can be copied.
  */
  @Test
  public void getCopyNodes() {
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
    //Comparing size of original arraylist of nodes to a copy of it to see if they are the same size.
    assertEquals("Compare the original node list to a copy of the list", list.size(), c.getCopyNodes(list).size());
  }

  /**
  * Test that the node with the shortest total distance travelled from the arraylist can be found.
  */
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
    //Uses getLowestDistanceNode method on the distance from the start node to test.
    assertEquals("Check length of list", 5, c.getLowestDistanceNode(list).getStartDistance(), 0.00001);
  }

  /**
  * Testing an array of nodes can be created successfully to find a path across a 2D grid of integers.
  */
  @Test
  public void createNodeArray() {
    int[][] a = {{0,0,0,129,0,0,0},
                  {0,1,1,0,0,0,0},
                  {0,0,1,1,0,0,0},
                  {0,0,0,0,180,0,0}};
    Path c = new Path(a ,180, 129);
    assertEquals("Check length of array", 6, c.createNodeArray(a).size());
  }

  /**
  * Testing neighbour node's values to the current node can be set.
  * Specifically looking at non-diagonal neighbour nodes.
  */
  @Test
  public void setNeighborInstances_nonDiagonal() {
    //Create neighbouring nodes to a path and set their values and test the values are correct.
    Path c = new Path(BIOSCIONE,180, 129);
    Node m2 = new Node(1, 1, 15);
    Node m3 = new Node(1, 2);
    c.setNeighborInstances(m2,m3);
    assertEquals("Check distance given to startDistance in Node", 25, m3.getStartDistance(), 0.00001);
  }

  /**
  * Testing neighbour node's values to the current node can be set.
  * Specifically looking at diagonal neighbour nodes.
  */
  @Test
  public void setNeighborInstances_Diagonal() {
    //Create neighbouring nodes to a path and set their values and test the values are correct.
    Path c = new Path(BIOSCIONE,180, 129);
    Node m2 = new Node(1, 1, 15);
    Node m3 = new Node(2, 2);
    c.setNeighborInstances(m2,m3);
    assertEquals("Check distance given to startDistance in Node", 29, m3.getStartDistance(), 0.00001);
  }

  /**
  * Testing the setting of node distances.
  */
  @Test
  public void setNodeDistances() {
    int[][] a = {{0,0,0,129,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,1,1,0,0,0},
            {0,0,0,0,180,0,0}};
    Path c = new Path(a ,180, 129);
    //Added the node distances to an arraylist for a path that has been constructed
    ArrayList<Node> list = c.setNodeDistances(c.createNodeArray(a));
    boolean infinityExists = false;
    //Iterate through the node distances' arraylist checking the infinitely large start distance was added.
    for (Node n : list) {
      if (n.getStartDistance() > 1000000000) {
          infinityExists = true;
      }
    }
    assertFalse("Ensure all instances got set", infinityExists);
  }

  /**
  * Testing the setting of node distances.
  */
  @Test
  public void setNodeDistances_checkifTestisGood() {
    int[][] a = {{0,0,0,129,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,1,1,0,0,0},
            {0,0,0,0,180,0,0}};
    Path c = new Path(a ,180, 129);
    //Added the node distances to an arraylist for a path that has been constructed
    ArrayList<Node> list = c.setNodeDistances(c.createNodeArray(a));
    //Set the start distance as infinitely large.
    list.get(3).setStartDistance(1000000);
    boolean infinityExists = false;
    //Iterate through the node distances' arraylist checking the infinitely large start distance was set.
    for (Node n : list) {
      if (n.getStartDistance() > 100000) {
        infinityExists = true;
      }
    }
    assertTrue("Ensure all instances got set", infinityExists);
  }

  /**
  * Test can get an arraylist of connected nodes.
  */
  @Test
  public void getConnectedNodes() {
    Path c = new Path(BIOSCIONE ,180, 129);
    Node m1 = new Node(9, 9, true);
    Node m2 = new Node(1, 7);
    Node m5 = new Node(8, 9);
    Node m6 = new Node(12, 4);
    //Set a bunch of connected nodes.
    m5.setConnectedNode(m6);
    m2.setConnectedNode(m5);
    m1.setConnectedNode(m2);
    //Check if the connected nodes can be added to an arraylist and maintain their connection.
    ArrayList<Node> list = new ArrayList<>();
    c.addNodeToList(list, m1);
    assertEquals("Check length of connected node array, should not include the EndNode", 3, c.getConnectedNodes(list).size());
  }

  /**
  * Test adding a path can be added to a grid after it has been found.
  */
  @Test
  public void addPathToGrid() {
    int[][] a = {{0,0,0,129,0,0,0},
                 {0,0,1,0,0,0,0},
                 {0,0,1,1,0,0,0},
                {0,0,0,0,180,0,0}};
    //Creat a path for the grid.
    Path c = new Path(a ,129, 180);
    Node m1 = new Node(0, 3, 0);
    Node m2 = new Node(1, 2);
    Node m3 = new Node(2, 2);
    Node m4 = new Node(2, 3);
    Node m5 = new Node(3, 4, true);
    ArrayList<Node> list = new ArrayList<>();
    c.addNodeToList(list,m1);
    c.addNodeToList(list,m2);
    c.addNodeToList(list,m3);
    c.addNodeToList(list,m4);
    c.addNodeToList(list,m5);
    ArrayList<Node> nodeDistances = c.setNodeDistances(list); //Set the node distances in an arraylist.
    ArrayList<Node> connectedNodes = c.getConnectedNodes(nodeDistances); //Connect the nodes in an arraylist.
    a = c.addPathToGrid(connectedNodes); //Add these node values to the original 2D grid.
    //Compare the created nodes with values on the grid to the hardcoded correct path for the grid.
    int[][] b = {{0,0,0,129,0,0,0},
                 {0,0,SWPATH,0,0,0,0},
                 {0,0,1,SEPATH,0,0,0},
                 {0,0,0,0,180,0,0}};
    assertTrue("Check if nodes added properly to the grid.", Arrays.deepEquals(b, a));
}

  /**
  * Test the creation of the path for a provided grid.
  */
  @Test
  public void createPath() {
    int[][] a = {{0,0,0,129,0,0,0},
    {0,0,1,0,0,0,0},
    {0,0,1,1,0,0,0},
    {0,0,0,0,180,0,0}};
    //Creat a path for the grid.
    Path c = new Path(a ,129, 180);
    Node m1 = new Node(0, 3, 0);
    Node m2 = new Node(1, 2);
    Node m3 = new Node(2, 2);
    Node m4 = new Node(2, 3);
    Node m5 = new Node(3, 4, true);
    ArrayList<Node> list = new ArrayList<>();
    c.addNodeToList(list,m1);
    c.addNodeToList(list,m2);
    c.addNodeToList(list,m3);
    c.addNodeToList(list,m4);
    c.addNodeToList(list,m5);
    //Compare the created path on the grid to the hardcoded correct path for the grid.
    int[][] b = {{0,0,0,129,0,0,0},
    {0,0,SWPATH,0,0,0,0},
    {0,0,1,SEPATH,0,0,0},
    {0,0,0,0,180,0,0}};
    assertTrue("Check if nodes added properly to the grid.", Arrays.deepEquals(b, c.createPath()));
  }
}
