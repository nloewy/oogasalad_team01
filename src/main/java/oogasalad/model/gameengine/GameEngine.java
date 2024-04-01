package oogasalad.model.gameengine;

import java.util.List;
import java.util.Map;
import oogasalad.Pair;
import oogasalad.model.api.ExternalGameEngine;
import oogasalad.model.api.GameRecord;
import oogasalad.model.api.PlayerRecord;
import oogasalad.model.gameengine.collidable.Collidable;
import oogasalad.model.gameengine.collidable.CollidableContainer;
import oogasalad.model.gameengine.command.Command;
import oogasalad.model.gameengine.logic.PlayerContainer;
import oogasalad.model.gameengine.logic.RulesRecord;

/**
 * @author Noah Loewy
 */
public class GameEngine implements ExternalGameEngine {

  private PlayerContainer playerContainer;
  private RulesRecord rules;
  private CollidableContainer collidables;
  private Map<Pair, Command> collisionHandlers;
  private int round;
  private int turn;
  private boolean gameOver;

  public GameEngine(int id) {
    GameLoader loader = new GameLoader(id);
    playerContainer = loader.getPlayerManager();
    rules = loader.getRules();
    collidables = loader.getCollidables();
    collisionHandlers = rules.collisionHandlers();
    round = 1;
    turn = 1;
    gameOver = false;
  }

  /**
   * Starts the current game
   */
  @Override
  public void start() {

  }

  /**
   * Pauses the current game.
   */

  @Override
  public void pause() {

  }

  /**
   * Resumes the paused game
   */

  @Override
  public void resume() {

  }

  /**
   * Provides view with updated GameState as immutable record after each frame
   *
   * @return GameRecord object representing the current Collidables, Scores, etc
   */
  @Override
  public GameRecord update(double dt) {
    if(collidables.checkStatic()) {
      return null;
    }
    collidables.update(dt);
    return new GameRecord(collidables.getCollidableRecords(), playerContainer.getPlayerRecords(),
        round, turn, gameOver);
  }

  @Override
  public void handleCollisions(List<Pair> collisions, double dt) {
    for(Pair collision : collisions) {
      Collidable collidable1 = collidables.getCollidable(collision.getFirst());
      Collidable collidable2 = collidables.getCollidable(collision.getSecond());
      collidable1.onCollision(collidable2, dt);
      collidable2.onCollision(collidable1, dt);
      Command cmd = collisionHandlers.get(collision);
      cmd.execute(this, List.of((double) collision.getFirst(), (double) collision.getSecond()));
    }
  }

  /**
   * Applies a velocity to the entity with the provided ID.
   *
   * @param magnitude The magnitude of the force to apply.
   * @param direction The direction of the force to apply.
   * @param id        The ID of the entity to apply the force to.
   */
  @Override
  public void applyInitialVelocity(double magnitude, double direction, int id) {
    Collidable collidable = collidables.getCollidable(id);
    collidable.applyInitialVelocity(magnitude, direction);
  }

  /**
   * Resets the game to its initial state.
   */
  @Override
  public void reset() {

  }

  public void advanceRound() {
    round++;
    //other stuff
  }

  public void advanceTurn() {
    turn = (turn + 1) % playerContainer.getPlayerRecords().size();
  }

  public int getRound() {
    return round;
  }

  public int getTurn() {
    return turn;
  }

  public RulesRecord getRules() {
    return rules;
  }

  public List<PlayerRecord> getImmutablePlayers() {
    return playerContainer.getPlayerRecords();
  }

  public void endGame() {
    gameOver = true;
  }
}
