package oogasalad.model.gamebuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import oogasalad.model.api.DirectorInterface;
import oogasalad.model.api.data.CollidableObject;
import oogasalad.model.api.data.GameData;
import oogasalad.model.api.data.ParserPlayer;
import oogasalad.model.api.data.Rules;
import oogasalad.model.api.data.Variables;
import oogasalad.model.gameengine.Player;

public class BuilderDirector implements DirectorInterface {

  //change game data record into an actual class
//
//  @JsonProperty("gameName") String gameName;
//  @JsonProperty("collidable_objects") List<CollidableObject> collidableObjects;
//  List<ParserPlayer> players;
//  List<Variables> variables;
//  Rules rules;

//  JSONObject jsonData = new JSONObject();

  @Override
  public void constructCollidableObjects(GameData gameData, List<Record> fieldData) {
    CollidablesBuilder collidablesBuilder = new CollidablesBuilder();
    collidablesBuilder.buildGameField(gameData, fieldData);
  }

  @Override
  public void constructPlayers(GameData gameData, List<Record> fieldData) {
    PlayersBuilder playersBuilder = new PlayersBuilder();
    playersBuilder.buildGameField(gameData, fieldData);
  }

  @Override
  public void constructVaraibles(GameData gameData, List<Record> fieldData) {
    VariablesBuilder variablesBuilder = new VariablesBuilder();
    variablesBuilder.buildGameField(gameData, fieldData);
  }

  @Override
  public void constructRules(GameData gameData, List<Record> fieldData) {
    RulesBuilder rulesBuilder = new RulesBuilder();
    rulesBuilder.buildGameField(gameData, fieldData);
  }


  public void writeGame(GameData gameData, String gameName, String filePath, String fileName) throws IOException {
    gameData.setGameName(gameName);
    ObjectMapper mapper = new ObjectMapper();
    mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath+fileName), gameData);
  }

}
