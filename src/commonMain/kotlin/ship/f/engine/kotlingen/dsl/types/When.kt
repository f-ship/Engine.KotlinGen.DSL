package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class When<R> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    val returnType: TypedValue<R>,
    override val id: Uuid = Uuid.random(),
    override var children: List<Code> = listOf(),
) : Container(), Child {
    operator fun String.invoke(block: When<R>.() -> TypedValue<R>) = block(this@When)
}
