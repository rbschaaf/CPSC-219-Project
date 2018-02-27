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
  private FloorPlans floorPlan1;
  /*
  * There needs to be an argument for the new map creation to work with the
  * changes I made, ideally this would come from a button click that would
  * take in a room number - Dayan 22 Feb 2018
  */

  private int roomNumber;
  private Map map1 = new Map(roomNumber);
  private int rowNum = 14;
  private int colNum = 18;
  //private Map copy = new Map(map1);
  private String building;
  private int roomNumbers = 0;



  public void makeGUI(int[][] aGrid, GridPane aGridPane){
    int[][] copyGrid = aGrid;
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
        roomNumbers = map1.getGridPointNum(row,col);
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
    // Create a startScreen scene.
    BorderPane borderPane2 = new BorderPane();

    Label appTitle = new Label("Room-Finder App!");
    appTitle.setFont(Font.font("Verdana", FontWeight.BOLD,20));

    Button startButton = new Button("Start");

    TextField buildingText = new TextField("Enter the building name.");

    TextField roomText = new TextField("Enter the room number.");


    VBox startVBox = new VBox(10);
    startVBox.getChildren().addAll(appTitle, buildingText, roomText,startButton);


    //add all boxes to borderPane2
    borderPane2.setCenter(startVBox);
    startVBox.setAlignment(Pos.CENTER);


    Group root = new Group();
    root.getChildren().add(borderPane2);
    Scene sceneStart = new Scene(root,700,700);

    int[][] copyGrid = map1.getCopyGrid();

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
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(gridPane);
    borderPane.setTop(topRow);

    Scene scene2 = new Scene(borderPane,700,700);
    //makeGUI(copyGrid,gridPane);

    //handle when start button is clicked
    startButton.setOnAction(new EventHandler<ActionEvent>(){
      public void handle(ActionEvent event){
        String building = buildingText.getText();
        int roomNumber = Integer.parseInt(roomText.getText());
        primaryStage.setScene(scene2);
      }
    });

    primaryStage.setTitle("GUI");
    primaryStage.setScene(sceneStart);
    primaryStage.show();
  }
}
