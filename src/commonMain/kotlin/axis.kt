import kotlin.js.JsName

/**
 * [xpath axes](https://en.wikipedia.org/wiki/XPath#Axis_specifiers)
 */
public enum class Axis {
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
public val xpathbuilder.any: Axis get() = Axis.`descendant-or-self`
public val xpathbuilder.attribute: Axis get() = Axis.attribute
public val xpathbuilder.parent: Axis get() = Axis.parent
public val xpathbuilder.self: Axis get() = Axis.self