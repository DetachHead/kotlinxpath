/**
 * an xpath expression
 */
public open class Xpath(
    public val axis: Axis,
    public val nodetest: nodetest,
    public var predicates: List<predicate> = listOf(),
    public var child: Xpath? = null
) {
    //TODO: handling for scenarios where you want the xpath to start with a single /
    override fun toString(): String = "$axis::$nodetest${predicates.joinToString("")}" + (child?.let { "/$it" } ?: "")
}

/**
 * creates an [Xpath] using the [xpathbuilder] with the given [block]
 */
public fun xpath(block: xpathbuilder.() -> Unit): Xpath = xpathbuilder().apply(block).build()

/**
 * typesafe builder for [Xpath]
 */
public class xpathbuilder {
    private lateinit var xpath: Xpath
    internal fun build(): Xpath = xpath

    /**
     * adds the given [predicate]s to the current [Xpath]
     */
    public operator fun Xpath.get(vararg predicates: String): Xpath = also {
        this.predicates += predicates.map { predicate(it) }
        xpath = it
    }

    /**
     * Adds an [Xpath] to the current [Xpath]
     */
    public operator fun Xpath.div(other: Xpath): Xpath = also {
        child = other
        xpath = it
    }

    /**
     * appends the given [nodetest] to the current [Xpath]
     */
    public operator fun Xpath.div(other: nodetest): Xpath = this / Xpath(Axis.child, other)

    /**
     * creates an [Xpath] with the current [Axis] and the given [nodetest]
     */
    public operator fun Axis.invoke(node: nodetest): Xpath = Xpath(this, node).also { xpath = it }
}