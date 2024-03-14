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

//    public static void main(String[] args) {
//        Map<String, Float> userList = new HashMap<>(Map.of(
//                "Shaoliang", 5.0f,
//                "Avril", -5.0f,
//                "Hafiz", 10.0f,
//                "Mukund", -10.0f
//        ));
//
//        Balance balance = new Balance("Junxiang", userList);
//
//        balance.printBalance();
//    }
}
