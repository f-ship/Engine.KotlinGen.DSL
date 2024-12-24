package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.Code
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
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class ControlFlowDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : ControlFlowInterface {
    override operator fun <T, R> WhenTypedValue<T>.invoke(block: When<T, R>.(ValTypedValue<T>?) -> WhenBranch<T, R>): When<T, R> =
        When(
            name = "When",
            arg = toValTypedValue(),
            block = block,
        )

    override infix fun <T, R> When(w: When<T, R>): When<T, R> = w.also(addChild)

    override infix fun <R> When(block: When<Unit, R>.(ValTypedValue<Unit>?) -> WhenBranch<Unit, R>): When<Unit, R> =
        When(name = "When", block = block).also { addChild(it) }

    override fun <R> If(
        arg: ValTypedValue<Boolean>,
        block: IfBranch<R>.() -> ValTypedValue<R>
    ): IfBranch<R> {
        return IfBranch(
            statement = arg,
            block = block,
        )
    }

    // IfBranch
    override fun <R> IfBranch<R>.ElseIf(
        statement: ValTypedValue<Boolean>,
        block: ElseIfBranch<R>.() -> ValTypedValue<R>
    ): ElseIfBranch<R> {
        return ElseIfBranch(
            statement = statement,
            block = block,
            previous = this,
        )
    }

    override infix fun <R> IfBranch<R>.Else(
        block: ElseBranch<R>.() -> ValTypedValue<R>
    ): ElseBranch<R> {
        return ElseBranch(
            block = block,
            previous = this,
        )
    }

    override fun <R> ElseIfBranch<R>.Else(
        block: ElseBranch<R>.() -> ValTypedValue<R>
    ): ElseBranch<R> {
        return ElseBranch(
            block = block,
            previous = this,
        )
    }

    override fun For(
        arg: String,
        block: For.() -> Unit
    ): For {
        return For(
            statement = arg,
            block = block,
        ).also { addChild(it) }
    }

    override fun While(
        arg: ValTypedValue<Boolean>,
        block: While.() -> Unit,
    ): While {
        return While(
            statement = arg,
            block = block,
        ).also { addChild(it) }
    }

    override fun DoWhile(
        arg: ValTypedValue<Boolean>,
        block: DoWhile.() -> Unit,
    ): DoWhile {
        return DoWhile(
            statement = arg,
            block = block,
        ).also { addChild(it) }
    }

    private fun <T> WhenTypedValue<T>.toValTypedValue() = ValTypedValue<T>(
        value = value,
        code = code,
        type = type,
        import = import,
        nullable = nullable,
        name = name,
        id = id,
    )
}