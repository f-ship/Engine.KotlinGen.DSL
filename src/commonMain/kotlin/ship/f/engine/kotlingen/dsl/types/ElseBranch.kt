package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ElseBranch<R>(
    var returnValue: ValTypedValue<R>? = null,
    val block: ElseBranch<R>.() -> ValTypedValue<R>,
    val previous: If,
    override val name: String = "ElseBranch", // will remove,
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Else() {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this.apply { returnValue = block() }
}
