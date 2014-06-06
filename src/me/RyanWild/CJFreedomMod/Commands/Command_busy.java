package me.RyanWild.CJFreedomMod.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = me.RyanWild.CJFreedomMod.Commands.SourceType.ONLY_IN_GAME)
@CommandParameters(
        description = "Toggles if you are busy",
        usage = "/<command> [clear | message]")
public class Command_busy extends CJFM_Command {
    
    

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        plugin.adminBusy.toggleBusyStatus((Player) sender);
        return true;
    }
}
