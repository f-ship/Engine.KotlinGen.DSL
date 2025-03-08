package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Unspecified
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Val<T : Any?> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    val isNullable: Boolean = false,
    val type: TypedValue<T>? = null,
    val visibility: Visibility = Unspecified,
    val getter: TypedBlock<T>? = null,
    val delegate: TypedBlock<T>? = null,
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Value<T>(), Child
