# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Architecture

### UI Component

# UserCommand feature

User can type command to do things on the Map. 

If the user type the command, `parseCommand` function will be called. After that, the original input will be
analyzed by `analyseCommand` function to see if it matches a kind of `CommandType`. Then, we will return new
Command back to the main based on the command type. The Final step is to call the `execute` function. If the command
is the type of `fightCommand`, we will call the execute function with one parameter `Scanner`. For all other
conditions, we will call the execute function with no parameter.

![dummy test](/picture/Command.svg)

### Map Component

The API of this component is defined in AMap.java.

Each map instance consists of a `currentMap` which stores a 2-dimensional array of characters which represents the 
printed map for the player. All maps will come with a given `height` and `width`, all of these attributes are inherited
from the AMap abstract class. Currently, the `FirstMap` and `BattleInterface` classes
extend AMap. `FirstMap` is the first map displayed upon entering the game and it displays the position of the player. 
The `BattleInterface` is the map displayed when the player interacts with an `interactable`. 


{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
|---------|----------|---------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
