# BinBash User Guide

## Introduction

This document aims to provide a comprehensive and in-depth guide on how to install, configure and use BinBash. For those who just want to get started right away, head to [Getting Started](#getting-started).

### What's New in BinBash 1.0

Our long awaited first release adds everything you would expect of an inventory management system, including:
* Adding of items
* Removing of items
* Searching for items
* Listing your entire inventory
* and more (head to [Features](#features) to find out!)

## Table of Contents
1. [Introduction](#introduction)
2. [Table of Contents](#table-of-contents)
3. [BinBash Overview](#binbash-overview)
4. [How to Use this Guide](#how-to-use-this-guide)
5. [Getting Started](#getting-started)
6. [Features](#features)
    - [Adding an item: `add`](#adding-an-item-add)
    - [Searching for an item: `search`](#searching-for-an-item-search)
    - [Listing current inventory: `list`](#listing-current-inventory-list)
    - [Deleting an item: `delete`](#deleting-an-item-delete)
    - [Exiting the application: `bye`](#exiting-the-application-bye)
    - [Saving and Loading data](#saving-and-loading-data)
7. [Possible issues during startup](#possible-issues-during-startup)
8. [Command Summary](#command-summary)
8. [FAQ](#faq)
9. [Glossary](#glossary)

## BinBash Overview

Welcome to BinBash, an **Inventory Management System** designed to streamline your stock control processes. 
BinBash offers a simple and lightweight solution that makes managing your items fast and efficient.
It's built to handle the complexities of inventory tracking so that you don't have to. 
This guide will walk you through each feature, providing clear instructions and examples to ensure that you 
can make the most out of BinBash. Let's get your inventory organized efficiently and effectively!

## How to Use this Guide

Though we make no assumptions on your technical familiarity, it would help if this isn't your first time seeing a command prompt.

Throughout the course of this guide we will describe the various inventory management capabilities BinBash is capable of, and how you might perform them using keyboard typed commands.
The format and expected outputs of these commands will be enumerated in turn. Don't worry if you can't get the hang of them immediately! We'll provide some concrete examples to better illustrate their use cases.

If you consider yourself a power user, we feel it is self-explanatory to jump to the [Command Summary](#command-summary).

## Getting Started

To run BinBash, ensure that your computer meets the following minimum system requirements:
* Operating System: Windows, macOS, or Linux
* Java `11` is installed. Refer to our [FAQ](#faq) for more details.

1. Ensure that you have Java `11` or above installed on your computer.
2. Head over to our [GitHub Page](https://github.com/AY2324S2-CS2113T-T09-2/tp/releases) and download the latest version of `BinBash.jar`.
3. Move the downloaded `BinBash.jar` file to an empty folder of your choice. This folder will now serve as the home folder for BinBash.
4. Open the terminal/command prompt for your system.
   1. If you're on Windows, press `Windows Key + R` and type in `cmd`. Press enter to launch the command prompt.
   2. If you're on Mac, click on `Launchpad` > `Other` > `Terminal`. Alternatively, click on the Spotlight icon in your menu bar, and type in `Terminal`.
   3. If you're on Linux, open the terminal in your Linux distribution.
5. Navigate to the folder containing `BinBash.jar` using the terminal/command prompt.
6. Type `java -jar BinBash.jar` into the command prompt, and press enter.

Awesome! You've now successfully started the BinBash application!

Now, you can head over to the [Features](#features) section to learn how to use the application.

## Features

### Adding an item: `add`

> Adds an item to the inventory.

Format: `add n/ITEM_NAME d/ITEM_DESCRIPTION q/ITEM_QUANTITY e/EXPIRATION_DATE s/SALE_PRICE c/COST_PRICE`

* `ITEM_NAME`, `ITEM_DESCRIPTION`, `SALE_PRICE` and `COST_PRICE` must be specified.
* All other fields are optional.
* If `ITEM_QUANTITY` is not specified, a default value of `0` will be assigned to it.
* There is no need to include the currency. A "$" sign will be appended to the prices.

Examples: 
* `add n/apple d/a type of fruit q/10 e/12-10-2024 s/1.20 c/0.45`
* `add n/lego d/toys q/7 s/102.00  c/34.32`

### Searching for an item: `search`

> Searches for items whose names contain the given keyword as a substring.

Format: `search KEYWORD`

- `KEYWORD`: The keyword to search for within the task descriptions.
- The search is case-sensitive and uses substring matching. This means it will find items whose names contain the exact 
keyword. For example, searching for "Car" will return items with the name "Toy Car" and "Carrot", but not items with 
the name "Scarlet Witch".

**Examples:**

- `search Milo`  
  This will return items with names such as "Milo Powder" and "Milo Packet".

### Listing current inventory: `list`

>Shows a list of all tasks in your task list.

**Format:** `list`

### Deleting an item: `delete`

> Deletes an item from the inventory. Item Identifier can be either item index or item name.

#### Deleting an item using item index

Format: `delete ITEM_INDEX`

* `ITEM_INDEX` must be specified.
* `ITEM_INDEX` specified must exist in the inventory, otherwise no item will be deleted
* Index of items can be viewed using the `list` command.

Examples:
* `delete 1` 
* `delete 4`

#### Deleting an item using item name

Format: `delete ITEM_NAME`

* `ITEM_NAME` must be specified.
* `ITEM_NAME` specified must be the exact name of the item.
* If there are no items with item names matching `ITEM_NAME`, no items will be deleted.
* Item names of items in the inventory can be viewed using the `list` command.

Examples:
* `delete cookie`
* `delete tissue paper`

### Exiting the application: `bye`

> Exits the application.

After a long day at work, it's time to take a rest!
Fret not, BinBash will save the state of your current inventory, and you can always come back to it later.

Format: `bye`

### Saving and Loading data

Unsure as to how you can save your BinBash data? Don't worry! Your data is automatically saved to your local storage after any command that modifies your inventory. No manual saving of data is required.

Similarly, your saved data will be automatically loaded into BinBash when you start the application. If no previous save data was found, the application starts on a clean state.

> **Caution**: For advanced users, BinBash data is stored locally as a `.txt` file in your BinBash install location (`<Location of BinBash.jar>/data/items.txt`). Do exercise caution when directly editing this file; BinBash **will not load** your save file if it is corrupted. 
> It is highly recommended to take a backup of the file before editing it.

## Possible Issues During Startup

Have problems loading up BinBash? Fret not, here's how to troubleshoot some of them:

1. **File is Corrupted Error**
   If you encounter an error that says the file is corrupted, you should first take a look at your `data.txt` file. It's possible that the content of the file has become invalid. Here's what you can do:
   - **Delete and Recreate**: If you're unsure about the formatting, simply delete the `data.txt` file. BinBash will create a new one when you restart the application.
   - **Rectify the Content**: If you have important data you can't lose, open the `data.txt` file and fix any formatting issues. Make sure each task follows the correct structure BinBash expects.

2. **Issues with Data Directory or File**
   If you encounter an error about not being able to create or read/write from the data directory or file, this usually means there's a permissions issue on your system. Here's how to handle it:
   - **Check Permissions**: Ensure that BinBash has the right permissions to access the folders it needs. Right-click on the directory and check its properties to make sure reading and writing are allowed.

## Command Summary

| **Commands** | **Usage**                                                                                        |
|--------------|--------------------------------------------------------------------------------------------------|
| **add**      | `add n/ITEM_NAME d/ITEM_DESCRIPTION q/ITEM_QUANTITY e/EXPIRATION_DATE s/SALE_PRICE c/COST_PRICE` |
| **search**   | `search KEYWORD`                                                                                 |
| **list**     | `list`                                                                                           |
| **delete**   | `delete ITEM_INDEX`                                                                              |
| **bye**      | `bye`                                                                                            |

## FAQ

**Q**: How do I know if I have Java `11` installed on my computer? <br>
**A**: Using the terminal/command prompt, type in `java -version`. If Java `11` is installed, you should see a result that is similar to this:
```bash
$ java -version
openjdk version "11.0.22" 2024-01-16
OpenJDK Runtime Environment ... (build ...)
OpenJDK 64-Bit Server VM ... (build ...)
```
If not, refer to Oracle's [guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) on installing Java `11` for your operating system.

**Q**: Can I move my BinBash data to another computer? <br>
**A**: Absolutely! Here's a step-by-step guide on how you can do this:
1. On your current computer, locate the BinBash save file. The save file can be found at `<Location of BinBash.jar>/data/items.txt`. Make a copy of this file.
2. Ensure that BinBash has been installed on the other computer. Refer to [this section](#getting-started) for more details.
3. On the other computer, create the `/data` folder in the BinBash install location if it does not exist.
4. Then, paste the copied save file in this folder. If an existing save file already exists, choose to overwrite it.
5. Start up BinBash, and execute the [`list` command](#listing-current-inventory-list) to check that your data has been loaded successfully.

**Q**: Do I need an Internet connection to use BinBash? <br>
**A**: You do not need an Internet connection. BinBash can be used offline.

## Glossary

### Bash
A computer program that provides a text-based interface and environment for user input. Also, the name of a programming language commonly used for scripting and operating system job control.

### Command Prompt / Command Line / Terminal
A means of interacting with a computer through keyboard typed lines of text, also known as commands. This is in contrast to the currently more popular graphical user interface (GUI), which uses visual elements that users can directly manipulate to perform their desired actions.

### Java
From Wikipedia:
> Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.
