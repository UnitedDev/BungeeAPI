package fr.uniteduhc.command.impl;

import fr.uniteduhc.common.cache.data.ProfileData;
import fr.uniteduhc.BungeeAPI;
import fr.uniteduhc.command.Command;
import fr.uniteduhc.command.param.Param;
import fr.uniteduhc.utils.ChatUtil;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("deprecation")
public class CoinsHostCommands {

    @Command(names = {"coins", "coins help"})
    public static void coinsHelp(ProxiedPlayer player) {
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
        player.sendMessage(ChatUtil.translate("&8┃ &6&lCommandes /coins "));
        player.sendMessage(ChatUtil.translate("  &e/coins set &f<pseudo> &7(&fSet des coins&7)"));
        player.sendMessage(ChatUtil.translate("  &e/coins add &f<pseudo> &7(&fAjoute des coins&7)"));
        player.sendMessage(ChatUtil.translate("  &e/coins remove &f<pseudo> &7(&fRetire des coins&7)"));
        player.sendMessage(ChatUtil.translate("  &e/coins get &f<pseudo> &7(&fRécuperer les coins&7)"));
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
    }

    @Command(names = {"box", "box help"})
    public static void boxHelp(ProxiedPlayer player) {
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
        player.sendMessage(ChatUtil.translate("&8┃ &6&lCommandes /coins "));
        player.sendMessage(ChatUtil.translate("  &e/box set &f<pseudo> &7(&fSet des boxs&7)"));
        player.sendMessage(ChatUtil.translate("  &e/box add &f<pseudo> &7(&fAjoute des boxs&7)"));
        player.sendMessage(ChatUtil.translate("  &e/box remove &f<pseudo> &7(&fRetire des boxs&7)"));
        player.sendMessage(ChatUtil.translate("  &e/box get &f<pseudo> &7(&fRécuperer les boxs&7)"));
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
    }

    @Command(names = {"host", "host help"})
    public static void hostHelp(ProxiedPlayer player) {
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
        player.sendMessage(ChatUtil.translate("&8┃ &6&lCommandes /host"));
        player.sendMessage(ChatUtil.translate("  &e/host set &f<pseudo> &7(&fSet des hosts&7)"));
        player.sendMessage(ChatUtil.translate("  &e/host add &f<pseudo> &7(&fAjoute des hosts&7)"));
        player.sendMessage(ChatUtil.translate("  &e/host remove &f<pseudo> &7(&fRetire des hosts&7)"));
        player.sendMessage(ChatUtil.translate("  &e/host get &f<pseudo> &7(&fRécuperer les hosts&7)"));
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
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

    @Command(names = {"exp", "exp help"})
    public static void expHelp(ProxiedPlayer player) {
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
        player.sendMessage(ChatUtil.translate("&8┃ &6&lCommandes /exp"));
        player.sendMessage(ChatUtil.translate("  &e/exp add &f<pseudo> &7(&fAjoute de l'experience&7)"));
        player.sendMessage(ChatUtil.translate("  &e/exp remove &f<pseudo> &7(&fEnlève de l'experience&7)"));
        player.sendMessage(ChatUtil.translate("&7&m--------------------------------"));
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

    @Command(names = {"box get"}, power = 50)
    public static void getBox(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa un total de &c" + profile.getBox() + " box&f."));
    }

    @Command(names = {"box set"}, power = 50)
    public static void setBox(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "host") int box) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setBox(box);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getBox() + " box&f."));
    }

    @Command(names = {"box add"}, power = 50)
    public static void addBox(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "host") int box) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setBox(profile.getBox() + box);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getBox() + " box&f."));
    }

    @Command(names = {"box remove"}, power = 50)
    public static void removeBox(ProxiedPlayer player, @Param(name = "player") ProxiedPlayer target, @Param(name = "host") int box) {
        ProfileData profile = BungeeAPI.getCommonAPI().getProfile(target.getUniqueId());
        profile.setBox(profile.getBox() - box);
        BungeeAPI.getCommonAPI().saveProfile(target.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&a" + target.getName() + " &fa maintenant un total de &c" + profile.getBox() + " box&f."));
    }

}