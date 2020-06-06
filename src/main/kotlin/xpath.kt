class xpath(block: xpath.() -> Unit) {
    var string = ""

    init {
        block()
    }

    override fun toString() = string

    /**
     * aka //
     */
    fun descendantOrSelf(block: xpath.() -> Unit) {
        //todo: less verbose name. this is pretty common so maybe remove the need for calling it somehow, or use an operator
        append("/") //first slash is added by the appender (todo: cring)
        block()
    }

    /**
     * adds a div
     */
    fun div(attributes: Map<String, String>?, block: xpath.() -> Unit) {
        //TODO: more abstract handling for element so we dont need to make one of these for every element type
        append("div")
        addAttributes(attributes)
        block()
    }

    fun innerText(text: String) {
        addAttribute("." to text)
    }

    operator fun String.unaryPlus() = innerText(this)

    /**
     * adds attributes to an xpath. does nothing if attributes are null
     */
    private fun addAttributes(attributes: Map<String, String>?) {
        if (attributes == null) return
        //todo: less cringe
        //if we already have a [], append to it:
        if (string.endsWith(']')) {
            string = string.removeSuffix("]")
            string += " and "
        } else {
            string += '['
        }
        attributes.forEach {
            //todo: proper handling of xpath functions
            val key = if (it.key == ".")
                "normalize-space(.)"
            else
                "@${it.key}"
            string += "$key='${it.value}'"
        }
        string += ']'
    }

    private fun addAttribute(attribute: Pair<String, String>) = addAttributes(mapOf(attribute))

    /**
     * appends a new element to the xpath
     */
    private fun append(str: String) {
        if (!string.endsWith('/'))
            string += '/'
        string += str
    }
}

/**
 * egg
 */
fun main() {
    println(xpath {
        descendantOrSelf {
            div(attributes = mapOf("id" to "thing")) {
                +"sdfg"
            }
        }
    })
}