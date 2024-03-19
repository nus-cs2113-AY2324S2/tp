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
9. Find Expense

### Display Commands : `menu`
Displays the corresponding features of BudgetBuddy

Format: `menu [INDEX]`

* The `INDEX` refers to the number associated with each menu option. If `INDEX` is not provided, the overall 
menu list will be displayed
* `INDEX` must be either be empty OR a positive integer and a valid index in the menu list

Example of usage:

`menu` : Displays all menu list items

`menu 1` : Displays commands related to feature associated to menu list item 1

### Add Expense
Adds new expense

Format: `add expense c/CATEGORY a/AMOUNT d/DESCRIPTION`

* Increments expense of the specified CATEGORY by AMOUNT given.
* The `CATEGORY` must be one of the following pre-defined categories: "Housing",
  "Groceries", "Utility", "Transport", "Entertainment" or "Others".
* The `AMOUNT` must be a positive integer.
* The `DESCRIPTION` can be any string.

Example of Usage:

`add expense c/Entertainment a/167 d/Bruno Mars`


### Add Savings
Increase savings by specified amount to the savings list

Format:  `add savings c/CATEGORY a/AMOUNT`

* Increments savings of the specified CATEGORY by AMOUNT given.
* The `CATEGORY` must be one of the following pre-defined categories: "Salary",
  "Investments", "Gifts" or "Others".
* The `AMOUNT` must be a positive integer.
* The `DESCRIPTION` can be any string.

Example of usage:

`add savings c/Salary a/10000`

### Edit Savings: `edit savings`
Edit Savings that have been added previously.

Format: `edit savings c/CATEGORY i/INDEX a/AMOUNT`

* The `CATEGORY` must be one of the following pre-defined categories: "Salary",
  "Investments", "Gifts" or "Others".
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

Lists savings

Format: `list savings CATEGORY`

* The `CATEGORY` is optional and can be left blank.
* The `CATEGORY` must be a pre-existing category if inputted.
* Similar to listing expenses, users can view their savings with optional category filtering.
* Savings are listed along with their respective categories and amounts.
* Total savings are displayed at the end of the list, after deducting relevant expenditures.

Example Usage:

`list savings`
`list savings Salary`
`list savings Investment`


### Listing Expenses: `list expense`

Lists expenses

Format: `list expense CATEGORY`

* The `CATEGORY` is optional and can be left blank.
* The `CATEGORY` must be a pre-existing category if inputted.
* When listing expenses, users have the option to filter expenses based on categories.
* Users can specify a category to view expenses related to that category only.
* If no category is specified, the system will list all expenses.
* The listed expenses include details such as the date of the expense, category, amount, and description.
* Total expenses are displayed at the end of the list.

Example Usage:

`list expenses`
`list expenses Transport`
`list expenses Housing`

### Finding expenses : `find expenses`

Finds expenses based on their description or amount

Format : `find expenses [d/DESCRIPTION] [morethan/MINAMOUNT] [lessthan/MAXAMOUNT]`

* `DESCRIPTION`, `MINAMOUNT`, `MAXAMOUNT` can be used in any order and combination
* `DESCRIPTION` is the description associated with the expenses the user wishes to find
* `MINAMOUNT` is the filter for expenses with amounts higher than specified value
* `MAXAMOUNT` is the filter for expenses with amounts lower than specified value
* At least one filter must be provided

Examples of usage :

`find expenses d/coffee` : Finds all expenses with the word "coffee" in the description
`find expenses d/coffee morethan/200` : Finds all expenses with the word "coffee" and amount higher than $200

## Command Summary
* Add Savings: `add savings c/CATEGORY a/AMOUNT`
* Add Expense: `add expense c/CATEGORY a/AMOUNT d/DESCRIPTION`
* Edit Expenses `edit expense c/CATEGORY i/INDEX a/AMOUNT d/DESCRIPTION`
* Edit Savings `edit savings c/CATEGORY i/INDEX a/AMOUNT`
* List Expenses: `list expenses CATEGORY`
* List Savings: `list savings CATEGORY`
*  Find Expenses `find expenses [d/DESCRIPTION] [morethan/MINAMOUNT] [lessthan/MAXAMOUNT] `

