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
# Categorising the different books by their genres
This functionality will allow the books to be segregated into different groups by their genres for improved tracking.  
This operation is a cross class method that:  
>1. Accesses the BookDetails class to access methods to categorise the books  
>2. The categories are saved directory to the individual books in the Book class  
>3. The Parser class will parse the command to obtain the specific index and genre given  

Below is an example usage:  
>Step 1: When the user inputs the command set-genre 1 Fantasy, the Parser class will split the command into an array   
of 2 to access the command set-genre  
>Step 2: The second part of the array is further split into 2 to access the index "1" and the genre message "Fantasy"  
>Step 3: The index and genre message is passed into the setBookGenreByIndex method in BookDetails to set the genre of 
the book indexed at 1.  



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
