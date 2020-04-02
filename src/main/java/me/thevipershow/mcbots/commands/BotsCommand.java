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

import me.thevipershow.mcbots.commands.enums.Messages;
import me.thevipershow.mcbots.commands.enums.Permissions;
import me.thevipershow.mcbots.commands.enums.SkinTexture;
import me.thevipershow.mcbots.player.BotPlayer;
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
            } else if (aLength == 2 && args[1].length() <= 16) {
                if (sender instanceof Player) {
                    final Player player = (Player) sender;
                    new BotPlayer(args[1], player.getLocation(), SkinTexture.THEVIPERSHOW);
                }
            }
        } else {
            Messages.NO_PERMISSION.send(sender, Permissions.GENERAL.get());
        }
        return true;
    }
}
