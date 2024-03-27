# Developer Guide

## Acknowledgements

Our team has referenced [Address Book (Level-3)](https://github.com/se-edu/addressbook-level3) 
referenced for their [User Guide (UG)](https://se-education.org/addressbook-level3/UserGuide.html)
and [Developer Guide (DG)](https://se-education.org/addressbook-level3/DeveloperGuide.html) to better structure our own Developer Guide.

## Introductiton

The aim of this guide is to provide an explanation for all the functions and processing of information in PulsePilot. This is to enable any technical readers to get a detailed understanding of the application's internals.

This guide will include UML diagrams to better each component of our product. 

## Design

### Overview of Components

`Main`

- When PulsePilot is launched, it creates an instance of `PulsePilot`.

`PulsePilot`

- Upon creation, it initialises a `LogFile` and `DataFile` object to create log and data files for the bot.
- The `initialiseBot()` function is called to retrieve the user's name. 

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


### How t