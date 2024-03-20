package seedu.stockpal.data.product;

public class Pid {
    private final Integer pid;

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

    /**
     * Converts the PID to the specific format for saving to the data file.
     *
     * @return A formatted string containing the PID for saving.
     */
    public String toSave() {
        return this.pid.toString();
    }
}
