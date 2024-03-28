# User Guide

## Introduction

Introducing 'Flirt and Fork', your personalised date planner! 

Ever had trouble coming up with fresh, fun ideas for date night? Well, say goodbye to the hassle of planning dates! 
Our application generates creative, enjoyable date itineraries that fit within your specified budget and location!

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest version of `Duke` from [here](http://link.to/duke).
3. Copy the file to the folder you want to use as the _home folder_
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Duke.jar` command to run the bot. The GUI should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the [Features](#Features) section below for an overview of all valid commands.

## Features 

This section provides an overview of the available commands in the application, grouped by their functionality. Detailed usage instructions for each command can be found in the [Usage](#Usage) section.

### Discovering Options
Explore potential dining, activities, and gifts options.
- `list`: View a comprehensive list of restaurants, activities, and gift ideas.

### Personalisation
Tailor the application experience to your preferences for more customised suggestions.
- `me`: Input personal details and preferences to enable personalised suggestions.
- `itinerary`: Generate a date itinerary based on specified preferences for location and pricing.
- `smart`: Generate a smart itinerary tailored to your personal details.
> Complete `me` before the smart itinerary function can be enabled

### Idea Generation
Stimulate creativity with randomised suggestions for dates and gifts.
- `idea`: Receive a randomised date idea.
- `gift`: Receive a randomised gift suggestion.

### Favourites Management
Manage a personalised list of your favourite foods and activities.
- `food`: Add a food option to your favourites list.
- `activity`: Add an activity to your favourites list.
- `find`: Search your favourites list for specific entries.
- `favourites`: List all entries in your current favourites list.
- `delete`: Remove an entry from your favourites list based on its entry number.

### Additional Utilities
Extra commands to facilitate application use.
- `help`: Display help message with a summary of all valid commands at any point in time.
- `history`: List all past marked date restaurants, activities, and gift ideas.

### Exit the program
There is only one command to terminate the program:
- `exit`: Saves all current data and exits the program.

Refer to the [Usage](#Usage) section below for details of each command.

## Usage

### Display all possible commands: `help`
Lists out all possible commands used to interact with Duke.

Example of usage: `help`

Expected outcome:
```
I know you are excited to Flirt & Fork :) Here's how: 

To take a look at potential restaurants, activities, or gifts simply send me the following: 
'list'

...
```


### Discovering Options: `list`
Lists out all the restaurants, activities, or gifts within the curated collection.

Example of usage: `list`

Expected outcome:
```
Looking for ideas to spice up your date night?
Choose from the following options:
1. List out delicious dining options (type 'food')
2. Discover exciting activities to do together (type 'activities')
3. Unwrap joy with our curated list of gifts that'll make hearts flutter! (type 'gifts')
What's your pleasure?
```
---

- If user input `food`: Displays a list of potential restaurants or eateries.

Expected outcome:
```
HMMMM Let's see what food is theree: 
1. 25 Degrees
2. 49 Seats
...
```
---
- If user input `activities`: Shows a list of fun and engaging activities.

Expected outcome:
```
What are some activities you can do as a couple? Let's see..
1. Botanic Garden Tours
2. Gardens by the Bay
...
```
---
- If user input `gifts`: Presents a selection of gift ideas.


Expected outcome:
```
Peek into Cupid's own gift collection!
1. [Accessory] Customised pendant necklace
2. [Accessory] Personalised charm bracelets
...
```

### Generate a randomised date idea: `idea`
Generates out a randomised date idea, consisting of 1 restaurant and 1 activity from our curated collection. After receiving the suggestion, users have the option to accept or request for another idea.

Example of usage: `idea`

Expected outcome:
```
You can do Marina Bay Sands Helicopter Tour and have a nice meal at 49 Seats
Satisfied with the date idea? [Yes/No]
```
---
- If user input `no`: Regenerates another date idea for users to consider.

Expected outcome:
```
Regenerating a new date idea..
You can do Punggol Settlement Seafood Dining and have a nice meal at Pura Barsa
Satisfied with the date idea? [Yes/No]
```
---
- If user input `yes`: Choice will be confirmed and saved in view history for ease of reference.

Expected outcome:
```
That's great! Enjoy your date!
```

### Generate a randomised gift suggestion: `gift`
Generates a randomised gift idea. Each suggestion comes with a category tag `[Creative]`, `[Food]`, `[Accessory]`, etc to give users a sense of what type of gift it is.

Example of usage: `gift`

Expected outcome:
```
[Creative] Mixed media collage incorporating photographs, ticket stubs
Satisfied with the gift suggestion? [Yes/No]
```
---
- If user input `no`: Regenerates another gift idea for users to consider.

Expected outcome:
```
No worries, love's journey has many paths. Let's try another! 
[Food] High-quality beans or loose leaf teas
Satisfied with the gift suggestion? [Yes/No]
```
---
- If user input `yes`: Choice will be confirmed and saved in view history for ease of reference.

Expected outcome:
```
This gift is about to make a love story even sweeter.
```


### Generate a date itinerary, based on preferred location and budget: `itinerary`
Generates out a date itinerary, consisting of 2 restaurants and 2 activities from our curated collection.
The itinerary selected will be based off the user's indicated location and budget.

Format: `itinerary [preferred_location] [preferred_price]`

> Note: The `preferred location` and `preferred price` must be within the [legend](#Legend).  

Example of usage: `itinerary E A`

Expected outcome:
```
Here is a rough itinerary for your date: 
We begin with lunch at Five Oars, followed by some fun at Changi Museum Visit.
We proceed to have dinner at Missus, and finish the night at Punggol Promenade Nature Walk.

Are you satisfied with the itinerary? [Yes/No]
```
---
- If user input `no`: Application will prompt user to initiate the generation process again if they wish to try for another option.

Expected outcome:
```
We apologise! Perhaps you could try again?
```
---
- If user input `yes`: Choice will be confirmed and saved in view history for ease of reference.

Expected outcome:
```
That's great! Enjoy your date!
```

### Generate a smart itinerary, based on your user profile: `smart`
Generates out a smart itinerary, based off the user information given to Duke

Format: `smart`

Expected outcome:
```

```


### Add a restaurant to your favourites: `food`
Adds a new restaurant to your favourited list.

Format: `food (name of eatery) (location) (price)`

* The `location` and `price` inputted must be inside the legend. 

Example of usage: 
`food East Coast BBQ NE B`
`food Omakase C S`

Expected outcome:
```

```


### Add an activity to your favourites: `activity`
Adds a new activity to your favourited list.

Format: `activity (name of activity) (location) (price)`

* The `location` and `price` inputted must be inside the legend. 

Example of usage: 
`activity Paragliding C S`
`activity Bungee Jumping at Sentose S P`

Expected outcome:
```

```

### Find an entry from your favourited list: `find`
Finds relevant entries from your favourited list, based on the keyword inputted.

Format: `find (keyword)`

* The `keyword` is case-sensitive. `Japanese` will not yield the same result as `japanese`. 
* The `keyword` can contain multiple words.

Example of usage: 
`find thrifting`
`find thrift shopping`

Expected outcome:
```

```

### List out all entries in your favourited list: `favourites`
Lists out all the entries within your favourited list.

Format: `favourites`

Expected outcome:
```

```

### Delete an entry from your favourited list: `delete`
Deletes an entry from your favourited list, based on the entry index inputted.

Format: `delete (index of entry)`

Example of usage: 
`delete 2`
`delete 7`

Expected outcome:
```

```


### List out all entries from your past date history: `history`
Lists out all the previous restaurants and activites completed on previous dates.

Format: `history`

Expected outcome:
```

```

### Exit the program: `exit`
Exits the program.

Format: `exit`

Expected outcome:
```

```

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
* Update your personal information: `me`
* Generate a randomised date idea: `idea`
* Generate a date itinerary, based on preferred location and budget: `itinerary (preferred location) (preferred price)`
* Generate a smart date itinerary, based on your user profile: `smart`
* Add a restaurant to your favourites: `food (name of eatery) (location) (price)`
* Add an activity to your favourites: `activity (name of activity) (location) (price)`
* Find an entry from your favourited list: `find (keyword)`
* List out all entries in your favourited list: `favourites`
* Delete an entry from your favourited list: `delete (index of entry)`
* List out all entries from your past date history: `history`
* Exit the program: `exit`
