package me.thevipershow.mcbots.player

import com.mojang.authlib.GameProfile
import net.minecraft.server.v1_15_R1.*
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_15_R1.CraftServer
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer
import org.bukkit.entity.Player


class Player() {

    fun destroy_block(): Boolean {
        TODO("Not yet implemented")
    }


    fun spawn(player: Player, name: String?): Boolean {
        val server: MinecraftServer = (Bukkit.getServer() as CraftServer).server
        val world = (Bukkit.getWorlds()[0] as CraftWorld).handle
        val target: Player? = Bukkit.getServer().getPlayer(name!!)
        val npc: EntityPlayer
        npc = if (target != null) {
            EntityPlayer(server, world, GameProfile(target.uniqueId, target.name), PlayerInteractManager(world))
        } else {
            val op = Bukkit.getServer().getOfflinePlayer(name)
            EntityPlayer(server, world, GameProfile(op.uniqueId, name), PlayerInteractManager(world))
        }
        val loc: Location = player.location
        npc.setLocation(loc.x, loc.y, loc.z, loc.yaw, loc.pitch)
        for (all in Bukkit.getOnlinePlayers()) {
            val connection = (all as CraftPlayer).handle.playerConnection
            connection.sendPacket(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc))
            connection.sendPacket(PacketPlayOutNamedEntitySpawn(npc))
        }
        return true
    }

    fun attack(): Boolean {
        TODO("Not yet implemented")
    }

    fun heal(value: Int): Boolean {
        TODO("Not yet implemented")
    }

    fun crawl(): Boolean {
        TODO("Not yet implemented")
    }

    fun place_block(): Boolean {
        TODO("Not yet implemented")
    }

    fun jump(): Boolean {
        TODO("Not yet implemented")
    }

    fun rotate(): Boolean {
        TODO("Not yet implemented")
    }

    fun run(): Boolean {
        TODO("Not yet implemented")
    }

    fun spawn(): Boolean {
        TODO("Not yet implemented")
    }

    fun uncrawl(): Boolean {
        TODO("Not yet implemented")
    }

    fun walk(): Boolean {
        TODO("Not yet implemented")
    }

    fun write_to_chat(message: String): Boolean {
        TODO("Not yet implemented")
    }

}