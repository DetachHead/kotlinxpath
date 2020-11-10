package io.github.detachhead.kotlinxpath.components

import io.github.detachhead.kotlinxpath.functions.position

/**
 * an xpath [location path](https://en.wikipedia.org/wiki/XPath#Syntax_and_semantics_(XPath_1.0))
 */
public open class LocationPath(
    /** whether or not the [LocationPath] starts with a `/` (ie. starts at the outermost element of the document) */
    public val isRoot: Boolean,
    public val axis: Axis,
    public val nodetest: NodeTest,
    public val predicates: Set<Expression> = setOf(),
    public val child: LocationPath? = null
) : Expression(
    "${if (isRoot) "/" else ""}$axis::$nodetest"
            + (predicates.takeIf { it.isNotEmpty() }?.joinToString("][", "[", "]") ?: "")
            + (child?.let { "/$it" } ?: "")
)

/**
 * creates a [LocationPath] using the [LocationPathBuilder] with the given [block].
 *
 * to make the [LocationPath] start with a single `/`, use a `+`. eg.
 * ```kotlin
 * xpath { +span / p }.toString() // "/span/p"
 * ```
 */
public fun xpath(block: LocationPathBuilder.() -> Unit): LocationPath = LocationPathBuilder().apply(block).currentXpath

/**
 * typesafe builder for [LocationPath]
 */
public class LocationPathBuilder {
    internal lateinit var currentXpath: LocationPath

    /**
     * adds the given [predicates] to the current [LocationPath]
     */
    public operator fun LocationPath.get(predicates: ExpressionBuilder.() -> Unit): LocationPath = (
            if (child == null)
                LocationPath(isRoot, axis, nodetest, this.predicates + expression(predicates))
            else
                LocationPath(isRoot, axis, nodetest, this.predicates, child[predicates])
            ).also { currentXpath = it }

    /** adds the given [predicate] to the current [LocationPath] */
    public operator fun LocationPath.get(predicate: Expression): LocationPath =
        this[{ expression = predicate }]

    /** creates a [LocationPath] from the current [NodeTest] and [predicates] */
    public operator fun NodeTest.get(predicates: ExpressionBuilder.() -> Unit): LocationPath =
        LocationPath(false, Axis.child, this)[predicates].also {
            currentXpath = it
        }

    /** creates a [LocationPath] from the current [NodeTest] and [predicate] */
    public operator fun NodeTest.get(predicate: Expression): LocationPath = this[{ expression = predicate }]


    /** creates a [LocationPath] with a [NodeTest] made from the current [String] and [predicates] */
    public operator fun String.get(predicates: ExpressionBuilder.() -> Unit): LocationPath =
        LocationPath(false, Axis.child, NodeTest(this))[predicates]

    /** creates a [LocationPath] with a [NodeTest] made from the current [String] and [predicate] */
    public operator fun String.get(predicate: Expression): LocationPath =
        LocationPath(false, Axis.child, NodeTest(this))[predicate]

    /**
     * Adds a [LocationPath] to the current [LocationPath]
     */
    public operator fun LocationPath.div(other: LocationPath): LocationPath = (
            if (child == null)
                LocationPath(isRoot, axis, nodetest, predicates, other)
            else
                LocationPath(isRoot, axis, nodetest, this.predicates, child / other)
            ).also { currentXpath = it }

    /**
     * appends the given [NodeTest] as a [Axis.child] to the current [LocationPath]
     */
    public operator fun LocationPath.div(other: NodeTest): LocationPath =
        this / LocationPath(false, Axis.child, other)

    /**
     * creates a [LocationPath] by appending the given [LocationPath] as a [Axis.child] to it
     */
    public operator fun NodeTest.div(other: LocationPath): LocationPath = LocationPath(false, Axis.child, this) / other

    /**
     * creates a [LocationPath] from the given [NodeTest] and appends the given [NodeTest] as a [Axis.child] to it
     */
    public operator fun NodeTest.div(other: NodeTest): LocationPath = LocationPath(false, Axis.child, this) / other

    /**
     * appends a [NodeTest] from the given [String] as a [Axis.child] to the current [LocationPath]
     */
    public operator fun LocationPath.div(other: String): LocationPath = this / NodeTest(other)

    /**
     * creates a [LocationPath] from the given [NodeTest] and appends another [NodeTest] from the given [String]
     * as a [Axis.child] to it
     */
    public operator fun NodeTest.div(other: String): LocationPath = this / NodeTest(other)

    /**
     * creates a [LocationPath] with the current [Axis] and the given [NodeTest]
     */
    public operator fun Axis.invoke(node: NodeTest): LocationPath = LocationPath(false, this, node)

    /**
     * creates a [LocationPath] with the current [Axis] and the given [String] as a new [NodeTest]
     */
    public operator fun Axis.invoke(node: String): LocationPath = this(NodeTest(node))

    /** adds a `/` to the start of the [LocationPath] (meaning it starts at the outermost element of the document) */
    public operator fun LocationPath.unaryPlus(): LocationPath = LocationPath(true, axis, nodetest, predicates, child)

    /**
     * creates a [LocationPath] from the [NodeTest] with a `/` at the start
     * (meaning it starts at the outermost element of the document)
     */
    public operator fun NodeTest.unaryPlus(): LocationPath = LocationPath(true, Axis.child, this)

    /**
     * creates a [LocationPath] from a [NodeTest] created from this [String], with a `/` at the start
     * (meaning it starts at the outermost element of the document)
     */
    public operator fun String.unaryPlus(): LocationPath = +NodeTest(this)

    //TODO: figure out how to separate these like the rest of the shortcuts (see shortcuts.kt):
    /**
     * shortcut for an [ExpressionBuilder.position] predicate
     */
    public operator fun LocationPath.get(index: Int): LocationPath = this[{ position() equal index.toString() }]

    /**
     * shortcut for an [ExpressionBuilder.position] predicate
     */
    public operator fun NodeTest.get(index: Int): LocationPath =
        LocationPath(false, Axis.child, this)[index]
}