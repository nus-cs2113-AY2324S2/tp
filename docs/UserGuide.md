# Wellness360 User Guide

## Introduction

Wellness360 is a wellness app. It is meant for stressed Engineering Students who prefer CLI over GUI and want to keep track of their overall wellbeing. Our app offers comprehensive tracking and management tools, providing personalized support to alleviate stress and enhance overall well-being. It can help you take control of your mental and physical health effortlessly, so you can focus on your studies with peace of mind.

## Table of Contents
- [Wellness360 User Guide](#wellness360-user-guide)
    - [Starting Wellness360](#quick-start)
    - [Features](#features)
    - [Command Format](#features)
    - [Usage](#usage)
        - Reflection Manager
          - [`reflect get` - Get reflection questions](#getting-reflection-questions-reflect-get)
        - Habit Tracker
          - [`habit add` - Add a new habit](#add-a-new-habit-habit-add)
          - [`habit list` - List out all habits](#list-out-all-habits-habit-list)
          - [`habit update` - Update habit count after completing a habit](#update-habit-count-after-completing-a-habit-habit-update)
        - Sleep Tracker
          - [`sleep add` - Add a new sleep cycle](#add-a-new-sleep-cycle-sleep-add)
          - [`sleep list` - List out all sleep cycles](#list-out-all-sleep-cycles-sleep-list)
## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Wellness360` from [here](http://link.to/duke).
3. To start `Wellness360` Using the `jar` file, go to the containing folder for Wellness360. 
Then, on your terminal of choice, run:
```
-$ java -jar ip.jar
```

## Features

Wellness360 comes with many features for you to manage your well-being using CLI. The 5 main features are Habit 
Tracker, Sleep Tracker, Self Reflection, Focus Timer, and Fitness Motivation.

## Command Format
A command has the general structure:
```
<feature> <command> </arg1> <param1> </arg2> <param2>
```
- Arguments and parameters are not compulsory for all commands. Refer to respective feature commands for specifics.  
- Feature, command and arguments are case-sensitive. It is compulsory to use lower case.
- Parameters are not case-sensitive.

## Usage

### Getting reflection questions: `reflect get`
Generate a set of 5 random unique reflection questions from 
a question bank for users to view and reflect on. The questions come 
from 5 main categories: personal growth and development, relationships 
and social connections, career and professional development, health 
and well-being and lastly creativity and personal expression. 
The questions are meant to be randomized for users to think about 
various aspects of their lives. It is not guaranteed to get a 
question from each category. One or more questions may come from the 
same category.  

Format: 
```
reflect get
```

* The `reflect` and `get` are case-sensitive.
* Use lower casing for this command.

Example of usage: 
```
reflect get
```
Expected outcome:  
* Note that the questions are randomized.
```
________________________________________________________________________________________________________________
1. How do you overcome creative blocks or periods of stagnation?
2. Reflect on a recent professional success. What factors contributed to your achievement?
3. What are your biggest strengths, and how can you leverage them more effectively in your daily life?
4. How do you prioritize self-care and well-being in your daily life?
5. Reflect on a time when you took a creative risk. What did you learn from the experience?
________________________________________________________________________________________________________________
```

### Add a new habit: `habit add`
Allow the user to add new habits into the habit tracker.

Format:
```
habit add [HABIT_DESCRIPTION]
```

* The `habit` and `add` are case-sensitive.
* Use lower casing for this command.

Example of usage:
```
habit add vacuum and mop the floor
```
Expected outcome:
```
________________________________________________________________________________________________________________
Great! You have added a new habit:
  'vacuum and mop the floor' was successfully added!
________________________________________________________________________________________________________________
```

### List out all habits: `habit list`
Prints a list of all the habits that the user has added into the habit tracker.

Format:
```
habit list
```

* The `habit` and `list` are case-sensitive.
* Use lower casing for this command.

Example of usage:
```
habit list
```
Expected outcome:
```
________________________________________________________________________________________________________________
Here is the list of all your habits!
  1. vacuum and mop the floor [count: 2]
  2. complete leetcode daily question [count: 3]
________________________________________________________________________________________________________________
```

### Update habit count after completing a habit: `habit update`
Allow the user to update the number of times they have completed a habit. The user will be able to increase the 
count after they have completed the habit during the day. If the user has accidentally increased the count, 
they can decrease the count too.

Format:
```
habit update /id [HABIT_ID] /by [INCREMENT_COUNT]
```

* The `habit`, `list`, `id` and `by` are case-sensitive.
* Use lower casing for this command.

Example of usage (increasing count):
```
habit update /id 2 /by +1
```
* For increasing count, you can omit the positive sign `+` in front, and just type the command as `/by 1`

Expected outcome:
```
________________________________________________________________________________________________________________
Good Job! You have completed your habit!
The count for your habit has been updated:
  2. complete leetcode daily question [count: 4]
________________________________________________________________________________________________________________
```

Example of usage (decreasing count):
```
habit update /id 2 /by -2
```
* For decreasing count, you must include a negative sign `-` in front of the count.

Expected outcome:
```
________________________________________________________________________________________________________________
The count for your habit has been updated:
  2. complete leetcode daily question [count: 2]
________________________________________________________________________________________________________________
```

### Add a new sleep cycle: `sleep add`
Allow the user to add new sleep Cycles into the sleep tracker.

Format:
```
sleep add [HOURS_SLEPT] /date [DATE_SLEPT]
```

* The `sleep`, `/date` and `add` are case-sensitive.
* Use lower casing for this command.

Example of usage:
```
sleep add 7 /date 18/03/24
```
Expected outcome:
```
________________________________________________________________________________________________________________
--- SleepCycle for 18/03/24 has been added ---
________________________________________________________________________________________________________________
```

### List out all sleep cycles: `sleep list`
Prints a list of all the sleep cycles that the user has added into the sleep tracker.

Format:
```
sleep list
```

* The `sleep` and `list` are case-sensitive.
* Use lower casing for this command.

Example of usage:
```
sleep list
```
Expected outcome:
```
________________________________________________________________________________________________________________
Total hrs slept: 15.0
1. 27/01/12: 7.0
2. 30/01/12: 8.0
________________________________________________________________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
