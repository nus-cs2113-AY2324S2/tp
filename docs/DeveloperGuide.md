# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

### Architecture
![Architecture](images\Architecture.png)

The **Architecture diagram** given above shows the high-level design of the application.

Given below is a quick overview of main components and how they interact with each other.

**`Main`** is in charge of the app launch, shut down, and taking user input.

* At the app launch, it loads the data from the file system, and initializes other components.
* At the app shut down, it saves the data to the file system, and shuts down other components.

The app's work is done by the following components:

* [**`MainLogic`**](#sublogic-component): The main logic command executor.
* [**`SubLogic`**](#sublogic-component): The sub logics(i.e. **`MenuLogic`**, **`OrderLogic`**) command executor.
* [**`Parser`**](#parser-component): The parser that parses user input to command.
* [**`Model`**](#model-component): The data model that stores the data.
* [**`Command`**](#command-component): Represents a command that the user can execute.
* [**`Storage`**](#storage-component): Reads data from, and writes data.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues 
the command `Create order -menu 01`, `add -item 001` and `complete`.

![Sequence Diagram](images\ArchitectureSequanceDiagram.png)

### Model Component
The model consists of classes describing the objects used in this application.

* [**`ItemManager`**](#model-component): An interface containing methods representing operations common to **`Menu`** 
  and **`Order`**. <br><br />
* [**`Item`**](#model-component): An abstract class representing a food item. It should be implemented by **`MenuItem`**.
  <br><br />
* [**`Menu`**](#model-component): A class representing the menu(s) of the restaurant, where each contains menuItem(s)
 that can be ordered. Multiple menus can exist and each has a unique ID. <br><br />
* [**`MenuItem`**](#model-component): A class inheriting item, and represents a food item on the menu. <br><br />
* [**`Order`**](#model-component): A class representing an order to be entered into the system to be kept track of. Each 
  order has a unique ID generated from the time of order.<br><br />
* [**`SetMenu`**](#model-component): An enumeration representing the different types of set menus available, examples of
  which includes *breakfast*, *lunch*, *dinner*.

## Implementation

### 


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
