import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class guiGrid extends Application {
  private Map map1 = new Map();
  private int rowNum = 14;
  private int colNum = 18;
  private int roomNumbers;
  private Map square = new Map();

  public void makeGUI(FloorPlans aFloorPlan, GridPane aGridPane){
    for (int row = 0; row < rowNum; row++){
      for(int col = 0; col < colNum; col++){
        Rectangle rect = new Rectangle();
        if (aMap.grid[row][col] == 0){
          rect.setFill(Color.BLACK);
        } else if (aMap.grid[row][col] == 1){
          rect.setFill(Color.TRANSPARENT);
        } else if (aMap.grid[row][col] == 9){
          rect.setFill(Color.GREY);
        } else{
          rect.setFill(Color.LIGHTBLUE);
        }
        int roomNumber = 0;
        roomNumber = square.getRoomNumber(row,col);
        Label rooms = new Label("");
        //conditional to add room numbers to the grid map.
        if (roomNumber != 0 && roomNumber != 1 && roomNumber != 9){
          rooms.setFont(Font.font("Times New Roman", FontWeight.BOLD, 10));
          rooms.setText("" + roomNumber);
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

    Scene scene = new Scene(borderPane,700,700);
    makeGUI(map1,gridPane);
    primaryStage.setTitle("GUI");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
