package fr.kohei.command.impl;

import fr.kohei.common.cache.data.ProfileData;
import fr.kohei.BungeeAPI;
import fr.kohei.command.Command;
import fr.kohei.command.param.Param;
import fr.kohei.utils.ChatUtil;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("deprecation")
public class IgnoreCommands {
    
    @Command(names = {"ignore add", "i add"})
    public static void ignoreAdd(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target) {

        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());
        if(profile.getSilentPlayer().contains(target.getName())) {
            player.sendMessage(ChatUtil.prefix("&cVous ignorez déjà " + target.getName()));
            return;
        }

        profile.getSilentPlayer().add(target.getName());
        BungeeAPI.getCommonAPI().saveProfile(player.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&fVous ignorez désormais &c" + target.getName()));
    }

    @Command(names = {"ignore remove", "i remove"})
    public static void ignoreRemove(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target) {

        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());
        if(!profile.getSilentPlayer().contains(target.getName())) {
            player.sendMessage(ChatUtil.prefix("&cVous n'ignorez pas " + target.getName()));
            return;
        }

        profile.getSilentPlayer().remove(target.getName());
        profile.getSilentPlayer().add(target.getName());
        player.sendMessage(ChatUtil.prefix("&fVous n'ignorez plus &a" + target.getName()));
    }

    @Command(names = {"ignore list", "i list"})
    public static void ignoreList(ProxiedPlayer player) {

        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());
        if(profile.getSilentPlayer().isEmpty()) {
            player.sendMessage(ChatUtil.prefix("&cVous n'ignorez personne pour le moment."));
            return;
        }

        player.sendMessage(" ");
        player.sendMessage(ChatUtil.translate("&8» &c&lJoueurs ignorés"));
        profile.getSilentPlayer().forEach(s -> player.sendMessage(ChatUtil.translate(" &7■ &f" + s)));
        player.sendMessage(" ");
    }

    @Command(names = {"ignore", "ignore help", "i", "i help"})
    public static void ignoreHelp(ProxiedPlayer player) {
        player.sendMessage(" ");
        player.sendMessage(ChatUtil.translate("&8» &c&lAide &l/ignore"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7i &cadd &f<pseudo> &8» &7Ignorer quelqu'un"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7i &cremove &f<pseudo> &8» &7Arrêter d'ignorer quelqu'un"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7i &clist &8» &7Liste de joueurs ignorés"));
        player.sendMessage(" ");
    }
}
