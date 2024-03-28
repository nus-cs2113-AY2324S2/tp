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
