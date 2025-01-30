package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class TypeAlias<T>(
    override val name: String,
    var type: TypedString<T>? = null,
    var simpleType: String? = null,
) : Code(), Child {

}
