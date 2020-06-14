//TODO: make it correctly store the string value. this doesnt work

class xpath(block: xpath.() -> Unit) {
    constructor(str: String) : this({ str() })

    private var string = ""

    init {
        block()
    }

    override fun toString() = string

    /**
     * aka //
     */
    val descendantOrSelf = "//"

    /**
     * overrides the / operator on a string to add an element to an xpath
     * eg. `"div" / "p"``
     */
    operator fun String.div(xpath: String): String {
        return "$this/$xpath"
    }

    /**
     * adds an element with [attributes] to the xpath string
     */
    operator fun String.invoke(attributes: Map<String, String>? = null, block: (xpath.() -> Unit)? = null): String {
        //TODO: less icky construction of attributes
        //this constructs a [] thingy in xpath containing either attributes or other xpath expressions.
        //eg. "//a[./h3[@class='asdf'] and @href='https://blah']"
        return "$this[${listOfNotNull(attributes, xpath {
            if (block != null) xpath { block() }
        }).joinToString(" and ")}]"
    }

    /**
     * adds an element with an [attribute] to the xpath string
     */
    operator fun String.invoke(attribute: Pair<String, String>, block: (xpath.() -> Unit)? = null): String {
        return invoke(mapOf(attribute), block)
    }

    /**
     * adds an element with the specified [innertext] to the xpath string
     */
    operator fun String.invoke(innertext: String): String {
        return addAttribute("." to innertext)
    }

    operator fun String.invoke(index: Int): String {
        return "$this[$index]"
    }

    /**
     * takes a [Map] of [String]s and converts it into attributes for an [xpath] as a [String]
     */
    private fun addAttributes(attributes: Map<String, String>?): String {
        var result = ""
        attributes?.forEach {
            if (it.key == ".")
                function("normalize-space", ".")
            else
                result += "@${it.key}"
            result += "='${it.value}'"
        }
        return result
    }

    /**
     * takes a [Pair] and converts it into attributes for an [xpath] as a [String]
     */
    private fun addAttribute(attribute: Pair<String, String>) = addAttributes(mapOf(attribute))

    /**
     * returns an xpath function call as a string
     */
    private fun function(name: String, vararg args: String): String {
        return "$name(${args.joinToString()})"
    }


    /**
     * appends a new element to the xpath
     */
    private fun appendElement(parent: String, child: String): String {
        return if (parent.endsWith('/'))
            parent + child
        else
            "$parent/$child"
    }
}