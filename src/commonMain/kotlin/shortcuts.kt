import components.*

/*
functions for the shortcut syntax's made available in Xpath
 */

/**
 * equivalent to the `//` shortcut.
 *
 * short for `/descendant-or-self::components.getNode()/`
 */
public val LocationPathBuilder.any: LocationPath get() = xpath { components.Axis.`descendant-or-self`(node()) }

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
public fun LocationPathBuilder.attr(name: String): LocationPath = xpath { components.Axis.attribute(name) }

/**
 * equivalent to the `..` shortcut.
 *
 * short for `parent::components.getNode()`
 */
public val LocationPathBuilder.parent: LocationPath get() = xpath { components.Axis.parent(node()) }

/**
 * equivalent to the `.` shortcut.
 *
 * short for `self::components.getNode()`
 */
public val LocationPathBuilder.self: LocationPath get() = xpath { components.Axis.self(node()) }