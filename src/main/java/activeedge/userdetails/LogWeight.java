package activeedge.userdetails;

public class LogWeight extends UserDetails {
    public LogWeight(Integer value) { super(value); }

    public String toString() {
        return "Weight " + this.getValue() + " kg";
    }
}
