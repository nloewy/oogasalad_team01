package oogasalad.model.gameparser;

import oogasalad.model.api.data.GameObjectProperties;
import oogasalad.model.gameengine.gameobject.Controllable;
import oogasalad.model.gameengine.gameobject.DefaultControllable;
import oogasalad.model.gameengine.gameobject.DefaultStrikeable;
import oogasalad.model.gameengine.gameobject.GameObject;
import oogasalad.model.gameengine.gameobject.scoreable.DefaultScoreable;

public class CollidableFactory {

  public static GameObject createCollidable(GameObjectProperties co) {

    GameObject c = new GameObject(
        co.collidableId(),
        co.mass(),
        co.position().xPosition(),
        co.position().yPosition(),
        co.properties().contains("visible") && !co.properties().contains("strikeable"),
        co.staticFriction(),
        co.kineticFriction(),
        co.inclineAngle(),
        co.dimension().xDimension(),
        co.dimension().yDimension(),
        co.shape(),
        co.inelastic(),
        co.phaser());

    if (co.properties().contains("strikeable")) {
      c.addStrikeable(new DefaultStrikeable(c));
      c.addScoreable(new DefaultScoreable(c));
    }
    if(co.properties().contains("controllable")) {
      c.addControllable(new DefaultControllable(c));
      c.getControllable().ifPresent(Controllable::allowMoveX);
      c.getControllable().ifPresent(Controllable::allowMoveY);
    }
    return c;
  }
}
