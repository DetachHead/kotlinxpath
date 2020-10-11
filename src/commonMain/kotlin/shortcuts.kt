import components.*

/*
functions for the various shortcuts that are available in Xpath
 */

/**
 * equivalent to the `//` shortcut.
 *
 * short for `/descendant-or-self::components.getNode()/`
 */
public val LocationPathBuilder.any: LocationPath get() = Axis.`descendant-or-self`(node())

/**
 * equivalent to the //&#42 shortcut.
 *
 * short for /descendant-or-self::components.getNode()/&#42
 */
public val LocationPathBuilder.anyNode: LocationPath get() = any / "*"

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

/**
 * equivalent to the `..` shortcut.
 *
 * short for `parent::components.getNode()`
 */
public val LocationPathBuilder.parent: LocationPath get() = Axis.parent(node())

/**
 * equivalent to the `.` shortcut.
 *
 * short for `self::components.getNode()`
 */
public val LocationPathBuilder.self: LocationPath get() = Axis.self(node())