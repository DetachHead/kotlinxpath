import kotlin.js.JsName

/**
 * any valid Xpath expression, not necessarily a [LocationPath]
 */
public open class Expression(public val value: String) {
    public constructor(value: XpathString) : this(value.toString())
    public constructor(value: Boolean) : this(XpathString(value.toString()))
    public constructor(value: Int) : this(value.toString())

    override fun toString(): String = value
}

/** a string represented in an xpath [Expression]. automatically handles escaping quote characters */
public class XpathString(public val value: String) {
    override fun toString(): String = when {
        //xpath has no real way of escaping, so use various ways to esc values with quotes.
        //https://stackoverflow.com/questions/14822153/escape-single-quote-in-xpath-with-nokogiri
        value.contains("'") && value.contains("\"") ->
            expression { concat(*(value.split("'").toTypedArray())) }.toString()
        value.contains("'") -> "\"$value\""
        else -> "'$value'"
    }
}

/** combines two [Expression]s together with an [Operator] */
private fun operatorExpression(first: Expression, operator: Operator, second: Expression): Expression =
    Expression("$first $operator $second")

/** creates an expression with an xpath [function call](https://en.wikipedia.org/wiki/XPath#Functions_and_operators) */
private fun functionExpression(name: String, args: List<Expression?> = listOf()): Expression =
    Expression("$name(${args.filterNotNull().joinToString(",")})")

/**
 * creates an [Expression] using the [ExpressionBuilder] with the given [block]
 * to build an actual xpath ([LocationPath]), use [xpath] instead
 */
public fun expression(block: ExpressionBuilder.() -> Unit): Expression = ExpressionBuilder().apply(block).build()

/**
 * typesafe builder for [Expression] for use as [predicates](https://en.wikipedia.org/wiki/XPath#Predicates)
 */
public class ExpressionBuilder : Buildable<Expression> {
    private lateinit var expression: Expression
    override fun build(): Expression = expression

    private fun Expression.operator(
        operator: Operator,
        other: Expression
    ): Expression = operatorExpression(this, operator, other).also { expression = it }

    /* Xpath operators */
    //=:
    public infix fun Expression.equal(other: Expression): Expression = this.operator(ComparisonOperator.`=`, other)
    public infix fun Expression.equal(other: String): Expression = this equal Expression(XpathString(other))
    public infix fun String.equal(other: Expression): Expression = Expression(this) equal other
    public infix fun String.equal(other: String): Expression = Expression(this) equal other

    //!=:
    public infix fun Expression.not(other: Expression): Expression = this.operator(ComparisonOperator.`=`, other)
    public infix fun Expression.not(other: String): Expression = this not Expression(XpathString(other))
    public infix fun String.not(other: Expression): Expression = Expression(this) not other
    public infix fun String.not(other: String): Expression = Expression(this) not other

    //>:
    public infix fun Expression.greaterThan(other: Expression): Expression = operator(ComparisonOperator.greater, other)
    public infix fun Expression.greaterThan(other: Int): Expression = greaterThan(Expression(other))
    public infix fun Int.greaterThan(other: Expression): Expression = Expression(this) greaterThan other
    public infix fun Int.greaterThan(other: Int): Expression = Expression(this) greaterThan other

    //>=:
    public infix fun Expression.greaterThanOrEqualTo(other: Expression): Expression =
        operator(ComparisonOperator.greaterOrEqual, other)

    public infix fun Expression.greaterThanOrEqualTo(other: Int): Expression =
        greaterThanOrEqualTo(Expression(other.toString()))

    public infix fun Int.greaterThanOrEqualTo(other: Expression): Expression =
        Expression(toString()) greaterThanOrEqualTo other

    public infix fun Int.greaterThanOrEqualTo(other: Int): Expression =
        Expression(toString()) greaterThanOrEqualTo other

    //<:
    public infix fun Expression.lessThan(other: Expression): Expression = operator(ComparisonOperator.less, other)
    public infix fun Expression.lessThan(other: Int): Expression = lessThan(Expression(other.toString()))
    public infix fun Int.lessThan(other: Expression): Expression = Expression(toString()) lessThan other
    public infix fun Int.lessThan(other: Int): Expression = Expression(toString()) lessThan other

    //<=:
    public infix fun Expression.lessThanOrEqualTo(other: Expression): Expression =
        operator(ComparisonOperator.lessOrEqual, other)

    public infix fun Expression.lessThanOrEqualTo(other: Int): Expression =
        greaterThanOrEqualTo(Expression(other.toString()))

    public infix fun Int.lessThanOrEqualTo(other: Expression): Expression =
        Expression(toString()) lessThanOrEqualTo other

    public infix fun Int.lessThanOrEqualTo(other: Int): Expression =
        Expression(toString()) lessThanOrEqualTo other

    //and:
    public infix fun Expression.and(other: Expression): Expression = operator(BooleanOperator.and, other)

    //or:
    public infix fun Expression.or(other: Expression): Expression = operator(BooleanOperator.or, other)

    /*
     * [Xpath functions](https://en.wikipedia.org/wiki/XPath#Functions_and_operators)
     */
    public fun position(): Expression = functionExpression("position")

    public fun count(nodeSet: LocationPath): Expression = functionExpression("count", listOf(nodeSet))

    public fun string(obj: Expression? = null): Expression = functionExpression("string", listOf(obj))
    public fun string(str: String): Expression = string(Expression(str))
    public fun string(str: Int): Expression = string(Expression(str))
    public fun string(str: Boolean): Expression = string(Expression(str))

    public fun concat(vararg expressions: Expression): Expression = functionExpression("concat", expressions.toList())
    public fun concat(vararg strings: String): Expression = concat(*(strings.map { Expression(it) }.toTypedArray()))

    @JsName("starts_with")
    public fun `starts-with`(first: Expression, second: Expression): Expression =
        functionExpression("starts-with", listOf(first, second))

    @JsName("starts_with2")
    public fun `starts-with`(first: Expression, second: String): Expression = `starts-with`(first, Expression(second))

    @JsName("starts_with3")
    public fun `starts-with`(first: String, second: Expression): Expression = `starts-with`(Expression(first), second)

    @JsName("starts_with4")
    public fun `starts-with`(first: String, second: String): Expression =
        `starts-with`(Expression(first), Expression(second))

    public fun contains(first: Expression, second: Expression): Expression =
        functionExpression("contains", listOf(first, second))

    public fun contains(first: Expression, second: String): Expression = contains(first, Expression(second))
    public fun contains(first: String, second: Expression): Expression = contains(Expression(first), second)
    public fun contains(first: String, second: String): Expression = contains(Expression(first), Expression(second))

    public fun substring(first: Expression, second: Expression, length: Expression? = null): Expression =
        functionExpression("substring", listOf(first, second, length))

    public fun substring(first: Expression, second: String, length: Expression? = null): Expression =
        substring(first, Expression(second), length)

    public fun substring(first: String, second: Expression, length: Expression? = null): Expression =
        substring(Expression(first), second, length)

    public fun substring(first: String, second: String, length: Expression? = null): Expression =
        substring(Expression(first), Expression(second), length)

    public fun substring(first: Expression, second: Expression, length: Int): Expression =
        substring(first, second, Expression(length))

    public fun substring(first: Expression, second: String, length: Int): Expression =
        substring(first, Expression(second), Expression(length))

    public fun substring(first: String, second: Expression, length: Int): Expression =
        substring(Expression(first), second, Expression(length))

    public fun substring(first: String, second: String, length: Int): Expression =
        substring(Expression(first), Expression(second), Expression(length))

    @JsName("substring_before")
    public fun `substring-before`(first: Expression, second: Expression): Expression =
        functionExpression("substring-before", listOf(first, second))

    @JsName("substring_before2")
    public fun `substring-before`(first: Expression, second: String): Expression =
        `substring-before`(first, Expression(second))

    @JsName("substring_before3")
    public fun `substring-before`(first: String, second: Expression): Expression =
        `substring-before`(Expression(first), second)

    @JsName("substring_before4")
    public fun `substring-before`(first: String, second: String): Expression =
        `substring-before`(Expression(first), Expression(second))

    @JsName("substring_after")
    public fun `substring-after`(first: Expression, second: Expression): Expression =
        functionExpression("substring-after", listOf(first, second))

    @JsName("substring_after2")
    public fun `substring-after`(first: Expression, second: String): Expression =
        `substring-before`(first, Expression(second))

    @JsName("substring_after3")
    public fun `substring-after`(first: String, second: Expression): Expression =
        `substring-before`(Expression(first), second)

    @JsName("substring_after4")
    public fun `substring-after`(first: String, second: String): Expression =
        `substring-before`(Expression(first), Expression(second))

    @JsName("string_length")
    public fun `string-length`(expression: Expression? = null): Expression =
        functionExpression("string-length", listOf(expression))

    @JsName("string_length2")
    public fun `string-length`(string: String): Expression = `string-length`(Expression(string))

    @JsName("normalize_space")
    public fun `normalize-space`(expression: Expression? = null): Expression =
        functionExpression("normalize-space", listOf(expression))

    @JsName("normalize_space2")
    public fun `normalize-space`(string: String): Expression = `normalize-space`(Expression(string))

    public fun not(expression: Expression): Expression = functionExpression("not", listOf(expression))
    public fun not(bool: Boolean): Expression = not(Expression(bool))

    @JsName("_true")
    public fun `true`(): Expression = functionExpression("true")

    @JsName("_false")
    public fun `false`(): Expression = functionExpression("true")

    public fun sum(nodeSet: LocationPath): Expression = functionExpression("true", listOf(nodeSet))
}