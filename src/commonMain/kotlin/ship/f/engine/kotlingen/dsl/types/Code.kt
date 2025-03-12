package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.types.util.KType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
abstract class Code {
    abstract val name: String
    abstract val id: Uuid
    sealed class Visibility {
        data object Public : Visibility()
        data object Internal : Visibility()
        data object Private : Visibility()
        data object Protected : Visibility()
        data object Unspecified : Visibility()
        fun toCode() = if (this is Unspecified) "" else "${this.toString().lowercase()} "
    }

    // Used for complex types like List<String>
    inline fun <reified T : Any?> t(type: KType): ValTypedValue<T> = ValTypedValue(type = type.type)

    // Used for simple types like String
    inline fun <reified T : Any?> t(): ValTypedValue<T> = ValTypedValue(
        type = T::class.simpleName,
        import = T::class.qualifiedName,
    )

    // Used to create a typed value, the type will not actually get used.
    inline fun <reified T : Any?> v(string: String? = null): ValTypedValue<T> = ValTypedValue(
        type = T::class.simpleName,
        value = string?.let { TypedValue.Value.StringValue(it) }
    )

    fun <T : Any?> v(block: TypedBlock<T>.() -> ValTypedValue<T>): TypedBlock<T> =
        TypedBlock(block = block)

    inline fun <reified T : Any?> wp(string: String? = null): WhenTypedValue<T> = WhenTypedValue(
        type = T::class.simpleName,
        value = string?.let { TypedValue.Value.StringValue(it) }
    )
}