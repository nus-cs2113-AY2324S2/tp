package activeedge.userdetails;

public class LogHeight extends UserDetails {
    public LogHeight(Integer value) {
        super(value);
    }

    public String toString() { return "Height " + this.getValue() + " cm";}
}
