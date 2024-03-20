# User Guide
_Florizz is your personal digital florist which helps people in Singapore to curate flowers to create bouquets for all occasions._

## Features
### Viewing help: `help`
Shows a list of commands and its corresponding function

Format: `help`

Expected output:

```
Here are the list of commands you can use:
1. new <bouquet_name> - Add a bouquet
2. delete <bouquet_name> - Delete a bouquets
3. mybouquets - List current saved bouquets
4. info <flower_name> - Provide information on chosen flower
5. flower - Shows a list of flowers that can be added into mybouquets
6. flower <occasion> - Shows a list of flowers associated with said occasion
7. bye - Exits the programme
```

### Create a new bouquet: `new`
Creates an empty bouquet to add flowers to later

Format: `new NAME`

Bouquet name must not already exist

Examples:
`new For Girlfriend`

Expected output:
```
Added new bouquet to list: 
For Girlfriend
```

### Delete existing bouquet: `delete`
Deletes a bouquet from the bouquet list

Format:  `delete <bouquetName>`

Bouquet of that name must exist in the list

Example:
`delete For Mother`

Expected output:
```
Deleted bouquet: 
For Mother
```

### View existing bouquets: mybouquets
Views all the bouquets in the list

Format: `mybouquets`

Expected output:
```
Here are the list of your saved bouquets:
1. For Girlfriend :
      No flowers added so far
```

### List all available flowers: flowers
List all available flowers in the database currently, also able to filter presented flowers according to occasion, colour and meaning

Format: `flower <occassion>`

Example: `flower funeral`

Expected output:
```
Here are all the flowers related to funeral: 
Lily
Chrysanthemum
```
### View detailed info of a flower: info

Get detailed info (colour, meaning etc) about a specific flower in the database

Format: `info <flowerName>`

Example: `info Orchid`

Expected Output:
```
Name: Orchid
Colour: White
Occasion: Wedding
```

### Add flower: add

Adds a flower into a bouquet

Format: `add <flowerName> /q <quantity> /to <bouquetName>`

- Flower must exist in the database
- Quantity must be a positive integer
- Bouquet must exist in the database

Examples:
- `add Rose /q 3 /to Bouquet for mom`
- `add Babys breath /q 2 /to Sister’s graduation`

Expected Output:
```
You have successfully added the following: 
    - 3 x Rose -> Bouquet: For Girlfriend
Here are the list of your saved bouquets:
1. For Girlfriend :
    - 3 x Rose
```

### Remove flower: remove

Removes a flower from a bouquet

Format: `remove <flowerName> /q <quantity> /from <bouquetName>`

- Flower must exist in the database and the bouquet specified
- Quantity must be more than 0 and a valid number
- Bouquet must exist in the database

Examples:
- `remove Rose /q 1 /from For Girfriend`
- `remove Gerbera /q 3 /from Valentine’s Day`

Expected output:
```
You have successfully added the following: 
    - 1 x Rose -> Bouquet: For Girlfriend
Here are the list of your saved bouquets:
1. For Girlfriend :
    - 2 x Rose
```

### List occasions: occasion
Shows a list of occasions for buying flowers that users can choose. Upon choosing an occasion, a list of flowers that is associated with the chosen occasion will be shown.

Format: `occasion`

Expected output:
```
Here are all the occasions associated with the available flowers: 
mother's day
funeral
valentines
wedding
```

### Exit programme: exit

Exits the program.

Format: `bye`

Expected output:
```
Enjoy your bouquet! Thank you for using Florizz
```

