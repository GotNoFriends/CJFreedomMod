package me.RyanWild.CJFreedomMod.Listener;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CJFM_PlayerListener implements Listener
{
   @EventHandler(priority = EventPriority.HIGH)
   public static void onPlayerJoinEvent(PlayerJoinEvent event)
   {
       Player player = event.getPlayer();
       if (CJFM_Util.SYSADMINS.contains(player.getName()))
       {
           player.setPlayerListName(ChatColor.DARK_RED + player.getName());
       }
       else if (CJFM_Util.EXECUTIVES.contains(player.getName()))
       {
           player.setPlayerListName(ChatColor.RED + player.getName());
       }
       else if (CJFM_Util.DEVELOPERS.contains(player.getName()))
       {
           
       }
       else if(TFM_AdminList.isSeniorAdmin(player))
       {
           player.setPlayerListName(ChatColor.LIGHT_PURPLE + player.getName());
       }
       else if(TFM_AdminList.isSuperAdmin(player))
       {
           player.setPlayerListName(ChatColor.AQUA + player.getName());
       }
   }
}
