package io.github.detachhead.kotlinxpath

import io.github.detachhead.kotlinxpath.components.Axis
import io.github.detachhead.kotlinxpath.components.LocationPath
import io.github.detachhead.kotlinxpath.components.LocationPathBuilder
import io.github.detachhead.kotlinxpath.components.node

@DslMarker
internal annotation class ShortcutMarker

/*
functions for the various shortcuts that are available in Xpath
 */

@ShortcutMarker
/**
 * equivalent to the `//` shortcut.
 *
 * short for `/descendant-or-self::node()/`
 */
public val LocationPathBuilder.any: LocationPath get() = +Axis.`descendant-or-self`(node())

@ShortcutMarker
/**
 * equivalent to the //&#42 shortcut.
 *
 * short for /descendant-or-self::node()/&#42
 */
public val LocationPathBuilder.anyNode: LocationPath get() = +any / "*"

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
 * short for `parent::node()`
 */
public val LocationPathBuilder.parent: LocationPath get() = Axis.parent(node())

@ShortcutMarker
/**
 * equivalent to the `.` shortcut.
 *
 * short for `self::node()`
 */
public val LocationPathBuilder.self: LocationPath get() = Axis.self(node())