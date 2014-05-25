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

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Changes god mode", usage = "/<command> <player>", aliases = "/egod")
public class Command_god extends CJFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            CJFM_Util.setGod(sender_p, !CJFM_Util.inGod(sender_p));
            TFM_Util.playerMsg(sender_p, "God mode set to " + CJFM_Util.inGod(sender_p));
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
                CJFM_Util.setGod(player, !CJFM_Util.inGod(player));
                TFM_Util.playerMsg(player, "God mode of " + player.getName() + " set to " + CJFM_Util.inGod(player));
            }
        }
        return true;
    }
}
