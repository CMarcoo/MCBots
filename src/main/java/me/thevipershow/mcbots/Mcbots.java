package me.thevipershow.mcbots;

import java.util.Objects;
import java.util.logging.Logger;
import me.thevipershow.mcbots.bots.Active;
import me.thevipershow.mcbots.commands.BotsCommand;
import me.thevipershow.spigotchatlib.chat.TextMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mcbots extends JavaPlugin implements Listener {

    private final Logger logger = getLogger();

    private void broadcastGlobal(String... message) {
        Bukkit.broadcastMessage(TextMessage.build(message).color().getText());
    }

    @Override
    public void onEnable() {
        // Plugin logic startup code ->
        Objects.requireNonNull(Bukkit.getPluginCommand("bots")).setExecutor(new BotsCommand());
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        Active.getInstance().botPlayersSet.forEach(bot -> {
            bot.showSingle(event.getPlayer());
            broadcastGlobal("&7Showing bot &3" + bot.getBotName() + " &7to &b" + player.getName());
        });
    }
}
