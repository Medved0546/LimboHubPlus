package net.elytrium.limbohub.protocol.packets;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.api.proxy.player.PlayerSettings.MainHand;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolUtils;
import com.velocitypowered.proxy.protocol.ProtocolUtils.Direction;
import io.netty.buffer.ByteBuf;

public class UseItem implements MinecraftPacket {
   private MainHand hand;
   private int sequence;

   public void decode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      if (protocolVersion.compareTo(ProtocolVersion.MINECRAFT_1_8) > 0) {
         this.hand = MainHand.values()[ProtocolUtils.readVarInt(buf)];
      }

      if (protocolVersion.compareTo(ProtocolVersion.MINECRAFT_1_19) >= 0) {
         this.sequence = ProtocolUtils.readVarInt(buf);
      }

   }

   public void encode(ByteBuf buf, Direction direction, ProtocolVersion protocolVersion) {
      throw new IllegalStateException();
   }

   public boolean handle(MinecraftSessionHandler handler) {
      handler.handleGeneric(this);
      return true;
   }

   public int getSequence() {
      return this.sequence;
   }

   public MainHand getHand() {
      return this.hand;
   }
}
