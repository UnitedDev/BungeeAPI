package fr.kohei.command.param;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;
import java.util.Set;

public interface ParameterType<T> {

    T transform(CommandSender sender, String source);

    List<String> tabComplete(ProxiedPlayer sender, Set<String> flags, String source);

}