package me.RyanWild.CJFreedomMod;

import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
public class CJFMAddon
{

    protected final TotalFreedomMod plugin;
    protected final CJFM_Util util;

    public CJFMAddon(TotalFreedomMod plugin)
    {
        this.plugin = plugin;
        this.util = plugin.util;
    }
}
