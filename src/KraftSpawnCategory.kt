package tk.skulk.kraft

import org.bukkit.entity.SpawnCategory

enum class KraftSpawnCategory(val bukkitSpawnCategory: SpawnCategory) {
    /** Entities related to Monsters. E.g.: Witch, Zombie, Creeper, etc. */
    MONSTER(SpawnCategory.MONSTER),

    /** Entities related to Animals, E.g.: Strider, Cow, Turtle, etc. */
    ANIMAL(SpawnCategory.ANIMAL),

    /** Entities related to Water Animals, E.g.: Squid, Dolphin, etc. */
    WATER_ANIMAL(SpawnCategory.WATER_ANIMAL),

    /** Entities related to Water Ambient, E.g.: Cod, PufferFish, Tropical Fish, Salmon, etc. */
    WATER_AMBIENT(SpawnCategory.WATER_AMBIENT),

    /** Entities related to Water Underground, E.g.: Glow Squid. */
    WATER_UNDERGROUND(SpawnCategory.WATER_UNDERGROUND_CREATURE),

    /** Entities related to Ambient, E.g.: Bat. */
    AMBIENT(SpawnCategory.AMBIENT),

    /** I have no fucking idea what this is. */
    AXOLOTL(SpawnCategory.AXOLOTL),

    /** Entities not related to a mob, E.g.: Player, ArmorStand, Boat, etc. */
    MISC(SpawnCategory.MISC);
}
