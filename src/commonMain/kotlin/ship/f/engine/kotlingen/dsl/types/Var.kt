package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Unspecified

data class Var<T : Any?>(
    override val name: String,
    val type: TypedString<T>? = null,
    val typedBlock: TypedBlock<T>? = null, // not sure what to do with this to be honest
    val visibility: Visibility = Unspecified,
    val getter: TypedBlock<T>? = null,
    val setter: TypedBlock<T>? = null,
) : Value<T>(), Child {

}
