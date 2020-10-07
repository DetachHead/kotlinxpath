# xpath-builder
A Kotlin typesafe builder for xpath that automatically handles annoying issues, like case sensitivity and quote escaping.

![CI](https://github.com/DetachHead/xpath-builder/workflows/CI/badge.svg)

## Example

```kotlin
println(xpath {
    descendantOrSelf / div("id" to "thing", text = "asdf\"sdfg'zxcv")
})
```
Outputs `//div[@id='thing' and translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')=concat('asdf"sdfg',"'",'zxcv')]`

## Disclaimer
This project is in a very early state, and the current implementation is really shit. I am currently working on a better version in the rewrite branch.
