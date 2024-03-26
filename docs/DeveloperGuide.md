# Developer Guide - Voyagers

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

The Voyagers application is designed as a command-line interface 
(CLI) tool for managing travel trips. 
It is implemented in Java and follows a modular architecture to promote scalability and maintainability.

## Setting Up, Getting Started

Run the JAR File, then follow the prompts through the command-line interface. Refer to this guide for syntax.

## Architecture

{UML Diagram of high-level app architecture}
Given below is a quick overview of main components and how they interact with each other.

Voyagers {...}  
Parser {...}  
Commands {...}  
Storage {...}  
Ui {...}  
Trip {...}  
Bill {...}

How the architecture components interact with each other
{Sequence Diagram}

## Bill
Bill implements Payable, an interface to be used a few times in this project to cover 
actions involving payment. It is dependent on the Profile and Trip classes, and Parser
is dependent on the Bill class. We will include this in the UML diagrams to be included soon.
Currently, the supported public methods can add and remove Profiles, pay the bill, change
percentages that different people owe, and set and return various attributes. This is still
not fully fleshed out, as we will add more features and better functionality in subsequent
releases.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|-|
|v1.0|new user|add a new trip|plan my upcoming travel itinerary|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
