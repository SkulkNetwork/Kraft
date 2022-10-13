package tk.skulk.kraft

import tk.skulk.kraft.enums.KraftSpawnCategory
import tk.skulk.kraft.player.KraftOfflinePlayer
import tk.skulk.kraft.player.KraftOfflinePlayerImpl
import tk.skulk.kraft.player.KraftPlayer
import tk.skulk.kraft.player.KraftPlayerImpl
import tk.skulk.kraft.world.KraftWorld
import tk.skulk.kraft.world.KraftWorldCreator
import tk.skulk.kraft.world.KraftWorldCreatorImpl
import tk.skulk.kraft.world.KraftWorldImpl
import org.bukkit.OfflinePlayer as BukkitOfflinePlayer
import org.bukkit.World as BukkitWorld
import org.bukkit.WorldCreator as BukkitWorldCreator
import org.bukkit.entity.Player as BukkitPlayer
import org.bukkit.entity.SpawnCategory as BukkitSpawnCategory

fun BukkitPlayer.toKraft() = KraftPlayerImpl(this) as KraftPlayer

fun BukkitOfflinePlayer.toKraft() = KraftOfflinePlayerImpl(this) as KraftOfflinePlayer

fun BukkitSpawnCategory.toKraft() = KraftSpawnCategory.valueOf(this.name)

fun BukkitWorld.toKraft() = KraftWorldImpl(this) as KraftWorld

fun BukkitWorldCreator.toKraft() = KraftWorldCreatorImpl(this) as KraftWorldCreator
