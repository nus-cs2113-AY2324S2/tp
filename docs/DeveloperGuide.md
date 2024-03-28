# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
### Parsing user input for caloric entries

This functionality is facilitated by `ParserCalories`. It implements one operation, namely:
- `ParserCalories#parseCaloriesInput(String)`

This operation is exposed in the `CalorieList` class as `CalorieList#addEntry(String)`.

Given below is an example usage scenario and how this mechanism behaves at every step:
- Step 1: When the user inputs the command `calories in burger c/200 date/270324` in the terminal,
the string is sent to `CalorieList#addEntry(String)`, which calls `ParserCalories#parseCaloriesInput(String)`.

- Step 2: Using `String.split()`, the method extracts information such as the description, number of calories, and date of entry. The obtained information is sent to the private method `ParserCalories#makeNewInputEntry(String, int, String)` to create a new entry of class `InputEntry` that extends `Entry`.

- Step 3: The created `InputEntry` instance is added into the `ArrayList<Entry>` attribute of the `CalorieList`.

### Parsing user input for hydration entries

This functionality is facilitated by `ParserHydration`. It implements one operation, namely:
- `ParserHydration#parseHydrationInput(String input)`

This operation is exposed in the `HydrationList` class as `HydrationList#addEntry(String)`.

Given below is an example usage scenario and how this mechanism behaves at every step:
- Step 1: When the user inputs the command `calories in Milo v/100 date/270324` in the terminal,
  the string is sent to `HydrationList#addEntry(String)`, which calls `ParserHydration#parseHydrationInput(String)`.

- Step 2: Using `String.split()`, the method extracts information such as the description, volume of beverage, and date of entry. The obtained information is sent to the private method `ParserHydration#makeNewInputEntry(String, int, String)` to create a new entry of class `HydrationEntry` that extends `Entry`.

- Step 3: The created `HydrationEntry` instance is added into the `ArrayList<Entry>` attribute of the `HydrationList`.

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
