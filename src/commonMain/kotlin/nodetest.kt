/**
 * an  xpath node test https://en.wikipedia.org/wiki/XPath#Node_tests
 */
class nodetest(val value: String) {
    override fun toString() = value
}

//constants for HTML node tests (https://developer.mozilla.org/en-US/docs/Web/HTML/Element):
val xpathbuilder.base get() = nodetest("base")
val xpathbuilder.head get() = nodetest("head")
val xpathbuilder.link get() = nodetest("link")
val xpathbuilder.meta get() = nodetest("meta")
val xpathbuilder.style get() = nodetest("style")
val xpathbuilder.title get() = nodetest("title")
val xpathbuilder.body get() = nodetest("body")
val xpathbuilder.address get() = nodetest("address")
val xpathbuilder.article get() = nodetest("article")
val xpathbuilder.aside get() = nodetest("aside")
val xpathbuilder.footer get() = nodetest("footer")
val xpathbuilder.header get() = nodetest("header")
val xpathbuilder.h1 get() = nodetest("h1")
val xpathbuilder.h2 get() = nodetest("h2")
val xpathbuilder.h3 get() = nodetest("h3")
val xpathbuilder.h4 get() = nodetest("h4")
val xpathbuilder.h5 get() = nodetest("h5")
val xpathbuilder.h6 get() = nodetest("h6")
val xpathbuilder.hgroup get() = nodetest("hgroup")
val xpathbuilder.main get() = nodetest("main")
val xpathbuilder.nav get() = nodetest("nav")
val xpathbuilder.section get() = nodetest("section")
val xpathbuilder.blockquote get() = nodetest("blockquote")
val xpathbuilder.dd get() = nodetest("dd")
val xpathbuilder.div get() = nodetest("div")
val xpathbuilder.dl get() = nodetest("dl")
val xpathbuilder.dt get() = nodetest("dt")
val xpathbuilder.figcaption get() = nodetest("figcaption")
val xpathbuilder.figure get() = nodetest("figure")
val xpathbuilder.hr get() = nodetest("hr")
val xpathbuilder.li get() = nodetest("li")
val xpathbuilder.ol get() = nodetest("ol")
val xpathbuilder.p get() = nodetest("p")
val xpathbuilder.ul get() = nodetest("ul")
val xpathbuilder.a get() = nodetest("a")
val xpathbuilder.abbr get() = nodetest("abbr")
val xpathbuilder.b get() = nodetest("b")
val xpathbuilder.bdi get() = nodetest("bdi")
val xpathbuilder.bdo get() = nodetest("bdo")
val xpathbuilder.br get() = nodetest("br")
val xpathbuilder.cite get() = nodetest("cite")
val xpathbuilder.code get() = nodetest("code")
val xpathbuilder.data get() = nodetest("data")
val xpathbuilder.dfn get() = nodetest("dfn")
val xpathbuilder.em get() = nodetest("em")
val xpathbuilder.i get() = nodetest("i")
val xpathbuilder.kbd get() = nodetest("kbd")
val xpathbuilder.mark get() = nodetest("mark")
val xpathbuilder.q get() = nodetest("q")
val xpathbuilder.rb get() = nodetest("rb")
val xpathbuilder.rp get() = nodetest("rp")
val xpathbuilder.rt get() = nodetest("rt")
val xpathbuilder.rtc get() = nodetest("rtc")
val xpathbuilder.ruby get() = nodetest("ruby")
val xpathbuilder.s get() = nodetest("s")
val xpathbuilder.samp get() = nodetest("samp")
val xpathbuilder.small get() = nodetest("small")
val xpathbuilder.span get() = nodetest("span")
val xpathbuilder.strong get() = nodetest("strong")
val xpathbuilder.sub get() = nodetest("sub")
val xpathbuilder.sup get() = nodetest("sup")
val xpathbuilder.time get() = nodetest("time")
val xpathbuilder.u get() = nodetest("u")
val xpathbuilder.`var` get() = nodetest("var")
val xpathbuilder.wbr get() = nodetest("span")
val xpathbuilder.area get() = nodetest("area")
val xpathbuilder.audio get() = nodetest("audio")
val xpathbuilder.img get() = nodetest("img")
val xpathbuilder.map get() = nodetest("map")
val xpathbuilder.track get() = nodetest("track")
val xpathbuilder.video get() = nodetest("video")
val xpathbuilder.embed get() = nodetest("embed")
val xpathbuilder.iframe get() = nodetest("iframe")
val xpathbuilder.`object` get() = nodetest("object")
val xpathbuilder.param get() = nodetest("param")
val xpathbuilder.picture get() = nodetest("picture")
val xpathbuilder.source get() = nodetest("source")
val xpathbuilder.canvas get() = nodetest("canvas")
val xpathbuilder.noscript get() = nodetest("noscript")
val xpathbuilder.script get() = nodetest("script")
val xpathbuilder.del get() = nodetest("del")
val xpathbuilder.ins get() = nodetest("ins")
val xpathbuilder.caption get() = nodetest("caption")
val xpathbuilder.col get() = nodetest("col")
val xpathbuilder.colgroup get() = nodetest("colgroup")
val xpathbuilder.table get() = nodetest("table")
val xpathbuilder.tbody get() = nodetest("tbody")
val xpathbuilder.td get() = nodetest("td")
val xpathbuilder.tfoot get() = nodetest("tfoot")
val xpathbuilder.th get() = nodetest("th")
val xpathbuilder.thead get() = nodetest("thead")
val xpathbuilder.tr get() = nodetest("tr")
val xpathbuilder.button get() = nodetest("button")
val xpathbuilder.datalist get() = nodetest("datalist")
val xpathbuilder.fieldset get() = nodetest("fieldset")
val xpathbuilder.form get() = nodetest("form")
val xpathbuilder.input get() = nodetest("input")
val xpathbuilder.label get() = nodetest("label")
val xpathbuilder.legend get() = nodetest("legend")
val xpathbuilder.meter get() = nodetest("meter")
val xpathbuilder.optgroup get() = nodetest("optgroup")
val xpathbuilder.option get() = nodetest("option")
val xpathbuilder.output get() = nodetest("output")
val xpathbuilder.progress get() = nodetest("progress")
val xpathbuilder.select get() = nodetest("select")
val xpathbuilder.textarea get() = nodetest("textarea")
val xpathbuilder.details get() = nodetest("details")
val xpathbuilder.dialog get() = nodetest("dialog")
val xpathbuilder.menu get() = nodetest("menu")
val xpathbuilder.summary get() = nodetest("summary")
val xpathbuilder.slot get() = nodetest("slot")
val xpathbuilder.template get() = nodetest("template")
val xpathbuilder.acronym get() = nodetest("acronym")
val xpathbuilder.applet get() = nodetest("applet")
val xpathbuilder.basefont get() = nodetest("basefont")
val xpathbuilder.bgsound get() = nodetest("bgsound")
val xpathbuilder.big get() = nodetest("big")
val xpathbuilder.blink get() = nodetest("blink")
val xpathbuilder.center get() = nodetest("center")
val xpathbuilder.command get() = nodetest("command")
val xpathbuilder.content get() = nodetest("content")
val xpathbuilder.dir get() = nodetest("dir")
val xpathbuilder.element get() = nodetest("element")
val xpathbuilder.font get() = nodetest("font")
val xpathbuilder.frame get() = nodetest("frame")
val xpathbuilder.frameset get() = nodetest("frameset")
val xpathbuilder.image get() = nodetest("image")
val xpathbuilder.isindex get() = nodetest("isindex")
val xpathbuilder.keygen get() = nodetest("keygen")
val xpathbuilder.listing get() = nodetest("listing")
val xpathbuilder.marquee get() = nodetest("marquee")
val xpathbuilder.menuitem get() = nodetest("menuitem")
val xpathbuilder.multicol get() = nodetest("multicol")
val xpathbuilder.nextid get() = nodetest("nextid")
val xpathbuilder.nobr get() = nodetest("nobr")
val xpathbuilder.noembed get() = nodetest("noembed")
val xpathbuilder.noframes get() = nodetest("noframes")
val xpathbuilder.plaintext get() = nodetest("plaintext")
val xpathbuilder.shadow get() = nodetest("shadow")
val xpathbuilder.spacer get() = nodetest("spacer")
val xpathbuilder.strike get() = nodetest("strike")
val xpathbuilder.tt get() = nodetest("tt")
val xpathbuilder.xmp get() = nodetest("xmp")