package calculation;

import constants.UOM;
import constants.uomList;
import item.Item;
public class quantityCalculator {

    public int convertUOM(UOM uom) {
        int value = 0;
        switch (uom) {
        case EA:
            //fallthrough
        case PIECE:
            value = 1;
            break;
        case BOX:
            value = 6;
            break;
        case DOZEN:
            value = 12;
            break;
        case CTN:
            value = 24;
            break;
        default:
            break;
        }

        return value;
    }

    public void updateQty(Item item) {
        item.setQuantity(item.getQuantity() + convertUOM(item.getUom()));
    }
}
