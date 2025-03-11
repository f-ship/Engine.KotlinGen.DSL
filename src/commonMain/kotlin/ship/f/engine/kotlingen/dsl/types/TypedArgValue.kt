package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class TypedArgValue<T : Any?> @OptIn(ExperimentalUuidApi::class) constructor(
    val value: Value? = null,
    val code: Code? = null,
    val type: String? = null,
    val import: String? = null,
    val nullable: Boolean = false,
    override val name: String = "TypedValue",
    override val id: Uuid = Uuid.random(),
) : Code(), Child {
    sealed class Value {
        data class StringValue(val value: String) : Value()
        data class CodeValue(val value: Code) : Value()
    }
}
