# Strategy Game Engine
![Gradle 7.6](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white&label=7.6) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white&label=1.11)

A strategy game engine written in Java.

## Installation

### Gradle

Add the following to your `build.gradle`:

```build.gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation("com.gitlab.StrategyGameEngine:strategy-game-engine:v1.0.4")
}

```
### From Source

```bash
./gradlew jar shadowJar sourcesJar javadocJar
```

This produces four jar files (in `build/libs/`):

One executable

- `sge-1.0.4-exe.jar` (Executable)

And three jars usually used for development

- `sge-1.0.4.jar` (Library)
- `sge-1.0.4-sources.jar` (Sources)
- `sge-1.0.4-javadoc.jar` (Documentation)

## Usage

For an extensive overview see:
```bash
java -jar sge-1.0.4-exe.jar --help
```

If you want to let two agents `agent1.jar` and `agent2.jar` play a game of
`game.jar` against each other run the command:

```bash
java -jar sge-1.0.4-exe.jar match game.jar agent1.jar agent2.jar
```

There is also a [manual](https://github.com/Entze/Strategy-Game-Engine/releases/download/v1.0.4/SGE-MANUAL.pdf) available.

## Games

| Name | Link |
|---|---|
| Risk  | [gitlab.com/StrategyGameEngine/sge-risk](https://gitlab.com/StrategyGameEngine/sge-risk) |
| Dice Poker | [gitlab.com/StrategyGameEngine/sge-dice-poker](https://gitlab.com/StrategyGameEngine/sge-dice-poker) |
| Kalaha | [gitlab.com/StrategyGameEngine/sge-kalaha](https://gitlab.com/StrategyGameEngine/sge-kalaha) |
| Hexapawn | [gitlab.com/StrategyGameEngine/sge-hexapawn](https://gitlab.com/StrategyGameEngine/sge-hexapawn) |

## Agents

| Name | Link |
|---|---|
| AlphaBeta | [gitlab.com/StrategyGameEngine/sge-alphabetaagent](https://gitlab.com/StrategyGameEngine/sge-alphabetaagent) |
| Monte-Carlo-Tree Search | [gitlab.com/StrategyGameEngine/sge-mctsagent](https://gitlab.com/StrategyGameEngine/sge-mctsagent) |
| Random | [gitlab.com/StrategyGameEngine/sge-randomagent](https://gitlab.com/StrategyGameEngine/sge-randomagent) |

## Contributing

Pull requests are welcome. For major changes, please open an issue first to
discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[GNU AGPLv3](https://choosealicense.com/licenses/agpl-3.0/)
