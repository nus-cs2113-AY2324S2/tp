# Developer Guide

## Acknowledgements

1. Reference to AB-3 Developer Guide

- [Source](https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature)
- Used as template to structure this Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

---

### Ui component

The `Ui` class handles message output for the user within the terminal.

The `UI`component,
- stores message output strings for user feedback
- holds methods for printing messages to be called by other classes, such as `InputParsing`.

#### Design considerations

Being a CLI application, UI/UX is minimal and user IO is confined within the terminal.

Hence, public methods within the`Ui` class are static for easy access by other classes, without the need to instantiate an instance of the `Ui` class to call.

### InputParsing component

This component ensures that the user parses in commands in a format that makes sense, which will modify the master list.

![InputParsingUML](./diagrams/src/InputParsing/InputParsing.png)

#### Design considerations
- The parser must be effecive in breaking down the user's input into its constitutent commands, with further breakdown if an associated argument is added.
- The parser should be quick and effective in understanding the user's input, with simple prompts given to help the user in correctly parsing the command through the input parser.
- Allowing the user to input optional arguments. For example, the user could type `view <student name>`, which takes in the "student name" as an optional argument. This is to increase the robustness of the program, which accounts for the two types of users, one who just types in `view`, and the other as formerly mentioned above. 
- Error handling must be intuitive for the user, so that appropriate error messages are produced if the user does not input a valid command. The error handling should also be robust, to account for the event a user is incapable in following instructions.

#### Implementation and rationale

The InputParsing class is designed to handle the considerations above by breaking down the input given by the user in a well structured process. Below is how the InputParsing class works:

1. Splitting User Input: The Parser would take on the user's input and split it into 2 parts, which is the command and the argument, if any.
   
2. Command recognition: Depending on the command and the argument given, if any, the parser would execute the command as per defined by the program. If the command and/or argument given is invalid/undefined, the input parser would generate a message to the user, informing them of the command's invalidity.

3. Argument extraction: Depending on the specific command, if an argument can be parsed into that command, the parser would understand the argument and execute the sub-command specified by the user. Should the argument be invalid, the user would be informed of the argument's invalidity.

Through structuring the InputParsing class in such a manner, the application ensures that the user input is correctly parsed into executable commands for the subsequent phases of the application.

The InputParsing class uses the method below to achieve its functionality:

- `public static void parseUserCommand(String[] userCommand, ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList, Scanner in)`
  
This method takes in the user's input as the userCommand, the master student list, a list of the recently deleted students and the next input as parameters, which then proceeds with executing the command as associated with the user's input.

Given below is an example of how a user may add a student to the data base. 

- Suppose the user types in the command `add wario`. The parser first splits the input into two, checking for the command and the argument.
- It then recognises the `add` command, which then further checks if there is an argument given.
- Since the `add` command only allows the user to add a student to the list if there were no duplicate names present before, it will run the method `findStudentByName(ArrayList<Student> masterStudentList, String name)`, to check if the name was already present in the masterlist.
- Assuming the name "wario" did not exist in the masterlist beforehand, the parser would grant the user's request to add the student to the masterlist.
- This `add wario` command is equivalent to having the user first type `add`, then after waiting fo the program to respond, type `wario`. In the latter scenario, the input parser deems that an argument was not present, and thus will ask the user to give the name of the student that they would like to add.


### Student Details

To facilitate the management of students within a tuition centre, it is imperative to have easy access and storage of important information regarding said personnel.

Seen below is an UML diagram of the relevant classes dealing with storage.

![DataStoringUML](./diagrams/src/DataStoring/DateStoring.png)

#### Design considerations

When building our implementation, we wanted our program to be able to easily access a student's details from a student object, and vice versa. 

#### Implementation and rationale

A `Student` is made up of a list of the subjects they are taking, along with relevant information one might need while managing their schedules and clienthood (maybe not gender, but who knows). Every `Student` object is stored in a public `static StudentList` that any class can call.

Storage of these details are handled via the `StudentsAttributes` class when the user package calls upon its methods. To ensure easy access of information from either a `Student` object, and be able to retrieve the `Student` parent when having access to an `StudentAttributes` object, we linked both `Student` and `StudentAttribute`.

Since both classes are referencing each other, it was easy for us to link it with the input parsing system, where we would just need a student's name (we have yet to account for repeat names; only showing a warning) to be able to edit their details.

We created a parent `Details` class as those are information not specifically related to students. It can thus be repurposed in future updates should we wish to expand this application into a personnel management system, which would include employees of the tuition centre.

However, our current implementation is not very secure as one can access every field of a `Student` object just by having access to it or the `StudentAttribute` object, which can be done via accessing the `static masterStudentList` variable.In future updates, we could possibly implement a Facade Pattern to better hide sensitive details.

---

## Product scope

### Target user profile

We aim to target private tuition centres with our product, specifically smaller ones without a good system in place to track the progress of their students.

### Value proposition

Classify serves as an attempt to modernise administrative tasks in education institutes, such as tuition centres or school environments.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

### Adding a student to the student list
1. Adding a student with a name and phone number only
   1. Prerequisites: View if a student with the name 'joe' exists by using the `view joe` command.
   2. Test case: `add joe` and when prompted for phone number enter `11111111`, while pressing enter to skip other optional fields.
   <br /> 
   Expected: `view joe` now shows the Student details of a student with Name: joe, Phone Number: 11111111. Other fields that were left blank will reflect 'Unknown' or for date fields, today's date.
   3. Test case: `add` and when prompted for Name, `joe`. `11111111` when prompted for phone number, press enter to skip other fields.
   <br />
   Expected: `view joe` shows the same results as when a student was added via `add joe`.
### Viewing a student's details
1. View a student who has been added to the student list
   1. Prerequisites: Add one student named 'joe' to the list with the `add` command
   2. Test case: `view joe`
   <br />
   Expected: Student's details shown correspond to the details input when `add` was used to add a student.
   3. Test case: `view dogman`
   <br />
   Expected: No details are displayed, an error message stating 'Student not found!' is shown.