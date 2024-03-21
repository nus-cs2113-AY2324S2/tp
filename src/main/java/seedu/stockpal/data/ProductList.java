package seedu.stockpal.data;

import java.util.List;
import java.util.ArrayList;

import seedu.stockpal.data.product.Product;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;
import seedu.stockpal.exceptions.PidNotFoundException;
import seedu.stockpal.ui.Ui;

public class ProductList {
    public List<Product> products = new ArrayList<>();

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
     *     If product is not in the product list, return -1.
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

    public void deleteProduct(Pid productPid) throws PidNotFoundException {
        int productIndex = findProductIndex(productPid);
        if (productIndex == -1) {
            throw new PidNotFoundException("Product with PID: " + productPid.toString() + " not found");
        }
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

    public List<Product> getProducts() {
        return products;
    }

    public String toSave(Integer productIndex) {
        Product currProduct = products.get(productIndex);
        return currProduct.toSave();
    }

    public void checkThresholdQuantity () {
        for (Product product : products) {
            Quantity productQuantity = product.getQuantity();
            if (productQuantity.getQuantity() < 20) {
                Ui.printLowQuantityProducts();
                Ui.printToScreen(product.getName() + " " + product.getQuantity().toString());
            }
        }
    }

}
