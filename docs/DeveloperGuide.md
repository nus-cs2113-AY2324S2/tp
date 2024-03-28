# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### 1. View all groceries added
   * First create a method in "Grocery" class that prints the grocery in a preferred format.\
     e.g., NAME, AMOUNT, EXPIRATION, PRICE.
   * Then create a method in "GroceryList" class that prints all the groceries in the list.


### 2. List the groceries by price in descending order
   * First, create a field in "Grocery" class that stores the cost of a grocery.
   * When adding a grocery, prompt the user to enter the cost.
   * Format the cost into 2 decimal places, remove the dollar sign and store it as a string.
   * Second, the grocery's cost accordingly before adding it into the list.
   * When setting the cost, convert the cost from String into Double.
   * Third, add method in "GroceryList" class to create a copy of the current grocery list, then sort the 
   new grocery list by price using lambda function. Reverse and print the new list.


### 3. Input category for each grocery added
   * In Grocery class, modified the Grocery constructor to accept the 'category' parameter.
   * In Parser class executeCommand method, modified the add command to prompt the user for the category of the grocery. Passed the category as a parameter when creating a new Grocery object.
   * In Ui class, added a new method promptForCategory to prompt the user for the category of the grocery.
   * In Grocery class, modified the printGrocery method to include the category information in the output string.


### 4. Input amount for each grocery added
   * In Grocery class, modified the Grocery constructor to accept the 'amount' parameter.
   * In Parser class executeCommand method, modified the add command to prompt the user for the amount of grocery. Passed the amount as a parameter when creating a new Grocery object.
   * In Ui class, added a new method promptForAmount to prompt the user for the amount of grocery.
   * In Grocery class, modified the printGrocery method to print different units of measurement for different categories.


### 5. Input the location of where each grocery is stored
   * In Grocery class, modified the Grocery class to include location (String) as an attribute.
   * In Grocery class, modified the Grocery constructor to accept the 'location' parameter.
   * In Grocery class, under printGrocery, added locationString to format location.
   * In Parser class executeCommand method, modified the add command to prompt the user for where the grocery is stored. Passed the location as a parameter when creating a new Grocery object.
   * In Ui class, added promptForLocation method to take in user input for location of the grocery.
   * In Ui class, modified the printGrocery method to print the 'location' of the grocery alongside the grocery name.
   * Alternative considered: Can possibly add location as enumeration however different people might store groceries in different places thus better to set as String so that user is free to input location details however specific they want.

### 6. Edit grocery amount after using a grocery
   * A `Grocery` stores its `amount` as an attribute. All `Grocery` objects are then stored in an ArrayList in `GroceryList`, which entirely handles the editing of the `amount`.

### 7. Input expiration date of each grocery when added
   * In Grocery class, the expiration field in the Grocery class was changed from a String to a LocalDate to standardize date handling.
   * In Grocery class, the setExpiration method was updated to accept a String input, convert it to a LocalDate using a specified format ("yyyy-MM-dd"), and then store this date.
   * In UI class, the UI now includes a multi-step process to prompt the user for the year, month, and day of the grocery item's expiration date. This process ensures that the date is captured in a user-friendly manner and stored accurately.
   * In GroceryList class, a new method, sortByExpiration, was added to allow sorting the list of groceries by their expiration dates in ascending order. This method utilizes the Collections.sort method with a lambda expression comparing the expiration dates of Grocery items.

### 8. Editing expiration date after it is added
   * In GroceryList class, modified the editExpiration method to parse String into localdate.


![Grocery (showing amount) and GroceryList class diagram](./diagrams/GroceryAmtGroceryList.png)

   * `GroceryList#editAmount()` is used to either decrease or directly set the `amount` of a `Grocery`. It takes in 2 parameters:
      1. details: String — User input read from `Scanner`.
      2. use: boolean — `true` decreases the `amount`, while `false` directly sets it.
   * To edit the `amount` after using a `Grocery`, the user inputs `use GROCERY a/AMOUNT`. 
Our app then executes `GroceryList#editAmount()` with parameter `use = true`, as illustrated by the following sequence diagram.

![useAmt sequence diagram](./diagrams/useAmt.png)

  * Additional checks specific to `use` ensure that the user only inputs a valid `int`, or that the `amount` must not be 0 beforehand.
  * Any exceptions thrown come with a message to help the user remedy their specific issue, as displayed by the `Ui`.


## Product scope
### Target user profile

Our target user is someone who regularly goes grocery shopping, and would like to track and manage their inventory of groceries.

### Value proposition

Grocery in Time aims to act as an easy-to-use central database for all the user's groceries. Managing many groceries stored at different locations around the house can get confusing,
therefore our app will allow users to track their groceries easily. 

Users are able to edit and manage the category, amount, expiration date, and storage location of their groceries.
When groceries are running low, the app can generate a shopping list to remind users of what they need to buy.
Furthermore, the app can generate a list of items that are expiring soon, reminding users to consume their groceries as soon as possible.

## User Stories

| Version | As a ...                      | I want to ...                               | So that I can ...                                      |
|---------|-------------------------------|---------------------------------------------|--------------------------------------------------------|
| v1.0    | new user                      | see instructions on how to use the app      | refer to them when I forget how to use the application |
| v1.0    | user                          | add groceries to the app                    | manage all my groceries                                |
| v1.0    | user                          | view all my groceries                       | know what I have bought                                |
| v1.0    | user                          | delete groceries from the list              | stop tracking those groceries                          |
| v1.0    | user                          | add the amount of a grocery                 | keep track of the amount of that item I have           |
| v1.0    | user                          | add the expiration date of the grocery      | keep track of when my items expire easily              |
| v2.0    | financially-aware user        | add the cost of the groceries               | know how much I am spending                            |
| v2.0    | health-conscious user         | categorise my groceries                     | know what types of groceries I have                    |
| v2.0    | user with many storage spaces | add the location of where an item is stored | see where I keep my groceries                          |
| v2.0    | user who consumes groceries   | track the usage of my groceries             | know how much I have left                              |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
