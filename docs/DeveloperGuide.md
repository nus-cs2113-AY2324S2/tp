# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Parser Implementation:
Parser Class (v1.0) reads user input from the standard input stream, checks the input, 
tests the validity, and conveys the validity back to the class it is called from.
![img.png](img.png)

Briefly, the Parser class interacts with the main program by reading the inputs and throwing exceptions
if the inputs are unexpected. These exceptions are then handled by the Duke class or the 
TicTacToe class. 
![img_4.png](img_4.png)

Currently, the Parser class only covers the reading of the chosen game by the user and 
the moves performed in TicTacToe. A future implementation for v2.0 would integrate methods
to read and verify user input for the HangMan class as well, such as readLetters(String: input) 
or readHMMove(String: input).
![img_1.png](img_1.png)

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
