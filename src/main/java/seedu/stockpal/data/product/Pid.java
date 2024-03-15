package seedu.stockpal.data.product;

public class Pid {
    protected Integer pid;
    public Pid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return this.pid;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Pid)) {
            return false;
        }

        Pid objToCompare = (Pid) obj;
        return this.pid.equals(objToCompare.getPid());
    }

    @Override
    public String toString() {
        return ("PID: " + pid);
    }
}
