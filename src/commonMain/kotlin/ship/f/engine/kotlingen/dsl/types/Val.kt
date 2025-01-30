package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Unspecified

data class Val<T : Any?>(
    override val name: String,
    val isNullable: Boolean = false,
    val type: TypedString<T>? = null,
    val visibility: Visibility = Unspecified,
    val getter: TypedBlock<T>? = null,
) : Value<T>(), Child {

}