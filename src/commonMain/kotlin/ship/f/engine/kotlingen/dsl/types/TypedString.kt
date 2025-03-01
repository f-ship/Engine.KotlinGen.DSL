package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class TypedString<T : Any?>(
    val value: String? = null,
    val type: String? = null,
    val import: String? = null,
    val nullable: Boolean = false,
    override val name: String = "TypedString",
) : Code(), Child {

}
