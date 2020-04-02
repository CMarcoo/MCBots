/*
 * 02/04/20, 16:47 - MCBots
 * ------------------------------------------------------------------------------------
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package me.thevipershow.mcbots.bots;

import java.util.HashSet;
import me.thevipershow.mcbots.player.BotPlayer;
import org.jetbrains.annotations.NotNull;

public class Active {

    private Active() {
    }

    private static final Active instance = new Active();

    public static Active getInstance() {
        return instance;
    }

    public HashSet<BotPlayer> botPlayersSet = new HashSet<>();

    public boolean addBot(@NotNull BotPlayer botPlayer) {
        return botPlayersSet.add(botPlayer);
    }
}
