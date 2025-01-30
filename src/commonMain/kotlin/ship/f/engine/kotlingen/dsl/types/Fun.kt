package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Fun<R : Any?>(
    override val name: String,
    val returnType: TypedString<R>? = null,
) : Container(), Child {

}