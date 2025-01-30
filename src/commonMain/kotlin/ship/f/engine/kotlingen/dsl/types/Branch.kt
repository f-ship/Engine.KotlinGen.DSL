package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class Branch(
    val arg: String? = null, // May turn into a typed string, in fact the Branch could be much more sophisticated in general
    override val name: String = "Branch"
) : Container(), Child {

}
