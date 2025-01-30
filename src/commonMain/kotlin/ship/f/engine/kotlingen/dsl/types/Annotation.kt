package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Annotation(
    override val name: String,
) : Container(), Child {

}
