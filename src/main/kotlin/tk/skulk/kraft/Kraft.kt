package tk.skulk.kraft

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import tk.skulk.kraft.enums.KraftSpawnCategory
import tk.skulk.kraft.key.KraftKey
import tk.skulk.kraft.player.KraftOfflinePlayer
import tk.skulk.kraft.player.KraftPlayer
import tk.skulk.kraft.world.KraftWorld
import tk.skulk.kraft.world.KraftWorldCreator
import java.io.File
import java.util.*
import org.bukkit.Server as BukkitServer

@Suppress("unused")
object Kraft {
    // Skipping Bukkit.setServer() and not exposing Bukkit.getServer() because it's not needed.
    private val server = Bukkit.getServer()

    /**
     * The de facto plugins directory, generally used for storing plugin jars to be loaded,
     * as well as their [data folders][dataFolder].
     *
     * Plugins should use [KraftPlugin.dataFolder] rather than traversing this directory manually
     * when determining the location in which to store their data and configuration files.
     */
    val pluginsFolder: File = server.pluginsFolder

    /** The name of the server. */
    val name: String = server.name

    /** The version of the server. */
    val version: String = server.version

    /** The version of Bukkit the server is running. */
    val bukkitVersion: String = server.bukkitVersion

    /** The version of Minecraft that the server is running on. */
    val minecraftVersion: String = server.minecraftVersion

    /** The message describing the version server is running on. */
    val versionMessage: String by lazy { Bukkit.getVersionMessage() }

    /**
     * An immutable list of all currently logged in players.
     *
     * You should **never** store this list for later use, as it may become invalid.
     * Accessing this in an asynchronous context isn't recommended since a player may
     * log out at any time making the player you are modifying offline.
     *
     * However, you should store this temporarily in a local variable
     * (E.g. in a [KraftCommand.execute]) to avoid multiple calls to this property.
     *
     * You can think of this as a snapshot of [Bukkit.getOnlinePlayers].
     */
    val onlinePlayers: List<KraftPlayer> get() = server.onlinePlayers.map { it.toKraft() }

    /** The maximum amount of players which can be on at the same time. */
    var playerCapacity: Int = server.maxPlayers
        set(value) {
            server.maxPlayers = value
            field = server.maxPlayers
        }

    /** The port that the server is running on. */
    val port: Int = server.port

    /** The view distance of the server. */
    val viewDistance: Int = server.viewDistance

    /** The simulation distance of the server. */
    val simulationDistance: Int = server.simulationDistance

    /** The IP address that the server is bound to, or and empty string if not specified. */
    val ip: String = server.ip

    /** The world type (Level-type setting) for the default world (e.g. DEFAULT, FLAT, DEFAULT_1_1). */
    val worldType: String = server.worldType

    /** Whether if the server generates structures. */
    val generateStructures: Boolean = server.generateStructures

    /** The max world size in blocks. */
    val maxWorldSize: Int = server.maxWorldSize

    /** Whether the server allows the End or not. */
    val allowsEnd: Boolean = server.allowEnd

    /** Whether the server allows the Nether or not. */
    val allowsNether: Boolean = server.allowNether

    /** The resource pack URI, or an empty string if not specified. */
    val resourcePackUri: String = server.resourcePack

    /** The SHA-1 digest of the server resource pack, or an empty string if not specified. */
    val resourcePackHash: String = server.resourcePackHash

    /**
     * The custom prompt message to be shown when the server resource pack is required,
     * or an empty string if not specified.
     */
    val resourcePackPrompt: String = server.resourcePackPrompt

    /** Whether the resource pack is enforced. */
    val resourcePackIsEnforced: Boolean = server.isResourcePackRequired

    // TODO: whitelist, whitelistEnforced, wtf?

    /**
     * The list of whitelisted players.
     *
     * You should **never** store this list for later use (Local variables are fine though),
     * as it may become outdated since players can be added/removed from the whitelist.
     */
    val whitelistedPlayers: List<KraftOfflinePlayer>
        get() = server.whitelistedPlayers.map { it.toKraft() }

    /** Reloads the whitelist from disk. */
    fun reloadWhitelist(): Unit = server.reloadWhitelist()

    /**
     * Broadcasts a message to all players that have the specified permission.
     * Or to all players if the permission is null.
     *
     * @param message The message.
     * @param permission The permission the players need to receive the message.
     *
     * @return The number of players the message was broadcasted to.
     */
    fun broadcastMessage(
        message: String, permission: String = BukkitServer.BROADCAST_CHANNEL_USERS
    ): Int = server.broadcast(Component.text(message), permission)

    /**
     * Broadcasts a message to all players that have the specified permission.
     * Or to all players if the permission is not specified.
     *
     * @param message The message.
     * @param permission The permission the players need to receive the message.
     *
     * @return The number of players the message was broadcasted to.
     */
    fun broadcastMessage(
        message: Component, permission: String = BukkitServer.BROADCAST_CHANNEL_USERS
    ): Int = server.broadcast(message, permission)

    // Skipping Server.getUpdateFolder() since it provides a String instead of a File.

    /** The update folder. This used to safely update plugins at the right moment on a plugin load. */
    val updateFolder: File = server.updateFolderFile

    /** The value of the connection throttle setting. */
    val connectionThrottle: Long = server.connectionThrottle

    /**
     * Returns the default ticks per [KraftSpawnCategory] spawns value.
     *
     * **Example Usage:**
     * * A value of 1 will mean the server will attempt to spawn [KraftSpawnCategory] mobs every tick.
     * * A value of 7 will mean the server wil attempt to spawn [KraftSpawnCategory] mobs every 7th tick.
     * * A value below 0 will be reset back to Minecraft's default (1).
     *
     * **Note:** If set to 0, [KraftSpawnCategory] mobs spawning will be disabled.
     *
     * **Note:** The [KraftSpawnCategory.MISC] are not considered.
     *
     * @param spawnCategory The category of spawn.
     *
     * @return The default ticks per [KraftSpawnCategory] mobs spawn value.
     */
    fun getTicksPerSpawnCategory(spawnCategory: KraftSpawnCategory): Int =
        server.getTicksPerSpawns(spawnCategory.bukkit)

    /**
     * Gets a [KraftPlayer] by the given username. The given name doesn't have to be exact.
     *
     * E.g: "rGb" might return a player with the name "RGB_Cube".
     *
     * This method will not return objects for players that are
     * offline. You should use [getOfflinePlayerLoose] for this.
     *
     * @param match The (partial) name to match.
     *
     * @return An online player if one was found mathing the name, null otherwise.
     */
    fun getPlayerThatMatches(match: String): KraftPlayer? = server.getPlayer(match)?.toKraft()

    /**
     * Gets a [KraftPlayer] by the given username. The given
     * name has to be exact. But it doesn't have to be case-sensitive.
     *
     * E.g: "rgb_Cube" will return a player with the name "RGB_Cube" if that player is online.
     *
     * This method will not return objects for players that are
     * offline. You should use [getOfflinePlayer] for this.
     *
     * @param playerName The name to look up.
     *
     * @return An online player if one was found mathing the name, null otherwise.
     */
    fun getPlayerNamed(playerName: String): KraftPlayer? =
        server.getPlayerExact(playerName)?.toKraft()

    /**
     * Attempts to match any online players with the given name,
     * and returns a list of all possibly matches.
     *
     * This list is not sorted in any particular order. If an exact match is
     * found, the returned list will only contain a single result.
     *
     * E.g: "foo" might return a list containing "xXx_Foo_xXx", "FOOBAR", "Fool123", etc.
     *
     * There is no offline counterpart to this method for obvious reasons.
     *
     * @param match The (partial) name to match.
     *
     * @return List of all online players that match the name.
     */
    fun getPlayersThatMatch(match: String): List<KraftPlayer> =
        server.matchPlayer(match).map { it.toKraft() }

    /**
     * Gets a [KraftPlayer] with the given [UUID].
     *
     * This method will not return objects for players that are
     * offline. You should use [getOfflinePlayerWithUUID] for this.
     *
     * @param uuid The UUID to look up.
     *
     * @return An online player if one was found mathing the [UUID][uuid], null otherwise.
     */
    fun getPlayerWithUUID(uuid: UUID): KraftPlayer? = server.getPlayer(uuid)?.toKraft()

    /**
     * Gets the [UUID] of the player currently known as the specified player name.
     * While in Offline Mode, will return an Offline [UUID] (Never returns null in Offline Mode).
     *
     * @param playerName The player name to look up the [UUID] for.
     *
     * @return A [UUID], or null if that player name is not registered with Minecraft and the server is in online mode.
     */
    fun getPlayerUUIDWithName(playerName: String): UUID? = server.getPlayerUniqueId(playerName)

    // TODO: Flatten out Bukkit.getPluginManager() and Bukkit.getScheduler() and Bukkit.getServicesManager().
    // TODO: Or maybe don't include them here and move them into KraftPlugin

    /**
     * An immutable list of all [KraftWorld]s on the server.
     *
     * You should **never** store this list for later use, as it may become invalid.
     * Accessing this in an asynchronous context isn't recommended since a player may
     * log out at any time making the player you are modifying offline.
     *
     * However, you should store this temporarily in a local variable
     * (E.g. in a [KraftCommand.execute]) to avoid multiple calls to this property.
     *
     * You can think of this as a snapshot of [Bukkit.getWorlds].
     */
    val worlds: List<KraftWorld> get() = server.worlds.map { it.toKraft() }

    /**
     * Creates or loads a [KraftWorld] with the given [KraftWorldCreator].
     *
     * If the world is already loaded, it will return the equivalent [KraftWorld].
     *
     * @param worldCreator The [KraftWorldCreator] to get the options from.
     *
     * @return The newly created or loaded world.
     */
    fun createWorld(worldCreator: KraftWorldCreator): KraftWorld? =
        server.createWorld(worldCreator.bukkit)?.toKraft()

    /**
     * Unloads the given [KraftWorld].
     *
     * @param world The world to unload.
     * @param save Whether to save the chunks before unloading.
     *
     * @return true if successful, false otherwise.
     */
    fun unloadWorld(world: KraftWorld, save: Boolean = true): Boolean =
        server.unloadWorld(world.bukkit, save)

    /**
     * Unloads a [KraftWorld] with the given name.
     *
     * @param worldName Name of the [KraftWorld] to unload.
     * @param save Whether to save the chunks before unloading.
     *
     * @return true if successful, false otherwise.
     */
    fun unloadWorld(worldName: String, save: Boolean = true): Boolean =
        server.unloadWorld(worldName, save)

    /**
     * Gets a [KraftWorld] by the given name.
     *
     * @param worldName The name of the [KraftWorld].
     *
     * @return The [KraftWorld] if one exists with the corresponding name, null otherwise.
     */
    fun getWorld(worldName: String): KraftWorld? = server.getWorld(worldName)?.toKraft()

    /**
     * Gets a [KraftWorld] by the given [UUID].
     *
     * @param uuid The [UUID] of the [KraftWorld].
     *
     * @return The [KraftWorld] if one exists with the corresponding [UUID], null otherwise.
     */
    fun getWorld(uuid: UUID): KraftWorld? = server.getWorld(uuid)?.toKraft()

    /**
     * Gets a [KraftWorld] by the given [KraftKey].
     *
     * @param key The [KraftKey] of the [KraftWorld] to retrieve.
     *
     * @return The [KraftWorld] if one exists with the corresponding [KraftKey], null otherwise.
     */
    fun getWorld(key: KraftKey): KraftWorld? = server.getWorld(key.bukkit)?.toKraft()
}
