package fr.uniteduhc.command.param.defaults;

import fr.uniteduhc.command.param.ParameterType;
import fr.uniteduhc.utils.ChatUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DoubleParameterType implements ParameterType<Double> {

    public Double transform(CommandSender sender, String source) {
        if (source.toLowerCase().contains("e")) {
            sender.sendMessage(ChatUtil.prefix(ChatColor.RED + source + " n'est pas un nombre valide."));
            return (null);
        }

        try {
            double parsed = Double.parseDouble(source);

            if (Double.isNaN(parsed) || !Double.isFinite(parsed)) {
                sender.sendMessage(ChatUtil.prefix("&cCe nombre n'est pas valide"));
                return (null);
            }

            return (parsed);
        } catch (NumberFormatException exception) {
            sender.sendMessage(ChatUtil.prefix("&cCe nombre n'est pas valide"));
            return (null);
        }
    }

    public List<String> tabComplete(ProxiedPlayer sender, Set<String> flags, String source) {
        return (new ArrayList<>());
    }

}