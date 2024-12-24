package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Unspecified
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class AssignedVal<T : Any?>(
    override val name: String,
    val type: ValTypedValue<T>? = null,
    val getter: TypedBlock<T>? = null,
    val visibility: Visibility = Unspecified,
    override val id: Uuid = Uuid.random(),
    override val children: MutableList<Code> = mutableListOf(),
) : Value<T>(), Child {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this
}
