# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation


### Find Feature

#### Implementation

The Find Feature allows users to search for expenses based on a specific criteria such as description, minimum amount
and maximum amount. This feature is orchestrated by the `FindExpensesCommand` class, which will be
initialized by the `Parser` class. Within the `FindExpensesCommand` object, the `Parser` would have initialized it with
4 variables, an `ExpenseList` object,  along with a `description`, `minAmount` , `maxAmount`. 
The relevance of these Class Attributes in `FindExpensesCommand` is as follows : 

| Class Attribute | Variable Type | Relevance                                                                 |
|-----------------|---------------|---------------------------------------------------------------------------|
| expenses        | ExpenseList   | ExpenseList Object containing the list of expenses which will be filtered |
| description     | String        | The description to match against expenses in `expenses`                   |
| minAmount       | Double        | The minimum amount matched expenses should be                             |
| maxAmount       | Double        | The **maximum** amount matched expenses should be                         |


Upon the call of the `execute()` method in `BudgetBuddy` using `command.execute()`,
the `FindExpensesCommand` Object, utilizes the following methods from the `ExpenseList` class in order to both 
obtain a new `ExpenseList` object containing the filtered expenses, along with printing them.

| Method           | Return Type        | Relevance                                                       |
|------------------|--------------------|-----------------------------------------------------------------|
| filterExpenses() | ArrayList<Expense> | Returns an ArrayList<Expense> containing all filtered expenses  |
| listExpenses()   | void               | Prints the filtered expenses obtained from `filterExpenses()`   |

The following UML Sequence diagram below shows how the Parser works to obtain the relevant inputs for the Find Feature :
![Sequence Diagram for Parser for Find Feature](diagrams/sequenceDiagram-Parser%20For%20Find%20Command.jpg)

The following is a step-by-step explanation for the Parser for the Find Feature :
1. `BudgetBuddy` calls `Parser#parseCommand(input)` with `input` being the entire user input.
E.g `find expenses d/bruno`
2. Within the `Parser`, it will have determined that the `input` is a Find Command from the `isFindCommand(input)`
function. This part has been left out in the diagram as it is trivial.
3. The `Parser` then self calls the method `handleFindCommand(input)` with the `input` still being the entire
user input.
4. Within `handleFindCommand(input)`, the first check would be the check for the existence of any combination of 
`d/ , morethan/ and lessthan/`. If none of these combinations were found, it immediately returns `null`
5. If the checks in `4.` is passed, Three variables would be initialized.

    * | Variable Name | Variable Type |                                                              
      |---------------|---------------|
      | description   | String        | 
      | minAmount     | Double        |
      | maxAmount     | Double        |
6. Depending on which parameters were present, the corresponding input would be extracted and placed into each variable
using the `Parser#extractDetailsForFind(input, "parameter")`
7. Should the values of `minAmount` and `maxAmount` not be empty,  a check is done to ensure `minAmount` is less than
or equals to `maxAmount`. If this check does not pass, the function immediately returns `null`
8. Finally, `Parser#handleFindCommand()` returns a `FindExpensesCommand` to `Parser#parseCommand()`, which is
then returned to `BudgetBuddy`

The following UML Sequence diagram below shows how the Find Feature command works when a user provides a **valid**
find expenses command:

![Sequence diagram for Find Feature](diagrams/sequenceDiagram_FindExpenses.jpg)

The following is an example of the processes that occur when the user uses the find expenses feature:
1. The user types `find expenses d/bruno morethan/30 lessthan/200`. This input is passed through the `Parser`
class from `BudgetBuddy`, which constructs a `FindExpenseCommand` Object with `expenses : current overall ExpenseList`, 
`description : bruno`, `minAmount : 30`, `maxAmount : 200`.
2. `Parser` returns this created `FindExpenseCommand` Object to `BudgetBuddy` and `BudgetBuddy` calls 
`FindExpenseCommand#execute()`
3. `execute()` is called, which initializes a variable `filteredExpenses` of type `ArrayList<Expense>`.
4. `execute()`then calls `ExpenseList#filterexpenses`, which returns the filtered expenses based on the `description`,
`minAmount` and `maxAmount` into the `filteredExpenses` variable.
5.  If `filteredExpenses` is empty, "No Matching Expenses Found" is printed and `execute` ends here.
5. If `filteredExpenses` is not empty, `execute()` then initializes a new variable `filteredExpenseList` 
of type `ExpenseList` with `filteredExpenses` initialized as the `expenses` Class attribute.
6. Finally `execute()` calls `filteredExpenseList#listexpenses()` to print filtered expenses into the CLI.

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
