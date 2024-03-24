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
