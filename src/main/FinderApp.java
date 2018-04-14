import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import resources.Constants;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;



public class FinderApp extends Application {

  /*
  * Class runs the GUI application, including scene creation, button handling
  * and the production of a visual grid.
  *
  */
    //Variables to do with constructing the gridd and placing of labels and buttons on the GUI
    private Map map1 = new Map();
    private GridPane gridPane = new GridPane();
    private int roomNumbers = 0;
    private Label invalidEntry = new Label (""); //Label used for messaging to the user through the GUI app
    private Label buildingAndFloorLabel = new Label(""); //Label for above the map.
    private int rectLength; //grid square size on the map.
    private int roomNumberFontSize = 10;
    private int startNumberInput=0;
    private int destNumberInput =0;
    private String buildingInput; //Building name from the building combobox.
    private int clickedRoom;
    private VBox enterStartRoomVBox = new VBox();
    private VBox enterDestRoomVBox = new VBox();
    private Rectangle[][] rectangleGrid;
    private Button startRoomButton = new Button("Store in start");
    private Button destRoomButton = new Button("Store in destination");
    private boolean stairs = false;
    private FloorPlans currentFloorPlan;
    private VBox eleStairBox = new VBox(Constants.PREFERRED_HBOX_VBOX_SIZE); //VBox that holds the buttons involved with changing floors.
    private VBox mapSize = new VBox();
    private File curDir = new File((System.getProperty("user.dir"))); //The current directory. https://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
    private String operatingSystem = System.getProperty("os.name"); //The opearting system of the computer running the program. https://stackoverflow.com/questions/14288185/detecting-windows-or-linux
    private Screen screen = Screen.getPrimary(); //https://stackoverflow.com/questions/6864540/how-to-set-a-javafx-stage-frame-to-maximized
    private Rectangle2D bounds = screen.getVisualBounds(); //The screens dimensions.
    private boolean differentFloor = false;
    private FloorPlans updatedPlan;

    // File variables
    private File savedPathDir; //saved paths' directory
    private FloorPlans planToSave = new FloorPlans();
    private int startToSave;
    private int destToSave;
    private boolean gridVisible = false;
    private FloorPlans planRead;
    private int startRead;
    private int destRead;

    //Variables that are not part of the map that can be moved through, plus the room numbers for elevators and stairs.
    private int[] notMap = {Constants.WALL,Constants.HALL,Constants.ROOM,
      Constants.REST,1270,1170,1171,1172,1225,1125,Constants.COFF};

    private int whichFloor = 0;
    private FloorPlans nextFloor = new FloorPlans();

    //Variables for the top row to do with inputting values to generate the path.
    private TextField enterStartRoom = new TextField("Enter the start room");
    private TextField enterDestRoom= new TextField("Enter destination room");
    private ComboBox<String> buildingDropDown = new ComboBox<String>();
    private ComboBox<String> savedPathDropDown = new ComboBox<String>();
    //https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm

    //Variables to do with the map sizing.
    private ToggleGroup sizeGroup = new ToggleGroup();
    private RadioButton smallButton = new RadioButton("Small Map");
    private RadioButton mediumButton = new RadioButton("Medium Map");
    private RadioButton largeButton = new RadioButton("Large Map");
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/RadioButton.html

    //Variables to do with generating the footprint images on the path.
    private URL resource = FinderApp.class.getResource("/resources/");
    private Image imgRestroom = new Image(resource+ "RestroomImage.png",rectLength,rectLength, true, false);
    //Image source: http://maxpixel.freegreatpicture.com/Rest-Room-Restroom-Ladies-Restroom-Public-Restroom-99226
    private Image imgCoffee = new Image(resource + "Coffee.png",rectLength,rectLength,true,false);
    //Image source: https://www.freepik.com/free-icon/hot-coffee-rounded-cup-on-a-plate-from-side-view_732944.htm
    private Image imgStairs = new Image(resource + "Stairs.png", rectLength, rectLength, true, false);
    //Image source:https://pixabay.com/en/stairs-climb-levels-descend-44071/
    private Image imgElevator = new Image(resource + "Elevator.png", rectLength, rectLength, true, false);
    //Image source: https://pixabay.com/en/elevator-people-silhouette-down-44013//
    private Image imgFootPrintsN = new Image(resource + "FootprintsN.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png
    private Image imgFootPrintsNE = new Image(resource + "FootprintsNE.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png
    private Image imgFootPrintsNW = new Image(resource + "FootprintsNW.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png
    private Image imgFootPrintsS = new Image(resource + "FootprintsS.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png
    private Image imgFootPrintsSE = new Image(resource + "FootprintsSE.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png
    private Image imgFootPrintsSW = new Image(resource + "FootprintsSW.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png
    private Image imgFootPrintsE = new Image(resource + "FootprintsE.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png
    private Image imgFootPrintsW = new Image(resource + "FootprintsW.png", rectLength, rectLength, true, false);
    //Image source: https://commons.wikimedia.org/wiki/File%3AFootprints.png



    /**
    * Handle class that deals with the clicking of the elevator button.
    * Creates the first part of the temporary path with regards to the elevator.
    */
    public class HandleElevatorClick implements EventHandler<ActionEvent>{
      public HandleElevatorClick(){}
      public void handle(ActionEvent even){
        /*Since we are using the elevator, we want to appear on the next floor
        at the elevator tile not at the stairs.*/
        stairs = false;

        // Create a new floorplan for this temporary path and set it as the current for our map.
        FloorPlans tempFloorPlan = map1.getCurrentBuilding().getFloorPlan(startNumberInput);
        map1.setCurrentFloorPlan(tempFloorPlan);

        // Set the temporary destination to the elevator.
        int tempDest = tempFloorPlan.getElevatorNum();

        // Reset the invalid entry message.
        invalidEntry.setText("");

        /* Set the values for the map for a path from the start location to
        the elevator on the same floor and generate the grid based on these values*/
        setMap(tempFloorPlan,startNumberInput,tempDest);

      }
    }
    /**
    * Method that handles the dropdown path item clicks by loading
    * the file clicked and setting the map values. The textfields and the
    * dropdown menu for building name are updated based on this information
    * and the user is notified of a path being successfully loaded.
    */
    public class HandleLoadPathClick implements EventHandler<ActionEvent>{
      public HandleLoadPathClick(){}
      public void handle(ActionEvent event){
        //Completing the abridged file name to the full file name
        readFromFile(System.getProperty("user.dir")+"/SavedPaths/" + savedPathDropDown.getValue());
        String buildingNameRead = "";
        //If building is successfully read from the file.
        if(planRead!=null && planRead.getBuildingName()!=null && planRead.getGrid()!=null){
          buildingNameRead = planRead.getBuildingName();
          System.out.println(buildingNameRead);
          /* Put the correct building and floor name above the map, inform the user the file has been loaded,
          and autocomplete the building combobox and starting and destination room textfields.*/
          buildingAndFloorLabel.setText(setBuildingAndFloorLabel(planRead.getFlNum(), buildingNameRead));
          invalidEntry.setText("You have loaded a path from "+ startRead+ " to " + destRead+".");
          enterStartRoom.setText(startRead+"");
          startNumberInput = startRead;
          enterDestRoom.setText(destRead+"");
          destNumberInput = destRead;
          buildingDropDown.setValue(buildingNameRead);
          /* Set the floorplan from the file, create the path, place the starting and end values on the map,
          and set the grid*/
          map1.setCurrentFloorPlan(planRead);
          Path readPath = new Path(planRead.getGrid(),startRead,destRead);
          map1.setStartValues(startRead);
          map1.setEndValues(destRead);
          int[][] readGrid = readPath.createPath();
          planRead.setGrid(readGrid);
          // Make the grid on the screen and highlight the destination room.
          makeGrid(readGrid,gridPane,rectLength);
          highlight(planRead, destRead);
          gridVisible = true;
          differentFloor = false;
          //Adjust the size of building and floor number label above the map based on the mapsize.
          buildingAndFloorLabel.setFont(Font.font("Verdana", (int)sizeGroup.getSelectedToggle().getUserData()/1.5));
          //Remove the buttons below the start room textfield if it is there.
          if(enterStartRoomVBox.getChildren().contains(startRoomButton)){
            enterStartRoomVBox.getChildren().remove(startRoomButton);
          }
          //Remove the buttons below the destination room textfield if it is there.
          if(enterDestRoomVBox.getChildren().contains(destRoomButton)){
            enterDestRoomVBox.getChildren().remove(destRoomButton);
          }
        }else{
          System.out.println("No file to load.");
        }
      }
    }
    /**
    * Handle class that deals with the clicking of the stair button.
    * Creates the first part of the temporary path with regards to the stairs.
    */
    public class HandleStairClick implements EventHandler<ActionEvent>{
      public HandleStairClick(){}
      public void handle(ActionEvent event){
        /* Since we are using the stairs, we want to appear on the next floor
        at the stairs tile.*/
        stairs = true;

        // Create a new floorplan for this temporary path and set it as the current for our map.
        FloorPlans tempFloorPlan1 = map1.getCurrentBuilding().getFloorPlan(startNumberInput);
        map1.setCurrentFloorPlan(tempFloorPlan1);

        // Get the stairs number from the given floor plan and set it as the temporary destination.
        int tempDest = tempFloorPlan1.getStairsNum();

        // Reset the invalid entry message.
        invalidEntry.setText("");

        /* Set the values for the map for a path from the start location to
        the stairs on the same floor and generate the grid based on these values*/
        setMap(tempFloorPlan1,startNumberInput,tempDest);

      }
    }

    /**
    * Handle class that deals with the clicking of the next floor click.
    * Creates the second part of the temporary path.
    */
    public class HandleNextFloorClick implements EventHandler<ActionEvent>{
      public HandleNextFloorClick(){}
      public void handle(ActionEvent event){
        int tempStart; // temporary starting room number.

        // Remove the button box because we no longer need to change floors.
        mapSize.getChildren().remove(eleStairBox);

        /* Determine the start location on the new floor based on which
        route was taken (elevator/stairs) and which floor the user's destination is on.*/
        if(stairs == true){
          tempStart = nextFloor.getStairsNum();
        }else{
          tempStart = nextFloor.getElevatorNum();
        }

        // Set the current floor plan to the destination floor.
        map1.setCurrentFloorPlan(nextFloor);

        //Reset the error messaging box
        invalidEntry.setText("");

        /* Set the next floor map with the values inputted by the user, using a temporary start
        based on whether the user has chosen to use the elevator or the stairs,
        and generate the grid*/
        setMap(nextFloor,tempStart,destNumberInput);

        // Reset the stairs boolean variable.
        stairs=false;
      }
    }

    /**
    * Method that allows the user to add a value from the grid to the
    * input text fields.
    * Removes the start and dest room buttons on click.
    */
    public class HandleAddClick implements EventHandler<ActionEvent>{
      int row1;
      int col1;
      int rNum;
      String buttonType;
      /*parameters are a row number (aRow) as an int, a column number (aCol) as an int,
      a room number (roomN) as an int, and a button type (type) as a String*/
      public HandleAddClick(int aRow, int aCol, int roomN, String type){
        rNum = roomN;
        row1 = aRow;
        col1 = aCol;
        buttonType = type;
      }
      public void handle(ActionEvent event){
      /* If the button clicked is for the start number, change the start room text.
      If the button clicked is for the destination number, change the destination text. */
          if(buttonType.equals("start")){
          enterStartRoom.setText(""+rNum);
        }else if(buttonType.equals("dest")){
          enterDestRoom.setText(""+rNum);
        }
        // Change the colour of the selected (red) rectangle to light blue.
        rectangleGrid[row1][col1].setFill(Color.LIGHTBLUE);
        enterStartRoomVBox.getChildren().remove(startRoomButton);
        enterDestRoomVBox.getChildren().remove(destRoomButton);
      }
    }

    /**
    * Method to remove a red square, if present on the grid.
    * @param aRow integer for the row number.
    * @param aCol integer for the column number.
    */
    public void removeRedSquare(int aRow, int aCol){
      /* Check all squares on the current floor plan grid to see if there is a red
      square, if so change it from red to the specified colour.*/
      for(int row=0;row<map1.getCurrentFloorPlan().getGrid().length;row++){
        for(int col=0;col<map1.getCurrentFloorPlan().getGrid()[0].length;col++){
          if(rectangleGrid[row][col].getFill().equals(Color.RED)
          && rectangleGrid[row][col]!=rectangleGrid[aRow][aCol]){
            //Starting door change to orange.
            if(map1.getCurrentFloorPlan().getGrid()[row][col] == map1.getStart()){
              rectangleGrid[row][col].setFill(Color.ORANGE);
              //Destination door change to yellow.
            }else if(map1.getCurrentFloorPlan().getGrid()[row][col] == map1.getDest()){
              rectangleGrid[row][col].setFill(Color.YELLOW);
              //All other doors change to light blue.
            }else{
              rectangleGrid[row][col].setFill(Color.LIGHTBLUE);
            }
          }
        }
      }
    }

    /**
    * Method that handles the clicking of a room button/room number on the map.
    * Allows user to select to store the room value in the start room or destination
    * room. The user can also unselect the room by clicking the temporary button generated
    * at it.
    http://code.makery.ch/blog/javafx-8-event-handling-examples/
    */
    public class HandleRoomClick implements EventHandler<ActionEvent>{
      int roomNum;
      StackPane aSP;
      int rowLength;
      int colLength;
      /* Parameters are a row length (aRowLength) as an int, a column length (aColLength) as an int,
      a room number (roomNumber) as an int, and a StackPane (aStackPane). */
      public HandleRoomClick(int aRowLength, int aColLength, int roomNumber, StackPane aStackPane){
        roomNum = roomNumber;
        aSP = aStackPane;
        rowLength = aRowLength;
        colLength = aColLength;
      }
      public void handle(ActionEvent event){
        int row;
        int col;

        //Remove any previously present buttons for entering selected rooms as start or destination.
        enterStartRoomVBox.getChildren().remove(startRoomButton);
        enterDestRoomVBox.getChildren().remove(destRoomButton);

        /* If the tile clicked is a room number and not red, make it red
        and activate the add buttons with the value of the tile clicked. If
        the tile is a room number and is red, change it to the specified colour.
        Loop through all coordinates.*/
        for(row=0;row<rowLength;row++){
          for(col=0;col<colLength;col++){
            //if current coordinate is a door number.
            if(map1.getCurrentFloorPlan().getGrid()[row][col] == roomNum){
              //If not already red, colour the door red.
              if(rectangleGrid[row][col].getFill().equals(Color.RED)==false){

                rectangleGrid[row][col].setFill(Color.RED);

                //If no start room button present, add it below the corresponding textfield.
                if(enterStartRoomVBox.getChildren().contains(startRoomButton)==false){
                  enterStartRoomVBox.getChildren().add(startRoomButton);
                } //If no destination room button present, add it below the corresponding textfield.
                if(enterDestRoomVBox.getChildren().contains(destRoomButton) ==false){
                  enterDestRoomVBox.getChildren().add(destRoomButton);
                }

                /*
                * Clicking the button below the Start room textfield stores the selected room
                * number in the textfield.
                */
                startRoomButton.setOnAction(new HandleAddClick(row,col,roomNum,"start"));

                /*
                * Clicking the button below the Destination room textfield stores the selected room
                * number in the textfield.
                */
                destRoomButton.setOnAction(new HandleAddClick(row,col,roomNum,"dest"));

                //Else if the door is already coloured red.
              }else if(rectangleGrid[row][col].getFill().equals(Color.RED)){
                //if door is Starting door, change to orange.
                  if(map1.getCurrentFloorPlan().getGrid()[row][col] == map1.getStart()){
                    rectangleGrid[row][col].setFill(Color.ORANGE);
                    //Else if destination door, change to yellow.
                  }else if(map1.getCurrentFloorPlan().getGrid()[row][col] == map1.getDest()){
                    rectangleGrid[row][col].setFill(Color.YELLOW);
                    //All other doors change to light blue.
                  }else{
                    rectangleGrid[row][col].setFill(Color.LIGHTBLUE);
                  }
              }
              // Check for any additional red squares and remove them.
              removeRedSquare(row,col);
            }

          }
        }
      }
    }

    /**
    * Recursive method that takes a number and finds the ordinal ending for it.
    * @param num integer to get the ordinal number for.
    * @return String with the ordinal number.
    */
    public static String getOrdinalNumber(int num){
     int n=0;
     /* If the number is less than 20, return its value
     based on which condition it meets. If it is greater than 20,
     get the rightmost digit and call the method again on it.*/
     if(num<=20){
       if(num == 1) return num+"st";
       if(num == 2) return num+"nd";
       if(num == 3) return num+"rd";
       if(num >=4 || num ==0) return num+"th";
     }
     if(num>20){
       n = num%10;
     }
     return (num/10)+getOrdinalNumber(n);

   }

   /**
   * Method that gets user information and generates a grid
   * with start and destination rooms on the same floor.
   *
   */
   public void submitSameFloor(){
     FloorPlans floorPlan = map1.getCurrentFloorPlan(); //Get the current floorplan from the map.

     setMap(floorPlan,startNumberInput,destNumberInput); //Set the map's floorplan with starting and destination room numbers

     /* Set the instance varibles for file saving: the FloorPlans
     being used, the start number, and the desination.*/
     planToSave = new FloorPlans(floorPlan);
     startToSave = startNumberInput;
     destToSave = destNumberInput;

     // Indicate the grid has been created.
     gridVisible = true;
   }

   /*
   * Method that gets user information and generates a grid
   * with start and destination rooms on different floors.
   */
   public void submitDiffFloor(){
     // Get the current floor plan.
     FloorPlans floorPlan = map1.getCurrentFloorPlan();

     // Set the temporary destination default as the elevator.
     int tempDest = floorPlan.getElevatorNum();

     // Add buttons for the next floor.
     if(mapSize.getChildren().contains(eleStairBox)!=true){
       mapSize.getChildren().addAll(eleStairBox);
     }

     // Indicate a grid has been created.
     gridVisible = true;

     /* Set the map based on the user input for the first half of
     the two-part path */
     setMap(floorPlan, startNumberInput,tempDest);

   }

   /**
   * Method that sets the map's current state. This includes setting the map
   * start and end values based on the given floorplan and start and end numbers
   * and then creating a grid based on those values and displaying it.
   * @param: floorPlan a floor plan of type FloorPlans
   * @param: start starting room of type int
   * @param: end ending room of type int
   */
   public void setMap(FloorPlans floorPlan, int start, int end){
     /* Create a new path along the given floor plan from the start room
     to the destination room*/
     Path thePath = new Path(floorPlan.getGrid(),start,end);

     /* Set the start and end values for the map based on the user inputted
     or temporary destination and the inputted or temporary starts*/
     map1.setStartValues(start);
     map1.setEndValues(end);

     // Create a grid with the path from start to final destination.
     int[][] theGrid = thePath.createPath();

     /*Update the grid with the temporary path from the starting point
     to the user's destination and highlight the destination.*/
     makeGrid(theGrid,gridPane,rectLength);
     highlight(floorPlan,end);

     // Update the grid label with the floor number and building name.
     buildingAndFloorLabel.setText(setBuildingAndFloorLabel(floorPlan.getFlNum(),buildingInput));
   }

   /*
   * Handle class that responds to the Submit button click by reading
   * the start and destination text fields and the dropdown value for
   * the building name.
   */
    public class HandleSubmitClick implements EventHandler<ActionEvent>{
      public HandleSubmitClick (){}
      public void handle(ActionEvent event){

        // Remove box if user doesn't follow through with next level button.
        if(mapSize.getChildren().contains(eleStairBox)){
          mapSize.getChildren().remove(eleStairBox);
        }

        // Reset the error label.
        invalidEntry.setText("");

        //Set the size of the label above the map according to map size.
        buildingAndFloorLabel.setFont(Font.font("Verdana", (int)sizeGroup.getSelectedToggle().getUserData()/1.5));

        // Get input from the textfields, making sure an integer is entered.
        try{
          startNumberInput = Integer.parseInt(enterStartRoom.getText());
          destNumberInput  = Integer.parseInt(enterDestRoom.getText());
          //If not an integer, message the user.
        } catch(NumberFormatException e){
          invalidEntry.setText ("Please enter the room number as an integer.");
        }

        /*If the building name is selected, create a new building with that
        name and set it as the current building. If the start number and
        destination number are valid for the building, set the current floor plan
        as the floor plan that contains the start input.*/
        if(buildingDropDown.getValue()!=null){
          buildingInput = buildingDropDown.getValue();

          Building building1 = new Building(buildingInput);
          map1.setCurrentBuilding(building1);

          if(building1.onAFloor(startNumberInput)!=null && building1.onAFloor(destNumberInput)!=null){
            map1.setCurrentFloorPlan(building1.getFloorPlan(startNumberInput));



          /* If the floors for the start and destination numbers are the same
          and the start and destination numbers are valid, generate a grid
          on the same floor */
          if(building1.getFloorPlan(startNumberInput).getFlNum()==
          building1.getFloorPlan(destNumberInput).getFlNum() &&
          building1.onAFloor(startNumberInput)!=null &&
          building1.onAFloor(destNumberInput)!=null){
            invalidEntry.setText("");
            submitSameFloor();
            differentFloor = false;



          /* If the floors for the start and destination numbers are different
          and the start and destination numbers are valid, create a temporary destination
          and generate that as a grid.*/
          }else if(building1.getFloorPlan(startNumberInput).getFlNum()!=
          building1.getFloorPlan(destNumberInput).getFlNum()
          && building1.onAFloor(startNumberInput)!=null &&
          building1.onAFloor(destNumberInput)!=null){

            // Determine which floor is the next floor
            nextFloor = building1.onAFloor(destNumberInput);
            whichFloor = nextFloor.getFlNum();

            // Communicate with the user that their destination is on a separate floor.
            invalidEntry.setText("Your destination is on the " + getOrdinalNumber(whichFloor)+" floor. Use the elevator and stair buttons"+
            "\non the right to choose which path you want to take to the next floor.");

            submitDiffFloor();

            // Specify that the start and destination are on different floors.
            differentFloor = true;
      }
      /* If the start and destination inputs are null, give the user
      an appropriate error message*/
      }else if(building1.onAFloor(startNumberInput)==null ||
      building1.onAFloor(destNumberInput)==null){
          if(building1.onAFloor(startNumberInput)==null) invalidEntry.setText("Please enter a valid start number.");
          else if(building1.onAFloor(destNumberInput)==null)invalidEntry.setText("Please enter a valid destination number.");
          System.out.println("No valid start/dest info. added");
      }

        /* If the value for the building name is null, give the user an
        appropriate error message.*/
        }else if(buildingDropDown.getValue()==null){
            invalidEntry.setText("Please select a building.");
        }
      }
    }

    /**
    * Method to set the building and floor label above the map. Returns a string for the label
    * and requires a floor number and building name.
    *@param floorNumber floor number to use on the floor label as an int.
    *@param buildingName building name to use on the floor label as a String.
    */
    public String setBuildingAndFloorLabel(int floorNumber, String buildingName){
      String currentBuildingAndFloor = "";
      //If floor number is not 0.
      if (floorNumber > 0){
        //If first floor.
      if (floorNumber%10 == 1){
        currentBuildingAndFloor = buildingName + " " + floorNumber + "st Floor";
        //Else if second floor
      } else if (floorNumber%10 == 2){
        currentBuildingAndFloor = buildingName + " " + floorNumber +  "nd Floor";
        //Else if third floor.
      } else if (floorNumber%10 == 3){
        currentBuildingAndFloor = buildingName + " " + floorNumber +  "rd Floor";
        //All other floors.
      } else{
        currentBuildingAndFloor = buildingName + " " + floorNumber +  "th Floor";
      }}
      //Else ground floor
      else{
        currentBuildingAndFloor = buildingName + " Ground Floor";
      }
      return currentBuildingAndFloor;
    }


    /**
    * Method to confirm whether an array contains a value.
    *@param anArray an array to search as an int[]
    *@param aValue the value to find in the array as an int..
    *@return result boolean value for if value is in the array.
    */
    public boolean contains(int[] anArray, int aValue){
      boolean result = false;
      //Iterate through the array parameter.
      for(int i : anArray){
        //If current iteration contains the value of interest, return true.
        if(i == aValue){
          result = true;
        }
      }
      return result;
    }

    /**
    * Method to highlight a room in the Grid (change colours of member tiles).
    *@param aFloorPlan floorplan as type FlooPlans to add highlights to.
    *@param roomNumber number of room as an integer to highlight.
    */
    public void highlight(FloorPlans aFloorPlan, int roomNumber){
      //Loop through each coordinate in the floorplan parameter
      for(int row=0;row<aFloorPlan.getRowLength();row++){
        for(int col=0;col<aFloorPlan.getColLength(row);col++){
          /* Add 1000 to the roomNumber to see if the room exists on
          the specified floor plan. If the number is on the grid
          and not a stair or elevator number, make the corresponding
          rectangle blue*/
          if(aFloorPlan.getRoom(roomNumber+1000) != null ){
            if(aFloorPlan.getGrid()[row][col] == aFloorPlan.getRoom(roomNumber+1000).getRoomsNumber()){
              if(aFloorPlan.getGrid()[row][col]!= aFloorPlan.getStairsNum()
              && aFloorPlan.getGrid()[row][col]!= aFloorPlan.getElevatorNum()){
                rectangleGrid[row][col].setFill(Color.BLUE);
              }
            }
          }
        }
      }
    }

    /**
    * Method that generates a visual of the current grid.
    *@param aGrid an integer grid (int[][]) to take data from.
    *@param aGridPane a GridPane to add the grid's data to.
    *@param rectLength the length as an int of the rectangles to be created.
    */
    public void makeGrid(int[][] aGrid, GridPane aGridPane, int rectLength){
      int rowLength = aGrid.length;
      int colLength = aGrid[0].length;
      // Create a new grid of rectangles based on the size of the grid.
      rectangleGrid = new Rectangle[aGrid.length][aGrid[0].length];

      // Clear the gridPane.
      aGridPane.getChildren().clear();

      //Loop through each coordinate of the 2D grid.
      for (int row = 0; row < aGrid.length; row++){
        for(int col = 0; col < aGrid[row].length; col++){

          // Add rectangles to the grid and save them in a rectangle grid.
          Rectangle rect = setRectangles(row, col, aGrid, rectLength);
          rectangleGrid[row][col] = rect;


          // An overlaying stackpane that is mouse transparent containing the rect and room labels.
          StackPane overlayStack = new StackPane();

          //Add room numbers to the grid map.
          int roomNumbers = 0;
          roomNumbers = aGrid[row][col];
          Label rooms = setNumbers(roomNumbers);

          // Add the rectangles and room labels to the stack pane.
          overlayStack.getChildren().addAll(rect,rooms);
          overlayStack.setMouseTransparent(true);
          //https://stackoverflow.com/questions/9899347/when-adding-a-second-item-to-my-stackpane-the-first-item-loses-its-event-mouseo

          // Complete stackpane containing the overlay stackpane and the clickable buttons hidden underneath.
          StackPane stack = new StackPane();

          //Add buttons at the room numbers on the map, if there is a room number there.
          if (rooms.getText().isEmpty()){ //https://stackoverflow.com/questions/25189027/how-to-check-if-lable-is-not-empty
            stack.getChildren().add(overlayStack);
          } else{
            Button roomButton = createRoomButtons(roomNumbers, startNumberInput, destNumberInput, stack, rowLength, colLength);
            roomButton.setMaxSize(rectLength, rectLength); //https://stackoverflow.com/questions/35344702/how-do-i-get-buttons-to-fill-a-javafx-gridpane
            stack.getChildren().addAll(roomButton, overlayStack);
          }

          // Specify the indices for the GridPane.
          GridPane.setRowIndex(stack, row);
          GridPane.setColumnIndex(stack, col);
          aGridPane.getChildren().add(stack);
          //https://stackoverflow.com/questions/35367060/gridpane-of-squares-in-javafx
          }
        }
      }

    /**
    * Method that sets the rectangles and their colours for the grid.
    *@param row the row as an int to place the rectangle
    *@param col the column as an int to place the rectangle
    *@param aGrid an integer grid (int[][]) to take data from.
    *@param rectLength the length as an int of the rectangles to be created.
    *@return rect a rectangle as type Rectangle.
    */
    public Rectangle setRectangles(int row, int col, int[][] aGrid, int rectLength){

      Rectangle rect = new Rectangle();
      rect.setStroke(Color.BLACK);

      //Color walls black
      if (aGrid[row][col] == Constants.WALL){
        rect.setFill(Color.BLACK);
        //Halls are clear
      } else if (aGrid[row][col] == Constants.HALL){
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.TRANSPARENT);
        //Rooms are coloured grey.
      } else if (aGrid[row][col] == Constants.ROOM){
        rect.setFill(Color.GREY);
        //Destination room is coloured yellow.
      } else if (aGrid[row][col] == map1.getDest()){
        rect.setFill(Color.YELLOW);
        //Starting room is coloured orange.
      } else if (aGrid[row][col] == map1.getStart()){
        rect.setFill(Color.ORANGE);
        //Path direction values determine which direction the feet images will point.
      } else if (aGrid[row][col] == Constants.NPATH){
        rect.setFill(new ImagePattern(imgFootPrintsN));
        rect.setStroke(Color.TRANSPARENT);
      } else if (aGrid[row][col] == Constants.NEPATH){
        rect.setFill(new ImagePattern(imgFootPrintsNE));
        rect.setStroke(Color.TRANSPARENT);
      } else if (aGrid[row][col] == Constants.NWPATH){
        rect.setFill(new ImagePattern(imgFootPrintsNW));
        rect.setStroke(Color.TRANSPARENT);
      } else if (aGrid[row][col] == Constants.SPATH){
        rect.setFill(new ImagePattern(imgFootPrintsS));
        rect.setStroke(Color.TRANSPARENT);
      } else if (aGrid[row][col] == Constants.SWPATH){
        rect.setFill(new ImagePattern(imgFootPrintsSW));
        rect.setStroke(Color.TRANSPARENT);
      } else if (aGrid[row][col] == Constants.SEPATH){
        rect.setFill(new ImagePattern(imgFootPrintsSE));
        rect.setStroke(Color.TRANSPARENT);
      } else if (aGrid[row][col] == Constants.EPATH){
        rect.setFill(new ImagePattern(imgFootPrintsE));
        rect.setStroke(Color.TRANSPARENT);
      } else if (aGrid[row][col] == Constants.WPATH){
        rect.setFill(new ImagePattern(imgFootPrintsW));
        rect.setStroke(Color.TRANSPARENT);
        //Restrooms will have appropriate image.
      } else if(aGrid[row][col] == Constants.REST){
        rect.setFill(new ImagePattern(imgRestroom));
        //https://stackoverflow.com/questions/22848829/how-do-i-add-an-image-inside-a-rectangle-or-a-circle-in-javafx
        //Stairs will have stair image.
      } else if(aGrid[row][col] == (map1.getCurrentFloorPlan().getStairsNum()+1000)){
        rect.setFill(new ImagePattern(imgStairs));
        //Elevators will have elevator image.
      } else if(aGrid[row][col] == (map1.getCurrentFloorPlan().getElevatorNum()+1000)){
        rect.setFill(new ImagePattern(imgElevator));
        //Coffee shop will have an image of a coffee cup.
      } else if(aGrid[row][col] == Constants.COFF){
        rect.setFill(new ImagePattern(imgCoffee));
        //All other rectangles will be light blue.
      }else{
        rect.setFill(Color.LIGHTBLUE);
      }

      //set the rectangle dimensions based on the size of the map selected.
      rect.setWidth(rectLength);
      rect.setHeight(rectLength);

      return rect;
    }

    /**
    * Method that sets number labels for tiles in the grid.
    *@param roomNumbers the integer room number to set.
    *@return rooms a Label with the room number.
    */
    public Label setNumbers(int roomNumbers){
      Label rooms = new Label("");
      /* If the room number is less than 1000 (not a room but a door), give the grid
      tile a label of either that number, or an "S" or "D" for start and destination
      room numbers */
      if(roomNumbers<1000){
        if (contains(notMap,roomNumbers) == false && roomNumbers != map1.getStart() &&
        roomNumbers !=map1.getDest()){
          rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
          rooms.setText("" + roomNumbers);
        } else if (roomNumbers == map1.getStart()){
          rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
          rooms.setText("S");
        } else if (roomNumbers == map1.getDest()){
          rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
          rooms.setText("D");
        }
      }
      return rooms;
    }

    /**
    * Method that creates buttons for the room numbers on the map.
    *@param roomNumbers a room number as an int on the grid.
    *@param startNumberInput an integer representing the start room number.
    *@param destNumberInput an integer representing the destination room number.
    *@param stack a stackPane to add the buttons to.
    *@param rowLength the length of the row as an int
    *@param colLength the length of the column as an int
    *@return aRoomButton a button for the room as a Button
    */
    public Button  createRoomButtons(int roomNumbers, int startNumberInput, int destNumberInput, StackPane stack, int rowLength, int colLength){
        Button aRoomButton = new Button();
        /* If the tile is either a room number or more specifically the start
        or end room, create a button on top of it with an appropriate String on it.*/
        if (contains(notMap,roomNumbers) == false && roomNumbers != map1.getStart() &&
        roomNumbers !=map1.getDest()){
          aRoomButton = new Button (""+roomNumbers);

        } else if(roomNumbers == map1.getStart()){
          aRoomButton = new Button (""+startNumberInput);

        } else if(roomNumbers == map1.getDest()){
          aRoomButton = new Button (""+destNumberInput);

        }
        Integer roomNumberValue = Integer.parseInt(aRoomButton.getText()); //the room number value from the button String.

        /*
        * Clicking a room button is handled by HandleRoomClick.
        */
        aRoomButton.setOnAction(new HandleRoomClick(rowLength, colLength, roomNumberValue, stack));
        return aRoomButton;
      }


    /**
    * Method to write the current floor plan, start and destination numbers
    * to a file of a name the user chooses.
    * The written files will be added to the SavedPaths folder.
    * Notification is provided to the user as a message when the file is
    * successfully saved. This is also reported in the terminal.
    * @param fileName the file name for the file to write to as a String.
    */

    public void writeToFile(String fileName){
      ObjectOutputStream outputStream = null;
      /* Try to open an output Stream. If successful, write the floor plans as
      as an object to the file, followed by the start number and destination number
      as integers. Successfully written files are added to the savedPathDropDown.*/
      try{
        outputStream =
        new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir")+"/SavedPaths/"+fileName));
      }catch(IOException e){
        System.out.println("Problem with output to file" + fileName);
      }
      try{
          outputStream.writeObject(planToSave);
          outputStream.writeInt(startToSave);
          outputStream.writeInt(destToSave);
          System.out.println("plan & start/dest Saved");
          outputStream.close();

      }catch(FileNotFoundException e){
        System.out.println("Problem opening the file" + fileName);
      }catch(IOException e){
        System.out.println("Problem with output to file"+ fileName);
      }
      savedPathDropDown.getItems().add(fileName);
      // Provide confirmation that the file is saved to the user.
      invalidEntry.setText("You have successfully saved a file from room "+
      startToSave+ " to "+ destToSave+".");
    }

    /**
    * Method to read the saved floor plan, start and destination numbers
    * from a file of a name saved in the savedPathDropDown combobox.
    * File is to be read when it is selected in the combobox.
    * Notification of the file being read is provided in the terminus.
    * @param fileName the file name for the file to be read as a String.
    */
    public void readFromFile(String fileName){
      /* Try to open the file and a new input stream. If successful, read the
      floor plan as an object followed by the start number and destination number
      as integers.*/
      ObjectInputStream inputStream = null;
      try{
        inputStream = new ObjectInputStream(new FileInputStream(fileName));
      }catch(IOException e){
        System.out.println("Error opening output file"+fileName);
      }

      try{
        planRead = (FloorPlans)inputStream.readObject();
        startRead = inputStream.readInt();
        destRead = inputStream.readInt();
        System.out.println("End of reading from file.");
        inputStream.close();


      }catch(FileNotFoundException e){
        System.out.println("Problem opening file"+ fileName);
      }catch(EOFException e){
        System.out.println("Problem reading1 the file" + fileName);
      }catch(IOException e){
        System.out.println("Problem reading2 the file" + fileName);
      }catch (ClassNotFoundException e){
        System.out.println("Class was not found.");
      }
    }

    /**
    * Method to get all the saved files from a directory.
    * @param: curDir is a directory of type File.
    * @return: savedFiles is an array of type File of the saved files in the parametar direcotry's SavedPaths subdirectory..
    */
    public File[] getSavedFileList(File curDir){
      //The files saved in the current directory.
      File[] savedFiles = curDir.listFiles(); //https://stackoverflow.com/questions/15482423/how-to-list-the-files-in-current-directory
      //Loop through the current directory to find the files that belong to the SavedPaths subdirectory.
      for (File file : savedFiles){
        if(file.getName().endsWith("SavedPaths")){ //http://javaconceptoftheday.com/list-all-files-in-directory-in-java/
          savedPathDir = (file);
        }
      }
      return savedFiles;
    }

  @Override
  public void start(Stage primaryStage){

    /*
    * Welcome screen (first scene)
    */
    BorderPane welcomeBorderPane = new BorderPane(); //Welcome screen on a border pane.
    welcomeBorderPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)");
    //https://stackoverflow.com/questions/22007595/borderpane-with-color-gradient

    Label appTitle = new Label("Room-Finder App"); //Label for the title of the app.
    appTitle.setFont(Font.font("Verdana", FontWeight.BOLD,Constants.APPTITLE_FONTSIZE));

    DropShadow titleShadow = new DropShadow(); //Setting the background effect of the welcome screen.
    appTitle.setEffect(titleShadow);
    appTitle.setTextFill(Color.WHITE);

    Button startButton = new Button("Find a Room"); //Button to enter the program from the welcome screen.
    Image uOfCCoat = new Image(resource +"UofCcoat.png");
    ImageView uOfCImage = new ImageView(uOfCCoat);
    //https://docs.oracle.com/javafx/2/api/javafx/scene/image/ImageView.html

    VBox startVBox = new VBox(Constants.PREFERRED_HBOX_VBOX_SIZE); //VBox for the welcome screen's contents set to the center of the border pane.
    startVBox.getChildren().addAll(appTitle, startButton, uOfCImage);
    welcomeBorderPane.setCenter(startVBox);
    startVBox.setAlignment(Pos.CENTER);

    Scene welcomeScene = new Scene(welcomeBorderPane,Constants.SCENESIZE,Constants.SCENESIZE);


    /*
    * Main screen (second scene)
    */

    /* Put gridpane in VBox with a title above it infoming the user of the building
    and floor number. Both are set to the center. The gridpane can be scrolled. */
    VBox gridPaneVBox = new VBox();
    VBox scrollPaneVBox = new VBox();
    ScrollPane borderScroll = new ScrollPane();
    borderScroll.setPannable(true);
    borderScroll.setFitToWidth(true);
    //https://stackoverflow.com/questions/30687994/how-to-center-the-content-of-a-javafx-8-scrollpane
    borderScroll.setStyle("-fx-background-color:transparent;");
    // https://stackoverflow.com/questions/12899788/javafx-hide-scrollpane-gray-border
    borderScroll.setContent(gridPane);
    scrollPaneVBox.getChildren().addAll(borderScroll);
    gridPane.setAlignment(Pos.CENTER);
    buildingAndFloorLabel.setFont(Font.font("Verdana", Constants.BUILDING_AND_FLOOR_LABEL_FONTSIZE));
    buildingAndFloorLabel.setTextFill(Color.GREEN);
    gridPaneVBox.getChildren().addAll(buildingAndFloorLabel,scrollPaneVBox);
    scrollPaneVBox.setAlignment(Pos.CENTER);
    gridPaneVBox.setAlignment(Pos.CENTER);


    Label appName = new Label ("University of Calgary Pathfinder"); //The name of the app put at the top of the main scene.
    appName.setFont(Font.font("Verdana", FontWeight.BOLD,Constants.APP_LABEL_FONTSIZE+6));
    appName.setPadding(new Insets(10));

    buildingDropDown.getItems().addAll("Taylor Family Digital Library","Bioscience"); //add the two buildings to the dropdown list for choosing the building.

    /*
    * Elevator and stair button box
    */

    eleStairBox.setPadding(new Insets(10));

    Button elevatorB = new Button("Elevator"); //Button for selecting the elevator to change floors.
    elevatorB.setOnAction(new HandleElevatorClick());
    elevatorB.setStyle("-fx-background-color: aquamarine;");

    Button stairB = new Button("Stairs"); //Button for selecting the stairs to change floors.
    stairB.setOnAction(new HandleStairClick());
    stairB.setStyle("-fx-background-color: #f1b10e;");

    Button nextFloorB = new Button("Next Floor"); //Button for changing floors when the start room and destination room are on seperate floors.
    nextFloorB.setOnAction(new HandleNextFloorClick());
    nextFloorB.setStyle("-fx-background-color: #ff4d4d");

    eleStairBox.getChildren().addAll(elevatorB,stairB,nextFloorB); //Put the three buttons to do with changing floors in the same box.


    // Set the radio buttons that control the size of the map into one toggle group and in a VBox.
    smallButton.setToggleGroup(sizeGroup);
    smallButton.setUserData(Constants.SMALL_RECT);
    smallButton.setSelected(true); //Set the small map size as default. http://www.learningaboutelectronics.com/Articles/How-to-select-an-item-by-default-in-JavaFX.php
    String defaultSelection = (String)sizeGroup.getSelectedToggle().getUserData().toString();
    rectLength = Integer.parseInt(defaultSelection); //Set the size of the grid squares to the default size.
    mediumButton.setToggleGroup(sizeGroup);
    mediumButton.setUserData(Constants.MEDIUM_RECT);
    largeButton.setToggleGroup(sizeGroup);
    largeButton.setUserData(Constants.LARGE_RECT);


    /*
    * File Saving and Management **
    */

    /*Creates a subdirectory within current directory of the program was opened in
    called SavedPaths to store any saved paths.
    https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java*/
    new File(System.getProperty("user.dir")+"/SavedPaths").mkdirs();

    //Button to save the path to a file.
    Button savePath = new Button("Save Path");
    //TextField to enter the file name to save to.
    TextField fileTextField = new TextField("Save path as:");
    fileTextField.setMaxWidth(Constants.SAVETEXTFIELDSIZE);

    //get a list of saved paths currently saved within the SavedPaths package.
    File[] filesList = getSavedFileList(curDir);


    /*Loop through the SavedPaths directory and get all files from within it.
    Abridges the name of the file directory, so it is more manageable on the screen.*/
    ArrayList<File> savedPathFiles = new ArrayList<File>();
    File[] savedPathsArray = savedPathDir.listFiles();
    for (File file : savedPathsArray){
      String shortenedNameFile = file+"";
      // If the operating system is Linux it will only take the String of the file name after "SavedPaths/" in the file path.
      if (operatingSystem.equals("Linux")){
        shortenedNameFile = shortenedNameFile.split("SavedPaths/")[1];
      // Else if the operating system is Windows it will only take the String of the file name after the last "\\" in the file path.
      } else{
        shortenedNameFile = shortenedNameFile.substring(shortenedNameFile.lastIndexOf("\\") + 1);
      }
      //https://stackoverflow.com/questions/3243721/how-to-get-the-last-characters-in-a-string-in-java-regardless-of-string-size
      //https://stackoverflow.com/questions/18220022/how-to-trim-a-string-after-a-specific-character-in-java
      savedPathDropDown.getItems().add(shortenedNameFile); //adds saved files' abrdiged names to drop down of saved paths.
    }

    savedPathDropDown.setPromptText("Saved Paths");
    savedPathDropDown.setMaxWidth(Constants.SAVETEXTFIELDSIZE);

    /**
    * Handles the button click of the savePath button to save the path to a file.
    */
    savePath.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        //If no path has been created, informs the user there is nothing to save.
        if(gridVisible== false){
          invalidEntry.setText("There is no path to save.");
          //Else if path is over two floors, informs user it will not be saved to prevent errors.
        }else if(differentFloor == true){
          invalidEntry.setText("Please choose a path with a start and end on the same floor.");
          //Else if user does not provide a name for the saved path, prompts the user to provide a name to save path under.
        }else if(fileTextField.getText().equals("")){
          invalidEntry.setText("Enter a name for the saved path.");
          //Else if the saving name already exists...
        }else if(new File(curDir + "/SavedPaths/"  + fileTextField.getText() + ".dat" ).exists()){
          int counter = 1;
          /*If file name already exists, loop adding a number in brackets to the end of the file name until it
          does not previously exist and save path under this name and inform user.*/
          while(new File(curDir + "/SavedPaths/"  + fileTextField.getText() + "(" + counter + ")" + ".dat" ).exists()){
            counter += 1;
          }
          invalidEntry.setText("This file name already exists. Path saved as: "+ fileTextField.getText() + "(" + counter + ")" + ".dat instead.");
          writeToFile(fileTextField.getText() + "(" + counter + ")" + ".dat");
          //else save file under name provided by user.
        }else{
          writeToFile(fileTextField.getText() + ".dat");
        }
        // Reset messaging that infomrs the user of issues to blank.
        fileTextField.setText("");
      }
    });

    /**
    * Handles user selecting a saved path from the combobox.
    */
    savedPathDropDown.setOnAction(new HandleLoadPathClick());



    mapSize.setAlignment(Pos.TOP_LEFT);
    // group containing radio buttons to control mapsize, the button to save a path, and combobox with saved paths.
    mapSize.getChildren().addAll(smallButton, mediumButton, largeButton, fileTextField, savePath, savedPathDropDown);

    /*Sets the size of the map based on the users selection of the map size radiobutton group.
     https://stackoverflow.com/questions/32424915/how-to-get-selected-radio-button-from-togglegroup */
    sizeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
      public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
        //Condition for if size toggle is selected.
        if (sizeGroup.getSelectedToggle() != null) {
          String userSelection = (String)sizeGroup.getSelectedToggle().getUserData().toString(); //Store the value of the size toggle selected as a Srting.
          //Adjust the size of the squares on the grid and size of the numbers on the map to the according size of the map.
          rectLength = Integer.parseInt(userSelection);
          roomNumberFontSize = (int)sizeGroup.getSelectedToggle().getUserData()/3;
        }
      }
    });

    //HBox for the title of the app.
    HBox titleRow = new HBox();
    titleRow.setAlignment(Pos.CENTER);
    titleRow.getChildren().add(appName);


    //HBox for the Label for messaging to the user to inform of errors or changes when the app is running.
    HBox invalidHBox = new HBox(Constants.PREFERRED_HBOX_VBOX_SIZE);
    invalidHBox.getChildren().add(invalidEntry);
    invalidHBox.setAlignment(Pos.CENTER);

    //Button to submit textfield information for starting and destination rooms and building combobox.
    Button submitB = new Button("Submit");
    submitB.setOnAction(new HandleSubmitClick());

    //The combobox for selecting and providing the building name.
    buildingDropDown.setPromptText("Select a Building:");
    Label buildingDropDownLabel = new Label("Building:");
    VBox buildingDropDownVBox = new VBox();
    buildingDropDownVBox.getChildren().addAll(buildingDropDownLabel, buildingDropDown);

    //The Label and VBox for typing and providing the starting room number.
    Label enterStartRoomLabel = new Label ("Start Room:");
    enterStartRoomVBox.getChildren().addAll(enterStartRoomLabel, enterStartRoom);

    //The Label and VBox for typing and providing the destination room number.
    Label enterDestRoomLabel = new Label("Destination Room:");
    enterDestRoomVBox.getChildren().addAll(enterDestRoomLabel, enterDestRoom);

    //The VBox for holding the submit button.
    VBox submitBBox = new VBox();
    Label submitBlankLabel = new Label(" "); //Blank label for formatting purposes.
    submitBBox.getChildren().addAll(submitBlankLabel,submitB);

    // Create FlowPane to hold items in the top row of the border pane.
    FlowPane topRow = new FlowPane();
    topRow.setAlignment(Pos.CENTER);
    topRow.getChildren().addAll(buildingDropDownVBox, enterStartRoomVBox,
    enterDestRoomVBox, submitBBox);

    //VBox containting everything above the map in border pane.
    VBox topVBox = new VBox(Constants.TOP_VBOX_SIZE);
    topVBox.getChildren().addAll(titleRow,topRow,invalidHBox);
    topVBox.setMinHeight(Constants.TOP_VBOX_MIN_HEIGHT);


    // Create an HBox to hold buttons in the bottom row of the border pane.
    HBox bottomHBox = new HBox(Constants.PREFERRED_HBOX_VBOX_SIZE);

    // Add a new button to go back to Welcome screen.
    Button backButton = new Button("Back");
    bottomHBox.getChildren().addAll(backButton);

    /**
    * Handles a button click to change scene back to the welcome screen.
    */
    backButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        primaryStage.setScene(welcomeScene);
      }
    });

    // Add an About button to provide information about the app such as creators and how to use app.
    Button aboutButton = new Button("About this App");
    bottomHBox.getChildren().add(aboutButton);

    //Add a popup window when user clicks the About button.
    //Source: https://gist.github.com/jewelsea/1926196 jewelsea
    Popup aboutPopup = new Popup();
    Label aboutLabel = new Label("This app allows the user to select a building and \n" +
    "enter a starting room location and a desired destination location. The user also\n"+
    "has the option to click the labelled room numbers instead of entering a room number.\n"+
    "The app will then highlight the path between these two destinations.\n" +
    "To use this app: Select a building from the dropdown box, enter a starting room\n" +
    "number in the first textbox and a destination room in the second textbox. Then press\n" +
    "the Submit button. Or, click a starting room label and a destination room label on the map\n" +
    "with their corresponding buttons before submitting.\n" +
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

    //Add the stackpane to the popup window.
    aboutPopup.getContent().add(aboutStackPane);

    /**
    * Handles a click of the About button to open the About popup with
    * information on the app.
    */
    aboutButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event){
        aboutPopup.show(primaryStage);
      }
    });


    /**
    * Handles a click of the Hide About this App button to hide the
    * information that was previously opened.
    */
    hideAboutButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        aboutPopup.hide();
      }
    });

    // Help button to provide guidance on using the app.
    Button helpButton = new Button("Help?");
    bottomHBox.getChildren().add(helpButton);

    //Add a popup window with information when user clicks the Help button.
    //Source: https://gist.github.com/jewelsea/1926196 jewelsea
    Popup helpPopup = new Popup();
    Label helpLabel = new Label(
    "To use this app: Select a building from the dropdown box, enter a starting room\n" +
    "number in the first textbox and a destination room in the second textbox. Then press\n" +
    "the Submit button.  Or, click a starting room number on the map and click the\n" +
    "corresponding button. Then click the destination room number on the map and click the\n" +
    "corresponding button before clicking the Submit button. If the room numbers are on \n" +
    "different floors: The path will start on the starting room's floor. To view the path\n" +
    "on the destination room's floor press the red \"Next Floor\" button on the right side.\n" +
    "If you enter an invalid room, example room numbers will be provided in a message.");


    //Add a Hide button to hide the Help popup window when opened.
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

    // Add the stackpane to the Help popup window.
    helpPopup.getContent().add(helpStackPane);

    /**
    * Handles a click of the Help button to open the Help popup with its information.
    */
    helpButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event){
        helpPopup.show(primaryStage);
      }
    });


    /**
    * Handles a click of the Hide Help button to hide the popup window.
    */
    hideHelpButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        helpPopup.hide();
      }
    });


    BorderPane mainBorderPane = new BorderPane();
    mainBorderPane.setCenter(gridPaneVBox);
    mainBorderPane.setTop(topVBox);
    mainBorderPane.setBottom(bottomHBox);
    mainBorderPane.setRight(mapSize);

    /* Scrollable display of the grid. Only shows scroll bars when needed.
    * https://docs.oracle.com/javafx/2/ui_controls/scrollpane.htm
    * https://stackoverflow.com/questions/17568688/how-to-resize-javafx-scrollpane-content-to-fit-current-size*/
    ScrollPane scrollPane = new ScrollPane(mainBorderPane);
    scrollPane.setFitToHeight(true);
    scrollPane.setFitToWidth(true);
    scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scrollPane.setPannable(true);

    //main scene of the program and it is set to default sizes.
    Scene mainScene = new Scene(scrollPane,Constants.SCENESIZE,Constants.SCENESIZE);

    /**
    * Handles a button click of the Start button on the Welcome Screen to change the scene to Main scene.
    */
    startButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        primaryStage.setScene(mainScene);
      }
    });

    /**
    * Handles resizing of the window by the user.
    * https://stackoverflow.com/questions/15659817/listener-for-a-stage-minimizing-maximizing-etc
    */
    primaryStage.maximizedProperty().addListener(new ChangeListener<Boolean>() {

        @Override
        public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean isMaximizedScreen) {
            //If the screen is maximized, set the stage to fill the entire screen and position it in the center of the screen.
            if(isMaximizedScreen == true){
              primaryStage.setWidth(bounds.getWidth());
              primaryStage.setHeight(bounds.getHeight());
              primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2);
              primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);
            }// If the screen is no longer maximized, set the stage to the original default size and position it in the center of the screen.
            else if(isMaximizedScreen == false){
              primaryStage.setWidth(Constants.SCENESIZE);
              primaryStage.setHeight(Constants.SCENESIZE);
              primaryStage.setX((bounds.getWidth() - primaryStage.getWidth()) / 2);
              primaryStage.setY((bounds.getHeight() - primaryStage.getHeight()) / 2);
            }
        }
    });


    primaryStage.setTitle("Room Finder App");
    primaryStage.setScene(welcomeScene); //Start on the Welcome screen.
    primaryStage.show();
  }
}
