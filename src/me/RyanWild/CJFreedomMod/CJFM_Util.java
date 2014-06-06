package me.RyanWild.CJFreedomMod;

import java.util.Arrays;
import java.util.List;
import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerRank;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CJFM_Util
{

    public static TotalFreedomMod plugin;

    public static final List<String> EXECUTIVES = Arrays.asList("Camzie99", "Kyled1986");
    public static final List<String> SYSADMINS = Arrays.asList("wild1145", "Varuct", "thecjgcjg", "darthsalamon");
    public static final List<String> DEVELOPERS = Arrays.asList("Madgeek1450", "DarthSalamon", "wild1145", "Paldiu", "MrPorkSausage", "Camzie99", "hawkeyeshi");
    public static final List<String> FAMOUS = Arrays.asList(
            "skythekidrs", "antvenom", "deadlox", "stampylongnose", "sethbling", "asfjerome", "dantdm", "pokemondanlv45", "zexyzek", "ssundee",
            "explodingtnt", "kurtjmac", "xephos", "honeydew", "captainsparklez", "truemu", "jeb_", "grumm", "notch", "chimneyswift", "vechs",
            "cavemanfilms", "tobyturner", "inthelittlewood", "sips_", "sjin", "lividcofee", "etho");
    /* 

     public static void updateDatabase(String SQLquery) throws SQLException
     {
     Connection c = mySQL.openConnection();
     Statement statement = c.createStatement();
     statement.executeUpdate(SQLquery);
     }

     public void getValueFromDB(String SQLquery) throws SQLException
     {
     Connection c = mySQL.openConnection();
     Statement statement = c.createStatement();
     ResultSet res = statement.executeQuery(SQLquery);
     res.next();
     }
     */
    private final Server server;

    public CJFM_Util(TotalFreedomMod plugin)
    {
        this.plugin = plugin;
        this.server = plugin.getServer();
    }

    public void sendSyncMessage(final CommandSender sendTo, final String message)
    {
        Bukkit.getScheduler().runTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                sendTo.sendMessage(message);
            }
        });
    }

    public static void SeniorAdminChatMessage(CommandSender sender, String message, boolean senderIsConsole)
    {
        String name = sender.getName() + " " + TFM_PlayerRank.fromSender(sender).getPrefix() + ChatColor.WHITE;
        TFM_Log.info("[SENIOR-ADMIN] " + name + ": " + message);

        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (TFM_AdminList.isSeniorAdmin(player))
            {
                player.sendMessage("[" + ChatColor.YELLOW + "SENIOR-ADMIN" + ChatColor.WHITE + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.YELLOW + message);
            }
        }
    }

    public static void donatorChatMessage(CommandSender sender, String message, boolean senderisConsole)
    {
        String name = sender.getName() + " " + TFM_PlayerRank.fromSender(sender).getPrefix() + ChatColor.WHITE;
        TFM_Log.info("[DonatorChat]" + name + ":" + message);

        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (CJFM_DonatorList.isSuperDonor(player)
                    || TFM_AdminList.isSeniorAdmin(player))
            {
                player.sendMessage("[" + ChatColor.LIGHT_PURPLE + "Donator Chat" + ChatColor.WHITE + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.LIGHT_PURPLE + message);
            }
        }
    }

    public static boolean inGod(Player player)
    {
        return TFM_PlayerData.getPlayerData(player).inGod();
    }

    public static void setGod(Player player, boolean enabled)
    {
        TFM_PlayerData.getPlayerData(player).setGod(enabled);
    }
    
     public void msg(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.YELLOW + message);

    }

}
