package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.implementations.ValDelegate
import ship.f.engine.kotlingen.dsl.implementations.ValInterface
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class WhenBranch<T, R>(
    override val name: String = "WhenBranch",
    override val id: Uuid = Uuid.random(),
    override val children: MutableList<Code> = mutableListOf(),
    val arg: ValTypedValue<T>? = null,
    val addChild: (Code) -> Unit = { code -> children.add(code) },
    val block: WhenBranch<T, R>.(ValTypedValue<T>?) -> ValTypedValue<R>,
    var returnValue: ValTypedValue<R>? = null,
) : Container(), Child,
    ValInterface by ValDelegate(children, addChild)  {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this.apply { returnValue = block(this, arg) }
}
