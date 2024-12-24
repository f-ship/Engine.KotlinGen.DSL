package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.TypeAlias
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class TypeAliasDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : TypeAliasInterface {
    override infix fun TypeAlias(a: String) = TypeAlias<Any>(name = a)
    override infix fun <T, R> TypeAlias<T>.assign(t: ValTypedValue<R>) =
        TypeAlias(name = name, type = t).also { addChild(it) }
}