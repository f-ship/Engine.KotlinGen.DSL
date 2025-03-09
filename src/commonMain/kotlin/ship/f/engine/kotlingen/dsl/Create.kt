package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.PartialFile
import ship.f.engine.kotlingen.dsl.types.TypedBlock
import ship.f.engine.kotlingen.dsl.types.TypedValue
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.When
import kotlin.uuid.ExperimentalUuidApi

data class Create(val context: String = "")
val named = Create()

data class Bundle<B, T>(
    val name: String,
    val type: TypedValue<T>? = null,
    val isNullable: Boolean = false,
    val block: B.() -> Unit = {}
)

data class Bundle2<BR,BP,R,T>(
    val name: String,
    val type: TypedValue<T>? = null,
    val isNullable: Boolean = false,
    val block: (BR.(BP) -> R)? = null,
)

// Used to create class, interfaces etc
operator fun <B, T : Any?> String.invoke(block: B.() -> Unit = {}) =
    Bundle<B, T>(name = this, block = block)

//Used to create values and variables
operator fun <T> String.invoke(type: TypedValue<T>) = Bundle<Val<T>.() -> Unit, T>(name = this, type = type)

fun <BR,BP,R> String.b(block: (BR.(BP) -> R)) =
    Bundle2<BR,BP,R,Unit>(name = this, block = block)

@OptIn(ExperimentalUuidApi::class)
infix fun Create.EntireFile(file: Bundle<EntireFile, Any>) = EntireFile(name = file.name, block = file.block)

@OptIn(ExperimentalUuidApi::class)
infix fun Create.PartialFile(file: Bundle<PartialFile, Any>) = PartialFile(name = file.name, block = file.block)

@OptIn(ExperimentalUuidApi::class)
class test {
    fun <T, R> WhenX(arg: TypedValue<T>, block: When<T, R>.(T) -> R): When<T, R> {
        return When(name = "When", block = block)
    }

    operator fun <T,R,NR> When<T,R>.invoke(block: When<T,NR>.(T) -> NR): When<T,NR> {
        return When(name = "When", block = block)
    }

    init {
        WhenX(v<String>("hello")) {

        }
    }

    // Used to create a typed value, the type will not actually get used.
    inline fun <reified T : Any?> v(string: String? = null): TypedValue<T> = TypedValue(
        type = T::class.simpleName,
        value = string?.let { TypedValue.Value.StringValue(it) }
    )

    inline fun <reified T : Any?> v(noinline block: TypedBlock<T>.() -> TypedValue<T>): TypedBlock<T> =
        TypedBlock(block = block)
}

