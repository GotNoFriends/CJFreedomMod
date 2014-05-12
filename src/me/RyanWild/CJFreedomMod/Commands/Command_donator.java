package me.RyanWild.CJFreedomMod.Commands;

import me.RyanWild.CJFreedomMod.Player.CJFM_Donator;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.RyanWild.CJFreedomMod.CJFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.OP, source = SourceType.BOTH)
@CommandParameters(description = "Manage donors.", usage = "/<command> <list | <add|delete|info> <username>>")
public class Command_donator extends CJFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0 || args.length > 2)
        {
            return false;
        }

        if (args.length == 1)
        {
            if (args[0].equals("list"))
            {
                playerMsg("Donors: " + StringUtils.join(CJFM_DonatorList.getDonatorNames(), ", "), ChatColor.GOLD);
                return true;
            }

            return false;
        }

        if (args[0].equalsIgnoreCase("info"))
        {
            if (!CJFM_DonatorList.isSuperDonor(sender))
            {
                playerMsg(TotalFreedomMod.MSG_NO_PERMS);
                return true;
            }

            CJFM_Donator donor = CJFM_DonatorList.getEntry(args[1].toLowerCase());

            if (donor == null)
            {
                try
                {
                    donor = CJFM_DonatorList.getEntry(getPlayer(args[1]).getName().toLowerCase());
                }
                catch (PlayerNotFoundException ex)
                {
                }
            }

            if (donor == null)
            {
                playerMsg("Superadmin not found: " + args[1]);
            }
            else
            {
                playerMsg(donor.toString());
            }

            return true;
        }

        if (!senderIsConsole)
        {
            playerMsg("This command may only be used from the console.");
            return true;
        }

        if (args[0].equalsIgnoreCase("add"))
        {
            OfflinePlayer player;

            try
            {
                player = getPlayer(args[1]);
            }
            catch (PlayerNotFoundException ex)
            {
                final CJFM_Donator donor = CJFM_DonatorList.getEntry(args[1]);

                if (donor == null)
                {
                    playerMsg(ex.getMessage(), ChatColor.RED);
                    return true;
                }

                player = Bukkit.getOfflinePlayer(donor.getLastLoginName());
            }

            TFM_Util.adminAction(sender.getName(), "Adding " + player.getName() + " to the donor list", true);
            CJFM_DonatorList.addSuperadmin(player);

            return true;
        }

        if ("remove".equals(args[0]))
        {
            if (!CJFM_Util.SYSADMINS.contains(sender.getName()))
            {
                playerMsg(TotalFreedomMod.MSG_NO_PERMS);
                return true;
            }

            String targetName = args[1];

            try
            {
                targetName = getPlayer(targetName).getName();
            }
            catch (PlayerNotFoundException ex)
            {
            }

            if (!CJFM_DonatorList.getLowerSuperNames().contains(targetName.toLowerCase()))
            {
                playerMsg("Donor not found: " + targetName);
                return true;
            }

            TFM_Util.adminAction(sender.getName(), "Removing " + targetName + " from the donor list", true);
            CJFM_DonatorList.removeSuperadmin(Bukkit.getOfflinePlayer(targetName));

            return true;
        }
        return false;

    }
}
