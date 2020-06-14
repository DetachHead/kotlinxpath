class xpath(block: xpath.() -> Unit) {
    var string = ""

    init {
        block()
    }

    override fun toString() = string

    /**
     * aka //
     */
    fun descendantOrSelf(block: xpath.() -> Unit): xpath {
        //TODO: less verbose name. this is pretty common so maybe remove the need for calling it somehow, or use an operator
        appendElement("/") //first slash is added by the appender (todo: cring)
        block()
        return this
    }

    /**
     * overrides the / operator on the xpath object to add an element to an xpath
     * eg. `xpath{...} / "p"``
     */
    operator fun div(xpath: xpath) {
        //currently does nothing, just for syntactic sugar when building the xpath
    }

    /**
     * overrides the / operator on a string to add an element to an xpath
     * eg. `"div" / "p"``
     */
    operator fun String.div(xpath: xpath) {
        this.invoke().toString() + xpath
    }

    /**
     * adds an element with attributes to the xpath string
     */
    operator fun String.invoke(attributes: Map<String, String>? = null, block: (xpath.() -> Unit)? = null): xpath {
        appendElement(this)
        addAttributes(attributes)
        if (block != null) block()
        return this@xpath
    }

    /**
     * adds innertext to an xpath
     */
    private fun innerText(text: String) {
        addAttribute("." to text)
    }

    /**
     * adds innertext to an xpath
     */
    operator fun String.unaryPlus(): Unit = innerText(this)

    /**
     * adds attributes to an [xpath]. does nothing if [attributes] are null
     */
    private fun addAttributes(attributes: Map<String, String>?) {
        if (attributes == null) return
        //TODO: this whole function is messy. should probably be done better
        //if we already have a [], append to it:
        if (string.endsWith(']')) {
            string = string.removeSuffix("]")
            string += " and "
        } else {
            string += '['
        }
        //if checking the innertext normalize space by default. TODO: case insensitivity by default (need to use translate function)
        attributes.forEach {
            if (it.key == ".")
                function("normalize-space", ".")
            else
                string += "@${it.key}"
            string += "='${it.value}'"
        }
        string += ']'
    }

    /**
     * adds a single attribute to an [xpath]. does nothing if [attribute] is null
     */
    private fun addAttribute(attribute: Pair<String, String>) = addAttributes(mapOf(attribute))

    /**
     * appends an xpath function call
     */
    private fun function(name: String, vararg args: String) {
        string += "$name(${args.joinToString()})"
    }


    /**
     * appends a new element to the xpath
     */
    private fun appendElement(str: String) {
        if (!string.endsWith('/'))
            string += '/'
        string += str
    }
}