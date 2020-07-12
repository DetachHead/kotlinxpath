# xpath-builder
kotlin typesafe builder for xpath that automatically handles annoying shit like case sensitivity and quote escaping

![CI](https://github.com/DetachHead/xpath-builder/workflows/CI/badge.svg)

## example

```kotlin
println(xpath {
    descendantOrSelf / div("id" to "thing", text = "asdf\"sdfg'zxcv")
})
```
outputs `//div[@id='thing' and translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')=concat('asdf"sdfg',"'",'zxcv')]`
