/**
 * logical operators used for xpath attributes //TODO: implement option to specify which operator to use
 */
enum class logicalOperator(val value: String) {
    and("and"),
    or("or")
}

/**
 * xpath typesafe builder. constructor takes either a string or an extension function. see tests for eggs
 */
class xpath(block: xpath.() -> Unit) {
    constructor(str: String) : this({ str() })

    private var string = ""

    //constants:
    val descendantOrSelf =
        "//" //TODO: less verbose name. this is pretty common so maybe remove the need for calling it somehow, or use an operator
    val self = "./"

    init {
        block()
    }

    override fun toString() = string

    /**
     * overrides the / operator on a string to add an element to an xpath
     * eg. `"div" / "p"``
     */
    operator fun String.div(xpath: String): String {
        return appendElement(this, xpath)
    }

    /**
     * adds an element with [attributes] to the xpath string
     */
    operator fun String.invoke(
        vararg attributes: Pair<String, String>,
        text: String?,
        block: (xpath.() -> Unit)? = null
    ): String {
        //TODO: less icky construction of attributes
        //this constructs a [] thingy in xpath containing either attributes or other xpath expressions.
        //eg. "//a[./h3[@class='asdf'] and @href='https://blah']"
        val attributesMap = mutableMapOf(*attributes)
        if (text != null)
            attributesMap["."] = text
        //concatenates multiple attributes using the specified logical operator
        //then adds any child xpath expressions (assumes they start with self (./))
        string =
            "$this[${
                addAttributes(
                    attributesMap,
                    logicalOperator.and
                )
            }${if (block != null) self + xpath { block() }.toString() else ""}]"
        return string
    }

    //bunch of overloads for flexibility when invoking. kinda cringe but w/e
    operator fun String.invoke(vararg attributes: Pair<String, String>) =
        invoke(attributes = *attributes, text = null, block = null)

    operator fun String.invoke(vararg attributes: Pair<String, String>, text: String) =
        invoke(attributes = *attributes, text = text, block = null)

    operator fun String.invoke(text: String, block: xpath.() -> Unit) =
        invoke(attributes = *arrayOf(), text = text, block = block)

    operator fun String.invoke(text: String) =
        invoke(attributes = *arrayOf(), text = text, block = null)

    operator fun String.invoke(block: xpath.() -> Unit) =
        invoke(attributes = *arrayOf(), text = null, block = block)

    operator fun String.invoke(index: Int): String {
        return "$this[$index]"
    }

    /**
     * takes a [Map] of [String]s and converts it into attributes for an [xpath] as a [String]
     * @return null if no attributes are passed
     */
    private fun addAttributes(attributes: Map<String, String>?, operator: logicalOperator): String? {
        if (attributes == null) return null
        val result = attributes.map {
            //TODO: support for values with single quotes
            val value = "'${it.value}'"
            if (it.key == ".")
                lowercase(function("normalize-space", ".")) + "=" + value.toLowerCase()
            else
                "@${it.key}=$value"
        }
        return result.joinToString(" " + operator.value + " ")
    }

    /**
     * returns an xpath function call as a string
     */
    private fun function(name: String, vararg args: String): String {
        return "$name(${args.joinToString()})"
    }

    /**
     * takes a string and returns a translated xpath function call to standardize it in lowercase
     */
    private fun lowercase(string: String): String {
        return function("translate", string, "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'", "'abcdefghijklmnopqrstuvwxyz'")
    }

    /**
     * appends a new element to the xpath
     */
    private fun appendElement(parent: String, child: String): String {
        string = if (parent.endsWith('/'))
            parent + child
        else
            "$parent/$child"
        return string
    }
}