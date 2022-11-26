package tk.skulk.kraft.player

import org.bukkit.entity.Player as BukkitPlayer

public fun BukkitPlayer.toKraft(): KraftPlayer = KraftPlayerImpl(this)

public interface KraftPlayer : KraftOfflinePlayer {
    override val bukkit: BukkitPlayer
}
