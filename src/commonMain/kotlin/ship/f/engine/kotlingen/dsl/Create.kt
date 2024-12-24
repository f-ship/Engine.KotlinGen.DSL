package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.PartialFile
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import kotlin.uuid.ExperimentalUuidApi

data class Create(val context: String = "")
val named = Create()

data class Bundle<B, T, R>(
    val name: String,
    val type: ValTypedValue<T>? = null,
    val isNullable: Boolean = false,
    val block: (B.(T) -> R)? = null
)

// Used to create class, interfaces etc
operator fun <B, T : Any?> String.invoke(block: B.(T) -> Unit = {}) = Bundle(name = this, block = block)

//Used to create values and variables
operator fun <T> String.invoke(type: ValTypedValue<T>) = Bundle<Val<T>.() -> Unit, T, Unit>(name = this, type = type)

@OptIn(ExperimentalUuidApi::class)
infix fun Create.EntireFile(file: Bundle<EntireFile, Any, Unit>) = EntireFile(name = file.name, block = file.block)

@OptIn(ExperimentalUuidApi::class)
infix fun Create.PartialFile(file: Bundle<PartialFile, Any, Unit>) = PartialFile(name = file.name, block = file.block)
