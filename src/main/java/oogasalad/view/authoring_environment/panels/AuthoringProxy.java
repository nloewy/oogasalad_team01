package oogasalad.view.authoring_environment.panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.shape.Shape;
import oogasalad.view.api.exception.MissingInteractionException;
import oogasalad.view.api.exception.MissingNonControllableTypeException;
import oogasalad.view.authoring_environment.Coordinate;
import oogasalad.view.authoring_environment.NewAuthoringController;
import oogasalad.view.authoring_environment.authoring_screens.InteractionType;

public class AuthoringProxy {
  private final Map<String, Map<String, List<Double>>> conditionsCommands = new HashMap<>();
  private final Map<String, String> policies = new HashMap<>();
  private final Map<List<Shape>, Map<InteractionType, List<Double>>> interactionMap = new HashMap<>();
  private final Map<Shape, GameObjectAttributesContainer> gameObjectMap = new HashMap<>();
  private final Map<Integer, List<Integer>> playersMap = new HashMap<>();
  //TODO: transfer imageMap functionality to gameObjectMap
  private final Map<Shape, String> imageMap = new HashMap<>();
  //TODO: transfer shapePosition functionality to gameObjectMap
  private final Map<Shape, Coordinate> shapePositionMap = new HashMap<>();
  // TODO: make sure that this is actually following the Proxy pattern
  private String gameName;
  private String currentScreenTitle;
  private NewAuthoringController authoringController;
  private int numPlayers;

  public AuthoringProxy() {
    initializeNumPlayers();
  }

  public void addShapeInteraction(List<Shape> shapes,
      Map<InteractionType, List<Double>> interaction) {
    interactionMap.put(shapes, interaction);
  }

  public Map<Integer, List<Integer>> getPlayers () {
    return playersMap;
  }

  public void addNoParamPolicies(String type, String command){
    policies.put(type, command);
  }

  public void addConditionsCommandsWithParam(String type, String commandName, List<Double> params){
    if (!conditionsCommands.containsKey(type)){
      conditionsCommands.put(type, new HashMap<>());
    }
    conditionsCommands.get(type).put(commandName,params);
  }

  public Map<Shape, GameObjectAttributesContainer> getGameObjectMap() {
    return gameObjectMap;
  }
  public void setGameObject(Shape shape, GameObjectAttributesContainer gameObjectAttributesContainer) {
    this.gameObjectMap.put(shape, gameObjectAttributesContainer);
  }

  public void addImage(Shape shape, String relativePath) {
    imageMap.put(shape, relativePath);
  }

  public void setShapePosition(Shape shape, Coordinate position) {
    shapePositionMap.put(shape, position);
  }

  public void completeAuthoring()
      throws MissingInteractionException, MissingNonControllableTypeException {
    authoringController.endAuthoring(gameName, interactionMap, gameObjectMap, playersMap);
  }

  public void updateScreen() {
    authoringController.updateAuthoringScreen();
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }

  public String getCurrentScreenTitle() {
    return currentScreenTitle;
  }

  public void setCurrentScreenTitle(String currentScreenTitle) {
    this.currentScreenTitle = currentScreenTitle;
  }

  public NewAuthoringController getAuthoringController() {
    return authoringController;
  }

  public void setAuthoringController(
      NewAuthoringController authoringController) {
    this.authoringController = authoringController;
  }

  public Map<List<Shape>, Map<InteractionType, List<Double>>> getInteractionMap() {
    return interactionMap;
  }

  public Map<Shape, String> getImageMap() {
    return imageMap;
  }

  public Map<Shape, Coordinate> getShapePositionMap() {
    return shapePositionMap;
  }

  public int getNumPlayers() {
    return numPlayers;
  }

  public void increaseNumPlayers() {
    numPlayers++;
  }

  public void decreaseNumPlayers() {
    numPlayers--;
  }

  public void initializeNumPlayers() {
    numPlayers = 1;
  }
}
