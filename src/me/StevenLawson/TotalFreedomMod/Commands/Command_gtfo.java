package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import net.minecraft.util.org.apache.commons.lang3.ArrayUtils;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Makes someone GTFO (deop and ip ban by username).", usage = "/<command> <partialname>")
public class Command_gtfo extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        
        if (args.length == 0)
        {
            return false;
        }
        
        final Player player = getPlayer(args[0]);
        
        if (player == null)
        {
            playerMsg(TotalFreedomMod.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }
        
        String reason = null;
        if (args.length >= 2)
        {
            reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        }
        
        TFM_Util.bcastMsg(player.getName() + " is about to be gassed.", ChatColor.RED);

        // set gamemode to survival:
        player.setGameMode(GameMode.SURVIVAL);
        
        player.setHealth(0);
        
        player.sendMessage("It has been nice knowing you. Now goodbye. HEIL HITTLER.");
        return true;
    }
}
