package me.thevipershow.mcbots;

import java.util.Objects;
import me.thevipershow.mcbots.commands.BotsCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mcbots extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin logic startup code ->
        Objects.requireNonNull(Bukkit.getPluginCommand("bots")).setExecutor(new BotsCommand());
    }
}
