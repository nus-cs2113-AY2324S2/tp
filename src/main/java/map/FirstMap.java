package map;

public class FirstMap extends AMap{
    protected String DIFFICULTY_MODIFIER = "easy"; //can use to determine question difficulty

    @Override
    public void fightLoop() {
        System.out.println("lol");
    }
    public boolean getEntityDeath(){
        return false;
    }
    @Override
    public boolean getPlayerDeath(){
        return false;
    }
    @Override
    public void handleDeath() {
    }
    @Override
    public void handleLootingByPlayer(){

    }
}
