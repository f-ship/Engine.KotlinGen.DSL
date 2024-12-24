package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class TypeAlias<T> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    var type: ValTypedValue<T>? = null,
    var simpleType: String? = null,
    override val id: Uuid = Uuid.random(),
) : Code(), Child {

}
