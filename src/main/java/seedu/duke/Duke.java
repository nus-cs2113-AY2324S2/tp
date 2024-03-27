package seedu.duke;

import seedu.duke.exception.ProcessInputException;
import seedu.duke.player.MediumSkill;
import seedu.duke.player.Player;
import seedu.duke.stats.MatchStat;
import seedu.duke.ui.Ui;

public class Duke {


    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Formatter.printWelcomeMsg();

        Player playerThisRound = createNewPlayer();
        //Assume there is single player, can only have one player in the game
        //After account login function done,
        //the PlayerThisRound will either return a new player, or a player existed in the PlayerList

        while (Ui.getIsRunning()) {
            if (MatchStat.getIsMatchEnd()) {
                Formatter.printMatchResult();
                PlayerList.skillUpgrade(Ui.curplayer);
                playerThisRound = PlayerList.l1.get(Ui.curplayer);
            } else {
                playerThisRound.printGoalBeforeShoot();
            }
            try {
                Ui.beginListening();
                Ui.processInput();
                Ui.executeCommand();
            } catch (ProcessInputException e) {
                Formatter.printErrorExecutionFail();
            }
        }
    }

    //Bruno is a sample player for demonstration, you can try any level player
    private static Player createNewPlayer() {
        PlayerList.l1.add(new MediumSkill("Bruno",3));
        Player playerThisRound = PlayerList.l1.get(Ui.curplayer);
        playerThisRound.printSelfInfo();
        MatchStat.setMatchCount(playerThisRound.matchCount);
        return playerThisRound;
    }
}
