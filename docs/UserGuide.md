# User Guide

## Introduction

PlaNUS is a Command Line Interface (CLI) desktop app for students majoring under the Faculty of Engineering in NUS.
It serves as a centralized platform for our target user to manage their past, current, and future courses.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `PlaNUS.jar` from [here](https://github.com/AY2324S2-CS2113-W12-1/tp/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar PlaNUS.jar`
   command to run the application.
4. Type the command in the command box and press Enter to execute it.

## Features 

> Notes about the command format:
> - Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `add course COURSE_CODE`, `COURSE_CODE` is a parameter which can be used as `add course CFG2101`.
> - Items in curly braces are optional.
e.g. `view {y/YEAR} {t/TERM}` can be used as `view` or `view y/1`.

<br>

### Saving the data
App data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

<br>

### Viewing help: `help`
Shows available commands to be given, along with examples.

Format: `help`

<br>

### Initializing the course plan: `init`
Initializes the course plan with a recommended course plan from the faculty 
given the major name (in short form) as input.

Format: `init MAJOR_NAME`
- MAJOR_NAME represents the major undertaken by the student. It must be a valid major (in short form) 
  under Faculty of Engineering.

Valid MAJOR_NAME:
- BME: Biomedical Engineering
- CEG: Computer Engineering
- ChBE: Chemical and Biomolecular Engineering
- CVE: Civil Engineering
- EE: Electrical Engineering
- ESP: Engineering Science Programme
- EVE: Environmental Engineering
- ISE: Industrial Systems Engineering
- ME: Mechanical Engineering
- MSE: Materials Science and Engineering

Example of usage: `init CEG`

<br>

### Displaying the recommended schedules: `display`
Displays the recommended schedule of the desired major without overwriting the user's timetable.
Allows user to check recommended schedules without extra data being saved.

Format: `display MAJOR_NAME`
- MAJOR_NAME must be a valid major (in short form) under Faculty of Engineering.

<br>

### Adding a course to course plan: `add course`
Adds a new course to the course plan at the term specified.

Format: `add course COURSE_CODE y/YEAR t/TERM`
- YEAR represents the year of study of the user. 
  It must be a positive integer from 1 to 6, which is the maximum candidature period.
- TERM must span from 1 to 4, with 1 and 2 representing the normal semesters, 
  while 3 and 4 represent the special terms.

Example of usage: `add course CS1010y/1t/1`

<br>

### Removing course from course plan: `rm course`
Removes an existing course from the course plan.

Format: `rm course COURSE_CODE`

Example of usage: `rm course CS1010`

<br>

### Viewing course plan: `view`
Shows the course plan, together with the number of modular credits.
If term is not specified, courses for the whole year will be shown.
If year is not specified, all courses will be shown.

Format: `view {y/YEAR} {t/TERM}`
- YEAR represents the year of study of the user.
  It must be a positive integer from 1 to 6, which is the maximum candidature period.
- TERM must span from 1 to 4, with 1 and 2 representing the normal semesters,
  while 3 and 4 represent the special terms.

Example of usage: `view y/1`

Outcome:
```
Year 1 Semester 1:
  CG1111A Engineering Principles & Practice I (MC: 4)
  CS1010 Programming Methodology (MC: 4)
  EG1311 Design and Make (MC: 4)
  MA1511 Engineering Calculus (MC: 2)
  MA1512 Differential Equation for Engineering (MC: 2)
  UE Unrestricted Elective (MC: 4)
Term MCs: 20
-----------------------------
Year 1 Semester 2:
  CG2111A Engineering Principles & Practice II (MC: 4)
  DTK1234 Design Thinking (MC: 4)
  MA1508E Linear Algebra for Engineering (MC: 4)
  PF1101 Fundamentals of Project Management (MC: 4)
  GEA1000 Quantitative Reasoning with Data (MC: 4)
Term MCs: 20
-----------------------------
Year MCs: 40
```

<br>

### Adding grades: `add grade`
Adds the grade to the corresponding course, provided that the course exists in the course plan.

Format: `add grade COURSE_CODE GRADE`
- GRADE is the letter grade, which can be A+/A/A-/B+/B/B-/C+/C/D+/D/F.

Example of usage: `add grade CS1010 A`

<br>

### Removing grades: `rm grade`
Removes the grade previously added to the corresponding course.

Format: `rm grade COURSE_CODE`

Example of usage: `rm grade CS1010`

<br>

### Changing grades: `change grade`
Changes the grade of the corresponding course.

Format: `change grade COURSE_CODE GRADE`
- GRADE is the letter grade, which can be A+/A/A-/B+/B/B-/C+/C/D+/D/F.

Example of usage: `change grade CS1010 A+`

<br>

### Checking grades: `check`
Shows grades for taken courses, along with the GPA calculated. 
If term is not specified, grades for the whole year will be shown.
If year is not specified, all grades will be shown.

Format: `check {y/YEAR} {t/TERM}`
- YEAR represents the year of study of the user.
  It must be a positive integer from 1 to 6, which is the maximum candidature period.
- TERM must span from 1 to 4, with 1 and 2 representing the normal semesters,
  while 3 and 4 represent the special terms.

Example of usage: `check y/1 t/2`

Outcome:
```
Year 1 Semester 2:
  CG2111A: A+
  DTK1234: B+
  MA1508E: A
  PF1101: A-
  GEA1000: A
Term GPA: 4.7
-----------------------------
```

<br>

### Exiting the program: `bye`
Exits the program.

Format: `bye`

<br>

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the `data` folder over to the new home directory where you intend to place the jar file.

## Command Summary
- Viewing help: `help`
- Initializing course plan: `init MAJOR_NAME`
- Adding a course to course plan: `add course COURSE_CODE y/YEAR t/TERM`
- Removing course from course plan: `rm course COURSE_CODE`
- Viewing course plan: `view {y/YEAR} {t/TERM}`
- Adding grades: `add grade COURSE_CODE GRADE`
- Removing grades: `rm grade COURSE_CODE`
- Changing grades: `change grade COURSE_CODE GRADE`
- Checking grades: `check {y/YEAR} {t/TERM}`
- Exiting the program: `bye`
