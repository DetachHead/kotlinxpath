import kotlin.test.Test
import kotlin.test.assertEquals

class test {
    @Test
    fun test1() {
        assertEquals(
            "//div[@id='thing' and translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='sdfg']",
            xpath {
                descendantOrSelf / "div"("id" to "thing", text = "sdfg")
            }.toString()
        )
    }

    @Test
    fun test2() {
        assertEquals("//div/p[@class='asdf']", xpath {
            descendantOrSelf / "div" / "p"("class" to "asdf")
        }.toString())
    }

    @Test
    fun test3() {
        assertEquals(
            "//div[./div[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='asdf']]/div/p[1]",
            xpath {
                descendantOrSelf / "div" { "div"("Asdf") } / "div" / "p"(1)
            }.toString()
        )
    }
}