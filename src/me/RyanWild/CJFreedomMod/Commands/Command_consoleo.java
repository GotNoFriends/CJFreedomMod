package me.RyanWild.CJFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Commands.AdminLevel;
import me.StevenLawson.TotalFreedomMod.Commands.CommandParameters;
import me.StevenLawson.TotalFreedomMod.Commands.CommandPermissions;
import me.StevenLawson.TotalFreedomMod.Commands.SourceType;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.ONLY_CONSOLE)
@CommandParameters(description = "Broadcasts the given message. Supports colors.", usage = "/<command> <message>")
public class Command_consoleo extends CJFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length > 0)
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                if (TFM_AdminList.isSuperAdmin(player))
                {
                    player.sendMessage(TFM_Util.colorize(StringUtils.join(args, " ")));
                }
            }
        }

        return true;
    }
}
