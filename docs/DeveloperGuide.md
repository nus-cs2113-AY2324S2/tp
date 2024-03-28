# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
### Exception
### Itemlist
### Parser
### Storage
Storage class contains methods to write description of items to the file `./StockMasterData.txt`, 
and retrieve information from the file when program restarts.
### UI


## Product scope
### Target user profile

Small Business Owners who:
* has a need to manage a significant number of inventory products
* able to track revenue/loss of the business
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

### Value proposition

Help small business owners organise and better manage their inventory faster than 
a typical mouse/GUI driven app


## User Stories

|Version| As a ... | I want to ...               | So that I can ...                                          |
|--------|----------|-----------------------------|------------------------------------------------------------|
|v1.0|new user| see usage instructions      | refer to them when I forget how to use the application     |
|v1.0|user| add new items               |                                                            |
|v1.0|user| make changes to added items | change details about items such as quantity or price       |
|v1.0|user| search for specific items   ||
|v2.0|user| find a to-do item by name   | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *CLI* - Command Line Interface, where the user types commands rather than clicking options.
* *Item* - Item to be sold at the shop, with key information such as quantity, buying/selling price, description etc.

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
