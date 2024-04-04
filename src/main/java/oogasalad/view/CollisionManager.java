package oogasalad.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.shape.Shape;
import oogasalad.Pair;
import oogasalad.view.VisualElements.CompositeElement;
import oogasalad.view.VisualElements.GameElement;

/**
 * Class to maintain intersections and detect which collisions are active
 *
 * @author Jordan Haytaian
 */
public class CollisionManager {

  private Shape[][] intersections;
  private CompositeElement compositeElement;

  public CollisionManager() {}

  /**
   * Traverses intersection matrix and to create list of ids that are currently intersecting
   *
   * @return A list of the ID pairs that are intersecting
   */
  public List<Pair> getIntersections() {
    List<Pair> intersectionList = new ArrayList<>();
    for (Integer i : compositeElement.idList()){
      for (Integer j : compositeElement.idList()){
        if (i<j) {
          if (compositeElement.getNode(i).getBoundsInParent().intersects(
              compositeElement.getNode(j).getBoundsInParent())) {
            intersectionList.add(new Pair(i,j));
          }
        }
      }
    }
    return intersectionList;
  }

//  public Map<Pair, String> getIntersectionsMap(){
//    List<Pair> intersectionList = getIntersections();
//    Map<Pair, String> collisionType = new HashMap<>();
//    for (Pair pair : intersectionList){
//      collisionType.put(pair, getCollisionType(pair));
//    }
//    return collisionType;
//  }

  /**
   * Assigns the composite element to the given composite element
   * @param newCompositeElement the new composite element
   */
  public void setNewCompositeElement(CompositeElement newCompositeElement) {
    this.compositeElement = newCompositeElement;
  }

//  private void createIntersectionMatrix() {
//    int maxId = -1;
//    for (Integer currId : elementMap.keySet()) {
//      if (currId > maxId) {
//        maxId = currId;
//      }
//    }
//
//    intersections = new Shape[maxId][maxId];
//
//    for (Map.Entry<Integer, Node> currEntry : elementMap.entrySet()) {
//      for (Map.Entry<Integer, Node> interEntry : elementMap.entrySet()) {
//
//        int currId = currEntry.getKey();
//        int interId = interEntry.getKey();
//
//        if (currId != interId) {
//          Shape currShape = (Shape) currEntry.getValue();
//          Shape interShape = (Shape) interEntry.getValue();
//          Shape currIntersection = Shape.intersect(currShape, interShape);
//
//          intersections[currId][interId] = currIntersection;
//        }
//      }
//    }
//  }


//  public void triggerCollisions(){
//    Map<Integer,Map<Integer, Command>> collisionMap = null;
//    for (Integer i : collisionMap.keySet()){
//      for (Integer j : collisionMap.get(i).keySet()){
//        if (compositeElement.getNode(i).intersects(compositeElement.getNode(j).getBoundsInLocal())){
//          collisionMap.get(i).get(j).execute(engine);
//        }
//      }
//    }
//  }
}
