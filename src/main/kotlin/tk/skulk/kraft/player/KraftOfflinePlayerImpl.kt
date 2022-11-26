package tk.skulk.kraft.player

import org.bukkit.OfflinePlayer as BukkitOfflinePlayer

internal open class KraftOfflinePlayerImpl(override val bukkit: BukkitOfflinePlayer) :
    KraftOfflinePlayer
