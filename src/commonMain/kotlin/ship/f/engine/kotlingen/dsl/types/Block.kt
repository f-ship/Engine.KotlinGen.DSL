package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Block(
    override val name: String, // remove at some point
) : Code(), Child {

}