package tk.skulk.kraft.player

import org.bukkit.OfflinePlayer as BukkitOfflinePlayer

fun BukkitOfflinePlayer.toKraft() = KraftOfflinePlayer(this)

open class KraftOfflinePlayer internal constructor(val bukkitOfflinePlayer: BukkitOfflinePlayer)
