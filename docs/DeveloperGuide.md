# Developer Guide

## Table of Contents

* [Acknowledgements](#acknowledgements)
* [Setting up, getting started](#setting-up-getting-started)
* [Design](#design)
  * [Data Component](#data-component)
* [Implementation](#implementation)
* [Logging](#logging)
* [Product scope](#product-scope)
  * [Target user profile](#target-user-profile)
  * [Value proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Instructions for manual testing](#instructions-for-manual-testing)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Setting up, getting started

## Design

### Data Component

![DataComponent](images/DataComponent.png)

API: [`ItemList.java`](https://github.com/AY2324S2-CS2113T-T09-2/tp/blob/master/src/main/java/seedu/binbash/ItemList.java)

The `Data` component is primarily composed of an `ItemList` object that stores different types of `Item`.

`Item` has different types, such as `RetailItem`, `OperationalItem`, `PerishableRetailItem`, and `PerishableOperationalItem`.

### Iman

### Haziq

### Jun Han

### UI

API: [`Ui.java`](../src/main/java/seedu/binbash/ui/Ui.java)

![Ui class diagram](images/UiClassDiagram.png)

The above class diagram shows the components delegating separate functionalities of the Ui.

The `TextIn` class is responsible for reading user input and returning it to `Ui`, upon which it is passed to the *main()* program.

The `PrintStream` class writes text at the behest of `Ui` to standard output, upon which it is received by the user.

Note the use of an externally provided `LineReader` object in the `TextIn` class that handles input. This allows us to greatly extend our text-based user interface with features such as:

1. Command completion on tab

2. Displaying option descriptions on hover

3. Contextual help menus

This allows us to overload options on a small number of commands to provide full functionality of the application. Developers can then extend its features without also the worry of finding a way for users to access those features easily.

### Yi Hao

### Xavier

## Implementation

### Iman

### Haziq

### Jun Han

### Kota

### Yi Hao

### Xavier

## Logging

* We are using `java.util.logging` package for logging.
* The `BinBashLogger` class is used to manage all logging related funtions.
* The `Logger` for a class can be obtained by contructing a `BinBashLogger` object and assigning it as a class-level variable
* Log messages are output to a `logs.txt` file in the `*/logs/` directory by default.
* If there are issues with the `logs.txt` file that results in no `logs` being written, warnings logs will be output through the console instead.

## Product scope

### Target user profile

* Retail shop owners who has a need to efficiently manage their inventory list
* prefer desktop apps over other types of apps
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

### Value proposition

* Manage inventory list more efficiently compared to manual stock taking and typical mouse/GUI driven apps
* Portability allows usage on multiple operating systems (E.g. Windows, Linux, Mac).
* Lightweight, only requires entry-level hardware to run.

## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
| ------- | -------- | ------------------------- | ----------------------------------------------------------- |
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
