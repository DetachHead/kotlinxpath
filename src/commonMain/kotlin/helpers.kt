import components.Expression
import components.LocationPathBuilder
import components.expression
import functions.functions.`normalize-space`
import functions.functions.concat
import functions.functions.contains
import functions.xpath1_shims.`lower-case`

/*
useful helper functions unique to this library to help make xpath less of a pain to worm with
 */

/** compares text case-insensitively and trims whitespace */
public fun LocationPathBuilder.textIs(text: String): Expression =
    expression { `lower-case`(`normalize-space`(self(node()))) equal text }

/** checks whether the element has the given [className] */
public fun LocationPathBuilder.classContains(className: String): Expression =
    expression { contains(concat(" ", attr("class").toString(), " "), " $className ") }