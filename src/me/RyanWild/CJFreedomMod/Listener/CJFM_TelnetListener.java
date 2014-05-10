package me.StevenLawson.TotalFreedomMod.Listener;

import me.StevenLawson.BukkitTelnet.api.TelnetPreLoginEvent;
import me.StevenLawson.TotalFreedomMod.TFM_Admin;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class CJFM_TelnetListener implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL)
    public void onTelnetPreLogin(TelnetPreLoginEvent event)
    {
        final String ip = event.getIp();
        if (ip == null || ip.isEmpty())
        {
            return;
        }

        final TFM_Admin admin = TFM_AdminList.getEntryByIp(ip, true);

        if (admin == null || !admin.isTelnetAdmin())
        {
            return;
        }
        
        TFM_Util.bcastMsg(admin.getLastLoginName() + " connected via Telnet!", ChatColor.GREEN);
    }
}
