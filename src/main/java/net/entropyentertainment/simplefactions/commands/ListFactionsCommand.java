package net.entropyentertainment.simplefactions.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.CompletableFuture;

public class ListFactionsCommand extends AbstractCommand {

    private final DefaultArg<Integer> pageNumberArg;

    public ListFactionsCommand() {
        super("list", "Shows a list of all existing factions");

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