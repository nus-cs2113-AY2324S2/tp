# User Guide

## Introduction

PulsePilot is a **desktop app for tracking health-related information, optimised for users via a Command Line Interface (CLI)**. If one can type fast, you can key in and track health-related information faster than traditional GUI applications installed on your phone or computer.

* [Quick Start](#quick-start)
* [Notes About Command Format](#notes-about-command-format)
* [Adding Data](#adding-workouts-new)
  * [Workout: Run](#workout-run)
  * [Workout: Gym](#workout-gym)
  * [Health: BMI](#health-bmi)
  * [Health: Period](#health-period)
* [List](#list)
* [Help](#help)
* [Exit](#exit)
* [Logging](#logging)
* [Saving Data](#saving-data)
* [Frequently Asked Questions (FAQ)](#faq)
* [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have the latest Java 11.
2. Download the latest `pulsepilot.jar`.
3. Copy the file to the folder you want to use as the home folder for PulsePilot.
4. Open a command terminal (either cmd.exe or bash), cd to the folder with `pulsepilot.jar` in it, and use `java -jar pulsepilot.jar` to run the application.
5. The welcome message for PulsePilot should be printed to the screen.
6. Type commands in the command line and press Enter to execute it. Using `help` and pressing Enter will print the help message.

```
____________________________________________________________
 _              _
|_)    |  _  _ |_) o  |  _ _|_
|  |_| | _> (/_|   |  | (_) |_
Engaging orbital thrusters...
PulsePilot on standby
____________________________________________________________
```

## Notes About Command Format

* Parameters in `UPPER_CASE` are the parameters to be **supplied by the user**.
* Parameters in square brackets are optional.
  * `[/d:DATE]` means that the `DATE` parameter is **optional**.
* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

## Adding Workouts: `new`

Adds new data to track to PulsePilot.

### Workout: Run

Adds a new Run workout to track. 

Format: `new /e:run /d:DISTANCE /t:TIME [/date:DATE]`

* All parameters must be provided in correct order as shown above.
* `DISTANCE` is a **2 decimal point positive number** (i.e. `15.24`) representing the distance ran.
* `TIME` is in `[HH]:MM:SS` format (i.e. `25:30`). The `HH` representing hours is **optional**.
* `DATE` is in `DD-MM-YYYY` format (i.e. `19-03-2024`).

Examples: `new /e:run /d:5.24 /t:25:23 /date:19-03-2024`

Expected Output:

```
TO BE ADDED.
```

### Workout: Gym

Adds a new gym session to track. 

Format: `new /e:gym /n:NUMBER_OF_STATIONS [/d:DATE]`

* All parameters must be provided in correct order as shown above.
* `NUMBER_OF_STATIONS` is a **positive integer**  representing the number of stations for one Gym session.
* `DATE` is in `DD-MM-YYYY` format (i.e. `19-03-2024`).

Examples: `new /e:gym /n:4 /d:19-03-2024`

Expected output:

```
TO BE ADDED.
```

#### Adding Gym Stations

Upon entry of the `new /e:gym` command, the bot will prompt for further details:

Format: `STATION_NAME /s:SET /r:REPS /w:WEIGHT`

* All parameters must be provided in correct order as shown above.
* `STATION_NAME` is a **string**  representing the name of the gym station.
* `SET` is a **positive integer**  representing the number of sets done for one station.
* `REPS` is a **positive integer**  representing the number of repetitions done for one station.
* `WEIGHT` is a **positive integer**  representing the weight used for one station.

Examples: `Bench Press /s:4 /r:10 /w:75`

Expected Output:

```
TO BE ADDED.
```

### Health: BMI

Calculates user's Body Mass Index (BMI).

Format: `/h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE`
* All parameters must be provided in correct order as shown above.
* `HEIGHT` is a **2 decimal point number in metres** (i.e. `1.71`) representing the user's height.
* `WEIGHT` is a **2 decimal point number in kilograms** (i.e. `60.50`) representing the user’s weight.
* `DATE` is in `DD-MM-YYYY` format (i.e. `19-03-2024`).

Examples:
* `/h:bmi /height:1.71 /weight:60.50 /date:19-03-2024`

Usage:
```
2024-03-19
Your BMI is 20.69
Great! You're within normal range.
```

### Health: Period

Tracks the start and end of user's menstrual cycle.

Format: `/h:period /start:START_DATE /end:END_DATE`

* All parameters must be provided in correct order as shown above.
* `START_DATE` is `DD-MM-YYYY` format (i.e. `19-03-2024`) representing the start of a cycle.
* `END_DATE` is a `DD-MM-YYYY` format (i.e. `19-03-2024`) representing the end of a cycle.

Examples:
* `/h:period /start:09/03/2022 /end:16/03/2022`

Usage:
```
Period Start: 2022-03-09 Period End: 2022-03-16
Period Length: 8 days
```

### List

To be added.

### Help

Prints the `help` message. 

Format: `help`

Expected output:

```
____________________________________________________________
Commands List:

list - prints out the List
help - procures command list
exit - terminates the bot
____________________________________________________________
bmi - calculates BMI
weight - save current weight
height - save current height
____________________________________________________________
bmi format: bmi *parameter*
____________________________________________________________
```

### Exit

### Logging

The latest logs are written to `pulsepilot_log.txt` once the bot exits. Each time the bot is run, the current `pulsepilot_log.txt` file is overwritten with the most recent logs. The logs record both info messages and any error messages.

### Saving Data

As of now, the bot does not write or read from any file. This feature will be implemented in v2.0. 

## FAQ

**1.** How do I transfer my data to another computer?

As of now, it is not possible to do so. This feature will be implemented in `v2.0`. 


## Command Summary

| Action | Format, Examples |
|--------|------------------|
| help   |                  |
| run    |                  |
| gym    |                  |
| bmi    |                  |


