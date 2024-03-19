# FitNUS
## Project Introduction
FitNUS is a CLI application that aims to help combat diabetes and the overconsumption of calories, sugar, and
carbohydrates. Promote healthy lifestyle.

Users are able to track the meals and drinks they have in a day.

## Table of Contents
<!-- TOC -->
[1) Features List](#1-features-list)
* [1.1 Information for users](#11-information-for-users)
   * [1.1.1 Viewing all commands:** `help`](#111-viewing-all-commands-help-)
* [1.2 For user to add data](#12-for-user-to-add-data)
   * [1.2.1 Add a meal eaten: `ate`](#121-add-a-meal-eaten-ate)
   * [1.2.2 Add a drink: `drink`](#122-add-a-drink-drink)
   * [1.2.3 Add water: `water`](#123-add-water-water)
* [1.3 For data retrieval](#13-for-data-retrieval)
   * [1.3.1 Find the information about a certain meal: `infoMeal`](#131-find-the-information-about-a-certain-meal-infomeal)
   * [1.3.2 Find the information about a certain drink: `infoDrink`](#132-find-the-information-about-a-certain-drink-infodrink)
   * [1.3.3 View daily calories consumed: `calories`](#133-view-daily-calories-consumed-calories)
   * [1.3.4 View daily carbohydrates consumed: `carbs`](#134-view-daily-carbohydrates-consumed-carbs)
   * [1.3.5 View daily proteins consumed: `protein`](#135-view-daily-proteins-consumed-protein)
   * [1.3.6 View daily fat consumed: `fat`](#136-view-daily-fat-consumed-fat)
   * [1.3.7 View daily sugar consumed: `sugar`](#137-view-daily-sugar-consumed-sugar)
   * [1.3.8 View daily fiber consumed: `fiber`](#138-view-daily-fiber-consumed-fiber)
   * [1.3.9 View daily water consumption: `viewWater`](#139-view-daily-water-consumption-viewwater)
* [1.4 For listing arrays](#14-for-listing-arrays)
   * [1.4.1 List meal intake: `listMeals`](#141-list-meal-intake-listmeals)
   * [1.4.2 List drink intake: `listDrinks`](#142-list-drink-intake-listdrinks)
   * [1.4.3 List entire food intake for the day: `listEverything`](#143-list-entire-food-intake-for-the-day-listeverything)
* [1.5 For editing existing data](#15-for-editing-existing-data)
   * [1.5.1 Edit an existing meal after inserted: `editMeal`](#151-edit-an-existing-meal-after-inserted-editmeal)
   * [1.5.2 Edit an existing drink after inserted: `editDrink`](#152-edit-an-existing-drink-after-inserted-editdrink)
   * [1.5.3 Edit water intake after inserted: `editWater`](#153-edit-water-intake-after-inserted-editwater)
* [1.6 For deleting data](#16-for-deleting-data)
   * [1.6.1 Delete certain meal entry: `deleteMeal`](#161-delete-certain-meal-entry-deletemeal)
   * [1.6.2 Delete certain drink entry: `deleteDrink`](#162-delete-certain-drink-entry-deletedrink)
* [1.7 For clearing data](#17-for-clearing-data-)
   * [1.7.1 Clear all entries: `clear`](#171-clear-all-entries-clear)
* [1.8: Exit program](#18-exit-program)
   * [1.8.1 Exit the app: `exit`](#181-exit-the-app-exit)
<!-- TOC -->

## 1) Features List
### 1.1 Information for users
### 1.1.1 Viewing all commands:** `help`
Shows a list of all possible command inputs.  
**Format**: help  
**Sample Input**: help  
**Expected Output**:  
here's all the valid commands i recognise:
- Add a meal eaten: ate m/MEAL s/SERVING_SIZE
- Add a drink: drink d/DRINK s/SERVING_SIZE
- Add water: water s/SERVING_SIZE
- Find the information about a certain meal: infoMeal MEAL
- Find the information about a certain drink: infoDrink DRINK
- View daily calories consumed: calories
- View daily carbohydrates consumed: carbs
- View daily proteins consumed: protein
- View daily fat consumed: fat
- View daily sugar consumed: sugar
- View daily fiber consumed: fiber
- View daily water consumption: viewWater
- List meal intake: listMeals
- List drink intake: listDrinks
- List entire food intake for the day: listEverything
- Edit an existing meal after inserted: editMeal INDEX s/NEW_SERVING_SIZE
- Edit an existing drink after inserted: editDrink INDEX s/NEW_SERVING_SIZE
- Delete certain meal entry: deleteMeal INDEX
- Delete certain drink entry: deleteDrink INDEX
- Clear all entries: clear
- Exit the app: exit

### 1.2 For user to add data
### 1.2.1 Add a meal eaten: `ate`
Adds a meal to the list of meals  
**Format**: ate m/MEAL s/SERVING_SIZE  
**Sample Input**: ate m/Chicken Rice s/1  
**Expected Output**: Added 1 serving of Chicken Rice

### 1.2.2 Add a drink: `drink`
Adds a drink to the list of drinks  
**Format**: drink d/DRINK s/SERVING_SIZE  
**Sample Input**: drink d/Lemon Tea s/100  
**Expected Output**: Added 100ml of Lemon Tea

### 1.2.3 Add water: `water`
Adds water (in ml) to the daily water intake count  
**Format**: water s/SERVING_SIZE  
**Sample Input**: water s/200  
**Expected Output**: Added 200ml of water

## 1.3 For data retrieval
### 1.3.1 Find the information about a certain meal: `infoMeal`
For the specified meal, display its nutritional content to the user  
**Format**: infoMeal MEAL  
**Sample Input**: infoMeal chicken rice  
**Expected Output**:  
Meal: chicken rice (per serving)  
Calories: 400  
Carbs: 50  
Protein: 30  
Fat: 20  
Fiber: 10  
Sugar: 5

### 1.3.2 Find the information about a certain drink: `infoDrink`
For the inputed drink, display its nutritional content to the user  
**Format**: infoDrink DRINK  
**Sample input**: infoDrink sprite  
**Expected output**:    
SPRITE (473 ml)  
Calories: 194 kcal  
Carbs: 50g  
Protein: 0.2g  
Fat: 0.1g

### 1.3.3 View daily calories consumed: `calories`
Display current total calorie intake for the day   
**Format**: calories    
**Expected output**: Total calories: 100 cal

### 1.3.4 View daily carbohydrates consumed: `carbs`
Display current total carbohydrates intake for the day  
**Format**: carbs  
**Expected output**: Total Carbohydrates: 150 grams

### 1.3.5 View daily proteins consumed: `protein`
Display current total protein intake for the day  
**Format**: protein  
**Expected output**: Total proteins: 100 grams

### 1.3.6 View daily fat consumed: `fat`
Display current total fat intake for the day  
**Format**: fat  
**Expected output**: Total fat: 50 grams

### 1.3.7 View daily sugar consumed: `sugar`
Display current total sugar intake for the day  
**Format**: sugar  
**Expected output**: Total sugar: 20 grams

### 1.3.8 View daily fiber consumed: `fiber`
Display current total fiber intake (g) for the day  
**Format**: viewFiber  
**Expected output**: Total fiber: 20 grams

### 1.3.9 View daily water consumption: `viewWater`
Display current total water intake (in ml) for the day  
**Format**: viewWater  
**Expected output**: Total water intake: 0 ml

## 1.4 For listing arrays
### 1.4.1 List meal intake: `listMeals`
List all the meals user inputted today  
**Format**: listMeals   
**Expected output**:   
here's what you have eaten today  
1.pizza (serving size: 1)

### 1.4.2 List drink intake: `listDrinks`
List all the drinks user inputted today  
**Format**: listDrinks  
**Expected output**:  
here's what you have drank today  
1.sprite (serving size: 1)  
Total water intake: 0 ml

### 1.4.3 List entire food intake for the day: `listEverything`
List all the drinks and meals inputted today  
**Format**: listEverything  
**Expected output**:  
here's what you have consumed today  
1.pizza (serving size: 1)  
2.sprite (serving size: 1)  
Total water intake: 0 ml

## 1.5 For editing existing data
### 1.5.1 Edit an existing meal after inserted: `editMeal`
For a meal that was inputted in the day, edit its serving size  
**Format**: editMealServingSize INDEX s/NEW_SERVING_SIZE  
**Sample input**: editMeal 2 s/2  
**Expected output**: Pizza has been edited to 2 servings

### 1.5.2 Edit an existing drink after inserted: `editDrink`
For a drink that was inputted in the day, edit its serving size  
**Format**: editDrinkServingSize INDEX s/NEW_SERVING_SIZE  
**Sample input**: editDrink 1 s/200  
**Expected output**: Sprite has been edited to 200 ml

### 1.5.3 Edit water intake after inserted: `editWater`
Edit serving size of total water intake  
**Format**: editWaterIntake s/TOTAL_WATER_INTAKE  
**Sample input**: editWaterIntake 200  
**Expected output**: Total water has been edited to 200 ml

## 1.6 For deleting data
### 1.6.1 Delete certain meal entry: `deleteMeal`
For a meal that was inputted in the day, delete its input based on its index in the meal list
**Format**: deleteMeal INDEX
**Sample Input**: deleteMeal 1
**Expected output**: Removed Chicken Rice From Meals

### 1.6.2 Delete certain drink entry: `deleteDrink`
For a drink that was inputted in the day, delete its input based on its index in the drink list  
**Format**: deleteDrink INDEX  
**Sample input**: deleteDrink 1  
**Expected output:**  Removed Iced Lemon Tea From Drinks

## 1.7 For clearing data
### 1.7.1 Clear all entries: `clear`
Clear all entries in mealList and drinkList  
**Format**: clear  
**Expected output**: All entries have been deleted

## 1.8: Exit program
### 1.8.1 Exit the app: `exit`
**Format**: exit  
**Expected output**: Bye. Hope to see you again soon!
