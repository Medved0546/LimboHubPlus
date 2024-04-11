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

package net.elytrium.limbohub;

import java.util.List;
import net.elytrium.commons.config.YamlConfig;
import net.elytrium.commons.kyori.serialization.Serializers;
import net.elytrium.limboapi.api.chunk.Dimension;
import net.elytrium.limboapi.api.file.BuiltInWorldFileType;
import net.elytrium.limboapi.api.player.GameMode;
import net.kyori.adventure.bossbar.BossBar;

public class Settings extends YamlConfig {
  @Ignore
  public static final Settings IMP = new Settings();
  @Final
  public String VERSION = "1.0.5";
  @Comment({"Available serializers:", "LEGACY_AMPERSAND - \"&c&lExample &c&9Text\".", "LEGACY_SECTION - \"§c§lExample §c§9Text\".", "MINIMESSAGE - \"<bold><red>Example</red> <blue>Text</blue></bold>\". (https://webui.adventure.kyori.net/)", "GSON - \"[{\"text\":\"Example\",\"bold\":true,\"color\":\"red\"},{\"text\":\" \",\"bold\":true},{\"text\":\"Text\",\"bold\":true,\"color\":\"blue\"}]\". (https://minecraft.tools/en/json_text.php/)", "GSON_COLOR_DOWNSAMPLING - Same as GSON, but uses downsampling."})
  public Serializers SERIALIZER;
  @Create
  public Settings.MAIN MAIN;

  public Settings() {
    this.SERIALIZER = Serializers.LEGACY_AMPERSAND;
  }

  public static class MAIN {
    @Comment({"World file type:", " SCHEMATIC (MCEdit .schematic, 1.12.2 and lower, not recommended)", " STRUCTURE (structure block .nbt, any Minecraft version is supported, but the latest one is recommended).", " WORLDEDIT_SCHEM (WorldEdit .schem, any Minecraft version is supported, but the latest one is recommended)."})
    public boolean CHECK_ON_UPDATES = true;
    public BuiltInWorldFileType WORLD_FILE_TYPE;
    public String WORLD_FILE_PATH;
    @Comment({"World time in ticks (24000 ticks == 1 in-game day)"})
    public long WORLD_TICKS;
    @Comment({"World light level (from 0 to 15)"})
    public int WORLD_LIGHT_LEVEL;
    @Comment({"Should we override block light level (to light up the nether and the end)"})
    public boolean WORLD_OVERRIDE_BLOCK_LIGHT_LEVEL;
    @Comment({"Available: ADVENTURE, CREATIVE, SURVIVAL, SPECTATOR"})
    public GameMode GAME_MODE;
    public boolean LOAD_SKIN;
    @Comment({"You can get skin properties at https://mineskin.org/."})
    public String VALUE;
    public String SIGNATURE;
    @Comment({"(255 - all skin parts)", "Sum of the following numbers:", "Cape - 1", "Jacket - 2", "Left Sleeve - 4", "Right Sleeve -  8", "Left Leg - 16", "Right Leg - 32", "Hat - 64"})
    public int DISPLAYED_SKIN_PARTS;
    @Comment({"Available dimensions: OVERWORLD, NETHER, THE_END"})
    public Dimension DIMENSION;
    public int MAX_SUPPRESS_PACKET_LENGTH;
    @Create
    public Settings.MAIN.HUB_COMMAND HUB_COMMAND;
    @Comment({"Message that will be sent when player joins the hub."})
    public List<String> WELCOME_MESSAGE;
    @Comment({"Title that will be displayed when player joins the hub."})
    public String WELCOME_TITLE;
    public String WELCOME_SUBTITLE;
    public int WELCOME_TITLE_FADE_IN_MILLIS;
    public int WELCOME_TITLE_STAY_MILLIS;
    public int WELCOME_TITLE_FADE_OUT_MILLIS;
    @Comment({"Player will teleported back to the spawn position if it falls below the `y-limit` when enabled."})
    public boolean ENABLE_Y_LIMIT;
    public int Y_LIMIT;
    @Comment({"Try increasing this value if NPC skins are not loading."})
    public int SKIN_LOAD_SECONDS;
    @Create
    public Settings.MAIN.WORLD_COORDS WORLD_COORDS;
    @Create
    public Settings.MAIN.PLAYER_COORDS PLAYER_COORDS;
    @Comment({"Menu that will be opened when player joins the hub.", "Set to blank value (default-menu: \"\") to disable."})
    public String DEFAULT_MENU;
    @NewLine
    @Comment({"List of boss bars that will be set for the player in the specified order. Set to empty list (bossbars: []) to disable."})
    public List<Settings.MAIN.BOSSBAR> BOSSBARS;
    @NewLine
    @Create
    public Settings.MAIN.SIDEBAR SIDEBAR;
    @NewLine
    @Comment({"Commands that will be available in the hub."})
    public List<Settings.MAIN.COMMAND> COMMANDS;
    @NewLine
    public List<Settings.MAIN.MENU> MENUS;
    @NewLine
    public List<Settings.MAIN.NPC> NPCS;
    @NewLine
    public List<Settings.MAIN.ITEM_DATA> PLAYER_ITEMS;
    @Comment({"Cooldown in milliseconds."})
    public int ITEM_COOLDOWN;
    @NewLine
    public List<Settings.MAIN.HOLOGRAM> HOLOGRAMS;
    @NewLine
    public List<Settings.MAIN.PORTAL> PORTALS;

    public MAIN() {
      this.WORLD_FILE_TYPE = BuiltInWorldFileType.STRUCTURE;
      this.WORLD_FILE_PATH = "world.nbt";
      this.WORLD_TICKS = 1000L;
      this.WORLD_LIGHT_LEVEL = 15;
      this.WORLD_OVERRIDE_BLOCK_LIGHT_LEVEL = true;
      this.GAME_MODE = GameMode.ADVENTURE;
      this.LOAD_SKIN = false;
      this.VALUE = "";
      this.SIGNATURE = "";
      this.DISPLAYED_SKIN_PARTS = 255;
      this.DIMENSION = Dimension.OVERWORLD;
      this.MAX_SUPPRESS_PACKET_LENGTH = 65536;
      this.WELCOME_MESSAGE = List.of("&7Type /menu to open menu.");
      this.WELCOME_TITLE = "";
      this.WELCOME_SUBTITLE = "";
      this.WELCOME_TITLE_FADE_IN_MILLIS = 100;
      this.WELCOME_TITLE_STAY_MILLIS = 2000;
      this.WELCOME_TITLE_FADE_OUT_MILLIS = 100;
      this.ENABLE_Y_LIMIT = true;
      this.Y_LIMIT = -10;
      this.SKIN_LOAD_SECONDS = 3;
      this.DEFAULT_MENU = "";
      this.BOSSBARS = List.of(Settings.createNodeSequence(BOSSBAR.class, new Object[]{false, "BossBar 1", 1.0D, BossBar.Color.PINK, BossBar.Overlay.PROGRESS, 5000}), Settings.createNodeSequence(BOSSBAR.class, new Object[]{false, "BossBar 2", 0.5D, BossBar.Color.RED, BossBar.Overlay.PROGRESS, 5000}), Settings.createNodeSequence(BOSSBAR.class, new Object[]{false, "BossBar 3", 0.0D, BossBar.Color.BLUE, BossBar.Overlay.PROGRESS, 5000}), Settings.createNodeSequence(BOSSBAR.class, new Object[]{true, "", 0.0D, BossBar.Color.PINK, BossBar.Overlay.PROGRESS, 5000}));
      this.COMMANDS = List.of(Settings.createNodeSequence(COMMAND.class, new Object[]{"menu", List.of("servers"), Settings.createNodeSequence(ACTION.class, ACTION.Type.OPEN_MENU, "menu")}), Settings.createNodeSequence(COMMAND.class, new Object[]{"server1", List.of(), Settings.createNodeSequence(ACTION.class, ACTION.Type.CONNECT_TO_SERVER, "server1")}));
      this.MENUS = List.of(Settings.createNodeSequence(MENU.class, new Object[]{"menu", "Menu", List.of(Settings.createNodeSequence(MENU.ITEM_DATA.class, new Object[]{".", "minecraft:black_stained_glass_pane", List.of("minecraft:white_stained_glass_pane"), 1, 15, false, 0, false, "", "", List.of()}), Settings.createNodeSequence(MENU.ITEM_DATA.class, new Object[]{"1", "minecraft:leather_helmet", List.of(), 1, 0, true, 12544467, false, "", "&fOpen another menu", List.of()}), Settings.createNodeSequence(MENU.ITEM_DATA.class, new Object[]{"2", "minecraft:stone", List.of(), 2, 0, false, 0, true, "", "&fServer1", List.of("&r&7This is a server.")}), Settings.createNodeSequence(MENU.ITEM_DATA.class, new Object[]{"3", "minecraft:player_head", List.of("minecraft:experience_bottle"), 1, 0, false, 0, false, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjcwNWZkOTRhMGM0MzE5MjdmYjRlNjM5YjBmY2ZiNDk3MTdlNDEyMjg1YTAyYjQzOWUwMTEyZGEyMmIyZTJlYyJ9fX0=", "&fWhat is this?", List.of()})), List.of(".........", "...123...", "........."), MENU.DEFAULT_ACTION_TYPE.DO_NOTHING, List.of(Settings.createNodeSequence(MENU.MENU_ACTION.class, new Object[]{List.of("3,1"), Settings.createNodeSequence(ACTION.class, ACTION.Type.OPEN_MENU, "menu2")}), Settings.createNodeSequence(MENU.MENU_ACTION.class, new Object[]{List.of("4,1"), Settings.createNodeSequence(ACTION.class, ACTION.Type.CONNECT_TO_SERVER, "server1")}), Settings.createNodeSequence(MENU.MENU_ACTION.class, new Object[]{List.of("5,1"), Settings.createNodeSequence(ACTION.class, ACTION.Type.SEND_MESSAGE, "&7This is a virtual hub!")}))}), Settings.createNodeSequence(MENU.class, new Object[]{"menu2", "Another Menu", List.of(Settings.createNodeSequence(MENU.ITEM_DATA.class, new Object[]{".", "minecraft:black_stained_glass_pane", List.of("minecraft:white_stained_glass_pane"), 1, 15, false, 0, false, "", "", List.of()}), Settings.createNodeSequence(MENU.ITEM_DATA.class, new Object[]{"1", "minecraft:barrier", List.of(), 1, 0, false, 0, false, "", "&fClose menu", List.of()}), Settings.createNodeSequence(MENU.ITEM_DATA.class, new Object[]{"2", "minecraft:bedrock", List.of(), 1, 0, false, 0, false, "", "&fGo back", List.of()})), List.of(".........", ".       .", ".  1 2  .", ".       .", "........."), MENU.DEFAULT_ACTION_TYPE.DO_NOTHING, List.of(Settings.createNodeSequence(MENU.MENU_ACTION.class, new Object[]{List.of("3,2"), Settings.createNodeSequence(ACTION.class, ACTION.Type.CLOSE_MENU, "")}), Settings.createNodeSequence(MENU.MENU_ACTION.class, new Object[]{List.of("5,2"), Settings.createNodeSequence(ACTION.class, ACTION.Type.OPEN_MENU, "menu")}))}));
      this.NPCS = List.of(Settings.createNodeSequence(NPC.class, new Object[]{"&7Click to open menu", 0.0D, 0.0D, 5.0D, 270.0D, 0.0D, true, Settings.createNodeSequence(NPC.SKIN_DATA.class, "09469aaa-422e-4717-a73f-bd530dbd6c7e", "ewogICJ0aW1lc3RhbXAiIDogMTY1NDMzNjk2MDgzNiwKICAicHJvZmlsZUlkIiA6ICIyZDlhNWExYjIzZWQ0MWRkYjgzMWMzZjM3Zjk2NzA3ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJBd2Vzb21lS2FsaW41NSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9jOGJjZTJmMWExY2QyNWZiYTU2MWU3NjI2ODYxYmYwMzA1OGY5MGI1OTNhY2MwODE2ZWZlNGZmZGY0ZGUwNmE0IgogICAgfQogIH0KfQ==", "PsdiXxQldurw+qrNHyXRkAhPpcLeY/c8hTW7lr8S1kGFE2P4OLuQZbnjzKxLCCcudhBBDgjU/3hJNXgC1Spnz4ywT1+RVCJ/F4SQFh39Y8mQQra1dz4jM0mvTfyCttTLY/sP+g91HCirYfmS6xXcmaFW9aGhFJasJ5xwXQ9Ez9mdVgajeBDh7DxPv4dKkyQsE13XD8Z4ipgRUsDflLIF2oDF8jLSgTpnlukJdlFmJIBPcO+g8S5K1V4UVxeZFRykuu1Hqv9g0BU56A2uKYGF7xKL9xUMsvRoRl0NbdJgWtuJRmt8ybMdD+KKhIQ2TNO/7hNKkZV8t5vWXPbLRS/8Q1KtINwVtJyha1SZ0jTxbrGrFmD2VxzNJMI2KcFBINS66+zVopgAV79xIiqQEa3x7PsCPUOGbNZO8oc5DmFstiBo/ypyZERZnwAvkrxag97Q7wPQrXkEmiYQWp4d5CWg8yfg0h3PJGUT58hcw9poaLSXS9lpA4Zp1nv+BWx8U32rczTqaEKCaW3p2b9mRmEYTcGl7+GsCsbLDuBDiU/JNVEUu8bhzVDKBNGaIHR2nVWoqZG0nReMx7b11j53T3ubAj3ATLuYIyRBqysNeztACo98ynMBVLn7gXaYDhfxqtWYxvLkrYpk/JvPOwoDX2gUUFGf3i+oQbJHW6YR5brqrCw="), 1000, Settings.createNodeSequence(ACTION.class, ACTION.Type.OPEN_MENU, "menu")}));
      this.PLAYER_ITEMS = List.of(Settings.createNodeSequence(ITEM_DATA.class, new Object[]{"minecraft:compass", 1, 36, 1, false, 0, true, null, "bebra", List.of("right click to sniff..."), Settings.createNodeSequence(ACTION.class, ACTION.Type.CONNECT_TO_SERVER, "server1")}));
      this.ITEM_COOLDOWN = 1000;
      this.HOLOGRAMS = List.of(Settings.createNodeSequence(HOLOGRAM.class, new Object[]{5.0D, 1.0D, 5.0D, List.of("&fThis", "&7is a", "&8hologram.")}));
      this.PORTALS = List.of(Settings.createNodeSequence(PORTAL.class, new Object[]{10, 0, 10, 20, 1, 20, Settings.createNodeSequence(ACTION.class, ACTION.Type.CONNECT_TO_SERVER, "server1")}));
    }

    public static class BOSSBAR {
      @Comment({"Hides boss bar"})
      public boolean HIDDEN = false;
      public String NAME = "";
      public double PROGRESS = 1.0D;
      public BossBar.Color COLOR;
      public BossBar.Overlay OVERLAY;
      @Comment({"Set -1 to mark this boss bar as the last one. (Next boss bar won't be displayed)"})
      public long STAY_TIME_MILLIS;

      public BOSSBAR() {
        this.COLOR = BossBar.Color.PINK;
        this.OVERLAY = BossBar.Overlay.PROGRESS;
        this.STAY_TIME_MILLIS = -1L;
      }
    }

    public static class COMMAND {
      public String COMMAND;
      public List<String> ALIASES;
      @Comment({"Action to perform when the command is executed."})
      @Create
      public Settings.MAIN.ACTION ACTION;
    }

    public static class ACTION {
      @Comment({"Available values: DO_NOTHING, CLOSE_MENU, SEND_MESSAGE, OPEN_MENU, CONNECT_TO_SERVER, KICK_PLAYER, TELEPORT_PLAYER"})
      public Settings.MAIN.ACTION.Type TYPE;
      @Comment({"Depends on action type:", "DO_NOTHING: Not used.", "CLOSE_MENU: Not used.", "SEND_MESSAGE: Message to send, lines should be separated with {NL}.", "OPEN_MENU: Menu ID", "CONNECT_TO_SERVER: Server name (as in velocity.toml).", "KICK_PLAYER: Kick reason", "TELEPORT_PLAYER: Coordinates in \"x y z yaw pitch\" format."})
      public String DATA;

      public ACTION() {
        this.TYPE = ACTION.Type.DO_NOTHING;
        this.DATA = "";
      }

      public enum Type {
        DO_NOTHING,
        CLOSE_MENU,
        SEND_MESSAGE,
        OPEN_MENU,
        CONNECT_TO_SERVER,
        KICK_PLAYER,
        TELEPORT_PLAYER;

        // $FF: synthetic method
        private static Settings.MAIN.ACTION.Type[] $values() {
          return new Settings.MAIN.ACTION.Type[]{DO_NOTHING, CLOSE_MENU, SEND_MESSAGE, OPEN_MENU, CONNECT_TO_SERVER, KICK_PLAYER, TELEPORT_PLAYER};
        }
      }
    }

    public static class MENU {
      @Comment({"Menu ID that will be used in OPEN_MENU actions."})
      public String MENU_ID = "menu";
      public String TITLE = "Menu";
      @Comment({"Items that will be used in `menu-contents`."})
      public List<Settings.MAIN.MENU.ITEM_DATA> ITEMS = List.of();
      @Comment({"Menu content. The size of the menu depends on the length and number of lines.", "For example, 6 lines of 9 characters each represent a 9x6 menu.", "Each character represents an item (see `item-id`); space is an empty slot."})
      public List<String> MENU_CONTENTS = List.of("         ", "         ", "         ");
      @Comment({"The default action to be performed when player clicks on slot.", "Available values: DO_NOTHING, CLOSE_MENU."})
      public Settings.MAIN.MENU.DEFAULT_ACTION_TYPE DEFAULT_ACTION;
      public List<Settings.MAIN.MENU.MENU_ACTION> ACTIONS;

      public MENU() {
        this.DEFAULT_ACTION = MENU.DEFAULT_ACTION_TYPE.DO_NOTHING;
        this.ACTIONS = List.of();
      }

      public enum DEFAULT_ACTION_TYPE {
        DO_NOTHING(Settings.createNodeSequence(ACTION.class, new Object[]{ACTION.Type.DO_NOTHING, ""})),
        CLOSE_MENU(Settings.createNodeSequence(ACTION.class, new Object[]{ACTION.Type.CLOSE_MENU, ""}));

        private final Settings.MAIN.ACTION action;

        DEFAULT_ACTION_TYPE(Settings.MAIN.ACTION action) {
          this.action = action;
        }

        public Settings.MAIN.ACTION getAction() {
          return this.action;
        }

        // $FF: synthetic method
        private static Settings.MAIN.MENU.DEFAULT_ACTION_TYPE[] $values() {
          return new Settings.MAIN.MENU.DEFAULT_ACTION_TYPE[]{DO_NOTHING, CLOSE_MENU};
        }
      }

      public static class MENU_ACTION {
        @Comment({"List of slots in one of the following formats: \"slotNumber\", \"column,row\"."})
        public List<String> SLOTS = List.of();
        @Comment({"Action to perform when player clicks on specified slots."})
        @Create
        public Settings.MAIN.ACTION ACTION = new Settings.MAIN.ACTION();
      }

      public static class ITEM_DATA {
        @Comment({"Any character that will represent this item in `menu-contents`."})
        public String ID = "";
        @Comment({"Modern item ID, e.g. \"minecraft:stone\"."})
        public String ITEM = "";
        public List<String> FALLBACK_ITEMS = List.of();
        public int COUNT = 1;
        @Comment({"Only used on legacy versions (1.12.2 and lower)."})
        public int DATA = 0;
        public boolean HAS_COLOR = false;
        @Comment({"You can get color value at https://notwoods.github.io/minecraft-tools/armorcolor/."})
        public int COLOR = 0;
        public boolean ENCHANTED = false;
        @Comment({"Player skin value.", "You can get this value at https://mineskin.org/ or https://minecraft-heads.com/."})
        public String SKULL_OWNER = "";
        public String CUSTOM_NAME = "";
        public List<String> LORE = List.of();
      }
    }

    public static class NPC {
      public String DISPLAY_NAME = "";
      public double X = 0.0D;
      public double Y = 0.0D;
      public double Z = 0.0D;
      public double YAW = 0.0D;
      public double PITCH = 0.0D;
      public boolean LOAD_SKIN = false;
      @Comment({"You can get skin properties at https://mineskin.org/."})
      @Create
      public Settings.MAIN.NPC.SKIN_DATA SKIN_DATA;
      @Comment({"Cooldown in milliseconds."})
      public int NPC_COOLDOWN = 1000;
      @Comment({"Action to be performed when player clicks on NPC."})
      @Create
      public Settings.MAIN.ACTION ACTION;

      public static class SKIN_DATA {
        public String UUID = "";
        public String VALUE = "";
        public String SIGNATURE = "";
        @Comment({"(255 - all skin parts)", "Sum of the following numbers:", "Cape - 1", "Jacket - 2", "Left Sleeve - 4", "Right Sleeve -  8", "Left Leg - 16", "Right Leg - 32", "Hat - 64"})
        public int DISPLAYED_SKIN_PARTS = 255;
      }
    }

    public static class ITEM_DATA {
      @Comment({"Modern item ID, e.g. \"minecraft:stone\"."})
      public String ITEM = "";
      public int COUNT = 1;
      public int SLOT = 36;
      @Comment({"Only used on legacy versions (1.12.2 and lower)."})
      public int DATA = 1;
      public boolean HAS_COLOR = false;
      @Comment({"You can get color value at https://notwoods.github.io/minecraft-tools/armorcolor/."})
      public int COLOR = 0;
      public boolean ENCHANTED = false;
      @Comment({"Player skin value.", "You can get this value at https://mineskin.org/ or https://minecraft-heads.com/."})
      public String SKULL_OWNER = "";
      public String CUSTOM_NAME = "";
      public List<String> LORE = List.of();
      @Comment({"Action to be performed when player clicks on NPC."})
      @Create
      public Settings.MAIN.ACTION ACTION;
    }

    public static class HOLOGRAM {
      public double X = 0.0D;
      public double Y = 0.0D;
      public double Z = 0.0D;
      public List<String> LINES = List.of();
    }

    public static class PORTAL {
      public int START_X = 0;
      public int START_Y = 0;
      public int START_Z = 0;
      public int END_X = 0;
      public int END_Y = 0;
      public int END_Z = 0;
      @Create
      public Settings.MAIN.ACTION ACTION;
    }

    public static class SIDEBAR {
      public boolean ENABLED = true;
      public String TITLE = "This is a sidebar.";
      public List<String> LINES = List.of("&7Configure me in config.yml.");
    }

    public static class PLAYER_COORDS {
      public double X = 0.0D;
      public double Y = 0.0D;
      public double Z = 0.0D;
      public double YAW = 90.0D;
      public double PITCH = 0.0D;
    }

    public static class WORLD_COORDS {
      public int X = 0;
      public int Y = 0;
      public int Z = 0;
    }

    public static class HUB_COMMAND {
      public boolean REQUIRE_PERMISSION = false;
      public List<String> ALIASES = List.of("hub", "lobby");
    }
  }
}
