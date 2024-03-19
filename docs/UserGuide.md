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
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
7. [Command Summary](#command-summary)
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

To run BinBash, ensure that your Computer meets the following minimum system requirements:
* Operating System: Windows, macOS, or Linux
* Java `11` is installed. Refer to our [FAQ](#faq) for more details.

1. Ensure that you have Java `11` or above installed on your Computer.
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

**Q**: How do I know if I have Java `11` installed on my computer?

**A**: Using the terminal/command prompt, type in `java -version`. If Java `11` is installed, you should see a result that is similar to this:
```bash
$ java -version
openjdk version "11.0.22" 2024-01-16
OpenJDK Runtime Environment ... (build ...)
OpenJDK 64-Bit Server VM ... (build ...)
```
If not, do refer to Oracle's [guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) on installing Java `11` for your operating system.

## Glossary
