package tk.skulk.kraft.player

import org.bukkit.entity.Player as BukkitPlayer

fun BukkitPlayer.toKraft() = KraftPlayer(this)

class KraftPlayer internal constructor(val bukkitPlayer: BukkitPlayer) : KraftOfflinePlayer(bukkitPlayer)
