package tk.skulk.kraft

import org.bukkit.entity.SpawnCategory as BukkitSpawnCategory

enum class KraftSpawnCategory(val bukkit: BukkitSpawnCategory) {
    /** Entities related to Monsters. E.g.: Witch, Zombie, Creeper, etc. */
    MONSTER(BukkitSpawnCategory.MONSTER),

    /** Entities related to Animals, E.g.: Strider, Cow, Turtle, etc. */
    ANIMAL(BukkitSpawnCategory.ANIMAL),

    /** Entities related to Water Animals, E.g.: Squid, Dolphin, etc. */
    WATER_ANIMAL(BukkitSpawnCategory.WATER_ANIMAL),

    /** Entities related to Water Ambient, E.g.: Cod, PufferFish, Tropical Fish, Salmon, etc. */
    WATER_AMBIENT(BukkitSpawnCategory.WATER_AMBIENT),

    /** Entities related to Water Underground, E.g.: Glow Squid. */
    WATER_UNDERGROUND(BukkitSpawnCategory.WATER_UNDERGROUND_CREATURE),

    /** Entities related to Ambient, E.g.: Bat. */
    AMBIENT(BukkitSpawnCategory.AMBIENT),

    /** I have no fucking idea what this is. */
    AXOLOTL(BukkitSpawnCategory.AXOLOTL),

    /** Entities not related to a mob, E.g.: Player, ArmorStand, Boat, etc. */
    MISC(BukkitSpawnCategory.MISC)
}
