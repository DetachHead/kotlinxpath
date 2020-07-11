import kotlin.test.Test
import kotlin.test.assertEquals

class test {
    @Test
    fun innertext() =
        assertEquals(
            "//div[@id='thing' and translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='sdfg']",
            xpath {
                descendantOrSelf / "div"("id" to "thing", text = "sdfg")
            }.toString()
        )

    @Test
    fun child() =
        assertEquals(
            "//div/p[@class='asdf']", xpath {
                descendantOrSelf / "div" / "p"("class" to "asdf")
            }.toString()
        )

    @Test
    fun nested() =
        assertEquals(
            "//div[./div[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='asdf']]/div/p[1]",
            xpath {
                descendantOrSelf / "div" { "div"("Asdf") } / "div" / "p"(1)
            }.toString()
        )

    @Test
    fun escapequotes() =
        //https://stackoverflow.com/questions/14822153/escape-single-quote-in-xpath-with-nokogiri
        assertEquals(
            "//*[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')=concat('\"that',\"'\",'s mine\", he said.')]",
            xpath {
                descendantOrSelf / "*"("\"That's mine\", he said.")
            }.toString()
        )
}