package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class TypedBlock<T : Any?>(
    var typedString: TypedString<T>? = null,
    val block: TypedBlock<T>.() -> TypedString<T> = { TypedString() }, //May not make sense
    override val name: String = typedString?.type ?: "TypedBlock"
) : Container(), Child {

    val named = this

    infix fun TypedBlock<T>.assign(t: TypedString<T>) = t.apply { typedString = this }
}
