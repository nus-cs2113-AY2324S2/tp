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


### Calculating calorie requirements based on a user's goals

#### Implementation

This functionality is facilitated by `UserGoals`. It implements one operation, namely:
- `UserGoals#getHealthInfo(User)`

This operation is exposed in the `User` class as `User#getHealhInfo()`.

Given below is an example usage scenario and how this mechanism behaves at every step:
- Step 1: When the user inputs the command `user progress` in the terminal,
  the string is sent to `User#getHealthInfo()`, which calls `UserGoals#getHealthInfo(User)`.

- Step 2: The method retrieves the user's information such as his height, weight, age, gender, exercise levels and intended goal.

- Step 3: Using these information, the method creates a `requestBody` `String`. 

- Step 4: The created `requestBody` is used to send a `HttpRequest` to RapidAPI's Fitness API, and the response is parsed to determine the number of calories a user needs to consume according to their personal goals.

- Step 5: This value is set to `User.caloriesRequired` by `User#setCaloriesRequired(int)`.

#### Design considerations

- **Alternative 1 (current choice):** Uses an API to get the calories needed
    - Pros: No need to figure out the optimal algorithm
    - Cons: Need to parse response to sieve out necessary information

- **Alternative 2:** Uses an algorithm to find the number of calories needed
  - Pros: Not dependent on external APIs
  - Cons: Need to come up with an algorithm to use

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
