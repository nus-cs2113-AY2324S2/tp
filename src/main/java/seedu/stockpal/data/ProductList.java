package seedu.stockpal.data;

import seedu.stockpal.data.product.Product;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;

import java.util.ArrayList;

public class ProductList extends ArrayList<Product> {
    public ArrayList<Product> products = new ArrayList<Product>();

    /**
     * Finds product with a specific Pid and
     * returns its index in the product list.
     *
     * @param pid Product ID to search for.
     * @return Index of the product in the product list.
     */
    public int findProductIndex(Pid pid) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.isPidMatch(pid)) {
                return i;
            }
        }
        return -1;
    }

    public void updateProduct(int productIndex, Name newName, Quantity newQuantity
            , Description newDescription, Price newPrice) {
        Product updatedProduct = products.get(productIndex);
        if (newName != null) {
            updatedProduct.setName(newName);
        }
        if (newQuantity != null) {
            updatedProduct.setQuantity(newQuantity);
        }
        if (newDescription != null) {
            updatedProduct.setDescription(newDescription);
        }
        if (newPrice != null) {
            updatedProduct.setPrice(newPrice);
        }
        products.set(productIndex, updatedProduct);
    }
}
