package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Unspecified

data class AssignedVal<T : Any?>(
    override val name: String,
    val type: TypedString<T>? = null,
    val typedBlock: TypedBlock<T>? = null,
    val visibility: Visibility = Unspecified,
) : Value<T>(), Child {

}
