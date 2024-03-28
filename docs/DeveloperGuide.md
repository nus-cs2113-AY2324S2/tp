# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


### Daily feature


This daily mechanism is facilitated by a constructor from the `DailyNewsCommand` class. It takes in an input from 
the user and the current list of articles to display the news on published on a particular day to the user.  
This feature also implements the following operations:  
- [Proposed] `DailyNewsCommand#save()` — Saves a news article from the list given to their reading list
- [Proposed] `DailyNewsCommand#back()` — Exits the daily feature loop.

Given below is an example usage of the daily mechanism behaves at each step.

Step 1: The user launches the application. This initialises a list of news articles named `newsArticles` by reading
from a text file.

Step 2: When the user executes the `daily 10 March, 2024` to find news articles on that day. The `DailyNewsCommand`
constructor is called, which searches the `newsArticles` list to find the corresponding news articles. The list of 
articles found are collected into a list `articlesOfTheDay`, which will be output to the user.

Step 3: After being shown the list of newsArticles, the user is able to select news article that he wants to read later
by using the `save(1)` command, which saves the first news article on the list.

Step 4: When the user is done saving the desired news articles, he is able to go back to the main function by using the
`back()` command.

The flow can be seen from the sequence diagram below.
<img src="UML Diagrams/dailyFunctionSequence.png">

### Source Function


The `sourceNews` function in the `NewsOnTheGo` class 
is used to retrieve the source of a news article. 
The function takes in a string and a list of 
`NewsArticle` objects. The string is split into an 
array and the second element (index 1) is parsed as 
an integer. This integer is used as an index to 
retrieve a `NewsArticle` from the list, and the 
source of the news article is then printed.

Here is the code snippet for the `sourceNews` 
function:

```java
/**
 * Enter the news article number as stored in the array, and it will return the source of the news article.
 */
static void sourceNews(String line, List<NewsArticle> list) {
    String[] split = line.split(" ");
    int index = Integer.parseInt(split[1]) + 1;
    System.out.println(list.get(index).getSource());
}
```

### Topics Function
The `showTopics` function in  `NewsOnTheGo` class is used to show the list of topics linked to the current list of news 
articles. 

In `importNewsFromText` in the `NewsImporter` class, the function not only parses the articles from the text file into a
list of `NewsArticle` objects, it also creates a list of `NewsTopic` objects. Each `NewsTopic` object stores a list of 
`NewsArticle` objects for articles related to that specific topic. 

### Filter Function
The `filterNews` function in `NewsOnTheGo` class is used to show the list of articles linked to a specific topic.

This feature also implements the following operations:
- [Proposed] `FilterNewsCommand#save()` — Saves the list of news articles in the topic to their reading list
- [Proposed] `FilterNewsCommand#back()` — Exits the filter topic feature loop.

### User Preferences Feature

This feature allows users to personalize their news feed by specifying topics of interest. The `UserPreferences` class stores and manages these preferences, allowing the application to deliver relevant news articles to the user.

#### Implementation

The User Preferences feature is implemented through the `UserPreferences` class, which manages a set of topics. It includes functionality to add and remove topics from the preferences and handles loading and saving these preferences to a text file.

The main operations of this feature include:

- `UserPreferences.addTopic(String topic)` — Adds a new topic to the user's list of interests.
- `UserPreferences.removeTopic(String topic)` — Removes a topic from the user's list of interests.

The persistence of user preferences is achieved through file I/O operations, specifically using the `java.nio.file.Files` class for reading from and writing to the `userPreferences.txt` file.

#### Example Usage

When a user first starts the application, the `UserPreferences` class is instantiated, automatically loading any previously saved preferences. As the user interacts with the application, they can add or remove topics from their preferences, which are immediately persisted to the file system.

```java
UserPreferences userPrefs = new UserPreferences();
userPrefs.addTopic("technology"); // Adds technology to the list of interested topics.
userPrefs.removeTopic("sports"); // Removes sports from the list of interested topics.
```

#### Persistence Mechanism

The user preferences are saved in a text file named userPreferences.txt, with each line representing a topic of interest. The loadPreferences and savePreferences methods handle the reading and writing of this file, respectively.

```java
private void loadPreferences() {
    try {
        Files.lines(Paths.get(PREFERENCES_FILE))
            .forEach(line -> interestedTopics.add(line.trim().toLowerCase()));
    } catch (IOException e) {
        System.out.println("Could not load user preferences. Starting with an empty list of topics.");
    }
}
```

#### Design Considerations
The design of the User Preferences feature aimed to achieve simplicity and efficiency, opting for a lightweight file-based storage solution to avoid the overhead of more complex persistence mechanisms. 

#### Alternatives Considered
- **Cloud-Based Storage:** Providing cross-device synchronization was deemed unnecessary at this stage, given the application's primary focus on delivering a personalized news experience on individual devices.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
