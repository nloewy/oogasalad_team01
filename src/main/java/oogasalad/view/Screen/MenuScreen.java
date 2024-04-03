package oogasalad.view.Screen;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import oogasalad.view.Controller;

/**
 * Scene allows player to select from list of available games
 * @author Jordan Haytaian
 */
public class MenuScreen extends UIScreen {

  public MenuScreen(List<String> titles, Controller controller){
    root = new Group();
    createScene(titles);
    this.controller = controller;
  }

  private void createScene(List<String> titles){
    createTitle();
    createMenu(titles);
    scene = new Scene(root, sceneWidth, sceneHeight);
  }

  private void createTitle(){
    double titleX = sceneWidth / 2 - 350;
    double titleY = sceneHeight / 5;
    Text title = new Text(titleX, titleY, "Game Options");

    setToThemeFont(title, 100);
    title.setEffect(createDropShadow());

    root.getChildren().add(title);
  }

  private void createMenu(List<String> titles){
    ObservableList<String> observableList = FXCollections.observableList(titles);
    ListView<String> listView = new ListView<>(observableList);
    listView.setPrefSize(sceneWidth - 800, sceneHeight - 400);
    listView.setLayoutX(400);
    listView.setLayoutY(300);
    addListViewEventHandling(listView);
    root.getChildren().add(listView);
  }

  private void addListViewEventHandling(ListView<String> listView){
    listView.setOnMouseClicked(e -> {
      //TODO: find a way to pass selected option
      controller.startGamePlay();
    });
  }

}
