import components.xpath
import functions.position
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun attributeAndChild() =
        assertEquals(
            "descendant-or-self::node()/child::div[attribute::id = '1']/child::span",
            xpath { any / div[{ attr("id") equal "1" }] / span }.toString()
        )

    @Test
    fun indexTest() =
        assertEquals(
            "descendant-or-self::node()/child::div[position() = '1']", xpath { any / div[1] }.toString()
        )

    @Test
    fun innertext() =
        assertEquals(
            "descendant-or-self::node()/child::div[attribute::id = 'thing' and translate(normalize-space(self::node()),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = 'sdfg']",
            xpath {
                any / div[{ attr("id") equal "thing" and textIs("sdfg") }]
            }.toString()
        )

    @Test
    fun child() =
        assertEquals(
            "descendant-or-self::node()/child::div/child::p[attribute::class = 'asdf']", xpath {
                any / div / p[{ attr("class") equal "asdf" }]
            }.toString()
        )

    @Test
    fun nested() =
        assertEquals(
            "descendant-or-self::node()/child::div[self::node()/child::div[translate(normalize-space(self::node()),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = 'asdf']]/child::div/child::p[position() = '1']",
            xpath {
                any / div[self / div[textIs("asdf")]] / div / p[1]
            }.toString()
        )

    @Test
    fun escapequotes() =
        //https://stackoverflow.com/questions/14822153/escape-single-quote-in-xpath-with-nokogiri
        assertEquals(
            "descendant-or-self::node()/child::*[translate(normalize-space(self::node()),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = concat('\"That',\"'\",'s mine\", he said.')]",
            xpath {
                anyNode[textIs("\"That's mine\", he said.")]
            }.toString()
        )

    @Test
    fun stringNodeTest() =
        assertEquals(
            "descendant-or-self::node()/child::asdf[attribute::id = 'thing' and translate(normalize-space(self::node()),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = 'sdfg']",
            xpath {
                any / "asdf"[{ attr("id") equal "thing" and textIs("sdfg") }]
            }.toString()
        )

    @Test
    fun twoStringNodeTests() =
        assertEquals(
            "descendant-or-self::node()/child::div/child::p[attribute::class = 'asdf']",
            xpath {
                any / "div" / "p"[{ attr("class") equal "asdf" }]
            }.toString()
        )

    @Test
    fun nestedstring() =
        assertEquals(
            "descendant-or-self::node()/child::div[self::node()/child::div[translate(normalize-space(self::node()),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = 'asdf']]/child::div/child::p[position() = '1']",
            xpath {
                //TODO: figure out a way to add the index predicate shortcut to strings (gets shadowed by the default implementation)
                //TODO: fix inconsistency with predicates, some of them require blocks and others dont. and messing them up causes a runtime error
                any / "div"[self / "div"[textIs("asdf")]] / "div" / "p"[{ position() equal "1" }]
            }.toString()
        )
}