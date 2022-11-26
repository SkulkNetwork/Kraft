package tk.skulk.kraft.key

import org.bukkit.NamespacedKey as BukkitKey

public fun String.toKraftKey(): BukkitKey {
    val split = this.split(":")
    return BukkitKey(split[0], split[1])
}

public fun BukkitKey.toKraft(): KraftKey = KraftKeyImpl(this)

public interface KraftKey {
    public val bukkit: BukkitKey
}
