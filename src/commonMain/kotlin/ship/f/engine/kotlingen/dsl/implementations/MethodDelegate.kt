package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.Fun
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class MethodDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : MethodInterface {
    override infix fun Fun(n: String) = Fun<Any>(name = n)
    override infix fun <R> Fun(v: Bundle<Val<R>.() -> Unit, R, Unit>): Fun<Fun<R>> = Fun(name = v.name)
    override infix fun <R> Fun<R>.withTypes(types: List<Bundle<out Any, Any, out Any>>): Fun<R> = this
    override infix fun <R> Fun<R>.constructor(args: List<Bundle<out Any, out Any, out Any>>): Fun<R> = this
    override infix fun <R, N> Fun<R>.returns(t: ValTypedValue<N>): Fun<N> = Fun(name = "", returnType = t)
    override infix fun <R> Fun<R>.block(block: Fun<R>.() -> Unit): Fun<R> = this
}