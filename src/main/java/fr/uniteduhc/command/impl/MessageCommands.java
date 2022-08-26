package fr.uniteduhc.command.impl;

import fr.uniteduhc.common.cache.data.ProfileData;
import fr.uniteduhc.BungeeAPI;
import fr.uniteduhc.command.Command;
import fr.uniteduhc.command.param.Param;
import fr.uniteduhc.utils.ChatUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("deprecation")
public class MessageCommands {

    public static void msgHelp(ProxiedPlayer player) {
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
        player.sendMessage(ChatUtil.translate("&8┃ &6&lCommandes de Messages "));
        player.sendMessage(ChatUtil.translate("  &e/msg &f<pseudo> <message> &7(&fEnvoyer un message&7)"));
        player.sendMessage(ChatUtil.translate("  &e/msg on &7(&fActiver ses messages privés&7)"));
        player.sendMessage(ChatUtil.translate("  &e/msg off &7(&fDésactiver ses messages privés&7)"));
        player.sendMessage(ChatUtil.translate("  &e/r &f<message> &7(&fRépondre à un message&7)"));
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
    }

    @Command(names = {"msg", "message", "tell"})
    public static void message(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "message", wildcard = true) String message) {
        attemptMessage(player, target, message);
    }

    @Command(names = {"msg on", "message on", "tell on"})
    public static void messageOn(ProxiedPlayer player) {
        ProfileData playerData = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());

        playerData.setPrivateMessages(true);
        player.sendMessage(ChatUtil.prefix("&fVous avez &aactivé &fvos messages privés."));
    }

    @Command(names = {"msg off", "message off", "tell off"})
    public static void messageOff(ProxiedPlayer player) {
        ProfileData playerData = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());

        playerData.setPrivateMessages(false);
        player.sendMessage(ChatUtil.prefix("&fVous avez &cdésactivé &fvos messages privés."));
    }

    @Command(names = {"r", "reply"})
    public static void message(ProxiedPlayer player, @Param(name = "message", wildcard = true) String message) {

        if (BungeeAPI.getReplys().get(player.getUniqueId()) == null) {
            player.sendMessage(ChatUtil.prefix("&cVous n'avez personne à qui répondre"));
            return;
        }

        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(BungeeAPI.getReplys().get(player.getUniqueId()));
        attemptMessage(player, target, message);
    }

    public static void attemptMessage(ProxiedPlayer player, ProxiedPlayer target, String message) {
        ProfileData playerData = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());
        ProfileData targetData = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());


        if ((playerData.getSilentPlayer().contains(target.getName()) || targetData.getSilentPlayer().contains(player.getName())
                || !playerData.isFriendRequests() || !targetData.isPrivateMessages()) && playerData.getRank().permissionPower() < 39) {
            player.sendMessage(ChatUtil.prefix("&cVous ne pouvez pas envoyer de messages à " + target.getName()));
            return;
        }

        player.sendMessage(ChatUtil.translate("&8┃ &fEnvoyé à " + targetData.getRank().getTabPrefix() + " " + target.getName() + " &8&l» &f" + message));
        target.sendMessage(ChatUtil.translate("&8┃ &fReçu de " + playerData.getRank().getTabPrefix() + " " + player.getName() + " &8&l» &f" + message));

        BungeeAPI.getReplys().put(player.getUniqueId(), target.getUniqueId());
        BungeeAPI.getReplys().put(target.getUniqueId(), player.getUniqueId());
    }

}
