package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class While @OptIn(ExperimentalUuidApi::class) constructor(
    val statement: TypedValue<Boolean>,
    val block: While.() -> Unit,
    override val name: String = "While",
    override val id: Uuid = Uuid.random(),
    override var children: List<Code> = listOf(),
) : Container(), Child {
    fun execute() {
        block()
    }
}
