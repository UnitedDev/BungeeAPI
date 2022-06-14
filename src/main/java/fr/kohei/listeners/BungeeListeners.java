package fr.kohei.listeners;

import fr.kohei.BungeeAPI;
import fr.kohei.common.cache.data.ProfileData;
import fr.kohei.common.cache.data.PunishmentData;
import fr.kohei.utils.ChatUtil;
import fr.kohei.utils.TimeUtil;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class BungeeListeners implements Listener {
    @EventHandler(priority = 64)
    public void onTab(TabCompleteEvent event) {
        event.getSuggestions().removeIf(s -> s.contains(":"));
    }

    @EventHandler(priority = 64)
    public void onTabResponse(TabCompleteResponseEvent event) {
        event.getSuggestions().removeIf(s -> s.contains(":"));
    }

    @EventHandler(priority = 64)
    public void onProxyPing(ProxyPingEvent event) {
        List<ServerPing.PlayerInfo> info = new ArrayList<>();

        ProxyServer.getInstance().getPlayers().forEach(player -> {
            ProfileData data = BungeeAPI.getCommonAPI().getProfile(player.getUniqueId());
            if (data.getRank().permissionPower() >= 50 || data.getRank().permissionPower() == 15) {
                info.add(new ServerPing.PlayerInfo("§6§l» §f" + player.getName(), player.getUniqueId()));
            }
        });

        event.getResponse().setPlayers(new ServerPing.Players(
                BungeeAPI.getOnlinePlayers() + 1,
                BungeeAPI.getOnlinePlayers(),
                info.toArray(new ServerPing.PlayerInfo[0])
        ));
        if (BungeeAPI.isMaintenance()) event.getResponse().setVersion(new ServerPing.Protocol("§cMaintenance", 1));
    }

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        BungeeAPI.getCommonAPI().getProfile(event.getPlayer().getUniqueId());

        if (BungeeAPI.isMaintenance()) {
            ProfileData data = BungeeAPI.getCommonAPI().getProfile(event.getPlayer().getUniqueId());

            if (data == null || data.getRank() == null || data.getRank().permissionPower() < 35 && !event.getPlayer().getName().equalsIgnoreCase("rhodless")) {
                event.getPlayer().disconnect(ChatUtil.prefix("\n" +
                        "&cLe serveur est sous &lmaintenance" + "\n" +
                        "&c⚠ &cSi vous pensez qu'il s'agit d'une erreur," + "\n" +
                        "&ccontactez un administrateur"));
            }
        }
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.isCommand()) return;

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if (getMute(player.getUniqueId()) != null) {
            event.setCancelled(true);
            player.sendMessage(getMuteMessage(getMute(player.getUniqueId())));
        }
    }

    public String getMuteMessage(PunishmentData data) {
        if (data.getDuration() == -1L) {
            return ChatUtil.translate(
                    "&8(&c&l!&8) &fVous avez été &cmute &fpour &c" + data.getReason() + " &fpour une durée &cpermanente&f."
            );
        }

        return ChatUtil.translate(
                "&8(&c&l!&8) &fVous avez été &cmute &fpour &c" + data.getReason() + " &fpour une durée de &c" + TimeUtil.getReallyNiceTime(data.getDuration()) + "."
        );
    }

    public PunishmentData getMute(UUID uuid) {
        return BungeeAPI.getCommonAPI().getPunishments(uuid).stream()
                .filter(data -> data.getPunishmentType() == PunishmentData.PunishmentType.MUTE)
                .filter(this::isValid)
                .findFirst().orElse(null);
    }

    public boolean isValid(PunishmentData data) {
        return data.getDuration() < 0 || System.currentTimeMillis() < data.getDate().getTime() + data.getDuration();
    }
}
