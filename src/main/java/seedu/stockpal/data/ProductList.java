package seedu.stockpal.data;

import java.util.List;
import java.util.ArrayList;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;
import seedu.stockpal.exceptions.InsufficientAmountException;
import seedu.stockpal.exceptions.InventoryQuantityOverflowException;
import seedu.stockpal.exceptions.NoLowQuantityException;
import seedu.stockpal.exceptions.PidNotFoundException;
import seedu.stockpal.ui.Ui;

import static seedu.stockpal.common.Messages.MESSAGE_ERROR_PID_NOT_FOUND;
import static seedu.stockpal.ui.Ui.printToScreen;
import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;


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
    public int findProductIndex(Pid pid) throws PidNotFoundException {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.isPidMatch(pid)) {
                return i;
            }
        }
        throw new PidNotFoundException(MESSAGE_ERROR_PID_NOT_FOUND + pid.getPid());
    }

    public void deleteProduct(Pid productPid) throws PidNotFoundException {
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
            updatedProduct.getQuantity().notifyLowQuantity(newQuantity);
        }
        if (!newDescription.isNull()) {
            updatedProduct.setDescription(newDescription);
        }
        if (!newPrice.isNull()) {
            updatedProduct.setPrice(newPrice);
        }
        products.set(productIndex, updatedProduct);
    }

    /**
     * Method to call the driver code(updateQuantity) to increase quantity.
     *
     * @param productIndex The PID of the specific product
     * @param amountToIncrease Amount to increase
     * @return true if quantity is updated successfully and false if exception is thrown
     */
    public boolean increaseAmountCaller(int productIndex, int amountToIncrease) {
        Product updatedProduct = products.get(productIndex);
        Quantity initialQuantity = updatedProduct.getQuantity();
        return updateQuantity(initialQuantity, amountToIncrease, "increase");
    }

    /**
     * Method to call the driver code(updateQuantity) to decrease quantity.
     *
     * @param productIndex The PID of the specific product
     * @param amountToDecrease Amount to decrease
     * @return true if quantity is updated successfully and false if exception is thrown
     */
    public boolean decreaseAmountCaller(int productIndex, int amountToDecrease) {
        Product updatedProduct = products.get(productIndex);
        Quantity initialQuantity = updatedProduct.getQuantity();
        return updateQuantity(initialQuantity, amountToDecrease, "decrease");
    }

    /**
     * Driver function to update the quantity of the products based on the
     * operation (increase / decrease).
     *
     * @param initialQuantity initial quantity object
     * @param amount Amount to increase/decrease
     * @param operation Increase / decrease quantity
     * @return true is quantity updated successfully, else false if exception thrown
     */
    private boolean updateQuantity(Quantity initialQuantity, int amount, String operation) {
        assert amount > 0 : "Amount should be greater than zero.";
        assert ("increase".equals(operation) || "decrease".equals(operation)) :
                "Operation should be 'increase' or 'decrease'.";
        try {
            if ("increase".equals(operation)) {
                initialQuantity.updateIncreaseQuantity(initialQuantity, amount);
            } else {
                initialQuantity.updateDecreaseQuantity(initialQuantity, amount);
            }
            initialQuantity.notifyLowQuantity(initialQuantity);
            Ui.printToScreen("Quantity updated. " + initialQuantity);
            return true;
        } catch (InventoryQuantityOverflowException iqoe) {
            Ui.printToScreen("Overflow detected. No change to quantity. " + initialQuantity);
            return false;
        } catch (InsufficientAmountException ise) {
            Ui.printToScreen("Insufficient amount in inventory. " +
                    "No change to quantity. " + initialQuantity);
            return false;
        }
    }


    /**
     * @param productList ProductList object.
     * @param keyword Keyword to search for.
     */
    public static void findKeyword(ProductList productList, String keyword) {
        ProductList findList = new ProductList();
        for (int i = 0; i < productList.getSize(); i ++) {
            List<Product> products = productList.getProducts();
            Product product = products.get(i);
            String productName = product.getName().getName().toLowerCase();

            if (productName.equals(keyword)) {
                findList.addProduct(product);
            }
        }

        if (findList.isEmpty()) {
            printToScreen(Messages.MESSAGE_EMPTY_FIND_LIST);
            return;
        }

        Ui.printListTasks(findList);
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

    /**
     * Check if a product has low quantity.
     * If it has low quantity, throw exception
     *
     * @throws NoLowQuantityException exception thrown when there are no
     *          products with low quantity
     */
    public void checkLowQuantityProducts () throws NoLowQuantityException {
        boolean hasLowQuantity = false;

        Ui.printLowQuantityAlert();
        for (Product product : products) {
            Quantity productQuantity = product.getQuantity();
            if (productQuantity.isLowQuantity(product)) {
                Ui.printToScreen (product.getPid() + " | " + product.getName() + " | " +
                        productQuantity);
                Ui.printToScreen(HORIZONTAL_LINE);
                hasLowQuantity = true;
            }
        }
        if (!hasLowQuantity) {
            throw new NoLowQuantityException("No products with low quantity");
        }
    }

    /**
     * Driver function to print low quantity products from the product list.
     */
    public void printLowQuantityProducts () {
        try {
            checkLowQuantityProducts();
        } catch (NoLowQuantityException nlqe) {
            Ui.printNoLowQuantity();
        }
    }
}
