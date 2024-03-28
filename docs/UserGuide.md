# User Guide
# Introduction
StockPal is a command line interface (CLI) application designed to help small E-commerce business owners who are 
just starting up to manage their stock-taking effectively. Users would mostly interact with StockPal via text commands.

StockPal is compatible for usage on Windows, Mac and Linux systems.

Not sure where to begin? Start by learning how to utilize this user guide.

# Table of Contents

- [Introduction](#introduction)
- [Quick Start](#quick-start)
    - [Downloading StockPal](#downloading-stockpal)
    - [Running StockPal](#running-stockpal)
- [Features](#features)
    - [Viewing help: `help`](#viewing-help-help)
    - [Adding new product: `new`](#adding-new-product-new)
    - [Editing product details: `edit`](#editing-product-details-edit)
    - [Deleting a product and its details: `delete`](#deleting-a-product-and-its-details-delete)
    - [Increasing quantity of existing product: `inflow`](#increasing-quantity-of-existing-product-inflow)
    - [Decreasing quantity of existing product: `outflow`](#decreasing-quantity-of-existing-product-outflow)
    - [Find keywords in all products: `find`](#find-keywords-in-all-products-find)
    - [Listing all products: `list`](#listing-all-products-list)
    - [Exiting the program: `exit`](#exiting-the-program-exit)
    - [Editing the data file](#editing-the-data-file)
- [Command Summary](#command-summary)

# Quick Start

## Downloading StockPal

1.  Ensure you have [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed in your computer.

2.  Download the latest stockpal.jar from [here](https://github.com/AY2324S2-CS2113T-T09-3/tp/releases).

3.  Copy the file to the folder you want to use as the home folder for
    the application. For example,`C:\Users\setupuser\Documents\StockPal\stockpal.jar`.

## Running StockPal

1.  **For Windows users:**

    Open a command prompt terminal. You can do so by searching for `command
    prompt` in the Windows search bar. <br><br>

    **For MAC users:**

    Open a terminal. You can do so by searching for the `terminal `using the Spotlight icon in the menu bar. Click on the terminal.


2.  Determine the file path to the home folder of stockpal.jar. You can
    do so by navigating to stockpal.jar in File Explorer. For example,
    the image below shows that the file path to the home folder of
    stockpal.jar is `C:\Users\setupuser\Documents\StockPal`


3.  In the command prompt terminal, navigate to the home folder using
    the command `cd <file path to the home folder>`. For example, `cd
    C:\Users\setupuser\Documents\StockPal`.


4.  In the command prompt terminal, run StockPal using the command `java
    -jar stockpal.jar`.


## Features 
> ## Notes about the command format
>
> - Words in UPPER_CASE are the parameters to be supplied by the user.
> - Items in square brackets are optional.
> - Parameters must be in the specified order.
> - Commands are case-sensitive and must strictly follow case specified.

## Viewing help: `help`

List all available commands supported by Stockpal.

Format: `help`


## Editing product details: `edit`

Edits an existing product in the inventory at the specific PID by the input value(s).

Format: `edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]`

- At least one optional field must be provided.
- PID must be a valid Product ID of an existing product.
- QUANTITY must be an integer more than or equals to 0.
- PRICE must have an integer more than or equals to 0, and have exactly 2 decimal places.
- DESCRIPTION is limited to 100 characters.

### Example 1
Context:
- Product `Sticky note`’s Product ID (PID) is 1.
- You wish to change `Sticky note`'s information as follows.
  - PRICE: $2.00
  - DESCRIPTION: 100 pieces per stack

Input:

```edit 1 p/2.00 d/100 pieces per stack```

Output: 

```Product details have been updated.```

### Example 2
Context
- `Highlighter`’s Product ID (PID) is 23.
- You wish to change `Highlighter`’s information as follows.
  - PRODUCT_NAME: Neon highlighter
  - QUANTITY: 200
  - PRICE: $1.50
  - DESCRIPTION: Erasable

Input:

```edit 23 n/Neon highlighter q/200 p/1.50 d/Erasable```

Output:

```Product details have been updated.```


# Command Summary

<table>
  <tr>
   <td><strong>Command</strong>
   </td>
   <td><strong>Description</strong>
   </td>
  </tr>

  <tr>
   <td><code>help</code>
   </td>
   <td>List all available commands supported by Stockpal
   </td>
  </tr>

  <tr>
   <td><code>edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]</code>
   </td>
   <td>Edit an existing product’s field
   </td>
  </tr>
</table>


# FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the app on the other computer and overwrite the empty `inventory.csv` file it creates with the file that contains the data of your previous StockPal home folder.


# Glossary

PID (Product ID) - a number that we use to keep track of the products.

CLI (Command Line Interface) - a text-based interface used to interact with our program by entering commands into a terminal.

CSV (Comma-Separated Values) - a file format used to store tabular data. Each line represents a row of data, and each field within a row is separated by a comma
