package oogasalad.model.api;

public record GameObjectRecord(int id, double mass, double x, double y, double velocityX,
                               double velocityY, boolean visible, double staticMu, double kineticMu,
                               double width,
                               double height) {

}