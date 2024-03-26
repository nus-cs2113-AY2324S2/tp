# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
### Timetable plan component
In order to keep track of the timetable plan of the user, 3 classes are used mainly.
- `Course`: Represents a course taken or planned by the user
- `Timetable`: Stores the list of courses
- `PlanGetter`: Provides access to the timetable plan

Below is the class diagram to illustrate this:
![TimetablePlan_ClassDiagram](uml/TimetablePlan_ClassDiagram.png)

There are 3 overloaded variations of the `getPlan()` method in the `PlanGetter` class.
The first is as shown in the class diagram above. 
The second takes in an additional integer `year`, while the third takes in two additional integers `year` and `term`.

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
