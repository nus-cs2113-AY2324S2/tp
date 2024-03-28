# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Table of Contents
- [Developer Guide](#developer-guide)
  - [Acknowledgements](#acknowledgements)
  - [Table of Contents](#table-of-contents)
  - [Design \& implementation](#design--implementation)
    - [UI and I/O](#ui-and-io)
    - [Storage](#storage)
    - [Member and MemberList](#member-and-memberlist)
    - [Transaction and TransactionList](#transaction-and-transactionlist)
    - [PIN](#pin)
    - [Class Diagram](#class-diagram)
    - [Sequence Diagram](#sequence-diagram)
  - [Product scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
  - [User Stories](#user-stories)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
  - [Instructions for manual testing](#instructions-for-manual-testing)

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### UI and I/O

### Storage

Storage is performed by the [`Storage`](../src/main/java/longah/handler/StorageHandler.java)

### Member and MemberList

### Transaction and TransactionList

### PIN

### Class Diagram

### Sequence Diagram

## Product scope

### Target user profile

Busy people with large transaction quantities among friends

### Value proposition

- Help users to find the least transactions solution to a large quantity of transactions
- Allow users to view past expenses of a group

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|to be able to find the least transactions needed to resolve amounts owed by various members of my various groups|-|
|v1.0|user|add transactions involving multiple people in a group|keep track of people involved and value of the transaction|
|v1.0|user|edit transactions|fix mistakes made when entering transactions|
|v1.0|user|delete transactions|clear erroneous transactions which I do not intend to keep|
|v1.0|user|keep a log of my data|retain memory of past transactions in past runs of the platform|
|v1.0|user|have easy access command to clear my pending debts|-|
|v1.0|user|be able to organise people into groups|minimise the occurence of being affected by typos|
|v1.0|user|add members to a group|add them to future transactions|
|v1.0|user|restart data for a group|reduce clutter of the application|
|v2.0|new user|view help commands|have an easy reference for commands while using the application|
|v2.0|user|enable the use of passwords for my application|prevent wrongful access to my records|
|v2.0|user|disable the password|have an easier time allowing people to view my records|
|v2.0|user|edit my password|change my password in case it has been compromised|
|v2.0|user|have my password be encrypted|ensure my password cannot be easily found out|
|v2.0|user|edit members in my group|change their nicknames which I store within the application|
|v2.0|user|delete current members|keep my groups neat and free of people who are no longer part of them|
|v2.0|user|create more groups|use the application for multiple groups of friends without data overlapping|
|v2.0|forgetful user|time of transactions to be saved|reference when each transaction were made|
|v2.0|user|search for specific transactions|find out information relating to the transaction in case I need to affect it|
|v2.1|advanced user|merge different groups together|combine groups which have large overlaps in members|
|v2.1|user|setup expenditure limits|be notified when someone have too large of a debt|
|v2.1|advanced user|create equal share transactions|add multiple people to a transaction without having to type their associated value to each of them|
|v2.1|advanced user|have command shortcuts|input commands faster|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* Lender - Member making payments on behalf of other members
* Borrower - Members being paid for by the lender
* Transaction - Payment made by ONE Lender on behalf of MULTIPLE Borrower
* Subtransaction - Subset of Transaction, 

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
