package resources;

/**
* This class is for built FloorPlans based
* on the floorplans of a building.
*
* NUMBERS       CORRESPONDING ROOM
*    0          Wall
*    1          Hallway - only thing path can move through
*    9          Portions of rooms
*    25         Constants.STAIRs
*   >= 100      Room Numbers, represent doors
*  888 Bathrooms
*/

public class builtFloorPlans{

public static final int[][] TFDLGROUND =
{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
{0, 1011, 1011, 1011, 1011, 1011, 1011, 1011, 0, 1015, 1015, 1015, 1015, 1015, 1015, 0},
{0, 1011, 1011, 1011, 1011, 1011, 1011, 1011, 0,1015, 1015, 1015, 1015, 1015, 1015, 0},
{0, 1011, 1011, 1011, 1011, 1011, 1011, 1011, 0, 1015, 1015, 1015, 1015, 1015, 1015, 0},
{0, 1011, 1011, 1011, 1011, 1011, 0, 0, 0, 0, 0, 0, 0, 1015, 1015, 0},
{0, 1011, 1011, 1011, 1011, 1011, 0, 1012, 1012, 1012, 1012, 1012, 0, 1015, 1015,  0},
{0, 1011, 1011, 11, 1011, 1011, 0, 1012, 1012, 1012, 1012, 1012, 0, 1015, 1015,  0},
{0, 1011, 1011, 1, 1, 1, 1, 12, 1012, 1012, 1012, 1, 1, 15, 1015, 0},
{0, 1011, 1011, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
{0, 1011, 1011, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1019, 1019, 0},
{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1019, 1019, 0},
{0, 1039, 39, 1, 1, 1038, 1038, 0, 10, 1010, 1, 1, 1, 19, 1019, 0},
{0, 1039, 1039, 1, 1, 1038, 1038, 0, 1010, 1010, 1, 1, 1, 1019, 1019, 0},
{0, 1039, 1039, 1, 1, 38, 1038, 0, 1010, 1010, 1, 1, 1, 1019, 1019, 0},
{0, 1039, 1039, 1, 1, 0, 0, 1010, 1010, 1010, 1, 1, 1, 1019, 1019, 0},
{0, 1039, 1039, 1, 1, 17, 1017, 1010, 1010, 1010, 1, 1, 1, 1019, 1019, 0},
{0, 1039, 1039, 1, 1, 23, 1023, 0, 0, 0, 1, 1, 1, 1019, 1019, 0},
{0, 1039, 1039, 1, 1, 31, 1031, 0, 1022, 22, 1, 1, 1, 0, 0, 0},
{0, 1039, 1039, 1, 1, 1031, 1031, 0, 1022, 1022, 1, 1, 1, 21, 1021, 0},
{0, 0, 0, 1, 1, 1031, 1031, 0, 1022, 1022, 1, 1, 1, 0, 0, 0},
{0, 1035, 1035, 1, 1, 1031, 1031, 0, 1022, 1022, 1, 1, 1, 37, 1037, 0},
{0, 1035, 1035, 1, 1, 0, 0, 0, 1022, 1022, 1, 1, 1, 1037, 1037, 0},
{0, 1035, 35, 1, 1, 14, 1014, 0, 1022, 1022, 1, 1, 1, 1037, 1037, 0},
{0, 1035, 1035, 1, 1, 1014, 1014, 0, 1022, 1022, 1, 1, 1, 1037, 1037, 0},
{0, 1035, 1035, 1, 1, 1014, 1014, 0, 1022, 1022, 1, 1, 1, 0, 0, 0},
{0, 1035, 1035, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1002, 1002, 0},
{0, 1035, 1035, 1, 1, 18, 1018, 0, 1020, 20, 1, 1, 1, 1002, 1002, 0},
{0, 1035, 1035, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1002, 1002, 0},
{0, 1035, 1035, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1002, 0},
{0, 1035, 1035, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
{0, 1035, 1035, 1, 1, 1, 1, 1010, 1010, 1, 1, 1, 1, 1013, 1013, 0},
{0, 1035, 1035, 0, 10, 1010, 1010, 1010, 1010, 0, 13, 1013, 1013, 1013,1013,  0},
{0, 1035, 1035, 0, 1010, 1010, 1010, 1010, 1010, 0, 1013, 1013, 1013, 1013, 1013, 0},
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
//17 is Bathroom, 18/20 is elevator, 21/23 is stairs

public static final int[][] TFDLONE =
{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 153, 1153, 0, 0, 0, 1, 1, 0},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1153, 1153, 0, 0, 1, 1, 1, 0},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 152, 1152, 0, 0, 1, 155, 1155, Constants.REST},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1150, 1150, 0, 0, 1, 154, 1154, Constants.REST},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 150, 1150, 0, 0, 1, 1, 125, 1125},
{0, 1, 1, 1151, 1151, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0},
{0, 1, 1, 151, 1151, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
{0, 1, 1, Constants.COFF, 1151, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 170, 1170},
{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

public static final int[][] TFDLTWO =
{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
{0, 0, 1, 1, 1, 1, 1, 1, 1252, 1252, 9, 9, 1, 1, 0, 0, 0, 0},
{0, 0, 1, 1, 1, 1, 1, 1, 252, 1252, 9, 9, 1, 1, 0, 0, 0, 0},
{0, 0, 1, 1, 261, 1261, 260, 1260, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
{0, 0, 1, 1, 1261, 1261, 1260, 1260, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
{0, 0, 1, 1, 1262, 1262, 1263, 1263, 1264, 1264, 1, 1, 1, 1, 0, 0, 0, 0},
{0, 0, 1, 1, 1262, 262, 1263, 263, 1264, 264, 1, 1, 1, 1, 0, 0, 0, 0},
{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 251, 1251, 1251,0},
{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1251, 1251, 1251, 0},
{0, 0, 1, 1, 1259, 259, 1259, 1259, 1259, 1259, 1, 1, 1, 1, 250, Constants.REST, 1250, 0},
{0, 0, 1, 1, 1259, 1259, 1259, 1259, 1259, 1259, 1, 1, 1, 1, 9, 9, 9, 0},
{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 225, 1225, 0, 0},
{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 270, 1270, 0, 0},
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

public static final int[][] TFDLSIX =
{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
{0,1640, 1640, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1},
{0,1640, 640, 1, 1,1647, 1647, 1, 1, 1648, 1648, 1648, 0, 1636, 1636, 0, 1638, 1638, 0, 639,0, 1653, 1653, 1, 1, 1652,0  },
{0,0,0, 1, 1,1647, 1647, 1, 1, 1648, 1648, 1648, 0, 1636, 1636, 0, 1638, 1638, 0, 0,0, 1653, 1653, 1,1, 1652, 0},
{0,1641, 1641, 1, 1,1647, 1647,  1, 1, 0, 0, 0, 0, 0, 0,0,0,1638, 1638,0 , 1653, 1653,1653, 1653, 1, 1, 1652, 0},
{0,1641, 641, 1, 1,0,0, 1, 1,  1649, 1649,1649,  0, 0, 1638, 1638, 1638,0 , 1653, 1653,1653, 1653, 1, 1, 1652, 0},
{0,0,0, 1, 1, 1646, 1646, 1, 1,1649, 1649, 1649, 0, 0,0,0,0,0,0,0,0,0,0,0,1,1,1652,0},
{0,1642, 642, 1, 1,1646, 1646, 1, 1,0,0,0,0, 0, 1656, 1656, 1656, 1656, 0 ,0,0, 1,1, 1652, 0},
{0,1642, 1642, 1, 1,1646, 1646,  1, 1,1639, 1639,1639, 0, 0,1656, Constants.REST, 1656, 1656, 0, 1657, 1657, 1, 1, 1652, 0},
{0,0,0, 1, 1, 1, 1, 1, 1, 1639, 1639, 1639, 0, 0,0,0,0,0,0,0, 1657, 1657, 1, 1, 1652, 0},
{0,1643, 643, 1, 1, 1, 1, 1, 1, 1, 1, 1630, 1, 1,1655, Constants.REST, 1655, 1655, 1,1 , 1, 1, 1, 1652, 0},
{0,1643, 1643, 1, 1, 1, 1, 1, 1, 1, 1, 1630, 1, 1, 1655, 1655, 1655, 1655, 1,1, 1, 1 ,1, 1652, 0},
{0,1643, 1643,0,1644,644,0, 1645, 1645,  1, 1,1630, 1, 1,1655, 1655, 1655, 1655, 1,1, 1659, 1659, 1, 1, 1652, 0},
{0,1643, 1643,0,1644,1644,0, 1645, 1645,  1, 1, 1630, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1659, 1659, 1, 1, 1652, 0},
{0, 0, 0, 0, 0, 0, 0, 0, 0,1 ,1, 1630, 1, 1,1603, 1603, 1603, 1603, 1, 1 },
{0, 1631, 1631, 1631, 1631,1631, 1631,1631, 1631,1,1,1,1,1,1603, 1603, 1603, 1603, 1, 1  },
{0, 1631, 1631, 1631, 1631,1631, 1631,1631, 1631,1,1,1,1,1,1603, 1603, 1603, 1603, 1, 1 },
{0, 1631, 1631, 1631, 1631,1631, 1631,1631, 1631,1,1,1632,1,1, 1603, 1603, 1603, 1603, 1, 1 },
{0, 1631, 1631, 1631, 1631,1631, 1631,1631, 1631,1,1,1632,1,1, 0, 0, 0, 0, 1, 1},
{0, 0, 0, 0, 0, 0, 0, 0, 0,1 ,1, 1, 1, 1,10,0,1623,1623,1623,1,1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1,10,0,1623,1623,1623, 1,1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1,0,0, 0, 0,1,1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1,11,0,1624,1624,1624, 1, 1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1, 11, 0,1624,1624,1624, 1,1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1,0,0,0,0,0,1,1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1, 12,0,1635,1635,1635,1, 1 },
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1, 12,0,1635,1635,1635,1, 1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1, 0,0,1635,1635,1635,1, 1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1, 1635,1635,1635,1635,1635,1, 1},
{0, 1625, 1625, 1625, 1625, 1625, 1,1,1,1,1,1,1,1}

};
//10,11,12 is elevator
}