package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Bridge.TFM_EssentialsBridge;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.confuser.barapi.BarAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Essentials Interface Command - Remove the nickname of all players on the server.", usage = "/<command>")
public class Command_denick extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.adminAction(sender.getName(), "Removing all nicknames", false);
        BarAPI.setMessage(ChatColor.RED + "" + ChatColor.BOLD + sender.getName() + " Has removed all nicknames from the server. ", 60);

        for (Player player : server.getOnlinePlayers())
        {
            TFM_EssentialsBridge.getInstance().setNickname(player.getName(), null);
        }

        return true;
    }
}
