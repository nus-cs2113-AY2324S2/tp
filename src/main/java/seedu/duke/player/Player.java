package seedu.duke.player;

public class Player {
    public String name;
    public int matchCount;
    private int power;
    private int skill;

    public Player(String name, int matchCount) {
        this.name = name;
        this.matchCount = matchCount;
    }
    public void printSelfInfo(){
        System.out.println("--------Player Info--------");
        System.out.println("Name: "+this.name);
        System.out.println("Number of match played: "+this.matchCount);
        printPower();
        printSkill();
        System.out.println("---------------------------");
    }

    public int getSkill() { //For inheritance
        return skill;
    }

    protected void printPower(){} //For override

    protected void printSkill(){} //For override

    public void printGoalBeforeShoot(){} //For override
    public void upgradePower(int level){} //For override
}
