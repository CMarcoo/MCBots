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
import java.util.logging.Logger


class BotPlayer @Throws(Exception::class) constructor(val botName: String, val location: Location) {
    private lateinit var entityPlayer: EntityPlayer
    val gameProfile: GameProfile = GameProfile(UUID.randomUUID(), botName)

    fun create() {
        val mcServer = (Bukkit.getServer() as CraftServer).server
        val worldServer = (location.world as CraftWorld).handle
        this.entityPlayer = EntityPlayer(mcServer, worldServer, gameProfile, PlayerInteractManager(worldServer))
        this.entityPlayer.setLocation(location.x, location.y, location.z, location.yaw, location.pitch)
        showEach()
    }

    fun setskin(minecraftSkin: SkinTexture){
        this.gameProfile.properties.put("textures", Property("textures", minecraftSkin.value, minecraftSkin.signature))
    }

    fun showSingle(player: Player) {
        val playerConnection: PlayerConnection = (player as CraftPlayer).handle.playerConnection
        playerConnection.sendPacket(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer))
        playerConnection.sendPacket(PacketPlayOutNamedEntitySpawn(entityPlayer))
    }

    private fun showEach() {
        Bukkit.getOnlinePlayers().forEach() { player ->
            val playerConnection: PlayerConnection = (player as CraftPlayer).handle.playerConnection
            playerConnection.sendPacket(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer))
            playerConnection.sendPacket(PacketPlayOutNamedEntitySpawn(entityPlayer))
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is BotPlayer && botName == other.botName
    }
}