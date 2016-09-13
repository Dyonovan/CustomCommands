package com.dyonovan.customcommands.commands;

import com.dyonovan.customcommands.CustomCommands;
import com.dyonovan.customcommands.customexceptions.PlayerIsDeadException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;

import java.util.Collections;
import java.util.List;

/**
 * This file was created for CustomCommands
 * <p>
 * CustomCommands is licensed under the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * @author Dyonovan
 * @since 9/13/2016
 */
public class ChangePlayerHealth extends CommandBase {

    @Override
    public String getCommandName() {
        return "cc_changehealth";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return I18n.translateToLocal("customcommands:command.changehealth.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 3) throw new WrongUsageException("customcommands:command.changehealth.usage");

        EntityPlayer player = getPlayer(server, sender, args[0]);
        if (player.getHealth() <= 0.0F) {
            throw new PlayerIsDeadException();
        }
        Float healthChange;
        try {
            healthChange = Float.parseFloat(args[1]);
        } catch (NumberFormatException e){
            throw new WrongUsageException("customcommands:command.changehealth.usage");
        }
        player.setHealth(player.getHealth() + healthChange);

        player.addChatMessage(new TextComponentString(args[2] + " thought you could use a health change..."));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getAllUsernames()) : Collections.emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }
}
