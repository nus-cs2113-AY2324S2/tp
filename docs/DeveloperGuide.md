# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
1. View all groceries added
   * First create a method in "Grocery" class that prints the grocery in a preferred format.\
     e.g., NAME, AMOUNT, EXPIRATION, PRICE.
   * Then create a method in "GroceryList" class that prints all the groceries in the list.
2. List the groceries by price in descending order
   * First, create a field in "Grocery" class that stores the cost of a grocery.
   * When adding a grocery, prompt the user to enter the cost.
   * Format the cost into 2 decimal places, remove the dollar sign and store it as a string.
   * Second, the grocery's cost accordingly before adding it into the list.
   * When setting the cost, convert the cost from String into Double.
   * Third, add method in "GroceryList" class to create a copy of the current grocery list, then sort the 
   new grocery list by price using lambda function. Reverse and print the new list.
3. Input category for each grocery added
   * In Grocery class, modified the Grocery constructor to accept the 'category' parameter.
   * In Parser class executeCommand method, modified the add command to prompt the user for the category of the grocery. Passed the category as a parameter when creating a new Grocery object.
   * In Ui class, added a new method promptForCategory to prompt the user for the category of the grocery.
   * In Grocery class, modified the printGrocery method to include the category information in the output string.
4. Input amount for each grocery added
   * In Grocery class, modified the Grocery constructor to accept the 'amount' parameter.
   * In Parser class executeCommand method, modified the add command to prompt the user for the amount of grocery. Passed the amount as a parameter when creating a new Grocery object.
   * In Ui class, added a new method promptForAmount to prompt the user for the amount of grocery.
   * In Grocery class, modified the printGrocery method to print different units of measurement for different categories.
5. Input the location of where each grocery is stored
   * In Grocery class, modified the Grocery class to include location (String) as an attribute.
   * In Grocery class, modified the Grocery constructor to accept the 'location' parameter.
   * In Grocery class, under printGrocery, added locationString to format location.
   * In Parser class executeCommand method, modified the add command to prompt the user for where the grocery is stored. Passed the location as a parameter when creating a new Grocery object.
   * In Ui class, added promptForLocation method to take in user input for location of the grocery.
   * In Ui class, modified the printGrocery method to print the 'location' of the grocery alongside the grocery name.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ...                          | So that I can ...|
|--------|---------|----------------------------------------|------------------|
|v1.0|new user|see instructions on how to use the app|refer to them when I forget how to use the application|
|v1.0|user| add groceries to the app               |manage all my groceries|
|v1.0|user| view all my groceries                  |know what I have bought|
|v1.0|user| delete the groceries from the list     |know which items are depleted|
|v1.0|user| add the amount of a grocery|keep track of the amount of that item I have|
|v1.0|user| add the expiration date of the grocery |keep track of when my items expire easily|
|v2.0|financially-aware user|add the cost of the groceries|know how much I am spending|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
