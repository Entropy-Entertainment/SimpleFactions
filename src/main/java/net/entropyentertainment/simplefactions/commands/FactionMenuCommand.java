package net.entropyentertainment.simplefactions.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.CompletableFuture;

public class FactionMenuCommand extends AbstractCommand {
    public FactionMenuCommand() {
        super("faction", "Opens the Simple Factions UI");
        addAliases("f");

        addSubCommand(new CreateFactionCommand());
        addSubCommand(new DeleteFactionCommand());
        addSubCommand(new InviteFactionMemberCommand());
        addSubCommand(new LeaveFactionCommand());
        addSubCommand(new ListFactionsCommand());
        addSubCommand(new ListFactionMembersCommand());
        addSubCommand(new GetFactionOwnerCommand());
        addSubCommand(new HelpCommand());
        addSubCommand(new KickFactionMemberCommand());
        addSubCommand(new ModifyFactionCommand());
    }

    @NullableDecl
    @Override
    protected CompletableFuture<Void> execute(@NonNullDecl CommandContext commandContext) {
        return null;
    }
}
