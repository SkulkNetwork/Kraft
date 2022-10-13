package tk.skulk.kraft.key

import org.bukkit.NamespacedKey as BukkitKey

class KraftKeyImpl internal constructor(override val bukkit: BukkitKey) : KraftKey
