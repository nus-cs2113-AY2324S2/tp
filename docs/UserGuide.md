# BYTE-CEPS User Guide

## Introduction
Welcome to BYTE-CEPS, your CLI-based all-in-one tool for setting and tracking fitness goals. Whether you're a tech-savvy fitness enthusiast or just starting your fitness journey, BYTE-CEPS offers the simplicity and efficiency of a CLI interface to help you maintain or improve your fitness through self-managed routines.

## Features
BYTE-CEPS can track & manage several types of tasks, such as:
1. Exercise
2. Workout
3. Program
   
## Usage
Running Byte
- You are required to install Java 11 onto your computer.
- Download the [latest release](https://github.com/AY2324S2-CS2113-F14-3/tp/releases) from the releases page.
- Run the program in your preferred terminal using the command: java -jar byteceps.jar.

## Exercise Management
Using the `exercise` command, you may manage your exercises that have been stored in BYTE-CEPS.
### Add an exercise
You may add a new exercise using the `/add` flag.
```
exercise /add <EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /add pushups
```

Expected outcome:
```
[BYTE-CEPS]> Added Exercise: pushups
```

### Delete an exercise
You may also delete an existing exercise using the `/delete` flag.
```
exercise /delete <EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /delete pushups
```

Expected outcome:
```
[BYTE-CEPS]> Deleted Exercise: pushups
```

### Edit an exercise
If you ever need to edit an exercise name, you may do so using the `/edit` flag.
```
exercise /edit <OLD_EXERCISE_NAME [string]> /to <NEW_EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /edit pushups /to Decline pushups
```

Expected outcome:
```
[BYTE-CEPS]> Edited Exercise from pushups to Decline pushups
```

### List all exercises
You may lists all existing exercises by using the `/list` flag.
```
exercise /list
```

Example of usage: 
```
exercise /list
```

Expected outcome:
```
[BYTE-CEPS]> Listing Exercises:
          1. Decline pushups
```

## Workout Plan Management
A workout plan is a curated list of exercises that you would like to do in a single session. You may use the `workout` command to manage your workout plans.

### Add a workout plan
In order to assign a exercise to a workout plan, you must first create a workout using the `/create` flag.
```
workout /create <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /create push day
```

Expected outcome:
```
[BYTE-CEPS]> Added Workout Plan: push day
```

### Delete a workout plan
To delete an existing workout plan, use the `/delete` flag.
```
workout /delete <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /delete push day
```

Expected outcome:
```
[BYTE-CEPS]> Deleted Workout: push day
```

### List workout plan
You may list all your workout plans by using the `/list` flag.
```
workout /list 
```

Example of usage: 
```
workout /list
```

Expected outcome:
```
[BYTE-CEPS]> Listing Workouts:
            1. test
            2. push day
```

### Assign an exercise to a workout plan
You may assign an exercise to a specified workout plan using the `/assign` flag.
```
workout /assign <EXERCISE_NAME [string]> /to <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /assign pushups /to push day
```
Expected outcome:
```
[BYTE-CEPS]> Assigned Exercise 'pushups' to Workout Plan 'push day'
```

### Remove an exercise from a workout plan
You may remove an exercise from a specified workout plan using the `/unassign` flag.
```
workout /unassign <EXERCISE_NAME [string]> /from <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /unassign pushups /from push day
```

Expected outcome:
```
[BYTE-CEPS]> Unassigned Exercise 'pushups' from Workout Plan 'push day'
```

### List all exercises in a workout plan
You may see all the exercises in a given workout plan by using the `/info` flag.
```
workout /info <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /info push day
```

Expected outcome:
```
[BYTE-CEPS]> Listing exercises in workout plan 'push day':
            1. pushups
```

## Program Management
The `program` command not only allows you to assign a workout to a given day, but it allows you to log your completed exercises.

### Choose a workout plan for a day
You may assign a workout plan to a specific day of the week using the `/assign` flag.
```
program /assign <WORKOUT_PLAN_NAME [string]> /to <DAY [string]>
```

The `<DAY [string]>` parameter must be either variants of a day of the week, and is case insensitive:
1. Monday/Mon
2. Tuesday/Tues/Tue
3. Wednesday/Wed
4. Thursday/Thurs/Thu
5. Friday/Fri
6. Saturday/Sat
7. Sunday/Sun

Example of usage: 
```
program /assign push day /to monday
```

Expected outcome:
```
[BYTE-CEPS]> Workout push day assigned to monday
```

### View Today's workout plan:
You may see the workout plan for today using the `/today` flag.
```
program /today
```

Example of usage: 
```
program /today
```

Expected outcome:
```
[BYTE-CEPS]> Listing Exercises on 2024-03-28:
			1. benchpress
			2. overhead press
			3. chest fly
```
OR 
```
[BYTE-CEPS]> There is no workout assigned today (MONDAY)
```

### View Weekly workout plan
You may see all workout plans assigned to each day of the week by using the `/list` flag.
```
program /list
```

Example of usage: 
```
program /list
```

Expected outcome:
```
[BYTE-CEPS]> Your workouts for the week:
	MONDAY: 	full body
		1. benchpress
		2. barbell squat
		3. deadlift

	TUESDAY: Rest day

	WEDNESDAY: 	legs
		1. leg extensions
		2. barbell squat

	THURSDAY: Rest day

	FRIDAY: push day
		1. benchpress
		2. overhead press
		3. chest fly

	SATURDAY: Rest day

	SUNDAY: Rest day
```

### Remove a workout plan for a day
You can also remove a workout plan from a given day of the week by using the `/clear` flag.
```
program /clear <DAY [string]>
```
As stated, the DAY parameter must follow the format above.

Example of usage: 
```
program /clear Tuesday
```

Expected outcome:
```
[BYTE-CEPS]> Your workout on Tuesday has been cleared
```

## Logging Workouts
You are also able to log the amount of weight, sets and repetitions you have completed for an exercise on a given day, through the logging functionality. 

In order to log your exercises, must first have a workout plan assigned to the day that you are logging. However, you may log an exercise that was not originally in the workout plan to allow for flexibility of programs. 

### Adding an exercise log
You may create a workout log using the `/log` flag in the program command.

```
program /log <EXERCISE_NAME [string]> /weight <WEIGHT [integer]> /sets <NUMBER_OF_SETS [integer]> /reps <NUMBER_OF_REPS [integer]>
```

Example of usage: 
```
program /log benchpress /weight 125 /sets 3 /reps 5
```

Expected outcome:
```
[BYTE-CEPS]> Successfully logged 125kg benchpress with 3 sets and 5 reps on 2024-03-28
```

### Adding an exercise log for a separate date
You may also create a workout log for a specified date.
```
program /log <EXERCISE_NAME [string]> /weight <WEIGHT [integer]> /sets <NUMBER_OF_SETS [integer]> /reps <NUMBER_OF_REPS [integer]> /date <DATE [yyyy-mm-dd]>
```

Example of usage: 
```
program /log benchpress /weight 125 /sets 3 /reps 5 /date 2024-03-25
```

Expected outcome:
```
[BYTE-CEPS]> Successfully logged 125kg benchpress with 3 sets and 5 reps on 2024-03-25
```

### Viewing logs
You may see all the dates that you have entered at least a log entry by using the `/history` flag in the program command. 
```
program /history
```

Example of usage:
```
program /history
```

Expected outcome:
```
[BYTE-CEPS]> Listing Tracked Workouts:
			1. 2024-03-27
			2. 2024-03-06
			3. 2024-03-28
			4. 2024-03-25
```

### Viewing historic logs
You may view the logs that you have added on a given date by specifying a date in the `/history` flag.
```
program /history <DATE [yyyy-mm-dd]>
```

Example of usage:
```
program /history 2024-03-27
```

Expected outcome:
```
[BYTE-CEPS]> Listing Exercises on 2024-03-27:
			1. barbell squat (weight: 70, sets: 3, reps: 5)
			2. leg extensions (weight: 55, sets: 3, reps: 15)
```
