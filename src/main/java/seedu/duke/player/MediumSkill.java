package seedu.duke.player;

import seedu.duke.Formatter;

public class MediumSkill extends Player {
    private int power = 1;
    private final int skill = 2;

    public MediumSkill(String name, int matchCount) {
        super(name, matchCount);
    }

    @Override
    public void printSelfInfo() {
        super.printSelfInfo();
    }

    @Override
    protected void printPower() {
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
    public int getSkill() {
        return skill;
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
        System.out.println(" Level-Medium");
    }

    //For now, only finished visualization part, user can only choose 0,1,2
    //Will be completed in V2.0b
    @Override
    public void printGoalBeforeShoot() {
        Formatter.printGoalBeforeShotforMedium();
    }

    @Override
    public void upgradePower(int level) {
        assert level>=0&&level<=2;
        this.power=level+1;
    }
}
