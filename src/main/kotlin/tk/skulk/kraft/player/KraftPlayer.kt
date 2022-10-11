package tk.skulk.kraft.player

import org.bukkit.entity.Player as BukkitPlayer

class KraftPlayer internal constructor(bukkit: BukkitPlayer) : KraftOfflinePlayer(bukkit)
