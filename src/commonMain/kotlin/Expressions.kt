/**
 * any valid Xpath expression, not necessarily a [LocationPath]
 */
public open class Expression(public val value: String) {
    public constructor(value: XpathString) : this(value.toString())

    override fun toString(): String = value
}

/** a string represented in an xpath [Expression]. automatically handles escaping quote characters */
public class XpathString(public val value: String) {
    override fun toString(): String = when {
        //xpath has no real way of escaping, so use various ways to esc values with quotes.
        //https://stackoverflow.com/questions/14822153/escape-single-quote-in-xpath-with-nokogiri
        value.contains("'") && value.contains("\"") ->
            "concat('${value.replace("'", "',\"'\",'")}')"
        value.contains("'") -> "\"$value\""
        else -> "'$value'"
    }
}

/** two [Expression]s combined together by an [Operator] */
public class OperatorExpression(
    public val first: Expression,
    public val operator: Operator,
    public val second: Expression
) : Expression("$first $operator $second")

/**
 * typesafe builder for [Expression] for use as [predicates](https://en.wikipedia.org/wiki/XPath#Predicates)
 */
public class PredicateBuilder : Buildable<Expression> {
    private lateinit var predicate: Expression
    override fun build(): Expression = predicate

    public fun String.compare(operator: ComparisonOperator, other: String): Expression = OperatorExpression(
        Expression(this), operator, Expression(XpathString(other))
    ).also { predicate = it }

    /**
     * creates a predicate checking the equality of an Xpath
     */
    public fun LocationPath.compare(operator: ComparisonOperator, other: String): Expression =
        this.toString().compare(operator, other)

    public infix fun String.equals(other: String): Expression = this.compare(ComparisonOperator.`=`, other)
    public infix fun LocationPath.equals(other: String): Expression = this.toString() equals other

    public infix fun String.greaterThan(other: Int): Expression =
        this.compare(ComparisonOperator.greater, other.toString())

    public infix fun LocationPath.equals(other: Int): Expression = this.toString() greaterThan other
}