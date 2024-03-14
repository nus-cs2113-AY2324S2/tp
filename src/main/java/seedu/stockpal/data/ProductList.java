package seedu.stockpal.data;

import seedu.stockpal.data.product.Product;

import java.util.ArrayList;

public class ProductList {
    public ArrayList<Product> products = new ArrayList<Product>();


    public void addProduct(Product toAdd) {

        products.add(toAdd);
    }


}
