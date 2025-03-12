package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.AssignedVal
import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.ElseBranch
import ship.f.engine.kotlingen.dsl.types.Fun
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import ship.f.engine.kotlingen.dsl.types.Var
import ship.f.engine.kotlingen.dsl.types.When

interface ValInterface {
    infix fun <T : Any?> Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Public_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Internal_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Private_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
    infix fun <T : Any?> Public_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
    infix fun <T : Any?> Internal_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
    infix fun <T : Any?> Private_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
    infix fun <T> Val<T>.assign(t: ValTypedValue<T>): AssignedVal<T>
    infix fun <R> Val<R>.assign(t: Fun<R>): AssignedVal<Fun<R>>
    infix fun <R> Val<R>.assign(t: When<*, R>): AssignedVal<R>
    infix fun <R> Val<R>.assignWhen(t: When<*, R>): AssignedVal<R>
    infix fun <T> Val<T>.assign(t: ElseBranch<T>): AssignedVal<T>
    infix fun Val<Clazz>.new(clazz: Clazz): Val<Clazz>
    infix fun Val<Clazz>.withTypes(types: List<Bundle<*, *, *>>): Val<Clazz>
    infix fun Val<Clazz>.constructor(types: List<Bundle<*, *, *>>): Val<Clazz>
    infix fun <R> AssignedVal<R>.withTypes(types: List<Bundle<out Any, Any, Any>>): AssignedVal<R>
    infix fun <R> AssignedVal<R>.calls(args: List<Bundle<out Any, out Any, out Any>>): AssignedVal<R>
    infix fun <T> Var<T>.assign(t: ValTypedValue<T>): Var<T>
}