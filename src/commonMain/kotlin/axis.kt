import kotlin.js.JsName

/**
 * [xpath axes](https://en.wikipedia.org/wiki/XPath#Axis_specifiers)
 */
enum class Axis {
    ancestor, attribute, child, descendant, following, namespace, parent, preceding, self,

    @JsName("ancestor_or_self")
    `ancestor-or-self`,

    @JsName("descendant_or_self")
    `descendant-or-self`,

    @JsName("preceding_sibling")
    `preceding-sibling`,

    @JsName("following_sibling")
    `following-sibling`
}

//constants for axis shortcuts:
val xpathbuilder.any get() = Axis.`descendant-or-self`
val xpathbuilder.attribute get() = Axis.attribute
val xpathbuilder.parent get() = Axis.parent
val xpathbuilder.self get() = Axis.self