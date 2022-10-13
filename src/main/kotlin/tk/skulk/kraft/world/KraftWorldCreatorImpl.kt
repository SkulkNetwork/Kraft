package tk.skulk.kraft.world

import org.bukkit.WorldCreator as BukkitWorldCreator

class KraftWorldCreatorImpl internal constructor(override val bukkit: BukkitWorldCreator) : KraftWorldCreator
