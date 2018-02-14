import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

public class guiGrid extends Application {
  private Map map1 = new Map();
  private int rowNum = 14;
  private int colNum = 18;

  public void makeGUI(Map aMap, GridPane aGridPane){
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
          rect.setFill(Color.BLUE);
        }
        rect.setStroke(Color.BLACK);
        rect.setWidth(30);
        rect.setHeight(30);
        GridPane.setRowIndex(rect, row);
        GridPane.setColumnIndex(rect, col);
        aGridPane.getChildren().addAll(rect);
        //https://stackoverflow.com/questions/35367060/gridpane-of-squares-in-javafx
      }
    }
  }
  public void start(Stage primaryStage){
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    Scene scene = new Scene(gridPane,700,700);
    makeGUI(map1,gridPane);
    primaryStage.setTitle("GUI");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
