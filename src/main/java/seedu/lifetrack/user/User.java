package seedu.lifetrack.user;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.user.usergoals.UserGoals;

import static seedu.lifetrack.system.parser.ParserUser.parseSetUp;

public class User {
    protected String name;
    protected int height;
    protected int weight;
    protected int age;
    protected String sex;
    protected String exerciseLevels;
    protected String goal;
    protected int caloriesRequired;

    public User(String name, int height, int weight, int age, String sex, String exerciseLevels, String goal) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.exerciseLevels = exerciseLevels;
        this.goal = goal;
    }

    public User(String name, int height, int weight, int age, String sex) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
    }

    public User() {

    }

    public void setUp(String line) {
        try {
            parseSetUp(line, this);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setExerciseLevels(String exerciseLevels) {
        this.exerciseLevels = exerciseLevels;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getExerciseLevels() {
        return exerciseLevels;
    }

    public String getGoal() {
        return goal;
    }

    public void getHealthInfo() {
        UserGoals.getHealthInfo(this);
    }

    public void setCaloriesRequired(int caloriesRequired) {
        this.caloriesRequired = caloriesRequired;
    }

    public int getCaloriesRequired() {
        return caloriesRequired;
    }
}
