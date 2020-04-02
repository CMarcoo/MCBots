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

import com.mojang.authlib.GameProfile;
import me.thevipershow.mcbots.bots.Active;
import me.thevipershow.mcbots.commands.enums.Messages;
import me.thevipershow.mcbots.commands.enums.Permissions;
import me.thevipershow.mcbots.commands.enums.SkinTexture;
import me.thevipershow.mcbots.player.BotPlayer;
import me.thevipershow.spigotchatlib.chat.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BotsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final int aLength = args.length;

        if (sender.hasPermission(Permissions.GENERAL.get())) {
            if (aLength == 0 || (aLength == 1 && args[0].equalsIgnoreCase("help"))) {
                Messages.HELP.send(sender);
            } else {
                final String bot_name = args[1];
                if (aLength == 2 && bot_name.length() <= 16) {
                    if (sender instanceof Player) {
                        final Player player = (Player) sender;

                        BotPlayer createdBot = null;
                        try {
                            createdBot = new BotPlayer(bot_name, player.getLocation());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        assert createdBot != null;

                        for (BotPlayer gameProfile : Active.getInstance().botPlayersSet) {
                            if (bot_name.equals(gameProfile.getGameProfile().getName())) {
                                Messages.BOT_CREATION_ERROR.send(sender, bot_name);
                                return false;
                            }
                        }

                        createdBot.create();
                        createdBot.setskin(SkinTexture.THEVIPERSHOW);
                        Active.getInstance().botPlayersSet.add(createdBot);


                    }
                }
            }
        } else {
            Messages.NO_PERMISSION.send(sender, Permissions.GENERAL.get());
        }
        return true;
    }
}
