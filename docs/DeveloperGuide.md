# Developer Guide

## Table of Contents

* [Design](#design)
  * [Architecture](#architecture)
    * [Main components](#main-components)
    * [Component interaction](#component-interaction)
  * [UI components](#ui-components)
  * [EconoCraftLogic components](#econocraftlogic-components)
  * [MiniGame components](#miniGame-components)
* [Implementation](#implementation)
  * [MiniGame - Typing Game](#miniGame---typing-Game)
  * [MiniGame - Tic Tac Toe](#miniGame---tic-tac-toe)
  * [MiniGame - True or False](#miniGame---true-or-false)
* [Product scope](#product-scope)
  * [Target user playerProfile](#target-user-playerprofile)
  * [Value proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

### Architecture

![Architecture.png](UML%20diagram%2FArchitecture.png)

The **Architecture diagram** above showcases the high-level design of the EconoCraft Pro application. 

The following section would give a brief overview of the different components of the application and their interactions
with each other.

#### Main components:

`Main` (consisting of class `EconoCraft` and `EconoCraftLogic`) 
is in charge of the game initialization and starting the main game loop.

* At game initialization, it reads the necessary information from prompted user input and develops the player profile.
* When game starts, it initializes the game logic and starts the main game loop.

The functionality of the game is divided into different components, each responsible for a different aspect of the game.

* `PlayerProfile`: Represents the player's profile and is responsible for storing and updating the player's information.
* `Parser`: Responsible for parsing the user input.
* `ResponseManager`: Responsible for generating the response to the user input.
* `EconoCraftLogic`: Responsible for executing user command and updating the game state.
* `MiniGame`: Responsible for handling the mini-games that the player can play to gain rewards.
* `CommandFactory`: Responsible for using the parsed user input to produce executable commands.

#### Component interaction:

The *Sequence Diagram* below showcases the interaction between the different components of the EconoCraft Pro 
application when a user inputs a command `work`.

For `CommandFactory` and `Minigame`,
* Each defines its API for creating commands and mini-games respectively, where
  `Command` is the API for `CommandFactory` and `MiniGame` is the API for `MiniGame`.
* Implements its functionality with concrete classes such as `WorkCommand` and `TypingGame`.

The sections below would give more details of each component.

## UI components

UI consists of the following components:
* `Parser`
* `ResponseManager`
* `CommandType`

![UI.png](UML%20diagram%2FUI.png)

The **UI components**,
* `Parser` parses the user input for `CommandFactory` to produce `Command`
* `ResponseManager` generate the response to the user according to the *command execution* and *game logic*.

## EconoCraftLogic components

Here is the partial class diagram of the `EconoCraftLogic` component:

![Logic.png](UML%20diagram%2FLogic.png)

> [!NOTE]
> - The `XYZ` in `XYZCommand` represents the exact command e.g., `WorkCommand`, `RestCommand`, `ExerciseCommand`.
> - The `XYZ` in `XYZMiniGame` represents the exact mini-game e.g., `TypingGame`, `TicTacToe`, etc.


Here is the sequence diagram of the `EconoCraftLogic` executing the command:

![Work.png](UML%20diagram%2FWork.png)

The `EconoCraftLogic` mechanism:

1. `EconoCraftLogic` receives the user input string and pass it into `CommandFactory`.
2. `CommandFactory` would use `Parser` to parse the user input string and produce a `Command`.
3. `EconoCraftLogic` would execute the `Command` which will:
   * update the player profile accordingly.
   * use `ResponseManager` to generate the response to the user.

## MiniGame components

Here is the partial class diagram of the `MiniGame` component:

![MiniGame.png](UML%20diagram%2FMiniGame.png)

The `MiniGame` mechanism:
1. For command `work`, `rest`, and `exercise`, they would have their respective mini-games.
2. When these commands are generated and executed in `EconoCraftLogic`, the respective mini-game would be played.
3. The command would then update the player profile according to the mini-game result.

# Implementation

## MiniGame - Typing Game

The implementation of the Typing Game is as follows:

1. The game can be invoked by the `WorkCommand` class when the user inputs the `work` command.
2. It makes use of the `ResponseManager` to generate the instructions of the game to the user. 
3. The user would be prompted to type the given text as fast as they can.
4. This game made use of the `CompletableFuture` class to create separate thread which handles the countdown timer and user input at the same time.
5. When the user finishes typing or the time limit is reached, the game would calculate the user's typing speed and accuracy and reward the user accordingly. 
6. Finally, the `WorkCommand` would update the player profile with the reward earned according to the user's performance in the game.

## MiniGame - Tic Tac Toe

{Describe the implementation of the Tic Tac Toe}

## MiniGame - True or False

{Describe the implementation of the True or False}

## Product scope
### Target user playerProfile

{Describe the target user playerProfile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
