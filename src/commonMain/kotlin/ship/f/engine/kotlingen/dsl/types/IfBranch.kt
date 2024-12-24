package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class IfBranch<R>(
    val statement: ValTypedValue<Boolean>,
    var returnValue: ValTypedValue<R>? = null,
    val block: IfBranch<R>.() -> ValTypedValue<R>, //May not make sense
    override val name: String = "IfBranch",
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : If() {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this.apply { returnValue = block() }
}
