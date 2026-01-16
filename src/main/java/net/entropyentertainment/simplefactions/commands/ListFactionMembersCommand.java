package net.entropyentertainment.simplefactions.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.CompletableFuture;

public class ListFactionMembersCommand extends AbstractCommand {

    private final DefaultArg<Integer> pageNumberArg;

    public ListFactionMembersCommand() {
        super("member", "Lists members of the specified faction");

        pageNumberArg = withDefaultArg(
                "pageNumber",
                "The number of the page you want to open",
                ArgTypes.INTEGER,
                0,
                "The first page"
        );
    }

    @NullableDecl
    @Override
    protected CompletableFuture<Void> execute(@NonNullDecl CommandContext commandContext) {
        return null;
    }
}