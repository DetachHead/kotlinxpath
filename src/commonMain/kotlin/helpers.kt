import components.Expression
import components.LocationPathBuilder
import components.expression
import functions.`normalize-space`
import functions.concat
import functions.contains
import functions.xpath1_shims.`lower-case`

/*
useful helper functions unique to this library to help make xpath less of a pain to work with
 */

/** compares text case-insensitively and trims whitespace */
public fun LocationPathBuilder.textIs(text: String): Expression =
    expression { `lower-case`(`normalize-space`(self)) equal text }

/** checks whether the element has the given [className] */
public fun LocationPathBuilder.hasClass(className: String): Expression {
    val space = Expression.fromString(" ")
    return expression { contains(concat(space, attr("class"), space), " $className ") }
}
