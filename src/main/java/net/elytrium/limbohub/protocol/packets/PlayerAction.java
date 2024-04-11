package net.elytrium.limbohub.protocol.packets;

import com.velocitypowered.api.network.ProtocolVersion;
import com.velocitypowered.proxy.connection.MinecraftSessionHandler;
import com.velocitypowered.proxy.protocol.MinecraftPacket;
import com.velocitypowered.proxy.protocol.ProtocolUtils;
import com.velocitypowered.proxy.protocol.ProtocolUtils.Direction;
import io.netty.buffer.ByteBuf;
import net.elytrium.limbohub.protocol.packets.extra.Position;

public class PlayerAction implements MinecraftPacket {
   private Status status;
   private Position location;
   private Face face;
   private int sequence;

   public void decode(ByteBuf buf, Direction direction, ProtocolVersion version) {
      this.status = Status.values()[buf.readByte()];
      long index = buf.readLong();
      long x = index >> 38;
      long y = index << 52 >> 52;
      long z = index << 26 >> 28;
      this.location = new Position((int)x, (int)y, (int)z);
      this.face = Face.values()[buf.readByte()];
      if (version.compareTo(ProtocolVersion.MINECRAFT_1_19) >= 0) {
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

   public Status getStatus() {
      return this.status;
   }

   public Position getLocation() {
      return this.location;
   }

   public Face getBlockFace() {
      return this.face;
   }

   public int getSequence() {
      return this.sequence;
   }

   public static enum Status {
      STARTED_DIGGING,
      CANCELLED_DIGGING,
      FINISHED_DIGGING,
      DROP_ITEM_STACK,
      DROP_ITEM,
      SHOOT_ARROW_OR_FINISH_EATING,
      SWAP_ITEM_IN_HAND;

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{STARTED_DIGGING, CANCELLED_DIGGING, FINISHED_DIGGING, DROP_ITEM_STACK, DROP_ITEM, SHOOT_ARROW_OR_FINISH_EATING, SWAP_ITEM_IN_HAND};
      }
   }

   public static enum Face {
      BOTTOM,
      TOP,
      NORTH,
      SOUTH,
      WEST,
      EAST;

      // $FF: synthetic method
      private static Face[] $values() {
         return new Face[]{BOTTOM, TOP, NORTH, SOUTH, WEST, EAST};
      }
   }
}
