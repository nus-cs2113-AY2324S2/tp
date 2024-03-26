
---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* The formatting for the developer guide is inspired by [AB-3](https://se-education.org/addressbook-level3/DeveloperGuide.html).

Third-party libraries:
* [OpenCSV](https://opencsv.sourceforge.net/) - This package is licensed under [Apache2](https://opencsv.sourceforge.net/licenses.html), which is a business-friendly open-source software license.


--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture
<img src="images/ArchitectureDiagram.png" alt=""/>

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

[**`StockPal`**](https://github.com/AY2324S2-CS2113T-T09-3/tp/blob/master/src/main/java/seedu/stockpal/StockPal.java) is in charge of the app launch and shut down.

The bulk of the app's work is done by the following five components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Parser`**](#parser-component): Parses user input into respective commands.
* [**`Commands`**](#commands-component): The command executor.
* [**`Data`**](#data-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple components above.
[**`Exceptions`**](#exceptions-classes) represents a collection of exceptions used by multiple components above.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" alt=""/>

The sections below give more details of each component.

### UI component
The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2113T-T09-3/tp/tree/master/src/main/java/seedu/stockpal/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

### Parser component

**API** : [`Parser.java`](https://github.com/AY2324S2-CS2113T-T09-3/tp/blob/master/src/main/java/seedu/stockpal/parser/Parser.java)

![Structure of the Parser Component](images/ParserClassDiagram.png)

### Commands component

**API** : [`Command.java`](https://github.com/AY2324S2-CS2113T-T09-3/tp/blob/master/src/main/java/seedu/stockpal/commands/Command.java)


![Structure of the Commands Component](images/CommandsClassDiagram.png)

### Data component

**API** : [`Data`](https://github.com/AY2324S2-CS2113T-T09-3/tp/blob/master/src/main/java/seedu/stockpal/data)

![Structure of the Data Component](images/DataClassDiagram.png)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2113T-T09-3/tp/blob/master/src/main/java/seedu/stockpal/storage/Storage.java)

![Structure of the Storage Component](images/StorageClassDiagram.png)

The `Storage` component,
* can save product list data in CSV format, and load them back into corresponding Products.
* depends on the `StockPal` component (because the `Storage` component's job is to save/load objects that belong to `StockPal`)
* consists of the classes `Storage`, `CsvWriter` and `CsvReader`.
  * `Storage` defines methods that loads and saves data.
  * `CsvWriter` is responsible for handling the writing of data to the CSV data file.
  * `CsvReader` is responsible for handling the reading of data from the CSV data file.

### Common classes

Classes used by multiple components are in the `seedu.stockpal.common` package.

### Exception classes

Exceptions classes used by multiple components are in the `seedu.stockpal.exceptions` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add new product feature
<img src="images/AddCommandClass.png" alt=""/>

The NewCommand class is responsible for adding a new product to the inventory in the StockPal application.

**Attributes**
* name: The name of the product.
* quantity: The initial quantity of the product.
* price: The price of the product.
* description: The description of the product.

**Methods**
* `NewCommand`: Constructor for creating a new instance of the NewCommand class.
* `execute`: Method to add the new product to the product list.
* `createProduct`: Method to create a new product with a unique product ID.

The following sequence diagram shows how an add operation works:
<img src="images/AddCommandSequence.png" alt=""/>



### Edit product feature

#### Implementation
The edit product feature is facilitated by `EditCommand` which extends `Command`.

Given below is an example usage scenario and how the edit product mechanism behaves.

Step 1. The user launches the application for the first time. There are no products in the product list.

Step 2. The user executes `add n/Highlihgter q/100 p/2.00 d/Neon highlighter` to add a product to product list.

Step 3. The user realises that he made mistakes in the product name and price. The user executes
`find Highlihgter` to obtain the `PID` of the product. The `PID` is `1`.

Step 4. The user executes `edit 1 n/Highlighter p/1.00` to edit the product name and price. The product name is 
successfully updated from `Highlihgter` to `Highlighter`. The price is successfully updated from `2.00` to `1.00`.

The following sequence diagram summarizes what happens when a user inputs a valid `edit` command.
<img src="images/EditCommandSequenceDiagram.png" alt=""/>

> INFO:
> The lifeline for EditCommand should end at the destroy marker (X) but due to a limitation of PlantUML, 
> the lifeline reaches the end of diagram.

#### Design consideration

**Aspect: Validating parameters and handling errors**

- Alternative 1 (current choice): Check parameters and handle errors within EditCommand.
  - Pros: Easy to implement
  - Cons: -
  
- Alternative 2: Handle validation of errors within to productList#updateProduct().
    - Pros: Implementing EditCommand#execute() will be very simple. Usage of throw/catch to handle errors.
    - Cons: productList#updateProduct() will be more lengthy. May require further abstraction.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:



**Value proposition**:

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...                                            | So that I can ...                                                                                          |
|---------|----------|----------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| v1.0    | new user | see usage instructions                                   | refer to them when I forget how to use the application                                                     |
| v1.0    | user     | I want to be able to edit details of the products easily | so that I can easily change the name / price / quantity /description of the products, if there is an error |
| v1.0    | user     | I want to have a small manual page                       | so that I am aware of the commands that I can enter                                                        |
| v2.0    | user     | find a to-do item by name                                | locate a to-do without having to go through the entire list                                                |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**


### Editing Product Details
1. Prerequisites: List all products using `list` command. There should be at least multiple products in the list.

3. Test case: `edit 1 n/Updated name d/Updated description`<br> 
   Expected: The name and description of the product with Product ID (PID) 1 
   will be changed to `Updated name` and `Updated description` respectively.

3. Test case: `edit 1 q/100 p/0.99`<br>
   Expected: The quantity and price of the product with Product ID (PID) 1
   will be changed to `100` and `0.99` respectively.
