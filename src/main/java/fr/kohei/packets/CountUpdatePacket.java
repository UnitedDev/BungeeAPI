package fr.kohei.packets;

import fr.kohei.common.messaging.pigdin.Packet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CountUpdatePacket implements Packet {

    private final int normalPlayers;
    private final int totalPlayers;

}
