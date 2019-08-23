package dev.entze.sge.engine.game.tournament;

import dev.entze.sge.agent.GameAgent;
import dev.entze.sge.engine.Logger;
import dev.entze.sge.engine.factory.GameFactory;
import dev.entze.sge.game.Game;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public enum TournamentMode {

  ROUND_ROBIN {
    public Tournament<Game<Object, Object>, GameAgent<Game<Object, Object>, Object>, Object> getTournament(
        GameFactory<Game<Object, Object>> gameFactory, int numberOfPlayers,
        String board, List<GameAgent<Game<Object, Object>, Object>> gameAgents,
        long computationTime,
        TimeUnit timeUnit, boolean debug, Logger log, ExecutorService pool) {
      return new RoundRobin<>(gameFactory, numberOfPlayers, board, gameAgents, computationTime,
          timeUnit, debug, log,
          pool);
    }

    @Override
    public int getMinimumPerRound() {
      return 2;
    }

    @Override
    public int getMaximumPerRound() {
      return 2;
    }


  };

  public abstract Tournament<Game<Object, Object>, GameAgent<Game<Object, Object>, Object>, Object> getTournament(
      GameFactory<Game<Object, Object>> gameFactory, int numberOfPlayers,
      String board, List<GameAgent<Game<Object, Object>, Object>> gameAgents,
      long computationTime,
      TimeUnit timeUnit, boolean debug, Logger log, ExecutorService pool);

  public abstract int getMinimumPerRound();

  public abstract int getMaximumPerRound();
}