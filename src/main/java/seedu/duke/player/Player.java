package seedu.duke.player;

public class Player {
    public String name;
    public int matchCount;
    private int power;
    private int skill;

    public Player(String name) {
        this.name = name;
    }
    public void printSelfInfo(){
        System.out.println("--------Player Info--------");
        System.out.println("Name: "+this.name);
        printPower();
        printSkill();
        System.out.println("---------------------------");
    }

    protected void printPower(){} //For override

    protected void printSkill(){} //For override

    public void printGoalBeforeShoot(){} //For override
    public void upgradePower(int level){} //For override
}
