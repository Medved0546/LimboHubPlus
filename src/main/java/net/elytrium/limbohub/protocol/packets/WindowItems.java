package net.elytrium.limbohub.protocol.packets;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolUtils.Direction;
import io.netty.buffer.ByteBuf;

public class WindowItems implements MinecraftPacket {
   public void decode(ByteBuf byteBuf, Direction direction, ProtocolVersion protocolVersion) {
      throw new IllegalStateException();
   }

   public void encode(ByteBuf byteBuf, Direction direction, ProtocolVersion protocolVersion) {
   }

   public boolean handle(MinecraftSessionHandler minecraftSessionHandler) {
      return false;
   }
}
