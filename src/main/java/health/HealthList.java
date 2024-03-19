package health;

import java.util.ArrayList;

public class HealthList extends ArrayList<Health> {
    private static final ArrayList<Bmi> bmis = new ArrayList<>();
    private static final ArrayList<Period> periods = new ArrayList<>();

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

    public static void addPeriod(Period period) {
        periods.add(period);
    }

    public static void showLatestPeriod() {
        int currentIndex = periods.size();
        System.out.println(periods.get(currentIndex - 1));
    }
}
