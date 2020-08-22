/**
 * an xpath node test. more info [here](https://en.wikipedia.org/wiki/XPath#Node_tests)
 */
public class nodetest(public val value: String) {
    override fun toString(): String = value
}

//constants for HTML node tests (https://developer.mozilla.org/en-US/docs/Web/HTML/Element):
public val xpathbuilder.base: nodetest get() = nodetest("base")
public val xpathbuilder.head: nodetest get() = nodetest("head")
public val xpathbuilder.link: nodetest get() = nodetest("link")
public val xpathbuilder.meta: nodetest get() = nodetest("meta")
public val xpathbuilder.style: nodetest get() = nodetest("style")
public val xpathbuilder.title: nodetest get() = nodetest("title")
public val xpathbuilder.body: nodetest get() = nodetest("body")
public val xpathbuilder.address: nodetest get() = nodetest("address")
public val xpathbuilder.article: nodetest get() = nodetest("article")
public val xpathbuilder.aside: nodetest get() = nodetest("aside")
public val xpathbuilder.footer: nodetest get() = nodetest("footer")
public val xpathbuilder.header: nodetest get() = nodetest("header")
public val xpathbuilder.h1: nodetest get() = nodetest("h1")
public val xpathbuilder.h2: nodetest get() = nodetest("h2")
public val xpathbuilder.h3: nodetest get() = nodetest("h3")
public val xpathbuilder.h4: nodetest get() = nodetest("h4")
public val xpathbuilder.h5: nodetest get() = nodetest("h5")
public val xpathbuilder.h6: nodetest get() = nodetest("h6")
public val xpathbuilder.hgroup: nodetest get() = nodetest("hgroup")
public val xpathbuilder.main: nodetest get() = nodetest("main")
public val xpathbuilder.nav: nodetest get() = nodetest("nav")
public val xpathbuilder.section: nodetest get() = nodetest("section")
public val xpathbuilder.blockquote: nodetest get() = nodetest("blockquote")
public val xpathbuilder.dd: nodetest get() = nodetest("dd")
public val xpathbuilder.div: nodetest get() = nodetest("div")
public val xpathbuilder.dl: nodetest get() = nodetest("dl")
public val xpathbuilder.dt: nodetest get() = nodetest("dt")
public val xpathbuilder.figcaption: nodetest get() = nodetest("figcaption")
public val xpathbuilder.figure: nodetest get() = nodetest("figure")
public val xpathbuilder.hr: nodetest get() = nodetest("hr")
public val xpathbuilder.li: nodetest get() = nodetest("li")
public val xpathbuilder.ol: nodetest get() = nodetest("ol")
public val xpathbuilder.p: nodetest get() = nodetest("p")
public val xpathbuilder.ul: nodetest get() = nodetest("ul")
public val xpathbuilder.a: nodetest get() = nodetest("a")
public val xpathbuilder.abbr: nodetest get() = nodetest("abbr")
public val xpathbuilder.b: nodetest get() = nodetest("b")
public val xpathbuilder.bdi: nodetest get() = nodetest("bdi")
public val xpathbuilder.bdo: nodetest get() = nodetest("bdo")
public val xpathbuilder.br: nodetest get() = nodetest("br")
public val xpathbuilder.cite: nodetest get() = nodetest("cite")
public val xpathbuilder.code: nodetest get() = nodetest("code")
public val xpathbuilder.data: nodetest get() = nodetest("data")
public val xpathbuilder.dfn: nodetest get() = nodetest("dfn")
public val xpathbuilder.em: nodetest get() = nodetest("em")
public val xpathbuilder.i: nodetest get() = nodetest("i")
public val xpathbuilder.kbd: nodetest get() = nodetest("kbd")
public val xpathbuilder.mark: nodetest get() = nodetest("mark")
public val xpathbuilder.q: nodetest get() = nodetest("q")
public val xpathbuilder.rb: nodetest get() = nodetest("rb")
public val xpathbuilder.rp: nodetest get() = nodetest("rp")
public val xpathbuilder.rt: nodetest get() = nodetest("rt")
public val xpathbuilder.rtc: nodetest get() = nodetest("rtc")
public val xpathbuilder.ruby: nodetest get() = nodetest("ruby")
public val xpathbuilder.s: nodetest get() = nodetest("s")
public val xpathbuilder.samp: nodetest get() = nodetest("samp")
public val xpathbuilder.small: nodetest get() = nodetest("small")
public val xpathbuilder.span: nodetest get() = nodetest("span")
public val xpathbuilder.strong: nodetest get() = nodetest("strong")
public val xpathbuilder.sub: nodetest get() = nodetest("sub")
public val xpathbuilder.sup: nodetest get() = nodetest("sup")
public val xpathbuilder.time: nodetest get() = nodetest("time")
public val xpathbuilder.u: nodetest get() = nodetest("u")
public val xpathbuilder.`var`: nodetest get() = nodetest("var")
public val xpathbuilder.wbr: nodetest get() = nodetest("span")
public val xpathbuilder.area: nodetest get() = nodetest("area")
public val xpathbuilder.audio: nodetest get() = nodetest("audio")
public val xpathbuilder.img: nodetest get() = nodetest("img")
public val xpathbuilder.map: nodetest get() = nodetest("map")
public val xpathbuilder.track: nodetest get() = nodetest("track")
public val xpathbuilder.video: nodetest get() = nodetest("video")
public val xpathbuilder.embed: nodetest get() = nodetest("embed")
public val xpathbuilder.iframe: nodetest get() = nodetest("iframe")
public val xpathbuilder.`object`: nodetest get() = nodetest("object")
public val xpathbuilder.param: nodetest get() = nodetest("param")
public val xpathbuilder.picture: nodetest get() = nodetest("picture")
public val xpathbuilder.source: nodetest get() = nodetest("source")
public val xpathbuilder.canvas: nodetest get() = nodetest("canvas")
public val xpathbuilder.noscript: nodetest get() = nodetest("noscript")
public val xpathbuilder.script: nodetest get() = nodetest("script")
public val xpathbuilder.del: nodetest get() = nodetest("del")
public val xpathbuilder.ins: nodetest get() = nodetest("ins")
public val xpathbuilder.caption: nodetest get() = nodetest("caption")
public val xpathbuilder.col: nodetest get() = nodetest("col")
public val xpathbuilder.colgroup: nodetest get() = nodetest("colgroup")
public val xpathbuilder.table: nodetest get() = nodetest("table")
public val xpathbuilder.tbody: nodetest get() = nodetest("tbody")
public val xpathbuilder.td: nodetest get() = nodetest("td")
public val xpathbuilder.tfoot: nodetest get() = nodetest("tfoot")
public val xpathbuilder.th: nodetest get() = nodetest("th")
public val xpathbuilder.thead: nodetest get() = nodetest("thead")
public val xpathbuilder.tr: nodetest get() = nodetest("tr")
public val xpathbuilder.button: nodetest get() = nodetest("button")
public val xpathbuilder.datalist: nodetest get() = nodetest("datalist")
public val xpathbuilder.fieldset: nodetest get() = nodetest("fieldset")
public val xpathbuilder.form: nodetest get() = nodetest("form")
public val xpathbuilder.input: nodetest get() = nodetest("input")
public val xpathbuilder.label: nodetest get() = nodetest("label")
public val xpathbuilder.legend: nodetest get() = nodetest("legend")
public val xpathbuilder.meter: nodetest get() = nodetest("meter")
public val xpathbuilder.optgroup: nodetest get() = nodetest("optgroup")
public val xpathbuilder.option: nodetest get() = nodetest("option")
public val xpathbuilder.output: nodetest get() = nodetest("output")
public val xpathbuilder.progress: nodetest get() = nodetest("progress")
public val xpathbuilder.select: nodetest get() = nodetest("select")
public val xpathbuilder.textarea: nodetest get() = nodetest("textarea")
public val xpathbuilder.details: nodetest get() = nodetest("details")
public val xpathbuilder.dialog: nodetest get() = nodetest("dialog")
public val xpathbuilder.menu: nodetest get() = nodetest("menu")
public val xpathbuilder.summary: nodetest get() = nodetest("summary")
public val xpathbuilder.slot: nodetest get() = nodetest("slot")
public val xpathbuilder.template: nodetest get() = nodetest("template")
public val xpathbuilder.acronym: nodetest get() = nodetest("acronym")
public val xpathbuilder.applet: nodetest get() = nodetest("applet")
public val xpathbuilder.basefont: nodetest get() = nodetest("basefont")
public val xpathbuilder.bgsound: nodetest get() = nodetest("bgsound")
public val xpathbuilder.big: nodetest get() = nodetest("big")
public val xpathbuilder.blink: nodetest get() = nodetest("blink")
public val xpathbuilder.center: nodetest get() = nodetest("center")
public val xpathbuilder.command: nodetest get() = nodetest("command")
public val xpathbuilder.content: nodetest get() = nodetest("content")
public val xpathbuilder.dir: nodetest get() = nodetest("dir")
public val xpathbuilder.element: nodetest get() = nodetest("element")
public val xpathbuilder.font: nodetest get() = nodetest("font")
public val xpathbuilder.frame: nodetest get() = nodetest("frame")
public val xpathbuilder.frameset: nodetest get() = nodetest("frameset")
public val xpathbuilder.image: nodetest get() = nodetest("image")
public val xpathbuilder.isindex: nodetest get() = nodetest("isindex")
public val xpathbuilder.keygen: nodetest get() = nodetest("keygen")
public val xpathbuilder.listing: nodetest get() = nodetest("listing")
public val xpathbuilder.marquee: nodetest get() = nodetest("marquee")
public val xpathbuilder.menuitem: nodetest get() = nodetest("menuitem")
public val xpathbuilder.multicol: nodetest get() = nodetest("multicol")
public val xpathbuilder.nextid: nodetest get() = nodetest("nextid")
public val xpathbuilder.nobr: nodetest get() = nodetest("nobr")
public val xpathbuilder.noembed: nodetest get() = nodetest("noembed")
public val xpathbuilder.noframes: nodetest get() = nodetest("noframes")
public val xpathbuilder.plaintext: nodetest get() = nodetest("plaintext")
public val xpathbuilder.shadow: nodetest get() = nodetest("shadow")
public val xpathbuilder.spacer: nodetest get() = nodetest("spacer")
public val xpathbuilder.strike: nodetest get() = nodetest("strike")
public val xpathbuilder.tt: nodetest get() = nodetest("tt")
public val xpathbuilder.xmp: nodetest get() = nodetest("xmp")