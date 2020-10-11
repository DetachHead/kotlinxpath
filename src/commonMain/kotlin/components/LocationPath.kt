package components

import NodeTest
import functions.position

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
public fun xpath(block: LocationPathBuilder.() -> Unit): LocationPath = LocationPathBuilder().apply(block).currentXpath

/**
 * typesafe builder for [LocationPath]
 */
public class LocationPathBuilder {
    internal lateinit var currentXpath: LocationPath

    /**
     * adds the given [Expression]components.getS to the current [LocationPath]
     */
    public operator fun LocationPath.get(predicates: ExpressionBuilder.() -> Unit): LocationPath = (
            if (child == null)
                LocationPath(axis, nodetest, this.predicates + expression(predicates))
            else
                LocationPath(axis, nodetest, this.predicates, child[predicates])
            ).also { currentXpath = it }

    public operator fun NodeTest.get(predicates: ExpressionBuilder.() -> Unit): LocationPath =
        LocationPath(Axis.child, this)[predicates].also {
            currentXpath = it
        }

    public operator fun LocationPath.get(predicate: Expression): LocationPath =
        this[{ expression = predicate }]

    /**
     * Adds a [LocationPath] to the current [LocationPath]
     */
    public operator fun LocationPath.div(other: LocationPath): LocationPath = (
            if (child == null)
                LocationPath(axis, nodetest, predicates, other)
            else
                LocationPath(axis, nodetest, this.predicates, child / other)
            ).also { currentXpath = it }

    /**
     * appends the given [NodeTest] to the current [LocationPath]
     */
    public operator fun LocationPath.div(other: NodeTest): LocationPath =
        this / LocationPath(Axis.child, other)

    /**
     * appends the given [NodeTest] to the current [LocationPath]
     */
    public operator fun LocationPath.invoke(other: NodeTest): LocationPath {
        return this / other
    }

    /**
     * creates a [LocationPath] with the current [Axis] and the given [NodeTest]
     */
    public operator fun Axis.invoke(node: NodeTest): LocationPath = LocationPath(this, node)

    /**
     * creates a [LocationPath] with the current [Axis] and the given [String] as a new [NodeTest]
     */
    public operator fun Axis.invoke(node: String): LocationPath = this(NodeTest(node))

    /**
     * shortcut for an [ExpressionBuilder.position] predicate
     */
    //TODO: components.getFigure out how to separate this like the rest of the shortcuts (see shortcuts.kt)
    public operator fun LocationPath.get(index: Int): LocationPath = this[{ position() equal index.toString() }]
}