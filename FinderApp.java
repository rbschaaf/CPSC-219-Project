import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ScrollPane;
import java.util.ArrayList;
//import java.util.NumberFormatException;

public class FinderApp extends Application {

  /*
  * Runs the GUI application, including scene creation, button handling
  * and the production of a visual grid.
  *
  * Last edited March 9, 2008 by Riley.
  */

  private Map map1 = new Map();
  private GridPane gridPane = new GridPane();
  private int roomNumbers = 0;
  private Label invalidEntry = new Label ("");
  private Label buildingAndFloorLabel = new Label("");
  private int rectLength;
  private int roomNumberFontSize = 10;
  private int startNumberInput;
  private int destNumberInput ;
  private int clickedRoom;
  private VBox enterStartRoomVBox = new VBox();
  private VBox enterDestRoomVBox = new VBox();
  private Rectangle[][] rectangleGrid = new Rectangle[Constants.ROWNUM][Constants.COLNUM];
  private Button startRoomButton = new Button("Store selection in start room");
  private Button destRoomButton = new Button("Store selection in destination room");

  private int[] notMap = {Constants.WALL,Constants.HALL,Constants.ROOM,Constants.START,Constants.DEST,
    Constants.PATH,Constants.REST,Constants.EL,Constants.STAIR,Constants.COFF};

    private TextField enterStartRoom = new TextField("Enter the start room");
    private TextField enterDestRoom= new TextField("Enter destination room");
    private ComboBox<String> buildingDropDown = new ComboBox<String>();
    //https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm

    private ToggleGroup sizeGroup = new ToggleGroup();
    private RadioButton smallButton = new RadioButton("Small Map");
    private RadioButton mediumButton = new RadioButton("Medium Map");
    private RadioButton largeButton = new RadioButton("Large Map");
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/RadioButton.html

    private Image imgRestroom = new Image("RestroomImage.png",rectLength,rectLength, true, false);
    //Image source: http://maxpixel.freegreatpicture.com/Rest-Room-Restroom-Ladies-Restroom-Public-Restroom-99226
    private Image imgCoffee = new Image("Coffee.png",rectLength,rectLength,true,false);
    //Image source: https://www.freepik.com/free-icon/hot-coffee-rounded-cup-on-a-plate-from-side-view_732944.htm
    private Image imgStairs = new Image("Stairs.png", rectLength, rectLength, true, false);
    //Image source:https://pixabay.com/en/stairs-climb-levels-descend-44071/
    private Image imgElevator = new Image("Elevator.png", rectLength, rectLength, true, false);
    //Image source: https://pixabay.com/en/elevator-people-silhouette-down-44013//
    private FloorPlans currentFloorPlan;


    /*
    * Method that handles the clicking of the start room on the grid.
    * Removes the sta
    */
    public class HandleStartRoomClick implements EventHandler<ActionEvent>{
      int row1;
      int col1;
      int rNum;

      public HandleStartRoomClick(int aRow, int aCol, int roomN){
        rNum = roomN;
        row1 = aRow;
        col1 = aCol;
      }
      public void handle(ActionEvent event){
        enterStartRoom.setText(""+rNum);
        rectangleGrid[row1][col1].setFill(Color.LIGHTBLUE);
        enterStartRoomVBox.getChildren().remove(startRoomButton);
        enterDestRoomVBox.getChildren().remove(destRoomButton);
      }
    }
    /*
    * Method that handles the clicking of a room button/room number on the map.
    * Allows user to select to store the room value in the start room or destination
    room. The user can also unselect the room by clicking the temporary button generated
    * at it.
    http://code.makery.ch/blog/javafx-8-event-handling-examples/
    */
    public class HandleRoomClick implements EventHandler<ActionEvent>{
      int roomNum;
      int count =0;
      StackPane aSP;
      public HandleRoomClick(int roomNumber, StackPane aStackPane){
        roomNum = roomNumber;
        aSP = aStackPane;
      }
      public void handle(ActionEvent event){
        int row;
        int col;

        enterStartRoomVBox.getChildren().remove(startRoomButton);
        enterDestRoomVBox.getChildren().remove(destRoomButton);
        for(row=0;row<Constants.ROWNUM;row++){
          for(col=0;col<Constants.COLNUM;col++){
            if(map1.getCurrentFloorPlan().getGrid()[row][col] == roomNum && count ==0){

              rectangleGrid[row][col].setFill(Color.RED);
              count+=1;
              enterStartRoomVBox.getChildren().add(startRoomButton);
              enterDestRoomVBox.getChildren().add(destRoomButton);

              /*
              * Clicking the button below the Start room textfield stores the selected room
              * number in the textfield.
              */
              startRoomButton.setOnAction(new HandleStartRoomClick(row,col,roomNum));

              /*
              * Clicking the button below the Destination room textfield stores the selected room
              * number in the textfield.
              */
              destRoomButton.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent event){
                  enterDestRoom.setText(""+roomNum);
                  enterStartRoomVBox.getChildren().remove(startRoomButton);
                  enterDestRoomVBox.getChildren().remove(destRoomButton);
                }
              });
            }else if(map1.getCurrentFloorPlan().getGrid()[row][col] == roomNum && count ==1){
              rectangleGrid[row][col].setFill(Color.LIGHTBLUE);
              count=0;


            }
          }
        }
      }
    }
    /*
    * Handle button click for Submit button. Takes text from Text Fields and
    * creates the grid.
    */
    public class HandleButtonClick implements EventHandler<ActionEvent>{
      private String buildingInput;
      public HandleButtonClick (){}
        public void handle(ActionEvent event){
          // Get input from the textfields.
          invalidEntry.setText("");
          try{
            startNumberInput = Integer.parseInt(enterStartRoom.getText());
            destNumberInput  = Integer.parseInt(enterDestRoom.getText());
          } catch(NumberFormatException e){
            invalidEntry.setText ("Please enter the room number as an integer.");
          }
          buildingInput = buildingDropDown.getValue();
          // Create a new FloorPlan.
          FloorPlans updatedPlan = new FloorPlans(buildingInput, destNumberInput);
          // Check if the numbers entered are valid.
          isValidStartRoom(startNumberInput);
          isValidDestRoom(destNumberInput);
          // Set the grid of the new FloorPlan to contain these numbers.
          //updatedPlan.setGrid(buildingInput, destNumberInput);
          if(isValidStartRoom(startNumberInput) && isValidDestRoom(destNumberInput)){
            map1.setCurrentFloorPlan(updatedPlan);
            // Create a new path and set its start and dest inputs.
            Path path = new Path(updatedPlan.getGrid(),
            startNumberInput, destNumberInput);


            // Place markers on the map for the start and end points of the path.
            /*
            * I got rid of start and end placers as the new path does not require
            * them - Dayan J.
            * --15 Mar 2018
            */
            map1.setStartValues(updatedPlan,startNumberInput);
            map1.setEndValues(updatedPlan,destNumberInput);
            //map1.placeDest();
            //map1.placeStart();
            // Create the path from the start location to the dest location.
            // path.createPath();
            // Create the updated GUI for the map
            int[][] finalGrid = path.createPath();

            map1.placeStart(finalGrid);
            map1.placeDest(finalGrid);

            makeGrid(finalGrid,gridPane,rectLength);
            highlight(updatedPlan, destNumberInput);

            // Updates the label above the map providing building name and floor number
            buildingAndFloorLabel.setText(setBuildingAndFloorLabel(updatedPlan.getFloorNum(startNumberInput), buildingInput));
            buildingAndFloorLabel.setTextFill(Color.GREEN);
            //http://www.java2s.com/Code/Java/JavaFX/SetLabelTextcolor.htm
          }
        }
      }


      /*
      * Method to set the building and floor label above the map. Returns a string for the label
      * and requires a floor number and building name.
      */
      public String setBuildingAndFloorLabel(int floorNumber, String buildingName){
        String currentBuildingAndFloor = "";
        if (floorNumber%10 == 1){
          currentBuildingAndFloor = buildingName + " " + floorNumber + "st Floor";
        } else if (floorNumber%10 == 2){
          currentBuildingAndFloor = buildingName + " " + floorNumber +  "nd Floor";
        } else if (floorNumber%10 == 3){
          currentBuildingAndFloor = buildingName + " " + floorNumber +  "rd Floor";
        } else{
          currentBuildingAndFloor = buildingName + " " + floorNumber +  "th Floor";
        }
        return currentBuildingAndFloor;
      }


      /*
      * Method to confirm whether an array contains a value.
      */
      public boolean contains(int[] anArray, int aValue){
        boolean result = false;
        for(int i : anArray){
          if(i == aValue){
            result = true;
          }
        }
        return result;
      }

      /*
      * Highlight a room in the Grid.
      */
      public void highlight(FloorPlans aFloorPlan, int roomNumber){
        int roomNum = roomNumber;
        for(int row=0;row<Constants.ROWNUM;row++){
          for(int col=0;col<Constants.COLNUM;col++){
            //Room colours
            if(aFloorPlan.getRoom(roomNum+1000) != null ){
              if(aFloorPlan.getGrid()[row][col] == aFloorPlan.getRoom(roomNum+1000).getRoomsNumber()){
                rectangleGrid[row][col].setFill(Color.BLUE);

                /*//Door colours
              }else if((aFloorPlan.getGrid()[row][col]) == roomNumber)
              rectangleGrid[row][col].setFill(Color.AQUAMARINE);
            }*/
          }
        }
      }
    }
  }
  /*
  * Generate a visual of the current grid.
  */
  public void makeGrid(int[][] aGrid, GridPane aGridPane, int rectLength){
    // Clear the gridPane.
    aGridPane.getChildren().clear();

    // Set up the grid for the floor.
    for (int row = 0; row < Constants.ROWNUM; row++){
      for(int col = 0; col < Constants.COLNUM; col++){
        Rectangle rect = setRectangles(row, col, aGrid, rectLength);

        int roomNumbers = 0;

        roomNumbers = aGrid[row][col];

        // An overlaying stackpane that is mouse transparent containing the rect and room labels.
        StackPane overlayStack = new StackPane();

        //Add room numbers to the grid map.
        Label rooms = setNumbers(roomNumbers);

        overlayStack.getChildren().addAll(rect,rooms);
        overlayStack.setMouseTransparent(true); //https://stackoverflow.com/questions/9899347/when-adding-a-second-item-to-my-stackpane-the-first-item-loses-its-event-mouseo

        // Complete stackpane containing the overlay stackpane and the clickable buttons hidden underneath.
        StackPane stack = new StackPane();

        //Add buttons at the room numbers on the map, if there is a room number there.
        if (rooms.getText().isEmpty()){ //https://stackoverflow.com/questions/25189027/how-to-check-if-lable-is-not-empty
          stack.getChildren().add(overlayStack);
        } else{
          Button roomButton = createRoomButtons(roomNumbers, startNumberInput, destNumberInput, stack);
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
  /*
  * Method that sets the rectangles for the grid.
  */
  public Rectangle setRectangles(int row, int col, int[][] aGrid, int rectLength){

    Rectangle rect = new Rectangle();
    if (aGrid[row][col] == 0){
      rect.setFill(Color.BLACK);
    } else if (aGrid[row][col] == Constants.HALL){
      rect.setFill(Color.TRANSPARENT);
    } else if (aGrid[row][col] == Constants.ROOM){
      rect.setFill(Color.GREY);
    } else if (aGrid[row][col] == Constants.DEST){
      rect.setFill(Color.YELLOW);
    } else if (aGrid[row][col] == Constants.START){
      rect.setFill(Color.ORANGE);
    } else if (aGrid[row][col] == Constants.PATH){
      rect.setFill(Color.GREEN);
    } else if(aGrid[row][col] == Constants.REST){
      rect.setFill(new ImagePattern(imgRestroom));
      //https://stackoverflow.com/questions/22848829/how-do-i-add-an-image-inside-a-rectangle-or-a-circle-in-javafx
    } else if(aGrid[row][col] == Constants.STAIR){
      rect.setFill(new ImagePattern(imgStairs));
    } else if(aGrid[row][col] == Constants.EL){
      rect.setFill(new ImagePattern(imgElevator));
    } else if(aGrid[row][col] == Constants.COFF){
      rect.setFill(new ImagePattern(imgCoffee));
    }else{
      rect.setFill(Color.LIGHTBLUE);
    }
    rect.setStroke(Color.BLACK);
    rect.setWidth(rectLength);
    rect.setHeight(rectLength);

    rectangleGrid[row][col] = rect;

    return rect;
  }
  /*
  * Method that sets number labels for tiles in the grid.
  */
  public Label setNumbers(int roomNumbers){
    Label rooms = new Label("");
    if(roomNumbers<1000){
      if (contains(notMap,roomNumbers) == false){
        rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
        rooms.setText("" + roomNumbers);
      } else if (roomNumbers == Constants.START){
        rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
        rooms.setText("" + "S");
      } else if (roomNumbers == Constants.DEST){
        rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, roomNumberFontSize));
        rooms.setText("" + "D");
      }
    }

    return rooms;
  }

  /*
  * Method that creates buttons for the room numbers on the map.
  */
  public Button  createRoomButtons(int roomNumbers, int startNumberInput, int destNumberInput, StackPane stack){
    Button aRoomButton = new Button();
    if (contains(notMap,roomNumbers) == false){
      aRoomButton = new Button (""+roomNumbers);
      //roomButtons.setOnAction(new NumberButtonHandler(aRoomButton));
    } else if(roomNumbers == Constants.START){
      aRoomButton = new Button (""+startNumberInput);
      //roomButtons.setOnAction(new NumberButtonHandler(aRoomButton));
    } else if(roomNumbers == Constants.DEST){
      aRoomButton = new Button (""+destNumberInput);
      //roomButtons.setOnAction(new NumberButtonHandler(aRoomButton));
    }
    Integer roomNumberValue = Integer.parseInt(aRoomButton.getText());

    /*
    * Clciking a room button calls the selectRoom method
    */
    aRoomButton.setOnAction(new HandleRoomClick(roomNumberValue, stack));
    return aRoomButton;
  }


  /*
  * Check if entered valid start is entered.
  */
  public boolean isValidStartRoom (int aStartRoom){
    boolean isValidStart = true;
    if(aStartRoom != 25 && aStartRoom != 150&&  aStartRoom != 151&&
    aStartRoom != 152&&  aStartRoom != 153&& aStartRoom != 154&&
    aStartRoom != 155&&  aStartRoom != 250&&  aStartRoom != 251&&
    aStartRoom != 252&&  aStartRoom != 259&& aStartRoom != 260&&
    aStartRoom != 261&&  aStartRoom != 262&& aStartRoom != 263&&
    aStartRoom != 264){
      System.out.println("Please enter a valid start room. Example: 262 or 264");
      invalidEntry.setText("Please enter a valid start room. Example: 262 or 264");
      isValidStart = false;
    }return isValidStart;
  }

  /*
  * Check if a valid destination is entered.
  */
  public boolean isValidDestRoom (int aDestRoom){
    boolean isValidDest = true;
    if(aDestRoom != 25 && aDestRoom != 150&&  aDestRoom != 151&&
    aDestRoom != 152&&  aDestRoom != 153&& aDestRoom != 154&&
    aDestRoom != 155&&  aDestRoom != 250&&  aDestRoom != 251&&
    aDestRoom != 252&&  aDestRoom != 259&& aDestRoom != 260&&
    aDestRoom != 261&&  aDestRoom != 262&& aDestRoom != 263&&
    aDestRoom != 264){
      System.out.println("Please enter a valid end room. Example: 251 or 261");
      invalidEntry.setText("Please enter a valid end room. Example: 251 or 261");
      isValidDest = false;
    }return isValidDest;
  }

  @Override
  public void start(Stage primaryStage){

    //**
    // Welcome screen (first scene)
    //**

    BorderPane borderPanes1 = new BorderPane();
    borderPanes1.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)");
    //https://stackoverflow.com/questions/22007595/borderpane-with-color-gradient

    Label appTitle = new Label("Room-Finder App!");
    appTitle.setFont(Font.font("Verdana", FontWeight.BOLD,Constants.APPTITLE_FONTSIZE));

    Button startButton = new Button("Find a Room");
    Image uOfCCoat = new Image("UofCcoat.png");
    ImageView uOfCImage = new ImageView(uOfCCoat);
    //https://docs.oracle.com/javafx/2/api/javafx/scene/image/ImageView.html

    VBox startVBox = new VBox(Constants.PREFERRED_HBOX_VBOX_SIZE);
    startVBox.getChildren().addAll(appTitle, startButton, uOfCImage);
    borderPanes1.setCenter(startVBox);
    startVBox.setAlignment(Pos.CENTER);

    Scene scene1 = new Scene(borderPanes1,Constants.SCENESIZE,Constants.SCENESIZE);


    //**
    // Main screen (second scene)
    //**

    /* Put gridpane in VBox with a title above it infoming the user of the building
    and floor number. Both are set to the center. */
    VBox gridPaneVBox = new VBox();
    gridPane.setAlignment(Pos.CENTER);
    buildingAndFloorLabel.setFont(Font.font("Verdana", Constants.BUILDING_AND_FLOOR_LABEL_FONTSIZE));
    gridPaneVBox.getChildren().addAll(buildingAndFloorLabel,gridPane);
    gridPaneVBox.setAlignment(Pos.CENTER);


    Label appName = new Label ("Taylor Family Digital Library Pathfinder");
    appName.setFont(Font.font("Verdana", FontWeight.BOLD,Constants.APP_LABEL_FONTSIZE));

    buildingDropDown.getItems().addAll("Taylor Family Digital Library");

    // Set the radio buttons that control the size of the map into one group and in a VBox.
    smallButton.setToggleGroup(sizeGroup);
    smallButton.setUserData(Constants.SMALL_RECT);
    smallButton.setSelected(true); //http://www.learningaboutelectronics.com/Articles/How-to-select-an-item-by-default-in-JavaFX.php
    String defaultSelection = (String)sizeGroup.getSelectedToggle().getUserData().toString();
    rectLength = Integer.parseInt(defaultSelection);
    mediumButton.setToggleGroup(sizeGroup);
    mediumButton.setUserData(Constants.MEDIUM_RECT);
    largeButton.setToggleGroup(sizeGroup);
    largeButton.setUserData(Constants.LARGE_RECT);
    VBox mapSize = new VBox();
    mapSize.setAlignment(Pos.TOP_LEFT);
    mapSize.getChildren().addAll(smallButton, mediumButton, largeButton);

    /*Sets the size of the map based on the users selection of the map size radiobutton group.
    * https://stackoverflow.com/questions/32424915/how-to-get-selected-radio-button-from-togglegroup */
    sizeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
      public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
        if (sizeGroup.getSelectedToggle() != null) {
          String userSelection = (String)sizeGroup.getSelectedToggle().getUserData().toString();
          rectLength = Integer.parseInt(userSelection);
          roomNumberFontSize = (int)sizeGroup.getSelectedToggle().getUserData()/3;
          System.out.println(roomNumberFontSize);
        }
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

    Label enterStartRoomLabel = new Label ("Start Room:");
    enterStartRoomVBox.getChildren().addAll(enterStartRoomLabel, enterStartRoom);

    Label enterDestRoomLabel = new Label("Destination Room:");
    enterDestRoomVBox.getChildren().addAll(enterDestRoomLabel, enterDestRoom);

    // Create FlowPane to hold items in the top row of the border pane.
    FlowPane topRow = new FlowPane();
    topRow.setAlignment(Pos.CENTER);
    topRow.getChildren().addAll(buildingDropDownVBox, enterStartRoomVBox,
    enterDestRoomVBox, submitB);

    VBox topVBox = new VBox(15);
    topVBox.getChildren().addAll(topRow2,topRow,invalidHBox);


    // Create an HBox to hold items in the  bottom row of the border pane.
    HBox bottomHBox = new HBox(Constants.PREFERRED_HBOX_VBOX_SIZE);



    // Add a new button to go back to scene 1.
    Button backButton = new Button("Back");
    bottomHBox.getChildren().addAll(backButton);

    /*
    * Handles a button click to change scene.
    */
    backButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
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
    "enter a starting room location and a desired destination location. The user also\n"+
    "has the option to click the labelled room numbers instead of entering a room number.\n"+
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
      public void handle(ActionEvent event){
        aboutPopup.show(primaryStage);
      }
    });


    /** Handles a click of the Hide About this App button. */
    hideAboutButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
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
    "in a message. Or, click a starting room label and a destination room label on the map and \n"+
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

    // Handles a click of the Help button to open the Help popup.
    helpButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event){
        helpPopup.show(primaryStage);
      }
    });


    /** Handles a click of the Hide Help button. */
    hideHelpButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
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


    Scene scene2 = new Scene(scrollPane,Constants.SCENESIZE,Constants.SCENESIZE);

    /*
    * Handles a button click to change the scene to scene 2.
    */
    startButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        primaryStage.setScene(scene2);
      }
    });

    primaryStage.setTitle("Room Finder App");
    primaryStage.setScene(scene1);
    primaryStage.show();
  }
}
