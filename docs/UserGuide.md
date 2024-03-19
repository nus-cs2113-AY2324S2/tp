# User Guide

## Features
1. Menu
2. Add
3. Edit Savings
4. Edit Expense
5. Reduce Savings
6. Delete Expense
7. List Savings
8. List Expense


### Edit Savings: `edit savings`
Edit Savings that have been added previously.

Format: `edit savings c/CATEGORY i/INDEX a/AMOUNT`

* The `CATEGORY` must be one of the following pre-defined categories: "Housing",
  "Groceries", "Utility", "Transport", "Entertainment" or "Others".
* The `INDEX` must be a positive integer and a valid index in the menu list.
* The `AMOUNT` must be a positive integer.

Example of usage:

`edit savings c/Entertainment i/3 a/300`


### Edit Expenses: `edit expense`
Edit expenses that have been added previously.

Format: `edit expense c/CATEGORY i/INDEX a/AMOUNT d/DESCRIPTION`

* The `CATEGORY` must be one of the following pre-defined categories: "Housing",
  "Groceries", "Utility", "Transport", "Entertainment" or "Others".
* The `INDEX` must be a positive integer and a valid index in the menu list.
* The `AMOUNT` must be a positive integer.
* The `DESCRIPTION` can be any string.

Example of usage:

`edit expense c/Utility i/2 a/180 d/Household Electricity`


### Reduce Savings: `reduce savings`

Reduces the amount saved in a particular category

Format: `reduce savings i/INDEX a/AMOUNT`

* The `INDEX` must be a positive integer and a valid index in the menu list.
* The `AMOUNT` to be reduced must be a positive integer

Example of usage:

`reduce savings i/4 a/10`
Reduces the savings of category of index 4 listed in the savings tracker by $10


### Delete Expense: `delete expense`

Deletes expenses that have been added wrongly or are no longer relevant.
Format: `delete expense i/INDEX`

* The `INDEX` must be a positive integer and a valid index in the menu list.

Example of usage:

`delete expense i/4` 
Deletes the expense of at index 4 listed in the expenditure tracker.

**Note:**
- Once an expense is deleted, it cannot be recovered.


### Listing Savings: `list savings`
    - Similar to listing expenses, users can view their savings with optional category filtering.
    - Savings are listed along with their respective categories and amounts.
    - Total savings are displayed at the end of the list, after deducting relevant expenditures.

Format: `list savings CATEGORY`

* The `CATEGORY` is optional and can be left blank.
* The `CATEGORY` must be a pre-existing category if inputted.

Example Usage:

`list savings`
`list savings Salary`
`list savings Investment`


### Listing Expenses: `list expense`
    - When listing expenses, users have the option to filter expenses based on categories.
    - Users can specify a category to view expenses related to that category only.
    - If no category is specified, the system will list all expenses.
    - The listed expenses include details such as the date of the expense, category, amount, and description.
    - Total expenses are displayed at the end of the list.

Format: `list expense CATEGORY`

* The `CATEGORY` is optional and can be left blank.
* The `CATEGORY` must be a pre-existing category if inputted.

Example Usage:

`list expenses`
`list expenses Transport`
`list expenses Housing`


## Command Summary
* Edit Expenses `edit expense c/CATEGORY i/INDEX a/AMOUNT d/DESCRIPTION`
* Edit Savings `edit savings c/CATEGORY i/INDEX a/AMOUNT`
* List Expenses: `list expenses CATEGORY`
* List Savings: `list savings CATEGORY`

