# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

Reference to AB-3 Developer Guide
* [Source URL](https://se-education.org/addressbook-level3/DeveloperGuide.html#documentation-logging-testing-configuration-dev-ops)
* Used as template to structure this DeveloperGuide
* Reference to AB-3 diagrams code

Reference to AB-3 diagrams code
* [Source URL](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams)
* Used as reference to understand PlantUML syntax


## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Categorising the different books by their genres
This functionality enables the categorization of books into distinct groups based on their genres, facilitating better 
organization and tracking. The implementation of this feature involves interactions across multiple classes within the 
system. 
#### Overview
The process of categorizing books by genre is a multi-step operation that involves the following classes:
1. 'BookDetails': This class contains methods that handle the categorization of books.
2. 'Book': Individual book objects are updated with their respective genres directly in this class.
3. 'Parser': This class is responsible for parsing the input command to extract the specific index and genre.

#### Detailed Workflow
Below is an example usage:  
Here’s a step-by-step guide on how the feature works:
Step 1: The user initiates the process by inputting a command like set-genre 1 Fantasy. Here, the Parser class plays a 
crucial role as it interprets the command and segregates it into a manageable array. The first part of this array holds 
the command set-genre, which indicates the action to be executed.

Step 2: The second segment of the input string is then further dissected into two components, which are the index (1) 
and the genre (Fantasy). This step is essential for identifying the specific book and the genre it needs to be 
associated with.

Step 3: With the index and genre clearly identified, these parameters are passed to the setBookGenreByIndex method 
within the BookDetails class. This method is then responsible for assigning the specified genre to the book located at 
the given index.



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
