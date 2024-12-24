package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.DoWhile
import ship.f.engine.kotlingen.dsl.types.ElseBranch
import ship.f.engine.kotlingen.dsl.types.ElseIfBranch
import ship.f.engine.kotlingen.dsl.types.For
import ship.f.engine.kotlingen.dsl.types.IfBranch
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import ship.f.engine.kotlingen.dsl.types.When
import ship.f.engine.kotlingen.dsl.types.WhenBranch
import ship.f.engine.kotlingen.dsl.types.WhenTypedValue
import ship.f.engine.kotlingen.dsl.types.While

interface ControlFlowInterface {
    operator fun <T, R> WhenTypedValue<T>.invoke(block: When<T, R>.(ValTypedValue<T>?) -> WhenBranch<T, R>): When<T, R>
    infix fun <T, R> When(w: When<T, R>): When<T, R>
    infix fun <R> When(block: When<Unit, R>.(ValTypedValue<Unit>?) -> WhenBranch<Unit, R>): When<Unit, R>
    fun <R> If(
        arg: ValTypedValue<Boolean>,
        block: IfBranch<R>.() -> ValTypedValue<R>
    ): IfBranch<R>

    fun <R> IfBranch<R>.ElseIf(
        statement: ValTypedValue<Boolean>,
        block: ElseIfBranch<R>.() -> ValTypedValue<R>
    ): ElseIfBranch<R>

    infix fun <R> IfBranch<R>.Else(
        block: ElseBranch<R>.() -> ValTypedValue<R>
    ): ElseBranch<R>

    fun <R> ElseIfBranch<R>.Else(
        block: ElseBranch<R>.() -> ValTypedValue<R>
    ): ElseBranch<R>

    fun For(
        arg: String,
        block: For.() -> Unit
    ): For

    fun While(
        arg: ValTypedValue<Boolean>,
        block: While.() -> Unit,
    ): While

    fun DoWhile(
        arg: ValTypedValue<Boolean>,
        block: DoWhile.() -> Unit,
    ): DoWhile
}