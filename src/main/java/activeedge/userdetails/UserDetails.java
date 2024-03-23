package activeedge.userdetails;

public class UserDetails {
    protected Integer value;

    public UserDetails(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}
