package fr.kohei.packets;

import fr.kohei.common.messaging.pigdin.Packet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@RequiredArgsConstructor
public class UpdatePlayersPacket implements Packet {

    private final ConcurrentHashMap<UUID, String> players;

}
