package net.entropyentertainment.simplefactions;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import net.entropyentertainment.simplefactions.commands.FactionMenuCommand;

import javax.annotation.Nonnull;

public class SimpleFactions extends JavaPlugin {
    public SimpleFactions(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new FactionMenuCommand());
    }
}
