# xpath-builder
kotlin typesafe builder for xpath

![CI](https://github.com/DetachHead/xpath-builder/workflows/CI/badge.svg)

## example

```kotlin
println(xpath {
    descendantOrSelf {
        "div"(attributes = mapOf("id" to "thing")) {
            +"sdfg"
        }
    }
})
```
outputs `//div[@id='thing' and normalize-space(.)='sdfg']`
