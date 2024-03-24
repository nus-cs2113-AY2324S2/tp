package map.BattleInterface;

import InteractableEntity.Enemy;
import InteractableEntity.InteractableEntity;
import filereader.FileReader;
import map.AMap;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;
import Math.*;
import filereader.filepath.EnemiesDesignFilePath;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BattleInterface extends AMap {
    protected PlayerStatus currentPlayer;
    protected TextBox currentTextBox;
    protected InteractableEntity currentEntity;


    public BattleInterface(PlayerStatus player, TextBox text, InteractableEntity entity) {
        this.currentPlayer = player;
        this.currentTextBox = text;
        this.currentEntity = entity;
    }

    @Override
    public void fightLoop() {

    }

    @Override
    public void fightLoop(Scanner in) {
        MathPool mathPool = new MathPool();
        mathPool.init();
        Ui ui = new Ui();

        while (currentPlayer.getPlayerHealth() > 0 && currentEntity.getHealth() > 0) {
            int answer;
            Pattern pattern = Pattern.compile("^[--]?[0-9]+$");
            Matcher matcher;
            ui.printPlayerStatus(currentPlayer);
            ui.printMap(currentMap);
            MathQuestion mathQuestion = mathPool.getQuestionByDifficulty(0);
            ui.printQuestion(mathQuestion);
            String answerCommand = in.nextLine().trim();
            while (!pattern.matcher(answerCommand).matches()) {
                currentTextBox.setNextError("Answer must be an integer.");
                currentTextBox.setNextInstruction(mathQuestion.getQuestion());
                ui.printTextBox(currentTextBox);
                answerCommand = in.nextLine().trim();
            }
            answer = Integer.parseInt(answerCommand);
            if (mathQuestion.checkAns(answer)) {
                System.out.println("CORRECT!!!");

                playerHitEnemy();
            } else {
                System.out.println("WRONG ANSWER!!!");
                enemyHitPlayer();
            }
        }
    }

    public void initMap(int givenWidth, int givenHeight) {
        this.width = givenWidth;
        this.height = givenHeight;
        this.currentMap = new ArrayList<>(height);

        FileReader fileReader = new FileReader(EnemiesDesignFilePath.CENTAUR_PATH);
        try {
            currentMap = fileReader.readEnemyDesign();
        } catch (Exception e) {
            System.out.println(e);

        }
    }


    public void playerHitEnemy() {
        if (currentEntity instanceof Enemy) {
            int dmgDone = 10;
            ((Enemy) currentEntity).harmHealth(dmgDone);
        }
    }

    public void enemyHitPlayer() {
        if (currentEntity instanceof Enemy) {
            int dmgDone = ((Enemy) currentEntity).getDamage();
            currentPlayer.harmHealth(dmgDone);
        }
    }


    public InteractableEntity getCurrentEntity() {
        return currentEntity;
    }

    @Override
    public boolean getEntityDeath() {
        return currentEntity.getHealth() <= 0;
    }

    @Override
    public boolean getPlayerDeath() {
        return currentPlayer.getPlayerHealth() <= 0;
    }

    @Override
    public void handleDeath(){
        Ui ui = new Ui();
        ui.printDeathMessage();
        System.exit(0);
    }

    public PlayerStatus getCurrentPlayer() {
        return currentPlayer;
    }

    public void handleLootingByPlayer(){
        int exp = this.currentEntity.getExp_dropped();
        int money = this.currentEntity.getMoney_dropped();
        this.currentPlayer.addExp(exp);
        this.currentPlayer.addMoney(money);
        this.currentTextBox.setNextNarration("The beast was slain. You looted its cold dead corpse and found $" + money
                + " and gained " + exp + " exp.");
    }



}
