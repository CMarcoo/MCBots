package me.thevipershow.mcbots.player

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import me.thevipershow.mcbots.bots.Active
import me.thevipershow.mcbots.commands.enums.SkinTexture
import net.minecraft.server.v1_15_R1.EntityPlayer
import net.minecraft.server.v1_15_R1.PacketPlayOutNamedEntitySpawn
import net.minecraft.server.v1_15_R1.PacketPlayOutPlayerInfo
import net.minecraft.server.v1_15_R1.PlayerConnection
import net.minecraft.server.v1_15_R1.PlayerInteractManager
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.CraftServer
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.UUID;


class BotPlayer {
    val entityPlayer: EntityPlayer
    val gameProfile: GameProfile
    val botName: String
    val location: Location

    @Throws(Exception::class)
    constructor(botName: String, location: Location) {
        if (!(this in Active.getInstance().botPlayersSet)) {
            val mcServer = (Bukkit.getServer() as CraftServer).server
            val worldServer = (location.world as CraftWorld).handle
            this.botName = botName
            this.location = location
            gameProfile = GameProfile(UUID.randomUUID(), botName)
            this.entityPlayer = EntityPlayer(mcServer, worldServer, gameProfile, PlayerInteractManager(worldServer))
            this.entityPlayer.setLocation(location.x, location.y, location.z, location.yaw, location.pitch)
            showEach()
            Active.getInstance().addBot(this)
        } else {
            throw Exception("Bot with name '" + botName + "' already exists on the server")
        }
    }

    constructor(botName: String, location: Location, minecraftSkin: SkinTexture) : this(botName, location) {
        this.gameProfile.properties.put("textures", Property("textures", minecraftSkin.value, minecraftSkin.signature))
    }

    fun showSingle(player: Player) {
        val playerConnection: PlayerConnection = (player as CraftPlayer).handle.playerConnection
        playerConnection.sendPacket(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer))
        playerConnection.sendPacket(PacketPlayOutNamedEntitySpawn(entityPlayer))
    }

    fun showEach() {
        Bukkit.getOnlinePlayers().forEach() { player ->
            val playerConnection: PlayerConnection = (player as CraftPlayer).handle.playerConnection
            playerConnection.sendPacket(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer))
            playerConnection.sendPacket(PacketPlayOutNamedEntitySpawn(entityPlayer))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (!(other is BotPlayer)) {
            throw IllegalArgumentException("Cannot compare BotPlayer with other Objects")
        }
        return botName.equals(other.botName)
    }
}