# User Guide

## Introduction

Introducing 'Flirt and Fork', your personalised date planner! 

Ever had trouble coming up with fresh, fun ideas for date night? Well, say goodbye to the hassle of planning dates! 
Our application generates creative, enjoyable date itineraries that fit within your specified budget and location!

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `Duke` from [here](http://link.to/duke).
3. Using the command terminal, locate the downloaded jar file, and run the command `java -jar Duke.jar`.


## Features 

### Display all possible commands: `help`
Lists out all possible commands used to interact with Duke.

Format: `help`


### List out all restaurants or activities: `list`
Lists out all the restaurants or activities within our curated collection.

Format: `list`


### Generate a randomised date idea: `idea`
Generates out a randomised date idea, consisting of 1 restaurant and 1 activity from our curated collection.

Format: `idea`


### Generate a date itinerary, based on preferred location and budget: `itinerary`
Generates out a date itinerary, consisting of 2 restaurants and 2 activities from our curated collection.
The itinerary selected will be based off the user's indicated location and budget (Refer to the legend [below](#legend)])

Format: `itinerary (preferred location) (preferred price)`

* The `preferred location` and `preferred price` inputted must be inside the legend.  

Example of usage: 
`itinerary NE S`
`itinerary ACC B`


### Add a restaurant to your favourites: `food`
Adds a new restaurant to your favourited list.

Format: `food (name of eatery) (location) (price)`

* The `location` and `price` inputted must be inside the legend. 

Example of usage: 
`food East Coast BBQ NE B`
`food Omakase C S`


### Add an activity to your favourites: `activity`
Adds a new activity to your favourited list.

Format: `activity (name of activity) (location) (price)`

* The `location` and `price` inputted must be inside the legend. 

Example of usage: 
`activity Paragliding C S`
`activity Bungee Jumping at Sentose S P`


### Find an entry from your favourited list: `find`
Finds relevant entries from your favourited list, based on the keyword inputted.

Format: `find (keyword)`

* The `keyword` is case-sensitive. `Japanese` will not yield the same result as `japanese`. 
* The `keyword` can contain multiple words.

Example of usage: 
`find thrifting`
`find thrift shopping`


### List out all entries in your favourited list: `favourites`
Lists out all the entries within your favourited list.

Format: `favourites`


### Delete an entry from your favourited list: `delete`
Deletes an entry from your favourited list, based on the entry index inputted.

Format: `delete (index of entry)`

Example of usage: 
`delete 2`
`delete 7`


### Exit the program: `exit`
Exits the program.

Format: `exit`


## Legend
Prices:
* C: Cheap
* B: Budget
* A: Affordable
* P: Pricey
* S: Special Ocassions Only

Locations:
* E: East
* W: West
* C: Central
* S: South
* NE: NorthEast
* ACC: Accessible (found in multiple places around SG)


## FAQ

**Q**:
**A**: 

## Command Summary

* Display all possible commands: `help`
* List out all restaurants or activities: `list`
* Generate a randomised date idea: `idea`
* Generate a date itinerary, based on preferred location and budget: `itinerary (preferred location) (preferred price)`
* Add a restaurant to your favourites: `food (name of eatery) (location) (price)`
* Add an activity to your favourites: `activity (name of activity) (location) (price)`
* Find an entry from your favourited list: `find (keyword)`
* List out all entries in your favourited list: `favourites`
* Delete an entry from your favourited list: `delete (index of entry)`
* Exit the program: `exit`
