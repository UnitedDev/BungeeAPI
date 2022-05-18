package fr.kohei.manager;

import fr.kohei.BungeeAPI;
import fr.kohei.command.impl.CommandHandler;
import fr.kohei.commands.*;
import fr.kohei.listeners.BungeeListeners;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeManager {

    private final Plugin plugin;

    public BungeeManager(Plugin plugin) {
        this.plugin = plugin;

        this.loadListeners();
        this.loadCommands();
    }

    private void loadCommands() {
        CommandHandler commandHandler = BungeeAPI.getCommandHandler();

        commandHandler.registerClass(AdminCommands.class);
        commandHandler.registerClass(MessageCommands.class);
        commandHandler.registerClass(CoinsHostCommands.class);
        commandHandler.registerClass(IgnoreCommands.class);
        commandHandler.registerClass(ModCommands.class);
        commandHandler.hook();
    }

    public void loadListeners() {
        PluginManager pluginManager = plugin.getProxy().getPluginManager();

        pluginManager.registerListener(plugin, new BungeeListeners());
    }

}
