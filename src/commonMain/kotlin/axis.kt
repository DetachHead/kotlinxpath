/**
 * [xpath axes](https://en.wikipedia.org/wiki/XPath#Axis_specifiers)
 */
enum class Axis {
    ancestor, `ancestor-or-self`, attribute, child, descendant, `descendant-or-self`,
    following, `following-sibling`, namespace, parent, preceding, `preceding-sibling`, self
}

//constants for axis shortcuts:
val xpathbuilder.any get() = Axis.`descendant-or-self`
val xpathbuilder.attribute get() = Axis.attribute
val xpathbuilder.parent get() = Axis.parent
val xpathbuilder.self get() = Axis.self