package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.AssignedVal
import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Internal
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Private
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Public
import ship.f.engine.kotlingen.dsl.types.ElseBranch
import ship.f.engine.kotlingen.dsl.types.Fun
import ship.f.engine.kotlingen.dsl.types.TypedValue
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import ship.f.engine.kotlingen.dsl.types.Var
import ship.f.engine.kotlingen.dsl.types.When
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class ValDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : ValInterface {
    override infix fun <T> Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type).also { addChild(it) }

    override infix fun <T> Public_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type, visibility = Public).also { addChild(it) }

    override infix fun <T> Internal_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type, visibility = Internal).also { addChild(it) }

    override infix fun <T> Private_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type, visibility = Private).also { addChild(it) }

    override infix fun <T> Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type).also { addChild(it) }

    override infix fun <T> Public_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type, visibility = Public).also { addChild(it) }

    override infix fun <T> Internal_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type, visibility = Internal).also { addChild(it) }

    override infix fun <T> Private_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type, visibility = Private).also { addChild(it) }

    override infix fun <T> Val<T>.assign(t: ValTypedValue<T>) =
        AssignedVal(name = name, type = t, id = id).also { addChild(it) }

    override infix fun <R> Val<R>.assign(t: Fun<R>) =
        AssignedVal(name = name, type = v<Fun<R>>(), id = id).also { addChild(it) }

    override infix fun <R> Val<R>.assign(t: When<*, R>) =
        AssignedVal(
            name = name,
            type = ValTypedValue<R>(
                value = TypedValue.Value.CodeValue(t),
                code = t
            ),
            id = id,
        ).also { addChild(it) }

    override infix fun <R> Val<R>.assignWhen(t: When<*, R>) =
        AssignedVal(
            name = name,
            type = ValTypedValue<R>(
                value = TypedValue.Value.CodeValue(t),
                code = t,
            ),
            id = id,
        ).also { addChild(it) }

    override infix fun <T> Val<T>.assign(t: ElseBranch<T>) =
        AssignedVal(
            name = name,
            type = ValTypedValue<T>(value = TypedValue.Value.CodeValue(t)),
            id = id,
        ).also { addChild(it) }

    override infix fun Val<Clazz>.new(clazz: Clazz) = Val<Clazz>(name = clazz.name)
    override infix fun Val<Clazz>.withTypes(types: List<Bundle<*, *, *>>) = this
    override infix fun Val<Clazz>.constructor(types: List<Bundle<*, *, *>>) = this
    override infix fun <R> AssignedVal<R>.withTypes(types: List<Bundle<out Any, Any, Any>>) = this
    override infix fun <R> AssignedVal<R>.calls(args: List<Bundle<out Any, out Any, out Any>>) = this
    override infix fun <T> Var<T>.assign(t: ValTypedValue<T>) = Var(name = name, type = t, id = id).also { addChild(it) }
}