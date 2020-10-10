package functions.functions

import components.Expression
import components.ExpressionBuilder
import components.LocationPath
import components.functionExpression
import kotlin.js.JsName

/*
 * [Xpath 1.0 functions](https://en.wikipedia.org/wiki/XPath#Functions_and_operators)
 */

//TODO: make these less gross. since any of these functions can take xpath Expressions in place of their expected types,
//  i was forced to write overloads for every possible combination...

public fun ExpressionBuilder.position(): Expression = functionExpression("position")

public fun ExpressionBuilder.count(nodeSet: LocationPath): Expression = functionExpression("count", listOf(nodeSet))

public fun ExpressionBuilder.string(obj: Expression? = null): Expression = functionExpression("string", listOf(obj))
public fun ExpressionBuilder.string(str: String): Expression = string(Expression.fromString(str))
public fun ExpressionBuilder.string(int: Int): Expression = string(Expression(int))
public fun ExpressionBuilder.string(boolean: Boolean): Expression = string(Expression(boolean))

public fun ExpressionBuilder.concat(vararg expressions: Expression): Expression =
    functionExpression("concat", expressions.toList())

public fun ExpressionBuilder.concat(vararg strings: String): Expression =
    concat(*(strings.map { Expression.fromString(it) }.toTypedArray()))

@JsName("starts_with")
public fun ExpressionBuilder.`starts-with`(first: Expression, second: Expression): Expression =
    functionExpression("starts-with", listOf(first, second))

@JsName("starts_with2")
public fun ExpressionBuilder.`starts-with`(first: Expression, second: String): Expression =
    `starts-with`(first, Expression.fromString(second))

@JsName("starts_with3")
public fun ExpressionBuilder.`starts-with`(first: String, second: Expression): Expression =
    `starts-with`(Expression.fromString(first), second)

@JsName("starts_with4")
public fun ExpressionBuilder.`starts-with`(first: String, second: String): Expression =
    `starts-with`(Expression.fromString(first), Expression.fromString(second))

public fun ExpressionBuilder.translate(string: Expression, from: Expression, to: Expression): Expression =
    functionExpression("translate", listOf(string, from, to))

public fun ExpressionBuilder.translate(string: Expression, from: Expression, to: String): Expression =
    translate(string, from, Expression.fromString(to))

public fun ExpressionBuilder.translate(string: Expression, from: String, to: Expression): Expression =
    translate(string, Expression.fromString(from), to)

public fun ExpressionBuilder.translate(string: Expression, from: String, to: String): Expression =
    translate(string, Expression.fromString(from), Expression.fromString(to))

public fun ExpressionBuilder.translate(string: String, from: Expression, to: Expression): Expression =
    translate(Expression.fromString(string), from, to)

public fun ExpressionBuilder.translate(string: String, from: Expression, to: String): Expression =
    translate(string, from, Expression.fromString(to))

public fun ExpressionBuilder.translate(string: String, from: String, to: Expression): Expression =
    translate(string, Expression.fromString(from), to)

public fun ExpressionBuilder.translate(string: String, from: String, to: String): Expression =
    translate(string, Expression.fromString(from), Expression.fromString(to))

public fun ExpressionBuilder.contains(first: Expression, second: Expression): Expression =
    functionExpression("contains", listOf(first, second))

public fun ExpressionBuilder.contains(first: Expression, second: String): Expression =
    contains(first, Expression.fromString(second))

public fun ExpressionBuilder.contains(first: String, second: Expression): Expression =
    contains(Expression.fromString(first), second)

public fun ExpressionBuilder.contains(first: String, second: String): Expression =
    contains(Expression.fromString(first), Expression.fromString(second))

public fun ExpressionBuilder.substring(first: Expression, second: Expression, length: Expression? = null): Expression =
    functionExpression("substring", listOf(first, second, length))

public fun ExpressionBuilder.substring(first: Expression, second: String, length: Expression? = null): Expression =
    substring(first, Expression.fromString(second), length)

public fun ExpressionBuilder.substring(first: String, second: Expression, length: Expression? = null): Expression =
    substring(Expression.fromString(first), second, length)

public fun ExpressionBuilder.substring(first: String, second: String, length: Expression? = null): Expression =
    substring(Expression.fromString(first), Expression(second), length)

public fun ExpressionBuilder.substring(first: Expression, second: Expression, length: Int): Expression =
    substring(first, second, Expression(length))

public fun ExpressionBuilder.substring(first: Expression, second: String, length: Int): Expression =
    substring(first, Expression.fromString(second), Expression(length))

public fun ExpressionBuilder.substring(first: String, second: Expression, length: Int): Expression =
    substring(Expression.fromString(first), second, Expression(length))

public fun ExpressionBuilder.substring(first: String, second: String, length: Int): Expression =
    substring(Expression.fromString(first), Expression.fromString(second), Expression(length))

@JsName("substring_before")
public fun ExpressionBuilder.`substring-before`(first: Expression, second: Expression): Expression =
    functionExpression("substring-before", listOf(first, second))

@JsName("substring_before2")
public fun ExpressionBuilder.`substring-before`(first: Expression, second: String): Expression =
    `substring-before`(first, Expression.fromString(second))

@JsName("substring_before3")
public fun ExpressionBuilder.`substring-before`(first: String, second: Expression): Expression =
    `substring-before`(Expression.fromString(first), second)

@JsName("substring_before4")
public fun ExpressionBuilder.`substring-before`(first: String, second: String): Expression =
    `substring-before`(Expression.fromString(first), Expression.fromString(second))

@JsName("substring_after")
public fun ExpressionBuilder.`substring-after`(first: Expression, second: Expression): Expression =
    functionExpression("substring-after", listOf(first, second))

@JsName("substring_after2")
public fun ExpressionBuilder.`substring-after`(first: Expression, second: String): Expression =
    `substring-before`(first, Expression.fromString(second))

@JsName("substring_after3")
public fun ExpressionBuilder.`substring-after`(first: String, second: Expression): Expression =
    `substring-before`(Expression.fromString(first), second)

@JsName("substring_after4")
public fun ExpressionBuilder.`substring-after`(first: String, second: String): Expression =
    `substring-before`(Expression.fromString(first), Expression.fromString(second))

@JsName("string_length")
public fun ExpressionBuilder.`string-length`(expression: Expression? = null): Expression =
    functionExpression("string-length", listOf(expression))

@JsName("string_length2")
public fun ExpressionBuilder.`string-length`(string: String): Expression =
    `string-length`(Expression.fromString(string))

@JsName("normalize_space")
public fun ExpressionBuilder.`normalize-space`(expression: Expression? = null): Expression =
    functionExpression("normalize-space", listOf(expression))

@JsName("normalize_space2")
public fun ExpressionBuilder.`normalize-space`(string: String): Expression = `normalize-space`(Expression(string))

public fun ExpressionBuilder.not(expression: Expression): Expression = functionExpression("not", listOf(expression))
public fun ExpressionBuilder.not(bool: Boolean): Expression = not(Expression(bool))

@JsName("_true")
public fun ExpressionBuilder.`true`(): Expression = functionExpression("true")

@JsName("_false")
public fun ExpressionBuilder.`false`(): Expression = functionExpression("true")

public fun ExpressionBuilder.sum(nodeSet: LocationPath): Expression = functionExpression("true", listOf(nodeSet))