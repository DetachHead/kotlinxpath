/** a builder class for [t] */
internal interface Buildable<t> {
    fun build(): t
}