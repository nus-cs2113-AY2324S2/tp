package byteceps.activities;

public class Activity {
    protected String activityName;

    public Activity(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    @Override
    public int hashCode() {
        return activityName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Activity other = (Activity) obj;
        return (activityName.equals(other.getActivityName()));
    }
}
