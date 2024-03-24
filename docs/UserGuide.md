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
          - [`reflect get` - Get reflection questions](#get-reflection-questions-reflect-get)
          - [`reflect save` - Save favourite reflection question](#save-favourite-reflection-question-reflect-save)
          - [`reflect unsave` - Unsave favourite reflection question](#unsave-favourite-reflection-question-reflect-unsave)
          - [`reflect list` - View favourite reflection questions](#view-favourite-reflection-questions-reflect-list)
          - [`reflect help` - View reflection help menu](#view-reflection-help-menu-reflect-help)
        - Habit Tracker
          - [`habit add` - Add a new habit](#add-a-new-habit-habit-add)
          - [`habit list` - List out all habits](#list-out-all-habits-habit-list)
          - [`habit update` - Update habit count after completing a habit](#update-habit-count-after-completing-a-habit-habit-update)
        - Sleep Tracker
          - [`sleep add` - Add a new sleep cycle](#add-a-new-sleep-cycle-sleep-add)
          - [`sleep list` - List out all sleep cycles](#list-out-all-sleep-cycles-sleep-list)
        - Focus Timer
          - [`focus switch` - Switch focus timer mode](#switch-focus-timer-mode-focus-switch)
          - [`focus start` - Start a new focus timer](#start-a-new-focus-timer-focus-start)
          - [`focus stop` - Stop the current focus timer](#stop-the-current-focus-timer-focus-stop)
          - [`focus pause` - Pause the current focus timer](#pause-the-current-focus-timer-focus-pause)
          - [`focus resume` - Resume the current focus timer](#resume-the-current-focus-timer-focus-resume)
          - [`focus check` - Check time for focus timer](#check-time-for-focus-timer-focus-check)
          - [`focus set` - Set focus time duration](#set-focus-timer-duration-focus-set)
      - Fitness Motivator
          - [`fitness get` - Get a pre-loaded list of different exercises](#get-a-list-of-exercises-fitness-get)
          - [`fitness add` - Add new exercises into the list](#add-exercises-to-the-list-fitness-add)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Wellness360` from [here](http://link.to/duke).
3. To start `Wellness360` Using the `jar` file, go to the containing folder for Wellness360. 
Then, on your terminal of choice, run:
```
-$ java -jar tp.jar
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

### Get reflection questions: `reflect get`
Allows user to generate a set of 5 random unique reflection questions from 
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
Note that the questions are randomized.
```
________________________________________________________________________________________________________________
1. How do you overcome creative blocks or periods of stagnation?
2. Reflect on a recent professional success. What factors contributed to your achievement?
3. What are your biggest strengths, and how can you leverage them more effectively in your daily life?
4. How do you prioritize self-care and well-being in your daily life?
5. Reflect on a time when you took a creative risk. What did you learn from the experience?
________________________________________________________________________________________________________________
```

### Save favourite reflection question: `reflect save`
Allows user to save reflection question to favourites after viewing generated questions. This allows the user 
to review the question another time. The favourites list is stored in memory as a text file. 
New users will have an empty favourites list file on load, but existing users can load back favourite questions from
past sessions.

Format:
```
reflect save [QUESTION_ID]
```

* The `reflect` and `save` keywords are case-sensitive. Use lower casing for these 2 keywords.
* Questions that can be saved correspond to the most recent list of generated questions.
* Users need to generate questions before attempting to save it to favourites.
* `QUESTION_ID` only accepts integers between 1 and 5 inclusive.


Example of usage:
```
reflect save 1
```
Expected outcome:
```
________________________________________________________________________________________________________________
Got it. Added reflection question to favourites:
How do you overcome creative blocks or periods of stagnation?
________________________________________________________________________________________________________________
```

### Unsave favourite reflection question: `reflect unsave`
Allows user to unsave reflection question from favourites list if the question is no longer relevant to the user. The favourites list is stored in memory as a text file.
New users will have an empty favourites list file on load, but existing users can load back favourite questions from
past sessions.

Format:
```
reflect unsave [QUESTION_ID]
```

* The `reflect` and `unsave` keywords are case-sensitive. Use lower casing for these 2 keywords.
* Questions that can be unsaved correspond to the reflection questions favourites list.

Example of usage:
```
reflect unsave 1
```
Expected outcome:
```
________________________________________________________________________________________________________________
Got it. Unsaved reflection question from favourites:
How do you overcome creative blocks or periods of stagnation?
________________________________________________________________________________________________________________
```

### View favourite reflection questions: `reflect list`
Allow the user to view favourite reflection questions that have been saved.

Format:

```
reflect list
```
* The `reflect` and `list` keywords are case-sensitive. Use lower casing for these 2 keywords.  

Example of usage:
```
reflect list
```

Expected outcome:
```
________________________________________________________________________________________________________________
Favourites list:
1. How do you overcome creative blocks or periods of stagnation?
2. How do you prioritize self-care and well-being in your daily life?
3. Reflect on a time when you took a creative risk. What did you learn from the experience?
________________________________________________________________________________________________________________
```

### View reflection help menu: `reflect help`
Allows new users to check what commands are available for reflection feature and their formats.

Format:
```
reflect help
```
* The `reflect` and `help` keywords are case-sensitive. Use lower casing for these 2 keywords.

Example of usage:
```
reflect help
```

Expected outcome:
```
________________________________________________________________________________________________________________
Commands for reflection feature:
1. reflect get: Get 5 random reflection questions
2. reflect save <reflection_id>: Save reflection question by id to favourites list
3. reflect unsave <reflection_id>: Unsave reflection question by id from favourites list
4. reflect list: Retrieve questions from favourites list
5. reflect help: Get help menu for reflect commands
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
### Switch focus timer mode: `focus switch`
Focus timer offers 2 kind of timer for the user. Using `focus switch` command allows user to choose
between count up timer and count down timer.

Format:
~~~
focus switch
~~~
* The `focus` and `switch` are case-sensitive.
* Use lower casing for this command.

Expected outcome:
~~~
________________________________________________________________________________________________________________
Switched to Count down timer
________________________________________________________________________________________________________________
~~~
* Outcome depends on the current timer mode.
* `focus switch` will be disable if a timer is currently running.

### Start a new focus timer: `focus start`
Allow the user to start a new focus timer session. The user will be able
to start the timer whenever they want and the application will keep track of the time.
In addition, only 1 timer will run at a time, thus multiple uses of `focus start` is not allowed.

Format:
~~~
focus start
~~~
* The `focus` and `start` are case-sensitive.
* Use lower casing for this command.

Expected outcome:
* Count up timer.
~~~
________________________________________________________________________________________________________________
Your session has started. Time to grind!
________________________________________________________________________________________________________________
~~~

* Count down timer.
~~~
________________________________________________________________________________________________________________
Countdown timer started! 
Duration set: 1 minute(s) 0 second(s)
________________________________________________________________________________________________________________
~~~

### Stop the current focus timer: `focus stop`
> [!NOTE]
> Countdown timer will automatically stop when the duration expires.
>
Allow users to stop a timer that is currently running. The users will be able to see the total
time elapsed upon a successful stop.

Format:
~~~
focus stop
~~~
* The `focus` and `stop` are case-sensitive.
* Use lower casing for this command.

Expected outcome:
* Count up timer
~~~
________________________________________________________________________________________________________________
Your focus session has ended.
 Time spent: X hours, X minutes, XX seconds
To start a new session, use ‘focustimer start’ 
________________________________________________________________________________________________________________
~~~

* Count down timer
* Timer will automatically stop if the duration expires. However, users will still be able to stop it manually
~~~
________________________________________________________________________________________________________________
5 seconds left
________________________________________________________________________________________________________________
________________________________________________________________________________________________________________
4 seconds left
________________________________________________________________________________________________________________
________________________________________________________________________________________________________________
3 seconds left
________________________________________________________________________________________________________________
________________________________________________________________________________________________________________
2 seconds left
________________________________________________________________________________________________________________
________________________________________________________________________________________________________________
1 seconds left
________________________________________________________________________________________________________________
________________________________________________________________________________________________________________
Count down timer completed!
________________________________________________________________________________________________________________
~~~

### Pause the current focus timer: `focus pause`
Allow users to pause the timer momentarily while the timer is running.

Format:
~~~
focus pause
~~~
* The `focus` and `pause` are case-sensitive.
* Use lower casing for this command.

Expected outcome:
* Count up timer
~~~
________________________________________________________________________________________________________________
Count up timer paused.
________________________________________________________________________________________________________________
~~~

* Count down timer
~~~
________________________________________________________________________________________________________________
Timer paused. 
Remaining time: 0 minutes 56 seconds
________________________________________________________________________________________________________________
~~~
### Resume the current focus timer: `focus resume`
Allow users to resume the paused timer.

Format:
~~~
focus resume
~~~
* The `focus` and `resume` are case-sensitive.
* Use lower casing for this command.

Expected outcome:
* Count up timer
~~~
________________________________________________________________________________________________________________
Count up timer resumed
________________________________________________________________________________________________________________
~~~

* Count down timer
~~~
________________________________________________________________________________________________________________
Countdown timer resumed.
________________________________________________________________________________________________________________
~~~

### Check time for focus timer: `focus check`
Allow users to check the total time elapsed or total time remaining, depending on the mode of the timer.

Format:
~~~
focus check
~~~
* The `focus` and `check` are case-sensitive.
* Use lower casing for this command.

Expected outcome:
* Count up timer
~~~
________________________________________________________________________________________________________________
Total time elapsed: 
0 hours, 0 minutes, 8 seconds
________________________________________________________________________________________________________________
~~~

* Count down timer
~~~
________________________________________________________________________________________________________________
Remaining time: 
0 minutes 54 seconds left.
________________________________________________________________________________________________________________
~~~

### Set focus timer duration: `focus set`
> Using `focus set` command only affects count down timer.
>

Allow users to set the desired countdown timer duration for the session.

Format:
~~~
focus set [minutes]
~~~
* The `focus` and `set` are case-sensitive.
* Use lower casing for this command.
* Input `minutes` must be in numerical form and can be more than 60.
* Example: *120 minutes implies 2 hours*

Example of usage:
~~~
focus set 10
~~~

Expected outcome:
~~~
________________________________________________________________________________________________________________
Countdown duration has been set to 10 minute(s)
________________________________________________________________________________________________________________

~~~

### Get a list of exercises: `fitness get`
Prints a list of 5 different exercises from 5 different types of exercises targeting different body parts.

Format:
```
fitness get
```

* The `fitness` and `get` are case-sensitive.
* Use lower casing for this command.
* The command will generate different exercises with each repeated command, but there should be one exercise from
  each type

Expected outcome:
```
________________________________________________________________________________________________________________
These are some of the exercises you can do!

1. Arms: Barbell Curls, 3 sets & 8 reps
2. Chest: Bench Press, 3 sets & 8 reps
3. Abs: Crunches, 5 sets & 20 reps
4. Back: Pull Ups, 3 sets & 6 reps
5. Legs: Leg Press, 3 sets & 8 reps

________________________________________________________________________________________________________________
```

### Add exercises to the list: `fitness add`
Allows the user to add their own exercises to the list.

Format:
```
fitness add [EXERCISE_TYPE], [EXERCISE_NAME], [NUMBER_OF_SETS], [NUMBER_OF_REPS]
```

* The `fitness` and `add` is case-sensitive.
* Use of commands between each parameter is required.

Example of usage (increasing count):
```
fitness add Arms, Tricep Dips, 8, 10
```

Expected outcome:
```
________________________________________________________________________________________________________________
I have added the following exercise into our list!
Arms: Tricep Dips, 8 sets & 10 reps
________________________________________________________________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
