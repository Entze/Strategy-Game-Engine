package dev.entze.sge.engine.cli;

import dev.entze.sge.agent.GameAgent;
import dev.entze.sge.engine.Match;
import dev.entze.sge.game.Game;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

@Command(name = "match", aliases = {
    "m"}, description = "Let agents play against each other in a single match.", customSynopsis = {
    "sge [OPTION]... FILE... [AGENT]...",
    "   or: sge [OPTION]... [AGENT]... FILE...",
    "   or: sge [OPTION]... [FILE]... [DIRECTORY]... [AGENT]...",
    "   or: sge [OPTION]... [AGENT]... [FILE]... [DIRECTORY]...",
    "   or: sge [OPTION]... -f FILE... [ARGUMENTS]...",
    "   or: sge [OPTION]... -d DIRECTORY... [ARGUMENTS]...",
    "   or: sge [OPTION]... -a AGENT... [ARGUMENTS]...",
})
public class MatchCommand implements Runnable {

  @Option(names = {"-c",
      "--computation-time"}, defaultValue = "60", description = "Amount of computational time given for each action")
  long computationTime = 60;
  @Option(names = {"-u",
      "--time-unit"}, defaultValue = "SECONDS", description = "Time unit in which -c is. Valid values: ${COMPLETION-CANDIDATES}")
  TimeUnit timeUnit = TimeUnit.SECONDS;
  @ParentCommand
  private SgeCommand sge;
  @Option(names = {"-h", "--help"}, usageHelp = true, description = "print this message")
  private boolean helpRequested = false;
  @Option(names = {"-q",
      "--quiet"}, description = "Found once: Log only warnings. Twice: only errors. Thrice: no output")
  private boolean[] quiet = new boolean[0];
  @Option(names = {"-v",
      "--verbose"}, description = "Found once: Log debug information. Twice: with trace information")
  private boolean[] verbose = new boolean[0];
  @Option(names = {"-p",
      "--number-of-players"}, arity = "1", paramLabel = "N", description = "Number of players. By default the minimum required to play")
  private int numberOfPlayers = (-1);

  @Option(names = {"-f",
      "--file"}, arity = "1..*", paramLabel = "FILE", description = "File(s) of game and agents.")
  private List<File> files = new ArrayList<>();

  @Option(names = {"-d",
      "--directory"}, arity = "1..*", paramLabel = "DIRECTORY", description = "Directory(s) of game and agents. Note that all subdirectories will be considered.")
  private List<File> directories = new ArrayList<>();

  @Option(names = {"-a",
      "--agent"}, arity = "1..*", paramLabel = "AGENT", description = "Configuration of agents.")
  private List<String> agentConfiguration = new ArrayList<>();

  @Parameters(index = "0", arity = "0..*", description = {"Other valid synopsis"})
  private List<String> arguments = new ArrayList<>();


  @Override
  public void run() {
    if (verbose.length != 0 || quiet.length != 0) {
      sge.log.setLogLevel(quiet.length - verbose.length);
    }

    sge.determineArguments(arguments, files, directories, agentConfiguration);
    sge.loadDirectories(files, directories);

    sge.log.tra("Files: ");

    for (File file : files) {
      sge.log.tra_(file.getPath() + " ");
    }

    sge.log.trace_();

    sge.loadFiles(files);
    sge.log.debug("Successfully loaded all files.");

    if (numberOfPlayers < 0) {
      numberOfPlayers = sge.gameFactory.getMinimumNumberOfPlayers();
    }

    sge.fillAgentList(agentConfiguration, numberOfPlayers);

    sge.log.deb("Configuration: ");
    for (String s : agentConfiguration) {
      sge.log.deb_(s + " ");
    }
    sge.log.debug_();

    List<GameAgent<Game<?, ?>, ?>> agentList = sge
        .createAgentListFromConfiguration(numberOfPlayers, agentConfiguration);

    Match<Game<?, ?>, GameAgent<Game<?, ?>, ?>, ?> match = new Match(
        sge.gameFactory.newInstance(numberOfPlayers), sge.gameASCIIVisualiser, agentList, computationTime,
        timeUnit, sge.log, sge.pool);

    match.call();


  }
}