/**
 * disclaimer. this is very messy, and there's probably a lot wrong with it.
 * this is currently just a project im working on to improve my skills, mainly because typesafe builders are really fascinating to me :)
 */

/**
 * dsl marker to differentiate xpath syntax consts (eg. //, *, ./) from element consts
 */
@DslMarker
annotation class xpathSyntaxDSL

typealias xpathattribute = Pair<String, String>

/**
 * logical operators used for xpath attributes //TODO: implement option to specify which operator to use
 */
enum class logicalOperator(val value: String) {
    and("and"),
    or("or")
}

/**
 * xpath typesafe builder. constructor takes either a string or an extension function.
 * TODO: figure out how to separate the different components of this into their own files or something
 */
class xpath(block: xpath.() -> Unit) {
    constructor(str: String) : this({ xpathcodesegment(str)() })

    private var string = ""

    override fun toString() = string

    /**
     * represents an xpath "thingy" such as an element, or a //, *, etc. (TODO: better names and description for these, idk what theyre actually called)
     */
    open class xpathcodesegment(val value: String) {
        override fun toString() = value
    }

    /**
     * an xpath axis specifier or something like that (eg. such as //, *, ./, etc)
     */
    class xpathsyntax(value: String) : xpathcodesegment(value)

    /**
     * an xml/html element (eg. div, p, h1)
     */
    class xpathelement(value: String) : xpathcodesegment(value)

    /**
     * overrides the / operator on a string to add an element to an xpath
     * eg. `"div" / "p"``
     */
    operator fun xpathcodesegment.div(xpath: String): xpathcodesegment = appendElement(this.toString(), xpath)
    operator fun xpathcodesegment.div(xpath: xpathcodesegment): xpathcodesegment = this / xpath.toString()
    operator fun xpathcodesegment.div(xpath: xpath): xpathcodesegment = this / xpath.toString()

    /**
     * adds an element with [attributes] to the xpath string
     */
    operator fun xpathcodesegment.invoke(
        vararg attributes: xpathattribute,
        text: String? = null,
        block: (xpath.() -> Unit)? = null
    ): String {
        val attributesSet = attributes.toMutableSet()
        //constructs a Predicate (the expression in square brackets []) in the xpath containing either attributes or other xpath expressions.
        //eg. "//a[./h3[@class='asdf'] and @href='https://blah']"
        if (text != null) attributesSet.add(self.toString() to text)
        val attributesMap = attributesSet.toMap()
        //concatenates multiple attributes using the specified logical operator
        //then adds any child xpath expressions (assumes they start with self (./))
        string =
            "$this[${
                addAttributes(
                    attributesMap,
                    logicalOperator.and
                )
            }${if (block != null) xpath { self / xpath { block() } }.toString() else ""}]"
        return string
    }

    /**
     * [xpathcodesegment.invoke] overloads for invocation flexibility. this is probs cringe but idk how to do it better
     */
    @Suppress("REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION")
    operator fun xpathcodesegment.invoke(text: String, block: (xpath.() -> Unit)? = null) =
        invoke(attributes = *arrayOf(), text = text, block = block)

    operator fun xpathcodesegment.invoke(index: Int): String = "$this[$index]"

    /**
     * [xpathsyntax.invoke] overloads. has the same functionality as the [xpathcodesegment.invoke] ones except with the [xpathSyntaxDSL] dsl annotation
     */
    @Suppress("REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION")
    @xpathSyntaxDSL
    operator fun xpathsyntax.invoke(
        vararg attributes: xpathattribute,
        text: String? = null,
        block: (xpath.() -> Unit)? = null
    ) = (this as xpathcodesegment).invoke(attributes = *attributes, text = text, block = block)

    @Suppress("REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION")
    @xpathSyntaxDSL
    operator fun xpathsyntax.invoke(text: String, block: (xpath.() -> Unit)? = null) =
        invoke(attributes = *arrayOf(), text = text, block = block)

    @xpathSyntaxDSL
    operator fun xpathsyntax.invoke(index: Int): String = "$this[$index]"

    /**
     * string overloads in case no [xpathelement] is defined for a specific element
     */
    @Suppress("REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION")
    operator fun String.invoke(
        vararg attributes: xpathattribute,
        text: String? = null,
        block: (xpath.() -> Unit)? = null
    ) = xpathcodesegment(this).invoke(attributes = *attributes, text = text, block = block)

    @Suppress("REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION")
    operator fun String.invoke(text: String, block: (xpath.() -> Unit)? = null) =
        invoke(attributes = *arrayOf(), text = text, block = block)

    operator fun String.invoke(index: Int): String = "$this[$index]"

    /**
     * takes a [Map] of [String]s and converts it into attributes for an [xpath] as a [String]
     * @return null if no attributes are passed
     */
    private fun addAttributes(attributes: Map<String, String>?, operator: logicalOperator): String? {
        if (attributes == null) return null
        val result = attributes.map {
            //xpath has no real way of escaping, so use various ways to esc values with quotes.
            //https://stackoverflow.com/questions/14822153/escape-single-quote-in-xpath-with-nokogiri
            val value = when {
                it.value.contains("'") && it.value.contains("\"") ->
                    "concat('${it.value.replace("'", "',\"'\",'")}')"
                it.value.contains("'") -> "\"${it.value}\""
                else -> "'${it.value}'"
            }
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
    private fun function(name: String, vararg args: String): String = "$name(${args.joinToString()})"

    /**
     * takes a string and returns a translated xpath function call to standardize it in lowercase
     */
    private fun lowercase(string: String): String =
        function("translate", string, "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'", "'abcdefghijklmnopqrstuvwxyz'")

    /**
     * appends a new element to the xpath
     */
    private fun appendElement(parent: String, child: String): xpathcodesegment {
        string = if (parent.endsWith('/'))
            parent + child
        else
            "$parent/$child"
        return xpathcodesegment(string)
    }

    //xpath syntax constants:
    @xpathSyntaxDSL
    val descendantOrSelf =
        xpathsyntax("//") //TODO: less verbose name. this is pretty common so maybe remove the need for calling it somehow, or use an operator

    @xpathSyntaxDSL
    val self = xpathsyntax(".")

    @xpathSyntaxDSL
    val any = xpathsyntax("*")

    @xpathSyntaxDSL
    val anything = xpathsyntax("$descendantOrSelf$any")

    //html element constants (https://developer.mozilla.org/en-US/docs/Web/HTML/Element):
    val base = xpathelement("base")
    val head = xpathelement("head")
    val link = xpathelement("link")
    val meta = xpathelement("meta")
    val style = xpathelement("style")
    val title = xpathelement("title")
    val body = xpathelement("body")
    val address = xpathelement("address")
    val article = xpathelement("article")
    val aside = xpathelement("aside")
    val footer = xpathelement("footer")
    val header = xpathelement("header")
    val h1 = xpathelement("h1")
    val h2 = xpathelement("h2")
    val h3 = xpathelement("h3")
    val h4 = xpathelement("h4")
    val h5 = xpathelement("h5")
    val h6 = xpathelement("h6")
    val hgroup = xpathelement("hgroup")
    val main = xpathelement("main")
    val nav = xpathelement("nav")
    val section = xpathelement("section")
    val blockquote = xpathelement("blockquote")
    val dd = xpathelement("dd")
    val div = xpathelement("div")
    val dl = xpathelement("dl")
    val dt = xpathelement("dt")
    val figcaption = xpathelement("figcaption")
    val figure = xpathelement("figure")
    val hr = xpathelement("hr")
    val li = xpathelement("li")
    val ol = xpathelement("ol")
    val p = xpathelement("p")
    val ul = xpathelement("ul")
    val a = xpathelement("a")
    val abbr = xpathelement("abbr")
    val b = xpathelement("b")
    val bdi = xpathelement("bdi")
    val bdo = xpathelement("bdo")
    val br = xpathelement("br")
    val cite = xpathelement("cite")
    val code = xpathelement("code")
    val data = xpathelement("data")
    val dfn = xpathelement("dfn")
    val em = xpathelement("em")
    val i = xpathelement("i")
    val kbd = xpathelement("kbd")
    val mark = xpathelement("mark")
    val q = xpathelement("q")
    val rb = xpathelement("rb")
    val rp = xpathelement("rp")
    val rt = xpathelement("rt")
    val rtc = xpathelement("rtc")
    val ruby = xpathelement("ruby")
    val s = xpathelement("s")
    val samp = xpathelement("samp")
    val small = xpathelement("small")
    val span = xpathelement("span")
    val strong = xpathelement("strong")
    val sub = xpathelement("sub")
    val sup = xpathelement("sup")
    val time = xpathelement("time")
    val u = xpathelement("u")
    val `var` = xpathelement("var")
    val wbr = xpathelement("span")
    val area = xpathelement("area")
    val audio = xpathelement("audio")
    val img = xpathelement("img")
    val map = xpathelement("map")
    val track = xpathelement("track")
    val video = xpathelement("video")
    val embed = xpathelement("embed")
    val iframe = xpathelement("iframe")
    val `object` = xpathelement("object")
    val param = xpathelement("param")
    val picture = xpathelement("picture")
    val source = xpathelement("source")
    val canvas = xpathelement("canvas")
    val noscript = xpathelement("noscript")
    val script = xpathelement("script")
    val del = xpathelement("del")
    val ins = xpathelement("ins")
    val caption = xpathelement("caption")
    val col = xpathelement("col")
    val colgroup = xpathelement("colgroup")
    val table = xpathelement("table")
    val tbody = xpathelement("tbody")
    val td = xpathelement("td")
    val tfoot = xpathelement("tfoot")
    val th = xpathelement("th")
    val thead = xpathelement("thead")
    val tr = xpathelement("tr")
    val button = xpathelement("button")
    val datalist = xpathelement("datalist")
    val fieldset = xpathelement("fieldset")
    val form = xpathelement("form")
    val input = xpathelement("input")
    val label = xpathelement("label")
    val legend = xpathelement("legend")
    val meter = xpathelement("meter")
    val optgroup = xpathelement("optgroup")
    val option = xpathelement("option")
    val output = xpathelement("output")
    val progress = xpathelement("progress")
    val select = xpathelement("select")
    val textarea = xpathelement("textarea")
    val details = xpathelement("details")
    val dialog = xpathelement("dialog")
    val menu = xpathelement("menu")
    val summary = xpathelement("summary")
    val slot = xpathelement("slot")
    val template = xpathelement("template")
    val acronym = xpathelement("acronym")
    val applet = xpathelement("applet")
    val basefont = xpathelement("basefont")
    val bgsound = xpathelement("bgsound")
    val big = xpathelement("big")
    val blink = xpathelement("blink")
    val center = xpathelement("center")
    val command = xpathelement("command")
    val content = xpathelement("content")
    val dir = xpathelement("dir")
    val element = xpathelement("element")
    val font = xpathelement("font")
    val frame = xpathelement("frame")
    val frameset = xpathelement("frameset")
    val image = xpathelement("image")
    val isindex = xpathelement("isindex")
    val keygen = xpathelement("keygen")
    val listing = xpathelement("listing")
    val marquee = xpathelement("marquee")
    val menuitem = xpathelement("menuitem")
    val multicol = xpathelement("multicol")
    val nextid = xpathelement("nextid")
    val nobr = xpathelement("nobr")
    val noembed = xpathelement("noembed")
    val noframes = xpathelement("noframes")
    val plaintext = xpathelement("plaintext")
    val shadow = xpathelement("shadow")
    val spacer = xpathelement("spacer")
    val strike = xpathelement("strike")
    val tt = xpathelement("tt")
    val xmp = xpathelement("xmp")

    init {
        block()
    }
}
