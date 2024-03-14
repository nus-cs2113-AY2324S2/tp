import java.util.HashMap;
import java.util.Map;

public class Balance {
    protected String userName;
    protected Map<String, Float> userList = new HashMap<>();

    public Balance(String userName, Map<String, Float> userList) {
        this.userName = userName;
        this.userList = userList;
    }

    public void printBalance() {
        String firstLine = String.format("User %s's Balance List:", userName);
        System.out.println(firstLine);

        for (Map.Entry<String, Float> entry : userList.entrySet()) {
            String balanceLine = String.format("  %s : %.2f", entry.getKey(), entry.getValue());
            System.out.println(balanceLine);
        }

        System.out.println("End of Balance List");
    }
}
