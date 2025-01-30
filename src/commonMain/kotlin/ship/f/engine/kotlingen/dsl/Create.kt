package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.PartialFile
import ship.f.engine.kotlingen.dsl.types.TypedString
import ship.f.engine.kotlingen.dsl.types.Val

data class Bundle<B, T : Any?>(
    val name: String,
    val type: TypedString<T>? = null,
    val simpleType: T? = null,
    val isNullable: Boolean = false,
    val block: B.() -> Unit = {}
)
operator fun <B, T : Any?> String.invoke(vararg v: Bundle<*, *>, block: B.() -> Unit = {}) =
    Bundle<B, T>(name = this, block = block)
operator fun <R> String.invoke(v: Bundle<R, Any>) = Bundle<R, Any>(name = this)
operator fun <T> String.invoke(type: T) = Bundle<Val<T>.() -> Unit, T>(name = this, simpleType = type)
operator fun <T> String.invoke(type: TypedString<T>) = Bundle<Val<T>.() -> Unit, T>(name = this, type = type)
operator fun <T> String.invoke() = Bundle<Clazz, T>(name = this)

data class Create(val context: String = "")
infix fun Create.EntireFile(file: Bundle<EntireFile, Any>) = EntireFile(names = file.name).apply {
    name = file.name
    block = file.block
}
infix fun Create.PartialFile(file: Bundle<PartialFile, Any>) = PartialFile(names = file.name).apply { name = file.name }
val named = Create()
