# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


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

### Add Feature

#### Implementation

The Add Feature allows users to add expenses to different categories. `AddExpenseCommand` class enables this feature, 
after initialized by the `Parser` class. Within the `AddExpense` object, the `Parser` would have initialized it with
4 variables, an `ExpenseList` object,  along with a `category`, `amount` , `description`. 
The relevance of these Class Attributes in `AddExpenseCommand` is as follows : 

| Class Attribute | Variable Type | Relevance                                         |
|-----------------|---------------|---------------------------------------------------|
| expenses        | ExpenseList   | ExpenseList Object containing the list of expenses|
| category        | String        | The category that the `expense` belongs to        |
| amount          | String        | The amount spent                                  |
| description     | String        | The description of the expense                    |


Upon the call of the `execute()` method in `BudgetBuddy` using `command.execute()`,
the `AddExpenseCommand` Object utilizes the following method from the `ExpenseList` class to add it to the existing
list of `expenses` matching against the corresponding `category`.

| Method       | Return Type | Relevance                                       |
|--------------|-------------|-------------------------------------------------|
| addExpense() | void        | Add expense to the existing list of `expenses`  |

