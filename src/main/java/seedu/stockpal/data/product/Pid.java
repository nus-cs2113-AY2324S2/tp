package seedu.stockpal.data.product;

public class Pid {
    protected Integer pid;
    public Pid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return ("PID: " + pid);
    }
}
