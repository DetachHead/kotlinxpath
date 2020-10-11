package io.github.detachhead.kotlinxpath.functions.xpath1_shims

import io.github.detachhead.kotlinxpath.components.Expression
import io.github.detachhead.kotlinxpath.components.ExpressionBuilder
import io.github.detachhead.kotlinxpath.functions.FunctionMarker
import io.github.detachhead.kotlinxpath.functions.translate
import kotlin.js.JsName

/*
 * shims of [Xpath 2.0 functions](https://en.wikipedia.org/wiki/XPath_2.0#Function_library)
 * that compile to Xpath 1.0 function calls
 */

private const val uppercase_alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
private const val lowercase_alphabet = "abcdefghijklmnopqrstuvwxyz"

@FunctionMarker
@JsName("lower_case")
public fun ExpressionBuilder.`lower-case`(string: Expression): Expression =
    translate(string, uppercase_alphabet, lowercase_alphabet)

@FunctionMarker
@JsName("lower_case2")
public fun ExpressionBuilder.`lower-case`(string: String): Expression =
    `lower-case`(Expression.fromString(string))

@FunctionMarker
@JsName("upper_case")
public fun ExpressionBuilder.`upper-case`(string: Expression): Expression =
    translate(string, lowercase_alphabet, uppercase_alphabet)

@FunctionMarker
@JsName("upper_case2")
public fun ExpressionBuilder.`upper-case`(string: String): Expression =
    `upper-case`(Expression.fromString(string))