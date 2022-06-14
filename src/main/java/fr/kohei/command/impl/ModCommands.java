package fr.kohei.command.impl;

import fr.kohei.common.cache.data.ProfileData;
import fr.kohei.BungeeAPI;
import fr.kohei.command.Command;
import fr.kohei.command.param.Param;
import fr.kohei.utils.ChatUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("deprecation")
public class ModCommands {

    @Command(names = {"alert", "galert"}, power = 40)
    public static void alert(ProxiedPlayer player, @Param(name = "message", wildcard = true) String message) {
        ProxyServer.getInstance().getPlayers().forEach(target -> {
            ProfileData data = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());

            target.sendMessage(" ");
            target.sendMessage(ChatUtil.translate("&8┃ " + data.getRank().getTabPrefix() + " " + player.getName() + " &f&l» &f" + message));
            target.sendMessage(" ");
        });
    }

    @Command(names = {"lookup", "find"}, power = 39)
    public static void lookup(ProxiedPlayer sender, @Param(name = "player") ProxiedPlayer target) {
        sender.sendMessage(ChatUtil.prefix("&fLe joueur &c" + target.getName() + " &fest connecté sur &c" + target.getServer().getInfo().getName()));
    }

    @Command(names = {"server", "changeserver"}, power = 40)
    public static void changeServer(ProxiedPlayer sender, @Param(name = "server") String server) {
        ServerInfo info = ProxyServer.getInstance().getServerInfo(server);

        if(info == null) {
            sender.sendMessage(ChatUtil.prefix("&cCe serveur n'existe pas"));
            return;
        }

        sender.sendMessage(ChatUtil.prefix("&fTéléportation au serveur &c" + info.getName()));
        sender.connect(info);
    }

    @Command(names = {"send"}, power = 50)
    public static void send(ProxiedPlayer sender, @Param(name = "player") ProxiedPlayer target, @Param(name = "server") String server) {
        ServerInfo info = ProxyServer.getInstance().getServerInfo(server);

        if(info == null) {
            sender.sendMessage(ChatUtil.prefix("&cCe serveur n'existe pas"));
            return;
        }

        sender.sendMessage(ChatUtil.prefix("&fVous avez envoyé &c" + target.getName() + " &fsur le serveur &c" + info.getName()));
        target.sendMessage(ChatUtil.prefix("&fTéléportation au serveur &c" + info.getName()));
        target.connect(info);
    }

}
