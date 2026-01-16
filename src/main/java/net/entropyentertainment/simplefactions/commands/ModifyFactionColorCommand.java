package net.entropyentertainment.simplefactions.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.CompletableFuture;

public class ModifyFactionColorCommand extends AbstractCommand {

    private final RequiredArg<Integer> colorArg;

    public ModifyFactionColorCommand() {
        super("color", "Changes the color of your faction");
        setPermissionGroups("simplefactions.owner");

        colorArg = withRequiredArg("color", "The color you want to assign to your faction", ArgTypes.COLOR);
    }

    @NullableDecl
    @Override
    protected CompletableFuture<Void> execute(@NonNullDecl CommandContext commandContext) {
        return null;
    }
}