import kotlin.test.Test
import kotlin.test.assertEquals

class test {
    @Test
    fun test1() {
        assertEquals("//div[@id='thing' and normalize-space(.)='sdfg']", xpath {
            descendantOrSelf {
                "div"(attributes = mapOf("id" to "thing")) {
                    +"sdfg"
                }
            }
        }.toString())
    }

    @Test
    fun test2() {
        assertEquals("//div/p[@class='asdf']", xpath {
            descendantOrSelf {
                //TODO: fix wacky global xpath string. currently the first string needs to be invoked before the divider override because of the order it appends to the string
                "div"() / "p"(mapOf("class" to "asdf"))
            }
        }.toString())
    }
}