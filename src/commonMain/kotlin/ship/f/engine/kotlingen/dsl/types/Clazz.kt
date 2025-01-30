package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Clazz(
    override val name: String,
//    override var indent: Int,
) : Container(), Child {

}