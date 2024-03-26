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

### Storage component
In order to store user data and load different data files for course planning, 
PlaNUS uses Storage class for this purpose.

Storage has two main file access APIs:
- `writeToFile(Timetable)`:
  - Take in a timetable containing courses, then write courses to the user data file at __./data/myTimetable.csv__.
    - Params: timetable – A table containing all courses of the user.
- `Timetable loadTimetable(String)`:
  - Take in a file name, then load the file containing all courses of the major/user to a Timetable object.
    - Params: timetableName – The name of the file containing all courses of the major/user. e.g. timetableName of "CEG" indicating the recommended timetable of Computer Engineering, while timetableName of "myTimetable" indicating the timetable of the user.
    - Returns: A timetable object that is loaded from the given file.

If target file is not found, the above two methods will be able to create the missing file.

Storage also contains a useful parser method `parseCourse(String)` that can parse a line of csv file into a Course object.

Below is a sequence diagram that shows how Storage takes part in the programme.

![Storage_SequenceDiagram](uml/Storage_SequenceDiagram-0.png)

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
