# User Guide

## Introduction

StockMaster is a platform aimed at helping SMEs track and organise their inventory.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `StockMaster` from [here](http://link.to/duke).
3. 

## Features 

{Give detailed description of each feature}

### Adding an item: `add`
Adds a new item to the list of items.

Format: `add ITEM_NAME qty/ITEM_QUANTITY [cat/CATEGORY]`

* `CATEGORY` is an optional field. If blank, it will default to `N/A`.

Example of usage: 
```
add Apples qty/50
add Phone qty/5 cat/Electronics
```

### Deleting an item: `del`
Deletes the item from the list of items.

Format: `del ITEM_NAME`

Example of usage:
```
del Apples
```

### Editing an item: `edit`
Edits the quantity of the item.

Format: `edit ITEM_NAME qty/NEW_QUANTITY`

Example of usage:
```
edit Apples qty/10
edit Phone qty/0
```

### Listing all items: `list`
Lists all stored items.

Format: `list [cat/CATEGORY]`

* `CATEGORY` is an optional field. By default, it will list all the stored items.

Example of usage:
```
list
list Electronics
```

### List all available commands: `help`
Lists all commands as per the command summary below.

Format: `help [c/COMMAND]`

* `COMMAND` is optional. Specifying the command will give a more comprehensive
  description of the command.

Example of usage:
```
help
help c/add
```


### Closing the app: `exit`
Saves and closes the app safely.

Format: `exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Simply copy and paste the saved folder that is created upon launch of 
the application.

## Command Summary

* Add new item: `add Apples qty/10 cat/Food`
* Delete an item: `delete Apples`
* List all items: `list`
* List all commands: `help`