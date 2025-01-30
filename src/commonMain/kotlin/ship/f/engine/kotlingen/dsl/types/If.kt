package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class If<R>(
    val statement: String,
    val returnType: TypedString<R>,
    override val name: String = "If"
) : Container(), Child {

}
