package seedu.stockpal.data;

import seedu.stockpal.data.product.Pid;
import java.time.LocalDateTime;

//@@author EdmundTangg
public class Transaction {

    private Integer changeInQuantity;

    private final LocalDateTime time;
    private final Pid pid;


    public Transaction(Pid pid,
                       Integer changeInQuantity,
                       LocalDateTime time
                       ) {
        this.pid = pid;
        this.changeInQuantity = changeInQuantity;
        this.time = time;
    }



}
