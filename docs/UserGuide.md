# User Guide

## Introduction

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
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
7. [Command Summary](#command-summary)
8. [FAQ](#faq)
9. [Glossary](#glossary)

## BinBash Overview

## How to Use this Guide

## Getting Started

1. Ensure you have Java 11 or above installed.

## Features

### Adding an item: `add`

Adds an item to the inventory.

Format: `add n/ITEM_NAME d/ITEM_DESCRIPTION q/ITEM_QUANTITY e/EXPIRATION_DATE s/SALE_PRICE c/COST_PRICE`

* `ITEM_NAME`, `ITEM_DESCRIPTION`, `SALE_PRICE` and `COST_PRICE` must be specified.
* All other fields are optional.
* If `ITEM_QUANTITY` is not specified, a default value of `0` will be assigned to it.
* There is no need to include the currency. A "$" sign will be appended to the prices.

Examples: 
* add `n/apple d/a type of fruit q/10 e/12-10-2024 s/1.20 c/0.45`
* add `n/lego d/toys q/7 s/102.00  c/34.32`

### Searching for an item: `search`
### Listing current inventory: `list`
### Deleting an item: `delete`
### Exiting the program: `bye`
### Saving the data

## Command Summary

| **Commands** | **Usage**                                                                                        |
|--------------|--------------------------------------------------------------------------------------------------|
| **add**      | `add n/ITEM_NAME d/ITEM_DESCRIPTION q/ITEM_QUANTITY e/EXPIRATION_DATE s/SALE_PRICE c/COST_PRICE` |
| **search**   |                                                                                                  |
| **list**     | `list`                                                                                           |
| **delete**   |                                                                                                  |
| **bye**      | `bye`                                                                                            |

## FAQ

**Q**: How do I ...

**A**: {your answer here}

## Glossary
