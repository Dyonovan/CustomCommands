package com.dyonovan.customcommands.customexceptions;

import net.minecraft.command.CommandException;

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
public class PlayerIsDeadException extends CommandException {

    public PlayerIsDeadException() {
        this("Player is dead");
    }

    public PlayerIsDeadException(String message, Object... objects) {
        super(message, objects);
    }
}
