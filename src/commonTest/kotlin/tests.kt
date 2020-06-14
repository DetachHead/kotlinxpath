import kotlin.test.Test
import kotlin.test.assertEquals

class test {
    @Test
    fun test1() {
        assertEquals("//div[@id='thing' and normalize-space(.)='sdfg']", xpath {
            descendantOrSelf / "div"("id" to "thing") / "sdfg"
        }.toString())
    }

    @Test
    fun test2() {
        assertEquals("//div/p[@class='asdf']", xpath {
            descendantOrSelf / "div" / "p"(mapOf("class" to "asdf"))
        }.toString())
    }
    @Test
    fun test3() {
        assertEquals("//div[./div[.='asdf']]/div/p[1]", xpath {
            descendantOrSelf / "div" {
                "div"("asdf")
            } / "div" / "p"(1)
        }.toString())
    }
}