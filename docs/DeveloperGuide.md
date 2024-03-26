# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

Students and Working adults

### Value proposition

Users can use this app to keep track of their expenditures and therefore they can better manage their finances.
Students can use this app to keep track of their GPA grade

## User Stories

| Version | As a ... | I want to ...                     | So that I can ...|
|---------|------|-----------------------------------|------------------|
| v1.0    |student| focus on financial responsibility |view a breakdown of his expenditures by category|
| v1.0    |user| find a to-do item by name         |locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

# Motivational messages
The Motivational module prints a randomly generated motivational quote for the user whenever the program starts 

# Expenditure
The expenditure module is acomprehensive allows users to key in their expenses to enable them to store all their 
expenses.
This guide will walk developers through how to use the expenditure function to track their expenses

# GPA Function
The GPA Calculator Module is a comprehensive component designed to facilitate the calculation 
of a user's updated GPA based on their current academic standing and projected module grades. 
This guide will walk developers through the architecture, functionality, and core components of the module.

## Overview
The module is divided into two primary classes:

### GPACommand: 
Handles user interactions, input collection, and directs the flow of the GPA calculation process.
### GPAMain: 
Contains the logic for calculating the updated GPA.


## Environment Setup
Java JDK 8 or above
An IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

## Class Descriptions

## Class: GPACommand
This class is responsible for interacting with the user, collecting inputs, 
and managing the flow of the GPA calculation process. It operates within a loop, 
allowing the user to perform multiple calculations or exit at any point.

### Key Methods
#### processGPACommand(): 
Orchestrates the overall process, including collecting user inputs and displaying the updated GPA.

### Workflow
#### Start/Exit Prompt: 
Prompt the user to start the calculation or exit.
#### GPA and MCs Input: 
Collect the user's current GPA and the number of Modular Credits (MCs) taken.
#### Module Details Input: 
For each module the user wants to add, collect the modular credit and the expected grade.
#### GPA Calculation: 
Calls GPAMain.calculateNewGPA to compute the updated GPA based on inputs.
#### Display Updated GPA: 
Displays the calculated updated GPA to the user.


## Class: GPAMain
Contains the core logic for calculating the new GPA given the user's current GPA, 
total accumulated credits, and details of modules being added.

### Key Methods
#### calculateNewGPA()
input: double currentGPA, int totalAccumulatedCredits, int numOfModules, int[] moduleCredits, String[] moduleGrades
Calculates and returns the updated GPA.
#### calculatePointsForGrade(String grade): 
Translates a letter grade into its corresponding GPA points.

### GPA Calculation Logic
#### Total Points Calculation: 
Calculates the total points achieved so far by multiplying the current GPA by the total accumulated credits.
#### Add Points for New Modules: 
For each new module, add to the total points based on the grade and credits.
#### Calculate Updated GPA: 
Divide the total points by the new total credits (accumulated + new modules) to get the updated GPA.

## Class: InvalidInputFormatException

### InvalidInputFormatException()
Throws an error message when the user enters an input that has the wrong format and the user will be informed about their wrong format

### Usages 
This exception is thrown in addExpenditure()


## Development Notes
Input Validation: Ensure that GPA scores and credit numbers are within valid ranges. This module expects a GPA between 0 and 5, and non-negative numbers for credits.
Error Handling: Properly handle invalid inputs, such as non-numeric values for credits or unsupported grade values.
Assertions: Use assertions to catch unexpected values during development. Ensure they are adequately handled or logged.

## Future Enhancements
GUI Integration: Consider developing a graphical user interface for easier input and interaction.

Persistent Data: Implement functionality to save and retrieve historical GPA calculations.

Expanded Grade Scale: Allow for customization of the grade to GPA points mapping to accommodate different institutions' grading scales.






