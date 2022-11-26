package tk.skulk.kraft.world

import org.bukkit.World as BukkitWorld

public fun BukkitWorld.toKraft(): KraftWorld = KraftWorldImpl(this)

public interface KraftWorld {
    public val bukkit: BukkitWorld
}
