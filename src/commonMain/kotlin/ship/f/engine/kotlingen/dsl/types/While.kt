package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class While(
    val statement: String,
    override val name: String = "While",
) : Container(), Child {

}
