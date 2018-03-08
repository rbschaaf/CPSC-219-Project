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


public class FinderApp extends Application {

  /*
  * Runs the GUI application, including scene creation, button handling
  * and the production of a visual grid.
  *
  * Last edited March 7, 2008 by Nicki.
  */

  private int rowNum = 14;
  private int colNum = 18;
  private Map map1 = new Map();
  private GridPane gridPane = new GridPane();
  private int roomNumbers = 0;
  private Label invalidEntry = new Label ("");


  private final int SCENESIZE = 700;
  private final int RECTD =30;
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

  private Image imgRestroom = new Image("RestroomImage.png",RECTD,RECTD, true, false);
  //Image source: http://maxpixel.freegreatpicture.com/Rest-Room-Restroom-Ladies-Restroom-Public-Restroom-99226
  private Image imgCoffee = new Image("Coffee.png",RECTD,RECTD,true,false);
  //Image source: https://www.freepik.com/free-icon/hot-coffee-rounded-cup-on-a-plate-from-side-view_732944.htm
  private Image imgStairs = new Image("Stairs.png", RECTD, RECTD, true, false);
  //Image source:https://pixabay.com/en/stairs-climb-levels-descend-44071/
  private Image imgElevator = new Image("Elevator.png", RECTD, RECTD, true, false);
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
        makeGrid(updatedPlan.getGrid(),gridPane);
    }
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
  public void makeGrid(int[][] aGrid, GridPane aGridPane){
    // Clear the gridPane.
    aGridPane.getChildren().clear();

    // Set up the grid for the floor.
    for (int row = 0; row < rowNum; row++){
      for(int col = 0; col < colNum; col++){
        Rectangle rect = setRectangles(row, col, aGrid);

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
  public Rectangle setRectangles(int row, int col, int[][] aGrid){

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
    rect.setWidth(RECTD);
    rect.setHeight(RECTD);

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
      System.out.println("Please enter a valid start room. Example: 162 or 164");
      invalidEntry.setText("Please enter a valid start room. Example: 162 or 164");
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
      System.out.println("Please enter a valid end room. Example: 151 or 161");
      invalidEntry.setText("Please enter a valid end room. Example: 151 or 161");
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

    // Set the alignment of the grid pane.
    gridPane.setAlignment(Pos.CENTER);

    Label appName = new Label ("Taylor Family Digital Library Pathfinder");
    appName.setFont(Font.font("Verdana", FontWeight.BOLD,15));

    buildingDropDown.getItems().addAll("Taylor Family Digital Library");

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


    BorderPane borderPanes2 = new BorderPane();
    borderPanes2.setCenter(gridPane);
    borderPanes2.setTop(topVBox);
    borderPanes2.setBottom(bottomHBox);

    Scene scene2 = new Scene(borderPanes2,SCENESIZE,SCENESIZE);

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

