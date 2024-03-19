package health;

import java.util.ArrayList;

public class HealthList extends ArrayList<Health> {
    private static final ArrayList<Bmi> bmis = new ArrayList<>();

    public static void addBmi(Bmi bmi) {
        bmis.add(bmi);
    }

    public static void showCurrentBmi() {
        int currentIndex = bmis.size();
        System.out.println(bmis.get(currentIndex - 1));
    }

    public static void showBmiHistory() {
        for (Bmi bmi : bmis) {
            System.out.println(bmi);
        }
    }
}
