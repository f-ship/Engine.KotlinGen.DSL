package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Unspecified
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class AssignedVal<T : Any?> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    val type: TypedValue<T>? = null,
    val getter: TypedBlock<T>? = null,
    val visibility: Visibility = Unspecified,
    override val id: Uuid = Uuid.random(),
    override var children: List<Code> = listOf(),
) : Value<T>(), Child {

}
