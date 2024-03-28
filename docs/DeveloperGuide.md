# Developer Guide

---

## Acknowledgements

We would like to acknowledge the following third-party libraries, frameworks and sources:

### Development

- **[JUnit 5](https://junit.org/junit5/)**: The java testing framework.

### Gradle

- **[Checkstyle](https://docs.gradle.org/current/userguide/checkstyle_plugin.html)**: The Gradle plugin that ensures consistent and appropriate code style.
- **[Shadow](https://github.com/johnrengelman/shadow)**: The Gradle plugin for creating fat JARs.

---

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### [Implemented] Generate Idea feature

#### Implementation

The existing Generate Idea feature is facilitated by `GenerateIdea` by leveraging the methods `getRandomActivity()` and `getRandomFood()` in `ActivityList` and `FoodList`. It extends `Command` and implements the following operation:

- `execute()` - Generates a randomised date idea consisting of 1 food and 1 dining option. Users can prompt to regenerate an idea until they are satisfied.

Given below is an example usage scenario and how the Generate Idea mechanism behaves at each step.

Step 1. The user launches the application and executes the `idea` command. The `idea` command is parsed by the `parseCommand` method in the `Parser` class, which creates a `GenerateIdeaCommand` instance.

Step 2. The `execute` method of `GenerateIdea` is invoked. It retrieves a random activity and a random dining option from `ActivityList` and `FoodList` and presents it to the user.

Step 3. The user is not satisfied with the proposed idea and inputs the `no` command. The loop in `execute` does not meet the exit condition and thus, generates another idea using the same process as Step 2.

Step 4. The user is satisfied with the proposed idea and inputs the `yes` command. The loop in `execute` has met the exit condition and thus, the `run` method continues running allowing the user to input other commands.

### [Proposed] Smart Itinerary Generation feature

#### Proposed Implementation
The proposed Smart Itinerary Generation mechanism leverages the existing UserDetails class to retrieve user preferences and create smart itineraries. It extends the Command class with a new ```GenerateSmartItineraryCommand```. Additionally, it implements the following operations:
- ```GenerateSmartItineraryCommand(UserDetails userDetails)``` — Initializes the command with the user's stored preferences from the UserDetails object.
- ```execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui, Storage storage)``` — Generates a personalized itinerary by filtering food and activity options based on the user's preferences, ensures variety in the selections, and outputs the generated itinerary.

These operations are detailed in the ```Parser``` class, where the ```parseCommand(String userInput, UserDetails userDetails)``` method is updated to accept the ```UserDetails``` object and return a ```GenerateSmartItineraryCommand``` when the `"smart"` command is entered by the user.

Given below is an example usage scenario and how the Smart Itinerary Generation mechanism behaves at each step.

Step 1. The user launches the application for the first time. Their user details, including preferred location and cuisine preferences, are collected via the ```UserDetailsCommand``` and stored in a ```UserDetails``` object.

Step 2. The user enters the `"smart"` command to generate a personalized itinerary. The `"smart"` command is parsed by the ```parseCommand``` method in the ```Parser``` class, which creates a ```GenerateSmartItineraryCommand``` instance, passing the ```UserDetails``` object.

Step 3. The execute method of ```GenerateSmartItineraryCommand``` is invoked. It retrieves the user's preferred location and cuisine from the ```UserDetails``` object. The method then filters the ```FoodList``` to find food options matching the user's preferences, ensures the selected food options are distinct, and randomly selects two activities from the ```ActivityList```. The generated itinerary, consisting of the selected food and activity options, is then output to the user.


### [Proposed] History Tracking feature

#### Proposed Implementation
The proposed History Tracking feature keeps a database of previously visited restaurants, activities and gifts. It extends the Command class with a new ```ViewHistoryCommand```. Additionally, it implements the following operation:
- ```execute(FavouritesList favourites, FoodList foods, ActivityList activities, Ui ui, Storage storage, UserDetails userDetails, GiftList gifts)``` — Displays a list of all past restaurants, activities and gifts based on the user's past history.

These operations are detailed in the ```Parser``` class, where the ```parseCommand(String userInput, UserDetails userDetails)``` method is updated to return a ```ViewHistoryCommand``` when the `"history"` command is entered by the user.

The ```GenerateGiftCommand```, ```GenerateIdeaCommand```, ```GenerateItineraryCommand``` and ```GenerateSmartItineraryCommand``` update the relevant txt files (GiftList.txt, ActivityList.txt, and FoodList.txt), toggling the "U" indicator, which represents uncomplete, to be "C", which represents complete. This is done by calling the markComplete() method under the ```Activity```/```Food```/```Gift``` class. Updating their status to be marked as completed ensures that they are not called in future


## Gift-related Features

This feature enhances the user experience by offering access to a curated list of gift ideas and enabling the generation of random gift suggestions. 

### Feature Components and Interaction

The feature comprise several key components:

- `Gift` Class: Extends `Favourites` class to include gift-specific attributes.
- `GiftList` Class: Manages a collection of `Gift` objects.
- `GenerateGiftCommand` Class: Handles the logic for generating random gift suggestions.
- `ListOptionsCommands` Class: Provides functionality to list gifts among other options of food and activity.
- `ViewHistoryCommand` Class: Displays a history of saved gift ideas.
- `Storage` Class: Manages data persistence for gifts.

**Component Interaction Flow:**
1. Initialisation

    - The `Storage` class loads existing gift data from the `GiftList.txt` file into a `GiftList` object at application startup. 
    - Users interact with the gift-related feature via commands `list`, `gift`, `history`.
   
2. Listing Gifts

    - The `ListOptionsCommand` interprets user input to display the `GiftList` when user inputs `gifts` after `list` command input.
    - This interaction leverages the `Ui` class to output the list to the user.
   
3. Generating Gift Suggestions

    - Upon `gift` command input, `GenerateGiftCommand` is triggered.
    - It fetches a random gift from `GiftList` that is not previously marked as complete (`C`).
    - The user's satisfaction response is handled in a loop that continuously generate new suggestions until `yes` is received, at which point the gift is marked as complete.
   
4. View Gift History

    - The `ViewHistoryCommand` displays gifts marked as complete, showing the user's previously accepted suggestions.

5. Data Saving

    - Changes to gift selections are persisted back to the `GiftList.txt` file via `Storage`, ensuring user choices are saved across different sessions.

#### Detailed Implementation

`Gift` Class

- The `Gift` class inherits from `Favourites`, adding a `completionStatus` attribute. 
- Each `Gift` object holds a description and a completion status to track if the gift has been selected by the user.

`GiftList` Class

- The `GiftList` class maintains a collection of `Gift` objects using `ArrayList<Gift>`.
- It includes methods to access the size of the list, retrieve a specific gift, and fetch a random gift suggestion, filtering out already selected gifts.

`Command` Classes

- `GenerateGiftCommand` executes the logic for interactive gift suggestions, incorporating user feedback loops until a satisfactory gift is found.
- `ListOptionsCommand` segregates listing functionality by the type of list requested by the user (food, activities, gifts), specifically isolating the gift listing process.
- `ViewHistoryCommand` compiles and presents a history of gifts that have been marked as complete.

`Storage` Class

- The `Storage` class is crucial for loading gift data at startup and saving updates when gifts are marked complete. 
- It utilises Java's I/O and Collections to manage the lifecycle of gift data within the application.

#### Design Considerations

- Using a class hierarchy where `Gift` extends `Favourites` allows for easy addition of new types of favourites in the future.
- Segregating functionalities into distinct classes (`Gift`, `GiftList`, `GenerateGiftCommand`, etc.) enhances modularity, making the codebase more maintainable and scalable.
- Random selection from the `GiftList` ensures a diverse range of suggestions, enhancing user experience by preventing repetitive recommendations.
- The interactive approval process for gift suggestions enhances user engagement, making the feature more dynamic and responsive to user preferences.
- Utilising a text file for storing gift data ensures simplicity and reliability, avoiding over-complication with external databases or dependencies.

#### Future Enhancements: Gender-Specific Recommendations

Enhance the gift suggestion feature to provide gender-specific recommendations, allowing the users to refine their searches based on the recipient's gender. 

This enhancement aims to offer more personalised and relevant gift options, improving user satisfaction and the applicability of suggestions.

**Implementation Overview**

The implementation of gender-specific gift recommendations would involve extending the current `Gift` class structure, modifying the `GiftList` management logic, and updating the `GenerateGiftCommand` to interpret and act on additional user inputs specifying gender preferences.

- `Gift` class enhancement: Include a new attribute for gender specificity. This attribute could take values such as "male", "female", or "unisex", indicating the intended recipient's gender for the gift.
- `GiftList` class adjustments: Update to filter gift suggestions based on the new gender attribute. This might involve adding a method to retrieve a random gift filtered by the specified gender.
- `GenerateGiftCommand` modifications: Revise this command to accept and process additional arguments for gender, altering the random gift selection logic to utilise the updated `getRandomGift` method in `GiftList`.

**User Interaction Flow**

The command syntax for generating gift suggestions would be expanded to allow for optional gender specification: `gift`, `gift male`, or `gift female`. This modification enables the application to cater to a broader range of user intentions and preferences, enhancing the personalised nature of the gift suggestion feature.

- By enabling gender-specific gift suggestions, the application can provide more targeted and relevant options, increasing the likelihood of user satisfaction.
- This enhancement also retains flexibility by allowing users to opt for non-gendered suggestions when preferences are not specified or when seeking more universally appealing gift ideas.


## Product scope
### Target user profile

Couples in Singapore

### Value proposition

In today's fast-paced world, maintaining a healthy and exciting relationship can often be challenging for couples. Balancing work, personal responsibilities, and quality time with a partner requires effort and planning. Many couples struggle to come up with new and interesting date ideas that cater to both partners' preferences, leading to a routine that can become stale and uninspiring. This is where Flirt and Fork steps in to rejuvenate the love life of couples by offering a seamless, engaging, and personalised date planning experience.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|first-time user|see a help message|know how to use the features within the app & its parameters|
|v1.0|busy user|effortlessly generate date ideas|not waste time endlessly searching for ideas|
|v1.0|budget conscious user|generate a date itinerary based on price|choose when to splurge and when to save|
|v1.0|user looking for variety|view a variety of date activities|enjoy diverse date experiences|
|v1.0|experienced user of the platform|save some of my preferred activities|reference back to them in future|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
