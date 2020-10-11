import components.*

@DslMarker
internal annotation class ShortcutMarker

/*
functions for the various shortcuts that are available in Xpath
 */

@ShortcutMarker
/**
 * equivalent to the `//` shortcut.
 *
 * short for `/descendant-or-self::components.getNode()/`
 */
public val LocationPathBuilder.any: LocationPath get() = Axis.`descendant-or-self`(node())

@ShortcutMarker
/**
 * equivalent to the //&#42 shortcut.
 *
 * short for /descendant-or-self::components.getNode()/&#42
 */
public val LocationPathBuilder.anyNode: LocationPath get() = any / "*"

@ShortcutMarker
/**
 * equivalent to the `@` shortcut.
 *
 * short for `attribute::abc`
 *
 * eg.
 * ```kotlin
 * attr("components.getFoo").toString() == "@components.getFoo"
 * ```
 */
public fun LocationPathBuilder.attr(name: String): LocationPath = Axis.attribute(name)

@ShortcutMarker
/**
 * equivalent to the `..` shortcut.
 *
 * short for `parent::components.getNode()`
 */
public val LocationPathBuilder.parent: LocationPath get() = Axis.parent(node())

@ShortcutMarker
/**
 * equivalent to the `.` shortcut.
 *
 * short for `self::components.getNode()`
 */
public val LocationPathBuilder.self: LocationPath get() = Axis.self(node())