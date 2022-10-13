package tk.skulk.kraft.player

import org.bukkit.entity.Player as BukkitPlayer

interface KraftPlayer : KraftOfflinePlayer {
    override val bukkit: BukkitPlayer
}
