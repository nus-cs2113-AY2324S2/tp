package seedu.duke.player;

import seedu.duke.Formatter;
import seedu.duke.ui.Ui;

public class BeginnerSkill extends Player {
    public String name;
    private int power=1;
    private final int skill=1;

    public BeginnerSkill(String name) {
        super(name);
    }

    @Override
    public void printSelfInfo(){
        System.out.println("--------Player Info--------");
        System.out.println("Name: "+this.name);
        printPower();
        printSkill();
        System.out.println("---------------------------");
    }

    @Override
    protected void printPower(){
        System.out.print("Power:");
        for (int i=0; i<3;i++){
            if (i<power){
                System.out.print(" ###");
            }else{
                System.out.print("    ");
            }
        }
        if (power==1){
            System.out.println(" Level-Beginner");
        }else if (power == 2){
            System.out.println(" Level-Medium");
        }else{
            System.out.println(" Level-Expert");
        }
    }

    @Override
    protected void printSkill(){
        System.out.print("Skill:");
        for (int i=0; i<3;i++){
            if (i<skill){
                System.out.print(" ###");
            }else{
                System.out.print("    ");
            }
        }
        System.out.println(" Level-Beginner");
    }

    @Override
    public void printGoalBeforeShoot() {
        Formatter.printGoalBeforeShotforBeginner(Ui.roundCount);
    }

    @Override
    public void upgradePower(int level) {
        assert level>=0&&level<=2;
        this.power=level+1;
    }
}
