/**
 * an xpath expression
 */
open class Xpath(
    val axis: Axis,
    val nodetest: nodetest,
    var predicates: List<predicate> = listOf(),
    var child: Xpath? = null
) {
    //TODO: handling for scenarios where you want the xpath to start with a single /
    override fun toString() = "$axis::$nodetest${predicates.joinToString("")}" + (child?.let { "/$it" } ?: "")
}

/**
 * creates an [Xpath] using the [xpathbuilder] with the given [block]
 */
fun xpath(block: xpathbuilder.() -> Unit): Xpath = xpathbuilder().apply(block).build()

/**
 * typesafe builder for [Xpath]
 */
class xpathbuilder {
    lateinit var xpath: Xpath
    fun build() = xpath

    /**
     * adds the given [predicate]s to the current [Xpath]
     */
    operator fun Xpath.get(vararg predicates: String) = also {
        this.predicates += predicates.map { predicate(it) }
        xpath = it
    }

    /**
     * Adds an [Xpath] to the current [Xpath]
     */
    operator fun Xpath.div(other: Xpath) = also {
        child = other
        xpath = it
    }

    /**
     * appends the given [nodetest] to the current [Xpath]
     */
    operator fun Xpath.div(other: nodetest) = this / Xpath(Axis.child, other)

    /**
     * creates an [Xpath] with the current [Axis] and the given [nodetest]
     */
    operator fun Axis.invoke(node: nodetest) = Xpath(this, node).also { xpath = it }
}