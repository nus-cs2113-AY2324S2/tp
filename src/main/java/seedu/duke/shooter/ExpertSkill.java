package seedu.duke.shooter;

import seedu.duke.Formatter;
import seedu.duke.ui.Ui;

public class ExpertSkill extends Shooter {
    private String name;
    private int power = 1;
    private final int skill = 3;

    public ExpertSkill(String name) {
        super(name);
    }

    @Override
    public void printSelfInfo() {
        super.printSelfInfo();
    }

    @Override
    protected void printPower() {
        super.printPower();
    }

    @Override
    protected void printSkill(){
        System.out.print("Skill:");
        for (int i=0; i<3;i++){
            if (i<skill){
                System.out.print(" 000");
            }else{
                System.out.print(" OOO");
            }
        }
        System.out.println(" Level-Expert");
    }

    @Override
    public void printGoalBeforeShoot() {
        Formatter.printGoalBeforeShotforExpert(Ui.roundCount);
    }

    @Override
    public void upgradePower(int level) {
        assert level>=0&&level<=2;
        this.power=level+1;
    }
}
