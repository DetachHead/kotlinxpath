/**
 * splits a [String] with the given [delimiter], but adds that delimiter to the resulting [List]
 *
 * eg.
 * ```kotlin
 * "foo,bar,baz".splitKeep(",") == listOf("foo", ",", "bar", ",", "baz")
 * ```
 */
internal fun String.splitKeep(delimiter: String): List<String> = split(Regex("(?<=[$delimiter])|(?=[$delimiter])"))
