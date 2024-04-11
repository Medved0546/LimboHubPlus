/*
 * Copyright (C) 2023 Elytrium
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.elytrium.limbohub.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.proxy.Player;
import net.elytrium.limboapi.api.event.LoginLimboRegisterEvent;
import net.elytrium.limbohub.LimboHub;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class HubListener {

  private final LimboHub plugin;

  public HubListener(LimboHub plugin) {
    this.plugin = plugin;
  }

  @Subscribe(order = PostOrder.LAST)
  public void onLogin(LoginLimboRegisterEvent event) {
    Player player = event.getPlayer();
    event.addOnJoinCallback(() -> this.plugin.sendToHub(player));
    event.setOnKickCallback(kickevent -> {
      this.plugin.sendToHub(player);
      if (kickevent.getServerKickReason().isEmpty()) {
        player.sendMessage(Component.text("Не удалось подключить вас к серверу " + kickevent.getServer().getServerInfo().getName() + ". Попробуйте позже.").color(TextColor.fromHexString("#FF0000")));
      } else {
        player.sendMessage(kickevent.getServerKickReason().get());
      }
      return true;
    });
  }
}
