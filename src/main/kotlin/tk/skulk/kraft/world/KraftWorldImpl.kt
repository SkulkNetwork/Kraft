package tk.skulk.kraft.world

import org.bukkit.World as BukkitWorld

class KraftWorldImpl internal constructor(override val bukkit: BukkitWorld) : KraftWorld
