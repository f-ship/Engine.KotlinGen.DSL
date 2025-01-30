package ship.f.engine.kotlingen.dsl.types

import kotlin.jvm.JvmInline

data class EntireFile(
    val names: String
) : KotlinFile() {
    private var shouldShowChild = true
    var block: EntireFile.() -> Unit = {
        println("not implemented")
    }

    val add
        get() = this.apply { shouldShowChild = true }

    val define
        get() = this.apply { shouldShowChild = false }
}

@JvmInline
value class KB(val a: String) {

}

fun a() {
    val kb = KB("a")
}