package tk.skulk.kraft.player

import org.bukkit.entity.Player as BukkitPlayer

class KraftPlayerImpl internal constructor(override val bukkit: BukkitPlayer) : KraftOfflinePlayerImpl(bukkit),
    KraftPlayer
