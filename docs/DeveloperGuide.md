# Developer Guide

* [Acknowledgements](#acknowledgements)
* [Introduction](#introduction)
* [Design](#design)
  * [Overview of Components](#overview-of-components)
  * [Architecture](#architecture)
  * [UI component](#ui-component)
  * [Utility component](#utility-component)
  * [Health component](#health-component)
  * [Workout component](#workout-component)
  * [Storage component](#storage-component)
  * [PulsePilot component](#pulsepilot-component)
* [Product scope](#product-scope)
  * [Target user profile](#target-user-profile)
  * [Value proposition](#value-proposition)
* [User Stories](#user-stories)
* [Non-Functional Requirements](#non-functional-requirements)
* [Glossary](#glossary)
* [Instructions for manual testing](#instructions-for-manual-testing)

## Acknowledgements

Our team has referenced [Address Book (Level-3)](https://github.com/se-edu/addressbook-level3) 
referenced for their [User Guide (UG)](https://se-education.org/addressbook-level3/UserGuide.html)
and [Developer Guide (DG)](https://se-education.org/addressbook-level3/DeveloperGuide.html) to better structure our own Developer Guide.

- The `java.util.Scanner` class from the Java Standard Library is used for reading user input.
- The `JUnit 5` testing framework is used for writing and running unit tests.
- {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries
-- include links to the original source as well}


## Introduction


The aim of this guide is to provide an explanation for all the functions and processing of information in PulsePilot. This is to enable any technical readers to get a detailed understanding of the application's internals.

The application follows an Object-Oriented Design approach, with separate classes for handling different components
of the application, such as user input, output, exercise logging, and health data management.
The main entry point of the application is the Handler class, which contains the processInput method. 
This method is responsible for parsing user input, validating it, and delegating the appropriate actions 
to other classes based on the command provided.

The Output class is responsible for printing messages, prompts, and information to the console.

The Run and Gym classes represent different types of exercises that the user can log. 
The Health, Bmi, and Period classes are used to manage health-related data, such as Body Mass Index (BMI) 
and menstrual period information.

The LogFile class is used for logging application events and user actions to a log file.

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

This guide will include UML diagrams to better each component of our product. 

## Design

### Overview of Components

`Main`

- When PulsePilot is launched, it creates an instance of `PulsePilot`.

`PulsePilot`

- Upon creation, it initialises a `LogFile` and `DataFile` object to create log and data files for the bot.
- The `initialiseBot()` function is called to retrieve the user's name. 

### Architecture

### UI component

### Utility component

### Health component

The Health component consists of Health, HealthList, Bmi, Period, and Appointment.

{Insert class diagram -- half drawn in draw.io}

1. `Health`
2. `HealthList`class stores separate lists for different `Health` objects using ArrayList.
`HealthList`includes methods to add, delete, view history of the various `Health`lists.
3. `Bmi`class
4. `Period`class 
5. `Appointment`class stores appointment attributes (i.e. date, time, appointment description). Primarily, `Appointment`
has all necessary getter methods to access the attributes.

### Workout component

### Storage component

### PulsePilot component

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

- **Usability**: The application should have a user-friendly command-line interface with 
clear instructions and prompts for user input.
- **Reliability**: The application should handle invalid or incomplete user input gracefully, 
providing appropriate error messages and prompting the user for correct input.
- **Maintainability**: The codebase should follow best practices for Object-Oriented Programming, 
including proper separation of concerns, modularization, and code documentation.
- **Testability**: The application should have comprehensive unit tests to 
ensure correct functionality and enable easier maintenance and future enhancements.

{Give non-functional requirements}

## Glossary
- **Run**: An exercise activity involving running or jogging, typically characterized by distance, duration, and date.
-  **Gym**: An exercise activity involving various strength training exercises or 
workouts performed at a gym or fitness center.
- **BMI (Body Mass Index)**: A measure of body fat based on height and weight, 
used to assess overall health and fitness.
- **Menstrual Period**: A recurring physiological event in females, characterized by the start and end dates.
- **Medical Appointment**: An arrangement with a doctor, physiotherapist, or healthcare professional, 
to meet at a certain time and place.
* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}


### How to load sample data
The application does not currently support loading sample data. However, you can manually test different scenarios 
by entering commands and providing input through the command-line interface.

For example, to test logging a run exercise, you can enter the following command:
```java
WORKOUT /e:run /d:10.3 /t:00:40:10 /date:15-03-2024
```

This command will create a new run exercise with a distance of 10.3 units, a duration of 40 minutes and 10 seconds,
and a date of March 15, 2024.

Similarly, you can test logging gym workouts, recording BMI and menstrual period information, tracking medical
appointment, viewing the exercise history, and accessing the latest run details by entering the appropriate commands.