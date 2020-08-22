import kotlin.js.JsName

/**
 * operators used for xpath attributes
 */
public enum class operator(public val value: String = this.toString()) {
    and, or, div, mod,

    @JsName("equal")
    `=`,

    @JsName("notEqual")
    `!=`,

    @JsName("plus")
    `+`,

    @JsName("minus")
    `-`,

    @JsName("times")
    `*`,

    //kotlin doesnt allow symbols with < or > in them i guess:
    less("<"), lessOrEqual("<="), greater(">"), greaterOrEqual(">=")
}

/**
 * an xpath predicate. more info [here](https://en.wikipedia.org/wiki/XPath#Predicates)
 */
public class predicate(public val value: String) {
    //TODO: extrapolate the possible expressions in predicates
    override fun toString(): String = "[$value]"
}

/**
 * typesafe builder for [predicate]
 */
public class predicatebuilder {
    //TODO
}