package dev.entze.sge.engine.factory;

import dev.entze.sge.engine.Logger;
import dev.entze.sge.game.Game;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GameFactory implements Factory<Game<Object, Object>> {

  private final Constructor<Game<Object, Object>> gameConstructor;
  private final Logger log;


  private final int minimumNumberOfPlayers;
  private final int maximumNumberOfPlayers;


  public GameFactory(Constructor<Game<Object, Object>> gameConstructor, int minimumNumberOfPlayers,
      int maximumNumberOfPlayers, Logger log) {
    this.gameConstructor = gameConstructor;
    this.minimumNumberOfPlayers = minimumNumberOfPlayers;
    this.maximumNumberOfPlayers = maximumNumberOfPlayers;
    this.log = log;
  }

  @Override
  public Game<Object, Object> newInstance(Object... initargs) {
    try {
      return gameConstructor.newInstance(initargs);
    } catch (InstantiationException e) {
      log.error_();
      log.error("Could not instantiate new element with constructor of game");
      log.printStackTrace(e);
    } catch (IllegalAccessException e) {
      log.error_();
      log.error("Could not access constructor of game");
      log.printStackTrace(e);
    } catch (InvocationTargetException e) {
      log.error_();
      log.error("Could not invoke constructor of game");
      log.printStackTrace(e);
    }
    throw new IllegalStateException("GameFactory is faulty");

  }

  public int getMinimumNumberOfPlayers() {
    return minimumNumberOfPlayers;
  }

  public int getMaximumNumberOfPlayers() {
    return maximumNumberOfPlayers;
  }

}
