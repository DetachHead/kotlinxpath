# xpath-builder
kotlin typesafe builder for xpath

![CI](https://github.com/DetachHead/xpath-builder/workflows/CI/badge.svg)

## example

```kotlin
println(xpath {
    descendantOrSelf / "div"("id" to "thing", text = "sdfg")
})
```
outputs `//div[@id='thing' and normalize-space(.)='sdfg']`
