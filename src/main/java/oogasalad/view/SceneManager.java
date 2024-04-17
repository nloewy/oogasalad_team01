package oogasalad.view;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import oogasalad.Main;
import oogasalad.model.api.GameRecord;
import oogasalad.model.api.PlayerRecord;
import oogasalad.view.Controlling.GameController;
import oogasalad.view.GameScreens.GameScreen;
import oogasalad.view.VisualElements.CompositeElement;

/**
 * Manages different screens (scenes) within the game, such as the title screen, menu screen, game
 * screen, and transition screen. It updates and transitions between screens based on game state and
 * player interactions.
 *
 * @author Doga Ozmen
 */
public class SceneManager {

  public final static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
  public final static double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
  private final Pane root;
  private final Scene scene;
  private final SceneElementParser sceneElementParser;
  private final ElementStyler elementStyler;
  private CompositeElement compositeElement;
  private GameScreen gameScreen;
  private int currentRound = 1;
  private final String titleSceneElementsPath = "data/scene_properties/titleSceneElements.xml";


  public SceneManager() {
    root = new Pane();
    scene = new Scene(root);
    sceneElementParser = new SceneElementParser(SCREEN_WIDTH, SCREEN_HEIGHT);
    elementStyler = new ElementStyler(root);
  }

  public void createScene(SceneType sceneType) {
    switch (sceneType) {
      case TITLE -> {
        createSceneElementsAndUpdateRoot(titleSceneElementsPath);
      }
      case MENU -> {
        createSceneElementsAndUpdateRoot(menuSceneElementsPath);
      }
      case GAME -> {
      }
      case TRANSITION -> {
      }
      case PAUSE -> {
      }
    }

  }

  public void createSceneElementsAndUpdateRoot(String filePath) {
    try {
      Map<Node, String> sceneElements = sceneElementParser.createElementsFromFile(filePath);
      root.getChildren().clear();
      root.getChildren().addAll(sceneElements.keySet());
      elementStyler.style(sceneElements);
    } catch (Exception e) {
      //TODO: Exception Handling
    }
  }


  public Scene getScene() {
    return scene;
  }

  public void update(GameRecord gameRecord) {
    compositeElement.update(gameRecord.collidables());
    Map<Integer, Double> scoreMap = new TreeMap<>();
    for (PlayerRecord p : gameRecord.players()) {
      scoreMap.put(p.playerId(), p.score());
    }
    updateScoreTurnBoard(scoreMap, gameRecord.turn(), gameRecord.round());
    checkEndRound(gameRecord);
  }

  public void makeGameScreen(GameController controller, CompositeElement compositeElement) {
    this.compositeElement = compositeElement;
    gameScreen = new GameScreen(controller, compositeElement);
    scene.setRoot(gameScreen.getRoot());
    gameScreen.initiateListening(scene);
  }

  public void enableHitting() {
    gameScreen.enableHitting();
  }


  public void checkEndRound(GameRecord gameRecord) {
    if (gameRecord.round() != currentRound) {
      currentRound = gameRecord.round();
    }
    if (gameRecord.gameOver()) {
      gameScreen.endRound(true);
    }
  }

  public void updateScoreTurnBoard(Map<Integer, Double> scoreMap, int turn, int round) {
    gameScreen.updateScoreBoard(scoreMap);
    gameScreen.updateTurnBoard(turn, round);
  }
}
