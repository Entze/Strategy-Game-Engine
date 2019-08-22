package dev.entze.sge.engine.game;

import dev.entze.sge.agent.GameAgent;
import dev.entze.sge.game.Game;
import dev.entze.sge.util.Util;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MatchResult<G extends Game<?, ?>, E extends GameAgent<G, ?>> {

  private final List<E> gameAgents;
  private final long duration;
  private final double[] result;
  private String string = null;

  public MatchResult(Collection<E> gameAgents, long startTime, long endTime, double[] result) {
    this(gameAgents, endTime - startTime, result);
  }

  public MatchResult(Collection<E> gameAgents, long duration, double[] result) {
    this.gameAgents = List.copyOf(gameAgents);
    this.duration = duration;
    this.result = result;
  }

  public List<E> getGameAgents() {
    return gameAgents;
  }

  public long getDuration() {
    return duration;
  }

  public double[] getResult() {
    return result;
  }


  @Override
  public String toString() {
    if (string != null) {
      return string;
    }
    StringBuilder stringBuilder = new StringBuilder();

    List<String> gameAgentNames = gameAgents.stream().map(Objects::toString)
        .collect(Collectors.toList());
    List<Integer> gameAgentWidths = gameAgentNames.stream().map(String::length)
        .collect(Collectors.toList());

    appendSeperationLine(stringBuilder, gameAgentWidths);
    stringBuilder.append('|').append(' ').append(String.join(" | ", gameAgentNames)).append(' ')
        .append('|')
        .append('\n');
    appendSeperationLine(stringBuilder, gameAgentWidths).append('|');
    for (int i = 0; i < gameAgentWidths.size(); i++) {
      int width = gameAgentWidths.get(i) + 2;
      Util.appendWithBlankBuffer(stringBuilder, result[i], width).append('|');
    }
    stringBuilder.append('\n');
    appendSeperationLine(stringBuilder, gameAgentWidths).deleteCharAt(stringBuilder.length() - 1);
    string = stringBuilder.toString();
    return string;
  }

  private static StringBuilder appendSeperationLine(StringBuilder stringBuilder,
      List<Integer> widths) {
    stringBuilder.append('|');
    for (Integer width : widths) {
      Util.appendNTimes(stringBuilder, '-', width + 2).append('+');
    }
    stringBuilder.deleteCharAt(stringBuilder.length() - 1).append('|').append('\n');
    return stringBuilder;
  }

}
