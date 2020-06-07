import kotlin.test.Test
import kotlin.test.assertEquals

class test {
    @Test
    fun test1() {
        assertEquals("//div[@id='thing' and normalize-space(.)='sdfg']", xpath {
            descendantOrSelf {
                div(attributes = mapOf("id" to "thing")) {
                    +"sdfg"
                }
            }
        }.toString())
    }
}