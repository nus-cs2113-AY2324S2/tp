# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
* To be added

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

* The student who wish to practise their mathematical problem solving ability.  
* The teachers who wish to provide students with some math problems and check their performance.  

### Value proposition

The product automates the generation of mathematical problems and their corresponding answers, thereby reducing the effort required by students to find practice problems.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|student who just started learning quadratic equations|use MathGenius to specifically generate problem sets|gain specialized practice on this topic|
|v1.0|a student who’s unfamiliar with mathematics terms|watch explanations and introductions to unfamiliar terms|strengthen understanding|
|v1.0|primary school teacher|wcreate course or topic-specific arithmetic questions for students|teaching student more effortlessly|
|v2.0|student who want to pracise solving foumula|generate various kinds of formula|practise the ability of solving math formula|

## Non-Functional Requirements
1. Should work on any mainstream OS as long as it has Java 11 or above installed.  
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.   
    
**{More to be add}**

## Glossary

* Mainstream OS: Windows, Linux, Unix, MacOS  
* Private contact detail: A contact detail that is not meant to be shared with others  

## Instructions for manual testing

{To be added}
