# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

{Describe the target user profile}: Students (NUS student?)

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...                                           |
|--------|----------|---------------|-------------------------------------------------------------|
|v1.0| new user |see usage instructions| refer to them when I forget how to use the application      |
|v2.0| user     |find a to-do item by name| locate a to-do without having to go through the entire list |
|    | user     |add a task to the timetable| record when a task needs to be done  |
 

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

## Parser Component

Here is a general guide of how Parse Class looks like in sequence diagram.

![Parse.png](diagram%2FParse.png)

Here is a class diagram for the method changeTaskTiming method in Parser Class.

![changeTaskTimingClassDiagram.png](diagram%2FchangeTaskTimingClassDiagram.png)

The following sequence diagram of changeTaskTiming shows the interactions with other objects.

![changeTaskTiming_Sequence_Diagram.png](diagram%2FchangeTaskTiming_Sequence_Diagram.png)

Here is how this method works:

1. The Parser calls the changeTaskTiming method with a command and the UserList. It calls InputValidator.

2. The InputValidator validates the command, ensuring that it meets the required format in days and index.

3. If validation is successful, the User calls the timetable and calls its changeFlexibleTaskTiming method with the specified parameters.

4. The Timetable updates the timing of the flexible task.

5. If successful, the InputValidator sends a success message back to the Parser. Otherwise, it throws a RuntimeException.

## Timetable Component
Here is a sequence diagram of changeTaskType method in Timetable class. It shows the interactions with other objects.
![changeTaskType.png](diagram%2FchangeTaskType.png)

Here is how this method works:
1. The sequence starts with the Parser sending a request to the Timetable to change the type of a task.

2. The Timetable activates and processes the changeTaskType method.

3. Within the Timetable, the dayOfWeek parameter is capitalized to ensure consistency.

4. The Timetable calls the list of tasks associated with the specified day (dayOfWeek). It activates the Task object to fetch the task at the specified index (index).

5. The Task object modifies the type of the task to the new type specified by the Parser.

6. Finally, the modified task or any relevant status information is returned to the Parser.
