package functions.xpath1_shims

import components.Expression
import components.ExpressionBuilder
import functions.functions.translate
import kotlin.js.JsName

/*
 * shims of [Xpath 2.0 functions](https://en.wikipedia.org/wiki/XPath_2.0#Function_library)
 * that compile to Xpath 1.0 function calls
 */

private const val uppercase_alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
private const val lowercase_alphabet = "abcdefghijklmnopqrstuvwxyz"

@JsName("lower_case")
public fun ExpressionBuilder.`lower-case`(string: Expression): Expression =
    translate(string, uppercase_alphabet, lowercase_alphabet)

@JsName("lower_case2")
public fun ExpressionBuilder.`lower-case`(string: String): Expression =
    `lower-case`(Expression(string))

@JsName("upper_case")
public fun ExpressionBuilder.`upper-case`(string: Expression): Expression =
    translate(string, lowercase_alphabet, uppercase_alphabet)

@JsName("upper_case2")
public fun ExpressionBuilder.`upper-case`(string: String): Expression =
    `upper-case`(Expression(string))