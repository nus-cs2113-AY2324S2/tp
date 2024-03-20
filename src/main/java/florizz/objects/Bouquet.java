package florizz.objects;

import florizz.core.FlorizzException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class Bouquet {
    private final String bouquetName;
    private HashMap<Flower, Integer> flowerHashMap;

    public Bouquet() {
        this("");
    }
    public Bouquet(String bouquetName){
        this.bouquetName = bouquetName;
        this.flowerHashMap = new HashMap<Flower,Integer>();
    }
    @Override
    public String toString() {
        return bouquetName;
    }

    public HashMap<Flower, Integer> getFlowerHashMap() {
        return flowerHashMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Bouquet)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Bouquet c = (Bouquet) obj;

        // Compare the data members and return accordingly
        return Objects.equals(c.bouquetName.toUpperCase(), this.bouquetName.toUpperCase());
    }

    /**
     * check if flower exist in bouquet.
     *
     * @param flowerName
     * @return boolean true if flower exist
     */
    public boolean doesFlowerExist(Flower flowerName) {
        if (flowerHashMap.get(flowerName) == null) {
            return false;
        }
        return true;
    }

    /**
     * add flowers into bouquet
     *
     * @param flowerName
     * @param quantity
     */
    public void addFlower(Flower flowerName, Integer quantity) {
        if (doesFlowerExist(flowerName)) {
            Integer currentQuantity = getFlowerQuantity(flowerName);
            Integer newQuantity = currentQuantity + quantity;
            flowerHashMap.replace(flowerName, newQuantity);
        } else {
            flowerHashMap.put(flowerName,quantity);
        }
    }

    public boolean removeFlower(Flower flowerName, Integer quantity) {
        // if flower already in bouquet
        if (doesFlowerExist(flowerName)) {
            Integer currentQuantity = getFlowerQuantity(flowerName);
            Integer newQuantity = currentQuantity - quantity;
            if (newQuantity < 0) {
                System.out.println("Tried to remove more than quantity available, quantity set to 0");
                newQuantity = 0;
            }
            flowerHashMap.replace(flowerName, newQuantity);
            return true;
        }
        return false;

    }

    /**
     * Get quantity of a flower. If flower does not exist in bouquet, return 0
     *
     * @param flowerName
     * @return
     */
    public Integer getFlowerQuantity(Flower flowerName) {
        if (flowerHashMap.get(flowerName) == null) {
            return 0;
        }
        return flowerHashMap.get(flowerName);
    }

    /**
     * get bouquet name
     * @return
     */
    public String getBouquetName() {
        return this.bouquetName;
    }
}
