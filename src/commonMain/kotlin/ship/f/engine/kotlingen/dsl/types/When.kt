package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class When<T,R> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    val block: When<T,R>.(T) -> R,
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Container(), Child {
    operator fun String.invoke(block: When<T,R>.() -> TypedValue<R>) = block(this@When)
}
