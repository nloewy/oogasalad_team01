package oogasalad.model.gameengine.command;

import java.util.List;
import oogasalad.model.gameengine.GameEngine;
import oogasalad.model.gameengine.PlayerContainer;

public class NRoundsCompletedCommand extends CheckEndConditionCommand {

  public NRoundsCompletedCommand(List<Double> arguments) {
    super(arguments);
  }

  @Override
  protected boolean evaluateCondition(GameEngine engine, List<Double> arguments) {
    return engine.getRound() > arguments.get(0);
  }

}
