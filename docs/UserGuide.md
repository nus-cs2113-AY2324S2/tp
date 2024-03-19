# User Guide

## Introduction

PulsePilot is a **desktop app for tracking health-related information, optimised for users via a Command Line Interface (CLI)**. If one can type fast, you can key in and track health-related information faster than traditional GUI applications installed on your phone or computer.

* [Quick Start](#quick-start)
* [Features](#features)
    - [Notes About Command Format](#notes-about-command-format)
    - [General](#general)
      - [List](#list)
      - [Help](#help)
      - [Exit](#exit)
      - [Saving Data](#saving-data)
    - [Workouts](#workouts)
      - [Run](#run)
      - [Gym](#gym)
    - [Health](#health)
      - [BMI](#bmi)
      - [Menstrual Cycle](#menstrual-cycle)
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
Include banner from bot here.
```

## Features

### Notes About Command Format

* Words in UPPER_CASE are the parameters to be supplied by the user.
    * TO ADD EXAMPLES FOR THE SPECIFIC COMMANDS.
* Items in square brackets are optional.
    * TO ADD EXAMPLES FOR THE SPECIFIC COMMANDS.
* Extraneous parameters for commands that do not take in parameters (such as help) will be ignored.
    * e.g. If the command entered is `help me`, it will be interpreted as `help`.
* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
* Items with `...`  after them can be used multiple times, not including zero times.

### General

#### List

#### Help

#### Exit

#### Saving Data


### Workouts

Tracks either a gym or exercise workout (to be changed).

#### Gym

Add New Gym: `gym`

Adds a new gym exercise to the exercise tracker.

Format: `gym /s:Bench Press...` (to be changed)
* **At least one station** has to be provided. Can include as many as the user wants.
* Parameters must be **in order**.
* `STATION` refers to the station name (i.e. `Bench Press`, `Deadlift`)
* `WEIGHT` is a **1 decimal point** number in kilograms (i.e. `85.5`) representing the amount of weight carried for the station.
* `SETS` is a **positive integer more than 1** representing the number of sets done for the station.
* `REPETITIONS` is a **positive integer more than 1** representing the number of repetitions done per set.
* `DATE` is in `DD/MM/YYYY` format (i.e. `05/12/2023`).

Example:

Expected Output:

```
Include the expected output of the bot here. 
```

#### Run

To be added. 

### Health

Tracks health of the user.

#### BMI

Calculates user's Body Mass Index (BMI).

Format: `/h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE'
* All parameters must be provided in correct order as shown above.
* `HEIGHT` is a **2 decimal point number in metres** (i.e. 1.71) representing the user's height.
* `WEIGHT` is a **2 decimal point number in kilograms** (i.e. 60.50) representing the user’s weight.
* `DATE` is in `DD-MM-YYYY` format (i.e. `19-03-2024`).

Examples: 
* /h:bmi /height:1.71 /weight:60.50 /date:19-03-2024

Usage:
```
2024-03-19
Your BMI is 20.69
Great! You're within normal range.
```

#### Menstrual Cycle

## FAQ

**1.** How do I transfer my data to another computer?

Ensure that you have the `data.txt` file in the same directory as `pulsepilot.jar`. 

TO ADD MORE. 

## Command Summary

| Action | Format, Examples |
|--------|------------------|
| help   |                  |
| run    |                  |
| gym    |                  |
| bmi    |                  |


