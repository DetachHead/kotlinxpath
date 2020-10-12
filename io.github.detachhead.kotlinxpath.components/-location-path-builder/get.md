---
title: get -
---
//[kotlinxpath](../../index.md)/[io.github.detachhead.kotlinxpath.components](../index.md)/[LocationPathBuilder](index.md)/[get](get.md)



# get  
[common]  
Brief description  


adds the given predicates to the current [LocationPath](../-location-path/index.md)

  
Content  
operator fun [LocationPath](../-location-path/index.md).[get](get.md)(predicates: [ExpressionBuilder](../-expression-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [LocationPath](../-location-path/index.md)  


[common]  
Brief description  


adds the given predicate to the current [LocationPath](../-location-path/index.md)

  
Content  
operator fun [LocationPath](../-location-path/index.md).[get](get.md)(predicate: [Expression](../-expression/index.md)): [LocationPath](../-location-path/index.md)  


[common]  
Brief description  


creates a [LocationPath](../-location-path/index.md) from the current [NodeTest](../-node-test/index.md) and predicates

  
Content  
operator fun [NodeTest](../-node-test/index.md).[get](get.md)(predicates: [ExpressionBuilder](../-expression-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [LocationPath](../-location-path/index.md)  


[common]  
Brief description  


creates a [LocationPath](../-location-path/index.md) from the current [NodeTest](../-node-test/index.md) and predicate

  
Content  
operator fun [NodeTest](../-node-test/index.md).[get](get.md)(predicate: [Expression](../-expression/index.md)): [LocationPath](../-location-path/index.md)  


[common]  
Brief description  


creates a [LocationPath](../-location-path/index.md) with a [NodeTest](../-node-test/index.md) made from the current [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/index.html) and predicates

  
Content  
operator fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[get](get.md)(predicates: [ExpressionBuilder](../-expression-builder/index.md).() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [LocationPath](../-location-path/index.md)  


[common]  
Brief description  


creates a [LocationPath](../-location-path/index.md) with a [NodeTest](../-node-test/index.md) made from the current [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/index.html) and predicate

  
Content  
operator fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[get](get.md)(predicate: [Expression](../-expression/index.md)): [LocationPath](../-location-path/index.md)  


[common]  
Brief description  


shortcut for an [ExpressionBuilder.position](../../io.github.detachhead.kotlinxpath.functions/position.md) predicate

  
Content  
operator fun [LocationPath](../-location-path/index.md).[get](get.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [LocationPath](../-location-path/index.md)  
operator fun [NodeTest](../-node-test/index.md).[get](get.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [LocationPath](../-location-path/index.md)  



