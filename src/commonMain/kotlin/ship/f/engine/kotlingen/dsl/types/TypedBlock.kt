package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class TypedBlock<T : Any?> @OptIn(ExperimentalUuidApi::class) constructor(
    var returnValue: TypedValue<T>? = null,
    val block: TypedBlock<T>.() -> TypedValue<T> = { TypedValue() }, //May not make sense
    val args: List<Bundle<out Any, out Any, out Any>> = listOf(),
    val definition: String = "{",
    override val name: String = returnValue?.type ?: "TypedBlock",
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Container(), Child {

    fun execute() {
        returnValue = block.invoke(this)
    }

    val add = this

    infix fun TypedBlock<T>.assign(t: TypedValue<T>) = t.apply { returnValue = this }
}
