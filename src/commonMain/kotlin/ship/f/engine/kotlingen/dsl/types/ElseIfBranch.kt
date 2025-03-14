package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ElseIfBranch<R>(
    val statement: ValTypedValue<Boolean>,
    var returnValue: ValTypedValue<R>? = null,
    val block: ElseIfBranch<R>.() -> ValTypedValue<R>, //May not make sense
    val previous: If,
    override val name: String = "ElseIfBranch",
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : If() {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this.apply { returnValue = block() }
}
