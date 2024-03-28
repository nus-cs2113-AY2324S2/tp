# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Help menu feature
#### Implementation
The "help" feature is facilitated by the  `Help` class. 
It provides a static method `printHelp` to print out a guide on how to use the commands in the application.
`printHelp` can be used in the event the user issues an invalid command


### Group Creation feature

#### Implementation

The "Group Creation" feature is facilitated by the `Group` class. It provides methods to create a new group and manage
group membership. The implementation of this feature is as follows:

The Group class maintains a list of members as a `private List<User>` field called `members`.

The `createGroup(String groupName)` method is responsible for creating a new group. It performs the following steps:

1. Checks if a group with the given `groupName` already exists using the `isGroup(String groupName)` method.
2. If the group does not exist, creates a new `Group` object with the provided `groupName`.
3. Prints a success message indicating that the group has been created.
4. Adds the new `Group` object to the `groups` list.
5. Returns the newly created `Group` object.
6. If the group already exists, prints an error message indicating that the group already exists.

### Add Member to Group feature

#### Implementation

The "Add Member to Group" feature is facilitated by the `Group` class. It provides methods to manage group membership
and allows users to add new members to an existing group. The implementation of this feature is as follows:

The Group class maintains a list of members as a `private List<User>` field called `members`.

The `addMember(String memberName)` method is responsible for adding a new member to the group. It performs the following
steps:

1. Checks if a user with the given `memberName` is already a member of the group using the `isMember(String memberName)`
   method.
2. If the user is not a member, creates a new `User` object with the provided `memberName`.
3. Adds the new `User` object to the `members` list.
4. Prints a success message indicating that the member has been added to the group.

### Expenses feature

#### Implementation

The Expenses feature is facilitated through the Expense class. It allows users to add a new Expense through creation of a new Expense object. Users can specify amount paid, the payee, and the members of the group involved in the transaction.
Additionally, it implements the following operations:
+ `Expenses#payer()` - Gives the name of the member who paid for the expense
+ `Expenses#totalAmount()` - Returns the total amount of the expense
+ `Expenses#payees()` - Returns all the members involved in the transaction

These operations are exposed in the Expense class through the `getPayerName()`, `getTotalAmount()`, and `getPayees()` functions respectively.

## Product scope

### Target user profile

{Describe the target user profile}
Our target users are people who share expenses with friends, family, roommates, or colleagues.
The application gives an accurate and simple way to represent unsettled debts between users and their friends

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories


| Version | As a ... | I want to ...                                                  | So that I can ...                                           |
|---------|----------|----------------------------------------------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions                                         | refer to them when I forget how to use the application      |
| v1.0    | user     | add a new expense with description, amount, and users involved | split the expense equally                                   |
| v1.0    | user     | create a new group                                             | split expenses with different groups                        |
| v1.0    | user     | list all expenses within a group                               | see recent transactions                                     |
| v1.0    | user     | check how much I owe each member in the group                  | keep track of my debts                                      |
| v2.0    | user     | find a to-do item by name                                      | locate a to-do without having to go through the entire list |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
