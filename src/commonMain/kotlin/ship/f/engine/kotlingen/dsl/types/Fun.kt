package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Fun<R : Any?> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    val returnType: TypedValue<R>? = null,
    override val id: Uuid = Uuid.random(),
    override var children: List<Code> = listOf(),
) : Container(), Child {

}