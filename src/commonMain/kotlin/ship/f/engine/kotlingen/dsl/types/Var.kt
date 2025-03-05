package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Unspecified
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Var<T : Any?> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    val type: TypedValue<T>? = null,
    val delegate: TypedBlock<T>? = null, // not sure what to do with this to be honest
    val visibility: Visibility = Unspecified,
    val getter: TypedBlock<T>? = null,
    val setter: TypedBlock<T>? = null,
    override val id: Uuid = Uuid.random(),
) : Value<T>(), Child
