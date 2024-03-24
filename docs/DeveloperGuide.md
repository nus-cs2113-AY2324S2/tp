### NewCommand Class

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Add feature*** is facilitated by stockPal.

The NewCommand class is responsible for adding a new product to the inventory in the StockPal application.

**Attributes**
* name: The name of the product. 
* quantity: The initial quantity of the product. 
* price: The price of the product. 
* description: The description of the product.

**Methods**
* `NewCommand`: Constructor for creating a new instance of the NewCommand class. 
* `execute`: Method to add the new product to the product list. 
* `createProduct`: Method to create a new product with a unique product ID.


**Usage**

How the `newCommand` class works:

1. User will input the necessary parameters:
   `name`, `quantity`, `price`, and `description`.
2. `newCommand()` constructor will be invoked to assign attributes' values.
3. `Execute()` will be invoked. A new stock will be created with `createProduct()`
and added to the list. 