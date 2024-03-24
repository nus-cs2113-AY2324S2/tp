# EconoCraft Pro v1.0 
# User Guide

## Introduction

EconoCraft is a single player text adventure game where a player takes on the role of a business owner or manager,
tasked with growing their company from a small startup to a large corporation. The game would simulate real-world
business challenges, including resource management, market analysis, product development, and financial planning to
help students prepare for the future challenges.

## Quick Start

1. Ensure you have Java 11 installed on your computer.
2. Download the latest `econoCraft.jar` from [here](https://github.com/AY2324S2-CS2113-T11-4/tp/releases/download/v1.0/econoCraft.jar).
3. Copy the file to the folder you want to use as the home folder for Zuke.
4. Open a terminal and navigate to the folder where `econoCraft.jar` is located.
5. Run the command `java -jar econoCraft.jar` to start the game. The chatbot should start and display the welcome message.
6. Follow the game instructions to start playing.

## Features 

### Feature - Work
Allows player to work to earn money by playing a small typing game.

Format: `work`

Expected outcome:
```
==============================
Welcome to the Typing Game!
Type the following text as fast as you can:
==============================
==============================
The quick brown fox jumps over the lazy dog.
==============================
==============================
Press ENTER to start
==============================
```
player will be prompted to type the given text as fast as they can. The faster they type, the more money they earn.
 
Examples:
```
Type here: The quick brown fox jumps over the
==============================
Good job! You finished within the time limit!
==============================
==============================
You typed at 77% accuracy in 12.04 seconds!
Great job!
==============================
==============================
You have earned $770
==============================
```

### Feature - Exercise
Allows player to exercise to increase their health.

Format: `exercise`

Expected outcome:
```
==============================
Please choose your mark: X or O
==============================
```
Player would then select which mark they would like to use, the game would start after the selection.

Format: `X` or `O`

Expected outcome:
```
==============================
- - - 
- - - 
- - - 
==============================
==============================
Player O, enter your move (row [1-3] column [1-3]):
==============================
```
Player would then enter the row and column they would like to place their mark. 
The bot would then place the other mark in a random position.

Format: `row column`

Example of usage:`1 1`

Expected outcome:
```
==============================
O - - 
- - - 
- - - 
==============================
==============================
AI's turn!
==============================
==============================
O - - 
- - - 
- - X 
==============================
==============================
Player O, enter your move (row [1-3] column [1-3]):
==============================
```
The game would continue until a player wins or the board is full.

Examples:
```
==============================
O O O 
- X - 
- - X 
==============================
==============================
Siuuuuu, player O wins!
==============================
```
This would increase 10% of the player's health if player wins the game.

### Feature - Rest
Allows player to rest to increase their health. Player would be tasked to answer two True or False question
related to cs2113.

Format: `rest`

Expected outcome:
```
==============================
Welcome to the MCQ Game!
Answer the following questions:
==============================
==============================
As per the textbook, brown-field projects are usually 
harder than green-field projects. True or False?
==============================
==============================
Type T for true and F for false
==============================
```
Player would then enter `T` or `F` to answer the question.

Format: `T` or `F`

Example of usage:`T`

Expected outcome:
```
==============================
Incorrect!
==============================
```
After two questions, the player would be informed of their score.

Examples:
```
You answered 1 questions correctly.
```

This would increase 10% of the player's health if player achieves more than 50% correct answers.

### Feature - Check Status

Allows player to check their current status.

Format: `status`

Expected outcome:
```
==============================
Current Status:
Asset: Your name is :zhu
occupation :artificial intelligence
current health :99
current asset: 5770
==============================
```

### Feature - Help

Shows a list of commands that the player can use when player is stuck.

Format: `help`

Expected outcome:
```
==============================
Enter ur action!
work - to work
rest - to rest
exercise - to exercise
status - to check status
bye - to exit
==============================
```

## FAQ

**Q**: How do I know what action I should do at different stages of the game? 

**A**: No worries! The game would prompt you to enter your action during the game. 
If you are stuck, you can use the `help` command to see a list of commands that you can use.

## Command Summary

| Description  | Command    |
|--------------|------------|
| Work         | `work`     |
| Exercise     | `exercise` |
| Rest         | `rest`     |
| Check Status | `status`   |
| Help         | `help`     |
