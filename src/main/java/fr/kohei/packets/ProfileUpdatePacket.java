package fr.kohei.packets;

import fr.kohei.common.cache.ProfileData;
import fr.kohei.common.messaging.pigdin.Packet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class ProfileUpdatePacket implements Packet {

    private final UUID uuid;
    private final Consumer<ProfileData> profileConsumer;

}
