package me.RyanWild.CJFreedomMod.Listener;

import java.util.List;
import java.util.UUID;
import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class CJFM_PlayerListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public static void onPlayerJoinEvent(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        
      /*  for (String username : (List<String>) TFM_ConfigEntry.UNBANNABLE_USERNAMES.getList())
        {
            player.setPlayerListName("[Fake]" + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&7Fake&8]");
            TFM_Util.bcastMsg(":WARNING: " + player.getName() + " is completely and utterly FAKE! - This server is in Offline Mode so anybody can join as anyone!", ChatColor.RED);
        } */

        if (TFM_AdminList.isSuperAdmin(player) && !player.getName().equalsIgnoreCase("varuct"))
        {
            TFM_PlayerData.getPlayerData(player).setCommandSpy(true);
        }
        if (CJFM_Util.FAMOUS.contains(player.getName().toLowerCase()))
        {
            player.setPlayerListName("[Fake]" + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&7Fake&8]");
            TFM_Util.bcastMsg(":WARNING: " + player.getName() + " is completely and utterly FAKE! - This server is in Offline Mode so anybody can join as anyone!", ChatColor.RED);
        }
        else if (CJFM_Util.SYSADMINS.contains(player.getName()))
        {
            player.setPlayerListName(ChatColor.DARK_RED + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&4System-Admin&8]");
        }
        else if (CJFM_Util.EXECUTIVES.contains(player.getName()))
        {
            player.setPlayerListName(ChatColor.BLUE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&9Executive&8]");
        }
        else if (CJFM_Util.DEVELOPERS.contains(player.getName()))
        {
            player.setPlayerListName(ChatColor.DARK_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&5Developer&8]");
        }
        else if (TFM_AdminList.isSeniorAdmin(player))
        {
            player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&dSenior Admin&8]");
        }
        else if (TFM_AdminList.isTelnetAdmin(player, true))
        {
            player.setPlayerListName(ChatColor.GREEN + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&2Telnet Admin&8]");
        }
        else if (TFM_AdminList.isSuperAdmin(player))
        {
            player.setPlayerListName(ChatColor.AQUA + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&8[&BSuper Admin&8]");
        }
        else if (CJFM_DonatorList.isSeniorDonor(player))
        {
            player.setPlayerListName(ChatColor.GOLD + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&6[Senior Donator]");
        }
        else if (CJFM_DonatorList.isSuperDonor(player))
        {
            player.setPlayerListName(ChatColor.YELLOW + player.getName());
            TFM_PlayerData.getPlayerData(player).setTag("&e[Super Donator]");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        Player player = event.getPlayer();
        String command = event.getMessage().toLowerCase().trim();

        if (TFM_AdminList.isSuperAdmin(player))
        {
            for (Player pl : Bukkit.getOnlinePlayers())
            {
                if (TFM_AdminList.isSeniorAdmin(pl) && pl != player && TFM_PlayerData.getPlayerData(pl).cmdspyEnabled())
                {
                    TFM_Util.playerMsg(pl, player.getName() + ": " + command);
                }
            }
        }

        if (command.contains("175:") || command.contains("double_plant:"))
        {
            event.setCancelled(true);
            TFM_Util.bcastMsg(player.getName() + " just attempted to use the crash item! Deal with them appropriately please!", ChatColor.DARK_RED);
        }
    }

    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent event)
    {
        final Player player = event.getPlayer();
        if (event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE)
        {
            player.setFlying(false);
            Vector jump = player.getLocation().getDirection().multiply(0.8).setY(1.1);
            player.setVelocity(player.getVelocity().add(jump));
            event.setCancelled(true);
        }
    }
}
