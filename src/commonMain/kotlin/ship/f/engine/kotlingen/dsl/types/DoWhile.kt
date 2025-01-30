package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class DoWhile(
    val statement: String,
    override val name: String = "DoWhile",
) : Container(), Child {

}
