package oogasalad.view.scene_management.scene_element.scene_element_handler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import oogasalad.view.api.enums.SceneElementEvent;
import oogasalad.view.controller.GameController;


/**
 * LoadGameEventHandler handles events related to loading games in the application. It creates event
 * handlers for nodes based on the specified events and provides appropriate actions for starting
 * new or saved games.
 */
public class LoadGameEventHandler {

  private final GameController gameController;
  private Map<SceneElementEvent, Callable> eventMap;

  /**
   * Constructs a LoadGameEventHandler with the specified GameController.
   *
   * @param gameController The GameController used to handle game-related events.
   */
  public LoadGameEventHandler(GameController gameController) {
    this.gameController = gameController;
    createEventMap();
  }

  /**
   * Creates an event handler for the specified node and event type.
   *
   * @param node  The node to which the event handler will be attached.
   * @param event The event type as a string.
   */
  public void createElementHandler(Node node, String event) {
    Callable callable = eventMap.get(SceneElementEvent.valueOf(event));
    createStartGameHandler(node, callable);
  }

  private void createEventMap() {
    eventMap = new HashMap<>();
    eventMap.put(SceneElementEvent.START_NEW_GAME, gameController::getNewGameTitles);
    eventMap.put(SceneElementEvent.START_SAVED_GAME, gameController::getSavedGameTitles);
  }

  private void createStartGameHandler(Node node, Callable callable) {
    try {
      ListView<String> gameList = (ListView<String>) node;
      ObservableList<String> items = (ObservableList<String>) callable.call();
      gameList.setItems(items);
      node.setOnMouseClicked(e -> {
        String game = gameList.getSelectionModel().getSelectedItem();
        if (game != null) {
          gameController.startGamePlay(game);
        }
      });
    } catch (Exception e) {
      //TODO: error handling
    }
  }
}