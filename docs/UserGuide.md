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

### - Produce a list of all the students who takes a subject `view_subject`

Displays a list of all the students with that associated subject taken in the tuition centre.

### - Sort the list lexicograpically, from A to Z `sort_name`

Sorts the masterlist of students from A to Z. When the user types list, it will generate the updated sorted list.

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
100

Enter Classes Attended (blank to skip):
1

Do you want to add another subject and grade? (yes/no) 
no

Please input a valid Phone number: 
NUMBER
.
.
```

### - View details of a student `view`
Views a student details. Allows the user to do view <student> or just view.

Example:
view wario

Student details: 
Name: wario
Phone Number: 12345678
Gender: male
Last Payment Date: 12/12/2023
Remarks: Unknown
Subject: subject
Current marks out of 100: 100.0
Classes Attended: 1

### - Edit details of a student `edit`


### - Delete a student from the working list `delete`
Deletes the student from the list. Allows the user to do delete <student> or just delete.

Example:
delete

Enter student name: 
wario
Student removed successfully!

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Plug out the corresponding drive from the computer you would like to transfer from, and connect it to either the SATA or m.2 port of the computer you would like to transfer to. Please make sure to reinstall any existing drivers to ensure maximum compatibility.

**Q**: How do I transfer my data to another computer **without** removing any components?

**A**: (parth this is for you to answer)

## Command Summary

### `add <student>` 
Adds a student into the list. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `list` 
Displays the list of students.

### `view <student>` 
Views a students details. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `delete <student>`
Deletes the student from the list. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `edit <student>` 
Allows the user to edit the details of the student. If the name of the student is not specified in the initial command, the program will prompt the user for the name.

### `sort_name` 
Sorts the list lexicographically.

### `view_subject <subject>` 
Lists all the students associated with that subject. If the user specifies the subject in the initial command, the list will be generated and the command will be terminated. If the user does not specify the subject, the programme will continuously prompt the user for a subject, until they choose to terminate the command.

### `help` 
Generates the list of commands.
