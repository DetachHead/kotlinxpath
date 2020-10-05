/**
 * an xpath [location path](https://en.wikipedia.org/wiki/XPath#Syntax_and_semantics_(XPath_1.0))
 */
public open class LocationPath(
    public val axis: Axis,
    public val nodetest: NodeTest,
    public val predicates: Set<Expression> = setOf(),
    public val child: LocationPath? = null
) : Expression(
    "$axis::$nodetest"
            + (predicates.takeIf { it.isNotEmpty() }?.joinToString("][", "[", "]") ?: "")
            + (child?.let { "/$it" } ?: "")
) {
    //TODO: handling for scenarios where you want the xpath to start with a single /
}

public val foo: String = ""

/**
 * creates a [LocationPath] using the [LocationPathBuilder] with the given [block]
 */
public fun xpath(block: LocationPathBuilder.() -> Unit): LocationPath = LocationPathBuilder().apply(block).build()

/**
 * creates a [LocationPath] representing the given attribute name
 * eg.
 * ```kotlin
 * attr("foo").toString() == "@foo"
 * ```
 */
public fun attr(name: String): LocationPath = xpath { attribute(name) }

/**
 * typesafe builder for [LocationPath]
 */
public class LocationPathBuilder : Buildable<LocationPath> {
    private lateinit var axis: Axis
    private lateinit var nodetest: NodeTest
    private var predicates: Set<Expression> = setOf()
    private var child: LocationPath? = null
    override fun build(): LocationPath = LocationPath(axis, nodetest, predicates, child)

    /**
     * adds the given [Expression]s to the current [LocationPath]
     */
    public operator fun LocationPath.get(predicates: PredicateBuilder.() -> Unit): LocationPath = also {
        this@LocationPathBuilder.predicates += PredicateBuilder().apply(predicates).build()
    }

    /**
     * Adds a [LocationPath] to the current [LocationPath]
     */
    public operator fun LocationPath.div(other: LocationPath): LocationPath = also {
        this@LocationPathBuilder.child = other
    }

    /**
     * appends the given [NodeTest] to the current [LocationPath]
     */
    public operator fun LocationPath.div(other: NodeTest): LocationPath = this / LocationPath(Axis.child, other)

    /**
     * creates a [LocationPath] with the current [Axis] and the given [NodeTest]
     */
    public operator fun Axis.invoke(node: NodeTest): LocationPath = LocationPath(this, node).also {
        axis = this
        nodetest = it.nodetest
    }

    /**
     * creates a [LocationPath] with the current [Axis] and the given [String] as a new [NodeTest]
     */
    public operator fun Axis.invoke(node: String): LocationPath = this(NodeTest(node))
}