package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.PartialFile
import ship.f.engine.kotlingen.dsl.types.TypedValue
import ship.f.engine.kotlingen.dsl.types.Val
import kotlin.uuid.ExperimentalUuidApi

data class Create(val context: String = "")
val named = Create()

data class Bundle<B, T : Any?>(
    val name: String,
    val type: TypedValue<T>? = null,
    val isNullable: Boolean = false,
    val block: B.() -> Unit = {}
)

// Used to create class, interfaces etc
operator fun <B, T : Any?> String.invoke(block: B.() -> Unit = {}) =
    Bundle<B, T>(name = this, block = block)

//Used to create values and variables
operator fun <T> String.invoke(type: TypedValue<T>) = Bundle<Val<T>.() -> Unit, T>(name = this, type = type)

@OptIn(ExperimentalUuidApi::class)
infix fun Create.EntireFile(file: Bundle<EntireFile, Any>) = EntireFile(names = file.name).apply {
    name = file.name
    block = file.block
}

@OptIn(ExperimentalUuidApi::class)
infix fun Create.PartialFile(file: Bundle<PartialFile, Any>) = PartialFile(names = file.name).apply {
    name = file.name
}
