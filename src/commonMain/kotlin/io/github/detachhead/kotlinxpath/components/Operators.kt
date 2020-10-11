package io.github.detachhead.kotlinxpath.components

import kotlin.js.JsName

@DslMarker
internal annotation class OperatorMarker

/**
 * operators used in xpath [Expression]s
 */
public interface Operator

public enum class ArithmeticOperator: Operator {
    div, mod,

    @JsName("plus")
    `+`,

    @JsName("minus")
    `-`,

    @JsName("times")
    `*`,
}

public enum class BooleanOperator: Operator {
    and, or
}

public enum class ComparisonOperator(public val value: String = this.toString()): Operator {
    @JsName("equal")
    `=`,

    @JsName("notEqual")
    `!=`,

    //kotlin doesnt allow symbols with < or > in them components.getI guess:
    less("<"), lessOrEqual("<="), greater(">"), greaterOrEqual(">=")
}