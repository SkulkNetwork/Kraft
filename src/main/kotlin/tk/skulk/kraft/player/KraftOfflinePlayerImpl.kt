package tk.skulk.kraft.player

import org.bukkit.OfflinePlayer as BukkitOfflinePlayer

open class KraftOfflinePlayerImpl internal constructor(override val bukkit: BukkitOfflinePlayer) : KraftOfflinePlayer
