package tk.skulk.kraft.world

import org.bukkit.WorldCreator as BukkitWorldCreator

public fun BukkitWorldCreator.toKraft(): KraftWorldCreator = KraftWorldCreatorImpl(this)

public interface KraftWorldCreator {
    public val bukkit: BukkitWorldCreator
}
