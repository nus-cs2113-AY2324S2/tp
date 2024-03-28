# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

ical4J Library: https://www.ical4j.org/

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Storage component
**API** : [Storage.java](https://github.com/AY2324S2-CS2113-W13-2/tp/blob/master/src/main/java/storage/Storage.java)

The 'storage' component:
* Reads tasks from the formatted tasks.txt file and appends to task hashmap.
* Identifies unique tasks stored in task hashmap, parses and writes to tasks.txt file
* Handles exception if tasks.txt is in corrupted format

### Exporting .ics File Component

The 'ics' component:
* Exports the tasks in the task hashmap to a .ics file that can be imported into calendar applications
* Import tasks from external .ics file into the task hashmap

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
