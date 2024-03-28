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
