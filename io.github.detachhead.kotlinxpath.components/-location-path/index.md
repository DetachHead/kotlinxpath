---
title: LocationPath -
---
//[kotlinxpath](../../index.md)/[io.github.detachhead.kotlinxpath.components](../index.md)/[LocationPath](index.md)



# LocationPath  
 [common] 

an xpath [location path](https://en.wikipedia.org/wiki/XPath#Syntax_and_semantics_(XPath_1.0))

open class [LocationPath](index.md)(**axis**: [Axis](../-axis/index.md), **nodetest**: [NodeTest](../-node-test/index.md), **predicates**: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Expression](../-expression/index.md)>, **child**: [LocationPath](index.md)?) : [Expression](../-expression/index.md)   


## Constructors  
  
|  Name|  Summary| 
|---|---|
| [LocationPath](-location-path.md)|  [common] fun [LocationPath](-location-path.md)(axis: [Axis](../-axis/index.md), nodetest: [NodeTest](../-node-test/index.md), predicates: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Expression](../-expression/index.md)>, child: [LocationPath](index.md)?)   <br>


## Functions  
  
|  Name|  Summary| 
|---|---|
| [equals](../-operator/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)| [common]  <br>Content  <br>open operator override fun [equals](../-operator/index.md#kotlin/Any/equals/#kotlin.Any?/PointingToDeclaration/)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [hashCode](../-operator/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)| [common]  <br>Content  <br>open override fun [hashCode](../-operator/index.md#kotlin/Any/hashCode/#/PointingToDeclaration/)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| [toString](../-expression/to-string.md)| [common]  <br>Content  <br>open override fun [toString](../-expression/to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [axis](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/axis/#/PointingToDeclaration/)|  [common] val [axis](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/axis/#/PointingToDeclaration/): [Axis](../-axis/index.md)   <br>
| [child](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/child/#/PointingToDeclaration/)|  [common] val [child](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/child/#/PointingToDeclaration/): [LocationPath](index.md)?   <br>
| [nodetest](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/nodetest/#/PointingToDeclaration/)|  [common] val [nodetest](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/nodetest/#/PointingToDeclaration/): [NodeTest](../-node-test/index.md)   <br>
| [predicates](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/predicates/#/PointingToDeclaration/)|  [common] val [predicates](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/predicates/#/PointingToDeclaration/): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)<[Expression](../-expression/index.md)>   <br>
| [value](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/value/#/PointingToDeclaration/)|  [common] override val [value](index.md#io.github.detachhead.kotlinxpath.components/LocationPath/value/#/PointingToDeclaration/): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)   <br>

