package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class TypedBlock<T : Any?>(
    var returnValue: ValTypedValue<T>? = null,
    val block: TypedBlock<T>.() -> ValTypedValue<T> = { ValTypedValue() }, //May not make sense
    val args: List<Bundle<out Any, out Any, out Any>> = listOf(),
    val definition: String = "{",
    override val name: String = returnValue?.type ?: "TypedBlock",
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Container(), Child {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this.apply { returnValue = block.invoke(this) }

    infix fun TypedBlock<T>.assign(t: ValTypedValue<T>) = t.apply { returnValue = this }
}
