# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Finding expenses : `find expenses`
Finds expenses based on their description or amount

Format : `find expenses [d/DESCRIPTION] [morethan/MINAMOUNT] [lessthan/MAXAMOUNT]`

* `DESCRIPTION`, `MINAMOUNT`, `MAXAMOUNT` can be used in any order and combination
* `DESCRIPTION` is the description associated with the expenses the user wishes to find
* `MINAMOUNT` is the filter for expenses with amounts higher than specified value
* `MAXAMOUNT` is the filter for expenses with amounts lower than specified value
* At least one filter must be provided

Examples of usage :

* `find expenses d/coffee` : Finds all expenses with the word "coffee" in the description
* `find expenses d/coffee morethan/200` : Finds all expenses with the word "coffee" and amount higher than $200

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
