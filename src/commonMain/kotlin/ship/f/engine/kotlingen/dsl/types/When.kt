package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child

data class When<R>(
    override val name: String,
    val returnType: TypedString<R>
) : Container(), Child {
    operator fun String.invoke(block: When<R>.() -> TypedString<R>) = block(this@When)
}
