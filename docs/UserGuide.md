# BYTE-CEPS User Guide

## Introduction
Welcome to BYTE-CEPS, your CLI-based all-in-one tool for setting and tracking fitness goals, managing reminders, creating and running timers for interval workouts, and accessing exercise information. Whether you're a tech-savvy fitness enthusiast or just starting your fitness journey, BYTE-CEPS offers the simplicity and efficiency of a CLI interface to help you maintain or improve your fitness through self-managed routines.

## Features
BYTE-CEPS can track & manage several types of tasks, such as:
1. Exercise
2. Workout
3. Week
   
## Usage
Running Byte
- You are required to install Java 11 onto your computer.
- Download the [latest release](https://github.com/AY2324S2-CS2113-F14-3/tp/releases) from the releases page.
- Run the program in your preferred terminal using the command: java -jar byteceps.jar.

## Exercise Management

### Add an exercise
Adds a new exercise to your current list of exercises.
```
exercise /add <EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /add pushups
```

Expected outcome:
```
[ByteCeps]> Added Exercise: pushups
```

### Delete an exercise
Deletes an existing exercise in your current list of exercises.
```
exercise /delete <EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /delete pushups
```

Expected outcome:
```
[ByteCeps]> Deleted Exercise: pushups
```

### Edit an exercise
Modify an existing exercise in your current list of exercises.
```
exercise /edit <OLD_EXERCISE_NAME [string]> /to <NEW_EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /edit pushups /to Decline pushups
```

Expected outcome:
```
[ByteCeps]> Edited Exercise from pushups to Decline pushups
```

### List all exercises
Lists all exercises available
```
exercise /list
```

Example of usage: 
```
exercise /list
```

Expected outcome:
```
[ByteCeps]> Listing Exercises:
          1. Decline pushups
```

## Workout Plan Management

### Add a workout plan
Create a new workout plan
```
workout /create <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /create push day
```

Expected outcome:
```
[ByteCeps]> Added Workout Plan: push day
```

### Delete a workout plan
Delete a new workout plan
```
workout /delete <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /delete push day
```

Expected outcome:
```
[ByteCeps]> Deleted Workout: push day
```

### list workout plan
list all workout plan
```
workout /list 
```

Example of usage: 
```
workout /list
```

Expected outcome:
```
[ByteCeps]> Listing Workouts:
            1. test
            2. push day
```

### Assign exercise to a workout plan
Add an exercise to a specific workout plan
```
workout /assign <EXERCISE_NAME [string]> /to <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /assign pushups /to push day
```

Expected outcome:
```
[ByteCeps]> Assigned Exercise 'pushups' to Workout Plan 'push day'
```

### Unassign exercise to a workout plan
Remove an exercise to a specific workout plan
```
workout /unassign <EXERCISE_NAME [string]> /from <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /unassign pushups /from push day
```

Expected outcome:
```
[ByteCeps]> Unassigned Exercise 'pushups' from Workout Plan 'push day'
```

### List all exercises in a workout plan
List all exercises in a specific workout plan
```
workout /info <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /info push day
```

Expected outcome:
```
[ByteCeps]> Listing exercises in workout plan 'push day':
            1. pushups
```

## Programme Management

### Choose a workout plan for a day
Assign a workout plan to a specific day of the week.
```
week /assign <WORKOUT_PLAN_NAME [string]> /to <DAY [string]>
```

Example of usage: 
```
week /assign push day /to monday
```

Expected outcome:
```
[ByteCeps]> Workout push day assigned to monday
```

### View Today’s workout plan:
Lists workout plan for today
```
week /today
```

Example of usage: 
```
week /today
```

Expected outcome:
```
[ByteCeps]> Here's today's workout: 
TUESDAY
[pushups]
```
OR 
```
There is no workout assigned today (MONDAY)
```


### View Weekly workout planL
List the workout plan for the entire week
```
week /list
```

Example of usage: 
```
week /list
```

Expected outcome:
```
[ByteCeps]> Your workouts for the week:
	MONDAY: Rest day

	TUESDAY: push day
		1. pushups

	WEDNESDAY: Rest day

	THURSDAY: Rest day

	FRIDAY: Rest day

	SATURDAY: Rest day

	SUNDAY: Rest day
```

### Remove a workout plan for a day
Clears any workout from a specific day of the week.
```
week /clear DAY
```

Example of usage: 
```
week /clear Tuesday
```

Expected outcome:
```
[ByteCeps]> Your workout on Tuesday has been cleared
```

