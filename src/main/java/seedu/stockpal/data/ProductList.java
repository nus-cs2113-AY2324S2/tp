package seedu.stockpal.data;

import seedu.stockpal.data.product.Product;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;

import java.util.ArrayList;

public class ProductList {
    public ArrayList<Product> products = new ArrayList<Product>();

    /**
     * @param toAdd Add a product to our list.
     */
    public void addProduct(Product toAdd) {
        products.add(toAdd);
    }

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

    public void deleteProduct(Pid productPid) {
        int productIndex = findProductIndex(productPid);
        products.remove(productIndex);
    }

    public void updateProduct(int productIndex, Name newName, Quantity newQuantity
            , Description newDescription, Price newPrice) {
        Product updatedProduct = products.get(productIndex);

        if (!newName.isNull()) {
            updatedProduct.setName(newName);
        }
        if (!newQuantity.isNull()) {
            updatedProduct.setQuantity(newQuantity);
        }
        if (!newDescription.isNull()) {
            updatedProduct.setDescription(newDescription);
        }
        if (!newPrice.isNull()) {
            updatedProduct.setPrice(newPrice);
        }
        products.set(productIndex, updatedProduct);
    }

    public void increaseAmount(int productIndex, Integer amountToIncrease) {
        Product updatedProduct = products.get(productIndex);
        updatedProduct.increaseQuantity(amountToIncrease);
    }

    public void decreaseAmount(int productIndex, Integer amountToDecrease) {
        Product updatedProduct = products.get(productIndex);
        updatedProduct.decreaseQuantity(amountToDecrease);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int getSize() {
        return products.size();
    }

    public Product get(int i) {
        return products.get(i);
    }

    public Quantity getProductQuantity (Integer productIndex) {
        Product updatedProduct = products.get(productIndex);
        return updatedProduct.getQuantity();
    }
    public ArrayList<Product> getProducts () {
        return products;
    }
}
