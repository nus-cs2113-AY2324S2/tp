# User Guide

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

Format: `log m/FOOD s/NUMBER OF SERVINGS`

* The `FOOD` can be from any food from the food list.
* The `NUMBER OF SERVINGS` can be any interger value.  

Example of usage: 

`log m/chicken rice s/1`

### Logging Water Intake : `log`
- Logs the quantity of water consumed.
- Tracks progress towards a customizable water intake goal.

Format: `log w/VOLUME_OF_WATER`

* The `VOLUME_OF_WATER` can be volume of water intake in ml.

Example of usage:

`log w/100`

### Goal Setting : `set goal` / `show g`
- Sets the daily water intake and calorie intake goal.
- Displays current calorie and water intake goals.

Format: `set goal c/CALORIE_GOAL`/ `set goal w/WATER_GOAL`/ `show goals`

* The `CALORIE_GOAL` can be calorie goal for the day.
* The `WATER_GOAL` can be water goal for the day

Example of usage:

`set goal c/1000`
`set goal w/WATER_GOAL`
`show g`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

Here's a quick summary of commands available in ActiveEdge:

* Log meals: `log m/FOOD s/NUMBER_OF_SERVINGS`
* View daily calories: `show c`
* Log water: `log w/VOLUME_OF_WATER`
* View water intake: `show w`
* Set daily calorie goal: `set goal c/CALORIE_GOAL`
* Set daily water goal: `set goal w/WATER_GOAL`
* View goals: `show g`
* Log exercise: `log e/EXERCISE_NAME d/DURATION`
* View exercises: `list exercises`
* Help: `help`
* Find: `find "keyword"`
* Delete items from list: `delete "Quanity of water/Food name`
* List calorie and water intake: `list`
* Clear: `clear`
