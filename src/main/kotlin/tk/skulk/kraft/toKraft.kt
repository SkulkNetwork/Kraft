package tk.skulk.kraft

import tk.skulk.kraft.player.KraftOfflinePlayer
import tk.skulk.kraft.player.KraftPlayer
import org.bukkit.OfflinePlayer as BukkitOfflinePlayer
import org.bukkit.entity.Player as BukkitPlayer
import org.bukkit.entity.SpawnCategory as BukkitSpawnCategory

fun BukkitPlayer.toKraft() = KraftPlayer(this)

fun BukkitOfflinePlayer.toKraft() = KraftOfflinePlayer(this)

fun BukkitSpawnCategory.toKraft() = KraftSpawnCategory.valueOf(this.name)
