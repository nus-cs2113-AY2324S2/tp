# Wellness360 Developer Guide

## Table of Contents
- [Wellness360 Developer Guide](#wellness360-developer-guide)
- [Table of contents](#table-of-contents)
- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#setting-up-getting-started)
  - [Setting up the project in your computer](#setting-up-the-project-in-your-computer)
  - [Before writing code](#before-writing-code)
- [Design & implementation]()
  - [Architecture]()
  - [Ui component]()
  - [Command parser component]()
  - [Storage component]()
  - [Reflection component](#reflection-component)
    - [Description](#description)
    - [Design Considerations](#design-considerations)
      - [User Design Considerations](#user-design-considerations)
      - [Developer Design Considerations](#developer-design-considerations)
    - [Implementation](#implementation)
      - [Class Diagram](#class-diagram)
      - [Sequence Diagram](#sequence-diagram)
  - [Habit tracker component]()
      - [Description](#description-1)
      - [Design Considerations](#design-considerations-1)
          - [User Design Considerations](#design-considerations-1)
          - [Developer Design Considerations](#design-considerations-1)
      - [Implementation](#implementation-1)
          - [Class Diagram](#class-diagram-1)
          - [Sequence Diagram](#sequence-diagram-1)
  - [Sleep tracker component]()
  - [Focus timer component](#focus-timer-component)
    - [Design Considerations](#design-considerations-1)
    - [Implementation](#implementation-1)
      - [Focus Class Diagram](#focus-class-diagram)
      - [Focus State Transition Diagram](#focus-state-transition-diagram)
      - [Focus Sequence Diagram](#focus-sequence-diagram)
  - [Fitness tracker component]()

## Acknowledgements

Reference to AB-3 Developer Guide
* [Source URL](https://se-education.org/addressbook-level3/DeveloperGuide.html#documentation-logging-testing-configuration-dev-ops)
* Used as template to structure this DeveloperGuide

Reference to AB-3 diagrams code
* [Source URL](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams)
* Used as reference to understand PlantUML syntax

## Setting up, getting started

### Setting up the project in your computer
Fork this repo, and clone it on your local machine.

You are recommended to use Intellij IDEA:

1. Configure the JDK: Follow the guide [[se-edu/guides] IDEA: Configuring the JDK](https://se-education.org/guides/tutorials/intellijJdk.html) to ensure Intellij is configured to use JDK 11.
2. Import the project as a Gradle project: Follow the guide [[se-edu/guides] IDEA: Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html) to import the project into IDEA.
3. Verify the setup:
    * Run the main class in IntelliJ and try a few commands.
    * Run the tests to ensure they all pass.

### Before writing code
1. Configure the coding style: [[se-edu/guides] IDEA: Configuring the code style](https://se-education.org/guides/tutorials/intellijCodeStyle.html)
2. Set up CI: This project comes with a GitHub Actions config files (in .github/workflows folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the master branch or to any PR. No set up required.
3. Read the developer guide to understand the design.

## Design & implementation

### Architecture

### Ui component

### Command parser component

### Storage component

### Reflection component
#### Description
The Reflection component provides users with random reflections questions to reflect on about their personal lives and goals. 
This component aims to contribute to the goal of improving user's wellness.

#### Design Considerations
* ##### User Design Considerations
  * The sets of 5 questions are randomly generated each time. They are randomised to allow users to reflect on different areas. 
  * Users can save questions they liked into their favourites list for future review.
  * Users can also delete questions from their favourites list if the question is no longer relevant to them.
  * Help menu is available for new users to guide them on how to use the reflection feature.
  * Error messages with guidance messages will be printed to console if command input by user is invalid.


* #### Developer Design Considerations
  * Modularity: Organized into packages for separation of concerns. Each package contains related classes, such as ReflectionManager and ReflectionList in the reflection package, and ReflectionCommandParser in the parser package.
  * Abstraction: Use of abstract classes and interfaces for flexibility and extensibility. Abstract classes (ReflectionList) provide a common structure and behavior for subclasses (FavoriteReflectionsList, ReflectionQuestionBank), promoting code reuse and ensuring consistent functionality across different implementations.
  * Encapsulation: Private access modifiers and encapsulated methods ensure data integrity. Methods like addReflectionQuestion() and removeReflectionQuestion() in ReflectionList encapsulate the manipulation of the reflection list, ensuring data integrity and promoting a clear interface for interacting with the list.
  * Exception Handling: Graceful error handling with meaningful messages.
  * Command Design Pattern: Encapsulation of actions as objects for decoupling.
  * Dependency Injection: Dependencies injected via constructors for loose coupling.
  * SRP: Classes adhere to the Single Responsibility Principle. For example, the ReflectionManager class is responsible for managing reflection-related operations, while the ReflectionCommandParser class handles parsing and determining reflection commands.
  * Readability and Maintainability: Descriptive naming, comments, and documentation for clarity.

#### Implementation

##### Class Diagram
![ReflectionClassDiagram.png](diagrams/reflection/ReflectionClassDiagram.png)

  * `ReflectionManager` class
    * Overview
      * The `ReflectionManager` class oversees reflection-related operations, managing reflection questions and favorites. 
    * Attributes:
      * `HELP_MENU_INSTRUCTIONS`: Array containing instructions for the help menu. (Omitted from Class Diagram)
      * `FAVOURITE_QUESTIONS_FILE_PATH`: Path to the file storing favorite reflection questions. (Omitted from Class Diagram)
      * `fiveRandomQuestions`: ArrayList holding five random reflection questions. (Omitted from Class Diagram)
      * `questionBank`: Instance of `ReflectionQuestionBank` managing reflection questions.
      * `favoriteReflectionsList`: Instance of `FavoriteReflectionsList` managing favorite reflection questions.
    * Methods:
      * `printFiveRandomQuestions()`: Prints five random reflection questions.
      * `saveReflectionQuestion(int reflectionId)`: Saves a reflection question to favorites based on ID.
      * `unsaveReflectionQuestion(int reflectionId)`: Removes a reflection question from favorites based on ID.
      * `printFavourites()`: Prints the list of favorite reflection questions.
      * `printHelpMenu()`: Prints the help menu with available reflection commands.
    * Dependencies:
      * Storage: Utilized for data storage operations.
      * Ui: Utilized for user interface interactions.
    * UML notes:
      * `ReflectionManager` contains exactly one `ReflectionQuestionBank` class and one `FavoriteReflectionsList` class.
      * It relies on `Storage` class for file operations and `Ui` class for user interactions.
  
  * `ReflectionQuestion` class
    * Overview:
      * This class represents a reflection question
    * Attributes:
      * `question`: Private attribute holding the text of the reflection question.
    * Methods:
      * `toString()`: Overriden method that retrieves the text of the reflection question.
    * UML notes:
      * `ReflectionList` and `FavoriteReflectionsList` may contain 0 or more `ReflectionQuestion` instances.
      * `ReflectionQuestionBank` contains exactly 41 of `ReflectionQuestion` instances.
      * When a `ReflectionList`/ `FavoriteReflectionsList`/ `ReflectionQuestionBank` object is destroyed, its associated `ReflectionQuestion` instances are also destroyed, reflecting a "whole-part" relationship.
  
  * `ReflectionList` class
    * Overview:
      * The `ReflectionList` class serves as an abstract representation of a list of reflection questions within the Reflection Management System. It provides basic operations for managing reflection questions, such as adding, removing, and retrieving questions from the list.
    * Attributes:
      * `reflectionList`: Protected attribute representing the list of reflection questions. It is an ArrayList of ReflectionQuestion objects.
    * Methods:
      * `addReflectionQuestion(ReflectionQuestion reflectionQuestion)`: Adds a reflection question to the list. 
      * `removeReflectionQuestion(ReflectionQuestion reflectionQuestion)`: Removes a reflection question from the list.
      * `getSize()`: Retrieves the size of the reflection list. 
      * `getReflectionList()`: Retrieves the list of reflection questions.
    * UML notes:
      * `FavoriteReflectionsList` inherits from `ReflectionList`
      * `ReflectionQuestionBank` inherits from `ReflectionList`
      * `ReflectionList` may contain 0 or more instances of `ReflectionQuestion`. The association is of type composition.
  
  * `FavoriteReflectionsList` class
    * Overview:
      * The `FavoriteReflectionsList` class extends the `ReflectionList` class and represents a specialized list of favorite reflection questions within the Reflection Management System. It inherits functionality from `ReflectionList`.
    * Attributes:
      * Inherits from the superclass `ReflectionList`.
    * Methods:
      * `get(int favouritesId)`: Retrieves a favorite reflection question by its index in the list.
    * Dependencies:
      * Inherits functionality from the `ReflectionList` class.
    * UML notes:
      * `FavoriteReflectionsList` inherits from `ReflectionList`.
      * `ReflectionManager` contains a single instance of `FavoriteReflectionsList`.
      * `FavoriteReflectionsList` may contain 0 or more instances of `ReflectionQuestion`.

  * `ReflectionQuestionBank` class
    * Overview:
      * The `ReflectionQuestionBank` class represents a bank of reflection questions within the Reflection Management System. It extends the ReflectionList class to inherit basic list management functionalities and provides additional methods for initializing the bank with predefined questions and retrieving random sets of questions.
    * Attributes:
      * Inherits from the superclass `ReflectionList`. 
      * `REFLECTION_QUESTIONS`: Static array containing predefined reflection questions that used for setup of question bank.
    * Methods:
      * `setUpReflectionBank()`: Initializes the reflection question bank with predefined questions. 
      * `getFiveRandomQuestions()`: Retrieves five random reflection questions from the bank.
    * Dependencies:
      * Inherits functionality from the `ReflectionList` class.
    * UML notes:
      * `ReflectionManager` contains a single instance of `ReflectionQuestionBank`
      * `ReflectionQuestionBank` inherits from `ReflectionList`
      * `ReflectionQuestionBank` contains 41 instances of `ReflectionQuestion`
  
  * `ReflectionCommandParser`
    * Overview: Parses reflection-related commands and create different reflection command objects based on user input.
    * Method: `determineReflectionCommand(ReflectionManager reflectionManager, String commandArgs)`
  
  * Reflection command classes
    * `GetReflectionQuestionsCommand`: Retrieve random reflection questions.
      * Command format: `reflect get`
    * `ListFavouriteReflectionsCommand`: List favorite reflection items.
      * Command format: `reflect list`
    * `ReflectionHelpCommand`: Display help menu for reflection commands.
      * Command format: `reflect help`
    * `SaveToFavouritesCommand`: Save a reflection item to favorites.
      * Command format: `reflect save <reflection_id>`
    * `UnsaveFromFavouritesCommand`: Unsave a reflection item from favorites.
      * Command format:`reflect unsave <reflection_id>`


#### Sequence Diagram
![ReflectionSequenceDiagram.png](diagrams/reflection/ReflectionSequenceDiagram.png)
* Note that `PlaceholderReflectionCommand` can refer to any of the reflection commands as mentioned above, as all of them follow the same call pattern.

When main starts, `scanner` and `ReflectionManager` objects are created. Upon receiving user input, the input will first be
determined if it is a command related to the reflection feature. If it is, it will be further parsed by `ReflectionCommandParser` to determine
the command. The corresponding reflection command object is then created and is returned to `Main`, where `execute` will then be called
and the corresponding method in `ReflectionManager` is invoked.

### Habit tracker component

#### Description
The habit tracker component aims to provide user with a tool to track and cultivate good habits.

#### Design Considerations
* ##### User Design Considerations
    * User can add new habits in the habit tracker that they wish to cultivate.
    * User can increase the count of a habit after they completed the habit for the day, allowing them to keep track of the total number of times they completed the habit.
    * User can also delete the habit if they no longer want to cultivate that habit.
    * User are able to set the priority of the habits (HIGH, MED, LOW), to prioritise their time on more important habits.
    * User can also sort the habits according to their priority for better visualization.
    * A help menu is also provided for users to guide them on how to use the habit tracker.
    * Error messages with guidance messages will be printed to console if command input by user is invalid.

* #### Developer Design Considerations
    * Modularity: Encapsulate related functionalities within classes to promote re-usability and maintainability.
    * Exception Handling: Use of custom exceptions to differentiate between various types of errors, and to handle them appropriately with error messages
    * Data Encapsulation: Control the access to a class's internal attributes, accessible only through getters and setters
    * Interface Segregation: Segregation of command interface to represent different command types for specific use cases.
    * Readability and Maintainability: Descriptive naming, robust documentation for code clarity.

#### Implementation

##### Class Diagram
![HabitClassDiagram.png](diagrams/habit/HabitClassDiagram.png)

* `HabitTracker` class
    * Overview
        * The `HabitTracker` class manages the habit tracker list which contains the habits.
    * Attributes:
        * `habitList`: Private attribute representing the list of habits. It is an ArrayList of Habit objects.
    * Methods:
        * `HabitTracker()`: Constructs a HabitTracker object. 
        * `getNumberOfHabits()`: Returns the number of habits in the habitList.
        * `addHabit(Habit newHabit)`: Adds a new Habit object into the habitList.
        * `listHabits()`: Prints a list of all habits in habitList .
        * `isValidHabitID(int habitID)`: Check if a habit ID is valid by comparing with the size of habitList.
        * `updateHabitCount(int habitID, String updatedCount)`: Update the habit count of a habit.
        * `deleteHabit(int habitID)`: Delete a habit from habitList.
        * `setPriorityLevel(int habitID, String priority)`: Set the priority of a habit.
        * `sortHabits()`: Sort the habits in habitList according to the habits' priorities.
        * `clearHabitList()`: Delete all habits from habitList.
    * Dependencies:
        * HabitTrackerStorage: Utilized for data storage operations.
        * Ui: Utilized for user interface interactions.
    * UML notes:
        * `HabitTracker` can contain any number of `Habit` class objects.
        * It relies on `HabitTrackerStorage` class for file operations and `Ui` class for user interactions.

* `Habit` class
    * Overview:
        * This class represents a Habit.
    * Attributes:
        * `description`: Private attribute holding the habit description.
        * `habitCount`: Private attribute holding the total count the user have completed the habit.
        * `priority`: Private attribute representing the priority level of a habit.
    * Methods:
        * `Habit(String description)`: Constructs a habit object.
        * `Habit(String description, int habitCount, Priority priority)`: Constructs a habit object.
        * `getDescription()`: Get the description of the habit.
        * `getHabitCount()`: Get the habit count of the habit.
        * `getPriority()`: Get the priority of the habit.
        * `toString()`: Method that formats the attributes of the habit for printing.
    * UML notes:
        * `HabitTracker` can contain any number of `Habit` instances.
        * When a `HabitTracker` object is destroyed, its associated `Habit` instances are also destroyed, showcasing a composition relationship.

* `HabitCommandParser`
    * Overview: Parses habit-tracker commands and create different habit command objects based on user input.
    * Method: `determineHabitCommand(HabitTracker habitTracker, String commandArgs)`

* Habit command classes
    * `AddHabitCommand`: Add a new habit to the habit-tracker.
        * Command format: `habit add <habit_description>`
    * `DeleteHabitCommand`: Delete a habit from the habit-tracker.
        * Command format: `habit delete /id <habit_ID>`
    * `ListHabitsCommand`: Prints a list of all existing habits.
        * Command format: `habit list`
    * `UpdateHabitCountCommand`: Increase habit count after completing a habit.
        * Command format: `habit update /id <habit_ID> /by <increment_count>`
    * `SetPriorityCommand`: Set priority level of habit
        * Command format: `habit set /id <habit_ID> /priority <priority_level>`
    * `SortHabitsCommand`: Sort habit list according to priority level.
        * Command format: `habit sort`
    * `HabitHelpCommand`: Display a help menu of the habit-tracker commands
        * Command format: `habit help`


#### Sequence Diagram
![HabitSequenceDiagram.png](diagrams/habit/HabitSequenceDiagram.png)
* Note that `PlaceholderHabitCommand` can refer to any of the habit commands. 

When main starts, `scanner` and `HabitTracker` objects are created. Upon receiving user input, the input will first be
determined if it is a command related to the habit tracker feature. If it is, it will be further parsed by `HabitCommandParser` to determine
the command. The corresponding habit command object is then created and is returned to `Main`, where `execute` will then be called
and the corresponding method in `HabitTracker` is invoked.

### Sleep tracker component

### Focus timer component
The focus timer component provides users with a countdown timer and a count up timer, which enables the user to set a 
goal to focus entirely on their work. This component aims to allow users to keep track of their time, improving their
productivity and well-being.

### Design Considerations
* #### User Design Considerations
  * Users will be able to choose between a count up timer and a countdown timer.
  * Users can start, pause, resume, stop the timer at any point in time.
  * Users will also be able to navigate to other functions while running the timer concurrently.
  * Error messages will inform users the current status of the timer and reason the error appeared.

* #### Developer Design Considerations
  * The `Focus Timer` component is a wrapper class for both `CountupTimer` and `CountdownTimer`, which contains 
utility logic to identify state and manage the different timers.

#### Implementation

#### Focus Class Diagram
![FocusClassDiagram.png](diagrams\focus\FocusClassDiagram.png)
* `FocusTimer` object
  * Overview:
    * The `FocusTimer` class is a facade class that sits between the component internals and users of the component 
    such that all access to the component like countdown timer and count up timer happens through the Facade class.
  * Attributes:
    * `countupTimer`: Count up timer object.
    * `countdownTimer`: Count down timer object.
    * `timerMode`: Indicates the timer mode to be operating.
  * Methods:
    * `getStartTiming()`: Gets the current running state of the timer.
    * `switchTimer()`: Changes the timer mode between count up and count down timer.
    * `getPausedStatus()`: Gets the current pause status of the timer.
    * `setStartTiming()`: Start the timer.
    * `setStopTiming()`: Stop the timer.
    * `setPauseTiming()`: Pause the timer.
    * `setResumeTiming()`: Resume the timer.
    * `checkTime`: Get the total time elapsed or time remaining in the timer.
    * `setDuration`: Change countdown timer duration.
    
* `CountupTimer` and `CountdownTimer` object
  * Dependencies:
    * `Ui` object: Utilized for user interface interactions.

#### Focus State transition Diagram
There are many commands for the focus timer feature. However, some commands logically cannot be executed in cetain 
states. For example, the command `focus pause` cannot be used if the timer hasn't started. Another example would be 
the `focus switch` command to switch between the timer could not be used if the current timer mode is running. To aid 
the understanding of the logic, we will use state transition diagram.

![FocusStateDiagram.png](diagrams\focus\FocusStateDiagram.png)
* The black circle in the diagram represents the starting point of focus timer. 
* The labels of the arrows represents the commands.

#### Focus Sequence Diagram
![FocusSequenceDiagram.png](diagrams\focus\FocusSequenceDiagram.png)
* Note that `PlaceholderFoucsCommand` can refer to any of the focus commands as mentioned above, since all of them 
follows the same call pattern.

When main starts, `scanner` and `FocusTimer` objects are created. Upon receiving user input, the input will first be
determined if it is a command related to the focus timer feature. 
If it is, it will be further parsed by `FocusCommandParser` to determine the command. 
The corresponding focus command object is then created and is returned to `Main`, where `execute` will then be called 
and the corresponding method in `FocusTimer` is invoked.


### Fitness tracker component


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
