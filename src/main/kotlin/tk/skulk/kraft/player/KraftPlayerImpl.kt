package tk.skulk.kraft.player

import org.bukkit.entity.Player as BukkitPlayer

internal class KraftPlayerImpl(override val bukkit: BukkitPlayer) :
    KraftOfflinePlayerImpl(bukkit), KraftPlayer
