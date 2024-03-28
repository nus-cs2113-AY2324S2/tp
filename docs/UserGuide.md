# User Guide

## Introduction

PulsePilot is a **desktop app for tracking health-related information, optimised for users via a Command Line Interface (CLI)**. If one can type fast, you can key in and track health-related information faster than traditional GUI applications installed on your phone or computer.

* [Quick Start](#quick-start)
* [Notes About Command Format](#notes-about-command-format)
* [Commands](#commands)
  * [Workout: Run](#workout-run)
  * [Workout: Gym](#workout-gym)
    * [Adding Gym Stations](#adding-gym-stations)
  * [Health: BMI](#health-bmi)
  * [Health: Period](#health-period)
  * [History](#history)
  * [Latest](#latest)
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

The bot will prompt you for your name before starting. 

```
____________________________________________________________
 _              _
|_)    |  _  _ |_) o  |  _ _|_
|  |_| | _> (/_|   |  | (_) |_
Engaging orbital thrusters...
PulsePilot on standby
____________________________________________________________
What is your name, voyager?
____________________________________________________________
Jason
Welcome aboard, Captain Jason
____________________________________________________________
Tips: Enter 'help' to view the pilot manual!
Initiating FTL jump sequence...
FTL jump completed.
Terminal primed. Command inputs are now accepted...
____________________________________________________________
```

## Notes About Command Format

* Parameters in `UPPER_CASE` are the parameters to be **supplied by the user**.
* Parameters in square brackets are optional.
  * `[/d:DATE]` means that the `DATE` parameter is **optional**.
* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

## Commands

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
new /e:run /d:5.24 /t:25:23 /date:19-03-2024
____________________________________________________________
Successfully added a new run session
Type	Time		Distance	Pace		Date
run 	25:23		5.24		4:51/km		2024-03-19
____________________________________________________________
```

### Workout: Gym

Adds a new gym session to track. 

Format: `new /e:gym /n:NUMBER_OF_STATIONS`

* All parameters must be provided in correct order as shown above.
* `NUMBER_OF_STATIONS` is a **positive integer**  representing the number of stations for one Gym session.

Examples: `new /e:gym /n:2`

#### Adding Gym Stations

Upon entry of the `new /e:gym` command, the bot will prompt for further details for each station done:

Format: `STATION_NAME /s:SET /r:REPS /w:WEIGHT`

* All parameters must be provided in correct order as shown above.
* `STATION_NAME` is a **string**  representing the name of the gym station.
* `SET` is a **positive integer**  representing the number of sets done for one station.
* `REPS` is a **positive integer**  representing the number of repetitions done for one station.
* `WEIGHT` is a **positive integer**  representing the weight used for one station.

Examples: `Bench Press /s:4 /r:10 /w:75`

Expected Output:
```
new /e:gym /n:2
____________________________________________________________
Please enter the details of station 1. (Format: [name of exercise:string] /s:[sets:number] /r:[reps:number] /w:[weights:number])
____________________________________________________________
Bench Press /s:4 /r:10 /w:5
____________________________________________________________
Please enter the details of station 2. (Format: [name of exercise:string] /s:[sets:number] /r:[reps:number] /w:[weights:number])
____________________________________________________________
Squat /s:4 /r:5 /w:100
____________________________________________________________
Successfully added a new gym session
Bench Press: 4 sets of 10 reps at 5 KG
Squat: 4 sets of 5 reps at 100 KG
____________________________________________________________
```

### Health: BMI

Calculates user's Body Mass Index (BMI).

Format: `health /h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE`
* All parameters must be provided in correct order as shown above.
* `HEIGHT` is a **2 decimal point number in metres** (i.e. `1.71`) representing the user's height.
* `WEIGHT` is a **2 decimal point number in kilograms** (i.e. `60.50`) representing the user’s weight.
* `DATE` is in `DD-MM-YYYY` format (i.e. `19-03-2024`).

Examples:
* `health /h:bmi /height:1.70 /weight:75.42 /date:19-03-2024`

Expected Output:
```
health /h:bmi /height:1.70 /weight:75.42 /date:19-03-2024
____________________________________________________________
Added: bmi | 1.70 | 75.42 | 19-03-2024
2024-03-19
Your BMI is 26.1
You're overweight.
____________________________________________________________
```

### Health: Period

Tracks the start and end of user's menstrual cycle.

Format: `health /h:period /start:START_DATE /end:END_DATE`

* All parameters must be provided in correct order as shown above.
* `START_DATE` is `DD-MM-YYYY` format (i.e. `19-03-2024`) representing the start of a cycle.
* `END_DATE` is a `DD-MM-YYYY` format (i.e. `19-03-2024`) representing the end of a cycle.

Examples:
* `health /h:period /start:09-03-2022 /end:16-03-2022`

Expected Output:
```
health /h:period /start:09-03-2022 /end:16-03-2022
____________________________________________________________
Added: period | 09-03-2022 | 16-03-2022
Period Start: 2022-03-09 Period End: 2022-03-16
Period Length: 8 days
____________________________________________________________
```

### History

Prints all tracked instances of `run`, `gym`, `bmi` or `period`.

Format: `history /view:TYPE`

* `TYPE` is either `run`, `gym`, `bmi` or `period`.

Examples:
* `history /view:run`

Expected Output:

```
history /view:run
____________________________________________________________
Index		Type	Time		Distance	Pace		Date
1.		run 	25:00		5.00		5:00/km		2024-03-17
2.		run 	25:23		5.24		4:51/km		2024-03-18
3.		run 	25:23		5.24		4:51/km		2024-03-19
____________________________________________________________
```

### Latest

Prints the latest instance of `run`, `gym`, `bmi` or `period`.

Format: `latest /view:TYPE`

* `TYPE` is either `run`, `gym`, `bmi` or `period`.

Examples:
* `latest /view:run`

Expected Output:

```
latest /view:period
____________________________________________________________
Period Start: 2022-03-09 Period End: 2022-03-16
Period Length: 8 days
____________________________________________________________
```

### Help

Prints the `help` message. 

Format: `help`

Expected output:

```
help
____________________________________________________________
Commands List:

new /e:run /d:DISTANCE /t:TIME [/date:DATE] - Add a new run
new /e:gym /n:NUMBER_OF_STATIONS [/date:DATE] - Add a new gym workout
health /h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE - Add new BMI data
health /h:period /start:START_DATE /end:END_DATE - Add new period data
history /view:[run/gym/bmi/period] - Show history of runs/gyms/bmi records/periods tracked
latest /view:[run/gym/bmi/period] - Show history of runs/gyms/bmi records/periods tracked
help - Show this help message
exit - Exit the program
____________________________________________________________
```

### Exit

Exits the bot gracefully.

Format: `exit`

Expected Output:

```
exit
Initiating PulsePilot landing sequence...
____________________________________________________________
PulsePilot successful touchdown
See you soon, Captain!
____________________________________________________________
```

## Logging

The latest logs are written to `pulsepilot_log.txt` once the bot exits. Each time the bot is run, the current `pulsepilot_log.txt` file is overwritten with the most recent logs. The logs record both info messages and any error messages.

## Saving Data

As of now, the bot does not write or read from any file. This feature will be implemented in v2.0. 

## FAQ

**1.** How do I transfer my data to another computer?

As of now, it is not possible to do so. This feature will be implemented in `v2.0`. 

## Command Summary

| Action       | Format, Examples                                                                                                                     |
|--------------|--------------------------------------------------------------------------------------------------------------------------------------|
| Print help   | `help`                                                                                                                               |
| Add new run  | `new /e:run /d:DISTANCE /t:TIME [/date:DATE]`<br/>Example: `new /e:run /d:5.24 /t:25:23 /date:19-03-2024`                            |
| Add gym      | `new /e:gym /n:NUMBER_OF_STATIONS`<br/>Example:`new /e:gym /n:4`                                                                     |
| Track BMI    | `health /h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE` <br/>Example:   `health /h:bmi /height:1.70 /weight:75.42 /date:19-03-2024` |
| Track Period | `health /h:period /start:START_DATE /end:END_DATE` <br/>Example:   `health /h:period /start:09-03-2022 /end:16-03-2022`              |
| View history | `history /view:TYPE` <br/>Example:   `history /view:run`                                                                             |
| View latest  | `latest /view:TYPE` <br/>Example:   `latest /view:bmi`                                                                               |
| Exit bot     | `exit`                                                                                                                               |

