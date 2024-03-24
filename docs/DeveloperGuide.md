# Developer Guide

## Acknowledgements

1. Reference to AB-3 Developer Guide

- [Source](https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature)
- Used as template to structure this Developer Guide

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

---

### InputParsing component

---

The first and foremost component in the Student Tracking app is to ensure that the user parses in commands in a format that makes sense, which will modify the master list.

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
- Since the `add` command only allows the user to add a student to the list if there were no duplicate names present before, it will run the method "findStudentByName(ArrayList<Student> masterStudentList, String name)", to check if the name was already present in the masterlist.
- Assuming the name "wario" did not exist in the masterlist beforehand, the parser would grant the user's request to add the student to the masterlist.
- This `add wario` command is equivalent to having the user first type `add`, then after waiting fo the program to respond, type `wario`. In the latter scenario, the input parser deems that an argument was not present, and thus will ask the user to give the name of the student that they would like to add.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

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

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
