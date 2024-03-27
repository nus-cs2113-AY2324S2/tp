# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Record Component - Design

API: ![Record.java](../src/main/java/seedu/duke/Record.java)

The Record component:

- a snapshot of a completed problem sets, including the individual problems, the date it was solved, the time taken to solve the problem set, and the accuracy of the attempt.
- \[Proposed\] records the specifics of each problem, including whether the answer is correct or not, for organized or filtered viewing of past records.

### Record Component - Implementation

- \[Proposed\] Store in each Record object a referential Test object for storing specifics of the attempted Problem Set.

### Storage Component - Design

API: ![Storage.java](../src/main/java/seedu/duke/Storage.java)

The Storage Component:

- Read / Write to external file at appropriate runtime to enable data persistence throughout multiple usages of the software.
- a unique and strict format for external file formatting for proper loading data as well as input file corruption detection.
- \[Proposed\] incorporate the UI, Parser component for proper user feedback regarding the save/load process

### Storage Component - Implementation

- Uses a list of Record objects to store all past attempts
- \[Proposed\] Use a hashing method to write / read properly all information of test object


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
