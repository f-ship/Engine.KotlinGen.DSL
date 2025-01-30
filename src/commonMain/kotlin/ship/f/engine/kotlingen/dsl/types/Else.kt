package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Else<R>(
    override val name: String, // will remove,
    val returnType: TypedString<R>,
) : Container(), Child {

}