package com.dyonovan.customcommands

import com.dyonovan.customcommands.commands.{ChangePlayerHealth, SpawnEntity}
import com.dyonovan.customcommands.lib.Constants
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event._
import org.apache.logging.log4j.LogManager

/**
  * This file was created for CustomCommands
  *
  * CustomCommands is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 9/11/2016
  */
@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES, modLanguage = "scala", updateJSON = Constants.UPDATE_JSON, acceptableRemoteVersions = "*")
object CustomCommands {

    final val logger = LogManager.getLogger(Constants.MOD_NAME)

    @EventHandler
    def preInit(event: FMLPreInitializationEvent) = {

    }

    @EventHandler
    def init(event: FMLInitializationEvent) = {

    }

    @EventHandler
    def postInit(event: FMLPostInitializationEvent) = {

    }

    @EventHandler
    def serverLoad(event: FMLServerStartingEvent): Unit = {
        //Inital our commands
        event.registerServerCommand(new SpawnEntity)
        event.registerServerCommand(new ChangePlayerHealth)
    }
}
