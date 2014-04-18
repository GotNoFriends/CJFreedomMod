package me.RyanWild.CJFreedomMod;

import me.RyanWild.CJFreedomMod.Commands.CJFM_Commands;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.plugin;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import net.minecraft.util.org.apache.commons.lang3.exception.ExceptionUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CJFreedomMod
{

    public static final String DONATOR_FILE = "donator.yml";



    public void onEnable()
    {
        loadDonatorConfig();
    }

    public static void loadDonatorConfig()
    {
        try
        {
            CJFM_DonatorList.backupSavedList();
            CJFM_DonatorList.loadDonatorList();
        }
        catch (Exception ex)
        {
            TFM_Log.severe("Error loading donator list: " + ex.getMessage());
        }
    }

}
