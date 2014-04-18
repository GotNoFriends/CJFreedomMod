package me.RyanWild.CJFreedomMod;

import me.StevenLawson.TotalFreedomMod.TFM_Log;

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
            CJFM_DonatorList.backupSavedList();
            CJFM_DonatorList.loadDonatorList();
        }
        catch (Exception ex)
        {
            TFM_Log.severe("Error loading donator list: " + ex.getMessage());
        }
    }

}
