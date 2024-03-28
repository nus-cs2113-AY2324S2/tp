# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

ical4J Library: https://www.ical4j.org/

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Updating a Task

### Overview

Updating a task involves modifying its description or other attributes such as date or time. This guide outlines the process and considerations for updating tasks within the application.

### Update Manager Method

The `updateManager` method facilitates the updating of tasks. It prompts the user for an updated task description and handles tasks of different types (Todo, Event, Deadline) while ensuring the changes are correctly reflected in the application.

#### Method Signature

```java
public void updateManager(Scanner scanner, WeekView weekView, MonthView monthView, boolean inMonthView,
                          TaskManager taskManager, int day, int taskIndex, String newDescription)
        throws TaskManagerException, DateTimeParseException
```

### Parameters

- `scanner`: Scanner object for user input.
- `weekView`: Current week being viewed.
- `monthView`: Current month being viewed.
- `inMonthView`: Boolean indicating whether the month is being viewed.
- `taskManager`: TaskManager instance managing tasks.
- `day`: Day of the task.
- `taskIndex`: Index of the task to update.
- `newDescription`: Updated description of the task.

### Exceptions

- `TaskManagerException`: Thrown if not in the correct week/month view.
- `DateTimeParseException`: Thrown if there is an error parsing the date.

### Method Functionality

1. Converts the specified day to a `LocalDate`.
2. Verifies if the date is in the current week/month view.
3. Updates the task description based on its type.
4. Saves the updated tasks to the appropriate file.
5. Provides feedback to the user upon successful update.

### Update Task Method

The `updateTask` method is responsible for modifying the details of a task based on the user input.

### Method Signature

```java
public static void updateTask(LocalDate date, int taskIndex, String newTaskDescription, Scanner scanner)
        throws IndexOutOfBoundsException
```

### Parameters

- `date`: Date of the task.
- `taskIndex`: Index of the task to update.
- `newTaskDescription`: Updated description of the task.
- `scanner`: Scanner object for user input.

### Exceptions

- `IndexOutOfBoundsException`: Thrown if the task index is out of bounds.

### Method Functionality

1. Retrieves the tasks for the specified date.
2. Validates if the task index is within bounds.
3. Handles different task types (Todo, Event, Deadline).
4. Updates the task description accordingly.
5. Logs the changes if applicable.

## Adding a Task/Event/Deadline

### Overview

The Add Task/Event/Deadline feature enhances the TaskManager application by enabling users to create various types of tasks such as Todo, Event, and Deadline. This section elaborates on the implementation details of this feature, encompassing methods for task creation, user input handling, and task addition management.

### `addTask` Method

The `addTask` method orchestrates the creation and addition of a new task to the TaskManager. It accepts parameters including the task date, description, type, and relevant dates/times for events and deadlines. Depending on the task type, it constructs the corresponding task object (Todo, Event, or Deadline) and integrates it into the tasks map.

#### Method Signature

```java
public static void addTask(LocalDate date, String taskDescription, TaskType taskType, String[] dates,
                           String[] times) throws TaskManagerException
```

#### Parameters

- `date`: The date for the task.
- `taskDescription`: The description of the task.
- `taskType`: The type of the task (Todo, Event, or Deadline).
- `dates`: An array containing relevant dates for events and deadlines.
- `times`: An array containing relevant times for events and deadlines.

### `addManager` Method

The `addManager` method facilitates the management of adding tasks from user input along with the specified date. It prompts users to input task details, validates the input, and delegates the task creation process to the `addTask` method.

#### Method Signature

```java
public void addManager(Scanner scanner, WeekView weekView, MonthView monthView, boolean inMonthView, String action,
                       String day, String taskTypeString, String taskDescription)
        throws TaskManagerException, DateTimeParseException
```

#### Parameters

- `scanner`: Scanner object for user input.
- `weekView`: WeekView object for date validation.
- `monthView`: MonthView object for date validation.
- `inMonthView`: Boolean indicating whether the view is in month view.
- `action`: Action to be performed.
- `day`: Day for the task.
- `taskTypeString`: String representing the task type.
- `taskDescription`: Description of the task.

### Handling User Input

This segment prompts users to input essential details for task creation, encompassing descriptions, dates, and times for events and deadlines. It utilizes the `Scanner` class to capture and validate user input before initiating task creation.

### Task Type Switch Statement

Within the `addTask` method, a switch statement delineates the type of task being created based on the `taskType` parameter. Corresponding actions are executed to create and add the task to the tasks map based on the identified type.

### Error Handling

The implementation incorporates error handling mechanisms to effectively manage scenarios involving invalid inputs or unsupported task types. Exceptions such as `TaskManagerException` are employed to convey descriptive error messages, ensuring user guidance and application integrity.

### Saving Tasks to File

Upon task creation, the `addTask` method guarantees the preservation of the updated task list to a file (`tasks.txt`) by invoking the `saveTasksToFile` method from the `Storage` class. This serves to persist task data across application sessions.

### Exceptions

`IndexOutOfBoundsException`: Thrown if the task index is out of bounds.

### Method Functionality

1. Retrieves the tasks for the specified date.
2. Validates if the task index is within bounds.
3. Handles different task types (Todo, Event, Deadline).
4. Updates the task description accordingly.
5. Logs the changes if applicable.

## Storage component
**API** : [Storage.java](https://github.com/AY2324S2-CS2113-W13-2/tp/blob/master/src/main/java/storage/Storage.java)

The 'storage' component:
* Reads tasks from the formatted tasks.txt file and appends to task hashmap.
* Identifies unique tasks stored in task hashmap, parses and writes to tasks.txt file
* Handles exception if tasks.txt is in corrupted format

## Exporting .ics File Component

The 'ics' component:
* Exports the tasks in the task hashmap to a .ics file that can be imported into calendar applications
* Import tasks from external .ics file into the task hashmap

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
