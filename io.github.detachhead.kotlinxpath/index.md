---
title: io.github.detachhead.kotlinxpath -
---
//[kotlinxpath](../index.md)/[io.github.detachhead.kotlinxpath](index.md)



# Package io.github.detachhead.kotlinxpath  


## Functions  
  
|  Name|  Summary| 
|---|---|
| [attr](attr.md)| [common]  <br>Brief description  <br><br><br><br><br>equivalent to the @ shortcut.<br><br><br><br>short for attribute::abc<br><br><br><br>eg.<br><br>attr("components.getFoo").toString() == "@components.getFoo"<br><br>  <br>Content  <br>fun [LocationPathBuilder](../io.github.detachhead.kotlinxpath.components/-location-path-builder/index.md).[attr](attr.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [LocationPath](../io.github.detachhead.kotlinxpath.components/-location-path/index.md)  <br><br><br>
| [hasClass](has-class.md)| [common]  <br>Brief description  <br><br><br>checks whether the element has the given className<br><br>  <br>Content  <br>fun [LocationPathBuilder](../io.github.detachhead.kotlinxpath.components/-location-path-builder/index.md).[hasClass](has-class.md)(className: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Expression](../io.github.detachhead.kotlinxpath.components/-expression/index.md)  <br><br><br>
| [textIs](text-is.md)| [common]  <br>Brief description  <br><br><br>converts all text to lowercase, automatically handles quote escaping and trims any whitespace<br><br>  <br>Content  <br>fun [LocationPathBuilder](../io.github.detachhead.kotlinxpath.components/-location-path-builder/index.md).[textIs](text-is.md)(text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Expression](../io.github.detachhead.kotlinxpath.components/-expression/index.md)  <br><br><br>


## Properties  
  
|  Name|  Summary| 
|---|---|
| [any](index.md#io.github.detachhead.kotlinxpath//any/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/)|  [common] <br><br><br><br>equivalent to the // shortcut.<br><br><br><br>short for /descendant-or-self::components.getNode()/<br><br><br><br>val [LocationPathBuilder](../io.github.detachhead.kotlinxpath.components/-location-path-builder/index.md).[any](index.md#io.github.detachhead.kotlinxpath//any/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/): [LocationPath](../io.github.detachhead.kotlinxpath.components/-location-path/index.md)   <br>
| [anyNode](index.md#io.github.detachhead.kotlinxpath//anyNode/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/)|  [common] <br><br><br><br>equivalent to the //&#42 shortcut.<br><br><br><br>short for /descendant-or-self::components.getNode()/&#42<br><br><br><br>val [LocationPathBuilder](../io.github.detachhead.kotlinxpath.components/-location-path-builder/index.md).[anyNode](index.md#io.github.detachhead.kotlinxpath//anyNode/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/): [LocationPath](../io.github.detachhead.kotlinxpath.components/-location-path/index.md)   <br>
| [parent](index.md#io.github.detachhead.kotlinxpath//parent/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/)|  [common] <br><br><br><br>equivalent to the .. shortcut.<br><br><br><br>short for parent::components.getNode()<br><br><br><br>val [LocationPathBuilder](../io.github.detachhead.kotlinxpath.components/-location-path-builder/index.md).[parent](index.md#io.github.detachhead.kotlinxpath//parent/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/): [LocationPath](../io.github.detachhead.kotlinxpath.components/-location-path/index.md)   <br>
| [self](index.md#io.github.detachhead.kotlinxpath//self/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/)|  [common] <br><br><br><br>equivalent to the . shortcut.<br><br><br><br>short for self::components.getNode()<br><br><br><br>val [LocationPathBuilder](../io.github.detachhead.kotlinxpath.components/-location-path-builder/index.md).[self](index.md#io.github.detachhead.kotlinxpath//self/io.github.detachhead.kotlinxpath.components.LocationPathBuilder#/PointingToDeclaration/): [LocationPath](../io.github.detachhead.kotlinxpath.components/-location-path/index.md)   <br>

