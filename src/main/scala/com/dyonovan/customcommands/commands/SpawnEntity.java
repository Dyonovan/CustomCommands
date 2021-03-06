package com.dyonovan.customcommands.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
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
 * @since 9/11/2016
 */
public class SpawnEntity extends CommandBase {
    @Override
    public String getCommandName() {
        return "cc_spawnentity";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return I18n.translateToLocal("customcommands:command.spawnentity.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 3) throw new WrongUsageException("customcommands:command.spawnentity.usage");

        EntityPlayer player = getPlayer(server, sender, args[0]);
        EntityMob entity;
        if (args[1].equalsIgnoreCase("creeper"))
            entity = new EntityCreeper(server.getEntityWorld());
        else if (args[1].equalsIgnoreCase("witherboss"))
            entity = new EntityWither(server.getEntityWorld());
        else if (args[1].equalsIgnoreCase("zombie")) {
            entity = new EntityZombie(server.getEntityWorld());
            entity.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.DIAMOND_AXE));
            entity.setDropItemsWhenDead(true);
            entity.setDropChance(EntityEquipmentSlot.MAINHAND, 0.8F);
        }
        else throw new WrongUsageException("customcommands:command.spawnentity.usage");

        entity.setLocationAndAngles(player.posX + 2, player.posY, player.posZ + 2, 0.0F, 0.0F);
        entity.attackEntityAsMob(player);
        server.getEntityWorld().spawnEntityInWorld(entity);

        player.addChatMessage(new TextComponentString(args[2] + " sent you a " + args[1] + ". Look around you..."));
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
