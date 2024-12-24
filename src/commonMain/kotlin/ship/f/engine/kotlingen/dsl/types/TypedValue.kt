package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
sealed class TypedValue<T : Any?> : Code(), Child {
    abstract val value: Value?
    abstract val code: Code?
    abstract val type: String?
    abstract val import: String?
    abstract val nullable: Boolean
    abstract override val name: String
    abstract override val id: Uuid

    sealed class Value {
        data class StringValue(val value: String) : Value()
        data class CodeValue(val value: Code) : Value()
    }
}
