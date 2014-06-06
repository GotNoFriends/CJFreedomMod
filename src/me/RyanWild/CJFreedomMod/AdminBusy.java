package me.RyanWild.CJFreedomMod;

import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.server;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AdminBusy extends CJFMAddon {
    private static final int MIN_WORD_LENGTH = 4;
    
    public AdminBusy(TotalFreedomMod plugin)
    {
        super(plugin);
    }
    
    public void toggleBusyStatus(Player player)
    {
        final PlayerManager.PlayerInfo info = plugin.playerManager.getInfo(player);
        
        info.setBusy(!info.isBusy());
        
        TFM_Util.bcastMsg(player + " Toggled busy status o" + (info.isBusy() ? "n" : "ff"), ChatColor.AQUA);
    }
    
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        final String[] words = event.getMessage().split(" ");
        for (final String word : words)
        {
            if (word.length() < MIN_WORD_LENGTH)
            {
                continue;
            }
            
            final Player player = server.getPlayer(word);
            if (player == null)
            {
                continue;
            }
            
            if (!TFM_AdminList.isSuperAdmin(player))
            {
                return;
            }
            
            if (plugin.playerManager.getInfo(player).isBusy())
            {
                plugin.util.sendSyncMessage(event.getPlayer(), ChatColor.RED + player.getName() + " is busy right now, try again later");
                
                
            }
        }
    }
}
