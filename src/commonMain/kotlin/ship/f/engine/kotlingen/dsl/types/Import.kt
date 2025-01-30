package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Import(
    override val name: String,
) : Code(), Child {

}