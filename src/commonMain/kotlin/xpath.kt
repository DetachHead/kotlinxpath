/**
 * an xpath expression
 */
public open class Xpath(
    public val axis: Axis,
    public val nodetest: NodeTest,
    public var predicates: List<Predicate> = listOf(),
    public var child: Xpath? = null
) {
    //TODO: handling for scenarios where you want the xpath to start with a single /
    override fun toString(): String = "$axis::$nodetest${predicates.joinToString("")}" + (child?.let { "/$it" } ?: "")
}

/**
 * creates an [Xpath] using the [XpathBuilder] with the given [block]
 */
public fun xpath(block: XpathBuilder.() -> Unit): Xpath = XpathBuilder().apply(block).build()

/**
 * typesafe builder for [Xpath]
 */
public class XpathBuilder: Buildable<Xpath> {
    private lateinit var xpath: Xpath
    override fun build(): Xpath = xpath

    /**
     * adds the given [Predicate]s to the current [Xpath]
     */
    public operator fun Xpath.get(predicates: PredicateBuilder.() -> Unit): Xpath = also {
        this.predicates += PredicateBuilder().apply(predicates).build()
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
     * appends the given [NodeTest] to the current [Xpath]
     */
    public operator fun Xpath.div(other: NodeTest): Xpath = this / Xpath(Axis.child, other)

    /**
     * creates an [Xpath] with the current [Axis] and the given [NodeTest]
     */
    public operator fun Axis.invoke(node: NodeTest): Xpath = Xpath(this, node).also { xpath = it }
}