package me.RyanWild.CJFreedomMod.Commands;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hawkeyeshi
 */
@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "For all the noobs out there", usage = "/<command> <player>")
public class Command_noob extends CJFM_Command
{

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 1)
        {
            return false;
        }

        Player target = null;

        try
        {
            target = getPlayer(args[0]);
        }
        catch (PlayerNotFoundException ex)
        {
            Logger.getLogger(Command_noob.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (target != null)
        {
            playerMsg(sender, TotalFreedomMod.PLAYER_NOT_FOUND);
            return false;
        }

        TFM_Util.adminAction(sender.getName(), "Deeming " + target.getName() + " a complete and total noob!", true);
        TFM_Util.bcastMsg(target.getName() + " has been deemed a complete noob!", ChatColor.RED);

        final Location targetPos = target.getLocation();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }

        TFM_BanManager.getInstance().addUuidBan(
                new TFM_Ban(target.getUniqueId(), target.getName(), sender.getName(), TFM_Util.parseDateOffset("5m"), ChatColor.RED + "You have been temporarily banned for 5 minutes."));
        target.kickPlayer(ChatColor.RED + "You have been deemed a complete noob. You've been banned for five minutes.");

        return true;
    }

}
