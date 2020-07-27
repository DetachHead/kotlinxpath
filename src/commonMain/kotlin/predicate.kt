/**
 * operators used for xpath attributes
 */
enum class operator(val value: String = this.toString()) {
    and, or, `=`, `!=`, `+`, `-`, `*`, div, mod,

    //kotlin doesnt allow symbols with < or > in them i guess:
    less("<"), lessOrEqual("<="), greater(">"), greaterOrEqual(">=")
}

/**
 * an xpath predicate https://en.wikipedia.org/wiki/XPath#Predicates
 */
class predicate(val value: String) {
    //TODO: extrapolate the possible expressions in predicates
    override fun toString() = "[$value]"
}

/**
 * typesafe builder for [predicate]
 */
class predicatebuilder {
    //TODO
}