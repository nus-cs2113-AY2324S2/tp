# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Table of Contents
- [Developer Guide](#developer-guide)
  - [Acknowledgements](#acknowledgements)
  - [Table of Contents](#table-of-contents)
  - [Design](#design)
    - [Class Diagram](#class-diagram)
    - [Sequence Diagram](#sequence-diagram)
  - [Implementation](#implementation)
    - [UI and I/O](#ui-and-io)
    - [Commands](#commands)
    - [Storage](#storage)
      - [Storage File Structure](#storage-file-structure)
      - [`loadMembersData()`](#loadmembersdata)
      - [`loadTransactionsData()`](#loadtransactionsdata)
      - [`saveMembersData()`](#savemembersdata)
      - [`saveTransactionsData()`](#savetransactionsdata)
    - [Member and MemberList](#member-and-memberlist)
    - [Transaction and TransactionList](#transaction-and-transactionlist)
    - [PIN](#pin)
    - [Exceptions and Logging](#exceptions-and-logging)
  - [Product scope](#product-scope)
    - [Target user profile](#target-user-profile)
    - [Value proposition](#value-proposition)
  - [User Stories](#user-stories)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
  - [Instructions for manual testing](#instructions-for-manual-testing)
  - [Instructions for JUnit Testing](#instructions-for-junit-testing)
  - [Instructions for text-ui-testing](#instructions-for-text-ui-testing)
  - [Future Enhancements](#future-enhancements)

## Design

### Class Diagram

### Sequence Diagram

## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

Design and Implementation has been broken down into the subsequent sections, each tagged for ease of reference:

* [UI and I/O](#ui-and-io)
* [Commands](#commands)
* [Storage](#storage)
* [Member and MemberList](#member-and-memberlist)
* [Transaction and TransactionList](#transaction-and-transactionlist)
* [PIN](#pin)
* [Exceptions](#exceptions)

### UI and I/O

### Commands

### Storage

Storage operations are performed by the [`StorageHandler Class`](../src/main/java/longah/handler/StorageHandler.java).

Each group calls its own `StorageHandler` object such that they maintain distinct storage directories. To perform its tasks, the class primarily uses the methods `loadMembersData()`, `loadTransactionsData()`, `saveMembersData()` and `saveTransactionsData()`, with several other helper functions.

`loadMembersData()` and `loadTransactionsData()` have been compiled into the method `loadAllData()` while `saveMembersData()` and `saveTransactionsData()` have been compiled into the method `saveAllData()`

Key attributes part of the class include `membersFile` and `trnsactionsFile` which respectively contain the `File` representation of the directories to each of the storage files.

#### Storage File Structure

Each `StorageHandler` instance creates `members.txt` and `transactions.txt` in their respective folders.

* `members.txt` - NAME | BALANCE
* `transactions.txt` - LENDER NAME | BORROWER1 NAME | AMOUNT1 | BORROWER2 NAME...

#### `loadMembersData()`

Takes in `MemberList` as a key argument. Reads data from the groups' associated `members.txt` and unpacks it before inserting `Member` objects into `MemberList`.

#### `loadTransactionsData()`

Takes in `TransactionList` and `MemberList` as key arguments. Reads data from the groups' associated `transactions.txt` and unpacks it, checking if each member exists in `MemberList` before inserting `Transaction` objects into `TransactionList`.

#### `saveMembersData()`

Takes in `MemberList` as a key argument. Writes packaged data from each `Member` and saves it as a record in `members.txt`.

#### `saveTransactionsData()`

Takes in `TransactionList` as a key argument. Writes packaged data from each `Transaction` and saves it as a record in `transactions.txt`

### Member and MemberList

### Transaction and TransactionList

### PIN

### Exceptions and Logging

Exception cases are handled by the [`LongAhException Class`](../src/main/java/longah/exception/LongAhException.java).

The class makes use of enumerations [`ExceptionMessage`](../src/main/java/longah/exception/ExceptionMessage.java) and [`ExceptionType`](../src/main/java/longah/exception/ExceptionType.java) for its use.

Notably, `ExceptionMessage` stores the desired output message for each kind of potential error along with its associated `ExceptionType`. `ExceptionType` is used to define the manner in which the exception is logged.

Use of the class are demonstrated below, including throwing of an exception and printing the desired output message. This example covers the throwing exception due to invalid index.
```
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

// Throw an exception
throw new LongAhException(ExceptionMessage.INVALID_INDEX);

// Catch exception and output desired message
catch (LongAhException e) {
    LongAhException.printException(e);
}
```

Note: All exception calls are logged by default, either as WARNING or INFO depending on the `ExceptionType` classification tagged to the `ExceptionMessage`.

Logging is handled by the [`Logging Class`](../src/main/java/longah/handler/Logging.java).

Logging can be performed using the following lines of code:
```
import longah.handler.Logging;

// Create log of INFO Level
Logging.logInfo(message);

// Create log of WARNING Level
Logging.logWarning(message);
```

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

* Technical Requirements: Any mainstream OS, i.e. Windows, MacOS or Linux, with Java 11 installed. Instructions for downloading Java 11 can be found [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
* Project Scope Constraints: The application should only be used for tracking. It is not meant to be involved in any form of monetary transaction.
* Quality Requirements: The application should be able to be used effectively by a novice with little experience with CLIs.

## Glossary

* Lender - Member making payments on behalf of other members.
* Borrower - Members being paid for by the lender.
* Transaction - Payment made by ONE Lender on behalf of MULTIPLE Borrower, represented as a list of Subtransaction.
* Subtransaction - Subset of Transaction, consists of ONE Lender and ONE Borrower.
* Group - Discrete units each containing their respective lists of Member and Transaction.
* Separator - "|" has been used to denote separator within this document but within the Storage related classes, the ASCII Unit Separator as denoted by ASCII 31 is used instead. This is defined within `StorageHandler`.

## Instructions for manual testing

View the [User Guide](UserGuide.md) for the full list of UI commands and their related use case and expected outcomes.

## Instructions for JUnit Testing

JUnit tests are written in the [`test directory`](../src/test/java/longah/) and serve to test key methods part of the application.

## Instructions for text-ui-testing

Files relating to Text UI Testing can be found [here](../text-ui-test/).

When running tests on a Windows system, run the following command from the specificied directory:
```
./runtest.bat
```

When running tests on a UNIX-based system, run the following command from the specified directory:
```
./runtest.sh
```

Warning: Text UI Testing has been configured to clear all past data records to simulate a fresh application starting when the above commands are invoked. This WILL result in loss of data from previous runs.

## Future Enhancements

1. Inclusion of anomaly detection algorithms to flag out potentially erroneous transactions.
2. Adding of further details tagged to each transaction and allow for searching of transactions based on these details.
3. Create a reminder system to inform users of upcoming events or to warn them to settle payments.
