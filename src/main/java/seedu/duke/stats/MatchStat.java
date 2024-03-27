package seedu.duke.stats;

public class MatchStat {
    private static int matchCount = 1;
    private static int roundCount = 1;
    private static int playerScore = 0;
    private static int aiScore = 0;
    private static boolean isPlayerWin = false;
    private static boolean isMatchEnd = false;

    public static void updateStat(boolean isPlayer, boolean isGoal) {
        if (isPlayer && isGoal) {
            playerScore += 1;
        }
        if (!isPlayer && isGoal) {
            aiScore += 1;
        }
        decideMatchEnd();
        assert playerScore + aiScore <= roundCount : "Wrong computation of score.";
        roundCount += 1;
    }

    public static void updateForNewMatch() {
        roundCount = 1;
        playerScore = 0;
        aiScore = 0;
        matchCount += 1;
        isMatchEnd = false;
    }

    private static void decideMatchEnd() {
        if (roundCount >= 2 && isCompleteRound() && playerScore != aiScore) {
            isMatchEnd = true;
            isPlayerWin = playerScore > aiScore;
        }
    }

    private static boolean isCompleteRound() {
        return roundCount % 2 == 0;
    }

    public static boolean getIsPlayerWin() {
        return isPlayerWin;
    }

    public static int getMatchCount() {
        return matchCount;
    }

    public static int getRoundCount() {
        return roundCount;
    }

    public static boolean getIsMatchEnd() {
        return isMatchEnd;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static int getAiScore() {
        return aiScore;
    }
    public static void setMatchCount(int matchCount) {
        MatchStat.matchCount = matchCount+1;
    }
}
