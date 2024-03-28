# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design
## Implementation
#### Add an Exercise
1. The process begins with the user inputting a command via the command-line interface. In this scenario, the user enters the command `exercise /add pushups`, indicating their intention to add a new exercise named `pushups` to the system.
2. The command parser receives the user input and parses it to extract relevant information, such as the action (`exercise /add`) and any additional parameters (e.g., the name of the exercise to be added).
3. Upon receiving the parsed command, the Exercise Manager component validates the input to ensure it conforms to the expected format and criteria. This step is crucial for maintaining data integrity and preventing errors in subsequent processing.
4. If the input passes validation, the Exercise Manager proceeds to create a new Exercise object with the specified name, `pushups`. It then invokes the add method within the Activity Manager component to add the newly created Exercise to the activity set. Finally, a success message confirming the addition of the exercise is printed to the user interface, indicating that the operation was completed successfully.
5. If the input fails validation, an error message is generated and displayed to the user, informing them of the invalid command format. This ensures that users receive timely feedback and can correct their input accordingly

The sequence diagram below shows how an exercise is created.

![addExercise](https://github.com/V4Vern/tp/assets/28131050/45f7e9b3-8a31-4dfe-a783-433acb71fa58)


#### Delete an Exercise
1. The process begins with the user inputting a command via the command-line interface. In this scenario, the User provides the command `exercise /delete pushups` to delete the exercise named `pushups`.
2. The command parser receives the user input and parses it to extract relevant information, such as the action (`exercise /delete`) and any additional parameters (e.g., the name of the exercise to be deleted).
3. Upon receiving the parsed command, the Exercise Manager component validates the input to ensure it conforms to the expected format and criteria. This step is crucial for maintaining data integrity and preventing errors in subsequent processing.
4. If the input passes validation, the ExerciseManager retrieves the Exercise object associated with the name `pushups`. It then instructs the ActivityManager to delete the Exercise from the activitySet. The ExerciseManager then informs the User of the successful deletion.
5. If the input fails validation, an error message is generated and displayed to the user, informing them of the invalid command format. This ensures that users receive timely feedback and can correct their input accordingly

The sequence diagram below shows how an exercise is deleted.

![deleteExercise](https://github.com/V4Vern/tp/assets/28131050/3fde6b4e-d292-497a-9468-2118125678a7)


#### List Exercises
1. The process begins with the user inputting a command via the command-line interface. In this scenario, the User provides the command `exercise /list` to list all exercises.
2. The command parser receives the user input and parses it to extract relevant information, such as the action (`exercise /list`) and any additional parameters.
3. Upon receiving the parsed command, the Exercise Manager component validates the input to ensure it conforms to the expected format and criteria. This step is crucial for maintaining data integrity and preventing errors in subsequent processing.
4. If the input passes validation, the ExerciseManager instructs the ActivityManager to list all exercises. The ActivityManager retrieves the list of exercises from the activitySet. The ActivityManager returns the exercise list to the ExerciseManager. The ExerciseManager prints the list of exercises to the User.
5. If the input fails validation, an error message is generated and displayed to the user, informing them of the invalid command format. This ensures that users receive timely feedback and can correct their input accordingly

The sequence diagram below shows how exercises can be listed

![listExercise](https://github.com/V4Vern/tp/assets/28131050/eebe0e2e-d486-4644-b36d-e26b499d1f53)

#### Search an Exercise
1. The process begins with the user inputting a command via the command-line interface. In this scenario, the User provides the command `exercise /search pushups` to search for exercises containing `pushups`.
2. The command parser receives the user input and parses it to extract relevant information, such as the action (`exercise /search`) and any additional parameters (e.g., the name of the exercise to be search).
3. Upon receiving the parsed command, the Exercise Manager component validates the input to ensure it conforms to the expected format and criteria. This step is crucial for maintaining data integrity and preventing errors in subsequent processing.
4. If the input passes validation, the ExerciseManager instructs the ActivityManager to search for exercises containing the specified term. The ActivityManager performs the search operation on the activitySet. The ActivityManager returns the search results to the ExerciseManager. The ExerciseManager prints the search results to the User.
5. If the input fails validation, an error message is generated and displayed to the user, informing them of the invalid command format. This ensures that users receive timely feedback and can correct their input accordingly

The sequence diagram below shows how an exercise can be searched.

![searchExercises](https://github.com/V4Vern/tp/assets/28131050/fd32eba4-a7f1-460d-81cc-d9e57ca100c2)




#### Logging of workouts 
In order to log workouts, we have several layers to implement:
1. Logging of exercises
2. Storing logged exercises in a logged workout
3. Storing all logged workouts

The implementation for the above is as such:
1. `ExerciseLog` extends from the `Activity` class, and introduces `weight`, `set` and `repetitions` as variables to be stored.
2. `WorkoutLog` extends from the `Activity` class, and introduces `HashSet<ExerciseLog>` to store all logged exercises for that given workout, and `workoutName` to store the name of the `Workout` that was intended to be for that day. The id for this class is the date of the workout.
3. All `WorkoutLog` classes created are stored in `WorkoutLogsManager`, which is extended from `ActivityManager`. 

The user interfaces with this feature through the `WeeklyProgramManager`, as it is intended that the user logs their exercises according to the workout program that they have assigned to a specified day. 

The sequence diagram below shows how a log is created.

![WorkoutLogOverview.png](./diagrams/WorkoutLogOverview.png)


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
