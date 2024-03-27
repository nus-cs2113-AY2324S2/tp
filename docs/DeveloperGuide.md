# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Menu Feature

#### Implementation
The menu feature is designed to allow users to view the relevant command formats by inputting the relevant menu
indexes. This feature is orchestrated by the `MenuCommand` class, which is initialized by the `MenuCommandCreator` 
class. Which is in turn, created by the `Parser` class. Within the `MenuCommand` object, the 
`MenuCommandCreator` would initialize one class-level variable `index` of type `String`. The relevance of
this class-level variable in `MenuCommand` is as follows

| Variable Name | Variable Type | Relevance                                              |
|---------------|---------------|--------------------------------------------------------|
| index         | int           | Refers to the corresponding item in the displayed menu |

For Clarity, the menu items and their corresponding indexes are as follows :

| index | Menu Item               |
|-------|-------------------------|
| Empty | Displays all Menu Items |
| 1     | Manage Expenses         |
| 2     | Manage Savings          |
| 3     | View Expenses           |
| 4     | View Savings            |
| 5     | Find Expenses           |

Upon the call of the `execute()` method in BudgetBuddy using `command.execute()`, the `MenuCommand` object
utilizes methods from the `UI` class to display the relevant menu items. The utilized methods are as follows :

| methodName          | Return Type | Relevance                           |
|---------------------|-------------|-------------------------------------|
| showMenuTitles()    | void        | Prints all Menu Items               |
| showMenuItem(INDEX) | void        | Prints commands associated at INDEX |

The following UML Sequence Diagram shows how the MenuCommandCreator for Menu Commands work, NOTING that the Parser
has already detected that the user input is a menu command and has initialized a MenuCommandCreator object:
![Sequence Diagram for MenuCommandCreator for Menu Command](diagrams/sequenceDiagram-MenuCommandCreator.jpg)

The following UML Sequence Diagram shows how the overall Menu feature works :
![Sequence Diagram for Menu Command](diagrams/sequenceDiagram_MenuFeature.jpg)

Given below is an example usage scenario and how the full Menu feature works :
1. The user types `menu 1`. This input passed from `BudgetBuddy` into `Parser#parseCommands()`.
2. Within the `Parser` , it determines that the input is a menu command from `isMenuCommand()`, and creates a new
`MenuCommandCreator` object.
3. The `Parser` then calls `MenuCommandCreator#createCommand()`
4. The checks for whether the input is valid, in particular whether it is a valid integer, 
along with obtaining the value of `index` is done in `MenuCommandCreator#handleMenuCommand`
5. `MenuCommandCreator` creates a constructor for `MenuCommand` with the parameter `1`, which in turn 
also constructs a new `Ui` object
6. `MenuCommandCreator` returns this created `MenuCommand` to `Parser`, which is then returned to `BudgetBuddy`
7. `BudgetBuddy` then calls `MenuCommand#execute()`
8. `execute()` then calls `Ui#showMenuItem(1)`
9. `showMenuItem()` in `Ui` then prints all commands for `case 1` which is for `Manage Expenses`

## Product scope
### Target user profile

{Describe the target user profile}

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
