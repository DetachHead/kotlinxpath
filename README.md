# kotlinxpath
A Kotlin typesafe builder for xpath with helpful shortcuts for handling the pain points, such as case sensitivity, quote escaping and HTML-specific helper functions for things like class checking.

![CI](https://github.com/DetachHead/xpath-builder/workflows/CI/badge.svg)

## Example
```kotlin
assertEquals(
    "descendant-or-self::node()/child::div[attribute::id = '1']/child::span",
    xpath { any / div[{ attr("id") equal "1" }] / span }.toString()
)
```
## features
### helpers
this library includes several useful shortcuts to help with common patterns.
#### examples
##### textIs
converts all text to lowercase, automatically handles quote escaping and trims any whitespace
```kotlin
assertEquals(
    "descendant-or-self::node()/child::*[translate(normalize-space(self::node()),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = concat('\"That',\"'\",'s mine\", he said.')]",
    xpath {
        anyNode[textIs("\"That's mine\", he said.")]
    }.toString()
)
```
##### classContains
checks whether the element has the given class
```kotlin
assertEquals(
    "descendant-or-self::node()/child::*[contains(concat(' ',attribute::class,' '),' foo ')]",
    xpath { anyNode[hasClass("foo")] }.toString()
)
```
### shims
most implementations of xpath still use xpath 1.0 (including all the modern browsers), but xpath 2 and 3 have some useful functions that aren't available in xpath 1. this library contains some shims for newer functions that compile to xpath 1

note: there's only a couple shims in here at the moment. intending to add more in the future.
#### examples
##### lower-case
```kotlin
assertEquals(
    "translate('Foo','ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = 'foo'",
    expression { `lower-case`("Foo") equal "foo" }.toString()
)
```