
package me.RyanWild.CJFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hawkeyeshi
 */

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(
        description = "Donator Chat - Chat With other Donators",
        usage = "/<command> [message...]",
        aliases = "donatorchat")

public class Command_d extends CJFM_Command
{

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if(args.length > 1)
            return false;
        
        if(!CJFM_DonatorList.isSuperDonor(sender) || !TFM_AdminList.isSeniorAdmin(sender))
            return false; 
        
        if(args.length == 1)
        {
            CJFM_Util.donatorChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
        }
                
        return false;
    }
}