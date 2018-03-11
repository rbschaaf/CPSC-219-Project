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
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Popup;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ScrollPane;

public class FinderApp extends Application {

  /*
  * Runs the GUI application, including scene creation, button handling
  * and the production of a visual grid.
  *
  * Last edited March 9, 2008 by Riley.
  */

  private int rowNum = 14;
  private int colNum = 18;
  private Map map1 = new Map();
  private GridPane gridPane = new GridPane();
  private int roomNumbers = 0;
  private Label invalidEntry = new Label ("");
  private Label buildingAndFloorLabel = new Label("");
  private int rectLength;

  private final int POPUP_WINDOW_HEIGHT = 600;
  private final int HELP_POPUP_WIDTH = 130;
  private final int ABOUT_POPUP_WIDTH = 170;
  private final int SCENESIZE = 700;
  private final int SMALL_RECT = 30;
  private final int MEDIUM_RECT = 60;
  private final int LARGE_RECT = 90;
  private final int BUILDING_AND_FLOOR_LABEL_FONTSIZE = 18;
  private final int HALL = 1;
  private final int WALL = 0;
  private final int ROOM = 9;
  private final int START = 8;
  private final int DEST = 5;
  private final int PATH = 7;
  private final int REST = 888;
  private final int EL = 555;
  private final int STAIR = 777;
  private final int COFF = 999;
  private int[] notMap = {WALL,HALL,ROOM,START,DEST,PATH,REST,EL,STAIR,COFF};

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

/*
* Handle button click for Submit button. Takes text from Text Fields and
* creates the grid.
*/
  public class HandleButtonClick implements EventHandler<ActionEvent>{
    private int startNumberInput;
    private int destNumberInput ;
    private String buildingInput;
    public HandleButtonClick (){}
    public void handle(ActionEvent event){
        // Get input from the textfields.
        startNumberInput = Integer.parseInt(enterStartRoom.getText());
        destNumberInput  = Integer.parseInt(enterDestRoom.getText());
        buildingInput = buildingDropDown.getValue();
        // Create a new FloorPlan.
        FloorPlans updatedPlan = new FloorPlans();
        // Check if the numbers entered are valid.
        isValidStartRoom(startNumberInput);
        isValidDestRoom(destNumberInput);
        // Set the grid of the new FloorPlan to contain these numbers.
        updatedPlan.setGrid(buildingInput, destNumberInput);
        map1.setCurrentFloorPlan(updatedPlan);
        // Create a new path and set its start and dest inputs.
        Path path = new Path(updatedPlan);
        path.setDestLoc(destNumberInput);
        path.setStartLoc(startNumberInput);
        // Place markers on the map for the start and end points of the path.
        map1.placeDest(path.getEndRow(), path.getEndCol());
        map1.placeStart(path.getStartRow(),path.getStartCol());
        // Create the path from the start location to the dest location.
        path.createPath();
        // Create the updated GUI for the map
        makeGrid(updatedPlan.getGrid(),gridPane, rectLength);
        // Updates the label above the map providing building name and floor number
        buildingAndFloorLabel.setText(setBuildingAndFloorLabel(updatedPlan.getFloorNum(startNumberInput), buildingInput));
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
    }return currentBuildingAndFloor;
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
  * Generate a visual of the current grid.
  */
  public void makeGrid(int[][] aGrid, GridPane aGridPane, int rectLength){
    // Clear the gridPane.
    aGridPane.getChildren().clear();

    // Set up the grid for the floor.
    for (int row = 0; row < rowNum; row++){
      for(int col = 0; col < colNum; col++){
        Rectangle rect = setRectangles(row, col, aGrid, rectLength);

        int roomNumbers = 0;
        roomNumbers = aGrid[row][col];
        //Add room numbers to the grid map.
        Label rooms = setNumbers(roomNumbers);

        StackPane stack = new StackPane();

        stack.getChildren().addAll(rect,rooms);
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
    } else if (aGrid[row][col] == HALL){
      rect.setFill(Color.TRANSPARENT);
    } else if (aGrid[row][col] == ROOM){
      rect.setFill(Color.GREY);
    } else if (aGrid[row][col] == DEST){
      rect.setFill(Color.YELLOW);
    } else if (aGrid[row][col] == START){
      rect.setFill(Color.RED);
    } else if (aGrid[row][col] == PATH){
      rect.setFill(Color.GREEN);
    } else if(aGrid[row][col] == REST){
      rect.setFill(new ImagePattern(imgRestroom));
      //https://stackoverflow.com/questions/22848829/how-do-i-add-an-image-inside-a-rectangle-or-a-circle-in-javafx
    } else if(aGrid[row][col] == STAIR){
      rect.setFill(new ImagePattern(imgStairs));
    } else if(aGrid[row][col] == EL){
      rect.setFill(new ImagePattern(imgElevator));
    } else if(aGrid[row][col] == COFF){
      rect.setFill(new ImagePattern(imgCoffee));
    }else{
      rect.setFill(Color.LIGHTBLUE);
    }
    rect.setStroke(Color.BLACK);
    rect.setWidth(rectLength);
    rect.setHeight(rectLength);

    return rect;
  }
  /*
  * Method that sets number labels for tiles in the grid.
  */
  public Label setNumbers(int roomNumbers){
    Label rooms = new Label("");
    if (contains(notMap,roomNumbers) == false){
      rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));
      rooms.setText("" + roomNumbers);
    } else if (roomNumbers == START){
      rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));
      rooms.setText("" + "S");
    } else if (roomNumbers == DEST){
      rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));
      rooms.setText("" + "D");
    }
    return rooms;
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
    appTitle.setFont(Font.font("Verdana", FontWeight.BOLD,40));

    Button startButton = new Button("Find a Room");


    VBox startVBox = new VBox(10);
    startVBox.getChildren().addAll(appTitle, startButton);
    borderPanes1.setCenter(startVBox);
    startVBox.setAlignment(Pos.CENTER);

    Scene scene1 = new Scene(borderPanes1,SCENESIZE,SCENESIZE);


    //**
    // Main screen (second scene)
    //**

    /* Put gridpane in VBox with a title above it infoming the user of the building
    and floor number. Both are set to the center. */
    VBox gridPaneVBox = new VBox();
    gridPane.setAlignment(Pos.CENTER);
    buildingAndFloorLabel.setFont(Font.font("Verdana", BUILDING_AND_FLOOR_LABEL_FONTSIZE));
    gridPaneVBox.getChildren().addAll(buildingAndFloorLabel,gridPane);
    gridPaneVBox.setAlignment(Pos.CENTER);


    Label appName = new Label ("Taylor Family Digital Library Pathfinder");
    appName.setFont(Font.font("Verdana", FontWeight.BOLD,15));

    buildingDropDown.getItems().addAll("Taylor Family Digital Library");

    // Set the radio buttons that control the size of the map into one group and in a VBox.
    smallButton.setToggleGroup(sizeGroup);
    smallButton.setUserData(SMALL_RECT);
    smallButton.setSelected(true); //http://www.learningaboutelectronics.com/Articles/How-to-select-an-item-by-default-in-JavaFX.php
    String defaultSelection = (String)sizeGroup.getSelectedToggle().getUserData().toString();
    rectLength = Integer.parseInt(defaultSelection);
    mediumButton.setToggleGroup(sizeGroup);
    mediumButton.setUserData(MEDIUM_RECT);
    largeButton.setToggleGroup(sizeGroup);
    largeButton.setUserData(LARGE_RECT);
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
         }
     }
});

    HBox topRow2 = new HBox();
    topRow2.setAlignment(Pos.CENTER);
    topRow2.getChildren().add(appName);


    HBox invalidHBox = new HBox(10);
    invalidHBox.getChildren().add(invalidEntry);
    invalidHBox.setAlignment(Pos.CENTER);

    // New button to submit textfield information.
    Button submitB = new Button("Submit");
    submitB.setOnAction(new HandleButtonClick());

    // Create an HBox to hold items in the top row of the border pane.
    HBox topRow = new HBox();
    topRow.setAlignment(Pos.CENTER);
    topRow.getChildren().addAll(buildingDropDown, enterStartRoom,
    enterDestRoom, submitB);

    VBox topVBox = new VBox(15);
    topVBox.getChildren().addAll(topRow2,topRow,invalidHBox);


    // Create an HBox to hold items in the  bottom row of the border pane.
    HBox bottomHBox = new HBox(10);

    // Add a new button to go back to scene 1.
    Button backButton = new Button("Back");
    bottomHBox.getChildren().add(backButton);

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
    "enter a starting room location and a desired destination location. The app will \n" +
    "then highlight the path between these two destinations.\n" +
    "To use this app: Select a building from the dropdown box, enter a starting room\n" +
    "number in the first textbox and a destination room in the second textbox. Then press\n" +
    "the submit button.\n" +
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
    aboutBackground.setWidth(POPUP_WINDOW_HEIGHT);
    aboutBackground.setHeight(ABOUT_POPUP_WIDTH);
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
    Button helpButton = new Button("Help!");
    bottomHBox.getChildren().add(helpButton);

    //Add a popup window when user clicks the Help button.
    //Source: https://gist.github.com/jewelsea/1926196 jewelsea
    Popup helpPopup = new Popup();
    Label helpLabel = new Label(
    "To use this app: Select a building from the dropdown box, enter a starting room\n" +
    "number in the first textbox and a destination room in the second textbox. Then press\n" +
    "the submit button. If you enter an invalid room, example room numbers will be provided\n" +
    "in a message.");


    //Add a Hide button to hide the Help popup window.
    Button hideHelpButton = new Button("Hide \"Help\"");
    bottomHBox.getChildren().add(hideHelpButton);

    /* Create VBox to hold the information and the button to hide the information
    when the Help popup is clicked. */
    VBox helpVBox = new VBox();
    helpVBox.setAlignment(Pos.CENTER);
    helpVBox.getChildren().addAll(helpLabel, hideHelpButton);

    // Create stackpane for the Help popup window with a light-green background.
    Rectangle helpBackground = new Rectangle();
    helpBackground.setFill(Color.LIGHTGREEN);
    helpBackground.setWidth(POPUP_WINDOW_HEIGHT);
    helpBackground.setHeight(HELP_POPUP_WIDTH);
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


    Scene scene2 = new Scene(scrollPane,SCENESIZE,SCENESIZE);

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
