# User Guide for **Classify**

## Introduction

Classify is a student management system meant to assist administrative staff of private tuition centres. 

The product is capable of storing students, and generating outputs based on provided parameters, such as grade improvements and payment statuses.

Within our program, a student can be stored with their 
- Subjects taken
- Grades in those subjects
- Phone Number
- Gender
- Last Payment Dates 
- Remarks

We have determined these to be the attributes important to running a working tuition centre. Based on these attributes, our program also has functions to sort students based on grades, subjects, improvements since joining the centre.

## Quick Start

1. Ensure that you have Java __11 or above__ installed.
2. Download the provided jar file into an empty folder. 
3. Run it from the terminal using the following command:
```
java -jar ./Classify.jar
```

4. Run the command ```help``` within the program to get a quick view of the available commands.

## Features 

### - Add a student `add`

Initialises the process of adding in a student. 

### - View details of a student `view`

Prints out the details of a given student in the interface.

### - Edit details of a student `edit`

Initialises the process of editing both academic and non-academic details of a student.

### - Delete a student from the working list `delete`

Removes a student from the master list of students. 



# Usage
//todo add usage guide for our different commands

### Adding a student: `add`
Initialises an interface for adding a student and their relevant details to the database.

**to change**
Currently, no two students can have the same name within the system.

Format: `add` or `add NAME`

Depending on whether the user entered the student's name or not during the command, fields will be printed out in the terminal, awaiting a user input each time.

Example: 
``` 
add wario

Subject (enter nothing to skip): 
SUBJECT

Current marks out of 100 (blank to skip) : 
GRADE

Do you want to add another subject and grade? (yes/no) 
no

Please input a valid Phone number: 
NUMBER
.
.
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Plug out the corresponding drive from the computer you would like to transfer from, and connect it to either the SATA or m.2 port of the computer you would like to transfer to. Please make sure to reinstall any existing drivers to ensure maximum compatibility.

**Q**: How do I transfer my data to another computer **without** removing any components?

**A**: (parth this is for you to answer)

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
