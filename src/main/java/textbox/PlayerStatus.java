package textbox;


public class PlayerStatus {
    private int playerHealth;
    private int playerMoney;
    private int playerExp;

    public PlayerStatus(int startHealth, int startMoney, int startExp){
        this.playerHealth = startHealth;
        this.playerMoney = startMoney;
        this.playerExp = startExp;
    }

    public int getPlayerHealth(){
        return this.playerHealth;
    }

    public int getPlayerMoney(){
        return this.playerMoney;
    }

    public int getPlayerExp(){
        return this.playerExp;
    }

    public void setPlayerExp(int playerExp) {
        this.playerExp = playerExp;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }
}
