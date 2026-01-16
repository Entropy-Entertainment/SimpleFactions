package net.entropyentertainment.simplefactions.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.CompletableFuture;

public class TransferFactionOwnershipCommand extends AbstractCommand {

    private final RequiredArg<PlayerRef> factionMemberArg;

    public TransferFactionOwnershipCommand() {
        super("owner", "Changes the owner of your faction", true);
        setPermissionGroups("simplefactions.owner");

        factionMemberArg = withRequiredArg("member", "The member you want to make the owner", ArgTypes.PLAYER_REF);
    }

    @NullableDecl
    @Override
    protected CompletableFuture<Void> execute(@NonNullDecl CommandContext commandContext) {
        return null;
    }
}
