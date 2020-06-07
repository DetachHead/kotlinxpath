# xpath-builder
kotlin typesafe builder for xpath

## example

```kotlin
println(xpath {
    descendantOrSelf {
        div(attributes = mapOf("id" to "thing")) {
            +"sdfg"
        }
    }
})
```
outputs `//div[@id='thing' and normalize-space(.)='sdfg']`
