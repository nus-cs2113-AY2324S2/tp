# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

1. Menu
2. Add
3. List Expenses
4. List Savings

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

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
* 
* List Expenses: `list expenses CATEGORY`
* List Savings: `list savings CATEGORY`
