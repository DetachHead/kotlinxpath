import io.github.detachhead.kotlinxpath.components.expression
import io.github.detachhead.kotlinxpath.functions.xpath1_shims.`lower-case`
import io.github.detachhead.kotlinxpath.functions.xpath1_shims.`upper-case`
import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertEquals

class ShimTests {
    @Test
    @JsName("lower_case")
    fun `lower-case`() =
        assertEquals(
            "translate('Foo','ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = 'foo'",
            expression { `lower-case`("Foo") equal "foo" }.toString()
        )
    @Test
    @JsName("upper_case")
    fun `upper-case`() =
        assertEquals(
            "translate('Foo','abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ') = 'foo'",
            expression { `upper-case`("Foo") equal "foo" }.toString()
        )
}