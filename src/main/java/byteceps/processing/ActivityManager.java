package byteceps.processing;

import byteceps.activities.Activity;
import byteceps.commands.Parser;
import byteceps.errors.Exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public abstract class ActivityManager {
    //    private static ActivityManager instance;
    private final String activityType;
    private final ArrayList<Activity> activityList;
    private final HashMap<String, Integer> activityHashMap;

    public ActivityManager() {
        //        instance = this;
        this.activityType = getActivityType();
        this.activityList = new ArrayList<>();
        this.activityHashMap = new HashMap<>();
    }

    //    public ActivityManager(ArrayList<Activity> activityList, HashMap<String, Integer> activityHashMap) {
    //        //        instance = this;
    //        this.activityType = getActivityType();
    //        this.activityList = activityList;
    //        this.activityHashMap = activityHashMap;
    //    }

    //    public static ActivityManager getInstance() {
    //        return instance;
    //    }

    public abstract void execute(Parser parser) throws Exceptions.InvalidInput,
            Exceptions.ErrorAddingActivity, Exceptions.ActivityExistsException,
            Exceptions.ActivityDoesNotExists;

    public void add(Activity activity) throws Exceptions.ActivityExistsException, Exceptions.ErrorAddingActivity {
        String activityName = activity.getActivityName();
        if (this.activityHashMap.containsKey(activityName)) {
            throw new Exceptions.ActivityExistsException(
                    String.format("The %s entry: %s already exists", this.activityType, activityName)
            );
        }

        int index = activityList.size(); // index of this activity in the ArrayList once added
        boolean listReturn = activityList.add(activity);
        Integer hashMapReturn = activityHashMap.put(activityName, index);

        if (listReturn && hashMapReturn != null) {
            throw new Exceptions.ErrorAddingActivity(
                    String.format("Error adding %s entry: %s", this.activityType, activityName)
            );
        }
    }

    public void delete(Activity activity) throws Exceptions.ActivityDoesNotExists {
        String activityName = activity.getActivityName();
        Integer index = this.activityHashMap.get(activityName);
        if (index == null) {
            throw new Exceptions.ActivityDoesNotExists(
                    String.format("The %s entry: %s does not exist and cannot be deleted",
                            this.activityType, activityName)
            );
        }

        activityList.remove(index.intValue());

    }

    public Activity retrieve(String activityName) throws Exceptions.ActivityDoesNotExists {
        Integer index = this.activityHashMap.get(activityName);
        if (index == null) {
            throw new Exceptions.ActivityDoesNotExists(
                    String.format("The %s entry: %s does not exist",
                            this.activityType, activityName)
            );
        }

        return activityList.get(index);
    }

    public void list() {
        if (activityList.isEmpty()) {
            System.out.println("Your list is empty");
            return;
        }

        System.out.printf("listing %s\n", activityType);
        for (ListIterator<Activity> it = activityList.listIterator(); it.hasNext(); ) {
            Activity currentActivity = it.next();
            System.out.printf("%d. %s\n", it.nextIndex(), currentActivity.getActivityName());
        }
    }

    //    public boolean hasActivity(String activityName) {
    //        Integer index = this.activityHashMap.get(activityName);
    //        return index != null;
    //    }
    //
    //    public void deleteAllActivities() {
    //        activityList.clear();
    //    }

    public abstract String getActivityType();
}
