# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### [Implemented] Account feature
#### Implementation
The Account feature in the BudgetBuddy application serves as a core component for tracking the user's current financial 
balance. This feature is primarily facilitated through the `Account` class, which encapsulates the balance attribute and
provides mechanisms to get and set the balance. The Account object interacts closely with transactions, being updated 
whenever transactions are added or deleted.

Looking ahead, users will have the flexibility to manage multiple accounts—such as Wallet, Savings, Checking, and 
Investment accounts—each encapsulated within the application's framework for seamless financial oversight. This 
expansion will provide a comprehensive view of diverse financial sources and enhance personal financial management 
within BudgetBuddy.

### [Implemented] List feature
#### Implementation
The list feature in the BudgetBuddy application allows users to view all their past transactions. This feature is
facilitated through the `UserInterface#printAllTransactions`, which loops through the entire ArrayList of transactions
and extract all the details of each transaction.

This feature will be further enhanced to include options to view transactions that the user is interested in only. For
example, transactions of the past week, past month, specified date range.

### [Implemented] Category feature

#### Description

The Category feature empowers users to effectively categorize transactions based on their preferences. When initiating a
new transaction through the `Add` command, users are prompted to select a category from a predefined list. This ensures
organized and streamlined transaction management.

#### Design and Implementation

The implementation of the Category feature revolves around the integration of a `category` attribute within each
transaction object. This attribute is defined as a member of the `Category` enum class.

Upon invoking the `Add` command, users are presented with a selection prompt featuring the available categories. User
input, typically in the form of a numerical identifier corresponding to a category within the enum class, facilitates
the assignment of the appropriate enum object to the transaction's category attribute.


### [Implemented] Remove transaction
#### Description
This method is used to remove a transaction from the list of transactions based on the transaction ID provided
by the user. After the transaction is removed, the account balance is updated accordingly and a message is 
displayed to the user indicating the success of the operation. This helps user to remove the transaction 
from the list they added by mistake or those transactions they no longer need to keep track off.

#### Parameters
1. String input: A string containing the user input, which should include the transaction ID to be removed.
2. Account account: The account object associated with the transaction list.

#### Design and Implementation
The method first validates the user input to ensure it's not empty or null. If the input is invalid, it throws
an EmptyArgumentException. Next, it extracts the transaction ID from the input and verifies its integrity as a
valid integer. If the ID is invalid, a NumberFormatException is thrown.

Once a valid transaction ID is obtained, the method calculates its corresponding index in the transactions 
ArrayList by subtracting 1 from the provided ID, as ArrayList indices start from 0 . It then verifies
if the calculated index falls within the bounds of the ArrayList. If the index is out of bounds, an 
InvalidIndexException is thrown.

Upon successful validation, the method removes the transaction at the calculated index from the transactions
ArrayList. Subsequently, it updates the account balance to reflect the removed transaction. Finally, it 
notifies the user of the successful removal along with displaying the details of the removed transaction.


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
