# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design 

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation

### Edit Expense Feature
The Edit Expense feature allows users to edit their previously added expenses, specifically the `category`, `amount`, 
and `description`. This feature is managed by the `EditExpenseCommand` class, which is initialized by the 
`Parser` class. Within the `EditExpenseCommand` object, 5 variables would have been initialized in the `Parser` class:
an `ExpenseList` object, `category`, `index`, `amount` and `description`. The relevance of these Class Attributes in 
`EditExpenseCommand` is as follows:

| Class Attribute | Variable Type | Relevance                                                             |
|-----------------|---------------|-----------------------------------------------------------------------|
| expenses        | ExpenseList   | ExpenseList Object containing the list of expenses that can be edited |
| category        | String        | The edited category for the expense in the spciefied index            |
| index           | Integer       | The index of the expense to be edited from `ExpenseList`              |
| amount          | Double        | The edited amount the expense in the specified index should be        |
| description     | String        | The edited description for the expense in the specified index         |

When the `execute()` method in `BudgetBuddy` is called via `command.execute()`, the `EditExpenseCommand` Object, 
utilizes the following method from the `ExpenseList` class to edit the expense.

| Method        | Return Type | Relevance                                                                                 |
|---------------|-------------|-------------------------------------------------------------------------------------------|
| editExpense() | void        | Edits the `category`, `amount` and `description` for the expense in the specified `index` |

The following UML Sequence diagram below shows how the Edit Expense Feature Command is executed when a user
inputs a valid edit expense command:

![EditExpenseSequence.png](team%2FEditExpenseSequence.png)

The following is a step by step explanation of the processes that occur for an example input:
`edit expense c/Transport i/2 a/40 d/GRAB`

1. The BudgetBuddy application receives the input string `edit expense c/Transport i/2 a/40 d/GRAB` and uses 
the `Parser` to interpret it.
2. The `Parser` splits the input into parts and constructs a `EditExpenseCommand` Object with the category 
(`c/Transport`), index (`i/2`), 
amount (`a/40`), and description (`d/GRAB`).
3. `Parser` returns this called `EditExpenseCommand` Object to `BudgetBuddy`.
4. The `BudgetBuddy` application calls `execute()` on the `EditExpenseCommand` object.
5. The `EditExpenseCommand` object calls `editExpense` on the `ExpenseList` with the provided parameters. 
6. The `ExpenseList` looks up the second expense in its list 
(as lists are zero-indexed, it uses index - 1 to access the correct item), and updates this expense’s 
category to "Transport," amount to 40.0, and description to "GRAB."
7. A message "Expense edited successfully." is printed to the console.

### Listing feature (List Savings)
The Listing Savings Feature enables users to view their savings, potentially filtered by a specific category. This functionality is orchestrated by the `ListSavingsCommand` class, which is initialized by the `Parser` class. Within the `ListSavingsCommand` object, the `Parser` provides it with an `SavingList` object, along with an optional `filterCategory`. The relevance of these class attributes in `ListSavingsCommand` is detailed in the following table:

| Class Attribute | Variable Type | Relevance                                                                       |
|-----------------|---------------|---------------------------------------------------------------------------------|
| savings         | SavingList    | The `SavingList` object containing the list of savings to be displayed or filtered |
| filterCategory  | String        | The category to filter the savings by, if provided                             |

When `BudgetBuddy` invokes the `execute()` method via `command.execute()`, the `ListSavingsCommand` object uses several methods from the `SavingList` class to perform its tasks:

| Method           | Return Type        | Relevance                                                         |
|------------------|--------------------|-------------------------------------------------------------------|
| getSavings()     | ArrayList<Saving>  | Retrieves the list of all savings from the `SavingList`           |
| findTotalSavings() | void             | Calculates the total amount of savings stored in `SavingList`     |
| listSavings()    | void               | Prints the savings, filtered by `filterCategory`, to the CLI      |
| calculateRemainingSavings() | double    | Calculates the remaining amount after deducting total expenses    |

The Listing Savings feature follows these steps when a user inputs a command to list savings:
1. The user inputs `list savings [optional: filterCategory]`. This input is processed by the `Parser` class in `BudgetBuddy`, which creates a `ListSavingsCommand` object with `savings` set to the current `SavingList` and `filterCategory` to the user-specified category, if any.
2. The `Parser` returns this `ListSavingsCommand` object to `BudgetBuddy`, which calls `ListSavingsCommand.execute()`.
3. `execute()` calls `SavingList.listSavings(filterCategory)`, where the `filterCategory` is applied if provided.
4. Within `listSavings()`, the `findTotalSavings()` method is called first to calculate the initial total savings amount.
5. The `listSavings()` method continues by iterating through each `Saving` and printing those that match the `filterCategory` criteria.
6. After listing, the method calculates and displays the remaining savings by calling `calculateRemainingSavings(initialAmount, totalExpenses)`, accounting for any expenses deducted.
7. If the `filterCategory` is not provided, all savings are printed, and the total initial amount and remaining savings after expenses are displayed.

#### Sequence Diagram
The UML Sequence diagram for the Listing Savings feature would illustrate the interactions between the `User`, `BudgetBuddy`, `Parser`, `ListSavingsCommand`, and `SavingList` classes, showing the method calls and returns between these objects to complete the operation.
![Sequence diagram for List Expense Feature](diagrams/SavingList_SequenceDiagram.png)

### Listing feature (List Expenses)
The Listing Expenses Feature enables users to view their recorded expenses, optionally filtered by a category. This functionality is coordinated by the `ListExpensesCommand` class, which is instantiated by the `Parser` class with an `ExpenseList` object and an optional `filterCategory`. The roles of these attributes in `ListExpensesCommand` are:

| Class Attribute | Variable Type | Relevance                                                                         |
|-----------------|---------------|-----------------------------------------------------------------------------------|
| expenses        | ExpenseList   | Holds the list of expenses to be filtered and listed                              |
| filterCategory  | String        | The category to filter the expenses by (null if no filtering is needed)           |

Upon invocation of the `execute()` method by `BudgetBuddy` via `command.execute()`, the `ListExpensesCommand` object leverages methods from the `ExpenseList` class to display the filtered list of expenses:

| Method         | Return Type | Relevance                                                               |
|----------------|-------------|-------------------------------------------------------------------------|
| listExpenses() | void        | Prints the expenses, filtered by `filterCategory`, to the command line  |

Here's an overview of the process flow when a user employs the Listing Expenses feature:
1. The user inputs `list expenses [category]`. This input is processed by the `Parser` class in `BudgetBuddy`, creating a `ListExpensesCommand` object with the `expenses` set to the current overall `ExpenseList`, and the `filterCategory` set to the user-specified category (or null if not specified).
2. `Parser` returns this `ListExpensesCommand` object to `BudgetBuddy`, which then invokes `ListExpensesCommand.execute()`.
3. The `execute()` method calls `ExpenseList.listExpenses(filterCategory)`.
4. The `listExpenses()` method in `ExpenseList` then iterates over the expenses, applying the category filter if one is provided, and prints each qualifying expense.
5. It concludes by printing the total amount of listed expenses.

#### Sequence Diagram
The sequence diagram for the Listing Expenses feature would illustrate the above steps, showing the interactions between the `User`, `BudgetBuddy`, `Parser`, `ListExpensesCommand`, and `ExpenseList` classes.
![Sequence diagram for List Expense Feature](diagrams/ExpenseList_SequenceDiagram.png)


### Currency Converter feature
The Currency Converter Feature allows users to convert the currency of expenses and savings. This feature is facilitated by the `ChangeCurrencyCommand` class, initialized by the `Parser` class with `CurrencyConverter`, `ExpenseList`, and `SavingList` objects, alongside the `newCurrency` to convert to. The importance of these class attributes is as follows:

| Class Attribute  | Variable Type     | Relevance                                                                         |
|------------------|-------------------|-----------------------------------------------------------------------------------|
| currencyConverter| CurrencyConverter | The object responsible for currency conversion calculations                        |
| expenseList      | ExpenseList       | Contains the expenses whose currency will be converted                             |
| savingList       | SavingList        | Contains the savings whose currency will be converted                              |
| newCurrency      | Currency          | The new currency to which the amounts will be converted                           |

When `BudgetBuddy` calls `command.execute()`, `ChangeCurrencyCommand` employs the following methods from `CurrencyConverter` to convert the currency of all financial records:

| Method             | Return Type | Relevance                                                                         |
|--------------------|-------------|-----------------------------------------------------------------------------------|
| convertCurrency()  | void        | Converts the currency of each `Expense` and `Saving` object to `newCurrency`      |

Here's the step-by-step process when the user uses the Currency Converter feature:
1. The user inputs `change currency [newCurrencyCode]`. `Parser` processes this input and constructs a `ChangeCurrencyCommand` object with the necessary attributes.
2. The `ChangeCurrencyCommand` object is returned to `BudgetBuddy`, which calls `ChangeCurrencyCommand.execute()`.
3. `execute()` invokes `CurrencyConverter.convertCurrency(newCurrency, expenseList)` and `CurrencyConverter.convertCurrency(newCurrency, savingList)`.
4. Within each `convertCurrency` call, the amounts of `Expense` or `Saving` objects are converted to the `newCurrency` using the `convertAmount` method.
5. The `setAmount` and `setCurrency` methods of `ExpenseList` and `SavingList` are used to update the amounts and currency codes.
6. The updated financial records are now in the new currency.

#### Sequence Diagram
![Sequence diagram for List Expense Feature](diagrams/CurrencyConverter_SequenceDiagram.png)



## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ...          | I want to ...                                            | So that I can ...                                                |
|---------|-------------------|----------------------------------------------------------|------------------------------------------------------------------|
| v1.0    | user              | be able to view my expenses                              | track my prior expenditures and plan future expenses accordingly |
| v1.0    | user              | be able to view my savings                               | plan my budget accordingly                                       |
| v1.0    | user              | be able to view my expenses by their relevant categories | control my spending                                              |
| v1.0    | user              | be able to identify my largest savings category          | allocate necessary saved funds                                   |
| v2.0    | frequent traveler | log my expenses in multiple currencies                   | accurately track my expenses across different countries          | 

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}


