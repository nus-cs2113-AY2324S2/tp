/**
 * Provides functionality for managing a list of player objects,
 * v2.0 add the ability to upgrade a player's skill based on their current skill level and match count.
 *
 */

package seedu.duke;

import seedu.duke.player.ExpertSkill;
import seedu.duke.player.MediumSkill;
import seedu.duke.player.Player;
import seedu.duke.stats.MatchStat;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class PlayerList {
    public static ArrayList<Player> l1 = new ArrayList<>();

    public static void skillUpgrade(int curPlayer){
        Player player = l1.get(curPlayer);
        player.matchCount= MatchStat.getMatchCount();

        if (player.getSkill()==1){
            l1.add(new MediumSkill(player.name,player.matchCount));
            System.out.println("--------NUSFC24-------");
            System.out.println("Congratulations! Your player's skill has updated to medium level.");
        }else if ((player.getSkill()==2)&&(player.matchCount>=5)){
            l1.add(new ExpertSkill(player.name,player.matchCount));
            System.out.println("--------NUSFC24-------");
            System.out.println("Congratulations! Your player's skill has updated to expert level.");
        }else{
            return;
        }
        Ui.curplayer= l1.size()-2;
        l1.remove(0);
        l1.get(Ui.curplayer).printSelfInfo();
    }
}
