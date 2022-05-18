package fr.kohei.commands;

import fr.kohei.common.cache.ProfileData;
import fr.kohei.common.cache.Rank;
import fr.kohei.BungeeAPI;
import fr.kohei.command.impl.Command;
import fr.kohei.command.param.Param;
import fr.kohei.utils.ChatUtil;
import fr.kohei.utils.TimeUtil;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class AdminCommands {

    @Command(names = "grant", power = 1000)
    public static void grant(ProxiedPlayer sender, @Param(name = "player") ProxiedPlayer player, @Param(name = "rank") String rankName, @Param(name = "duration") String durationString) {
        Optional<Rank> optionalRank = BungeeAPI.getCommonAPI().getRank(rankName);

        if (!optionalRank.isPresent()) {
            sender.sendMessage(ChatUtil.prefix("&cCe grade n'existe pas"));
            return;
        }

        Rank rank = optionalRank.get();

        long duration;
        if (durationString.startsWith("p")) {
            duration = -1L;
        } else {
            duration = TimeUtil.getDuration(durationString);
        }

        ProfileData data = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());

        data.setRankName(rank.getToken());
        BungeeAPI.getCommonAPI().saveProfile(player.getUniqueId(), data);

        sender.sendMessage(ChatUtil.prefix("&fLe nouveau &cgrade &fde &a" + player.getName() + " &fest &a" + rank.token()));
        sender.sendMessage(ChatUtil.prefix("&c⚠ &cL'option pour mettre un grade temporaire arrivera prochainement"));

    }

    @Command(names = "rank create", power = 1000)
    public static void createRank(ProxiedPlayer sender, @Param(name = "name") String name) {
        name = name.toLowerCase(Locale.ROOT);
        Rank rank = new Rank(name, 0, "", "");

        BungeeAPI.getCommonAPI().addRank(rank);

        sender.sendMessage(ChatUtil.prefix("&fVous avez &acréé &fle grade &a" + name));
    }

    @Command(names = "rank setpower", power = 1000)
    public static void changePower(ProxiedPlayer sender, @Param(name = "rank") String rankName, @Param(name = "power") int power) {

        Optional<Rank> optionalRank = BungeeAPI.getCommonAPI().getRank(rankName);

        if (!optionalRank.isPresent()) {
            sender.sendMessage(ChatUtil.prefix("&cCe grade n'existe pas"));
            return;
        }

        Rank rank = optionalRank.get();

        rank.setPermissionPower(power);
        BungeeAPI.getCommonAPI().removeRank(rank.token());
        BungeeAPI.getCommonAPI().addRank(rank);
        sender.sendMessage(ChatUtil.prefix("&fLe nouveau power du grade &a" + rank.token() + " &fest &a" + rank.permissionPower()));
    }

    @Command(names = "rank delete", power = 1000)
    public static void delete(ProxiedPlayer sender, @Param(name = "rank") String rankName) {

        Optional<Rank> optionalRank = BungeeAPI.getCommonAPI().getRank(rankName);

        if (!optionalRank.isPresent()) {
            sender.sendMessage(ChatUtil.prefix("&cCe grade n'existe pas"));
            return;
        }

        BungeeAPI.getCommonAPI().removeRank(optionalRank.get().token());
        sender.sendMessage(ChatUtil.prefix("&fVous avez &csupprimé le grade &c" + rankName.toLowerCase(Locale.ROOT)));
    }

    @Command(names = "rank setprefix", power = 1000)
    public static void changePrefix(ProxiedPlayer sender, @Param(name = "rank") String rankName, @Param(name = "prefix") String prefix) {

        Optional<Rank> optionalRank = BungeeAPI.getCommonAPI().getRank(rankName);

        if (!optionalRank.isPresent()) {
            sender.sendMessage(ChatUtil.prefix("&cCe grade n'existe pas"));
            return;
        }

        Rank rank = optionalRank.get();

        rank.setTabPrefix(prefix);
        rank.setChatPrefix(prefix);
        BungeeAPI.getCommonAPI().removeRank(rank.token());
        BungeeAPI.getCommonAPI().addRank(rank);
        sender.sendMessage(ChatUtil.prefix("&fLe nouveau prefix du grade &a" + rank.token() + " &fest &a" + rank.getTabPrefix()));
    }

    @Command(names = "rank rename", power = 1000)
    public static void rename(ProxiedPlayer sender, @Param(name = "rank") String rankName, @Param(name = "name") String name) {

        name = name.toLowerCase(Locale.ROOT);

        Optional<Rank> optionalRank = BungeeAPI.getCommonAPI().getRank(rankName);

        if (!optionalRank.isPresent()) {
            sender.sendMessage(ChatUtil.prefix("&cCe grade n'existe pas"));
            return;
        }

        Rank rank = optionalRank.get();

        rank.setToken(name);
        BungeeAPI.getCommonAPI().removeRank(rank.token());
        BungeeAPI.getCommonAPI().addRank(rank);
        sender.sendMessage(ChatUtil.prefix("&fLe nouveau name du grade &a" + rank.token() + " &fest &a" + rank.token()));
    }

    @Command(names = "rank list", power = 1000)
    public static void createRank(ProxiedPlayer sender) {
        sender.sendMessage(" ");
        sender.sendMessage(ChatUtil.translate("&8» &c&lRanks"));
        List<Rank> ranks = new ArrayList<>(BungeeAPI.getCommonAPI().getRanks());
        ranks = ranks.stream().sorted(Comparator.comparing(Rank::permissionPower).reversed()).collect(Collectors.toList());
        ranks.forEach(rank -> {
            TextComponent text = new TextComponent(ChatUtil.translate(" &7■ &f" + rank.token() + " &8(&7" + rank.permissionPower() + "&8)"));
            text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{
                    new TextComponent(ChatUtil.translate("&c&l" + rank.token().toUpperCase()) + "\n"),
                    new TextComponent(ChatUtil.translate("&7&m-------------------------") + "\n"),
                    new TextComponent(ChatUtil.translate(" &8┃ &fPrefix: " + rank.getTabPrefix()) + "\n"),
                    new TextComponent("  §8» §f" + rank.getTabPrefix() + "\n"),
                    new TextComponent(ChatUtil.translate(" &8┃ &fPower: " + rank.permissionPower()) + "\n"),
                    new TextComponent(ChatUtil.translate("&7&m-------------------------"))
            }));
            sender.sendMessage(text);
        });
        sender.sendMessage(" ");
    }

    @Command(names = "maintenance", power = 1000)
    public static void maintenance(ProxiedPlayer sender) {
        BungeeAPI.setMaintenance(!BungeeAPI.isMaintenance());

        sender.sendMessage(ChatUtil.prefix("&fVous avez " + (BungeeAPI.isMaintenance() ? "&aactivé" : "&cdésactivé") + " &fla whitelist."));
    }

    @Command(names = "tusersarien", power = 10000)
    public static void tusersarien(ProxiedPlayer sender, @Param(name = "inutile") float amount) {
        BungeeAPI.var = amount;
        sender.sendMessage(ChatUtil.prefix("&fVous avez ignoré &cTaliaMC"));
    }

    @Command(names = {"glist", "list"})
    public static void glist(ProxiedPlayer sender) {
        sender.sendMessage(ChatUtil.prefix("&fIl y a un total de &c" + BungeeAPI.getOnlinePlayers() + " &fconnectés sur le serveur."));
    }

}
