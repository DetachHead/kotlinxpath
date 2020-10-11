package io.github.detachhead.kotlinxpath.components

import io.github.detachhead.kotlinxpath.components.LocationPathBuilder
import kotlin.js.JsName
import kotlin.reflect.KProperty

@DslMarker
internal annotation class NodeTestMarker

/**
 * an xpath node test. more info [here](https://en.wikipedia.org/wiki/XPath#Node_tests)
 */
public open class NodeTest(public open val value: String) {
    override fun toString(): String = value
}

/** creates a [NodeTest] using the name of the property */
private class NodeTestDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): NodeTest = NodeTest(property.name)
}

/** creates a [NodeTest] that's a function call (ie. appends `()` to the end of it) */
private fun nodeTestFunction(value: String) = NodeTest("$value()")

/** creates a [NodeTest] function with [nodeTestFunction] using the name of the property */
private class NodeTestFunctionDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): () -> NodeTest =
        { nodeTestFunction(property.name) }
}

//constants for HTML node tests (https://developer.mozilla.org/en-US/docs/Web/HTML/Element):
@NodeTestMarker
public val LocationPathBuilder.base: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.head: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.link: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.meta: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.style: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.title: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.body: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.address: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.article: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.aside: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.footer: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.header: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.h1: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.h2: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.h3: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.h4: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.h5: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.h6: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.hgroup: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.main: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.nav: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.section: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.blockquote: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.dd: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.div: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.dl: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.dt: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.figcaption: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.figure: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.hr: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.li: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.ol: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.p: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.ul: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.a: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.abbr: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.b: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.bdi: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.bdo: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.br: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.cite: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.code: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.data: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.dfn: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.em: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.i: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.kbd: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.mark: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.q: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.rb: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.rp: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.rt: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.rtc: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.ruby: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.s: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.samp: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.small: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.span: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.strong: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.sub: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.sup: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.time: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.u: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.`var`: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.wbr: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.area: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.audio: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.img: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.map: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.track: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.video: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.embed: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.iframe: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.`object`: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.param: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.picture: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.source: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.canvas: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.noscript: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.script: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.del: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.ins: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.caption: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.col: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.colgroup: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.table: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.tbody: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.td: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.tfoot: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.th: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.thead: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.tr: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.button: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.datalist: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.fieldset: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.form: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.input: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.label: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.legend: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.meter: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.optgroup: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.option: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.output: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.progress: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.select: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.textarea: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.details: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.dialog: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.menu: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.summary: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.slot: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.template: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.acronym: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.applet: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.basefont: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.bgsound: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.big: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.blink: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.center: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.command: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.content: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.dir: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.element: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.font: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.frame: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.frameset: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.image: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.isindex: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.keygen: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.listing: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.marquee: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.menuitem: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.multicol: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.nextid: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.nobr: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.noembed: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.noframes: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.plaintext: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.shadow: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.spacer: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.strike: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.tt: NodeTest by NodeTestDelegate()
@NodeTestMarker
public val LocationPathBuilder.xmp: NodeTest by NodeTestDelegate()

//node test functions
@NodeTestMarker
public val LocationPathBuilder.comment: () -> NodeTest by NodeTestFunctionDelegate()
@NodeTestMarker
public val LocationPathBuilder.text: () -> NodeTest by NodeTestFunctionDelegate()

@JsName("processing_instruction") //JsName is banned on properties for some reason
public fun LocationPathBuilder.`processing-instruction`(): NodeTest = nodeTestFunction("processing-instruction")
@NodeTestMarker
public val LocationPathBuilder.node: () -> NodeTest by NodeTestFunctionDelegate()