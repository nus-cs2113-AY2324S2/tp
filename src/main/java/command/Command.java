package command;

import player.PlayerProfile;

public interface Command {
    void execute(PlayerProfile playerProfile);
    boolean isExit();
}
