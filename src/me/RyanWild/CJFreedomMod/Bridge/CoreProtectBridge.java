package me.RyanWild.CJFreedomMod.Bridge;

import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.plugin.Plugin;

public class CoreProtectBridge
{

    private CoreProtectAPI getCoreProtect()
    {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

        if (plugin == null || !(plugin instanceof CoreProtect))
        {
            return null;
        }

        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (CoreProtect.APIVersion() < 2)
        {
            return null;
        }
        return CoreProtect;
    }
}
