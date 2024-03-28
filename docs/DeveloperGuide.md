# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Help menu feature
#### Implementation
The "help" feature is facilitated by the  `Help` class. 
It provides a static method `printHelp` to print out a guide on how to use the commands in the application.
`printHelp` can be used in the event the user issues an invalid command


### Add Member to Group feature
#### Implementation

The "Add Member to Group" feature is facilitated by the `Group` class. It provides methods to manage group membership and allows users to add new members to an existing group. The implementation of this feature is as follows:

The Group class maintains a list of members as a `private List<User>` field called `members`.

The `addMember(String memberName)` method is responsible for adding a new member to the group. It performs the following steps:

1. Checks if a user with the given `memberName` is already a member of the group using the `isMember(String memberName)` method. 
2. If the user is not a member, creates a new `User` object with the provided `memberName`.
3. Adds the new `User` object to the `members` list.
4. Prints a success message indicating that the member has been added to the group.

## Product scope
### Target user profile

{Describe the target user profile}
Our target users are people who share expenses with friends, family, roommates, or colleagues.
The application gives an accurate and simple way to represent unsettled debts between users and their friends

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
|---------|----------|---------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
