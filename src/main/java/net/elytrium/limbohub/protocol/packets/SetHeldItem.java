package net.elytrium.limbohub.protocol.packets;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolUtils.Direction;
import io.netty.buffer.ByteBuf;

public class SetHeldItem implements MinecraftPacket {
   private short slot;

   public void decode(ByteBuf buf, Direction direction, ProtocolVersion version) {
      this.slot = buf.readShort();
   }

   public void encode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      throw new IllegalStateException();
   }

   public boolean handle(MinecraftSessionHandler handler) {
      handler.handleGeneric(this);
      return true;
   }

   public short getSlot() {
      return this.slot;
   }
}
