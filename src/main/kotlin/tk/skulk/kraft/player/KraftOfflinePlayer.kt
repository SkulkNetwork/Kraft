package tk.skulk.kraft.player

import org.bukkit.OfflinePlayer as BukkitOfflinePlayer

public fun BukkitOfflinePlayer.toKraft(): KraftOfflinePlayer = KraftOfflinePlayerImpl(this)

public interface KraftOfflinePlayer {
    public val bukkit: BukkitOfflinePlayer
}
