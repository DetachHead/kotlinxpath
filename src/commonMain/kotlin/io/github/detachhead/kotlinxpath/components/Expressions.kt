package io.github.detachhead.kotlinxpath.components

import io.github.detachhead.kotlinxpath.functions.concat
import splitKeep

/**
 * any valid Xpath expression, not necessarily a [LocationPath]
 */
public open class Expression(private val value: String) {
    public constructor(value: XpathString) : this(value.toString())
    public constructor(value: Boolean) : this(XpathString(value.toString()))
    public constructor(value: Int) : this(value.toString())

    override fun toString(): String = value

    public companion object {
        /** creates an [Expression] from the given [String], using [XpathString] */
        public fun fromString(string: String): Expression = Expression(XpathString(string))
    }
}

/** a string represented in an xpath [Expression]. automatically handles escaping quote characters */
public class XpathString(public val value: String) {
    override fun toString(): String = when {
        // xpath has no real way of escaping, so use various ways to esc values with quotes.
        // https://stackoverflow.com/questions/14822153/escape-single-quote-in-xpath-with-nokogiri
        value.contains("'") && value.contains("\"") ->
            expression { concat(*(value.splitKeep("'").map { Expression.fromString(it) }.toTypedArray())) }.toString()
        value.contains("'") -> "\"$value\""
        else -> "'$value'"
    }
}

/**
 * creates an [Expression] using the [ExpressionBuilder] with the given [block]
 * to build an actual xpath ([LocationPath]), use [xpath] instead
 */
public fun expression(block: ExpressionBuilder.() -> Unit): Expression = ExpressionBuilder().apply(block).expression

/**
 * typesafe builder for [Expression] for use as [predicates](https://en.wikipedia.org/wiki/XPath#Predicates)
 */
public class ExpressionBuilder {
    /** the current [Expression] buing built */
    internal lateinit var expression: Expression

    /** combines two [Expression]s together with an [Operator] */
    private fun operatorExpression(first: Expression, operator: Operator, second: Expression): Expression =
        Expression("$first $operator $second").also { expression = it }

    /** creates an [Expression] with an xpath [function call](https://en.wikipedia.org/wiki/XPath#Functions_and_operators) */
    internal fun functionExpression(name: String, args: List<Expression?> = listOf()): Expression =
        Expression("$name(${args.filterNotNull().joinToString(",")})").also { expression = it }

    /**
     * adds an [Operator] and another [Expression] to the current [Expression]
     *
     * for use by xpath functions
     */
    private fun Expression.operator(operator: Operator, other: Expression): Expression =
        operatorExpression(this, operator, other).also { expression = it }

    /* Xpath operators */
    // =:
    @OperatorMarker
    public infix fun Expression.equal(other: Expression): Expression = this.operator(ComparisonOperator.`=`, other)
    @OperatorMarker
    public infix fun Expression.equal(other: String): Expression = this equal Expression(XpathString(other))
    @OperatorMarker
    public infix fun String.equal(other: Expression): Expression = Expression(this) equal other
    @OperatorMarker
    public infix fun String.equal(other: String): Expression = Expression(this) equal other

    // !=:
    @OperatorMarker
    public infix fun Expression.not(other: Expression): Expression = this.operator(ComparisonOperator.`=`, other)
    @OperatorMarker
    public infix fun Expression.not(other: String): Expression = this not Expression(XpathString(other))
    @OperatorMarker
    public infix fun String.not(other: Expression): Expression = Expression(this) not other
    @OperatorMarker
    public infix fun String.not(other: String): Expression = Expression(this) not other

    // >:
    @OperatorMarker
    public infix fun Expression.greaterThan(other: Expression): Expression = operator(ComparisonOperator.greater, other)
    @OperatorMarker
    public infix fun Expression.greaterThan(other: Int): Expression = greaterThan(Expression(other))
    @OperatorMarker
    public infix fun Int.greaterThan(other: Expression): Expression = Expression(this) greaterThan other
    @OperatorMarker
    public infix fun Int.greaterThan(other: Int): Expression = Expression(this) greaterThan other

    // >=:
    @OperatorMarker
    public infix fun Expression.greaterThanOrEqualTo(other: Expression): Expression =
        operator(ComparisonOperator.greaterOrEqual, other)

    @OperatorMarker
    public infix fun Expression.greaterThanOrEqualTo(other: Int): Expression =
        greaterThanOrEqualTo(Expression(other.toString()))

    @OperatorMarker
    public infix fun Int.greaterThanOrEqualTo(other: Expression): Expression =
        Expression(toString()) greaterThanOrEqualTo other

    @OperatorMarker
    public infix fun Int.greaterThanOrEqualTo(other: Int): Expression =
        Expression(toString()) greaterThanOrEqualTo other

    // <:
    @OperatorMarker
    public infix fun Expression.lessThan(other: Expression): Expression = operator(ComparisonOperator.less, other)
    @OperatorMarker
    public infix fun Expression.lessThan(other: Int): Expression = lessThan(Expression(other.toString()))
    @OperatorMarker
    public infix fun Int.lessThan(other: Expression): Expression = Expression(toString()) lessThan other
    @OperatorMarker
    public infix fun Int.lessThan(other: Int): Expression = Expression(toString()) lessThan other

    // <=:
    @OperatorMarker
    public infix fun Expression.lessThanOrEqualTo(other: Expression): Expression =
        operator(ComparisonOperator.lessOrEqual, other)

    @OperatorMarker
    public infix fun Expression.lessThanOrEqualTo(other: Int): Expression =
        greaterThanOrEqualTo(Expression(other.toString()))

    @OperatorMarker
    public infix fun Int.lessThanOrEqualTo(other: Expression): Expression =
        Expression(toString()) lessThanOrEqualTo other

    @OperatorMarker
    public infix fun Int.lessThanOrEqualTo(other: Int): Expression =
        Expression(toString()) lessThanOrEqualTo other

    // and:
    @OperatorMarker
    public infix fun Expression.and(other: Expression): Expression = operator(BooleanOperator.and, other)

    // or:
    @OperatorMarker
    public infix fun Expression.or(other: Expression): Expression = operator(BooleanOperator.or, other)
}
