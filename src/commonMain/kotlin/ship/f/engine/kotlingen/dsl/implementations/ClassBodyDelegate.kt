package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.AssignedVal
import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.TypedBlock
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.Var
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class ClassBodyDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : ClassBodyInterface {
    override infix fun <T> Val<T>.getter(t: TypedBlock<T>) =
        AssignedVal(
            name = name,
            getter = t.copy(definition = "getter"),
            id = id
        ).also { addChild(it) }

    override infix fun <T> Var<T>.getter(t: TypedBlock<T>) =
        Var(name = name, getter = t.copy(definition = "getter"), id = id).also {
            addChild(it)
        }

    override infix fun <T> Var<T>.setter(t: TypedBlock<T>) =
        Var(name = name, setter = t.copy(definition = "setter"), id = id).also {
            addChild(it)
        }
}