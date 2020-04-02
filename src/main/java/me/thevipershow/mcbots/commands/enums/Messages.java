/*
 * 02/04/20, 13:23 - MCBots
 * ------------------------------------------------------------------------------------
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package me.thevipershow.mcbots.commands.enums;

import java.util.Arrays;
import me.thevipershow.spigotchatlib.chat.TextMessage;
import me.thevipershow.spigotchatlib.chat.builders.HoverMessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Messages {

    HELP("&3&nHover for commands list",
            "&8----------------",
            "&8| Display this help page",
            "&8| &f/&bbots help",
            "&8| Spawn a bot with a name on your location",
            "&8| &f/&bbots spawn &3<&bname&3>",
            "&8----------------"),
    NO_PERMISSION("&4You are missing a permission",
            "&4Missing permission: &7%s");

    private final String message, hoverMessage[];

    Messages(final String message, final String... hoverMessage) {
        this.message = message;
        this.hoverMessage = hoverMessage;
    }

    public void send(final CommandSender commandSender) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.spigot().sendMessage(HoverMessageBuilder.buildHover(
                    TextMessage.build("&7[&bMCBots&7]&f: " + this.message).color(),
                    TextMessage.build(this.hoverMessage).color()
            ));
        } else {
            commandSender.sendMessage(TextMessage.build(this.hoverMessage).color().getText());
        }
    }

    public void send(final CommandSender commandSender, final String format) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            final String[] copyArr = Arrays.stream(this.hoverMessage).map(s -> String.format(s,format)).toArray(String[]::new);
            player.spigot().sendMessage(HoverMessageBuilder.buildHover(
                    TextMessage.build("&7[&bMCBots&7]&f: " + this.message).color(),
                    TextMessage.build(copyArr).color()
            ));
        } else {
            commandSender.sendMessage(TextMessage.build(this.hoverMessage).color().getText());
        }
    }
}