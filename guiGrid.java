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



public class guiGrid extends Application {

  /*
  * There needs to be an argument for the new map creation to work with the
  * changes I made, ideally this would come from a button click that would
  * take in a room number - Dayan 22 Feb 2018
  */

  private int roomNumber =160;
  private String building = "Taylor Family Digital Library";
  //private Map map1 = new Map(roomNumber,building);
  private int rowNum = 14;
  private int colNum = 18;
  private FloorPlans currentFloorPlan;
  /* private int[][] testGrid = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {0,0,1,1,1,1,1,1,9,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,261,1,260,1,252,9,9,9,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,0,0,0,0,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,0,0,0,0},
  {0,0,1,1,262,1,263,1,264,1,1,1,1,1,1,251,9,9,0},
  {0,0,1,1,1,259,1,1,1,1,1,1,1,1,9,9,9,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,250,9,9,0},
  {0,0,1,1,9,9,9,9,9,9,1,1,1,1,9,9,9,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,25,9,0,0},
  {0,0,1,1,1,1,1,1,1,1,1,1,1,1,9,9,0,0},
  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
  */
  //private Map copy = new Map(map1);

  private int roomNumbers = 0;


  // Make the image of the map grid.
  public void makeGUI(int[][] aGrid, GridPane aGridPane){
    for (int row = 0; row < rowNum; row++){
      for(int col = 0; col < colNum; col++){
        Rectangle rect = new Rectangle();
        if (aGrid[row][col] == 0){
          rect.setFill(Color.BLACK);
        } else if (aGrid[row][col] == 1){
          rect.setFill(Color.TRANSPARENT);
        } else if (aGrid[row][col] == 9){
          rect.setFill(Color.GREY);
        } else{
          rect.setFill(Color.LIGHTBLUE);
        }
        //int roomNumbers = 0;
        //roomNumbers = map1.getGridPointNum(row,col);
        Label rooms = new Label("");
        //conditional to add room numbers to the grid map.
        if (roomNumbers != 0 && roomNumbers != 1 && roomNumbers != 9){
          rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));
          rooms.setText("" + roomNumbers);
        }
        rect.setStroke(Color.BLACK);
        rect.setWidth(30);
        rect.setHeight(30);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(rect, rooms);
        GridPane.setRowIndex(stack, row);
        GridPane.setColumnIndex(stack, col);
        aGridPane.getChildren().addAll(stack);
        //https://stackoverflow.com/questions/35367060/gridpane-of-squares-in-javafx
      }
    }

  }




  public void start(Stage primaryStage){
    // Create a new scene to start the app
    BorderPane borderPanes1 = new BorderPane();

    Label appTitle = new Label("Room-Finder App!");
    appTitle.setFont(Font.font("Verdana", FontWeight.BOLD,20));

    Button startButton = new Button("Start");

    TextField buildingText = new TextField("Enter the building name.");

    TextField roomText = new TextField("Enter the room number.");


    VBox startVBox = new VBox(10);
    startVBox.getChildren().addAll(appTitle, buildingText, roomText,startButton);


    //add all boxes to borderPane2
    borderPanes1.setCenter(startVBox);
    startVBox.setAlignment(Pos.CENTER);


    // create the scene itself
    Scene scene1 = new Scene(borderPanes1,700,700);


    // Main screen (second scene)
    HBox topRow = new HBox();
    topRow.setAlignment(Pos.CENTER);
    Label appName = new Label ("Taylor Family Digital Library Pathfinder");

    //https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
    ComboBox<String> buildingDropDown = new ComboBox<String>();
    buildingDropDown.getItems().addAll("Taylor Family Digital Library");

    TextField enterStartRoom = new TextField("Enter starting room");
    TextField enterDestRoom= new TextField("Enter destination room");

    topRow.getChildren().addAll(appName, buildingDropDown, enterStartRoom, enterDestRoom);

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);

    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
    BorderPane borderPanes2 = new BorderPane();
    borderPanes2.setCenter(gridPane);
    borderPanes2.setTop(topRow);

    Scene scene2 = new Scene(borderPanes2,700,700);

    // Create the GUI for the map
    currentFloorPlan = new FloorPlans();
    currentFloorPlan.setGrid(building,roomNumber);
    int[][] newGrid = currentFloorPlan.getGrid();
    makeGUI(newGrid,gridPane);


    //handle when start button (in scene 1) is clicked
    startButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        String building = buildingText.getText();
        int roomNumber = Integer.parseInt(roomText.getText());
        primaryStage.setScene(scene2);
      }
    });

    primaryStage.setTitle("GUI");
    primaryStage.setScene(scene1);
    primaryStage.show();
  }
}
