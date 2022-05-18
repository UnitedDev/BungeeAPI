package fr.kohei.command.param.defaults;


import fr.kohei.command.param.ParameterType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StringParameterType implements ParameterType<String> {

    public String transform(CommandSender sender, String source) {
        return source;
    }

    public List<String> tabComplete(ProxiedPlayer sender, Set<String> flags, String source) {
        return (new ArrayList<>());
    }

}