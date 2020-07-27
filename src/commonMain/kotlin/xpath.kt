/**
 * an xpath location step which is separated by `/`. https://en.wikipedia.org/wiki/XPath#Syntax_and_semantics_(XPath_1.0)
 */
class locationstep(
    val axis: Axis,
    val nodetest: nodetest,
    val predicates: List<predicate> = listOf()
) {
    override fun toString() = "$axis::$nodetest${predicates.joinToString("")}"
}

/**
 * an xpath expression made up of [locationstep]s
 */
class Xpath(val steps: List<locationstep>) {
    //TODO: handling for scenarios where you want the xpath to start with a single /
    override fun toString() = steps.joinToString("/")
}

/**
 * creates an [Xpath] using the [xpathbuilder] with the given [block]
 */
fun xpath(block: xpathbuilder.() -> Unit): Xpath = xpathbuilder().apply(block).build()

/**
 * typesafe builder for [Xpath]
 */
class xpathbuilder {
    var steps = mutableListOf<locationstep>()
    fun build() = Xpath(steps)

    /**
     * concatenates two [locationstep]s in xpath with the / operator
     */
    operator fun locationstep.div(other: locationstep) = steps.add(other)

    /**
     * concatenates a [locationstep] with a [nodetest] in xpath with the / operator, by creating a new [locationstep] with no [predicate]s
     */
    operator fun locationstep.div(other: nodetest) = steps.add(locationstep(Axis.child, other))

    /**
     * creates a [locationstep] with the given [Axis], [nodetest] and [predicate]s
     */
    operator fun Axis.invoke(node: nodetest, predicates: List<predicate> = listOf()) =
        locationstep(this, node, predicates).also { steps.add(it) }
}