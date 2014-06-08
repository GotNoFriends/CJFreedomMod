package me.RyanWild.CJFreedomMod;

import me.RyanWild.CJFreedomMod.Player.CJFM_DonatorList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import me.RyanWild.CJFreedomMod.Listener.CJFM_PlayerListener;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.mySQL;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.plugin;
import static me.StevenLawson.TotalFreedomMod.TotalFreedomMod.server;
import org.bukkit.plugin.PluginManager;

public class CJFreedomMod
{

    public static final String DONATOR_FILE = "donator.yml";

    public void onEnable()
    {
        loadDonatorConfig();
    }

    public static void loadDonatorConfig()
    {
        try
        {
            CJFM_DonatorList.load();
            CJFM_DonatorList.createBackup();
        }
        catch (Exception ex)
        {
            TFM_Log.severe("Error loading donator list: " + ex.getMessage());
        }
    }

    /*   public static void updateDatabase(String SQLquery) throws SQLException
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
     }*/
}
