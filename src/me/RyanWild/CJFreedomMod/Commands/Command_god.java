package me.RyanWild.CJFreedomMod.Commands;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_CONSOLE)
@CommandParameters(description = "Changes god mode", usage = "/<command> <player>", aliases = "/egod")
public class Command_god extends CJFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (CJFM_Util.GOD.contains(sender_p.getName()))
            {
                CJFM_Util.GOD.remove(sender_p.getName());
                TFM_Util.playerMsg(sender, "God disabled!", ChatColor.GOLD);        
            }
            if (!CJFM_Util.GOD.contains(sender_p.getName()))
            {
                CJFM_Util.GOD.add(sender_p.getName());
                TFM_Util.playerMsg(sender, "God enabled!", ChatColor.GOLD);
            }
        }
        if (args.length == 1 && TFM_AdminList.isSuperAdmin(sender))
        {
            Player player = null;
            try {
                player = getPlayer(args[0]);
            }
            catch (PlayerNotFoundException ex) {
                Logger.getLogger(Command_god.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (player == null)
            {
                TFM_Util.playerMsg(sender, TotalFreedomMod.PLAYER_NOT_FOUND);
            }
            else
            {
                if (CJFM_Util.GOD.contains(player.getName()))
                {
                    CJFM_Util.GOD.remove(player.getName());
                    TFM_Util.playerMsg(player, "God disabled!", ChatColor.GOLD);
                    TFM_Util.playerMsg(sender, "God disabled for " + player.getName(), ChatColor.GOLD);
                }
                if (!CJFM_Util.GOD.contains(player.getName()))
                {
                    CJFM_Util.GOD.add(player.getName());
                    TFM_Util.playerMsg(player, "God enabled!", ChatColor.GOLD);
                    TFM_Util.playerMsg(sender, "God enabled for " + player.getName(), ChatColor.GOLD);
                }
            }
        }
        return true;
    }
}
