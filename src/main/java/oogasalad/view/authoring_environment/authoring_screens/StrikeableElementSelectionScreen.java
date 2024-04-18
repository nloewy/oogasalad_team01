package oogasalad.view.authoring_environment.authoring_screens;

import java.util.List;
import java.util.Map;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import oogasalad.view.controller.AuthoringController;

/**
 * Class to represent the screen in which user places and customizes ball objects in their unique
 * game
 *
 * @author Jordan Haytaian, Doga Ozmen
 */
public class StrikeableElementSelectionScreen extends AuthoringScreen {

  private final List<Shape> strikeableList;

  public StrikeableElementSelectionScreen(AuthoringController controller,
      StackPane authoringBox, Map<Shape, List<Double>> posMap,
      Map<Shape, NonStrikeableType> nonStrikeableMap,
      List<Shape> strikeableList, Map<Shape, String> imageMap) {
    super(controller, authoringBox, posMap, nonStrikeableMap, strikeableList, imageMap);
    this.strikeableList = strikeableList;
  }

  /**
   * Creates the scene including the previously selected background
   */
  void createScene() {
    root = new AnchorPane();
    createTitle("Strikeable Selection");
    root.getChildren().add(authoringBox);
    addElements();
    createSizeAndAngleSliders();
    createShapeDisplayOptionBox();
    createDraggableShapeTemplates();
    createTransitionButton("Next");
    scene = new Scene(root, screenWidth, screenHeight);
  }

  /**
   * Called when user presses submit, triggers passing of info to back end
   */
  void endSelection() {
    addNewSelectionsToAuthoringBox();
    for (Shape shape : selectableShapes) {
      Bounds shapeBounds = shape.getBoundsInParent();
      Bounds authoringBoxBounds = authoringBox.getBoundsInParent();

      if (authoringBoxBounds.contains(shapeBounds)) {
        strikeableList.add(shape);
      }
    }
    controller.startNextSelection(ImageType.STRIKEABLE_ELEMENT, authoringBox, posMap,
        nonStrikeableMap,
        strikeableList, imageMap);
  }

  /**
   * Returns ball image type indicating that user is placing ball objects
   *
   * @return enum to represent ball image type
   */
  ImageType getImageType() {
    return ImageType.STRIKEABLE_ELEMENT;
  }

  /**
   * Updates ball options for currently selected ball
   */
  void updateOptionSelections() {//TODO: Can this be empty/ are there ball options?
  }
}