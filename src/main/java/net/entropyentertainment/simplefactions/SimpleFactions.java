package net.entropyentertainment.simplefactions;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import net.entropyentertainment.simplefactions.commands.FactionMenuCommand;
import net.entropyentertainment.simplefactions.database.Database;

import javax.annotation.Nonnull;

public class SimpleFactions extends JavaPlugin {
    public SimpleFactions(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        Database.initialize(this.getDataDirectory());

        this.getCommandRegistry().registerCommand(new FactionMenuCommand());

        Database.getInstance().factions();
        Database.getInstance().members();
        Database.getInstance().invites();
    }
}
