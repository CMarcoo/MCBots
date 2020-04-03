/*
 * 02/04/20, 12:56 - MCBots
 * ------------------------------------------------------------------------------------
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package me.thevipershow.mcbots.commands;

import me.thevipershow.mcbots.bots.Active;
import me.thevipershow.mcbots.commands.enums.Messages;
import me.thevipershow.mcbots.commands.enums.Permissions;
import me.thevipershow.mcbots.commands.enums.SkinTexture;
import me.thevipershow.mcbots.exceptions.BotException;
import me.thevipershow.mcbots.player.BotPlayer;
import me.thevipershow.spigotchatlib.chat.TextMessage;
import me.thevipershow.spigotchatlib.chat.builders.HoverMessageBuilder;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BotsCommand implements CommandExecutor {

    private static boolean isValidUsername(final String username) {
        return username.matches("[a-zA-Z0-9_]{3,16}");
    }

    private static void botCreatedNotification(final Player player, final Location location, final BotPlayer botPlayer) {
        player.spigot().sendMessage(HoverMessageBuilder.buildHover(
                TextMessage.build("&7[&bMCBots&7]&f: &3You successfully created a bot").color(),
                TextMessage.build("&7UUID: &b" + botPlayer.getBotUUID().toString(),
                        "&7Name: &b" + botPlayer.getBotName(),
                        "&7Location:",
                        "&7  World: &b" + location.getWorld().getName(),
                        "&7  X: &b" + location.getX(),
                        "&7  Y: &b" + location.getY(),
                        "&7  Z: &b" + location.getZ()).color()
                )
        );
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final int aLength = args.length;

        if (sender.hasPermission(Permissions.GENERAL.get())) {
            if (aLength == 0 || (aLength == 1 && args[0].equalsIgnoreCase("help"))) {
                Messages.HELP.send(sender);
            } else {
                final String botName = args[1];
                if (aLength == 2 && isValidUsername(args[1])) {
                    if (sender instanceof Player) {
                        final Player player = (Player) sender;
                        try {
                            final BotPlayer createdBot = new BotPlayer(botName, player.getLocation());
                            createdBot.create();
                            createdBot.setskin(SkinTexture.THEVIPERSHOW);
                            Active.getInstance().botPlayersSet.add(createdBot);
                            botCreatedNotification(player, player.getLocation(), createdBot);
                        } catch (BotException e) {
                            Messages.BOT_CREATION_ERROR.send(sender, botName);
                        }
                    }
                } else {
                    Messages.UNKNOWN_COMMAND.send(sender);
                }
            }
        } else {
            Messages.NO_PERMISSION.send(sender, Permissions.GENERAL.get());
        }
        return true;
    }
}
