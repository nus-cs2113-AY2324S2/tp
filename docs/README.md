# FitNUS

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
