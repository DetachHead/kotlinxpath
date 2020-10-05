/**
 * an xpath predicate. more info [here](https://en.wikipedia.org/wiki/XPath#Predicates)
 */
public class Predicate(public val value: String) {
    //TODO: extrapolate the possible expressions in predicates
    override fun toString(): String = "[$value]"
}

/**
 * typesafe builder for [Predicate]
 */
public class PredicateBuilder : Buildable<Predicate> {
    private lateinit var predicate: Predicate
    override fun build(): Predicate = predicate

    public fun String.compare(operator: ComparisonOperator, other: String): Predicate = Predicate(
        "$this$operator${
            when {
                //xpath has no real way of escaping, so use various ways to esc values with quotes.
                //https://stackoverflow.com/questions/14822153/escape-single-quote-in-xpath-with-nokogiri
                other.contains("'") && other.contains("\"") ->
                    "concat('${other.replace("'", "',\"'\",'")}')"
                other.contains("'") -> "\"$other\""
                else -> "'$other'"
            }
        }"
    ).also { predicate = it }

    /**
     * creates a predicate checking the equality of an Xpath
     */
    public fun Xpath.compare(operator: ComparisonOperator, other: String): Predicate =
        this.toString().compare(operator, other)

    public infix fun String.equals(other: String): Predicate = this.compare(ComparisonOperator.`=`, other)
    public infix fun Xpath.equals(other: String): Predicate = this.toString() equals other
}