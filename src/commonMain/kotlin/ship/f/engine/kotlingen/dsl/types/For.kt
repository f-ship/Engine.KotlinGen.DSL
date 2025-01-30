package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class For(
    override val name: String // will remove
) : Container(), Child {

}
