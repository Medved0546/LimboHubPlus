package net.elytrium.limbohub.protocol.packets;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolUtils.Direction;
import io.netty.buffer.ByteBuf;
import net.elytrium.limbohub.protocol.container.Container;

public class CloseContainerClient implements MinecraftPacket {
   private final int windowId;

   public CloseContainerClient(int windowId) {
      this.windowId = windowId;
   }

   public CloseContainerClient(Container container) {
      this(container.getId());
   }

   public CloseContainerClient() {
      throw new IllegalStateException();
   }

   public void decode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      throw new IllegalStateException();
   }

   public void encode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      buf.writeByte(this.windowId);
   }

   public boolean handle(MinecraftSessionHandler handler) {
      return true;
   }
}
