package fr.kohei;

import fr.kohei.command.CommandHandler;
import fr.kohei.common.api.CommonAPI;
import fr.kohei.common.cache.data.Division;
import fr.kohei.common.cache.data.ProfileData;
import fr.kohei.manager.BungeeManager;
import fr.kohei.pubsub.packets.CountUpdatePacket;
import fr.kohei.pubsub.packets.UpdatePlayersPacket;
import fr.kohei.utils.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BungeeAPI extends Plugin {

    @Getter
    private static Plugin plugin;
    @Getter
    private static BungeeManager bungeeManager;
    @Getter
    private static CommandHandler commandHandler;
    @Getter
    private static CommonAPI commonAPI;
    @Getter
    @Setter
    private static boolean maintenance;
    @Getter
    private static ConcurrentHashMap<UUID, UUID> replys;

    @Override
    public void onEnable() {
        plugin = this;

        commandHandler = new CommandHandler(plugin);
        bungeeManager = new BungeeManager(plugin);
        commonAPI = CommonAPI.create();
        maintenance = true;
        replys = new ConcurrentHashMap<>();

        commonAPI.getMessaging().registerAdapter(CountUpdatePacket.class, null);
        commonAPI.getMessaging().registerAdapter(UpdatePlayersPacket.class, null);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                commonAPI.getMessaging().sendPacket(new CountUpdatePacket(getPlugin().getProxy().getOnlineCount(), getOnlinePlayers()));

                ConcurrentHashMap<UUID, String> map = new ConcurrentHashMap<>();
                for (ProxiedPlayer player : getProxy().getPlayers()) {
                    if (player.getServer() == null || player.getServer().getInfo() == null || player.getServer().getInfo().getName() == null)
                        continue;
                    map.put(player.getUniqueId(), player.getServer().getInfo().getName());
                }
                commonAPI.getMessaging().sendPacket(new UpdatePlayersPacket(map));
            }
        }, 0, 1000);

    }

    public static int getOnlinePlayers() {
        return (int) (getPlugin().getProxy().getOnlineCount() * var);
    }

    public static void changeExperience(ProxiedPlayer player, int add, String reason) {
        ProfileData profile = getCommonAPI().getProfile(player.getUniqueId());

        profile.setExperience(profile.getExperience() + add);
        getCommonAPI().saveProfile(player.getUniqueId(), profile);
        player.sendMessage(ChatUtil.prefix("&f+&c" + add + " xp &7(&c" + reason + "&7)"));
        updateExperience(player);
    }

    private static void updateExperience(ProxiedPlayer player) {
        ProfileData profile = getCommonAPI().getProfile(player.getUniqueId());

        Division division = profile.getDivision();

        if (profile.getExperience() >= division.getExperience()) {
            profile.setExperience(0);
            profile.setDivision(nextDivision(profile.getDivision()));
            getCommonAPI().saveProfile(player.getUniqueId(), profile);

            division.getConsumer().accept(profile);
            player.sendMessage(ChatUtil.prefix("&fVous êtes désormais dans la division " + nextDivision(division).getDisplay()));
            player.sendMessage(ChatUtil.prefix(division.getMessage()));
        }
    }

    public static Division nextDivision(Division division) {
        return Division.values()[(division.ordinal() + 1) % Division.values().length];
    }

    public static double var = 938F / 469F / 2F;
}
