# Active Edge User Guide 💪

## Introduction

Welcome to ActiveEdge! ActiveEdge is a productivity tool 
designed to help you track your health and fitness goals
effectively. Whether you want to manage your calorie 
intake, track your water consumption, set daily goals,
or keep a record of your exercises, ActiveEdge has got
you covered.

## Quick Start

To get started quickly with ActiveEdge, 
follow these simple steps:

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `ActiveEdge` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Logging Calorie Intake : `log`
- Adds a new meal to the log along with the number of servings.
- Alerts if the calories consumed exceed the goal.

Format: `log m/<MEAL_NAME> s/<SERVINGS>`

* The `<MEAL_NAME>` can be from any food from the food list.
* The `<SERVINGS>` can be any interger value.  

Example of usage: 

`log m/chicken rice s/1`

### Logging Water Intake : `log`
- Logs the quantity of water consumed.
- Tracks progress towards a customizable water intake goal.

Format: `log w/<AMOUNT_OF_WATER>`

* The `<AMOUNT_OF_WATER>` can be volume of water intake in ml.

Example of usage:

`log w/100`

### Viewing Water Intake: `show`
-Displays the total water intake and the percentage of water 
that has been consumed in comparison to the water intake goal.

Format: `show w`

Example of usage: `show w`

### Goal Setting : `set goal` / `show g`
- Sets the daily water intake and calorie intake goal.
- Displays current calorie and water intake goals.

Format: `set goal c/<CALORIE_GOAL>`/ `set goal w/<WATER_GOAL>`/ `show goals`

* The `<CALORIE_GOAL>` can be calorie goal for the day.
* The `<WATER_GOAL>` can be water goal for the day

Example of usage:

`set goal c/1000`
`set goal w/2000`
`show g`

### Delete meals/water: `delete`
-Deletes a meal/quantity of water(water intake) from the task list.
-Supports deletion of tasks based on their descriptions.

Format: `delete QUANTITY_OF_WATER ml` / `delete MEAL_NAME`

* The `QUANTITY_OF_WATER` can be the quantity of water the 
user logged previously and wishes to delete.
* The `MEAL_NAME` can be the name of the meal the user 
previously logged in and wishes to delete.

Example of usage:

`delete 100 ml`
`delete sushi`

## FAQ ❓

**Q**: How do I transfer my data to another computer? 

**A**:
Create a "data" folder within the ActiveEdge application directory, 
and then copy the "data.txt" file into this newly created folder.

**Q**: I accidentally deleted an entry. Is there a way to recover it?

**A**: Unfortunately, deleted entries are not recoverable, so it's 
important to be careful when using the delete command.



## Command Summary

{Give a 'cheat sheet' of commands here}

Here's a quick summary of commands available in ActiveEdge:

* Set daily calorie goal: `set goal c/<CALORIE_GOAL>`
* Set daily water goal: `set goal w/<WATER_GOAL>`
* View goals: `show g`
* Log meals: `log m/<MEAL_NAME> s/<SERVINGS>`
* View daily calories: `show c`
* View entries for logged meals: `list meals`
* Log water: `log w/<AMOUNT_OF_WATER>`
* View water intake: `show w`
* View entries for logged water: `list water`
* Log exercise: `log e/<EXERCISE_NAME> d/<DURATION>`
* View entries for logged exercises: `list exercises`
* View all entries logged: `list`
* Help: `help`
* Find: `find <KEYWORD>`
* Delete items from list: `delete <AMOUNT_OF_WATER>/<MEAL_NAME>`
* Clear: `clear`
