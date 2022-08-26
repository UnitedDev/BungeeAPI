package fr.uniteduhc.command.param.defaults;

import fr.uniteduhc.command.param.ParameterType;
import fr.uniteduhc.utils.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayerParameterType implements ParameterType<ProxiedPlayer> {

    public ProxiedPlayer transform(CommandSender sender, String source) {
        if (sender instanceof ProxiedPlayer && (source.equalsIgnoreCase("self") || source.equals(""))) {
            return ((ProxiedPlayer) sender);
        }
        if (!(sender instanceof ProxiedPlayer) && (source.equalsIgnoreCase("self") || source.equals(""))) {
            sender.sendMessage(ChatUtil.prefix("&cVous êtes fou ?"));
            return (null);
        }

        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(source);

        if (player == null) {
            sender.sendMessage(ChatUtil.prefix("&cCe joueur n'est pas connecté"));
            return (null);
        }

        return (player);
    }

    @Override
    public List<String> tabComplete(ProxiedPlayer sender, Set<String> flags, String source) {
        return new ArrayList<>();
    }

}