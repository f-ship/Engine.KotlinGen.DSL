package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
@OptIn(ExperimentalUuidApi::class)
data class When<T,R>(
    override val name: String,
    val arg: ValTypedValue<T>? = null,
    val block: When<T,R>.(ValTypedValue<T>?) -> WhenBranch<T,R>,
    val returnType: ValTypedValue<R>? = null,
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Container(), Child {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }

    fun execute() {
        block(this, arg)
    }

    operator fun String.invoke(block: WhenBranch<T,R>.(ValTypedValue<T>?) -> ValTypedValue<R>): WhenBranch<T,R> {
        return WhenBranch(name = this, block = block, arg = arg).also { children.add(it) }
    }
}
