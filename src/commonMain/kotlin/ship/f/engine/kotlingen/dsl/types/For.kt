package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class For @OptIn(ExperimentalUuidApi::class) constructor(
    val statement: String,
    val block: For.() -> Unit,
    override val name: String = "For",
    override val id: Uuid = Uuid.random(),
    override var children: List<Code> = listOf(),
) : Container(), Child {
    fun execute() {
        block()
    }
}
