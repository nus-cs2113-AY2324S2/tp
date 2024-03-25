package seedu.duke.shooter;

public class Shooter {
    public String name;
    private int power;
    private int skill;

    public Shooter(String name) {
        this.name = name;
    }
    public void printSelfInfo(){
        System.out.println("--------Player Info--------");
        System.out.println("Name: "+this.name);
        printPower();
        printSkill();
        System.out.println("---------------------------");
    }

    protected void printPower(){

    }

    protected void printSkill(){
        System.out.print("Skill:");
        for (int i=0; i<3;i++){
            if (i<skill){
                System.out.print(" 000");
            }else{
                System.out.print(" OOO");
            }
        }
        System.out.println(" Level-Beginner");
    }

    public void printGoalBeforeShoot(){}
    public void upgradePower(int level){}
}
