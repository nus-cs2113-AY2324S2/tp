# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

Ideas and structure for the Developer Guide: https://se-education.org/addressbook-level3/DeveloperGuide.html

Ideas and structure for the User Guide: https://se-education.org/addressbook-level3/UserGuide.html

Additional Packages used: JSON

## Setting up and getting started

## Design & implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Add Medication Command
The add medication command extends from the Command parent class and implements the following operations:
- execute(MedicationManager, DailyMedicationManager, Ui) - Adds the medication object into the respective medication managers.
- setMedicineAttributes() - Sets the medicine object's attributes to be added to the medicine managers. 
- assertionTest(MedicationManager, DailyMedicationManager) - Asserts that medicine has been added to both medication managers.
- parseStringToValues(Arguments) - Parses string input for medicine quantity and dosage into double type.

Given below is an example usage scenario and how the add command behaves at each step.
- Step 1. To be added.
- Step 2. To be added.

### Utilising the argument parser
The `ArgumentParser` requires the following to work:
1. `ArgumentList` object
2. Raw user input in `String`

The `ArgumentList` object constitutes to a combination of `Argument` objects. The constructor for 
the `ArgumentList` takes in a variable amount of `Argument`-extended classes. Additional assertion
tests have been added to ensure that certain attributes of the `Argument` classes do not clash with
one another.

An `Argument` object consists of the following:
1. `name`: Name of the argument, uses enum type `ArgumentName` as this value is used by `ArgumentParser` as well
2. `flag`: Takes the form of `-*` where `*` represents any number of alphabetic characters
3. `prompt`: Used within the guided prompt system
4. `help`: Used in printing help message to provide user with the usage for the argument
5. `isOptional`: A `boolean` value to specify whether this argument is optional or not. This value is utilised
by the `ArgumentParser` to determine whether the argument is required, and will throw a `ArgumentNotFound` 
exception if this argument is required but not found in user specified argument string.
6. `hasNoValue`: A `boolean` value to specify whether this argument has a corresponding value tied to it. 
`ArgumentParser` requires this to know whether to take the value specified by the user.

Additional information regarding the `ArgumentName` enum:
- To create a new enum, follow the following convention: `ALL_CAPS_NAME(“justFollowAttributeNamingConvention”)`
- This enum is used in `ArgumentParser` when it returns a `Map<ArgumentName, String>`, where the enum is used as
the key. In order to query the returned data structure, you can utilised the same enum.

In order to utilise the argument parser,
1. Determine if the `Argument` variant already exist. If not, create a new class and extend the `Argument` class.
2. Prior to completing the creation of your new class, create a new enum in `ArgumentName`.
3. In the class/method that you intend to use the `ArgumentParser`, create a `ArgumentList` and specify the required
`Argument` objects.
4. Finally, invoking `ArgumentList.parse` with the `String` object to obtained the parsed argument values.

Overview of the `meditracker.argument` core classes:
- TODO: Add class diagrams and/or object diagrams required to illustrate the above information


- TODO: Design and Implementation of the Logging Functionaity (SX) (Issue #41)
- TODO: Design and Implementing of the Load and Save functionality (SX) (Issue #25)
- TODO: Design and Implementing of the Reading and Writing of JSON file (SX) (Issue #27)
- TODO: Consider the design and impl. of #48

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
|v1.0|multipurpose user|Store data locally|Use the application and see the data even when offline between sessions|

## Non-Functional Requirements
{Give non-functional requirements}
- The user's program data should be persistent between program sessions.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
- TODO: Talk about how to test with different kinds of JSON file, and provide a sample JSON file with instructions on how to use it. (SX)
