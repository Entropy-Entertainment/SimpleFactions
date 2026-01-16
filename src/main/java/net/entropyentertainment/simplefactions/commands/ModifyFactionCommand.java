package net.entropyentertainment.simplefactions.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class ModifyFactionCommand extends AbstractCommandCollection {
    public ModifyFactionCommand() {
        super("modify", "Modify your faction");
        setPermissionGroups("simplefactions.owner");

        addSubCommand(new ModifyFactionNameCommand());
        addSubCommand(new ModifyFactionColorCommand());
        addSubCommand(new TransferFactionOwnershipCommand());
    }
}