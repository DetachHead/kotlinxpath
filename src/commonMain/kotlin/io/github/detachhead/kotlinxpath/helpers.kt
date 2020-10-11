package io.github.detachhead.kotlinxpath

import io.github.detachhead.kotlinxpath.components.*
import io.github.detachhead.kotlinxpath.components.LocationPathBuilder
import io.github.detachhead.kotlinxpath.components.expression
import io.github.detachhead.kotlinxpath.functions.`normalize-space`
import io.github.detachhead.kotlinxpath.functions.concat
import io.github.detachhead.kotlinxpath.functions.contains
import io.github.detachhead.kotlinxpath.functions.xpath1_shims.`lower-case`

/*
useful helper functions unique to this library to help make xpath less of a pain to work with
 */

/** converts all text to lowercase, automatically handles quote escaping and trims any whitespace */
public fun LocationPathBuilder.textIs(text: String): Expression =
    expression { `lower-case`(`normalize-space`(self)) equal text }

/** checks whether the element has the given [className] */
public fun LocationPathBuilder.hasClass(className: String): Expression {
    val space = Expression.fromString(" ")
    return expression { contains(concat(space, attr("class"), space), " $className ") }
}
