package me.RyanWild.CJFreedomMod;

import me.StevenLawson.TotalFreedomMod.Commands.CJFM_Command;
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

    public static final String COMMAND_PATH = "me.RyanWild.CJFreedomMod.Commands";
    public static final String COMMAND_PREFIX = "Command_";

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
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        try
        {
            Player sender_p = null;
            boolean senderIsConsole = false;
            if (sender instanceof Player)
            {
                sender_p = (Player) sender;
                TFM_Log.info(String.format("[PLAYER_COMMAND] %s(%s): /%s %s",
                        sender_p.getName(),
                        ChatColor.stripColor(sender_p.getDisplayName()),
                        commandLabel,
                        StringUtils.join(args, " ")), true);
            }
            else
            {
                senderIsConsole = true;
                TFM_Log.info(String.format("[CONSOLE_COMMAND] %s: /%s %s",
                        sender.getName(),
                        commandLabel,
                        StringUtils.join(args, " ")), true);
            }

            final TFM_Command dispatcher;
            try
            {
                final ClassLoader classLoader = CJFreedomMod.class.getClassLoader();
                dispatcher = (CJFM_Command) classLoader.loadClass(String.format("%s.%s%s", COMMAND_PATH, COMMAND_PREFIX, cmd.getName().toLowerCase())).newInstance();
                dispatcher.setup(plugin, sender, dispatcher.getClass());
            }
            catch (Throwable ex)
            {
                TFM_Log.severe("Command not loaded: " + cmd.getName() + "\n" + ExceptionUtils.getStackTrace(ex));
                sender.sendMessage(ChatColor.RED + "Command Error: Command not loaded: " + cmd.getName());
                return true;
            }

            try
            {
                if (dispatcher.senderHasPermission())
                {
                    return dispatcher.run(sender, sender_p, cmd, commandLabel, args, senderIsConsole);
                }
                else
                {
                    sender.sendMessage(TotalFreedomMod.MSG_NO_PERMS);
                }
            }
            catch (Throwable ex)
            {
                TFM_Log.severe("Command Error: " + commandLabel + "\n" + ExceptionUtils.getStackTrace(ex));
                sender.sendMessage(ChatColor.RED + "Command Error: " + ex.getMessage());
            }

        }
        catch (Throwable ex)
        {
            TFM_Log.severe("Command Error: " + commandLabel + "\n" + ExceptionUtils.getStackTrace(ex));
            sender.sendMessage(ChatColor.RED + "Unknown Command Error.");
        }

        return true;
    }


}
