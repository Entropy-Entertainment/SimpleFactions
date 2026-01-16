package net.entropyentertainment.simplefactions.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.CompletableFuture;

public class CreateFactionCommand extends AbstractCommand {

    private final RequiredArg<String> factionNameArg;

    public CreateFactionCommand() {
        super("create", "Creates a new faction");

        factionNameArg = withRequiredArg("name", "The name of your new faction", ArgTypes.STRING);
    }

    @NullableDecl
    @Override
    protected CompletableFuture<Void> execute(@NonNullDecl CommandContext commandContext) {

        return null;
    }
}