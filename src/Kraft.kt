package tk.skulk.kraft

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import tk.skulk.kraft.player.KraftOfflinePlayer
import tk.skulk.kraft.player.KraftPlayer
import tk.skulk.kraft.player.toKraftOfflinePlayer
import tk.skulk.kraft.player.toKraftPlayer
import java.io.File
import java.util.UUID

import org.bukkit.Server as BukkitServer

@Suppress("unused")
object Kraft {
    // Skipping Bukkit.setServer() and not exposing Bukkit.getServer() because it's not needed.
    private val server get() = Bukkit.getServer()

    /**
     * The de facto plugins directory, generally used for storing plugin jars to be loaded,
     * as well as their [data folders][dataFolder].
     *
     * Plugins should use [KraftPlugin.dataFolder] rather than traversing this directory manually
     * when determining the location in which to store their data and configuration files.
     */
    val pluginsFolder: File = server.pluginsFolder

    /** The name of this server. */
    val name: String = server.name

    /** The version of this server. */
    val version: String = server.version

    /** The version of Bukkit this server is running. */
    val bukkitVersion: String = server.bukkitVersion

    /** The version of Minecraft that this server is running on. */
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
     * You can think of this as a snapshot of [org.bukkit.Bukkit.getOnlinePlayers].
     */
    val onlinePlayers: List<KraftPlayer> get() = server.onlinePlayers.map { it.toKraftPlayer() }

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

    /** Whether this server allows the End or not. */
    val allowsEnd: Boolean = server.allowEnd

    /** Whether this server allows the Nether or not. */
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
        get() = server.whitelistedPlayers.map { it.toKraftOfflinePlayer() }

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
        message: String,
        permission: String = BukkitServer.BROADCAST_CHANNEL_USERS
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
        message: Component,
        permission: String = BukkitServer.BROADCAST_CHANNEL_USERS
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
     * @param category The category of spawn.
     *
     * @return The default ticks per [KraftSpawnCategory] mobs spawn value.
     */
    fun getTicksPerSpawnCategory(category: KraftSpawnCategory): Int =
        server.getTicksPerSpawns(category.bukkitSpawnCategory)

    /**
     * Gets a [KraftPlayer] object by the given username. The given name doesn't have to be exact.
     *
     * E.g: "rGb" might return a player with the name "RGB_Cube".
     *
     * This method will not return objects for players that are
     * offline. You should use [getOfflinePlayerLoose] for this.
     *
     * @param name The (partial) name to match.
     *
     * @return An online player if one was found mathing the name, null otherwise.
     */
    fun getPlayerThatMatches(name: String): KraftPlayer? = server.getPlayer(name)?.toKraftPlayer()

    /**
     * Gets a [KraftPlayer] object by the given username. The given
     * name has to be exact. But it doesn't have to be case-sensitive.
     *
     * E.g: "rgb_Cube" will return a player with the name "RGB_Cube" if that player is online.
     *
     * This method will not return objects for players that are
     * offline. You should use [getOfflinePlayer] for this.
     *
     * @param name The name to look up.
     *
     * @return An online player if one was found mathing the name, null otherwise.
     */
    fun getPlayerNamed(name: String): KraftPlayer? = server.getPlayerExact(name)?.toKraftPlayer()

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
     * @param name The (partial) name to match.
     * @return List of all online players that match the name.
     */
    fun getPlayersThatMatch(name: String): List<KraftPlayer> =
        server.matchPlayer(name).map { it.toKraftPlayer() }

    fun getPlayerWithUUID(uuid: UUID): KraftPlayer? = server.getPlayer(uuid)?.toKraftPlayer()
}
