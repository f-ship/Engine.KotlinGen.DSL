package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class For(
    val statement: String,
    val block: For.() -> Unit,
    override val name: String = "For",
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Container(), Child {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this.apply { block() }
}
