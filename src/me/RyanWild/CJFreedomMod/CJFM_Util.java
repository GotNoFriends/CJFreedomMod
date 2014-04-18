package me.RyanWild.CJFreedomMod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
//import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.mySQL;
//import net.coreprotect.CoreProtect;
//import net.coreprotect.CoreProtectAPI;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CJFM_Util
{

    public static final List<String> EXECUTIVES = Arrays.asList("camzie99", "phoenix411", "kyled1986", "andoodle");
    public static final List<String> SYSADMINS = Arrays.asList("wild1145", "varuct", "thecjgcjg", "darthsalamon");
    public static final List<String> DEVELOPERS = Arrays.asList("Madgeek1450", "DarthSalamon", "wild1145", "Paldiu", "MrPorkSausage", "Camzie99");


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
}
