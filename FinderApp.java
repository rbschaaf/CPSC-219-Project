import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.beans.value.*;
import javafx.scene.control.ScrollPane.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.EOFException;


public class FinderApp extends Application {

  /*
  * Runs the GUI application, including scene creation, button handling
  * and the production of a visual grid.
  *
  * Last edited March 19, 2018 by Nicki.
  */

  private Map map1 = new Map();
  private GridPane gridPane = new GridPane();
  private int roomNumbers = 0;
  private Label invalidEntry = new Label("");
  private Label buildingAndFloorLabel = new Label("");
  private int rectLength;
  private int roomNumberFontSize = 10;
  private int startNumberInput;
  private int destNumberInput;
  private String buildingInput;
  private int clickedRoom;
  private VBox enterStartRoomVBox = new VBox();
  private VBox enterDestRoomVBox = new VBox();
  private Rectangle[][] rectangleGrid = new Rectangle[Constants.MAX_GRIDSIZE][Constants.MAX_GRIDSIZE];
  private Button startRoomButton = new Button("Store selection in start room");
  private Button destRoomButton = new Button("Store selection in destination room");
  private boolean stairs = false;
  private FloorPlans currentFloorPlan;
  private VBox eleStairBox = new VBox(10);
  private VBox mapSize = new VBox();
  private String fileName;
  private FloorPlans updatedPlan;

  private int[] notMap = {Constants.WALL, Constants.HALL, Constants.ROOM, Constants.START, Constants.DEST,
    Constants.PATH, Constants.REST, 1270, 1170, 1171, 1172, 1225, 1125, Constants.COFF};

  private TextField enterStartRoom = new TextField("Enter the start room");
  private TextField enterDestRoom = new TextField("Enter destination room");
  private ComboBox<String> buildingDropDown = new ComboBox<String>();
  private ComboBox<String> savedPathDropDown = new ComboBox<String>();
  //https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm

  private ToggleGroup sizeGroup = new ToggleGroup();
  private RadioButton smallButton = new RadioButton("Small Map");
  private RadioButton mediumButton = new RadioButton("Medium Map");
  private RadioButton largeButton = new RadioButton("Large Map");
  //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/RadioButton.html

  private Image imgRestroom = new Image("RestroomImage.png", rectLength, rectLength, true, false);
  //Image source: http://maxpixel.freegreatpicture.com/Rest-Room-Restroom-Ladies-Restroom-Public-Restroom-99226
  private Image imgCoffee = new Image("Coffee.png", rectLength, rectLength, true, false);
  //Image source: https://www.freepik.com/free-icon/hot-coffee-rounded-cup-on-a-plate-from-side-view_732944.htm
  private Image imgStairs = new Image("Stairs.png", rectLength, rectLength, true, false);
  //Image source:https://pixabay.com/en/stairs-climb-levels-descend-44071/
  private Image imgElevator = new Image("Elevator.png", rectLength, rectLength, true, false);
  //Image source: https://pixabay.com/en/elevator-people-silhouette-down-44013//

  /**
   * Handle class that deals with the clicking of the elevator button.
   * Creates the first part of the temporary path wrt elevator.
   */
  public class HandleElevatorClick implements EventHandler<ActionEvent> {
    public HandleElevatorClick() {
    }

    public void handle(ActionEvent even) {
      // Since we are using the elevator, we want to appear on the next floor
      // at the elevator tile.
      stairs = false;

      // Create a temporary floor plan for part one of the path.
      FloorPlans tempFloorPlan = new FloorPlans(buildingInput, startNumberInput);
      map1.setCurrentFloorPlan(tempFloorPlan);

      if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 1) {
        // If we are on the first floor we want our temporary destination to
        // be the elevator that is on the first floor.
        tempFloorPlan.setTemporaryDestNum(170);
      } else if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 2) {
        // If we are on the second floor we want our temporary destination to
        // be the elevator that is on the second floor.
        tempFloorPlan.setTemporaryDestNum(270);
      }

      invalidEntry.setText("");

      // Create a new path between the start number and the temporary dest.
      Path tempPath = new Path(tempFloorPlan.getGrid(),
        startNumberInput, tempFloorPlan.getTemporaryDestNum());

      map1.setStartValues(tempFloorPlan, startNumberInput);
      map1.setEndValues(tempFloorPlan, tempFloorPlan.getTemporaryDestNum());

      int[][] finalGrid2 = tempPath.createPath();

      map1.placeStart(finalGrid2);
      map1.placeDest(finalGrid2);

      // Update the grid with the temporary path to the elevator.
      makeGrid(finalGrid2, gridPane, rectLength);
    }
  }

  /**
   * Handle class that deals with the clicking of the stair button.
   * Creates the first part of the temporary path wrt stairs.
   */
  public class HandleStairClick implements EventHandler<ActionEvent> {
    public HandleStairClick() {
    }

    public void handle(ActionEvent event) {
      // Since we are using the stairs, we want to appear on the next floor
      // at the stairs tile.
      stairs = true;

      FloorPlans tempFloorPlan1 = new FloorPlans(buildingInput, startNumberInput);
      map1.setCurrentFloorPlan(tempFloorPlan1);
      if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 1) {
        tempFloorPlan1.setTemporaryDestNum(125);
      } else if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 2) {
        tempFloorPlan1.setTemporaryDestNum(225);
      }

      invalidEntry.setText("");

      // Create a new path and set its start and dest inputs.
      Path tempPath1 = new Path(tempFloorPlan1.getGrid(),
        startNumberInput, tempFloorPlan1.getTemporaryDestNum());

      map1.setStartValues(tempFloorPlan1, startNumberInput);
      map1.setEndValues(tempFloorPlan1, tempFloorPlan1.getTemporaryDestNum());

      int[][] finalGrid3 = tempPath1.createPath();

      map1.placeStart(finalGrid3);
      map1.placeDest(finalGrid3);

      //Update the grid with the temporary path to the stairs.
      makeGrid(finalGrid3, gridPane, rectLength);
    }
  }

  /**
   * Handle class that deals with the clicking of the next floor click.
   * Creates the second part of the temporary path.
   */
  public class HandleNextFloorClick implements EventHandler<ActionEvent> {
    public HandleNextFloorClick() {
    }

    public void handle(ActionEvent event) {
      mapSize.getChildren().remove(eleStairBox);
      // Determine the start location on the new floor based on which
      // route was taken (elevator/stairs) and which floor the user was on.
      if (stairs == true) {
        if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 1) {
          startNumberInput = 225;
        } else if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 2) {
          startNumberInput = 125;
        }
      } else {
        if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 1) {
          startNumberInput = 270;
        } else if (map1.getCurrentFloorPlan().getFloorNum(startNumberInput) == 2) {
          startNumberInput = 170;
        }

      }

      FloorPlans tempFloorPlan2 = new FloorPlans(buildingInput, destNumberInput);
      map1.setCurrentFloorPlan(tempFloorPlan2);

      invalidEntry.setText("");

      // Create a new path and set its start and dest inputs.
      Path tempPath2 = new Path(tempFloorPlan2.getGrid(),
        startNumberInput, destNumberInput);

      map1.setStartValues(tempFloorPlan2, startNumberInput);
      map1.setEndValues(tempFloorPlan2, destNumberInput);

      int[][] finalGridpt2 = tempPath2.createPath();

      map1.placeStart(finalGridpt2);
      map1.placeDest(finalGridpt2);

      makeGrid(finalGridpt2, gridPane, rectLength);

      //Update the floor label
      buildingAndFloorLabel.setText(setBuildingAndFloorLabel(tempFloorPlan2.getFloorNum(destNumberInput), buildingInput));
      buildingAndFloorLabel.setTextFill(Color.GREEN);

      // Reset the stairs boolean variable.
      stairs = false;
    }
  }

  /**
   * Method that allows the user to click a start room on the grid.
   * Removes the start and dest room buttons on click.
   */
  public class HandleStartRoomClick implements EventHandler<ActionEvent> {
    int row1;
    int col1;
    int rNum;

    public HandleStartRoomClick(int aRow, int aCol, int roomN) {
      rNum = roomN;
      row1 = aRow;
      col1 = aCol;
    }

    public void handle(ActionEvent event) {
      enterStartRoom.setText("" + rNum);
      rectangleGrid[row1][col1].setFill(Color.LIGHTBLUE);
      enterStartRoomVBox.getChildren().remove(startRoomButton);
      enterDestRoomVBox.getChildren().remove(destRoomButton);
    }
  }

  /**
   * Method that handles the clicking of a room button/room number on the map.
   * Allows user to select to store the room value in the start room or destination
   * room. The user can also unselect the room by clicking the temporary button generated
   * at it.
   * http://code.makery.ch/blog/javafx-8-event-handling-examples/
   */
  public class HandleRoomClick implements EventHandler<ActionEvent> {
    int roomNum;
    int count = 0;
    StackPane aSP;
    int rowLength;
    int colLength;

    public HandleRoomClick(int aRowLength, int aColLength, int roomNumber, StackPane aStackPane) {
      roomNum = roomNumber;
      aSP = aStackPane;
      rowLength = aRowLength;
      colLength = aColLength;
    }

    public void handle(ActionEvent event) {
      int row;
      int col;

      enterStartRoomVBox.getChildren().remove(startRoomButton);
      enterDestRoomVBox.getChildren().remove(destRoomButton);
      for (row = 0; row < rowLength; row++) {
        for (col = 0; col < colLength; col++) {
          if (map1.getCurrentFloorPlan().getGrid()[row][col] == roomNum && count == 0) {

            rectangleGrid[row][col].setFill(Color.RED);
            count += 1;
            enterStartRoomVBox.getChildren().add(startRoomButton);
            enterDestRoomVBox.getChildren().add(destRoomButton);

              /*
              * Clicking the button below the Start room textfield stores the selected room
              * number in the textfield.
              */
            startRoomButton.setOnAction(new HandleStartRoomClick(row, col, roomNum));

              /*
              * Clicking the button below the Destination room textfield stores the selected room
              * number in the textfield.
              */
            destRoomButton.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent event) {
                enterDestRoom.setText("" + roomNum);
                enterStartRoomVBox.getChildren().remove(startRoomButton);
                enterDestRoomVBox.getChildren().remove(destRoomButton);
              }
            });
          } else if (map1.getCurrentFloorPlan().getGrid()[row][col] == roomNum && count == 1) {
            rectangleGrid[row][col].setFill(Color.LIGHTBLUE);
            count = 0;
          }
        }
      }
    }
  }

  /**
   * Handle button click for Submit button. Takes text from Text Fields and
   * creates the grid.
   */
  public class HandleButtonClick implements EventHandler<ActionEvent> {
    public HandleButtonClick() {
    }

    public void handle(ActionEvent event) {
      // Reset the error label.
      invalidEntry.setText("");

      // Get input from the textfields.
      try {
        startNumberInput = Integer.parseInt(enterStartRoom.getText());
        destNumberInput = Integer.parseInt(enterDestRoom.getText());
      } catch (NumberFormatException e) {
        invalidEntry.setText("Please enter the room number as an integer.");
      }
      buildingInput = buildingDropDown.getValue();
      updatedPlan = new FloorPlans(buildingInput, startNumberInput);

      // Create a new FloorPlan and set it as the current floorplan of the map.
      FloorPlans dummyPlan = new FloorPlans(buildingInput, 250);
      FloorPlans dummyPlan1 = new FloorPlans(buildingInput, 150);
      map1.setCurrentFloorPlan(updatedPlan);

      // Check if the numbers entered by the user are valid.
      isValidStartRoom(startNumberInput, updatedPlan);
      isValidDestRoom(destNumberInput, updatedPlan);

          /*
          * If the start room and destination rooms are valid for the current
          * floor, create a 1-part path.
          */
      if (isValidStartRoom(startNumberInput, updatedPlan)
        && isValidDestRoom(destNumberInput, updatedPlan)) {
        invalidEntry.setText("");

        // Create a new path and set its start and dest inputs.
        Path path = new Path(updatedPlan.getGrid(),
          startNumberInput, destNumberInput);

        map1.setStartValues(updatedPlan, startNumberInput);
        map1.setEndValues(updatedPlan, destNumberInput);

        int[][] finalGrid = path.createPath();

        map1.placeStart(finalGrid);
        map1.placeDest(finalGrid);

        makeGrid(finalGrid, gridPane, rectLength);

        // Highlight the room chosen as the destination in blue.
        highlight(updatedPlan, destNumberInput);

        // Updates the label above the map providing building name and floor number
        buildingAndFloorLabel.setText(setBuildingAndFloorLabel(updatedPlan.getFloorNum(startNumberInput), buildingInput));
        buildingAndFloorLabel.setTextFill(Color.GREEN);
        //http://www.java2s.com/Code/Java/JavaFX/SetLabelTextcolor.htm

          /*
          * If the start room is valid for the currents floor and the destination
          * is invalid for the current floor, check if the destination is valid
          * on floor 2. If it is, create part 1 of a temporary path.
          */
      } else if (isValidStartRoom(startNumberInput, updatedPlan) == true
        && isValidDestRoom(destNumberInput, updatedPlan) == false
        && isValidDestRoom(destNumberInput, dummyPlan) == true) {

        // Communicate with the user that their destination is on a separate floor.
        invalidEntry.setText("Your destination is on a different floor. Use the elevator and stair buttons" +
          "on the right to choose which path you want to take to the next floor.");

        //Add buttons for the next floor.
        if (mapSize.getChildren().contains(eleStairBox) != true) {
          mapSize.getChildren().addAll(eleStairBox);
        }


        int destNum = updatedPlan.getFloorNum(destNumberInput);
        System.out.println("Dest room is invalid.");

        //Make a grid when the destination is one floor higher than the user.
        if (destNum == updatedPlan.getFloorNum(startNumberInput) + 1) {
          updatedPlan.setTemporaryDestNum(170);
          invalidEntry.setText("");

          // Create a new path and set its start and dest inputs.
          Path path = new Path(updatedPlan.getGrid(),
            startNumberInput, updatedPlan.getTemporaryDestNum());

          map1.setStartValues(updatedPlan, startNumberInput);
          map1.setEndValues(updatedPlan, updatedPlan.getTemporaryDestNum());

          int[][] finalGrid = path.createPath();

          map1.placeStart(finalGrid);
          map1.placeDest(finalGrid);

          makeGrid(finalGrid, gridPane, rectLength);
        }

            /*
            * If the start room is valid for the currents floor and the destination
            * is invalid for the current floor, check if the destination is valid
            * on floor 1. If it is, create part 1 of a temporary path.
            */
      } else if (isValidStartRoom(startNumberInput, updatedPlan) == true && isValidDestRoom(destNumberInput, updatedPlan) == false
        && isValidDestRoom(destNumberInput, dummyPlan1) == true) {
        invalidEntry.setText("Your destination is on a different floor. Use the elevator and stair buttons" +
          "on the right to choose which path you want to take to the next floor.");
        //Add buttons for the next floor.
        if (mapSize.getChildren().contains(eleStairBox) != true) {
          mapSize.getChildren().addAll(eleStairBox);
        }

        int destNum = updatedPlan.getFloorNum(destNumberInput);
        System.out.println("Dest room is invalid.");

        //Make a grid if the destination is a floor below the user.
        if (destNum == updatedPlan.getFloorNum(startNumberInput) - 1) {
          updatedPlan.setTemporaryDestNum(270);
          invalidEntry.setText("");

          // Create a new path and set its start and dest inputs.
          Path path = new Path(updatedPlan.getGrid(),
            startNumberInput, updatedPlan.getTemporaryDestNum());

          map1.setStartValues(updatedPlan, startNumberInput);
          map1.setEndValues(updatedPlan, updatedPlan.getTemporaryDestNum());

          int[][] finalGrid = path.createPath();

          map1.placeStart(finalGrid);
          map1.placeDest(finalGrid);

          makeGrid(finalGrid, gridPane, rectLength);
        }
      } else if (isValidStartRoom(startNumberInput, updatedPlan) == false && isValidStartRoom(startNumberInput, updatedPlan) == false) {
        System.out.println("No valid start/dest info. added");
      }
    }
  }

  /**
   * Method to set the building and floor label above the map. Returns a string for the label
   * and requires a floor number and building name.
   *
   * @param floorNumber  floor number to use on the floor label.
   * @param buildingName building name to use on the floor label.
   */
  public String setBuildingAndFloorLabel(int floorNumber, String buildingName) {
    String currentBuildingAndFloor = "";
    if (floorNumber > 0) {
      if (floorNumber % 10 == 1) {
        currentBuildingAndFloor = buildingName + " " + floorNumber + "st Floor";
      } else if (floorNumber % 10 == 2) {
        currentBuildingAndFloor = buildingName + " " + floorNumber + "nd Floor";
      } else if (floorNumber % 10 == 3) {
        currentBuildingAndFloor = buildingName + " " + floorNumber + "rd Floor";
      } else {
        currentBuildingAndFloor = buildingName + " " + floorNumber + "th Floor";
      }
    } else {
      currentBuildingAndFloor = buildingName + " Ground Floor";
    }
    return currentBuildingAndFloor;
  }


  /**
   * Method to confirm whether an array contains a value.
   *
   * @param anArray an array to search.
   * @param aValue  the value to find in the array.
   * @return boolean value for if value is in the array.
   */
  public boolean contains(int[] anArray, int aValue) {
    boolean result = false;
    for (int i : anArray) {
      if (i == aValue) {
        result = true;
      }
    }
    return result;
  }

  /**
   * Method to highlight a room in the Grid (change colours of member tiles).
   *
   * @param aFloorPlan floorplan to add highlights to.
   * @param roomNumber number of room to highlight.
   */
  public void highlight(FloorPlans aFloorPlan, int roomNumber) {
    int roomNum = roomNumber;
    for (int row = 0; row < aFloorPlan.getRowLength(); row++) {
      for (int col = 0; col < aFloorPlan.getColLength(row); col++) {
        //Room colours
        if (aFloorPlan.getRoom(roomNum + 1000) != null) {
          if (aFloorPlan.getGrid()[row][col] == aFloorPlan.getRoom(roomNum + 1000).getRoomsNumber()) {
            if (aFloorPlan.getGrid()[row][col] != 1270 && aFloorPlan.getGrid()[row][col] != 1170
              && aFloorPlan.getGrid()[row][col] != 1225 && aFloorPlan.getGrid()[row][col] != 1125) {
              rectangleGrid[row][col].setFill(Color.BLUE);
            }

                /*//Door colours
              }else if((aFloorPlan.getGrid()[row][col]) == roomNumber)
              rectangleGrid[row][col].setFill(Color.AQUAMARINE);
            }*/
          }
        }
      }
    }
  }

  /**
   * Generate a visual of the current grid.
   *
   * @param aGrid      an integer grid to take data from.
   * @param aGridPane  a gridPane to add the grid's data to.
   * @param rectLength the length of the rectangles to be created.
   */
  public void makeGrid(int[][] aGrid, GridPane aGridPane, int rectLength) {
    // Clear the gridPane.
    aGridPane.getChildren().clear();

    // Set up the grid for the floor.
    for (int row = 0; row < aGrid.length; row++) {
      for (int col = 0; col < aGrid[row].length; col++) {
        int rowLength = aGrid.length;
        int colLength = aGrid[row].length;
        Rectangle rect = setRectangles(row, col, aGrid, rectLength);

        int roomNumbers = 0;

        roomNumbers = aGrid[row][col];

        // An overlaying stackpane that is mouse transparent containing the rect and room labels.
        StackPane overlayStack = new StackPane();

        //Add room numbers to the grid map.
        Label rooms = setNumbers(roomNumbers);

        overlayStack.getChildren().addAll(rect, rooms);
        overlayStack.setMouseTransparent(true); //https://stackoverflow.com/questions/9899347/when-adding-a-second-item-to-my-stackpane-the-first-item-loses-its-event-mouseo

        // Complete stackpane containing the overlay stackpane and the clickable buttons hidden underneath.
        StackPane stack = new StackPane();

        //Add buttons at the room numbers on the map, if there is a room number there.
        if (rooms.getText().isEmpty()) { //https://stackoverflow.com/questions/25189027/how-to-check-if-lable-is-not-empty
          stack.getChildren().add(overlayStack);
        } else {
          Button roomButton = createRoomButtons(roomNumbers, startNumberInput, destNumberInput, stack, rowLength, colLength);
          roomButton.setMaxSize(rectLength, rectLength); //https://stackoverflow.com/questions/35344702/how-do-i-get-buttons-to-fill-a-javafx-gridpane
          stack.getChildren().addAll(roomButton, overlayStack);
        }

        GridPane.setRowIndex(stack, row);
        GridPane.setColumnIndex(stack, col);
        aGridPane.getChildren().add(stack);
        //https://stackoverflow.com/questions/35367060/gridpane-of-squares-in-javafx
      }
    }
  }

  /**
   * Method that sets the rectangles for the grid.
   *
   * @param row        the row to place the rectangle
   * @param col        the column to place the rectangle
   * @param aGrid      an integer grid to take data from.
   * @param rectLength the length of the rectangles to be created.
   * @return a rectangle.
   */
  public Rectangle setRectangles(int row, int col, int[][] aGrid, int rectLength) {

    Rectangle rect = new Rectangle();
    if (aGrid[row][col] == 0) {
      rect.setFill(Color.BLACK);
    } else if (aGrid[row][col] == Constants.HALL) {
      rect.setFill(Color.TRANSPARENT);
    } else if (aGrid[row][col] == Constants.ROOM) {
      rect.setFill(Color.GREY);
    } else if (aGrid[row][col] == Constants.DEST) {
      rect.setFill(Color.YELLOW);
    } else if (aGrid[row][col] == Constants.START) {
      rect.setFill(Color.ORANGE);
    } else if (aGrid[row][col] == Constants.PATH) {
      rect.setFill(Color.GREEN);
    } else if (aGrid[row][col] == Constants.REST) {
      rect.setFill(new ImagePattern(imgRestroom));
      //https://stackoverflow.com/questions/22848829/how-do-i-add-an-image-inside-a-rectangle-or-a-circle-in-javafx
    } else if (aGrid[row][col] == 1125 || aGrid[row][col] == 1225) {
      rect.setFill(new ImagePattern(imgStairs));
    } else if (aGrid[row][col] == 1170 || aGrid[row][col] == 1171
      || aGrid[row][col] == 1172 || aGrid[row][col] == 1270) {
      rect.setFill(new ImagePattern(imgElevator));
    } else if (aGrid[row][col] == Constants.COFF) {
      rect.setFill(new ImagePattern(imgCoffee));
    } else {
      rect.setFill(Color.LIGHTBLUE);
    }
    rect.setStroke(Color.BLACK);
    rect.setWidth(rectLength);
    rect.setHeight(rectLength);

    rectangleGrid[row][col] = rect;

    return rect;
  }

  /**
   * Method that sets number labels for tiles in the grid.
   *
   * @param roomNumbers the integer number to set.
   * @return a label with the room number.
   */
  public Label setNumbers(int roomNumbers) {
    Label rooms = new Label("");
    if (roomNumbers < 1000) {
      if (contains(notMap, roomNumbers) == false) {
        rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
        rooms.setText("" + roomNumbers);
      } else if (roomNumbers == Constants.START) {
        rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
        rooms.setText("" + "S");
      } else if (roomNumbers == Constants.DEST) {
        rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
        rooms.setText("" + "D");
      }
    }
    return rooms;
  }

  /**
   * Method that creates buttons for the room numbers on the map.
   *
   * @param roomNumbers      a room number on the grid.
   * @param startNumberInput an integer representing the start room number.
   * @param destNumberInput  an integer representing the destination room number.
   * @param stack            a stackPane to add the buttons to.
   */
  public Button createRoomButtons(int roomNumbers, int startNumberInput, int destNumberInput, StackPane stack, int rowLength, int colLength) {
    Button aRoomButton = new Button();
    if (contains(notMap, roomNumbers) == false) {
      aRoomButton = new Button("" + roomNumbers);

    } else if (roomNumbers == Constants.START) {
      aRoomButton = new Button("" + startNumberInput);

    } else if (roomNumbers == Constants.DEST) {
      aRoomButton = new Button("" + destNumberInput);

    }
    Integer roomNumberValue = Integer.parseInt(aRoomButton.getText());

        /*
        * Clicking a room button is handled by HandleRoomClick.
        */
    aRoomButton.setOnAction(new HandleRoomClick(rowLength, colLength, roomNumberValue, stack));
    return aRoomButton;
  }

  /**
   * Method to check if a valid start value is entered.
   *
   * @param aStartRoom the room number entered by the user
   * @return isValidStart a boolean
   */
  public boolean isValidStartRoom(int aStartRoom, FloorPlans aFP) {
    boolean isValidStart = false;
    ArrayList<Room> listOfRooms = aFP.getRoomList();
    for (int i = 0; i < listOfRooms.size(); i++) {
      int number = (listOfRooms.get(i).getRoomsNumber()) - 1000;
      if (aStartRoom == number) {
        isValidStart = true;
        System.out.println("Start room is valid");
      } else {
        invalidEntry.setText("Please enter a valid start room. Example: 262 or 264");
      }
    }
    return isValidStart;
  }

  /**
   * Method to check if a valid destination value is entered.
   *
   * @param aDestRoom the room number entered by the user
   * @return isValidDest a boolean
   */
  public boolean isValidDestRoom(int aDestRoom, FloorPlans aFP) {
    boolean isValidDest = false;
    ArrayList<Room> listOfRooms = aFP.getRoomList();
    for (int i = 0; i < listOfRooms.size(); i++) {
      int number = (listOfRooms.get(i).getRoomsNumber()) - 1000;
      if (aDestRoom == number) {
        isValidDest = true;
        System.out.println("Dest room is valid");
      } else {
        invalidEntry.setText("Please enter a valid destination room. Example: 262 or 264");
      }
    }
    return isValidDest;
  }

  /**
   * Method to write the current path to a file of a name the user chooses.
   * Adapted from https://stackoverflow.com/questions/34958829/how-to-save-a-2d-array-into-a-text-file-with-bufferedwriter
   */
  public void writeToFile() {
    //BufferedWriter outputStream = null;
    //  try{
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      //}catch(IOException e){
      //  System.out.println("Error opening output file" + fileName);
      //}

      StringBuilder builder = new StringBuilder();
      int[][] aGrid = updatedPlan.getGrid();
      for (int i = 0; i < aGrid.length; i++)//for each row
      {
        for (int j = 0; j < aGrid[i].length; j++)//for each column
        {
          //int currentRow = updatedPlan.getCurrentRow(i);
          //int currentCol = updatedPlan.getCurrentCol(j);
          builder.append(aGrid[i][j] + "");//append to the output string
          if (j < aGrid[i].length - 1)//if this is not the last row element
            builder.append(",");//then add comma (if you don't like commas you can use spaces)
        }
        builder.append("\n");//append new line at the end of the row
      }
      //BufferedWriter writer = new BufferedWriter(new FileWriter("/c:/sudoku" + date + ".txt"));
      writer.write(builder.toString());//save the string representation of the board
      writer.close();
      System.out.println("Map saved");
      savedPathDropDown.getItems().add(fileName);
    } catch (FileNotFoundException e) {
      System.out.println("Problem opening the file" + fileName);
    } catch (IOException e) {
      System.out.println("Problem with output to file" + fileName);
    }
  }

  /**
   * Method to read a saved path from a file of a name saved in the savedPathDropDown combobox.
   * Adapted from https://stackoverflow.com/questions/34958829/how-to-save-a-2d-array-into-a-text-file-with-bufferedwriter
   */
  public void readFromFile() {

    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
    } catch (IOException e) {
      System.out.println("Error opening output file" + fileName);
    }
    try {
      int[][] aGrid = new int[Constants.MAX_GRIDSIZE][Constants.MAX_GRIDSIZE];
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String line = "";
      int row = 0;
      FloorPlans savedFloorPlan = new FloorPlans();
      while ((line = reader.readLine()) != null) {
        String[] cols = line.split(","); //note that if you have used space as separator you have to split on " "
        int col = 0;
        for (String c : cols) {
          aGrid[row][col] = Integer.parseInt(c);
          col++;
        }
        row++;
      }
      reader.close();
      System.out.println("End of reading from file.");
      savedFloorPlan.printSavedGrid(aGrid);


    } catch (FileNotFoundException e) {
      System.out.println("Problem opening file" + fileName);
    } catch (EOFException e) {
      System.out.println("Problem reading1 the file" + fileName);
    } catch (IOException e) {
      System.out.println("Problem reading2 the file" + fileName);
    }
  }

  @Override
  public void start(Stage primaryStage) {

    //**
    // Welcome screen (first scene)
    //**

    BorderPane borderPanes1 = new BorderPane();
    borderPanes1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)");
    //https://stackoverflow.com/questions/22007595/borderpane-with-color-gradient

    Label appTitle = new Label("Room-Finder App!");
    appTitle.setFont(Font.font("Verdana", FontWeight.BOLD, Constants.APPTITLE_FONTSIZE));

    Button startButton = new Button("Find a Room");
    Image uOfCCoat = new Image("UofCcoat.png");
    ImageView uOfCImage = new ImageView(uOfCCoat);
    //https://docs.oracle.com/javafx/2/api/javafx/scene/image/ImageView.html

    VBox startVBox = new VBox(Constants.PREFERRED_HBOX_VBOX_SIZE);
    startVBox.getChildren().addAll(appTitle, startButton, uOfCImage);
    borderPanes1.setCenter(startVBox);
    startVBox.setAlignment(Pos.CENTER);

    Scene scene1 = new Scene(borderPanes1, Constants.SCENESIZE, Constants.SCENESIZE);


    //**
    // Main screen (second scene)
    //**

    /* Put gridpane in VBox with a title above it infoming the user of the building
    and floor number. Both are set to the center. */
    VBox gridPaneVBox = new VBox();
    gridPane.setAlignment(Pos.CENTER);
    buildingAndFloorLabel.setFont(Font.font("Verdana", Constants.BUILDING_AND_FLOOR_LABEL_FONTSIZE));
    gridPaneVBox.getChildren().addAll(buildingAndFloorLabel, gridPane);
    gridPaneVBox.setAlignment(Pos.CENTER);


    Label appName = new Label("Taylor Family Digital Library Pathfinder");
    appName.setFont(Font.font("Verdana", FontWeight.BOLD, Constants.APP_LABEL_FONTSIZE));

    buildingDropDown.getItems().addAll("Taylor Family Digital Library");

    /*
    * Elevator and stair button box
    */

    eleStairBox.setPadding(new Insets(10));

    Button elevatorB = new Button("Elevator");
    elevatorB.setOnAction(new HandleElevatorClick());

    Button stairB = new Button("Stairs");
    stairB.setOnAction(new HandleStairClick());

    Button nextFloorB = new Button("Next Floor.");
    nextFloorB.setOnAction(new HandleNextFloorClick());

    eleStairBox.getChildren().addAll(elevatorB, stairB, nextFloorB);


    // Set the radio buttons that control the size of the map into one group and in a VBox.
    smallButton.setToggleGroup(sizeGroup);
    smallButton.setUserData(Constants.SMALL_RECT);
    smallButton.setSelected(true); //http://www.learningaboutelectronics.com/Articles/How-to-select-an-item-by-default-in-JavaFX.php
    String defaultSelection = (String) sizeGroup.getSelectedToggle().getUserData().toString();
    rectLength = Integer.parseInt(defaultSelection);
    mediumButton.setToggleGroup(sizeGroup);
    mediumButton.setUserData(Constants.MEDIUM_RECT);
    largeButton.setToggleGroup(sizeGroup);
    largeButton.setUserData(Constants.LARGE_RECT);

    //Button to save the path to a file.
    Button savePath = new Button("Save Path");
    //TextField to enter the file name to save to.
    TextField fileTextField = new TextField("Save path as:");

    savedPathDropDown.setPromptText("Saved Paths");

    mapSize.setAlignment(Pos.TOP_LEFT);
    // group containing radio buttons to control mapsize, the button to save a path, and combobox with saved paths.
    mapSize.getChildren().addAll(smallButton, mediumButton, largeButton, fileTextField, savePath, savedPathDropDown);

    /*Sets the size of the map based on the users selection of the map size radiobutton group.
    * https://stackoverflow.com/questions/32424915/how-to-get-selected-radio-button-from-togglegroup */
    sizeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
        if (sizeGroup.getSelectedToggle() != null) {
          String userSelection = (String) sizeGroup.getSelectedToggle().getUserData().toString();
          rectLength = Integer.parseInt(userSelection);
          roomNumberFontSize = (int) sizeGroup.getSelectedToggle().getUserData() / 3;
          System.out.println(roomNumberFontSize);
        }
      }
    });

    /**
     * Handles the button click of the savePath button to save the path to a file.
     */
    savePath.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        fileName = (fileTextField.getText() + ".txt");
        writeToFile();
      }
    });

    /**
     * Handles user selecting a saved path from the combobox.
     */
    savedPathDropDown.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        fileName = savedPathDropDown.getValue();
        readFromFile();
      }
    });

    HBox topRow2 = new HBox();
    topRow2.setAlignment(Pos.CENTER);
    topRow2.getChildren().add(appName);


    HBox invalidHBox = new HBox(Constants.PREFERRED_HBOX_VBOX_SIZE);
    invalidHBox.getChildren().add(invalidEntry);
    invalidHBox.setAlignment(Pos.CENTER);

    // New button to submit textfield information.
    Button submitB = new Button("Submit");
    submitB.setOnAction(new HandleButtonClick());

    Label buildingDropDownLabel = new Label("Building:");
    VBox buildingDropDownVBox = new VBox();
    buildingDropDownVBox.getChildren().addAll(buildingDropDownLabel, buildingDropDown);

    Label enterStartRoomLabel = new Label("Start Room:");
    enterStartRoomVBox.getChildren().addAll(enterStartRoomLabel, enterStartRoom);

    Label enterDestRoomLabel = new Label("Destination Room:");
    enterDestRoomVBox.getChildren().addAll(enterDestRoomLabel, enterDestRoom);

    // Create FlowPane to hold items in the top row of the border pane.
    FlowPane topRow = new FlowPane();
    topRow.setAlignment(Pos.CENTER);
    topRow.getChildren().addAll(buildingDropDownVBox, enterStartRoomVBox,
      enterDestRoomVBox, submitB);

    VBox topVBox = new VBox(15);
    topVBox.getChildren().addAll(topRow2, topRow, invalidHBox);


    // Create an HBox to hold items in the  bottom row of the border pane.
    HBox bottomHBox = new HBox(Constants.PREFERRED_HBOX_VBOX_SIZE);


    // Add a new button to go back to scene 1.
    Button backButton = new Button("Back");
    bottomHBox.getChildren().addAll(backButton);

    /**
     * Handles a button click to change scene.
     */
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        primaryStage.setScene(scene1);
      }
    });

    // Add an About button to provide information about the app such as creators and how to use app.
    Button aboutButton = new Button("About this App");
    bottomHBox.getChildren().add(aboutButton);

    //Add a popup window when user clicks the About button.
    //Source: https://gist.github.com/jewelsea/1926196 jewelsea
    Popup aboutPopup = new Popup();
    Label aboutLabel = new Label("This app allows the user to select a building and \n" +
      "enter a starting room location and a desired destination location. The user also\n" +
      "has the option to click the labelled room numbers instead of entering a room number.\n" +
      "The app will then highlight the path between these two destinations.\n" +
      "To use this app: Select a building from the dropdown box, enter a starting room\n" +
      "number in the first textbox and a destination room in the second textbox. Then press\n" +
      "the submit button. Or, click a starting room label and a destination room label on the map.\n" +
      "Creators: Nicki, Dayan, and Riley. Created Winter 2018 for CPSC 219.");


    //Add a Hide button to hide the About app popup window.
    Button hideAboutButton = new Button("Hide \"About this App\"");
    bottomHBox.getChildren().add(hideAboutButton);

    /* Create VBox to hold the information and the button to hide the information
    when the About popup is clicked. */
    VBox aboutVBox = new VBox();
    aboutVBox.setAlignment(Pos.CENTER);
    aboutVBox.getChildren().addAll(aboutLabel, hideAboutButton);

    // Create stackpane for the About popup window with a light-blue background.
    Rectangle aboutBackground = new Rectangle();
    aboutBackground.setFill(Color.LIGHTBLUE);
    aboutBackground.setWidth(Constants.POPUP_WINDOW_HEIGHT);
    aboutBackground.setHeight(Constants.ABOUT_POPUP_WIDTH);
    StackPane aboutStackPane = new StackPane(aboutBackground);
    aboutStackPane.getChildren().add(aboutVBox);

    aboutPopup.getContent().add(aboutStackPane);

    /** Handles a click of the About button to open the About popup with
     * information on the app. */
    aboutButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        aboutPopup.show(primaryStage);
      }
    });


    /** Handles a click of the Hide About this App button. */
    hideAboutButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        aboutPopup.hide();
      }
    });

    // Add a Help button to provide guidance on using the app.
    Button helpButton = new Button("Help?");
    bottomHBox.getChildren().add(helpButton);

    //Add a popup window when user clicks the Help button.
    //Source: https://gist.github.com/jewelsea/1926196 jewelsea
    Popup helpPopup = new Popup();
    Label helpLabel = new Label(
      "To use this app: Select a building from the dropdown box, enter a starting room\n" +
        "number in the first textbox and a destination room in the second textbox. Then press\n" +
        "the submit button. If you enter an invalid room, example room numbers will be provided\n" +
        "in a message. Or, click a starting room label and a destination room label on the map and \n" +
        "follow the dialogue box instructions.");


    //Add a Hide button to hide the Help popup window.
    Button hideHelpButton = new Button("Hide \"Help?\"");
    bottomHBox.getChildren().add(hideHelpButton);

    /* Create VBox to hold the information and the button to hide the information
    when the Help popup is clicked. */
    VBox helpVBox = new VBox();
    helpVBox.setAlignment(Pos.CENTER);
    helpVBox.getChildren().addAll(helpLabel, hideHelpButton);

    // Create stackpane for the Help popup window with a light-green background.
    Rectangle helpBackground = new Rectangle();
    helpBackground.setFill(Color.LIGHTGREEN);
    helpBackground.setWidth(Constants.POPUP_WINDOW_HEIGHT);
    helpBackground.setHeight(Constants.HELP_POPUP_WIDTH);
    StackPane helpStackPane = new StackPane(helpBackground);
    helpStackPane.getChildren().add(helpVBox);

    helpPopup.getContent().add(helpStackPane);

    /** Handles a click of the Help button to open the Help popup.*/
    helpButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        helpPopup.show(primaryStage);
      }
    });


    /** Handles a click of the Hide Help button. */
    hideHelpButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        helpPopup.hide();
      }
    });


    BorderPane borderPanes2 = new BorderPane();
    borderPanes2.setCenter(gridPaneVBox);
    borderPanes2.setTop(topVBox);
    borderPanes2.setBottom(bottomHBox);
    borderPanes2.setRight(mapSize);

    /* Scrollable display. Only shows scroll bars when needed.
    * https://docs.oracle.com/javafx/2/ui_controls/scrollpane.htm
    * https://stackoverflow.com/questions/17568688/how-to-resize-javafx-scrollpane-content-to-fit-current-size*/
    ScrollPane scrollPane = new ScrollPane(borderPanes2);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);
    scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);


    Scene scene2 = new Scene(scrollPane, Constants.SCENESIZE, Constants.SCENESIZE);

    /**
     * Handles a button click to change the scene to scene 2.
     */
    startButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        primaryStage.setScene(scene2);
      }
    });

    primaryStage.setTitle("Room Finder App");
    primaryStage.setScene(scene1);
    primaryStage.show();
  }
}
