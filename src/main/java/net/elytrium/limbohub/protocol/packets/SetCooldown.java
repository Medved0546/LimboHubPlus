package net.elytrium.limbohub.protocol.packets;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolUtils;
import com.velocitypowered.proxy.protocol.ProtocolUtils.Direction;
import io.netty.buffer.ByteBuf;

public class SetCooldown implements MinecraftPacket {
   private final int itemId;
   private final int ticks;

   public SetCooldown(int itemId, int ticks) {
      this.itemId = itemId;
      this.ticks = ticks;
   }

   public SetCooldown() {
      throw new IllegalStateException();
   }

   public void decode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      throw new IllegalStateException();
   }

   public void encode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      ProtocolUtils.writeVarInt(buf, this.itemId);
      ProtocolUtils.writeVarInt(buf, this.ticks);
   }

   public boolean handle(MinecraftSessionHandler handler) {
      return true;
   }
}
