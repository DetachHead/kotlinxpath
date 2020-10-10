/**
 * equivalent to the `//` shortcut.
 *
 * short for `/descendant-or-self::node()/`
 */
public val LocationPathBuilder.any: LocationPath get() = xpath { Axis.`descendant-or-self`(node()) }

/**
 * equivalent to the `@` shortcut.
 *
 * short for `attribute::abc`
 *
 * eg.
 * ```kotlin
 * attr("foo").toString() == "@foo"
 * ```
 */
public fun LocationPathBuilder.attr(name: String): LocationPath = xpath { Axis.attribute(name) }

/**
 * equivalent to the `..` shortcut.
 *
 * short for `parent::node()`
 */
public val LocationPathBuilder.parent: LocationPath get() = xpath { Axis.parent(node()) }

/**
 * equivalent to the `.` shortcut.
 *
 * short for `self::node()`
 */
public val LocationPathBuilder.self: LocationPath get() = xpath { Axis.self(node()) }

public fun LocationPathBuilder.textIs(text: String): Expression =
    expression { translate(text, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz") }