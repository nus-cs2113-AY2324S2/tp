# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

Professional culinary practitioners, students who love to cook.
Basically anyone who cooks and want to keep track of his/her recipes!

### Value proposition

The user will be able to add, access, and list recipes.
He can also filter recipes based on ingredients, time required, dietary restrictions, etc.
The saved recipes will be loaded in a recipe text file for easy sharing with friends.

## User Stories

| Version | As a ... | I want to ...          | So that I can ...                                            |
|---------|----------|------------------------|--------------------------------------------------------------|
| v1.0    | new user | see usage instructions | refer to them when I forget how to use the application       |
| v1.0    | user     | add a recipe           | curate my own unique recipes for future use                  |
| v1.0    | user     | delete a recipe        | remove any outdated or unimportant recipes                   |
| v2.0    | user     | find a recipe by name  | locate a recipe without having to go through the entire list |
| v2.0    | foodie   | save and load recipes  | share recipes with friends conveniently                      |

## Non-Functional Requirements

{Give non-functional requirements}
Users can use the help function to understand all the available commands they can use

## Commands Glossary

* *add [NAME, MINUTES, KCALS, ALLERGIES, CATEGORY, URL]* - This is the command a user can call to add a recipe. 
* *help* - This shows the user all the available commands. 
* *list* - This lists out a users recipebook for them. 
* *delete* - This deletes a recipe at a given valid index. If not valid, it will return an error message.
* *find kw [KEYWORD]* - This finds recipes with a user-given keyword.
* *find date [YYYY-MM-DD]* - This finds recipes added on a user-given date. The date is auto-added when adding. 
* *exit* - This is the command to leave the program. 

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
