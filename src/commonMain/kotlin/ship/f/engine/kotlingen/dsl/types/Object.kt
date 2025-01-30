package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Object (
    override val name: String
) : Container(), Child {

}
