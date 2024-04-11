package net.elytrium.limbohub.protocol.packets;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolUtils.Direction;
import io.netty.buffer.ByteBuf;

public class CloseContainerServer implements MinecraftPacket {
   private short inventory;

   public void decode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      this.inventory = buf.readUnsignedByte();
   }

   public void encode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      throw new IllegalStateException();
   }

   public boolean handle(MinecraftSessionHandler handler) {
      handler.handleGeneric(this);
      return true;
   }

   public short getInventory() {
      return this.inventory;
   }
}
