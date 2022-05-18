package fr.kohei.command.param.defaults;


import fr.kohei.command.param.ParameterType;
import fr.kohei.utils.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;

public class BooleanParameterType implements ParameterType<Boolean> {

    static final Map<String, Boolean> MAP = new HashMap<>();

    static {
        MAP.put("true", true);
        MAP.put("on", true);
        MAP.put("oui", true);

        MAP.put("false", false);
        MAP.put("off", false);
        MAP.put("non", false);
    }

    public Boolean transform(CommandSender sender, String source) {
        if (!MAP.containsKey(source.toLowerCase())) {
            sender.sendMessage(ChatUtil.prefix("&cVous devez rentrez 'true' ou 'false'"));
            return (null);
        }

        return MAP.get(source.toLowerCase());
    }

    public List<String> tabComplete(ProxiedPlayer sender, Set<String> flags, String source) {
        return (new ArrayList<>());
    }

}