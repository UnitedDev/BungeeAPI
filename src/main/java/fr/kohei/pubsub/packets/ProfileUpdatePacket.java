package fr.kohei.pubsub.packets;

import fr.kohei.common.cache.data.ProfileData;
import fr.kohei.common.utils.messaging.pigdin.Packet;
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
