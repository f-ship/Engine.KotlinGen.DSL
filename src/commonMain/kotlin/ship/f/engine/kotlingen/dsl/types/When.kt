package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
@OptIn(ExperimentalUuidApi::class)
data class When<T,R>(
    override val name: String,
    val arg: TypedValue<T>? = null,
    val block: When<T,R>.(TypedValue<T>?) -> WhenBranch<T,R>,
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Container(), Child {
    val add get() = this

    val define get() = this.apply { children.add(Define()) }

    fun execute() {
        block(this, arg)
    }

//    operator fun String.invoke(block: When<T,R>.(T) -> R): Bundle<When<T,R>, T, R> {
//        TODO()
//    }

    operator fun String.invoke(block: WhenBranch<T,R>.(TypedValue<T>?) -> TypedValue<R>): WhenBranch<T,R> {
        return WhenBranch(name = this, block = block, arg = arg).also { children.add(it) }
    }
//    operator fun String.invoke(block: When<T,R>.(T) -> R): String {
//        TODO()
//    }
}
