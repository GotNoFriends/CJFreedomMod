package me.RyanWild.CJFreedomMod.Commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import me.StevenLawson.TotalFreedomMod.Commands.AdminLevel;
import me.StevenLawson.TotalFreedomMod.Commands.SourceType;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandPermissions
{
    AdminLevel level();
    
    DonatorLevel dlevel() default DonatorLevel.NONE;

    SourceType source();

    boolean block_host_console() default false;
}
