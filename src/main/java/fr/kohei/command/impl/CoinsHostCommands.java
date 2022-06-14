package fr.kohei.command.impl;

import fr.kohei.common.cache.data.ProfileData;
import fr.kohei.BungeeAPI;
import fr.kohei.command.Command;
import fr.kohei.command.param.Param;
import fr.kohei.utils.ChatUtil;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("deprecation")
public class CoinsHostCommands {

    @Command(names = {"coins", "coins help"}, power = 50)
    public static void coinsHelp(ProxiedPlayer player) {
        player.sendMessage(" ");
        player.sendMessage(ChatUtil.translate("&8» &c&lAide &l/coins"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7coins &cset &f<pseudo> &8» &7Set des coins"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7coins &cadd &f<pseudo> &8» &7Ajoute des coins"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7coins &cremove &f<pseudo> &8» &7Retire des coins"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7coins &cget &f<pseudo> &8» &7Récuperer les coins"));
        player.sendMessage(" ");
    }

    @Command(names = {"host", "host help"}, power = 50)
    public static void hostHelp(ProxiedPlayer player) {
        player.sendMessage(" ");
        player.sendMessage(ChatUtil.translate("&8» &c&lAide &l/host"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7host &cset &f<pseudo> &8» &7Set des hosts"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7host &cadd &f<pseudo> &8» &7Ajoute des hosts"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7host &cremove &f<pseudo> &8» &7Retire des hosts"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7host &cget &f<pseudo> &8» &7Récuperer les hosts"));
        player.sendMessage(" ");
    }

    @Command(names = {"coins get"}, power = 50)
    public static void getCoins(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa un total de &e" + profile.getCoins() + " coins&f."));
    }

    @Command(names = {"coins set"}, power = 50)
        public static void setCoins(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "coins") int coins) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setCoins(coins);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &e" + profile.getCoins() + " coins&f."));
    }

    @Command(names = {"coins add"}, power = 50)
    public static void addCoins(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "coins") int coins) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setCoins(profile.getCoins() + coins);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &e" + profile.getCoins() + " coins&f."));
    }

    @Command(names = {"coins remove"}, power = 50)
    public static void removeCoins(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "coins") int coins) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setCoins(profile.getCoins() - coins);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &e" + profile.getCoins() + " coins&f."));
    }

    @Command(names = {"host get"}, power = 50)
    public static void getHost(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa un total de &c" + profile.getHosts   () + " host&f."));
    }

    @Command(names = {"host set"}, power = 50)
    public static void setHost(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "host") int host) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setHosts(host);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getHosts() + " host&f."));
    }

    @Command(names = {"host add"}, power = 50)
    public static void addHost(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "host") int host) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setHosts(profile.getHosts() + host);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getHosts() + " host&f."));
    }

    @Command(names = {"host remove"}, power = 50)
    public static void removeHost(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "host") int host) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setHosts(profile.getHosts() - host);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getHosts() + " host&f."));
    }

    @Command(names = {"exp", "exp help"}, power = 50)
    public static void expHelp(ProxiedPlayer player) {
        player.sendMessage(" ");
        player.sendMessage(ChatUtil.translate("&8» &c&lAide &l/exp"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7exp &cadd &f<pseudo> &8» &7Ajoute de l'experience"));
        player.sendMessage(ChatUtil.translate(" &7■ &4/&7exp &cremove &f<pseudo> &8» &7Enlève de l'experience"));
        player.sendMessage(" ");
    }

    @Command(names = {"exp add"}, power = 50)
    public static void addExp(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "exp") int exp) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        BungeeAPI.changeExperience(target, exp, "Give");
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getExperience() + " xp&f."));
    }

    @Command(names = {"exp remove"}, power = 50)
    public static void removeExp(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "exp") int exp) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        BungeeAPI.changeExperience(target, exp, "Give");
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getExperience() + " xp&f."));
    }

}