package oogasalad.view.Controlling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import oogasalad.view.AuthoringScreens.BackgroundSelectionScreen;
import oogasalad.view.AuthoringScreens.ControllableElementSelectionScreen;
import oogasalad.view.AuthoringScreens.ImageType;
import oogasalad.view.AuthoringScreens.InteractionSelectionScreen;
import oogasalad.view.AuthoringScreens.InteractionType;
import oogasalad.view.AuthoringScreens.NonControllableElementSelection;
import oogasalad.view.AuthoringScreens.NonControllableType;

/**
 * Class to handle transitions between authoring environment scenes and communications with backend
 *
 * @author Jordan Haytaian, Doga Ozmen
 */
public class AuthoringController {

  private Stage stage;
  private BuilderDirector builderDirector = new BuilderDirector();

  public AuthoringController() {
    stage = new Stage();
  }

  public void startAuthoring(){
    Map<Shape, NonControllableType> map = new HashMap<>();
    BackgroundSelectionScreen scene = new BackgroundSelectionScreen(
        this, new StackPane(), map, new ArrayList<>());
    stage.setScene(scene.getScene());
    stage.show();
  }

  /**
   * Starts the next selection process by creating the applicable scene and showing it on the stage
   *
   * @param imageType    the selection process that has just finished
   * @param authoringBox holds the user's current game configurations
   */
  public void startNextSelection(ImageType imageType, StackPane authoringBox,
      Map<Shape, NonControllableType> nonControllableMap, List<Shape> controllableList) {
    switch (imageType) {
      case BACKGROUND -> {
        ControllableElementSelectionScreen controllableElementSelectionScreen =
            new ControllableElementSelectionScreen(this, authoringBox, nonControllableMap,
                controllableList);
        stage.setScene(controllableElementSelectionScreen.getScene());
      }
      case CONTROLLABLE_ELEMENT -> {
        NonControllableElementSelection nonControllableElementSelection =
            new NonControllableElementSelection(this, authoringBox, nonControllableMap,
                controllableList);
        stage.setScene(nonControllableElementSelection.getScene());
      }
      case NONCONTROLLABLE_ELEMENT -> {
        InteractionSelectionScreen interactionSelectionScreen
            = new InteractionSelectionScreen(this, authoringBox, nonControllableMap,
            controllableList);
        stage.setScene(interactionSelectionScreen.getScene());
      }
    }
  }

  public void endAuthoring(Map<List<Shape>, Map<InteractionType, Integer>> interactionMap){
    stage.close();
  }

}